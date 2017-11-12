package com.demo;

import com.company.world.world.world.city.City;
import com.company.world.world.world.city.CityManager;
import com.company.world.world.world.country.Country;
import com.company.world.world.world.country.CountryManager;
import com.speedment.runtime.core.manager.Manager;

import java.util.List;
import java.util.stream.Collectors;

public class GetAll {

    private CityManager cityManager;
    private CountryManager countryManager;

    public GetAll(CityManager cityManager, CountryManager countryManager) {
        this.cityManager = cityManager;
        this.countryManager = countryManager;
    }

    public List<City> cities(){
        return cityManager.stream().collect(Collectors.toList());
    }

    public List<Country> countries(){
        return countryManager.stream().collect(Collectors.toList());
    }

    public <T> List<T> getAll(Manager<T> manager){
        return manager.stream().collect(Collectors.toList());
    }

}
