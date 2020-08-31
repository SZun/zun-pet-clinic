package com.zun.petclinic.models;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
}
