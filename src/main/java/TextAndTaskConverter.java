import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Handles the conversion of texts and tasks.
 */
public class TextAndTaskConverter {

    /**
     * Converts texts into tasks.
     * @param text line of text read from the saved file.
     * @return respective task that corresponds to the text.
     */
    public static Task convertTextToTask(String text) {
        String[] splitLine = text.split(" ## ");
        String done = splitLine[1];
        String priority = splitLine[2];
        String description = splitLine[3];

        if (splitLine.length == 4) {
            Todo task = new Todo(description);
            if (done.trim().equals("1")) {
                task.setDone();
            }
            task.setPriority(Integer.parseInt(priority));
            return task;

        } else if (splitLine.length == 5) {
            Task task = null;
            String taskType = splitLine[0].trim();

            if (taskType.equals("D")) {
                LocalDate date = LocalDate.parse(splitLine[4]);
                task = new Deadline(description, date);

                if (done.equals("1")) {
                    task.setDone();
                }

            } else if (taskType.equals("E")) {
                String[] dateAndTime = splitLine[4].split(" ", 2);

                LocalDate date = LocalDate.parse(dateAndTime[0]);
                LocalTime time = LocalTime.parse(dateAndTime[1]);

                task = new Event(description, date, time);

                if (done.equals("1")) {
                    task.setDone();
                }
            }

            assert task != null;
            task.setPriority(Integer.parseInt(priority));
            return task;

        } else {
            return null;
        }
    }

    /**
     * Gets the name of the task.
     * @param input line of text read from the user input.
     * @return name of the task.
     */
    public static String getTaskName(String input) {
        String[] splitInput = input.split("/", 2);
        return splitInput[0];
    }

    /**
     * Gets the date the task needs to be done by.
     * @param input line of text read from the user input.
     * @return date of completion.
     */
    public static LocalDate getDate(String input) {
        String[] splitInput = input.split("/", 2);
        String trimText = splitInput[1].trim();

        if (!trimText.contains(" ")) {
            //for deadline because it only takes in the date
            return LocalDate.parse(trimText);

        } else {
            //for event because it takes in the date and time
            String[] splitDateAndTime = trimText.split(" ", 2);
            return LocalDate.parse(splitDateAndTime[0]);
        }
    }

    /**
     * Gets the time the task needs to be done by.
     * @param input line of text read from the user input.
     * @return time of completion.
     */
    public static LocalTime getTime(String input) {
        String[] splitInput = input.split("/", 2);
        String trimText = splitInput[1].trim();

        if (!trimText.contains(" ")) {
            //for deadline because it only takes in the date
            return null;

        } else {
            //for event because it takes in the date and time
            String[] splitDateAndTime = trimText.split(" ", 2);
            LocalTime t = LocalTime.parse(splitDateAndTime[1]);
            return t;
        }
    }
}

