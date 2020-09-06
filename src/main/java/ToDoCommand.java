public class ToDoCommand extends Command {
    private String command;

    ToDoCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeEmptyToDoException {
        String message = "";
        try {
            if (command.split(" ").length == 1) {
                throw new DukeEmptyToDoException(command);
            }
            String tasker = Parser.stringBuilder(command.split(" "), 1, command.split(" ").length - 1);
            ToDo todoTask = new ToDo(tasker);
            tasklist.addTask(todoTask);
            message = ui.printTaskAdd(todoTask, tasklist.numOfTasks());
        } catch (DukeEmptyToDoException e) {
            message =  e.getMessage();
        }
        return message;
    }
}
