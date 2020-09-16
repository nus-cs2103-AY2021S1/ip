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
     * Returns the content in the Format object.
     *
     * @return content.
     */
    public T getContent() {
        return this.content;
    }

    /**
     * This method only can be used when the content
     * is of the type of String.
     *
     * Returns a Format object whose content has been
     * shortened by eliminating the extra spaces at the
     * beginning of the content and at the end of the content.
     *
     * @return The above described Format object.
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
     * Returns Task whose output of toString method
     * is equal to the content.
     *
     * @return Task whose output of toString method
     *         is equal to the content.
     */
    public static Task decodeTask(String input) {
        String[] inputArray = input.split(FormatString.SPACE.toString());
        char typeOfTask = inputArray[0].charAt(1);
        boolean isDone = false;

        if (inputArray[0].substring(4, 5).equals(FormatString.TICK.toString())) {
            isDone = true;
        }

        Task task;

        if (typeOfTask == 'T') {
            task = new Todo(inputArray[1]);
        } else {
            int lenOfArray = inputArray.length;
            int lenOfLastInArray = inputArray[lenOfArray - 1].length();
            String time = inputArray[lenOfArray - 1]
                    .substring(0, lenOfLastInArray - 1)
                    .substring(0, lenOfLastInArray - 1);
            if (typeOfTask == 'D') {
                task = new Deadline(inputArray[1], time);
            } else {
                task = new Event(inputArray[1], time);
            }
        }

        if (isDone) {
            task.setDone();
        }

        return task;
    }

    @Override
    public String toString() {
        if (this.content instanceof Task) {
            return Statement.TASKADDED.toString()
                    + content
                    + FormatString.NEXTLINE.toString()
                    + String.format(Statement.REPORT.toString(), Parser.getTaskList().getTaskList().size());
        }

        return content.toString();
    }
}

