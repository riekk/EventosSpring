package org.viaadamo.spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String site;

    @JsonBackReference
    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    @ToString.Exclude
    private transient List<Event> events;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;


}
