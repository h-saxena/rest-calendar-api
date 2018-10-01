package com.hemant.cal.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="APP_CALENDAR")
@ApiModel(description="AppCal details. ")
public class AppCal {

    @Id
    @GeneratedValue
    Long id;

    String name;
    String user;

    public AppCal() {
    }

    public AppCal(Long id, String name, String user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
