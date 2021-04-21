package com.cognizant.springlearn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@RestController
@RequestMapping(value = "/country")
public class CountryController {

	@Autowired
	private CountryService cs;
// 	localhost:8888/country/getIndia
	@GetMapping(value = "/getIndia")
	public Country getCountryIndia() {
		return cs.getCountryIndia();
	}
	// localhost:8888/country/getAllCountries
	@GetMapping("/getAllCountries")
	public List<Country> getAllCountries() {
		return cs.getAllCountries();
	}

	/*
	 * @GetMapping("/getCountry/{code}") public Country
	 * getCountryByCode(@PathVariable String code) throws CountryNotFoundException {
	 * return cs.getCountryByCode(code);
	 * 
	 * }
	 */

	//localhost:8888/country/getCountry/in
	@GetMapping("/getCountry/{code}")
	public Country getCountry(@PathVariable String code) throws CountryNotFoundException {

//			log.info("START");
//			LOGGER.debug("Calling getCountry() with method 'GET' and code=" + code);
//		 LOGGER.info("END");
	
		return cs.getCountry(code);
	}

//	@PostMapping("/addCountry")
//	public String addCountry(@RequestBody Country c) {
//
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//
//		Validator validator = factory.getValidator();
//
//		// Validation is done against the annotations defined in country bean
//
//		Set<ConstraintViolation<Country>> violations = validator.validate(c);
//
//		List<String> errors = new ArrayList<String>();
//
//		// Accumulate all errors in an ArrayList of type String
//
//		for (ConstraintViolation<Country> violation : violations) {
//
//			errors.add(violation.getMessage());
//		}
//
//		// Throw exception so that the user of this web service receives appropriate
//		// error message
//
//		if (violations.size() > 0) {
//
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
//
//		}
//		return cs.addCountry(c);
//	}

	/*
	 * {
    "code": "SA",
    "name": "South Africa"
}
	 */
//	localhost:8888/country
	@PostMapping
	public String addCountry(@RequestBody @Valid Country country) {
		//LOGGER.info("Start PostMapping");
		//LOGGER.info(country.toString());
		//LOGGER.info("End PostMapping");
		return cs.addCountry(country);
	}
//	localhost:8888/country/updateCountry
	@PutMapping("/updateCountry")
	public String updateCountry(@RequestBody Country c) throws CountryNotFoundException {
		return cs.updateCountry(c);
	}
// localhost:8888/country/deleteCountry/in
	@DeleteMapping("/deleteCountry/{code}")
	public String deleteCountry(@PathVariable String code) throws CountryNotFoundException {
		return cs.deleteCountry(code);
	}
}
