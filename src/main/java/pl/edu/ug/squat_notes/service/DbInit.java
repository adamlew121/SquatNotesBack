package pl.edu.ug.squat_notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.ug.squat_notes.domain.*;
import pl.edu.ug.squat_notes.repository.*;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.List;
import java.util.Random;

@Component
public class DbInit {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MuscleRepository muscleRepository;

    @PostConstruct
    private void postConstruct() {
        //init user
        User user = new User();
        user.setName("Test user");
        user.setDateOfBirthday(new Date(90, 4, 20));
        user.setEmail("testuser@test.pl");
        user.setLogin("TEST");
        user.setPassword("TEST123!");
        user.setSex("male");
        user.setSurname("TEST");
        userRepository.save(user);
        //init muscles
        Muscle chest = new Muscle("Chest");
        muscleRepository.save(chest);
        Muscle legs = new Muscle("Legs");
        muscleRepository.save(legs);
        Muscle arms = new Muscle("Arms");
        muscleRepository.save(arms);
        Muscle shoulders = new Muscle("Shoulders");
        muscleRepository.save(shoulders);
        Muscle back = new Muscle("Back");
        muscleRepository.save(back);
        Muscle abs = new Muscle("ABS");
        muscleRepository.save(abs);

        //init exercises
        Exercise ohp = new Exercise("OHP");
        ohp.addTargetMuscle(shoulders);
        ohp.addTargetMuscle(arms);
        ohp.addTargetMuscle(chest);
        exerciseRepository.save(ohp);
        Exercise squat = new Exercise("Squat");
        squat.addTargetMuscle(legs);
        exerciseRepository.save(squat);
        Exercise deadlift = new Exercise("Deadlift");
        deadlift.addTargetMuscle(legs);
        exerciseRepository.save(deadlift);
        Exercise benchPress = new Exercise("Bench press");
        benchPress.addTargetMuscle(shoulders);
        benchPress.addTargetMuscle(arms);
        benchPress.addTargetMuscle(chest);
        exerciseRepository.save(benchPress);

        //init training with sets
        Random rand = new Random();
        for(int i = 0; i < 20; i++) {
            Training training = new Training();
            training.setDate(new Date(System.currentTimeMillis()));
            training.setName("Push day");
            training.setDifficulty(rand.nextInt(10)+1);
            training.setUser(user);
            for(int j = 0; j < 5; j++) {
                SuperSet superSet = new SuperSet();
                SingleSet singleSet = new SingleSet();
                singleSet.setExercise(ohp);
                singleSet.setReps(rand.nextInt(10 - 5) + 5);
                singleSet.setRPE(Math.round((5d + 5d * rand.nextDouble()) * 2) / 2d);
                singleSet.setWeight(50d + i * rand.nextDouble());
                singleSet.setSuperSet(superSet);
                superSet.addSet(singleSet);
                singleSet = new SingleSet();
                singleSet.setExercise(squat);
                singleSet.setReps(3);
                singleSet.setRPE(7d);
                singleSet.setWeight(160d + i * rand.nextDouble());
                singleSet.setSuperSet(superSet);
                superSet.addSet(singleSet);
                singleSet = new SingleSet();
                singleSet.setExercise(benchPress);
                singleSet.setReps(6);
                singleSet.setRPE(9.5);
                singleSet.setWeight(80d + i * rand.nextDouble());
                singleSet.setSuperSet(superSet);
                superSet.addSet(singleSet);
                singleSet = new SingleSet();
                singleSet.setExercise(deadlift);
                singleSet.setReps(1);
                singleSet.setRPE(10d);
                singleSet.setWeight(200d + i * rand.nextDouble());
                singleSet.setSuperSet(superSet);
                superSet.addSet(singleSet);

                training.addSuperSet(superSet);
                userRepository.save(user);
                trainingRepository.save(training);
            }
        }
    }
}
