public class DoneCommand extends Command {
    private String command;

    DoneCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeInvalidDoneNumException {
        String message = "";
        try {
            int doneTask = Integer.parseInt(command.split(" ")[1]) - 1;
            if (doneTask + 1 > tasklist.numOfTasks() || doneTask < 0) {
                throw new DukeInvalidDoneNumException(command);
            }
            message = tasklist.markAsDone(doneTask);
        } catch (DukeInvalidDoneNumException e) {
            message = e.getMessage();
        }
        return message;
    }

}
