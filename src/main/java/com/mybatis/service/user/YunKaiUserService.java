package com.mybatis.service.user;

import com.mybatis.domain.user.YunKaiUserModel;
import com.mybatis.response.RpcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * user service
 *
 * Created by yunkai on 2017/5/24.
 */
public interface YunKaiUserService {

    /**
     * 保存用户信息
     *
     * @param userName 用户名称
     * @param userPhone 用户联系方式
     * @return
     */
    RpcResult<Integer> saveUser (String userName, String userPhone);

    /**
     * 删除用户，将用户的is_del 数据置为1
     *
     * @param id
     * @return
     */
    RpcResult delete(Integer id);

}
