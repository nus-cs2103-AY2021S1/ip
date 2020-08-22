public class DeadlineCommand extends ComplexCommand {

    public DeadlineCommand(String params) {
        super(params);
    }

    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {
        try {
            String[] splitParams = this.parseParams();
            Deadline newDeadline = new Deadline(splitParams[0], splitParams[1]);
            taskManager.storeTask(newDeadline);
        } catch (DukeInputException e) {
            ui.displayException(e);
        }
    }

    public String[] parseParams() throws DukeInputException {
        if (this.params.equals("")) {
            throw new DukeInputException("'deadline' requires parameters.\n" +
                    "Use case: deadline <name> /by <deadline>");
        }

        String[] splitParams = this.params.split("/by ", 2);
        if (splitParams.length != 2) {
            throw new DukeInputException("<" + this.params + "> is not valid for the 'deadline' command.\n" +
                    "Please add a /by deadline to the task.");
        }
        return splitParams;
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
