package pl.recruitment.retentionmanager.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.recruitment.retentionmanager.model.system.System;

import java.util.List;

/**
 * This interface describes available crud operations on @System object repository.
 */
@Service
@Primary
public interface SystemServices {
    List<System> findAll();

    System findAllById(Long id);

    System findAllByName(String name);

    void save(System system);

    void delete(double id);
}
