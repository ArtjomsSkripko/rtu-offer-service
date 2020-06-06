package offer.service;

import org.springframework.web.util.UriComponentsBuilder;

public class ServicePathConfig {

    private String host;
    private String path;

    public ServicePathConfig() {
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public String buildUri() {
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder
                .fromHttpUrl(getHost()).path(getPath());

        return componentsBuilder.build(false).toUriString();
    }
}
