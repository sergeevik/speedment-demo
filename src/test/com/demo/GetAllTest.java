package com.demo;

import com.company.world.WorldApplication;
import com.company.world.WorldApplicationBuilder;
import com.company.world.world.world.city.City;
import com.company.world.world.world.city.CityManager;
import com.company.world.world.world.country.CountryManager;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.*;

public class GetAllTest {

    private GetAll getAll;
    private CityManager cityManager;
    private CountryManager countryManager;

    @Before
    public void setUp() throws Exception {
        WorldApplication app = new WorldApplicationBuilder()
                .withPassword("root")
                .build();

        countryManager = app.getOrThrow(CountryManager.class);
        cityManager = app.getOrThrow(CityManager.class);
        getAll = new GetAll(cityManager, countryManager);
    }

    @Test
    public void CitiesEqualsGenerics() throws Exception {
        assertThat(getAll.cities()).isEqualTo(getAll.getAll(cityManager));
    }

    @Test
    public void CountriesEqualsGenerics() throws Exception {
        assertThat(getAll.countries()).isEqualTo(getAll.getAll(countryManager));
    }
}