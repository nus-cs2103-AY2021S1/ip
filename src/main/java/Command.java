public abstract class Command {

    protected String[] stringArray;

    Command(String[] stringArray) {
        this.stringArray = stringArray;
    }

    public boolean isFirstIndexEmpty() {
        return stringArray.length < 2 || stringArray[1].equals("");
    }

    //Empty function to be overwridden
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    };

    public String getFirstIndex() {
        return getArray()[1];
    }

    public String[] getArray() {
        return this.stringArray;
    }
}
