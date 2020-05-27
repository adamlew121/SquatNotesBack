package pl.edu.ug.squat_notes;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.ug.squat_notes.domain.*;
import pl.edu.ug.squat_notes.util.Utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class UtilsTest {

    @Test
    public void testTrainingValid() {
        Training training = getValidTraining();
        assertThat(Utils.isValid(training)).isTrue();
    }

    @Test
    public void testTrainingNoAuthor() {
        Training training = getValidTraining();
        training.setUser(null);
        assertThat(Utils.isValid(training)).isFalse();
    }

    @Test
    public void testTrainingNoDate() {
        Training training = getValidTraining();
        training.setDate(null);
        assertThat(Utils.isValid(training)).isFalse();
    }

    @Test
    public void testTrainingSuperSetEmpty() {
        Training training = getValidTraining();
        training.setSuperSetList(new ArrayList<>());
        assertThat(Utils.isValid(training)).isFalse();
    }

    @Test
    public void testMessageValid() {
        Message message = getValidMessage();
        assertThat(Utils.isValid(message)).isTrue();
    }

    @Test
    public void testMessageNoDate() {
        Message message = getValidMessage();
        message.setMessageDate(null);
        assertThat(Utils.isValid(message)).isFalse();
    }

    @Test
    public void testMessageNoText() {
        Message message = getValidMessage();
        message.setText("");
        assertThat(Utils.isValid(message)).isFalse();
    }

    @Test
    public void testChatboxValid() {
        Chatbox chatbox = getValidChatbox();
        assertThat(Utils.isValid(chatbox)).isTrue();
    }

    @Test
    public void testChatboxNoOwner() {
        Chatbox chatbox = getValidChatbox();
        chatbox.setUser(null);
        assertThat(Utils.isValid(chatbox)).isFalse();
    }

    @Test
    public void testChatboxNoMessage() {
       Chatbox chatbox = getValidChatbox();
       chatbox.setMessageList(new ArrayList<>());
       assertThat(Utils.isValid(chatbox)).isFalse();
    }

    @Test
    public void testChatboxNoDate() {
        Chatbox chatbox = getValidChatbox();
        chatbox.setDate(null);
        assertThat(Utils.isValid(chatbox)).isFalse();
    }

    @Test
    public void testChatboxNoTitle() {
        Chatbox chatbox = getValidChatbox();
        chatbox.setTitle("");
        assertThat(Utils.isValid(chatbox)).isFalse();
    }

    @Test
    public void testAccountValid() {
        Account account = getValidAccount();
        assertThat(Utils.isValid(account)).isTrue();
    }

    @Test
    public void testAccountShortLogin() {
        Account account = getValidAccount();
        account.setLogin("de");
        assertThat(Utils.isValid(account)).isFalse();
    }

    @Test
    public void testAccountNoPassword() {
        Account account = getValidAccount();
        account.setPassword(null);
        assertThat(Utils.isValid(account)).isFalse();
    }

    @Test
    public void testAccountInvalidPassword() {
        Account account = getValidAccount();
        account.setPassword("^^^^");
        assertThat(Utils.isValid(account)).isFalse();
    }

    @Test
    public void testAccountNoName() {
        Account account = getValidAccount();
        account.setName(null);
        assertThat(Utils.isValid(account)).isFalse();
    }

    @Test
    public void testAccountNoEmail() {
        Account account = getValidAccount();
        account.setEmail(null);
        assertThat(Utils.isValid(account)).isFalse();
    }

    @Test
    public void testAccountInvalidEmail() {
        Account account = getValidAccount();
        account.setEmail("123");
        assertThat(Utils.isValid(account)).isFalse();
    }

    @Test
    public void testAccountInvalidBirthday() {
        Account account = getValidAccount();
        account.setDateOfBirthday(new Date(System.currentTimeMillis() + 100000000));
        assertThat(Utils.isValid(account)).isFalse();
    }







    protected Account getValidAccount() {
        Account acc = new Account();
        acc.setName("def");
        acc.setEmail("def@def.def");
        acc.setSurname("def");
        acc.setLogin("def");
        acc.setPassword("Def12345!@#");
        acc.setDateOfBirthday(new Date(System.currentTimeMillis() - 100000000));
        acc.setSex("def");

        return acc;
    }

    protected SuperSet getValidSuperSet() {
        Muscle muscle = new Muscle();
        muscle.setName("muscle");

        Exercise exercise = new Exercise();
        exercise.setName("exercise");
        exercise.setAuthor(getValidAccount());
        exercise.addTargetMuscle(muscle);

        SingleSet singleSet = new SingleSet();
        singleSet.setReps(10);
        singleSet.setRPE(10d);
        singleSet.setWeight(10d);
        singleSet.setExercise(exercise);

        SuperSet superSet = new SuperSet();
        superSet.addSet(singleSet);

        return superSet;
    }

    protected Message getValidMessage() {
        Message message = new Message();
        message.setText("def");
        message.setMessageDate(new Date());

        return message;
    }

    protected Chatbox getValidChatbox() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(getValidMessage());

        Chatbox chatbox = new Chatbox();
        chatbox.setUser(getValidAccount());
        chatbox.setTitle("def");
        chatbox.setDate(new Date());
        chatbox.setMessageList(messageList);

        return chatbox;
    }

    protected Training getValidTraining() {
        Training training = new Training();
        training.setUser(getValidAccount());
        training.setName("def");
        training.setDifficulty(5);
        training.setDate(new java.sql.Date(System.currentTimeMillis()));
        training.addSuperSet(getValidSuperSet());

        return training;
    }






}
