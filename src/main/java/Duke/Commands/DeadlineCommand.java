package Duke.Commands;

import Duke.Errors.DeadlineException;
import Duke.Errors.DukeException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.Deadline;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * has the method if deadline is keyword deadline
 */
public class DeadlineCommand extends AddCommand {
    /**
     * assigns string to a value of string
     * @param string assigns string to this this.string
     */
    public DeadlineCommand(String string) {
        super(string);
    }

    private static Deadline provide(String name, String string) throws DeadlineException {
        Deadline e;
        try{
            LocalDate parsedDate = localDate(string);
            e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")));
        }catch (DateTimeException d) {
            try {
                LocalDateTime parsedDate = localDateTime(string);
                e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")));
            } catch (DateTimeException g) {
                try {
                    LocalTime parsedDate = localTime(string);
                    e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("HH:mm")));
                } catch (DateTimeException f) {
                    throw new DeadlineException(false, true);
                }
            } }
        return e;

    }

    /**
     * to add deadline into a task list in TaskList,
     * @param tasks to change the taskList if necessary
     * @param ui
     * @param storage to change the file in the if necessary
     * @throws DukeException whenever there is an error, where the time adn or date is absent or in wrong format, no
     * description
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeadlineException{
        if (string.length() == 8 || string.length() == 9) {
            throw new DeadlineException(true, false);
        }
        String s = "";
        int index = -1;
        boolean time = false;
        for (int i = 8; i < string.length(); i++) {
            if (string.charAt(i) == '/') {
                index = i;
                time = true;
                break;
            }
            s = s + string.charAt(i);
        }
        if (!time) {
            throw new DeadlineException(false, false);
        }
        Deadline d = provide(s.substring(1, s.length() - 1), string.substring(index + 4));
        try {
            update(storage, d, tasks);
        }catch (IOException i){

        }

    }
}
