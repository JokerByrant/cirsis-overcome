package com.cloudfish.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 使用阿里巴巴的Druid配置多数据源
 * 需要在配置文件中指定，spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
 * @author FengJuan
 * @date 2019年5月5日
 * @Description 
 *
 */
@Configuration
public class DruidConfig {	
	
	@Primary
	@Bean(name = "primaryDataSource")
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix="spring.datasource.master")
	public DataSource druidPrimary(){		
		return new DruidDataSource();	
	}	
	
	@Bean(name = "readonlyDataSource")
	@Qualifier("readonlyDataSource")
	@ConfigurationProperties(prefix="spring.datasource.slave")
	public DataSource druidReadonly(){		
		return new DruidDataSource();	
	}
	
	/**
	 * 配置Druid的监控
	 * 1.配置一个管理后台的Servlet,对数据库进行可视化管理
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean	
	public ServletRegistrationBean statViewServlet(){		
		ServletRegistrationBean bean =new ServletRegistrationBean(new StatViewServlet()	, "/druid/*");				
		Map<String,String> initParams=new HashMap<>();				
		initParams.put("loginUsername", "root");		
		initParams.put("loginPassword", "admin");		
		initParams.put("allow", "");//默认就是允许所有访问。		
		//initParams.put("deny","");
		//拒绝访问的ip				
		bean.setInitParameters(initParams);		
		return bean;	
	}		
	
	/**
	 * 2.配置一个监控的filter	
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean	
	public FilterRegistrationBean webStatFilter(){		
		FilterRegistrationBean bean =new FilterRegistrationBean();		
		bean.setFilter(new WebStatFilter());		
		Map<String,String> initParams=new HashMap<>();				
		initParams.put("exclusions", "*.js,*.css,/druid/*");		
		bean.setInitParameters(initParams);				
		//拦截所有请求		
		bean.setUrlPatterns(Arrays.asList("/*"));				
		return bean;	
		
	}	
}


