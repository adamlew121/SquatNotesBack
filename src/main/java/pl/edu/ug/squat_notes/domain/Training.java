package pl.edu.ug.squat_notes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Account user;
    private String name;
    private Date date;
    private Integer difficulty;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "training", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SuperSet> superSetList = new ArrayList<SuperSet>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
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
        for (SuperSet s: superSetList) {
            s.setTraining(this);
        }
        this.superSetList = superSetList;
    }

    public void addSuperSet(SuperSet superSet) {
        this.superSetList.add(superSet);
        superSet.setTraining(this);
    }

//    public void removeComment(SuperSet superSet) {
//        this.superSetList.remove(superSet);
//        superSet.setTraining(null);
//    }
}
