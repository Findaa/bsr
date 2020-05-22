package pl.recruitment.retentionmanager.model.term;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Data transfer object for jsp forms.
 * @author: Michal Cop
 */
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TermDto {
    @Getter @Setter private String system;
    @Getter @Setter private String request;
    @Getter @Setter private String orderNumber;
    @Getter @Setter private String fromDate;
    @Getter @Setter private String toDate;
    @Getter @Setter private String amount;
    @Getter @Setter private String amountType;
    @Getter @Setter private String amountPeriod;
    @Getter @Setter private String authorizationPercent;
    @Getter @Setter private boolean active;
}
