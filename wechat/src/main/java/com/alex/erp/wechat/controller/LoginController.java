package com.alex.erp.wechat.controller;

import com.alex.erp.basic.dic.ResultCode;
import com.alex.erp.basic.vo.Result;
import com.alex.erp.basic.vo.factory.ResultFactory;
import com.alex.erp.dbutil.um.entity.EsMember;
import com.alex.erp.dbutil.um.entity.EsMemberRefreshToken;
import com.alex.erp.dbutil.um.service.IEsMemberRefreshTokenService;
import com.alex.erp.dbutil.um.service.IEsMemberService;
import com.alex.erp.wechat.service.AuthorizeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex ZY Liang
 */
@Controller
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private IEsMemberService memberService;


	@Autowired
	private IEsMemberRefreshTokenService memberRefreshTokenService;

	@Autowired
	private AuthorizeService authorizeService;

//	@Autowired
//	private MemberService memberService;
//	@Value("${GET_TOKEN_URL}")
//	private String GET_TOKEN_URL;
//	@Autowired
//	private MemberRefreshTokenService memberRefreshTokenService;

	@RequestMapping(value="checkLogin",method=RequestMethod.POST)
	@ApiOperation(value="检查登录接口", httpMethod = "POST")
	@ResponseBody
	public Result checkLogin(String openID, HttpServletRequest request){

		//判断用户
		EsMember member =
				memberService
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
	@RequestMapping("/login")
	public String toLogin(){
		return "login";
	}


	@PostMapping(value = "/appLogin")
	@ResponseBody
	public Result authrize(@RequestBody Map<String,String> object) {

		String openID = object.get("openID");
		object.remove("openID");
		Result result = authorizeService.login(object);
		EsMember member = (EsMember) result.getData();

		member.setOpenID(openID);

		memberService.lambdaUpdate().eq(EsMember::getId,member.getId()).set(EsMember::getOpenID,openID);
		memberRefreshTokenService
				.lambdaUpdate().eq(EsMemberRefreshToken::getMemberId,member.getId()).remove();

		EsMemberRefreshToken mrt = new EsMemberRefreshToken();
		mrt.setMemberId(member.getId());
		mrt.setRefreshToken(member.getRefreshToken());
		memberRefreshTokenService.save(mrt);

		return result;
	}
	@PostMapping(value = "/logout",produces = "application/json; charset=utf-8")
	@ResponseBody
	public Result logout(HttpServletRequest request){
		String openID = request.getSession().getAttribute("openID").toString();
		EsMember m =
				memberService
						.lambdaUpdate()
						.eq(EsMember::getOpenID,openID)
						.set(EsMember::getOpenID,null)
						.getEntity();
		boolean flag = memberService.updateById(m);
		QueryWrapper<EsMemberRefreshToken> mr = new QueryWrapper();
		mr.eq("member_id", m.getId());
		boolean flag1 = memberRefreshTokenService.remove(mr);
		return ResultFactory.buildSuccessResult("退出成功");
	}
}
