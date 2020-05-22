package pl.recruitment.retentionmanager.services;

import org.springframework.stereotype.Service;

/**
 * This interface describes how POI logic should be executed.
 */
@Service
public interface PoiUtility {
    void create(String path);
}
