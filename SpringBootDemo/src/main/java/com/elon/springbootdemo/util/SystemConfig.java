package com.elon.springbootdemo.util;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * 配置Spring boot支持在查询参数中加{}[]字符。
 * @author elon
 * @version 2019年1月6日
 */
@Configuration
public class SystemConfig {

    public EmbeddedServletContainerFactory webServerFactory() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            
            @Override
            public void customize(Connector connector) {
                connector.setProperty("relaxedQueryChars", "[]{}");
            }
        });
        
        return factory;
    }
}
