package pl.edu.ug.squat_notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.ug.squat_notes.domain.ChartPoint;
import pl.edu.ug.squat_notes.domain.SingleSet;
import pl.edu.ug.squat_notes.domain.Training;
import pl.edu.ug.squat_notes.repository.SingleSetRepository;
import pl.edu.ug.squat_notes.repository.TrainingRepository;
import pl.edu.ug.squat_notes.repository.UserRepository;
import pl.edu.ug.squat_notes.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    private SingleSetRepository singleSetRepository;
    private TrainingRepository trainingRepository;
    private UserRepository userRepository;

    @Autowired
    public ProgressServiceImpl(SingleSetRepository singleSetRepository, TrainingRepository trainingRepository, UserRepository userRepository) {
        this.singleSetRepository = singleSetRepository;
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<List<ChartPoint>> getChartByExerciseNameAndUserId(String exerciseName, Long idUser) {
        if (!userRepository.findById(idUser).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<Training> trainingList = trainingRepository.findAllByUserIdOrderByDateAsc(idUser);
        List<ChartPoint> result = new ArrayList<>();
        for (Training t : trainingList) {
            List<SingleSet> setsToChart = singleSetRepository.findAllByTrainingIdAndExerciseName(exerciseName, t.getId());
            ChartPoint c = new ChartPoint(new Date(), -1d);
            for (SingleSet s : setsToChart) {
                ChartPoint tmp = Utils.calculateChartPoint(s, t.getDate());
                if (tmp.getWeight() > c.getWeight()) {
                    c = tmp;
                }
            }
            if (c.getWeight() != -1d) {
                result.add(c);
            }
        }
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
