import java.util.HashSet;
import java.util.Set;

public class Parser {

    //checks if keyword is valid
    public boolean isValidKeyWord(String word) {
        Set<String> reservedKeyWords = new HashSet<>();
        reservedKeyWords.add("help");
        reservedKeyWords.add("list");
        reservedKeyWords.add("done");
        reservedKeyWords.add("delete");
        reservedKeyWords.add("deadline");
        reservedKeyWords.add("event");
        reservedKeyWords.add("todo");
        reservedKeyWords.add("bye");

        return reservedKeyWords.contains(word);
    }

    //checks the format of a given date and checks if it is acceptable
    public boolean isValidDate(String date) {
        if (date.equals("")) {
            return true;
        }
        String[] splitDate = date.split("-");
        if (splitDate.length == 3) {
            String year = splitDate[0];
            String month = splitDate[1];
            String day = splitDate[2];
            if (year.length() == 4 && month.length() == 2 && day.length() == 2 && Integer.parseInt(month) <= 12
                    && Integer.parseInt(month) >= 1 && Integer.parseInt(day) >= 1 && Integer.parseInt(day) <= 31) {
                return true;
            }
        }
        return false;
    }

    //checks the format of a given time and checks if it is acceptable
    public boolean isValidTime(String time) {
        if (time.equals("")) {
            return true;
        }
        String[] splitTime = time.split(":");
        if (splitTime.length == 2) {
            String hour = splitTime[0];
            String minutes = splitTime[1];
            if (hour.length() == 2 && minutes.length() == 2 && Integer.parseInt(hour) <= 24
                    && Integer.parseInt(hour) >= 0 && Integer.parseInt(minutes) >= 0
                    && Integer.parseInt(minutes) <= 60) {
                return true;
            }
        }
        return false;
    }
}
