package com.blog;

import org.eclipse.jetty.server.Authentication.User;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.blog.auth.JwtAuthenticationFilter;
import com.blog.auth.JwtAuthenticator;
import com.blog.auth.JwtAuthorizer;
import com.blog.auth.JwtUtil;
import com.blog.auth.principals.AuthUser;
import com.blog.db.RoleDAO;
import com.blog.db.UserDAO;
import com.blog.entity.RoleEntity;
import com.blog.entity.UserEntity;
import com.blog.health.HelloWorldHealthCheck;
import com.blog.resources.AuthResouce;
import com.blog.resources.HelloworldResource;
import com.blog.resources.UserResource;
import com.blog.service.impl.AuthServiceImpl;
import com.blog.service.impl.UserServiceImpl;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;

public class DropwizardBlogApplication extends Application<DropwizardBlogConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardBlogApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardBlog";
    }

    private final HibernateBundle<DropwizardBlogConfiguration> hibernateBundle = new HibernateBundle<DropwizardBlogConfiguration>(
            UserEntity.class, RoleEntity.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DropwizardBlogConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<DropwizardBlogConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new MigrationsBundle<DropwizardBlogConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(DropwizardBlogConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

    }

    @Override
    public void run(final DropwizardBlogConfiguration configuration,
            final Environment environment) {
        // TODO: implement application

        // DAOs
        final UserDAO userDao = new UserDAO(hibernateBundle.getSessionFactory());
        final RoleDAO roleDao = new RoleDAO(hibernateBundle.getSessionFactory());
        final JwtUtil jwtUtil = new JwtUtil(configuration.getJwtSecret(), configuration.getTokenExpiration());

        // Filters
        // environment.jersey().register(new JwtAuthenticationFilter(jwtUtil));
        environment.jersey().register(new AuthDynamicFeature(
                new OAuthCredentialAuthFilter.Builder<AuthUser>()
                        .setAuthenticator(new JwtAuthenticator(jwtUtil))
                        .setAuthorizer(new JwtAuthorizer())
                        .setPrefix("Bearer")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(AuthUser.class));

        // Resources
        environment.jersey().register(new HelloworldResource());
        environment.jersey().register(new UserResource(new UserServiceImpl(userDao)));
        environment.jersey().register(new AuthResouce(new AuthServiceImpl(userDao, roleDao, jwtUtil)));

        environment.healthChecks().register("hello", new HelloWorldHealthCheck());
    }
}
