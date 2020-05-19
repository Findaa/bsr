package pl.recruitment.retentionmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.recruitment.retentionmanager.model.AmountPeriod;
import pl.recruitment.retentionmanager.model.AmountType;
import pl.recruitment.retentionmanager.model.Terms;
import pl.recruitment.retentionmanager.services.TermsServices;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;

import static pl.recruitment.retentionmanager.model.AmountPeriod.MONTH;
import static pl.recruitment.retentionmanager.model.AmountType.NET;

@Component
public class InitializeDatabase {
    @Autowired
    public InitializeDatabase(TermsServices terms) {
        this.termsServices = terms;
    }

    TermsServices termsServices;

    @PostConstruct
    public void init() {
        termsServices.save(new Terms("KUCYK", 2222, "22/2011",
                new Date(2012, Calendar.FEBRUARY, 2), new Date(2014, Calendar.APRIL, 3),
                100.00, NET, MONTH, 2, true));
        termsServices.save(new Terms("ŁÓDKA", 2343, "21/2012",
                new Date(2012, Calendar.MARCH, 10), new Date(2014, Calendar.MARCH, 3),
                555.00, NET, MONTH, 2, true));
        termsServices.save(new Terms("KAPISZON", 1232, "34/2010",
                new Date(2011, Calendar.JUNE, 4), new Date(2014, Calendar.JUNE, 5),
                453.33, NET, MONTH, 2, true));
        termsServices.save(new Terms("KOTEK", 3131, "22/2015",
                new Date(2015, Calendar.JANUARY, 21), new Date(2016, Calendar.JANUARY, 1),
                123.31, AmountType.BRU, AmountPeriod.YEAR, 2, false));
        termsServices.save(new Terms("DEMON", 1222, "303/2017",
                new Date(2017, Calendar.MARCH, 15), new Date(2017, Calendar.DECEMBER, 20),
                122.11, NET, MONTH, 2, true));
        termsServices.save(new Terms("ZÓŁWIK", 5511, "212/2017",
                new Date(2017, Calendar.JANUARY, 23), new Date(2017, Calendar.DECEMBER, 25),
                122.12, NET, MONTH, 2, true));
        termsServices.save(new Terms("KOJOTEK", 3322, "311/2017",
                new Date(2017, Calendar.JANUARY, 12), new Date(2017, Calendar.DECEMBER, 31),
                444.00, NET, MONTH, 2, true));
        termsServices.save(new Terms("WYMYSLONYSTWOR", 1042, "201/2021",
                new Date(2017, Calendar.JANUARY, 12), new Date(2021, Calendar.DECEMBER, 31),
                444.00, NET, MONTH, 2, true));
        termsServices.save(new Terms("WYMYSLONYSTWOR2", 1024, "202/2021",
                new Date(2017, Calendar.JANUARY, 12), new Date(2021, Calendar.DECEMBER, 31),
                444.00, AmountType.BRU, AmountPeriod.YEAR, 2, false));
    }
}
