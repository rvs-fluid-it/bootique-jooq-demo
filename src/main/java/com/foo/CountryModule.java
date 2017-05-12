package com.foo;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.BQCoreModule;
import io.bootique.jersey.JerseyModule;

public class CountryModule implements Module {
    @Override
    public void configure(Binder binder) {
        BQCoreModule.extend(binder).declareVar("jdbc.schedule.password", "DB_PASSWORD");
        JerseyModule.extend(binder).addResource(CountryApi.class);

        binder.bind(CountryService.class).to(CountryServiceSimple.class);
    }
}
