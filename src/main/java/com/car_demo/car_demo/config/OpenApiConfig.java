/* Jeremy Holloway
 * This class handles the swagger documentation
 */
package com.car_demo.car_demo.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class OpenApiConfig {

    /** declare the function as a bean since no other functions should be needed
     * @return open api object for display on the swagger doc page
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
            .title("Car Inventory API")
            .version("v1.0")
            .description("An API that manages a car inventory")
        );
    }
    
}
