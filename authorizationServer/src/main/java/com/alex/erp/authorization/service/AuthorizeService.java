package com.alex.erp.authorization.service;

import com.alex.erp.authorization.vo.LogVo;
import com.alex.erp.basic.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(value = "authorization")
public interface AuthorizeService {
    @RequestMapping(value = "/oauth/token",method = RequestMethod.POST)
    Result authorize(@RequestBody LogVo loginInfoVo);
}
