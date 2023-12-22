package com.fds.quanlilichlamviec.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;

/**
 * @author vietdd
 */

@Configuration
public class SwaggerConfig {

    @Value("${swagger.open.apiinfo.title}")
    private String apiInfoTitle;

    @Value("${swagger.open.apiinfo.description}")
    private String apiInfoDescription;

    @Value("${swagger.open.apiinfo.license}")
    private String apiInfoLicense;

    @Value("${swagger.open.apiinfo.version}")
    private String apiInfoVersion;

    @Value("${swagger.open.apiinfo.licenseurl}")
    private String apiInfoLicenceUrl;

    @Value("${swagger.open.apiinfo.contact.name}")
    private String apiInfoContactName;

    @Value("${swagger.open.apiinfo.contact.url}")
    private String apiInfoContactUrl;

    @Bean
    public GroupedOpenApi flexManagementApi() {
        return GroupedOpenApi.builder()
                .group("quanlylichlamviec-1.0")
                .packagesToScan("com.fds.quanlilichlamviec.controller")
                .build();
    }


    @Bean
    public OpenAPI flexManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(apiInfoTitle)
                        .description(apiInfoDescription)
                        .version(apiInfoVersion)
                        .license(new License().name(apiInfoLicense).url(apiInfoLicenceUrl)))
                .externalDocs(new ExternalDocumentation()
                        .description(apiInfoContactName)
                        .url(apiInfoContactUrl));
    }
}
