import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Util {
    private static final String TASK_DELIMITER = "`";

    public static LocalDateTime convertStringToDateTime(String dateTime) {
        //Allow format of "YYYY-MM-dd HHmm", "dd/MM/yyyy HHmm"; Set HHmm to 0000 if not found.

        try {
            if (!dateTime.contains(" "))
                dateTime = dateTime + " 0000"; //pad with time if the input only contains date.

            if (dateTime.charAt(4) == '-') {
                return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } else if (dateTime.charAt(2) == '/') {
                return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            } else
                throw new DukeException("Invalid Date / time format...");
        } catch (DateTimeParseException dtpe) {
            throw new DukeException ("Invalid Date / time format...");
        }
    }

    public static Task convertStringToTask(String s) {
        String[] split = s.split(TASK_DELIMITER);
        Task t;

        //TODO: may want to check for file modification. Or invalid line input
        switch (split[0]) {
            case "T":
                t = new ToDo(split[2]);
                break;
            case "D":
                t = new Deadline(split[2], convertStringToDateTime(split[3]));
                break;
            case "E":
                t = new Event(split[2], convertStringToDateTime(split[3]));
                break;
            default:
                throw new DukeException("Error in reading this line...");
        }

        if (split[1].equals("1"))
            t.setDone();

        return t;
    }
}
