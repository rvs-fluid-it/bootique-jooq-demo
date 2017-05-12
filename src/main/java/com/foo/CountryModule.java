package com.foo;

import com.google.inject.*;
import io.bootique.BQCoreModule;
import io.bootique.jdbc.DataSourceFactory;
import io.bootique.jersey.JerseyModule;

import javax.sql.DataSource;

public class CountryModule implements Module {
    @Override
    public void configure(Binder binder) {
        BQCoreModule.extend(binder).declareVar("jdbc.schedule.password", "DB_PASSWORD");
        JerseyModule.extend(binder).addResource(CountryApi.class);

        binder.bind(CountryService.class).to(CountryServiceJdbc.class);
    }

    @Singleton
    @Provides
    public DataSource provideDatasource(DataSourceFactory dataSourceFactory) {
        return dataSourceFactory.forName("schedule");
    }
}
