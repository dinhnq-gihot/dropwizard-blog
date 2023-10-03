package com.blog;

import io.dropwizard.core.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import io.dropwizard.db.DataSourceFactory;

public class DropwizardBlogConfiguration extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @NotEmpty
    private String jwtSecret;

    @Min(1)
    private long tokenExpiration;

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

    @JsonProperty("jwtSecret")
    public String getJwtSecret() {
        return this.jwtSecret;
    }

    @JsonProperty("jwtSecret")
    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    @JsonProperty("tokenExpiration")
    public long getTokenExpiration() {
        return this.tokenExpiration;
    }

    @JsonProperty("tokenExpiration")
    public void setTokenExpiration(long tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }
}
