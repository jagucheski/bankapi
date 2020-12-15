package br.com.jagucheski.bankapi.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

	@Bean
	public Docket bankApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.jagucheski.bankapi"))
				.paths(PathSelectors.ant("/**"))
				.build();
//				.ignoredParameterTypes(ClienteDtoPF.class, ClienteDtoPJ.class, ClienteFormPJ.class, ClienteFormPF.class );
	}
	
}
