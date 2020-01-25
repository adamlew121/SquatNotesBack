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
    private SuperSetRepository superSetRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MuscleRepository muscleRepository;

    //do skasowania
    @Autowired
    private ExerciseService exerciseService;

    @PostConstruct
    private void postConstruct() {
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

        //init user
        User user = new User();
        user.setName("Jeff");
        user.setDateOfBirthday(new Date(90, 4, 20));
        user.setEmail("JeFFrEy@jeff.jeffi");
        user.setLogin("JEFF");
        user.setPassword("JEFF");
        user.setSex("male");
        user.setSurname("Jeffinito");
        userRepository.save(user);
//        superSetRepository.save(superSet);
        training.setUser(user);
        training.addSuperSet(superSet);
        userRepository.save(user);
//        superSetRepository.save(superSet);
        trainingRepository.save(training);
        //do skasowania
        List<Exercise> exercises = exerciseService.findAllExercises();
        for (Exercise e: exercises) {
            System.out.println(e);
        }
    }
}
