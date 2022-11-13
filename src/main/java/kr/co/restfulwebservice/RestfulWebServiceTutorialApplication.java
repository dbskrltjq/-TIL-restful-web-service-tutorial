package kr.co.restfulwebservice;

import java.util.Locale;

import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestfulWebServiceTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServiceTutorialApplication.class, args);
	}

	/*
	 * @Bean public LocaleResolver localeResolver() { SessionLocaleResolver
	 * localeResolver = new SessionLocaleResolver();
	 * localeResolver.setDefaultLocale(Locale.KOREA); return localeResolver(); }
	 */
}
