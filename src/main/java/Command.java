public class Command {
    CommandType commandType;
    String[] commandArr;

    public Command(CommandType commandType, String[] commandArr) {
        this.commandType = commandType;
        this.commandArr = commandArr;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    public boolean isExit() {
        if (commandType.equals("exit")) {
            return true;
        } else {
            return false;
        }
    }
}
