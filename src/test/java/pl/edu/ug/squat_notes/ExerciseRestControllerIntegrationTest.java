package pl.edu.ug.squat_notes;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.ug.squat_notes.domain.*;
import pl.edu.ug.squat_notes.repository.ExerciseRepository;
import pl.edu.ug.squat_notes.repository.MuscleRepository;
import pl.edu.ug.squat_notes.repository.TrainingRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class ExerciseRestControllerIntegrationTest extends IntegrationTest {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    MuscleRepository muscleRepository;

    private void resetDb() {

        exerciseRepository.deleteAll(exerciseRepository.findAllByAuthor(createDefaultAccount().get()));
        exerciseRepository.flush();
    }

    @Test
    public void testCreateExerciseValid() throws Exception {
        this.resetDb();
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Account account = createDefaultAccount().get();
        Muscle muscle = muscleRepository.findByName("Chest").get();

        Exercise exercise = new Exercise();
        exercise.setAuthor(account);
        exercise.setName("newExercise");
        exercise.addTargetMuscle(muscle);

        String inputJson = this.mapToJson(exercise);

        this.mvc.perform(post("/api/exercise").contentType(MediaType.APPLICATION_JSON).content(inputJson));

        List<Exercise> found = exerciseRepository.findAll();
        assertThat(found).extracting(Exercise::getName).contains("newExercise");
    }


}
