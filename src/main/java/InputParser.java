import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class InputParser {

    public static boolean isDone(String input) {
        String[] inputArr = input.split(" ");
        return inputArr.length == 2
                && input.substring(0, 4).equals("done")
                && isNumber(input.substring(5,6));
    }

    public static boolean isDelete(String input) {
        String[] inputArr = input.split(" ");
        return inputArr.length == 2
                && input.substring(0, 6).equals("delete")
                && isNumber(input.substring(7,8));
    }

    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean correctInputFormat(String input) {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            return false;
        }
        //correct todo format
        boolean todoBool = inputArr.length == 2 && inputArr[0].equals("todo");

        String taskWithDate = inputArr[1];
        String[] taskAndDateArr = taskWithDate.split(" /");
        //correct deadline format
        boolean deadlineBool = taskAndDateArr.length == 2 && inputArr[0].equals("deadline");

        //correct event format
        boolean eventBool = taskAndDateArr.length == 2 && inputArr[0].equals("event");

        return todoBool || deadlineBool || eventBool;
    }

    public static String parseDate(String date) throws DukeException {
        //date input could be "at 2/12/2019 1800"
        //returns "2019-12-02 1800"
        try {
            String errMessage = "Sorry! Format of date is wrong. " +
                    "Example input should be " +
                    "deadline return book /by 2/12/2019 1800.";

            String[] strArr = date.split(" ");
            if (strArr.length != 3) {
                throw new DukeException(errMessage);
            }

            String[] dateArr = strArr[1].split("/");
            if (dateArr.length != 3) {
                throw new DukeException(errMessage);
            }

            //if day < 10, add 0 in front
            if (Integer.parseInt(dateArr[0]) < 10) {
                dateArr[0] = "0" + dateArr[0];
            }
            //transform 2/12/2019 to 2019-12-02
            date = dateArr[2] + "-" + dateArr[1] + "-" + dateArr[0];
            strArr[1] = date;

            String res = strArr[1] + " " + strArr[2];
            return res;
        } catch (DukeException e) {
            throw e;
        }
    }

    public static String[] splitTaskAndDate(String task) throws DukeException {
        try {
            // date = "at 2/12/2019 1800"
            String date = task.substring(task.indexOf("/") + 1, task.length());
            // date = 2019-12-02 1800
            date = parseDate(date);

            // task = project meeting
            task = task.substring(0, task.indexOf("/") - 1);
            String[] res = new String[]{task, date};
            return res;
        } catch (DukeException e) {
            throw e;
        }
    }
}
