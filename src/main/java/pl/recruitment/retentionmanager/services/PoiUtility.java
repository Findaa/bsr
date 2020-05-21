package pl.recruitment.retentionmanager.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public interface PoiUtility {
    void create(String path);
}
