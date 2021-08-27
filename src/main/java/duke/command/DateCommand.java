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
            String[] parseArray = getInputCommand().split(" ");
            String[] dateSegments = parseArray[1].split("/");
            for (int i = 0; i < 2; i++) {
                if (dateSegments[i].length() == 1) {
                    dateSegments[i] = "0" + dateSegments[i];
                }
            }
            parseArray[1] = dateSegments[0] + "/" + dateSegments[1] + "/" + dateSegments[2];
            String dateString = parseArray[1];
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            int taskIndexCounter = 1;
            StringBuilder stringBuilder = new StringBuilder();
            for (Task task: list.getList()) {
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    LocalDateTime deadlineTime = deadline.getBy();
                    if (date.getYear() == deadlineTime.getYear() && date.getDayOfYear() == deadlineTime.getDayOfYear()) {
                        stringBuilder.append(taskIndexCounter).append(".").append(task).append("\n");
                        taskIndexCounter++;
                    }
                }
                if (task instanceof Event) {
                    Event event = (Event) task;
                    LocalDateTime eventTime = event.getAt();
                    if (date.getYear() == eventTime.getYear() && date.getDayOfYear() == eventTime.getDayOfYear()) {
                        stringBuilder.append(taskIndexCounter).append(".").append(task).append("\n");
                        taskIndexCounter++;
                    }
                }
            }

            if (stringBuilder.length() > 0) {
                ui.printMessage("Here are the tasks of date " + date + ":");
                ui.printMessage(stringBuilder.toString());
            } else {
                ui.printMessage("No matching task found!");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
