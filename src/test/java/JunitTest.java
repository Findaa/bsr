
import org.junit.Test;
import pl.recruitment.retentionmanager.model.system.System;
import pl.recruitment.retentionmanager.model.term.Term;
import pl.recruitment.retentionmanager.services.SystemServices;
import pl.recruitment.retentionmanager.services.TermsServices;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static pl.recruitment.retentionmanager.model.term.AmountPeriod.MONTH;
import static pl.recruitment.retentionmanager.model.term.AmountType.NET;

public class JunitTest {

    SystemServices systems;
    TermsServices terms;

    @Test
    public void assertAdditionalSystem() {
        int i = 0;
        try {
            i = systems.findAll().size();
        } catch (NullPointerException ignored) {
        }

        systems.save(new System("New", "Test", "for", "fun"));
        assertEquals(i + 1, systems.findAll().size());
    }

    @Test
    public void assertAdditionalTerm() {
        int i = 0;
        try {
            i = terms.findAll().size();
        } catch (NullPointerException ignored) {
        }
        terms.add(new Term("Test", 2222, "2/3",
                LocalDate.of(2012, 02, 02), LocalDate.of(2014, 04, 3),
                100.00, NET, MONTH, 2, true));
        assertEquals(i + 1, terms.findAll().size());
    }

    @Test
    public void testSystemAssignment() {
        int i = 0;
        try {
            i = systems.findAll().size();
        } catch (NullPointerException ignored) {
        }
        terms.add(new Term("newSys", 2222, "2/3",
                LocalDate.of(2012, 02, 02), LocalDate.of(2014, 04, 3),
                100.00, NET, MONTH, 2, true));
        assertEquals(i + 1, systems.findAll().size());
    }
}
