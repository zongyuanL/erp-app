package com.alex.erp.basic.dic;

import com.alex.erp.basic.baseconfig.utils.StringUtils;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-07-01 3:57 PM
 */
public class StaticParams {
    public static class USERROLE {
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_USER = "ROLE_USER";
        public static final String ROLE_SUPER = "ROLE_SUPER";
    }

    private final static String getPathRex(String path) {
        return "/" + path + "/**";
    }

    private final static String getPathRexWithSuffix(String path) {
        return "/**/*." + path;
    }


    public static class PATHREGX {


        public static final String ALL = getPathRex(PATH.ALL);
        public static final String VIEW = getPathRex(PATH.VIEW);
        public static final String ADMIN = getPathRex(PATH.ADMIN);
        public static final String API = getPathRex(PATH.API);
        public static final String RESOURCE = getPathRex(PATH.RESOURCE);
        public static final String STATIC = getPathRex(PATH.STATIC);
        public static final String PREFIX_JS = getPathRex(PATH.JS);
        public static final String SUFFIX_JS = getPathRexWithSuffix(PATH.JS);
        public static final String CSS = getPathRex(PATH.CSS);
        public static final String IMG = getPathRex(PATH.IMG);
        public static final String ACTIVITI=getPathRex(PATH.ACTIVITI);
        public static final String HTML = getPathRexWithSuffix(PATH.HTML);


        public static String[] getBasicResource() {
            return new String[]{
                    RESOURCE, STATIC, IMG, CSS, PREFIX_JS, SUFFIX_JS, HTML,ACTIVITI
            };
        }

    }


    public static class AUTHPATH {

        public static final String ERROR = getPathRex(PATH.ERROR);
        public static final String LOGIN = getPathRex(PATH.LOGIN);
        public static final String APP_LOGIN = getPathRex(PATH.APP_LOGIN);
        public static final String OAUTH = getPathRex(OAUTHPATH.OAUTH);

        public static String[] getAuthResource() {
            return new String[]{
                     ERROR, LOGIN, APP_LOGIN, OAUTH
            };
        }

    }

    public static class PATH {
        public static final String ALL = "**";
        public static final String VIEW = "view";
        public static final String ADMIN = "admin";
        public static final String API = "api";
        public static final String RESOURCE = "resource";
        public static final String STATIC = "static";
        public static final String JS = "js";
        public static final String CSS = "css";
        public static final String IMG = "img";
        public static final String HTML = "html";
        public static final String LOGIN = "login";
        public static final String APP_LOGIN = "appLogin";
        public static final String ERROR = "error";
        public static final String ACTIVITI="activiti";
        public static final String WEBSOCKET_SERVICE = "websocket-service";

        public static final String WEBSOCKET = "websocket";

    }

    public static class OAUTHPATH {
        public static final String OAUTH = "oauth";

    }

    /**
     * @Package: com.alex.erp.basic.dic<br>
     * @ClassName: SWAGGERUI<br>
     * @Description: swagger限制地址<br>
     */
    public static class SWAGGERUI {
        public static final String MAIN = "/**/swagger-ui.html";
        public static final String RESOURCES = "/**/swagger-resources/**";
        public static final String API_DOCS = "/**/api-docs";
        public static final String STATIC_RESOURCES = "/**/webjars/springfox-swagger-ui/**";

        /**
         * @return
         * @Title: getSwaggerResource
         * @Description: 获取swagger对应ui资源
         */
        public static String[] getSwaggerResource() {
            return new String[]{MAIN, RESOURCES, API_DOCS, STATIC_RESOURCES};
        }
    }




    public static String[] getIgnorePath() {
        String[] ignorePathes = StringUtils.joinStringArray(
                PATHREGX.getBasicResource(),
                AUTHPATH.getAuthResource(),
                SWAGGERUI.getSwaggerResource());
        return ignorePathes;

    }


}
