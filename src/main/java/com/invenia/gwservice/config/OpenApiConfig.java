/*
 *
 *  * Copyright 2019-2020 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.invenia.gwservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*@OpenAPIDefinition(info = @Info(title = "Groupware Service", description = "그룹웨어 연동 서비스", version = "v1"))
@SecurityScheme(
    name = "security_auth",
    type = SecuritySchemeType.OPENIDCONNECT,
    flows = @OAuthFlows(
        authorizationCode = @OAuthFlow(
            authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
            tokenUrl = "${springdoc.oAuthFlow.tokenUrl}")
    )
)*/
@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

    private static final String OPEN_ID_SCHEME_NAME = "openId";
    private static final String OPENID_CONFIG_FORMAT = "%s/realms/%s/.well-known/openid-configuration";

    @Bean
    ApiInfoProvider apiInfoProvider(SwaggerProperties swaggerProperties) {
        return new ApiInfoProvider(swaggerProperties);
    }

    @Bean
    OpenAPI customOpenApi(KeycloakProperties keycloakProperties, ApiInfoProvider infoProvider) {
        return new OpenAPI()
            .info(infoProvider.provide())
            .components(new Components()
                .addSecuritySchemes(OPEN_ID_SCHEME_NAME, createOpenIdScheme(keycloakProperties)))
            .addSecurityItem(new SecurityRequirement().addList(OPEN_ID_SCHEME_NAME));
    }

    private SecurityScheme createOpenIdScheme(KeycloakProperties properties) {
        String connectUrl = String.format(OPENID_CONFIG_FORMAT, properties.getAuthServerUrl(), properties.getRealm());

        return new SecurityScheme()
            .type(SecurityScheme.Type.OPENIDCONNECT)
            .openIdConnectUrl(connectUrl);
    }
}
