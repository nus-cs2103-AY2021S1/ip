package duke.task.todo;

import duke.task.Task;

import java.time.LocalDateTime;

/**
 * Todo Task class which contains the information for a todo task.
 */
public class ToDo extends Task {

    /**
     * Constructor for a todo task object
     * @param name name of the task
     * @param date date where the task is created.
     */
    public ToDo(String name, LocalDateTime date){
        super(name, date);
    }

    /**
     * Overloaded constructor when the todo task object is re-created from a tasklist.txt file.
     * @param line input from the tasklist.txt file
     */
    public ToDo(String line) {
        super(line);
    }

    @Override
    public String toString(){
        return "[T]"+ super.toString();
    }
}
