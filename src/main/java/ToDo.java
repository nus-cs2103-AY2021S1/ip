public class ToDo extends Task {

    /**
     * Constructor for a todo object.
     * @param new_task Name of todo object.
     */
    public ToDo(String new_task) {
        super(new_task);
    }


    /**
     * Return the file format form for the task.
     */
    public String fileFormat() {
        return "T" + " | " + super.fileFormat();
    }

    /**
     * Return the converted time form of the task.
     */
    public String timeConverted() {
        return "T" + " | " + super.fileFormat();

    }
    @Override
    public String toString() {
        return "[T]" +  super.toString() ;
    }
}

