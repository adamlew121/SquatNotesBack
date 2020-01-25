package pl.edu.ug.squat_notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.ug.squat_notes.domain.Training;
import pl.edu.ug.squat_notes.repository.TrainingRepository;
import pl.edu.ug.squat_notes.repository.UserRepository;
import pl.edu.ug.squat_notes.util.Utils;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private TrainingRepository trainingRepository;
    private UserRepository userRepository;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository, UserRepository userRepository) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<List<Training>> findAllByUserId(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<Training> trainingList = trainingRepository.findAllByUserId(id);
        if(trainingList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.ok(trainingList);
        }
    }

    @Override
    public ResponseEntity<Training> addTraining(Training training) {
        if (Utils.isValid(training)) {
            return ResponseEntity.ok(trainingRepository.save(training));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
