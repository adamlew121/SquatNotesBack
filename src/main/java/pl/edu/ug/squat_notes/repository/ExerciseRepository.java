package pl.edu.ug.squat_notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.ug.squat_notes.domain.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
