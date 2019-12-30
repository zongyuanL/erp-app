package com.alex.erp.wechat.templates;

import com.alex.erp.basic.web.HttpClientUtil;
import com.alex.erp.wechat.config.WechatConstants;
import com.alex.erp.wechat.dto.AccessTokenInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class MessageTemplate {
	static Logger log = LoggerFactory.getLogger(MessageTemplate.class);
	
	public  static String pushMessage(String toUser,String redirectUrl,String templeID,Map<String,String> keywords){
		JSONObject obj = new JSONObject();
		obj.put("touser", toUser);
		obj.put("template_id", templeID);
		obj.put("url", redirectUrl);
		obj.put("topcolor", "#FF0000");
		JSONObject data = new JSONObject();
		for (String key : keywords.keySet()) {
			JSONObject par = new JSONObject();
			par.put("value",keywords.get(key));
			par.put("color","#000000");
			data.put(key, par);
		}
		obj.put("data", data);
		String sendMessageUrl = String.format(WechatConstants.POST_TEMPLATE_SEND_MESSAGE_UR,AccessTokenInfo.accessToken.getAccessToken());
        log.info(sendMessageUrl);
        log.info(obj.toJSONString());
        String aa;
		try {
			aa = HttpClientUtil.sendPostDataByJson(sendMessageUrl , obj.toJSONString(),"UTF-8");
			log.info("1.推送消息请求微信接口=="+aa);
	        log.info("2.推送消息请求微信接口=="+JSON.toJSONString(aa));
	        return aa;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
