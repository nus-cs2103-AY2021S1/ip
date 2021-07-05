package data.task;

/**
 * A specific type of task that only contains a description of a task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the To_Do task into a string to be saved into a text file.
     * @return To_Do task in the form of a string.
     */
    public String toTxtFormat() {
        return "T | " + super.toTxtFormat();
    }

    /**
     * Parses a given string array into a To_Do task.
     * @param txtArray to be parsed into a To_Do task.
     * @return To_Do task based on input string array.
     */
    public static ToDo parse(String[] txtArray) {
        assert txtArray[1] != null; //This field should not be empty unless user manually modify TaskList.txt.
        assert txtArray[2] != null; //This field should not be empty unless user manually modify TaskList.txt.
        ToDo toDo = new ToDo(txtArray[2].trim());
        if (txtArray[1].trim().equals("1")) {
            toDo.markAsDone();
        }
        return toDo;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
