package main.java;

/**
 * Encapsulates a Todo which inherits a Task.
 */
public class Todo extends Task{
    /**
     * Constructs a Todo that has not been completed with a brief description.
     *
     * @param description a brief description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo, which may or may not be done, with a brief description.
     *
     * @param isDone indicates if the todo is completed.
     * @param description brief description of the todo.
     */
    public Todo(boolean isDone, String description) {super(isDone, description);}
}
