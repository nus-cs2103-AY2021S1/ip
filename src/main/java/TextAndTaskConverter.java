import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Handles the conversion of texts and tasks.
 */
public class TextAndTaskConverter {

    private final String text;

    TextAndTaskConverter(String text) {
        this.text = text;
    }

    /**
     * Converts texts into tasks.
     * @param text line of text read from the saved file.
     * @return respective task that corresponds to the text.
     */
    public static Task textConverter(String text) {
        String[] split = text.split(" ## ");
        String description = split[2];

        if (split.length == 3) {
            Todo task = new Todo(description);
            if (split[1].trim().equals("1")) {
                task.setDone();
            }
            return task;

        } else if (split.length == 4) {
            Task task = null;
            if (split[0].trim().equals("D")) {
                LocalDate date = LocalDate.parse(split[3]);
                task = new Deadline(description, date);

                if (split[1].equals("1")) {
                    task.setDone();
                }

            } else if (split[0].trim().equals("E")) {
                String[] dateAndTime = split[3].split(" ", 2);

                LocalDate date = LocalDate.parse(dateAndTime[0]);
                LocalTime time = LocalTime.parse(dateAndTime[1]);

                task = new Event(description, date, time);

                if (split[1].equals("1")) {
                    task.setDone();
                }
            }
            return task;

        } else {
            System.out.println("else");
            return null;
        }
    }

    /**
     * Gets the name of the task.
     * @param text line of text read from the saved file.
     * @return name of the task.
     */
    public static String getTaskName(String text) {
        String[] split1 = text.split("/", 2);
        return split1[0];
    }

    /**
     * Gets the date the task needs to be done by.
     * @param text line of text read from the saved file.
     * @return date of completion.
     */
    public static LocalDate getDate(String text) {
        String[] split1 = text.split("/", 2);
        String trimText = split1[1].trim();

        if (!trimText.contains(" ")) {
            return LocalDate.parse(trimText);

        } else {
            String[] split2 = trimText.split(" ", 2);
            return LocalDate.parse(split2[0]);
        }
    }

    /**
     * Gets the time the task needs to be done by.
     * @param text line of text read from the saved file.
     * @return time of completion.
     */
    public static LocalTime getTime(String text) {
        String[] split1 = text.split("/", 2);
        String trimText = split1[1].trim();

        if (trimText.split(" ", 2).length == 0) {
            System.out.println("trim text: " + trimText);
            return null;

        } else {
            String[] split2 = trimText.split(" ", 2);
            return LocalTime.parse(split2[1]);
        }
    }
}

