package duke.exception;

import duke.tool.Emoji;

public class NoDescriptionException extends DukeException {
    /**
     * Constructs an empty description exception.
     *
     * @param type type of the task.
     */
    public NoDescriptionException(String type) {
        super(" OOPS!!! I'm sorry, \n     but the description cannot be empty. \n"
                + "    You can do it by adding description after " + type + " ." + Emoji.SMILE.toString());
    }
}
