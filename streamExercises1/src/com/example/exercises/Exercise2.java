package com.example.exercises;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.City;


public class Exercise2 {
	private static final CountryDao countryDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		
        final BiConsumer<String, Optional<City>> printEntry =
                (country,cityAttribute) -> {
                    City city = cityAttribute.get();
                    System.out.println(country + ": City [ name= " + city.getName() + ", population= " + city.getPopulation() + " ]");
                };
        Collector<City, ?, Map<String, Optional<City>>> groupingHighPopulatedCitiesByContinent = Collectors.groupingBy
        		(city -> countryDao.findCountryByCode(city.getCountryCode()).getContinent()
        				, Collectors.maxBy(Comparator.comparing(City::getPopulation)));
        Map<String, Optional<City>> highPopulatedCitiesByContinent = countryDao.findAllCountries()
                .stream()
                .flatMap(country -> country.getCities().stream())
                .collect(groupingHighPopulatedCitiesByContinent);
        highPopulatedCitiesByContinent.forEach(printEntry);
		
		
		  
	}
		
		
		
		

				
		
}

