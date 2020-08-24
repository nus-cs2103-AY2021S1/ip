public class SimpleCommand extends Command {

    private final String input;
    private final SimpleCommandType type;

    protected SimpleCommand(String input, SimpleCommandType type) {
        this.input = input;
        this.type = type;
    }

    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (NumberAction.checkIfNumber(input)) {
            int digit = Integer.parseInt(input);
            if (tasks.checkIfValid(digit)) {
                Task current = tasks.get(digit - 1);
                if (type == SimpleCommandType.DONE) {
                    if (current.isDone()) {
                        throw new TaskAlreadyDoneException();
                    } else {
                        current.markAsDone();
                        ui.markTaskAsDone(current);
                    }
                } else {
                    tasks.delete(digit - 1);
                    ui.deleteTask(current, tasks.size());
                }
            } else {
                throw new InvalidTaskNumberException(tasks.size());
            }
        } else {
            if (type == SimpleCommandType.DONE) {
                throw new InvalidDoneException();
            } else {
                throw new InvalidDeleteException();
            }
        }
        storage.update(tasks);
    }
}
