package duke;

public class AddToDoCommand extends Command {
    public AddToDoCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        if (parsedCommand.length != 2) {
            throw new DukeException("NANI??! Enter a description for your todo!\n");
        }

        String description = parsedCommand[1];
        Task taskToAdd = new ToDo(description);
        addTask(tasks, taskToAdd);
    }

    void addTask(TaskList<Task> tasks, Task taskToAdd) {
        tasks.add(taskToAdd);
        System.out.println("Hai! I have added this task to your list:\n"
                + taskToAdd);
        printToDoListSize(tasks);
    }

    void printToDoListSize(TaskList<Task> tasks) {
        System.out.println("You now have "
                + tasks.size()
                + " tasks in your list. Gambatte!\n");
    }
}
