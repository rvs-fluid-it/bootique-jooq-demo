package com.foo;

public class CountryServiceSimple implements CountryService {

    @Override
    public Country get(String code) {
        Country result = null;
        switch (code) {
            case "be":
                result = new Country("be", "Belgium");
                break;
        }
        return result;
    }
}
