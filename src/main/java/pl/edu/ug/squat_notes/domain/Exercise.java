package pl.edu.ug.squat_notes.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "exercise_target_muscles",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "muscle_id"))
    private Set<Muscle> targetMuscles = new HashSet<Muscle>();

    //field to allow or prevent delete and modify exercise by user
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Exercise() {
    }

    public Exercise(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author: " + ((author != null) ? author.getName() : "") +
                '}';
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

    public Set<Muscle> getTargetMuscles() {
        return targetMuscles;
    }

//    public void setTargetMuscles(List<Muscle> targetMuscles) {
//        this.targetMuscles = targetMuscles;
//    }

    public void addTargetMuscle(Muscle muscle) {
        this.targetMuscles.add(muscle);
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
