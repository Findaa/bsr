package pl.recruitment.retentionmanager.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.recruitment.retentionmanager.model.Terms;

import java.util.List;

@Service
public interface ControllerHelper {
    List<Terms> setProductsData();
}
