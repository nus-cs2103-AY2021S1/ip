import java.io.IOException;

public class ToDoCommand extends Command {
    private static String description;

    public ToDoCommand(String description) {
        ToDoCommand.description = description;
    }
    public void execute (TaskList tasks, Ui ui, Storage storage) throws CustomException, IOException {
        Task temp = new ToDo(description);
        tasks.addTask(temp);
        Storage.appendToFile(temp);
        Ui.display("Yay! New task added:\n   " + temp +
                "\nNow you have " + tasks.getSize() + " tasks in your list.");
    }
}
