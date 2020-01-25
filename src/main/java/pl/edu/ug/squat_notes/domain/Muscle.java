package pl.edu.ug.squat_notes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Muscle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "targetMuscles")
    private Set<Exercise> exercises = new HashSet<Exercise>();

    public Muscle() {
    }

    @Override
    public String toString() {
        return "Muscle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Muscle(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Set<Exercise> getExerciseList() {
        return exercises;
    }

    public void setExerciseList(Set<Exercise> exercises) {
        this.exercises = exercises;
    }
}
