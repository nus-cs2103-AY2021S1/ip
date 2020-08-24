package ultron.commands;

import ultron.tasks.Deadline;
import ultron.tasks.Event;
import ultron.tasks.Task;
import ultron.tasks.Todo;

import java.util.function.Function;

public enum TaskCommand {

    //The main State of tasks
    todo(Todo::parseCommand),
    event(Event::parseCommand),
    deadline(Deadline::parseCommand);

    //Create a variable to store the Function
    private final Function<String, Task> commandParser;

    //Constructor for the function
    TaskCommand(final Function<String, Task> commandParser) {
        this.commandParser = commandParser;
    }

    //Get the task given description
    public Task createTask(final String description) {
        return commandParser.apply(description);
    }

    public Function<String, Task> getCommandParser() {
        return commandParser;
    }


}
