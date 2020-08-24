/**
 * Abstract class of all the commands that is created
 * after parsing the user's input
 */

abstract class Command {

    public final String input;

    public Command(String input) {
        this.input = input;
    }
    abstract void execute(TaskList tasks,Ui ui, Storage storage) throws DukeException;
    abstract boolean isExit();
}
