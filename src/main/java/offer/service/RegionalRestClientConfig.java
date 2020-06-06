package offer.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegionalRestClientConfig {

    @Bean("regionalServiceRestClientConfig")
    @ConfigurationProperties("regional")
    public ServicePathConfig getRegionalServiceRestClientConfig() {
        return new ServicePathConfig();
    }
}
