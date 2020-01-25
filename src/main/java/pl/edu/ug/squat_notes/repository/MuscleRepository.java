package pl.edu.ug.squat_notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.ug.squat_notes.domain.Muscle;

import java.util.Optional;

@Repository
public interface MuscleRepository extends JpaRepository<Muscle, Long> {
    Optional<Muscle> findByName(String name);
}
