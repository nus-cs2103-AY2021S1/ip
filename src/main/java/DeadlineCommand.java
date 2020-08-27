public class DeadlineCommand implements Command {

    protected final String command;

    public DeadlineCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] commandArgs = command.split(" ");
        if (commandArgs.length == 1 || commandArgs[1].equals("/by") || !command.contains("/by")) {
            throw new MissingDescriptionException();
        } else if (!commandArgs[2].equals("/by")) {
            throw new MissingTagException();
        } else if (commandArgs.length != 5) {
            throw new MissingDateTimeException();
        } else {
            String subCommand = command.substring(9);
            String[] subCommandArgs = subCommand.split("/by");
            Deadline d = new Deadline(subCommandArgs[0], subCommandArgs[1], false);
            taskList.addTask(d);
            storage.write(taskList.tasks);
            ui.showLine();
            ui.createDeadlineSuccessMessage(d, taskList.tasks.size());
            ui.showLine();
        }
    }

    public boolean isExit() {
        return false;
    }

}
