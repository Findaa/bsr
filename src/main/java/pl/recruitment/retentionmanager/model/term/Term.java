package pl.recruitment.retentionmanager.model.term;

import com.sun.istack.Nullable;
import lombok.*;
import pl.recruitment.retentionmanager.model.system.System;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Database table pattern mapped into java object.
 * This is a description for a real terms used to sell a @Product to customers.
 * @author: Michal Cop
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Terms")
public class Term {
    public Term(String systemName, int request, String orderNumber,
                LocalDate fromDate, LocalDate toDate, double amount,
                AmountType amountType, AmountPeriod amountPeriod,
                int authorizationPercent, boolean isActive) {
        this.systemName = systemName;
        this.request = request;
        this.orderNumber = orderNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.amount = amount;
        this.amountType = amountType;
        this.amountPeriod = amountPeriod;
        this.authorizationPercent = authorizationPercent;
        this.active = isActive;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="terms_id")
    @Getter @Setter private Long id;
    @Column(name="listed_system")
    @Getter @Setter private String systemName;
    @Column(name="request_no")
    @Getter @Setter private int request;
    @Column(name="order_no")
    @Getter @Setter private String orderNumber;
    @Column(name="date_from")
    @Getter @Setter private LocalDate fromDate;
    @Column(name="date_to")
    @Getter @Setter private LocalDate toDate;
    @Column(name="amount")
    @Getter @Setter private double amount;
    @Column(name="amount_type")
    @Getter @Setter private AmountType amountType;
    @Column(name="amount_period")
    @Getter @Setter private AmountPeriod amountPeriod;
    @Column(name="auth_percent")
    @Getter @Setter private int authorizationPercent;
    @Column(name="active_flag")
    @Getter @Setter private boolean active;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "term_product",
            joinColumns = @JoinColumn(name = "listed_system"),
            inverseJoinColumns = @JoinColumn(name = "system_name"))
    @Getter @Setter private System system;
}
