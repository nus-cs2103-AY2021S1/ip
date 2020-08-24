public class AddCommand extends Command{

    String input;

    AddCommand(String input) {
        this.input = input;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        int space = input.indexOf(" ");
        int slash = input.indexOf("/");
        int length = input.length();
        Task newTask;
        switch(input.substring(0, space)) {
        case "todo":
            newTask = new ToDo(input.substring(space, length));
            break;
        case "deadline":
            String dateStringDeadline = input.substring(slash + 1, input.length());
            newTask = new Deadline(input.substring(space, slash), dateStringDeadline);
            break;
        case "event":
            String dateStringEvent = input.substring(slash + 1, input.length());
            newTask = new Event(input.substring(space, slash), dateStringEvent);
            break;
        default:
            newTask = null;
        }
        tasks.addTask(newTask, storage);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
