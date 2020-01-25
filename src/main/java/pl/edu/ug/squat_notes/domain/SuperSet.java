package pl.edu.ug.squat_notes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SuperSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Super set usually has 1 - 5 single sets
    //so bidirectional relationship doesn't decrease performance
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "superSet")
    private List<SingleSet> sets = new ArrayList<SingleSet>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id")
    private Training training;

    public void addSet(SingleSet set) {
        this.sets.add(set);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SingleSet> getSets() {
        return sets;
    }

    public void setSets(List<SingleSet> sets) {
        for (SingleSet s: sets) {
            s.setSuperSet(this);
        }
        this.sets = sets;
    }

    @JsonIgnore
    public Training getTraining() {
        return training;
    }

    //setter is package-private because training field for SuperSet is set by addSuperSet in Training class
    void setTraining(Training training) {
        this.training = training;
    }
}
