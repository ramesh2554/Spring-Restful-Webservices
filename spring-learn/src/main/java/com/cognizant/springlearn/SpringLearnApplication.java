package com.cognizant.springlearn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SpringLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLearnApplication.class, args);
		//SpringLearnApplication SpringApplication1 = new SpringLearnApplication();
		displayDate();
		displayCountry();
		displayCountries();
		

	}

	/*
	 * void displayDate() {
	 * 
	 * Date date = new Date(); ApplicationContext context = new
	 * ClassPathXmlApplicationContext("date-format.xml"); SimpleDateFormat format =
	 * context.getBean("dateFormat", SimpleDateFormat.class); String format2 =
	 * format.format(date); System.out.println(format2); }
	 */

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	public static void displayDate() {
		LOGGER.info("START");
		ApplicationContext context=new ClassPathXmlApplicationContext("date-format.xml");
		SimpleDateFormat format=(SimpleDateFormat) context.getBean("dateFormat","SimpleDateFormat.class");
		try {
			Date date=format.parse("31/12/2018");
			String dat=date.toString();
			
			LOGGER.debug(dat);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("END");
	}
	
	/*
	 * void displayCountry(){
	 * 
	 * LOGGER.info("start"); ApplicationContext context = new
	 * ClassPathXmlApplicationContext("country.xml");
	 * 
	 * Country country = (Country) context.getBean("country", Country.class);
	 * 
	 * 
	 * LOGGER.debug("Country : {}", country.toString()); LOGGER.info("END"); }
	 */
//private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);
	
	public static void displayCountries()
	{
		LOGGER.info("Inside DisplayCountries");
		ApplicationContext context=new ClassPathXmlApplicationContext("country.xml");
		List<Country> list=(List<Country>)context.getBean("countryList");
		LOGGER.debug("Countries : {}",list);
		LOGGER.info("END");
		
		
	}
	public static void displayCountry() {
		LOGGER.info("START");
		ApplicationContext context=new ClassPathXmlApplicationContext("country.xml");
		Country country=(Country)context.getBean("country",Country.class);
		Country anotherCountry = context.getBean("country", Country.class);//The constructor will be called only once, which means that only one instance of Country bean is created
		//if scope is prototype then for every getBEan Invocation we will have new object instance
		LOGGER.debug("Country : {}", country.toString());
		LOGGER.info("END");
	}

}
