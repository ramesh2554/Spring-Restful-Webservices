package com.cognizant.springlearn.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CountryService {

	private ApplicationContext context;
	List<Country> list;

	public CountryService() {
		context = new ClassPathXmlApplicationContext("country.xml");
		list = context.getBean("countryList", java.util.ArrayList.class);
	}

	public Country getCountryIndia() {
		log.info("Start");
		context = new ClassPathXmlApplicationContext("country.xml");
		Country in = (Country) context.getBean("in");
		log.debug("Get country by get method : {}", in);

		log.info("End");
		return in;
	}

	public String addCountry(Country c) {
		log.info("Start");
		for (Country i : list) {
			if (i.getCode().equalsIgnoreCase(c.getCode())) {
				return "Country Already in the Inserted";
			}
		}
		list.add(c);

		log.debug("Country Added : {}", c);
		log.info("End");
		return "Country added successfully ";

	}

	public String deleteCountry(String code) throws CountryNotFoundException {
		log.info("Start");
		for (Country i : list) {
			if (i.getCode().equalsIgnoreCase(code)) {
				list.remove(i);
				log.debug("Delete country by delete method : {}", i);
				return "Country deleted Successfully";
			}
		}
		log.debug("Delete country by delete method : {}", code);
		log.info("End");
		throw new CountryNotFoundException();
	}

	public String updateCountry(Country c) throws CountryNotFoundException {
		for (Country i : list) {
			if (i.getCode().equalsIgnoreCase(c.getCode())) {
				list.remove(i);
				list.add(c);
				return "Country updated Successfully";
			}
		}
		throw new CountryNotFoundException();
	}

	public List<Country> getAllCountries() {
		return list;
	}

	/*
	 * public Country getCountryByCode(@PathVariable String code) throws
	 * CountryNotFoundException { log.info("Start"); for (Country i : list) { if
	 * (i.getCode().equalsIgnoreCase(code)) log.debug("get country by code { }",
	 * code); return i; }
	 * 
	 * log.info("End"); throw new CountryNotFoundException(); }
	 */

	public Country getCountry(String code) throws CountryNotFoundException {
		log.info("START");
		log.info("Calling CountryService.getCountry() with code=" + code);
		Country country = list.stream().filter(c -> c.getCode().equalsIgnoreCase(code)).findAny().orElse(null);
		log.debug("Country: {}", country);
		if (country == null) {
			throw new CountryNotFoundException();
		}
		log.info("END");
		return country;
	}

}
