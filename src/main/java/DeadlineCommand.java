public class DeadlineCommand extends Command {
    private String command;

    DeadlineCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasklist, UI ui) throws DukeEmptyDeadlineException, DukeEmptyDeadlineTimeException {
        try {
            if (command.split(" ").length == 1) {
                throw new DukeEmptyDeadlineException(command);
            }
            String deadliner = Parser.stringBuilder(command.split(" "), 1, command.split(" ").length - 1);
            String[] deadlinerparts = deadliner.split(" /by ");
            if (deadlinerparts.length == 1) {
                throw new DukeEmptyDeadlineTimeException(command);
            }
            Deadline deadlineTask = new Deadline(deadlinerparts[0], deadlinerparts[1]);
            tasklist.addTask(deadlineTask);
            ui.printTaskAdd(deadlineTask, tasklist.numOfTasks());
        } catch (DukeEmptyDeadlineException e) {
            ui.printFormattedMessage("OOPS!!! The description of a deadline cannot be empty.");
        } catch (DukeEmptyDeadlineTimeException e) {
            ui.printFormattedMessage("OOPS!!! The description of a deadline time cannot be empty.");
        }

    }
}
