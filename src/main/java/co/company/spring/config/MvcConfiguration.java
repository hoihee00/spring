package co.company.spring.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import co.company.spring.common.AuthCheckInterceptor;
import co.company.spring.controller.Greeter;

//어노테이션(Configuration) 아래 내용이 환경 설정 부분에 해당
@Configuration
@ComponentScan(basePackages = "co.company")
@EnableWebMvc // (annotation-driven)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MvcConfiguration implements WebMvcConfigurer{
	
	/**
	 * 언어 변경을 위한 인터셉터를 생성한다.
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
	    interceptor.setParamName("lang");	// 언어 변경 시 사용할 파라미터를 lang으로 지정
	    return interceptor;
	}

	/**
	 * 변경된 언어 정보를 기억할 로케일 리졸퍼를 생성한다.
	 * 여기서는 세션에 저장하는 방식을 사용한다.
	 * @return
	 */
	@Bean
	public SessionLocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}

	/**
	 * 인터셉터를 등록한다.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	    registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/emp*");
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("message.label");	// 폴더명.파일명(확장자 적을 필요 없음)
		ms.setDefaultEncoding("utf-8");
//		ms.setCacheSeconds(10);				// 시간 지정해서 특정 시간마다 메세지파일 새로 읽어들여서 재실행
		return ms;
	}

	@Bean // 컨테이너 객체를 등록
	public Greeter greeter() {
		Greeter g = new Greeter();
		g.setformat("%s, 안녕하세요");
		return g;
	}
	
	@Override
	public void configureDefaultServletHandling (DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");	//접두어
		resolver.setSuffix(".jsp");				//접미어
		resolver.setOrder(3);
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/"); //webapp 아래 images 폴더
	}
	
	@Bean
	public AuthCheckInterceptor authCheckInterceptor() {
		return new AuthCheckInterceptor();
	}
	
	@Bean 
	CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multi = new CommonsMultipartResolver();
		multi.setMaxUploadSize(1024*10000); //10MB로 첨부파일 용량 제한
		return multi;
	}
}
