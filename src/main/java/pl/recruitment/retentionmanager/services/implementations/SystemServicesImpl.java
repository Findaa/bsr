package pl.recruitment.retentionmanager.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.recruitment.retentionmanager.model.system.System;
import pl.recruitment.retentionmanager.repositories.SystemRepo;
import pl.recruitment.retentionmanager.services.SystemServices;

import java.util.List;

@Component
public class SystemServicesImpl implements SystemServices {
    @Autowired
    SystemRepo repo;

    @Override
    public List<System> findAll() {
        return repo.findAll();
    }

    @Override
    public System findAllById(Long id) {
        return repo.findAllById(id);
    }

    @Override
    public System findAllByName(String name) {
        return repo.findAllByName(name);
    }

    @Override
    public void save(System system) {
        repo.save(system);
    }

    @Override
    public void delete(double id) {
        repo.delete(findAllById((long) id));
    }
}
