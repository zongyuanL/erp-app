package com.alex.erp.wechat.controller;

import com.alex.erp.basic.dic.ResultCode;
import com.alex.erp.basic.vo.Result;
import com.alex.erp.basic.vo.factory.ResultFactory;
import com.alex.erp.basic.web.HttpClientUtil;
import com.alex.erp.dbutil.um.entity.EsMember;
import com.alex.erp.dbutil.um.entity.EsMemberRefreshToken;
import com.alex.erp.dbutil.um.service.IEsMemberRefreshTokenService;
import com.alex.erp.dbutil.um.service.IEsMemberService;
import com.alex.erp.wechat.config.WechatConstants;
import com.alex.erp.wechat.service.AuthorizeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class WechatCodeController {
	@Autowired
	private IEsMemberService userService;

	@Autowired
	private AuthorizeService authorizeService;

	@Autowired
	private IEsMemberRefreshTokenService memberRefreshTokenService;
	@GetMapping("/getCode")
	//@ResponseBody
	public ModelAndView getCode(String code,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Map<String,String> param = new HashMap<String,String>();
		param.put("appid", WechatConstants.APP_ID);
		param.put("secret", WechatConstants.APP_SECRET);
		param.put("code", code);
		param.put("grant_type", "authorization_code");
		try {
			Result result =  HttpClientUtil.sendPostDataByMap("https://api.weixin.qq.com/sns/oauth2/access_token", param, "UTF-8");
			JSONObject obj = JSONObject.parseObject(result.getData().toString());
			String access_token = obj.getString("access_token");
			String openid = obj.getString("openid");
			
			Map<String,String> param1 = new HashMap<String,String>();
			param1.put("access_token", access_token);
			param1.put("openid", openid);
			param1.put("lang", "zh_CN");
			Result result1 = HttpClientUtil.sendPostDataByMap("https://api.weixin.qq.com/sns/userinfo", param1, "UTF-8");
			if(!StringUtils.isEmpty(openid)){
				Result res = checkLogin(openid,request);
				if(res.getCode()==ResultCode.SUCCESS.getCode()){
					mv.addObject("data", JSONObject.toJSONString(result1));
					mv.setViewName("index");
				}else{
					mv.setViewName("login");
				}
			}else{
				mv.setViewName("error");
				mv.addObject("errdata", result1);
			}
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("error");
			mv.addObject("errdata", e.getMessage());
			return mv;
		}
	}


	public Result checkLogin(String openID, HttpServletRequest request){

		//判断用户
		EsMember member =
				userService
						.lambdaQuery()
						.eq(EsMember::getOpenID,openID).one();
		//.getEntity();
		if (member == null) {
			return ResultFactory.buildFailResult(ResultCode.NOT_LOGIN);
		}

		EsMemberRefreshToken mrt =
				memberRefreshTokenService
						.lambdaQuery()
						.eq(EsMemberRefreshToken::getMemberId,member.getId()).one();
//						.getEntity();

		if(mrt==null){
			return ResultFactory.buildFailResult(ResultCode.NOT_LOGIN);
		}




		Map<String,String> json = new HashMap<String,String>();
		json.put("client_id", "umService");
		json.put("client_secret", "user123");
		json.put("grant_type", "refresh_token");
		json.put("refresh_token", mrt.getRefreshToken());

		Result result = authorizeService.login(json);

		if(result.getCode()!=ResultCode.SUCCESS.getCode()) {
			return ResultFactory.buildFailResult(ResultCode.NOT_LOGIN);
		}

		JSONObject userObj = JSONObject.parseObject(result.getData().toString());

		String access_token = userObj.getString("access_token");
		String refresh_token = userObj.getString("refresh_token");
		mrt.setRefreshToken(refresh_token);
		memberRefreshTokenService.updateById(mrt);

		request.getSession().setAttribute("openID", openID);
		request.getSession().setAttribute("access_token", access_token);

		JSONObject info = new JSONObject();
		info.put("name", member.getMemberName());
		info.put("sex", member.getSex());
		return ResultFactory.buildSuccessResult(userObj);


	}

	@GetMapping("/getIndex")
	@ResponseBody
	public String getIndex(){
		return "测试调用";
	}
	@GetMapping("/index")
	public String toIndex(String openID){
		return "index";
	}
}
