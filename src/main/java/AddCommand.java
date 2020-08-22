import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private final String taskType;

    public AddCommand(String taskType) {
        this.taskType = taskType;
    }

    protected boolean isExit() {
        return false;
    }

    protected void execute(String input, TaskList taskList, Storage storage) throws DukeException {
        String information;
        switch (taskType) {
        case "todo": {
            try { // user did not input description of to-do task
                information = input.split("todo")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("\tThe description of a todo cannot be empty!");
            }
            if (information.isBlank()) {
                throw new DukeException("\tThe description of a todo cannot be empty!");
            }
            String description = information.substring(1);
            taskList.addToDo(description, storage);
            break;
        }
        case "deadline": {
            try { // user did not input description of deadline task
                information = input.split("deadline")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("\tPlease input an appropriate description!\n"
                        + "\tAn example would be:\n"
                        + "\tdeadline return book /by 2020-12-09 08:00");
            }
            if (information.isBlank()) {
                throw new DukeException("\tPlease input an appropriate description!\n"
                        + "\tAn example would be:\n"
                        + "\tdeadline return book /by 2020-12-09 08:00");
            }

            int end = information.indexOf("/");
            if (end == -1) { // user did not input correct command
                throw new DukeException("\tPlease input the appropriate command!\n"
                        + "\tAn example would be:\n"
                        + "\tdeadline return book /by 2020-12-09 08:00");
            }

            String description = information.substring(1, end - 1);
            String by;
            try { // user did not input date of deadline task
                by = information.substring(end + 4);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("\tPlease input the date!\n"
                        + "\tAn example would be:\n"
                        + "\tdeadline return book /by 2020-12-09 08:00");
            }
            if (by.isBlank()) {
                throw new DukeException("\tPlease input the date!\n"
                        + "\tAn example would be:\n"
                        + "\tdeadline return book /by 2020-12-09 08:00");
            }

            String formattedBy = by.replace(' ', 'T');
            LocalDateTime date;
            try {
                date = LocalDateTime.parse(formattedBy);
            } catch (DateTimeParseException e) {
                throw new DukeException("\tPlease input the correct date format!\n"
                        + "\tAn example would be:\n"
                        + "\tdeadline return book /by YYYY-MM-DD HH:mm");
            }
            taskList.addDeadline(description, date, storage);
            break;
        }
        case "event": {
            try { // user did not input description of event task
                information = input.split("event")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("\tPlease input an appropriate description!\n"
                        + "\tAn example would be:\n"
                        + "\tevent Christmas party /at 2020-12-25 17:00");
            }
            if (information.isBlank()) {
                throw new DukeException("\tPlease input an appropriate description!\n"
                        + "\tAn example would be:\n"
                        + "\tevent Christmas party /at 2020-12-25 17:00");
            }

            int end = information.indexOf("/");
            if (end == -1) { // user did not input correct command
                throw new DukeException("\tPlease input the appropriate command!\n"
                        + "\tAn example would be:\n"
                        + "\tevent Christmas party /at 2020-12-25 17:00");
            }

            String description = information.substring(1, end - 1);
            String at;
            try { // user did not input date of event task
                at = information.substring(end + 4);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("\tPlease input the date!\n"
                        + "\tAn example would be:\n"
                        + "\tevent Christmas party /at 2020-12-25 17:00");
            }
            if (at.isBlank()) {
                throw new DukeException("\tPlease input the date!\n"
                        + "\tAn example would be:\n"
                        + "\tevent Christmas party /at 2020-12-25 17:00");
            }

            String formattedAt = at.replace(' ', 'T');
            LocalDateTime date;
            try {
                date = LocalDateTime.parse(formattedAt);
            } catch (DateTimeParseException e) {
                throw new DukeException("\tPlease input the correct date format!\n"
                        + "\tAn example would be:\n"
                        + "\tevent Christmas party /at YYYY-MM-DD HH:mm");
            }
            taskList.addEvent(description, date, storage);
            break;
        }
        }
    }
}
