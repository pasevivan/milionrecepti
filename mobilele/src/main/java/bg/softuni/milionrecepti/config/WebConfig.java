package bg.softuni.milionrecepti.config;

import bg.softuni.milionrecepti.service.MaintenanceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final LocaleChangeInterceptor localeChangeInterceptor;

  private final MaintenanceInterceptor maintenanceInterceptor;

  public WebConfig(LocaleChangeInterceptor localeChangeInterceptor, MaintenanceInterceptor maintenanceInterceptor) {
    this.localeChangeInterceptor = localeChangeInterceptor;
    this.maintenanceInterceptor = maintenanceInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor);
    registry.addInterceptor(maintenanceInterceptor);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/src/main/resources/static/images/**")
            .addResourceLocations("file:src/main/resources/static/images/");
  }


}
