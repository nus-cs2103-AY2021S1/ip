public class ToDoCommand extends Command {
    private String command;

    ToDoCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasklist, UI ui) throws DukeEmptyToDoException {
        try {
            if (command.split(" ").length == 1) {
                throw new DukeEmptyToDoException(command);
            }
            String tasker = Parser.stringBuilder(command.split(" "), 1, command.split(" ").length - 1);
            ToDo todoTask = new ToDo(tasker);
            tasklist.addTask(todoTask);
            UI.printTaskAdd(todoTask, tasklist.numOfTasks());
        } catch (DukeEmptyToDoException e) {
            UI.printFormattedMessage("OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
