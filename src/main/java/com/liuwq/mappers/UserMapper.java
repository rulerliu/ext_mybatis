package com.liuwq.mappers;

import com.liuwq.entity.UserEntity;

public interface UserMapper {

    UserEntity getUser(Long id);

    int updateUser(Long id);

}
