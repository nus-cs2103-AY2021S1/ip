package main.java.com.jacob.duke.task;


public class Todo extends Task {

    /**
     * Constructor for To-do
     * @param description description of task
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

}
