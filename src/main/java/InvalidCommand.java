public class InvalidCommand extends Command {
    protected boolean isExit() {
        return false;
    }

    protected void execute(String input, TaskList taskList, Storage storage) throws DukeException {
        throw new DukeException("\tOops! I'm not sure what you meant!\n"
                + "\tPlease try again!");
    }
}
