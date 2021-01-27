package duke.exception;

import duke.tool.Emoji;

public class NoDateException extends DukeException {
    /**
     * Constructs an empty description exception.
     *
     * @param type type of the task.
     */
    public NoDateException(String type) {
        super("OOPS!!! I'm sorry, \n     but the time cannot be empty. \n"
                + "    You can do it by adding time after " + type + " ." + Emoji.SMILE.toString());
    }
}

