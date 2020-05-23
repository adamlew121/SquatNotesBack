package pl.edu.ug.squat_notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.ug.squat_notes.domain.Account;
import pl.edu.ug.squat_notes.domain.Training;
import pl.edu.ug.squat_notes.repository.TrainingRepository;
import pl.edu.ug.squat_notes.repository.AccountRepository;
import pl.edu.ug.squat_notes.util.Utils;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private TrainingRepository trainingRepository;
    private AccountRepository accountRepository;
    private ProgressService progressService;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository, AccountRepository accountRepository, ProgressService progressService) {
        this.trainingRepository = trainingRepository;
        this.accountRepository = accountRepository;
        this.progressService = progressService;
    }

    @Override
    public ResponseEntity<List<Training>> findAllByUserId(Long id) {
        if (!accountRepository.findById(id).isPresent()) {
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
            ResponseEntity<Training> result =  ResponseEntity.ok(trainingRepository.save(training));
            Account user = training.getUser();
            user.setAdvanced(progressService.getAdvancedByUserId(user.getId()).getBody());
            accountRepository.save(user);
            return result;
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<Training> deleteTraining(Training training) {
        if (Utils.isValid(training)) {
            trainingRepository.delete(training);
            Account user = training.getUser();
            user.setAdvanced(progressService.getAdvancedByUserId(user.getId()).getBody());
            accountRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
