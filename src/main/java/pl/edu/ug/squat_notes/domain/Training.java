package pl.edu.ug.squat_notes.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private Date date;
    private Integer difficulty;
    @OneToMany
    private List<SuperSet> superSetList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public List<SuperSet> getSuperSetList() {
        return superSetList;
    }

    public void setSuperSetList(List<SuperSet> superSetList) {
        this.superSetList = superSetList;
    }
}
