import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.recruitment.retentionmanager.model.system.System;
import pl.recruitment.retentionmanager.repositories.SystemRepo;
import pl.recruitment.retentionmanager.services.SystemServices;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestCrud {
    
    private List<System> generateList() {
        List<System> insertion = new ArrayList<>();
        insertion.add(new System("KUCYKt", "info", "technopozdro", "client"));
        insertion.add(new System("ŁÓDKAt", "info1", "techno1", "clientpays"));
        insertion.add(new System("KAPISZONt", "info2", "techno2", "clientdemands"));
        insertion.add(new System("KOTEKt", "info3", "technolog", "clientis"));
        insertion.add(new System("DEMONt", "info4", "techno-logies", "clientmy"));
        insertion.add(new System("ZÓŁWIKt", "info5", "technology", "clientmaster"));
        insertion.add(new System("KOJOTEKt", "info6", "techn", "clientspeaks"));
        insertion.add(new System("WYMYSLONYSTWOR", "info7", "techno1414", "clientwants"));
        insertion.add(new System("WYMYSLONYSTWOR2", "info8", "techno1516545", "clientcalls"));
        return insertion;
    }
    @Mock
    SystemRepo systemsMock;

    @InjectMocks
    SystemServices servicesMock;

    @Test
    public void testAddition(){
        when(servicesMock.findAll()).thenReturn(generateList());
        int i = systemsMock.findAll().size();
        assertEquals(9, i);
    }

}
