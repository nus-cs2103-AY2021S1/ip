import java.io.IOException;

public class EventCommand extends Command {
    private static String description;
    private static String at;

    public EventCommand(String description, String at) {
        EventCommand.description = description;
        EventCommand.at = at;
    }
    public void execute (TaskList tasks, Ui ui, Storage storage) throws CustomException, IOException {
//        try {
            Task temp = new Event(description, at);
            tasks.addTask(temp);
            Storage.appendToFile(temp);
            Ui.display("Yay! New task added:\n   " + temp +
                    "\nNow you have " + tasks.getSize() + " tasks in your list.");
//        } catch (Exception e) {
//            throw new CustomException("Please input a suitable date of <yyyy-mm-dd> format!");
//        }
    }
}
