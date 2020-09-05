public class DoneCommand extends Command {
    private String command;

    DoneCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeInvalidDoneNumException, DukeEmptyDoneNumException {
        String message = "";
        try {
            String[] splitMessage = command.split(" ");
            if (splitMessage.length == 1) {
                throw new DukeEmptyDoneNumException(command);
            }
            int doneTask = Integer.parseInt(splitMessage[1]) - 1;
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
