package pl.recruitment.retentionmanager.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Terms")
public class Terms {
    public Terms(String system, int request, String orderNumber,
                 Date fromDate, Date toDate, double amount,
                 AmountType amountType, AmountPeriod amountPeriod,
                 int authorizationPercent, boolean isActive) {
        this.system = system;
        this.request = request;
        this.orderNumber = orderNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.amount = amount;
        this.amountType = amountType;
        this.amountPeriod = amountPeriod;
        this.authorizationPercent = authorizationPercent;
        this.isActive = isActive;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="terms_id")
    @Getter @Setter private Long id;
    @Column(name="listed_system")
    @Getter @Setter private String system;
    @Column(name="request_no")
    @Getter @Setter private int request;
    @Column(name="order_no")
    @Getter @Setter private String orderNumber;
    @Column(name="date_from")
    @Getter @Setter private Date fromDate;
    @Column(name="date_to")
    @Getter @Setter private Date toDate;
    @Column(name="amount")
    @Getter @Setter private double amount;
    @Column(name="amount_type")
    @Getter @Setter private AmountType amountType;
    @Column(name="amount_period")
    @Getter @Setter private AmountPeriod amountPeriod;
    @Column(name="auth_percent")
    @Getter @Setter private int authorizationPercent;
    @Column(name="active_flag")
    @Getter @Setter private boolean isActive;
}
