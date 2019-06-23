package com.liuwq.mappers;

import com.liuwq.annotation.Select;
import com.liuwq.entity.UserEntity;

public interface UserMapper {

    @Select("select * from user where id = ?")
    UserEntity getUser(Long id);

    int updateUser(Long id);

}
