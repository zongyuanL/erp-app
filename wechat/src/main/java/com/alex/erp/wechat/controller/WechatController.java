package com.alex.erp.wechat.controller;


import com.alex.erp.wechat.templates.MessageTemplate;
import com.alex.erp.wechat.templates.TemplateConstants;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
public class WechatController {

    private final String TOKEN = "alex";

    @ApiOperation(value="微信认证接口",notes = "带权限登录", httpMethod = "GET")
    @GetMapping("/openApi/authorize")
    public String authorize(
            @RequestParam(value="signature") String signature,
            @RequestParam(value="timestamp") String timestamp,
            @RequestParam(value="nonce") String nonce,
            @RequestParam(value="echostr") String echostr){
        log.info("开始校验签名");
        /**
         * 接收微信服务器发送请求时传递过来的4个参数
         */

        //排序
        String sortString = sort(TOKEN, timestamp, nonce);
        //加密
        String mySignature = sha1(sortString);
        //校验签名
        if (mySignature != null && mySignature != "" && mySignature.equals(signature)) {
            log.info("签名校验通过");
            //如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
            //response.getWriter().println(echostr);
            return (echostr);
        } else {
            log.info("签名校验失败");
            return null;
        }
    }

   /* @ApiOperation(value="微信消息接口",notes = "带权限登录", httpMethod = "POST")
    @PostMapping("/openApi/authorize")
    public String message(HttpServletRequest request){
        // TODO 接收、处理、响应由微信服务器转发的用户发送给公众帐号的消息
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("不支持的字符格式");
        }
//        response.setCharacterEncoding("UTF-8");
        log.info("请求进入");
        String result = "";
        try {
            Map<String,String> map = MessageHandlerUtil.parseXml(request);
            log.info("开始构造消息");
            result = MessageHandlerUtil.buildXml(map);
            log.info("返回消息：%s",result);
            if(result.equals("")){
                result = "未正确响应";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("发生异常：%s",e.getMessage());
        }
//        response.getWriter().println(result);
        return result;
    }

    @ApiOperation(value="微信消息接口",notes = "带权限登录", httpMethod = "GET")
    @GetMapping("/openApi/sendMessage")
    public String sendMessage(
            @RequestParam(value="name") String userId,
            @RequestParam(value="message",required = false) String message){

//        String userId="107944";//推送给具体某个人的userid
        String departmentId="";
        //String msgtype="mytext";
        //WxConstants.AGENTID是应用的agenid
        String agentid=WechatConstants.APP_ID;
        String content;
        if(message == null){

            content = "测试消息推送：你的快递已到，请携带工卡前往邮件中心领取。\n出发前可查看<a href=\"http://work.weixin.qq.com\">邮件中心视频实况</a>，聪明避开排队。";
        }else{
            content = message;
        }

        JSONObject params = new JSONObject();
        params.put("touser", userId);
//      params.put("toparty", departmentId);
        params.put("agentid", agentid);
        JSONObject mytext = new JSONObject();
        mytext.put("content",content);
        params.put("msgtype", "text");
        params.put("text",mytext);

        try {

            String sendMessageUrl = String.format(WechatConstants.POST_SEND_MESSAGE_UR,AccessTokenInfo.accessToken.getAccessToken());
            log.info(sendMessageUrl);
            String aa=HttpClientUtil.sendPostDataByJson(sendMessageUrl , params.toJSONString(),"UTF-8");
            log.info("1.推送消息请求微信接口=="+aa);
            log.info("2.推送消息请求微信接口=="+JSON.toJSONString(aa));
            return aa;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }*/
    @ApiOperation(value="微信消息接口",notes = "带权限登录", httpMethod = "GET")
    @GetMapping("/openApi/sendMessageByTemplate")
    public String sendMessageByTemplate(
            @RequestParam(value="name") String userId,@RequestParam(value="title") String title,
            @RequestParam(value="message",required = false) String message){
    	Map<String,String> params = new HashMap<>();
    	params.put("first", title);
    	params.put("keyword1", "微信APP端");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	params.put("keyword2", sdf.format(new Date()));
    	params.put("remark", message);
    	String redirectUrl = "https://www.baidu.com";
    	String result = MessageTemplate.pushMessage(userId, redirectUrl, TemplateConstants.MESSAGE_INFO, params);
        return result;
    }

    /**
     * 排序方法
     *
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    private String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }

        return sb.toString();
    }

    /**
     * 将字符串进行sha1加密
     *
     * @param str 需要加密的字符串
     * @return 加密后的内容
     */
    private String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";}
}
