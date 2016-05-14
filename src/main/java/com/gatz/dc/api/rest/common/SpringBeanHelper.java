/**
 * @Title: SpringBeanHelper.java
 * Copyright: Copyright (c) 2016 jxdd 
 */
package com.gatz.dc.api.rest.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringBeanHelper<BR>
 * 从spring bean容器中取出bean实例
 * 
 * @author daish
 * @date 2016年5月13日
 * @version V1.0
 */
@Component
public class SpringBeanHelper implements ApplicationContextAware {
	

	private static ApplicationContext applicationContext;
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanHelper.applicationContext = applicationContext;
	}

	/**
	 * 从spring bean容器中取出bean实例
	 * 
	 * @param name bean name
	 * @return bean
	 */
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
}
