import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeleteCommand extends Command{
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public DeleteCommand(String input) {
        super(input);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidSaveFileException {
        if(super.input.length() <= 7) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of a delete operation cannot be empty / invalid index.");
        }
        int index = Integer.parseInt(super.input.substring(7));
        if( index >= tasks.getTasks().size() || index<0) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of a delete operation cannot be empty / invalid index.");
        }
        Task task = tasks.getTasks().get(index-1);
        tasks.getTasks().remove(index-1);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t"+task.toString());
        System.out.println("\tNow you have " + tasks.getTasks().size() + " tasks in the list.");
        storage.saveFile(tasks.getTasks());
    }
    public boolean isExit() {
        return false;
    }
}
