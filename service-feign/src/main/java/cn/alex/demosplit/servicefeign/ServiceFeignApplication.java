package cn.alex.demosplit.servicefeign;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2
public class ServiceFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceFeignApplication.class, args);
	}

//
//
//    @RequestMapping(value = "/hi",method = RequestMethod.GET)
//    String sayHiFromClientOne(@RequestParam(value = "name") String name);
//
//
//    /**
//     * 登录控制器，前后端分离用的不同协议和端口，所以需要加入@CrossOrigin支持跨域。
//     * 给VueLoginInfoVo对象加入@Valid注解，并在参数中加入BindingResult来获取错误信息。
//     * 在逻辑处理中我们判断BindingResult知否含有错误信息，如果有错误信息，则直接返回错误信息。
//     */
//    @CrossOrigin
//    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public Result login(@Valid @RequestBody LoginInfoVo loginInfoVo, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            String message = String.format("登陆失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
//            return ResultFactory.buildFailResult(message);
//        }
//        if (!Objects.equals("javalsj", loginInfoVo.getUsername()) || !Objects.equals("123456", loginInfoVo.getPassword())) {
//            String message = String.format("登陆失败，详细信息[用户名、密码信息不正确]。");
//            return ResultFactory.buildFailResult(message);
//        }
//        return ResultFactory.buildSuccessResult("登陆成功。");
//    }

}
