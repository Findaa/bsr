package pl.recruitment.retentionmanager.model.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    public System(String name, String info) {
        this.name = name;
        this.info = info;
        this.technologies = "No technologies specified";
        this.client = "No client specified";
    }

    public System(String name) {
        this.name = name;
        this.info = "No info specified";
        this.technologies = "No technologies specified";
        this.client = "No client specified";
    }
}
