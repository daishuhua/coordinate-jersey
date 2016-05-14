/**
 * @Title: RestApplication.java
 * Copyright: Copyright (c) 2016 jxdd 
 */
package com.gatz.dc.api.rest;

import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;



public class RestApplication extends ResourceConfig {
    public RestApplication() {
        packages("com.gatz.dc.api.rest");
        // 注册JSON转换器
        register(JacksonJsonProvider.class);
    }
}
