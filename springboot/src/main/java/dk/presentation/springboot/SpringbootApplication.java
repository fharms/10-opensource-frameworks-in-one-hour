package dk.presentation.springboot;

import dk.presentation.springboot.entities.Customer;
import dk.presentation.springboot.entities.Invoice;
import dk.presentation.springboot.entities.InvoiceLine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2WebMvc
@Import({SpringDataRestConfiguration.class})
public class SpringbootApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("10 frameworks")
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .paths(PathSelectors.regex("/error.*").negate())
                .paths(PathSelectors.regex("/profile.*").negate())
                .build();
    }

    @Bean
    public ResourceProcessor<Resource<Customer>> customerResourceProcessor(RepositoryEntityLinks entityLinks) {
        return new ResourceProcessor<Resource<Customer>>() {
            @Override
            public Resource<Customer> process(Resource<Customer> customerResource) {
                customerResource.add(entityLinks.linkToCollectionResource(Invoice.class));
                return customerResource;
            }
        };
    }

    @Bean
    public ResourceProcessor<Resource<Invoice>> invoiceResourceProcessor(RepositoryEntityLinks entityLinks) {
        return new ResourceProcessor<Resource<Invoice>>() {
            @Override
            public Resource<Invoice> process(Resource<Invoice> invoiceResource) {
                invoiceResource.add(entityLinks.linkToCollectionResource(InvoiceLine.class));
                invoiceResource.add(entityLinks.linkToCollectionResource(Customer.class));
                return invoiceResource;
            }
        };
    }

    @Bean
    public ResourceProcessor<Resource<InvoiceLine>> invoiceLineResourceProcessor(RepositoryEntityLinks entityLinks) {
        return new ResourceProcessor<Resource<InvoiceLine>>() {
            @Override
            public Resource<InvoiceLine> process(Resource<InvoiceLine> invoiceLineResource) {
                invoiceLineResource.add(entityLinks.linkToCollectionResource(Invoice.class));
                return invoiceLineResource;
            }
        };
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Example")
                .description("Spring Boot Example for talk '10 OSS Frameworks in 1 Hour'")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
                .version("1.0 Beta 1")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
