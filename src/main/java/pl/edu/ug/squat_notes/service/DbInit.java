package pl.edu.ug.squat_notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.ug.squat_notes.domain.*;
import pl.edu.ug.squat_notes.repository.ExerciseRepository;
import pl.edu.ug.squat_notes.repository.SuperSetRepository;
import pl.edu.ug.squat_notes.repository.TrainingRepository;
import pl.edu.ug.squat_notes.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.Random;

@Component
public class DbInit {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private SuperSetRepository superSetRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void postConstruct() {
        Exercise ohp = new Exercise("OHP");
        exerciseRepository.save(ohp);
        Exercise squat = new Exercise("Squat");
        exerciseRepository.save(squat);
        Exercise deadlift = new Exercise("Deadlift");
        exerciseRepository.save(deadlift);
        Exercise benchPress = new Exercise("Bench press");
        exerciseRepository.save(benchPress);

        Random rand = new Random();
        SuperSet superSet = new SuperSet();
        SingleSet singleSet = new SingleSet();
        singleSet.setExercise(ohp);
        singleSet.setReps(rand.nextInt(10 - 5) + 5);
        singleSet.setRPE(Math.round((5d + 5d * rand.nextDouble())*2) / 2d);
        singleSet.setWeight(50.0);
        singleSet.setSuperSet(superSet);
        superSet.addSet(singleSet);
        singleSet = new SingleSet();
        singleSet.setExercise(squat);
        singleSet.setReps(8);
        singleSet.setRPE(9.5);
        singleSet.setWeight(150.0);
        singleSet.setSuperSet(superSet);
        superSet.addSet(singleSet);
        Training training = new Training();
        training.setDate(new Date(System.currentTimeMillis()));
        training.setName("Push day");
        training.setDifficulty(2);
        User user = new User();
        user.setName("Jeff");
        user.setDateOfBirthday(new Date(90, 4, 20));
        user.setEmail("JeFFrEy@jeff.jeffi");
        user.setLogin("JEFF");
        user.setPassword("JEFF");
        user.setSex("male");
        user.setSurname("Jeffinito");
        training.setUser(user);
        superSet.setTraining(training);
        userRepository.save(user);
        trainingRepository.save(training);
        superSetRepository.save(superSet);
    }
}
