package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;
import duke.exception.DukeStorageException;
import duke.task.Event;



public class EventCommand extends Command {
    public EventCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException, DukeStorageException {
        Pattern pattern = Pattern.compile(
                "event ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*) /at [0-9]{1,2}/[0-9]{1,2}/[0-9]{4,4} [0-9]{4,4}");
        if (!pattern.matcher(getInputCommand()).matches()) {
            throw new DukeCommandException("\u2639 OOPS!!! Wrong 'event' command format!");
        } else {
            String[] parseArray = getInputCommand().substring(6).split(" /at ");
            String[] dateSegments = parseArray[1].split("/");
            for (int i = 0; i < 2; i++) {
                if (dateSegments[i].length() == 1) {
                    dateSegments[i] = "0" + dateSegments[i];
                }
            }
            parseArray[1] = dateSegments[0] + "/" + dateSegments[1] + "/" + dateSegments[2];

            Event event;
            try {
                if (LocalDateTime.parse(parseArray[1], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                        .isBefore(LocalDateTime.now())) {
                    throw new DukeCommandException("That date has already passed!");
                }

                event = new Event(parseArray[0], LocalDateTime.parse(
                  parseArray[1], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
            } catch (DateTimeParseException e) {
                throw new DukeCommandException("Invalid date!");
            }
            list.getList().add(event);

            ui.printMessage("Got it. I've added this task:");
            ui.printMessage(event.toString());
            ui.printMessage("Now you have " + list.getList().size() + " task(s) in the list.");

            storage.save(list);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
