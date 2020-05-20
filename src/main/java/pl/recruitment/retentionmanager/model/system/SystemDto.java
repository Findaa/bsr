package pl.recruitment.retentionmanager.model.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class SystemDto {
    @Getter @Setter double id;
    @Getter @Setter String system;
    @Getter @Setter String desc;
}
