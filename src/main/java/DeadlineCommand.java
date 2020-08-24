import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineCommand extends Command {
    public final static String COMMAND_WORD = "deadline";

    DeadlineCommand(String arguments) {
        super(arguments);
    }

    private static String formatDate(String date) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate localDate = LocalDate.parse(date);
        return localDate.format(pattern);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String[] s = this.arguments.split(" /by ");
        String desc = s[0];
        String by = formatDate(s[1]);
        Task task = new Deadline(desc, by);
        tasks.addTask(task);
        try {
            storage.save(tasks.getTasks());
        } catch (DukeException e) {
            System.err.println(e);
        }
        String message = this.ADD_MESSAGE + "  " + task.toString()
                + "\n" + tasks.replyNumTasks();
        return new CommandResult(message);
    }
}
