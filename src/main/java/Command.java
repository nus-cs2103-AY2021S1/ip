import java.util.function.Function;

public enum Command {

    //The main State of tasks
    TODO(Todo::parseCommand),
    EVENT(Event::parseCommand),
    DEADLINE(Deadline::parseCommand);

    //Create a variable to store the Function / Bi Function
    protected Function<String, Task> commandParser = null;

    //Constructor for the function
    Command(Function<String, Task> commandParser){
        this.commandParser = commandParser;
    }

    //Get the task given description
    public Task createTask(String description){
        return commandParser.apply(description);
    }


}