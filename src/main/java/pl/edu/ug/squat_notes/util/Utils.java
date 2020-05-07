package pl.edu.ug.squat_notes.util;

import pl.edu.ug.squat_notes.domain.*;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

    public static Boolean isValid(Training training) {
        return training.getUser() != null
                && !(training.getSuperSetList().isEmpty())
                && training.getDate() != null;
    }

    public static Boolean isValid(Message message) {
        return message.getMessageDate() != null
                && !(message.getText().length() == 0);
              //  && (message.getChatBox() != null);
    }

    public static Boolean isValid(Chatbox chatbox) {
        return chatbox.getUser() != null
                && !(chatbox.getMessageList().isEmpty())
                && chatbox.getDate() != null
                && !(chatbox.getTitle().length() == 0);
    }

    public static Boolean isValid(Account user) {
        return user.getLogin() != null
                && user.getLogin().length() >= 3
                && user.getPassword() != null
                && isPasswordValid(user.getPassword())
                && user.getName() != null
                && user.getEmail() != null
                && user.getEmail().matches("^(.+)@(.+)$")
                && user.getDateOfBirthday().before(new Date(System.currentTimeMillis()));
    }

    public static boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher;
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

    public static Double calculateMax(Double weight, Integer reps, Double RPE) {
        //weight x reps x 0.0333 + weight = 1RM
        Double result = 0d;
        if (RPE == null) {
            result = weight * reps * 0.0333 + weight;
        } else {
            result = weight * (reps + (10d - RPE)) * 0.0333 + weight;
        }
        return result;
    }

    public static ChartPoint calculateChartPoint(SingleSet singleSet, Date trainingDate) {
        Double oneRepMax = calculateMax(singleSet.getWeight(), singleSet.getReps(), singleSet.getRPE());
        return new ChartPoint(trainingDate, oneRepMax);
    }
}
