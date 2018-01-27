package com.mybatis.hessian;

import com.mybatis.api.APIUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

/**
 * Created by yunkai on 2017/9/19.
 */
@Configuration
public class HessionExportConfig {

    @Autowired
    private APIUserService apiUserService;

    @Bean(name = "/apiUserServiceExporter.hessian")
    public HessianServiceExporter accountService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(apiUserService);
        exporter.setServiceInterface(APIUserService.class);
        return exporter;
    }

}
