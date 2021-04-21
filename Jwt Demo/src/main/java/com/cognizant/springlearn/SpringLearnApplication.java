package com.cognizant.springlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLearnApplication.class, args);
		//SpringLearnApplication SpringApplication1 = new SpringLearnApplication();
		
		

	}

	/*
	 * void displayDate() {
	 * 
	 * Date date = new Date(); ApplicationContext context = new
	 * ClassPathXmlApplicationContext("date-format.xml"); SimpleDateFormat format =
	 * context.getBean("dateFormat", SimpleDateFormat.class); String format2 =
	 * format.format(date); System.out.println(format2); }
	 */

	

}
