import java.util.function.Function;

public enum Command {

    //The main State of tasks
    TODO(Todo::parseCommand),
    EVENT(Event::parseCommand),
    DEADLINE(Deadline::parseCommand);

    //Create a variable to store the Function
    protected final Function<String, Task> commandParser;

    //Constructor for the function
    Command(final Function<String, Task> commandParser) {
        this.commandParser = commandParser;
    }

    //Get the task given description
    public Task createTask(final String description) {
        return commandParser.apply(description);
    }


}
