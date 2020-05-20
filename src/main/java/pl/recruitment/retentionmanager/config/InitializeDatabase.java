package pl.recruitment.retentionmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.recruitment.retentionmanager.model.system.System;
import pl.recruitment.retentionmanager.model.term.AmountPeriod;
import pl.recruitment.retentionmanager.model.term.AmountType;
import pl.recruitment.retentionmanager.model.term.Term;
import pl.recruitment.retentionmanager.services.SystemServices;
import pl.recruitment.retentionmanager.services.TermsServices;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

import static pl.recruitment.retentionmanager.model.term.AmountPeriod.MONTH;
import static pl.recruitment.retentionmanager.model.term.AmountType.NET;

@Component
public class InitializeDatabase {
    @Autowired
    public InitializeDatabase(TermsServices terms, SystemServices systems) {
        this.terms = terms;
        this.systems = systems;
    }

    TermsServices terms;
    SystemServices systems;

    @PostConstruct
    public void init() {
        systems.save(new System("KUCYK", "info", "technopozdro", "client"));
        systems.save(new System("ŁÓDKA", "info1", "techno1", "clientpays"));
        systems.save(new System("KAPISZON", "info2", "techno2", "clientdemands"));
        systems.save(new System("KOTEK", "info3", "technolog", "clientis"));
        systems.save(new System("DEMON", "info4", "techno-logies", "clientmy"));
        systems.save(new System("ZÓŁWIK", "info5", "technology", "clientmaster"));
        systems.save(new System("KOJOTEK", "info6", "techn", "clientspeaks"));
        systems.save(new System("WYMYSLONYSTWOR", "info7", "techno1414", "clientwants"));
        systems.save(new System("WYMYSLONYSTWOR2", "info8", "techno1516545", "clientcalls"));


        terms.save(new Term("KUCYK", 2222, "22/2011",
                LocalDate.of(2012, 02, 02), LocalDate.of(2014, 04, 3),
                100.00, NET, MONTH, 2, true));
        terms.save(new Term("ŁÓDKA", 2343, "21/2012",
                LocalDate.of(2012, 03, 10), LocalDate.of(2014, 03, 3),
                555.00, NET, MONTH, 2, true));
        terms.save(new Term("KAPISZON", 1232, "34/2010",
                LocalDate.of(2011, 06, 4), LocalDate.of(2014, 06, 5),
                453.33, NET, MONTH, 2, true));
        terms.save(new Term("KOTEK", 3131, "22/2015",
                LocalDate.of(2015, 01, 21), LocalDate.of(2016, 01, 1),
                123.31, AmountType.BRU, AmountPeriod.YEAR, 2, false));
        terms.save(new Term("DEMON", 1222, "303/2017",
                LocalDate.of(2017, 03, 15), LocalDate.of(2017, 12, 20),
                122.11, NET, MONTH, 2, true));
        terms.save(new Term("ZÓŁWIK", 5511, "212/2017",
                LocalDate.of(2017, 01, 23), LocalDate.of(2017, 12, 25),
                122.12, NET, MONTH, 2, true));
        terms.save(new Term("KOJOTEK", 3322, "311/2017",
                LocalDate.of(2017, 01, 12), LocalDate.of(2017, 12, 31),
                444.00, NET, MONTH, 2, true));
        terms.save(new Term("WYMYSLONYSTWOR", 1042, "201/2021",
                LocalDate.of(2017, 01, 12), LocalDate.of(2021, 12, 31),
                444.00, NET, MONTH, 2, true));
        terms.save(new Term("WYMYSLONYSTWOR2", 1024, "202/2021",
                LocalDate.of(2017, 01, 12), LocalDate.of(2021, 12, 31),
                444.00, AmountType.BRU, AmountPeriod.YEAR, 2, false));
        java.lang.System.out.println("Count of terms: " + terms.findAll().size());
        try {
            terms.findAll().forEach(term -> {
                        java.lang.System.out.println("term getname: " + term.getSystemName());
                        java.lang.System.out.println("system findallbyname: " + systems.findAllByName(term.getSystemName()));
                        term.setSystem(systems.findAllByName(term.getSystemName()));
                        java.lang.System.out.println("get system name from term: " + term.getSystem().getName());
                    }
            );
        } catch (NullPointerException npe) {
            java.lang.System.out.println("Npe in setSystem");
        }
    }
}
