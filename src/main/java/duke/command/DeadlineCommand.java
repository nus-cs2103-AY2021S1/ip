package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;
import duke.exception.DukeStorageException;
import duke.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command{
    public DeadlineCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException, DukeStorageException {
        Pattern pattern = Pattern.compile("deadline ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*) /by [0-9]{1,2}/[0-9]{1,2}/[0-9]{4,4} [0-9]{4,4}");
        if(!pattern.matcher(getInputCommand()).matches()) {
            throw new DukeCommandException("\u2639 OOPS!!! Wrong 'deadline' command format!");
        } else {
            String[] s = getInputCommand().substring(9).split(" /by ");
            Deadline deadline;
            try {
                deadline = new Deadline(s[0], LocalDateTime.parse(s[1], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
            } catch (DateTimeParseException e) {
                throw new DukeCommandException("Invalid date!");
            }

            list.getList().add(deadline);

            ui.printMessage("Got it. I've added this task:");
            ui.printMessage(deadline.toString());
            ui.printMessage("Now you have " + list.getList().size() + " task(s) in the list.");

            storage.save(list);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
