package com.mybatis.mapper.user;

import com.mybatis.domain.user.YunKaiUserModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * user dao
 *
 * Created by yunkai on 2017/5/24.
 */
@Mapper
@Component
public interface YunKaiUserMapper {

    int insert(YunKaiUserModel user);

    void delete(@Param("id") Integer id);
}
