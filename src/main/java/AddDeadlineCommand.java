public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        // if length is not 2, nothing was passed in after 'deadline'
        if (parsedCommand.length != 2) {
            throw new DukeException("NANI??! Enter a description for your deadline!\n");
        }

        // if description is lacking a /by keyword
        String description = parsedCommand[1];
        if (description.indexOf("/by") < 0) {
            throw new DukeException("Please enter a valid deadline! Remember to add '/by'\n");
        }

        String[] descriptionArray = description.split("/by");
        if (descriptionArray.length != 2) {
            throw new DukeException("NANI??! Enter your deadline name & an end-time!\n");
        }

        String deadlineName = descriptionArray[0];
        String deadlineEndTime = descriptionArray[1];

        Task taskToAdd = new Deadline(deadlineName, deadlineEndTime);
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
