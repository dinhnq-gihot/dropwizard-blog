package com.blog;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class DropwizardBlogApplication extends Application<DropwizardBlogConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardBlogApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardBlog";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardBlogConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardBlogConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
