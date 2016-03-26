package org.jp.spark;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.jp.spark.config.WebConfig;
import org.jp.spark.service.DummyService;
import org.jp.spark.service.HomeAssistantService;

@Configuration
@ComponentScan({ "org.jp.spark" })
public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
    	
		new WebConfig(ctx.getBean(DummyService.class),ctx.getBean(HomeAssistantService.class));
		
        ctx.registerShutdownHook();
		
		System.out.println("goodbay");
	}
}
