package pl.edu.ug.squat_notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.ug.squat_notes.domain.ChartPoint;
import pl.edu.ug.squat_notes.domain.Exercise;
import pl.edu.ug.squat_notes.domain.SingleSet;

import java.util.List;

@Repository
public interface SingleSetRepository extends JpaRepository<SingleSet, Long> {

    String FIND_SETS_BY_TRAINING_AND_EXERCISE = "SELECT s.* FROM SINGLE_SET s INNER JOIN SUPER_SET sup ON sup.ID = s.SUPER_SET_ID " +
            "INNER JOIN EXERCISE e ON e.ID = s.EXERCISE_ID WHERE e.NAME = ?1 AND sup.TRAINING_ID = ?2";

    Long countByExercise(Exercise exercise);

    List<SingleSet> findAllBySuperSetId(Long id);

    @Query(value = FIND_SETS_BY_TRAINING_AND_EXERCISE, nativeQuery = true)
    List<SingleSet> findAllByTrainingIdAndExerciseName(String exerciseName, Long idTraining);

}
