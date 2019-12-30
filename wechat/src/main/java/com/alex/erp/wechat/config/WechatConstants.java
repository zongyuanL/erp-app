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
    public final static String GET_JSAPI_TICKET_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
//    public final static String APP_ID="wxb86f91df6604ce35";
//    public final static String APP_SECRET="7869564fcb7b104e22890c625bf94463";
//    public final static String MESSAGE_TEMPLATE_ID="U1n86Eod8hw7ci4sMWFhsXmpBAZI7LZ70kwEVQaf2pY";



    public final static String APP_ID="wx1a1bf2182af25af1";
    public final static String APP_SECRET="9751e826bd39e7420320b27ef1ed1e04";
    public final static String MESSAGE_TEMPLATE_ID="U1n86Eod8hw7ci4sMWFhsXmpBAZI7LZ70kwEVQaf2pY";


    //本次测试消息是{{message.DATA}},测试时间是{{testDate.DATA}},测试链接是{{testLink.DATA}}.
    public final static String MESSAGE_TENPLATE =
            "{\"touser\":\"%s\"," +
                "\"template_id\":\"6jK0-KgGO3IVWUM6xVB9FnVCGYMeD2GUT_nsDVKDXXg\"," +
                "\"url\":\"http://kzh4mf.natappfree.cc/index.html\"," +
                "\"topcolor\":\"#FF0000\"," +
                    "\"data\":{" +
                    "\"first\": {\"value\":\"您进行了微信扫一扫登录操作\",\"color\":\"#173177\"}," +
                    "\"keyword1\":{\"value\":\"yin.xiaogang\",\"color\":\"#000000\"}," +
                    "\"keyword2\":{\"value\":\"OA系统\",\"color\":\"#000000\"}," +
                    "\"keyword3\":{\"value\":\"2019-07-25 10:06:32 \",\"color\":\"#000000\"}," +
                    "\"remark\":{\"value\":\"如有疑问，请致电IT服务台400-888-8888 或 关注公众号在线反馈\",\"color\":\"#173177\"}}}";
}
