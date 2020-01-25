package pl.edu.ug.squat_notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.ug.squat_notes.domain.Muscle;
import pl.edu.ug.squat_notes.repository.MuscleRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class MuscleController {

    private MuscleRepository muscleRepository;

    @Autowired
    public MuscleController(MuscleRepository muscleRepository) {
        this.muscleRepository = muscleRepository;
    }

    @GetMapping("/api/muscle")
    public List<Muscle> getMuscles() {
        return muscleRepository.findAll();
    }

    @GetMapping("/api/muscle/{id}")
    ResponseEntity<Muscle> getMuscleById(@PathVariable Long id) {
        Optional<Muscle> muscle = muscleRepository.findById(id);
        if (muscle.isPresent()) {
            return ResponseEntity.ok(muscle.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/api/muscle/name/{muscleName}")
    ResponseEntity<Muscle> getMuscleByName(@PathVariable String muscleName) {
        Optional<Muscle> muscle = muscleRepository.findByName(muscleName);
        if (muscle.isPresent()) {
            return ResponseEntity.ok(muscle.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
