package pl.edu.ug.squat_notes.service;

import org.springframework.http.ResponseEntity;
import pl.edu.ug.squat_notes.domain.ChartPoint;

import java.util.List;

public interface ProgressService {
    ResponseEntity<List<ChartPoint>> getChartByExerciseNameAndUserId(String exerciseName, Long idUser);

    ResponseEntity<Integer> getAdvancedByUserId(Long userId);
}
