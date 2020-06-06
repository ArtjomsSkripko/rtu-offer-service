package offer.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntercityRestClientConfig {

    @Bean("intercityServiceRestClientConfig")
    @ConfigurationProperties("intercity")
    public ServicePathConfig getIntercityServiceRestClientConfig() {
        return new ServicePathConfig();
    }
}
