package ultron.commands;

import java.util.function.Function;

import ultron.tasks.Deadline;
import ultron.tasks.Task;
import ultron.tasks.Event;
import ultron.tasks.Todo;

public enum TaskCommand {

    //The main State of tasks
    todo(Todo::parseCommand),
    event(Event::parseCommand),
    deadline(Deadline::parseCommand);

    //Create a variable to store the Function
    public final Function<String, Task> commandParser;

    //Constructor for the function
    TaskCommand(final Function<String, Task> commandParser) {
        this.commandParser = commandParser;
    }

    //Get the task given description
    public Task createTask(final String description) {
        return commandParser.apply(description);
    }


}
