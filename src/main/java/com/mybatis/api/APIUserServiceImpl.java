package com.mybatis.api;

import com.mybatis.response.RpcResult;
import com.mybatis.service.user.YunKaiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 对外api
 *
 * Created by yunkai on 2018/1/27.
 */
@Service("apiUserService")
public class APIUserServiceImpl implements APIUserService{

    @Autowired
    private YunKaiUserService yunKaiUserService;

    /**
     * 新增用户
     *
     * @param userName  用户名称
     * @param userPhone  用户联系方式
     * @return
     */
    @Override
    public RpcResult<Integer> saveUser(String userName, String userPhone){
        return yunKaiUserService.saveUser(userName, userPhone);
    }
}
