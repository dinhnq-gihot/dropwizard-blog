package com.blog;

import io.dropwizard.core.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import org.hibernate.validator.constraints.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.dropwizard.db.DataSourceFactory;

public class DropwizardBlogConfiguration extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        if (Objects.isNull(database)) {
            database = new DataSourceFactory();
        }
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }
}
