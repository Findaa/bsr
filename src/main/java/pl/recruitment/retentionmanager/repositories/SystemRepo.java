package pl.recruitment.retentionmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.recruitment.retentionmanager.model.system.System;

import java.util.List;

@Repository
@Component
public interface SystemRepo extends JpaRepository<System, Long> {
    List<System> findAll();
    System findAllById(Long id);
    System findAllByName(String name);
}