package com.alex.erp.wechat.config;

import com.alex.erp.basic.web.HttpClientUtil;
import com.alex.erp.wechat.dto.AccessToken;
import com.alex.erp.wechat.dto.AccessTokenInfo;
import com.alex.erp.wechat.dto.JsapiTicket;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-07-13 3:38 AM
 */
@Log4j2
public class TokenConfig {
	

    private final String appId=WechatConstants.APP_ID;
    private final String appSecret=WechatConstants.APP_SECRET;
    public TokenConfig() {
        // 开启一个新的线程
    	/*Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					getIndex();
					try {
						Thread.sleep(180 * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});*/
    	//thread.start();
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("wechat-token-%d").build();
        ExecutorService singleThreadExecutor = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        singleThreadExecutor.execute(() -> {
            while (true) {
                try {
                	
                    //获取accessToken
                    AccessTokenInfo.accessToken = getAccessToken(appId, appSecret);
                    AccessTokenInfo.jsapiTicket = getJsapiTicket( AccessTokenInfo.accessToken).generateSignature("");
                    //获取成功
                    if (AccessTokenInfo.accessToken != null) {
                        //获取到access_token 休眠7000秒,大约2个小时左右
                        Thread.sleep(7000 * 1000);
                        //Thread.sleep(10 * 1000);//10秒钟获取一次
                    } else {
                        //获取失败
                        //获取的access_token为空 休眠3秒
                        Thread.sleep(1000 * 3);
                    }
                } catch (Exception e) {
                	e.printStackTrace();
                    log.warn("发生异常：" + e.getMessage());
                    try {
                        //发生异常休眠1秒
                        Thread.sleep(1000 * 10);
                    } catch (Exception e1) {

                    }
                }
            }
        });
    }

    /**
     * 获取access_token
     *
     * @return AccessToken
     */
    private AccessToken getAccessToken (String appId, String appSecret){
        HttpClientUtil netHelper = new HttpClientUtil();
        /**
         * 接口地址为https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET，其中grant_type固定写为client_credential即可。
         */
        String Url = String.format(WechatConstants.GET_TOKEN_URL, appId, appSecret);
        //此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
        String result = netHelper.getHttpsResponse(Url, "");
        log.info("获取到的access_token=" + result);
        //使用FastJson将Json字符串解析成Json对象
        JSONObject json = JSON.parseObject(result);
        AccessToken token = new AccessToken();
        token.setAccessToken(json.getString("access_token"));
        token.setExpiresin(json.getInteger("expires_in"));
        return token;
    }

    /**
     * 获取access_token
     *
     * @return AccessToken
     */
    private JsapiTicket getJsapiTicket (AccessToken accessToken){
        HttpClientUtil netHelper = new HttpClientUtil();
        /**
         * 接口地址为https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET，其中grant_type固定写为client_credential即可。
         */
        String Url = String.format(WechatConstants.GET_JSAPI_TICKET_URL, accessToken.getAccessToken());
        //此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
        String result = netHelper.getHttpsResponse(Url, "");
        log.info("获取到的access_token=" + result);
        //使用FastJson将Json字符串解析成Json对象
        JSONObject json = JSON.parseObject(result);
        JsapiTicket ticket = new JsapiTicket();
        ticket.setJsapiTicket(json.getString("ticket"));
        return ticket;
    }


//    private
    private String getIndex(){
    	HttpClientUtil netHelper = new HttpClientUtil();
        /**
         * 接口地址为https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET，其中grant_type固定写为client_credential即可。
         */
    	String Url = String.format("http://192.168.0.105:9020/app/getIndex", appId, appSecret);
        //此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
        String result;
		try {
			result = netHelper.sendGetData(Url, "");
			System.out.println(result);
		    log.info("获取到的access_token=" + result);
		    return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        //使用FastJson将Json字符串解析成Json对象
        return null;
    }
}
