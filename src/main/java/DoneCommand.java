public class DoneCommand extends Command {
    private String command;

    DoneCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasklist, UI ui) throws DukeInvalidDoneNumException {
        try {
            int doneTask = Integer.parseInt(command.split(" ")[1]) - 1;
            if (doneTask + 1 > tasklist.numOfTasks() || doneTask < 0) {
                throw new DukeInvalidDoneNumException(command);
            }
            tasklist.markAsDone(doneTask);
        } catch (DukeInvalidDoneNumException e) {
            UI.printFormattedMessage("OOPS!!! The invalid done number.");
        }
    }

}
