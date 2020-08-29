import java.time.LocalDate;
import java.time.LocalTime;

public class TextAndTaskConverter {

    private final String text;

    TextAndTaskConverter(String text) {
        this.text = text;
    }

    public static Task textConverter(String text) {
        String[] splitLines = text.split(" ## ");
        String description = splitLines[2];

        if (splitLines.length == 3) {
            Todo task = new Todo(description);
            if (splitLines[1].trim().equals("1")) {
                task.setDone();
            }
            return task;

        } else if (splitLines.length == 4) {
            Task task = null;
            if (splitLines[0].trim().equals("D")) {
                LocalDate date = LocalDate.parse(splitLines[3]);
                task = new Deadline(description, date);

                if (splitLines[1].equals("1")) {
                    task.setDone();
                }

            } else if (splitLines[0].trim().equals("E")) {
                String[] dateAndTime = splitLines[3].split(" ", 2);

                LocalDate date = LocalDate.parse(dateAndTime[0]);
                LocalTime time = LocalTime.parse(dateAndTime[1]);

                task = new Event(description, date, time);

                if (splitLines[1].equals("1")) {
                    task.setDone();
                }
            }
            return task;

        } else {
            System.out.println("else");
            return null;
        }
    }

    public static String getTaskName(String text) {
        String[] splitLines = text.split("/", 2);
        return splitLines[0];
    }

    public static LocalDate getDate(String text) {
        String[] splitLines = text.split("/", 2);
        String trimText = splitLines[1].trim();

        if (!trimText.contains(" ")) {
            return LocalDate.parse(trimText);

        } else {
            String[] splitLines2 = trimText.split(" ", 2);
            return LocalDate.parse(splitLines2[0]);
        }
    }

    public static LocalTime getTime(String text) {
        String[] splitLines = text.split("/", 2);
        String trimText = splitLines
        [1].trim();

        if (trimText.split(" ", 2).length == 0) {
            return null;

        } else {
            String[] splitLines2 = trimText.split(" ", 2);
            LocalTime t = LocalTime.parse(splitLines2[1]);
            return t;
        }
    }
}

