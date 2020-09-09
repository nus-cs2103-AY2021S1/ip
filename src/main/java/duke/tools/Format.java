package duke.tools;

import duke.exception.DukeException;
import duke.main.Statement;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * This class handles the final Format object
 * that is printed out by Duke.
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
    public Format<String> shorten() {
        assert content instanceof String;
        try {
            String input = (String) content;
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
                return new Format<>(FormatString.EMPTY.toString());
            }

            return new Format<>(input.substring(frontPos, backPos + 1));
        } catch (ClassCastException e) {
            System.out.println(DukeException.classCastException());
            return null;
        }
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
    public Task stringToTask() {
        assert content instanceof String;
        try {
            String input = (String) content;
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
        } catch (ClassCastException e) {
            System.out.println(DukeException.classCastException());
            return null;
        }
    }

    @Override
    public String toString() {
        if (this.content instanceof Task) {
            return FormatString.UNDERSCORE.toString()
                    + FormatString.NEXTLINE.toString()
                    + Statement.TASKADDED.toString()
                    + content
                    + FormatString.NEXTLINE.toString()
                    + String.format(Statement.REPORT.toString(), Parser.getTaskList().getTaskList().size())
                    + FormatString.NEXTLINE.toString()
                    + FormatString.UNDERSCORE;
        }

        return FormatString.UNDERSCORE.toString()
                + FormatString.NEXTLINE.toString()
                + content
                + FormatString.NEXTLINE.toString()
                + FormatString.UNDERSCORE.toString();
    }
}

