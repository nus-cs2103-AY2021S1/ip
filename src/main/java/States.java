import java.util.function.Function;

public enum States{

    //The main State of tasks
    TODO(Todo::parseCommand),
    EVENT(Event::parseCommand),
    DEADLINE(Deadline::parseCommand);

    //Create a variable to store the Function / Bi Function
    protected Function<String, Task> taskSupplier = null;

    //Constructor for the function
    States(Function<String, Task> taskSupplier){
        this.taskSupplier = taskSupplier;
    }

    //Get the task given description
    public Task createTask(String description){
        return taskSupplier.apply(description);
    }


}