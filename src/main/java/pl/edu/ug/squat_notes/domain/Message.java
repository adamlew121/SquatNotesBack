package pl.edu.ug.squat_notes.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;
    private Date messageDate;
    private String title;
    private String text;
}
