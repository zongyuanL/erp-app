package com.alex.erp.wechat.config;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-07-15 11:08 AM
 */
public class WechatConstants {

    public final static String POST_SEND_MESSAGE_UR="https://api.weixin.qq.com/cgi-bin/message/send?access_token=%s";
    public final static String POST_TEMPLATE_SEND_MESSAGE_UR="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

    public final static String GET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    public final static String APP_ID="wx1a1bf2182af25af1";
    public final static String APP_SECRET="9751e826bd39e7420320b27ef1ed1e04";

//    public final static String APP_ID="ww8de6389a2b292b58";
//    public final static String APP_SECRET="YdfUnZSt1vWFVNh6oFpPQWZN99POkgQMuamgEO-Argw";

    //DEEC
//	public static final String APP_ID = "wxb42848d2475e353e";
//	public static final String APP_SECRET = "9b79e82841b9ee6fe8405f900c615332";
//	public static final String MCH_ID = "1504214931";
//	public static final String PARTNER_KEY = "puzhili513030199206212618puzhili";
//	public static final String NOTIFY_URL = "https://mobile.sczysfx.com/app/pay/paySucessNotify.do";


    public final static String MESSAGE_TEMPLATE_ID="U1n86Eod8hw7ci4sMWFhsXmpBAZI7LZ70kwEVQaf2pY";


    //本次测试消息是{{message.DATA}},测试时间是{{testDate.DATA}},测试链接是{{testLink.DATA}}.
    public final static String MESSAGE_TENPLATE =
            "{\"touser\":\"%s\"," +
                "\"template_id\":\"U1n86Eod8hw7ci4sMWFhsXmpBAZI7LZ70kwEVQaf2pY\"," +
                "\"url\":\"http://www.163.com\"," +
                "\"topcolor\":\"#FF0000\"," +
                    "\"data\":{" +
                    "\"message\": {\"value\":\"ceshi xiaoxi\",\"color\":\"#173177\"}," +
                    "\"testDate\":{\"value\":\"06月07日 19时24分\",\"color\":\"#000000\"}," +
                    "\"testLink\":{\"value\":\"http://www.sina.com\",\"color\":\"#173177\"}}}";
}
