package duke.task;

import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;

/**
 * represents a to do task
 */
public class ToDo extends Task{

    /**
     * creates a new to do task based on the given description
     * @param taskDescription the full description of the to do task in the following format:
     *                        "todo todo_task_description"
     * @throws EmptyDescriptionException if the description given is empty
     */
    public ToDo(String taskDescription) throws EmptyDescriptionException {
        if (taskDescription.length() <= 5) {
            throw new EmptyDescriptionException("oops! the description of a todo cannot be empty");
        } else {
            int space = taskDescription.indexOf(" ");

            this.task = taskDescription.substring(space + 1);
            this.done = false;
        }
    }

    /**
     * returns a string representation of the to do task
     * @return string representation of the to do task
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * encodes the to do task to a more appropriate format for storage
     * @return the encoded version of the to do task
     */
    @Override
    public String encode() {
        return "T | " +
                this.isDoneInt() + " | " +
                this.task;
    }

    /**
     * decodes a given line of text and transforms it into a to do task
     * @param string the line of text to decode
     * @return the to do task that has been decoded from the given input
     * @throws EmptyDescriptionException if the description given is empty
     */
    public static ToDo decode(String string) throws EmptyDescriptionException {
        String[] split = string.split(" \\| ");

        String taskDescription = "todo " + split[2];

        ToDo todo = new ToDo(taskDescription);

        if (split[1].contains("1")) {
            todo.markAsDone();
        }

        return todo;
    }
}
