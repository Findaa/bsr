package pl.recruitment.retentionmanager.model.system;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.recruitment.retentionmanager.model.term.Term;

import javax.persistence.*;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Products")
public class System {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Getter
    @Setter
    private Long id;
    @Column(name="system_name")
    @Getter @Setter private String name;
    @Getter @Setter private String info;
    @Getter @Setter private String technologies;
    @Getter @Setter private String client;

    public System(String name, String info, String technologies, String client) {
        this.name = name;
        this.info = info;
        this.technologies = technologies;
        this.client = client;
    }

    public System(String name) {
        this.name = name;
    }
}
