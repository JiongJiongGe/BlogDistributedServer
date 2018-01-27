package com.mybatis.service.impl.user;

import com.mybatis.domain.user.YunKaiUserModel;
import com.mybatis.mapper.user.YunKaiUserMapper;
import com.mybatis.response.ErrorCode;
import com.mybatis.response.RpcResult;
import com.mybatis.service.user.YunKaiUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yunkai on 2017/5/24.
 */
@Service
@Transactional
public class YunKaiUserServiceImpl implements YunKaiUserService {

    private static final Logger logger = LoggerFactory.getLogger(YunKaiUserServiceImpl.class);

    @Autowired
    private YunKaiUserMapper yunKaiUserMapper;

    /**
     * 新增用户
     *
     * @param userName 用户名称
     * @param userPhone 用户联系方式
     * @return
     */
    @Override
    @Transactional
    public RpcResult<Integer> saveUser(String userName, String userPhone) {
        YunKaiUserModel user = new YunKaiUserModel();
        user.setUserName(userName);
        user.setUserPhone(userPhone);
        Integer resultId = yunKaiUserMapper.insert(user);
        if(resultId < 1){
            return RpcResult.ofError(ErrorCode.BIZ_ERROR.getCode(), ErrorCode.BIZ_ERROR.getMsg("新增用户"));
        }
        logger.info("userId = {}", user.getId());
        return RpcResult.ofSuccess(user.getId());
    }

    /**
     * 删除用户，将用户的is_del 数据置为1
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public RpcResult delete(Integer id){
        yunKaiUserMapper.delete(id);
        return RpcResult.ofSuccess("");
    }
}
