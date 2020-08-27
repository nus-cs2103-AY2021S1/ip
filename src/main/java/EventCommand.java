public class EventCommand implements Command{

    protected final String command;

    public EventCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] commandArgs = command.split(" ");
        if (commandArgs.length == 1 || commandArgs[1].equals("/at") || !command.contains("/at")) {
            throw new MissingDescriptionException();
        } else if (!commandArgs[2].equals("/at")) {
            throw new MissingTagException();
        } else if (commandArgs.length != 5) {
            throw new MissingDateTimeException();
        } else {
            String subCommand = command.substring(9);
            String[] subCommandArgs = subCommand.split("/at");
            Event e = new Event(subCommandArgs[0], subCommandArgs[1], false);
            taskList.addTask(e);
            storage.write(taskList.tasks);
            ui.showLine();
            ui.createEventSuccessMessage(e, taskList.tasks.size());
            ui.showLine();
        }
    }

    public boolean isExit() {
        return false;
    }
}
