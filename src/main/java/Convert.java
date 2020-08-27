import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Convert {
    static String at(String s) {
        String first = s.split("/at ")[0];
        String second = s.split("/at ")[1];
        // date of format "yyyy-mm-dd"
        LocalDate date = LocalDate.parse(second);
        return first + "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    static String by(String s) {
        String first = s.split("/by ")[0];
        String second = s.split("/by ")[1];
        LocalDate date = LocalDate.parse(second);
        return first + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    static Task add(String s) throws IllegalTaskTypeException {
        String taskType = s.split(" ; ")[0];
        String isDone = s.split(" ; ")[1];
        String message = s.split(" ; ")[2];

        if (taskType.equals("T")) {
            return new Todo(message, getStatus(Integer.parseInt(isDone)));
        } else if (taskType.equals("E")) {
            return new Event(at(message), getStatus(Integer.parseInt(isDone)));
        } else if (taskType.equals("D")) {
            return new Deadline(by(message), getStatus(Integer.parseInt(isDone)));
        } else {
            throw new IllegalTaskTypeException();
        }
    }


    static boolean getStatus(int i) {
        if (i == 0) {
            return false;
        } else {
            return true;
        }
    }
}
