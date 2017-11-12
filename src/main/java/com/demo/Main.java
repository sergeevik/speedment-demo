package com.demo;

import com.company.world.WorldApplication;
import com.company.world.WorldApplicationBuilder;
import com.company.world.world.world.city.City;
import com.company.world.world.world.city.CityManager;
import com.company.world.world.world.country.Country;
import com.company.world.world.world.country.CountryManager;
import com.company.world.world.world.countrylanguage.Countrylanguage;
import com.company.world.world.world.countrylanguage.CountrylanguageManager;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.speedment.runtime.core.ApplicationBuilder.LogType.STREAM;


public class Main {
    private static CountryManager countryManager;
    private static CityManager cityManager;
    private static CountrylanguageManager countrylanguageManager;

    public static void main(String... param) {
        WorldApplication app = new WorldApplicationBuilder()
                .withLogging(STREAM)
                .withPassword("root")
                .build();

        countryManager = app.getOrThrow(CountryManager.class);
        cityManager = app.getOrThrow(CityManager.class);
        countrylanguageManager = app.getOrThrow(CountrylanguageManager.class);


        Main main = new Main();
        main.getCountryForLanguage("Russian").stream()
                .map(Country::getName)
                .forEachOrdered(System.out::println);
    }

    private List<City> getRussiaCities(){
        return cityManager.stream()
                .filter(City.COUNTRY_CODE.equal("RUS"))
                .collect(Collectors.toList());
    }

    private Map<Country, List<City>> getAllCountryWithCities(){
        return cityManager.stream()
                .collect(Collectors.groupingBy(countryManager.finderBy(City.COUNTRY_CODE)));
    }

    private Map<Country, List<Countrylanguage>> getAllCountryWithCitiesAndLanguage(){
        return countrylanguageManager.stream()
                .collect(Collectors.groupingBy(countryManager.finderBy(Countrylanguage.COUNTRY_CODE)));
    }

    private Set<Country> getCountryForLanguage(String language){
        return countrylanguageManager.stream()
                .filter(Countrylanguage.LANGUAGE.equal(language))
                .filter(Countrylanguage.IS_OFFICIAL.equal("T"))
                .collect(Collectors.groupingBy(countryManager.finderBy(Countrylanguage.COUNTRY_CODE)))
                .keySet();

    }

}

