package caballero.eduardo.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

@Configuration
public class RepositoryConfiguration implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] unsupportedActions = {HttpMethod.PUT, HttpMethod.POST,
                                                HttpMethod.DELETE, HttpMethod.PATCH};

        Class[] allEntities = entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new); //config.exposeIdsFor(ProductCategory.class);
        config.exposeIdsFor(allEntities);
        disableHttpMethods(allEntities, config, unsupportedActions);



    }

    private void disableHttpMethods(Class[] allEntities, RepositoryRestConfiguration config, HttpMethod[] unsupportedActions) {
        for(Class entity : allEntities){
            config.getExposureConfiguration()
                    .forDomainType(entity)
                    .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
                    .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));
        }
    }
}
