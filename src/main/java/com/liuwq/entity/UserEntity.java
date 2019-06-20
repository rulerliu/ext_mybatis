package com.liuwq.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class UserEntity implements Serializable {
    private Long id;
    private String name;
    private Integer age;

    public UserEntity() {

    }

    public UserEntity(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
