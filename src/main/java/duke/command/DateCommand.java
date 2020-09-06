package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;



public class DateCommand extends Command {
    public DateCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException {
        Pattern pattern = Pattern.compile("date [0-9]{1,2}/[0-9]{1,2}/[0-9]{4,4}");
        if (!pattern.matcher(getInputCommand()).matches()) {
            throw new DukeCommandException("\u2639 OOPS!!! Wrong 'date' command format!");
        } else {
            String dateString = getInputCommand().split(" ")[1];
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            ui.printMessage("Here are the tasks of date " + date + ":");

            int taskIndexCounter = 1;
            for (Task task: list.getList()) {
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    LocalDateTime deadlineTime = deadline.getBy();
                    if (date.getYear() == deadlineTime.getYear() && date.getDayOfYear() == deadlineTime.getDayOfYear()) {
                        ui.printMessage("" + taskIndexCounter + "." + task);
                        taskIndexCounter++;
                    }
                }
                if (task instanceof Event) {
                    Event event = (Event) task;
                    LocalDateTime eventTime = event.getAt();
                    if (date.getYear() == eventTime.getYear() && date.getDayOfYear() == eventTime.getDayOfYear()) {
                        ui.printMessage("" + taskIndexCounter + "." + task);
                        taskIndexCounter++;
                    }
                }
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
