package pl.edu.ug.squat_notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.ug.squat_notes.domain.Account;
import pl.edu.ug.squat_notes.domain.ChartPoint;
import pl.edu.ug.squat_notes.domain.SingleSet;
import pl.edu.ug.squat_notes.domain.Training;
import pl.edu.ug.squat_notes.repository.SingleSetRepository;
import pl.edu.ug.squat_notes.repository.TrainingRepository;
import pl.edu.ug.squat_notes.repository.AccountRepository;
import pl.edu.ug.squat_notes.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressServiceImpl implements ProgressService {

    private SingleSetRepository singleSetRepository;
    private TrainingRepository trainingRepository;
    private AccountRepository accountRepository;

    @Autowired
    public ProgressServiceImpl(SingleSetRepository singleSetRepository, TrainingRepository trainingRepository, AccountRepository accountRepository) {
        this.singleSetRepository = singleSetRepository;
        this.trainingRepository = trainingRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<List<ChartPoint>> getChartByExerciseNameAndUserId(String exerciseName, Long idUser) {
        if (!accountRepository.findById(idUser).isPresent()) {
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

    @Override
    public ResponseEntity<Integer> getAdvancedByUserId(Long userId) {
        Optional<Account> user = accountRepository.findById(userId);
        if (user.isPresent()) {
            Integer advanced = calculateAdvanced(user.get());
            if (user.get().getSex().equals("female")) {
                advanced *= 2;
            }
            return ResponseEntity.ok(advanced);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    private Integer calculateAdvanced(Account user) {
        int advanced = 0;

        List<SingleSet> benchPressBestSets = singleSetRepository.findTopByUserIdAndExerciseNameOrderByWeightDesc(user.getId(), "Bench press");
        List<SingleSet> deadliftBestSets = singleSetRepository.findTopByUserIdAndExerciseNameOrderByWeightDesc(user.getId(), "Deadlift");
        List<SingleSet> squatBestSets = singleSetRepository.findTopByUserIdAndExerciseNameOrderByWeightDesc(user.getId(), "Squat");

        List<List<SingleSet>> allSets = new ArrayList<>();
        allSets.add(benchPressBestSets);
        allSets.add(deadliftBestSets);
        allSets.add(squatBestSets);

        for (List<SingleSet> exerciseSets : allSets) {
            int exerciseMax = 0;
            for (SingleSet set : exerciseSets) {
                double setMax = Utils.calculateMax(set.getWeight(), set.getReps(), set.getRPE());
                if (setMax > exerciseMax) {
                    exerciseMax = (int) setMax;
                }
            }
            advanced += exerciseMax;
        }

        return advanced;
    }
}
