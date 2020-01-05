package pl.edu.ug.squat_notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.ug.squat_notes.domain.SuperSet;
import pl.edu.ug.squat_notes.domain.Training;

import java.util.List;

@Repository
public interface SuperSetRepository extends JpaRepository<SuperSet, Long> {

    List<SuperSet> findAllByTraining(Training training);

}
