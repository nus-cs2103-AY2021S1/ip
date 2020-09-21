package task;

/**
 * ToDo object is a subclass of Task object but does not contain any extra information.
 *
 * @author Hakiem Rasid
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo object.
     *
     * @param name Description of this task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a String representation of ToDo object. Clearly labels the ToDo object to be
     * distinguishable from other Task objects.
     *
     * @return String representation of ToDo object.
     */
    @Override
    public String printTask() {
        StringBuilder sb = new StringBuilder();
        sb.append("[T]");
        sb.append(super.printTask());
        return sb.toString();
    }

}