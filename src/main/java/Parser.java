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
        for (Keyword kw : Keyword.values()) {
            if (word.toUpperCase().equals(kw.toString())) {
                return true;
            }
        }
        return false;
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
        boolean isValidDateFormat = splitDate.length == 3;
        if (isValidDateFormat) {
            String year = splitDate[0];
            String month = splitDate[1];
            String day = splitDate[2];
            boolean isValidYear = year.length() == 4;
            boolean isValidMonth = month.length() == 2 && Integer.parseInt(month) <= 12
                    && Integer.parseInt(month) >= 1;
            boolean isValidDay = day.length() == 2 && Integer.parseInt(day) >= 1
                    && Integer.parseInt(day) <= 31;
            if (isValidYear && isValidMonth && isValidDay) {
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
    public boolean isValidTime(String time) {
        if (time.equals("")) {
            return true;
        }
        String[] hoursAndMinutes = time.split(":");
        boolean isValidTimeFormat = hoursAndMinutes.length == 2;
        if (isValidTimeFormat) {
            String hour = hoursAndMinutes[0];
            String minutes = hoursAndMinutes[1];
            boolean isValidHour = hour.length() == 2 && Integer.parseInt(hour) <= 24
                    && Integer.parseInt(hour) >= 0;
            boolean isValidMinute = minutes.length() == 2 && Integer.parseInt(minutes) >= 0
                    && Integer.parseInt(minutes) <= 60;
            if (isValidHour && isValidMinute) {
                return true;
            }
        }
        return false;
    }
}
