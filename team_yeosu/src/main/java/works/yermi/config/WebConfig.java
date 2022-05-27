package works.yermi.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import lombok.extern.log4j.Log4j;

@Log4j
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	
	@Override
	protected Class<?>[] getRootConfigClasses() { // root-context
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() { // servlet-context
		// TODO Auto-generated method stub
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() { // url-pattern
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		MultipartConfigElement element =
			new MultipartConfigElement("C:/pension", 1024 * 1024 * 2 * 10, 1024 * 1024 * 4 * 10, 1024 * 1024 * 2 * 10);
		registration.setMultipartConfig(element);	
	}
	
	
	
	
	
}
