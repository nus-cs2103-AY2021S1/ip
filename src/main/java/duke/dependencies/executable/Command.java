package duke.dependencies.executable;

import duke.dependencies.task.Task;

public class Command implements Executable {
    private CommandType command;
    private Task task;
    private Command(CommandType command, Task task) {
        this.command = command;
        this.task = task;
    }


    @Override
    public CommandType getType() {
        return command;
    }

    @Override
    public Task getTask() {
        return this.task;
    }

    /* -------------------------------------- Static Factory Methods ---------------------------------------------*/

    public static Command createAddCommand(Task task) {
        return new Command(CommandType.ADD, task);
    }

    public static Command createDoneCommand(Task task) {
        return new Command(CommandType.DONE, task);
    }

    public static Command createListCommand(Task task) {
        return new Command(CommandType.LIST, Task.createEmptyTask());
    }

    public static Command createDeleteCommand(Task task) {
        return new Command(CommandType.DELETE, task);
    }
}
