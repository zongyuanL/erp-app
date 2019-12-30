package com.alex.erp.wechat.filter;

//import reactor.core.publisher.Mono;

//@Configuration
public class CrossOriginFilter {
//	private static final String MAX_AGE = "18000L";
//
//    @Bean
//    public WebFilter corsFilter() {
//        return (ServerWebExchange ctx, WebFilterChain chain) -> {
//            ServerHttpRequest request = ctx.getRequest();
//            if (CorsUtils.isCorsRequest(request)) {
//                HttpHeaders requestHeaders = request.getHeaders();
//                ServerHttpResponse response = ctx.getResponse();
//                HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
//                HttpHeaders headers = response.getHeaders();
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
//                headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders
//                        .getAccessControlRequestHeaders());
//                if(requestMethod != null){
//                    headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
//                }
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
//                headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
//                headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
//                if (request.getMethod() == HttpMethod.OPTIONS) {
//                    response.setStatusCode(HttpStatus.OK);
//                    return Mono.empty();
//                }
//            }
//            return chain.filter(ctx);
//        };
//    }
//    @Bean
//    public ServerCodecConfigurer serverCodecConfigurer() {
//        return new DefaultServerCodecConfigurer();
//    }

}
