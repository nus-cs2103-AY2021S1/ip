package Tasks;

public class ToDo extends Task {

    /**
     * Constructor for the class.
     * @param name
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Generate a string of the task.
     * @return a string.
     */
    @Override
    public String printTask() {
        return "[T]" + super.printTask();
    }

    /**
     * Generate a String in a correct format to save in a file.
     * @return a string.
     */
    @Override
    public String toSave() {
        return "T " + super.toSave();
    }
}
