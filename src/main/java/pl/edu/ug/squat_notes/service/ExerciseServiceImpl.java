package pl.edu.ug.squat_notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.ug.squat_notes.domain.Exercise;
import pl.edu.ug.squat_notes.domain.Muscle;
import pl.edu.ug.squat_notes.domain.Account;
import pl.edu.ug.squat_notes.repository.ExerciseRepository;
import pl.edu.ug.squat_notes.repository.MuscleRepository;
import pl.edu.ug.squat_notes.repository.SingleSetRepository;
import pl.edu.ug.squat_notes.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private ExerciseRepository exerciseRepository;
    private SingleSetRepository singleSetRepository;
    private MuscleRepository muscleRepository;
    private AccountRepository accountRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository,
                               SingleSetRepository singleSetRepository,
                               MuscleRepository muscleRepository,
                               AccountRepository accountRepository) {
        this.exerciseRepository = exerciseRepository;
        this.singleSetRepository = singleSetRepository;
        this.muscleRepository = muscleRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Exercise> findAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public ResponseEntity<Exercise> addExercise(Exercise exercise) {
        Optional<Exercise> exerciseFromDB = exerciseRepository.findByName(exercise.getName());
        if (exerciseFromDB.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.ok(exerciseRepository.save(exercise));
    }

    @Override
    public ResponseEntity<Exercise> updateExercise(Exercise exercise) {
        Optional<Exercise> exerciseFromDB = exerciseRepository.findById(exercise.getId());
        if (exerciseFromDB.isPresent() && exerciseFromDB.get().getAuthor().getId().equals(exercise.getId())) {
            return ResponseEntity.ok(exerciseRepository.save(exercise));
        } else {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        }
    }

    @Override
    public ResponseEntity<Exercise> findById(Long id) {
        Optional<Exercise> exerciseFromDB = exerciseRepository.findById(id);
        if (exerciseFromDB.isPresent()) {
            return ResponseEntity.ok(exerciseFromDB.get());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @Override
    public ResponseEntity deleteExercise(Long id) {
        Optional<Exercise> exerciseFromDB = exerciseRepository.findById(id);

        if (exerciseFromDB.isPresent() && exerciseFromDB.get().getAuthor().getId().equals(id)) {
            if (singleSetRepository.countByExercise(exerciseFromDB.get()) > 0) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            } else {
                exerciseRepository.delete(exerciseFromDB.get());
                return ResponseEntity.ok().body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        }
    }

    @Override
    public ResponseEntity<List<Exercise>> findAllExercisesByMuscleName(String muscleName) {
        Optional<Muscle> muscle = muscleRepository.findByName(muscleName);
        if (muscle.isPresent()) {
            return ResponseEntity.ok(exerciseRepository.findAllByTargetMusclesContaining(muscle.get()));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<List<Exercise>> findAllExercisesByUserId(Long id) {
        Optional<Account> user = accountRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok(exerciseRepository.findAllByAuthor(user.get()));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<List<Exercise>> findAllExercisesDefault() {
        List<Exercise> ex = exerciseRepository.findAllByAuthorNull();
            return ResponseEntity.ok(exerciseRepository.findAllByAuthorNull());
    }
}
