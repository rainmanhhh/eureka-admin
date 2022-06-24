package fy.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;
import ez.auth.sak.ServiceApiKey;

@Configuration
public class AdminConfig {
  private final ServiceApiKey serviceApiKey;

  public AdminConfig(ServiceApiKey serviceApiKey) {
    this.serviceApiKey = serviceApiKey;
  }

  @Bean
  public HttpHeadersProvider customHttpHeadersProvider() {
    return instance -> {
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(serviceApiKey.getName(), serviceApiKey.encodeLocal());
      return httpHeaders;
    };
  }
}
