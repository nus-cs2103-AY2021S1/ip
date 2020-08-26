package duke.task;

import java.util.HashMap;

/**
 * Class that represents a task to be done.
 */
public class ToDo extends Task{

    /**
     * Creates an initialized <code>ToDo</code> with name.
     *
     * @param name Name of <code>ToDo</code>.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Creates a fully detailed <code>ToDo</code>.
     * For creating <code>ToDo</code> from save file.
     *
     * @param name Name of <code>ToDo</code>.
     * @param isDone Whether <code>ToDo</code> is done.
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns String formatted for representation of <code>ToDo</code> for display.
     *
     * @return Formatted String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts <code>ToDo</code> to <code>HashMap</code> representation.
     * Used for further processing to save file string.
     *
     * @return HashMap representation of properties.
     */
    @Override
    public HashMap<String, String> convertToHashMap() {

        HashMap<String, String> dict = super.convertToHashMap();

        dict.put("type", "duke.task.ToDo");

        return dict;
    }
}
