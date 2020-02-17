package pl.edu.ug.squat_notes.domain;

import javax.persistence.*;
import java.sql.Blob;

@Entity
public class User extends Account {
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private Blob profilePicture;

    public Blob getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Blob profilePicture) {
        this.profilePicture = profilePicture;
    }
}
