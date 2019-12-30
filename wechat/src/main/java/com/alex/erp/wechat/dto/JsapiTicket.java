package com.alex.erp.wechat.dto;

import com.alex.erp.basic.utils.StringUtils;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-28 6:08 PM
 */

@Data
@Log4j2
public class JsapiTicket {


    private String nonceStr;
    private String timeStamp;
    private String signature;
    private String jsapiTicket;


    public JsapiTicket generateSignature(String url) {
        //这里的jsapi_ticket是获取的jsapi_ticket。
        this.nonceStr = StringUtils.createNonceString();
        this.timeStamp = StringUtils.createTimestamp();
        //注意这里参数名必须全部小写，且必须有序
        String str = "jsapi_ticket=" + this.jsapiTicket +
                "&noncestr=" + this.nonceStr +
                "&timestamp=" + this.timeStamp +
                "&url=" + url;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            this.signature = StringUtils.byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            log.warn(e.getLocalizedMessage());
        } catch (UnsupportedEncodingException e) {
            log.warn(e.getLocalizedMessage());
        }
        return this;

    }

}
