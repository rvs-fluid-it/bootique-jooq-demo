package com.foo;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;

public class CountryModuleProvider implements BQModuleProvider {
    @Override
    public Module module() {
        return new CountryModule();
    }
}
