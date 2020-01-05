package pl.edu.ug.squat_notes.domain;

import javax.persistence.*;

@Entity
public class SingleSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
    @ManyToOne
    @JoinColumn(name = "super_set_id")
    private SuperSet superSet;
    private Double weight;
    private Integer reps;
    private Double RPE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public SuperSet getSuperSet() {
        return superSet;
    }

    public void setSuperSet(SuperSet superSet) {
        this.superSet = superSet;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Double getRPE() {
        return RPE;
    }

    public void setRPE(Double RPE) {
        this.RPE = RPE;
    }
}
