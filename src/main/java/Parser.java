import java.util.HashSet;
import java.util.Set;

/**
 * The Parser class handles all the parsing needed.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class Parser {

    /**
     * Checks if the given keyword is exists in the pool of reserved keywords.
     *
     * @param word The given string describing the keyword.
     * @return True if the given string belongs to the set of reserved keywords, false otherwise.
     */
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

    /**
     * Checks if the given date string is valid for parsing in LocalDate.
     *
     * @param date The given string describing date.
     * @return True if the given string complies the criteria, false otherwise.
     */
    public boolean isValidDate(String date) {
        if (date.equals("")) {
            return true;
        }
        String[] splitDate = date.split("-");
        if (splitDate.length == 3) {
            String year = splitDate[0];
            String month = splitDate[1];
            String day = splitDate[2];
            if (year.length() == 4 && month.length() == 2 && day.length() == 2 &&
                    Integer.parseInt(month) <= 12 && Integer.parseInt(month) >= 1 &&
                        Integer.parseInt(day) >= 1 && Integer.parseInt(day) <= 31) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given time string is valid for parsing in LocalTime.
     *
     * @param time The given string describing time.
     * @return True if the given string complies the criteria, false otherwise.
     */
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
