package pl.edu.ug.squat_notes.service;

import org.springframework.http.ResponseEntity;
import pl.edu.ug.squat_notes.domain.Training;

import java.util.List;

public interface TrainingService {
    ResponseEntity<List<Training>> findAllByUserId(Long id);

    ResponseEntity<Training> addTraining(Training training);

    ResponseEntity<Training> deleteTraining(Training training);
}
