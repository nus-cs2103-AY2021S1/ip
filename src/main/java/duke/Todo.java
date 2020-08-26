package main.java.duke;

public class Todo extends Task {

    /**
     * Constructor of Todo class.
     *
     * @param description  Content of the Todo object.
     */
    public Todo(String description) {
        super(description);
    }


    /**
     * Another Constructor of Todo class.
     *
     * @param description  Content of the Todo object.
     * @param isDone  Completion status of the Todo object.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }


    /**
     * Return Type representation of the Todo object.
     *
     * @return String "T" as Type representation of the Todo object.
     */
    public String getType() {
        return "T";
    }


    /**
     * Return String representation of the Todo object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
