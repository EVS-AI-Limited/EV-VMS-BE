package com.evision.tms.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "file.upload")
@Component
public class FileUploadProperties {

    private String location;
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

}
