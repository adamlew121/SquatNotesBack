package pl.edu.ug.squat_notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.ug.squat_notes.domain.Exercise;
import pl.edu.ug.squat_notes.domain.SingleSet;

import java.util.List;

@Repository
public interface SingleSetRepository extends JpaRepository<SingleSet, Long> {
    Long countByExercise(Exercise exercise);
    List<SingleSet> findAllBySuperSetId(Long id);
}
