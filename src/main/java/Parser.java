import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public Parser() {}

    Task parseTxtToTask(String str) {
        try {
            String type = str.substring(1, 2);
            String status = str.substring(4, 5);
            String descriptionAndDate = str.substring(6);
            String[] arr = descriptionAndDate.split("\\(");
            String description = arr[0].trim();
            String date = "";
            Task task;


            if (type.equals("E") || type.equals("D")) {
                date = arr[1].substring(4).replace(")", "");
            }

            if (type.equals("T")) {
                task = new ToDo(description);
            } else if (type.equals("E")) {
                task = new Event(description, date);
            } else { // "D"
                task = new Deadline(description, parseDateAndTime(date));
            }

            if (status.equals("\u2713")) {
                task.markAsDone();
            }
            return task;
        }catch (IndexOutOfBoundsException|NullPointerException e) {
            System.out.println(e.getMessage() + "Error in tasks file");
            throw e;
        }
    }

    String[] parseString(String input) {
        String[] tokens = input.split(" ");
        return tokens;
    }

    LocalDateTime parseDateAndTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime d1 = LocalDateTime.parse(dateTime,formatter);
        return d1;
    }
}
