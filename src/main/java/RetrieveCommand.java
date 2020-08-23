import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RetrieveCommand extends Command {
    private final LocalDate date;

    public RetrieveCommand(LocalDate date) {
        super(false);
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            StringBuffer sb = new StringBuffer();
            int index = 1;
            sb.append(String.format("Here are the deadlines and events happening on %s:\n\t ",
                    date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))));
            boolean hasTasks = false;
            for (Task t : tasks.getList()) {
                if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    if (d.getDateTime().toLocalDate().isEqual(date)) {
                        hasTasks = true;
                        sb.append(index).append(".").append(t.toString()).append("\n\t ");
                        index++;
                    }
                }
                if (t instanceof Event) {
                    Event e = (Event) t;
                    if (e.getDateTime().toLocalDate().isEqual(date)) {
                        hasTasks = true;
                        sb.append(index).append(".").append(t.toString()).append("\n\t ");
                        index++;
                    }
                }
            }
            if (hasTasks) {
                ui.printMessage(sb.delete(sb.length() - 3, sb.length() - 1).toString());
            } else {
                    ui.printMessage(String.format("You do not have any deadlines or events happening on %s! :)",
                                    date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))));
            }
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateException();
        }

    }

    @Override
    public boolean getIsExit() {
        return isExit;
    }
}
