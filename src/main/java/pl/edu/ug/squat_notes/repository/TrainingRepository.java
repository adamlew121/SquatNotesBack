package pl.edu.ug.squat_notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.ug.squat_notes.domain.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
