package pl.edu.ug.squat_notes.service;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.edu.ug.squat_notes.domain.*;
import pl.edu.ug.squat_notes.repository.*;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.Random;

@Component
public class DbInit {

    private ExerciseRepository exerciseRepository;

    private TrainingRepository trainingRepository;

    private UserRepository userRepository;

    private UserService userService;

    private MuscleRepository muscleRepository;

    @Autowired
    public DbInit(ExerciseRepository exerciseRepository, TrainingRepository trainingRepository, UserRepository userRepository, UserService userService, MuscleRepository muscleRepository) {
        this.exerciseRepository = exerciseRepository;
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.muscleRepository = muscleRepository;
    }

    @PostConstruct
    private void postConstruct() {
        //init user
        User user = new User();
        user.setName("Jeffrey");
        user.setDateOfBirthday(new Date(90, 4, 20));
        user.setEmail("jeff@test.pl");
        user.setLogin("TEST123");
        user.setPassword("TEST123!");
        user.setSex("male");
        user.setSurname("Tester");
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

        String[] trainingNamesTmp = {"Push day", "Pull day", "Chest day", "Back day", "Arms day"};
        //init training with sets
        for (int u = 0; u < 10; u++) {
            Faker faker = new Faker();
            user = new User();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            user.setName(firstName);
            user.setSurname(lastName);
            user.setDateOfBirthday(faker.date().birthday(16, 50));
            user.setEmail(firstName + "." + lastName + "@gmail.com");
            user.setLogin(firstName + "123");
            user.setPassword(firstName + "123!");
            user.setSex((u % 2 == 0 ? "male" : "female"));
            if(userService.addUser(user).getStatusCode() != HttpStatus.OK) {
                continue;
            }
            Random rand = new Random();
            int lvlTmp = rand.nextInt(5);
            for (int i = 0; i < 20; i++) {
                Training training = new Training();
                long dayTime = 1000 * 60 * 60 * 24;
                training.setDate(new Date(System.currentTimeMillis() - dayTime * 100 + dayTime * i * 3));
                training.setName(trainingNamesTmp[i % trainingNamesTmp.length]);
                training.setDifficulty(rand.nextInt(10) + 1);
                training.setUser(user);
                for (int j = 0; j < 5; j++) {
                    SuperSet superSet = new SuperSet();
                    SingleSet singleSet = new SingleSet();
                    singleSet.setExercise(ohp);
                    singleSet.setReps(rand.nextInt(10 - 5) + 5);
                    singleSet.setRPE(Math.round((5d + 5d * rand.nextDouble()) * 2) / 2d);
                    singleSet.setWeight(lvlTmp * 8d + i * rand.nextDouble());
                    singleSet.setSuperSet(superSet);
                    superSet.addSet(singleSet);
                    singleSet = new SingleSet();
                    singleSet.setExercise(squat);
                    singleSet.setReps(3);
                    singleSet.setRPE(7d);
                    singleSet.setWeight(lvlTmp * 40d + i * rand.nextDouble());
                    singleSet.setSuperSet(superSet);
                    superSet.addSet(singleSet);
                    singleSet = new SingleSet();
                    singleSet.setExercise(benchPress);
                    singleSet.setReps(6);
                    singleSet.setRPE(9.5);
                    singleSet.setWeight(lvlTmp * 10d + i * rand.nextDouble());
                    singleSet.setSuperSet(superSet);
                    superSet.addSet(singleSet);
                    singleSet = new SingleSet();
                    singleSet.setExercise(deadlift);
                    singleSet.setReps(1);
                    singleSet.setRPE(10d);
                    singleSet.setWeight(lvlTmp * 50d + i * rand.nextDouble());
                    singleSet.setSuperSet(superSet);
                    superSet.addSet(singleSet);

                    training.addSuperSet(superSet);
                    userRepository.save(user);
                    trainingRepository.save(training);
                }
            }
        }
    }
}
