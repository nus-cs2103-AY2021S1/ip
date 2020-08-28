import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {
    private CommandType commandType;
    private final String description;

    public AddCommand(CommandType commandType, String description) {
        this.commandType = commandType;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = null;
        if (this.commandType == CommandType.TODO) {
            task = new Todo(this.description);
        } else if (this.commandType == CommandType.DEADLINE) {
            String[] descElements = this.description.split(" /by ");
            try {
                LocalDateTime dateTime = LocalDateTime.parse(descElements[1],
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                String taskName = descElements[0];
                task = new Deadline(taskName, dateTime);
            } catch (Exception e) {
                throw new WrongDeadlineException("deadline", "/by");
            }
        } else { // should be event
            String[] descElements = this.description.split(" /at ");
            try {
                LocalDateTime dateTime = LocalDateTime.parse(descElements[1],
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                String taskName = descElements[0];
                task = new Event(taskName, dateTime);
            } catch (Exception e) {
                throw new WrongDeadlineException("event", "/at");
            }
        }

        if (task != null) {
            tasks.add(task, storage);

            System.out.println("Orh. I added:" +
                    "\n  " +
                    task.toString() +
                    "\nNow you got " +
                    tasks.getListLength() +
                    " things in the list.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
