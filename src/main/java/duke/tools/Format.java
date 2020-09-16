package duke.tools;

import duke.main.Statement;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Handles the format of strings that is to be printed out by Duke.
 *
 * @param <T> The data type of the object in the Format.
 * */
public class Format<T> {
    private final T content;

    /**
     * Constructs Format object with content.
     *
     * @param content object of any type.
     */
    public Format(T content) {
        this.content = content;
    }

    /**
     * Returns a string whose content has been
     * shortened by eliminating the extra spaces at the
     * beginning of the content and at the end of the content.
     *
     * @return the above described Format object.
     */
    public static String shorten(String input) {
        int length = input.length();
        int frontPos = 0;
        int backPos = length - 1;

        while (frontPos < length && input.charAt(frontPos) == ' ') {
            frontPos++;
        }

        while (backPos >= 0 && input.charAt(backPos) == ' ') {
            backPos--;
        }

        if (frontPos > backPos) {
            return FormatString.EMPTY.toString();
        }

        return input.substring(frontPos, backPos + 1);
    }

    /**
     * This method only can be used when the content
     * is of the type of String.
     *
     * Returns task whose output of toString method
     * is equal to the content.
     *
     * @return task whose output of toString method
     *         is equal to the content.
     */
    public static Task decodeTask(String input) {
        String[] inputArray = input.split(" ");
        char typeOfTask = inputArray[0].charAt(1);
        boolean isDone = false;

        if (inputArray[0].substring(4, 5).equals(FormatString.TICK.toString())) {
            isDone = true;
        }

        Task task;
        StringBuilder details = new StringBuilder(inputArray[1]);
        int lenOfArray = inputArray.length;

        if (typeOfTask == 'T') {
            for (int i = 2; i < lenOfArray; i++) {
                details.append(" ").append(inputArray[i]);
            }
            task = new Todo(details.toString());
        } else {
            int lenOfLastInArray = inputArray[lenOfArray - 1].length();
            String time = inputArray[lenOfArray - 1]
                    .substring(0, lenOfLastInArray - 1)
                    .substring(0, lenOfLastInArray - 1);

            for (int i = 2; i < lenOfArray - 2; i++) {
                details.append(" ").append(inputArray[i]);
            }
            if (typeOfTask == 'D') {
                task = new Deadline(details.toString(), time);
            } else {
                task = new Event(details.toString(), time);
            }
        }

        if (isDone) {
            task.setDone();
        }

        return task;
    }

    /**
     * Returns a string with update statement.
     * Especially designed for <code>UpdateCommand</code>
     *
     * @return a string with update statement.
     */
    public String updateFormat() {
        return Statement.UPDATE.toString()
                + content
                + FormatString.NEXT_LINE.toString()
                + String.format(Statement.REPORT.toString(), Parser.getTaskList().getTaskList().size());
    }

    @Override
    public String toString() {
        if (this.content instanceof Task) {
            return Statement.TASKADDED.toString()
                    + content
                    + FormatString.NEXT_LINE.toString()
                    + String.format(Statement.REPORT.toString(), Parser.getTaskList().getTaskList().size());
        }

        return content.toString();
    }
}

