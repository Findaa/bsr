package pl.recruitment.retentionmanager.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.recruitment.retentionmanager.model.Terms;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface ControllerHelper {
    String setProductsData(HttpSession session);
}
