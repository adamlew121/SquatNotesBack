package pl.edu.ug.squat_notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.ug.squat_notes.domain.Training;
import pl.edu.ug.squat_notes.domain.User;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findAllByUserId(Long id);

    List<Training> findAllByUserIdOrderByDateAsc(Long id);
}
