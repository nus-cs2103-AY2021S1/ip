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

public class DeadlineCommand extends AddCommand {
    public DeadlineCommand(String string) {
        super(string);
    }
    public static Deadline provide(String name, String string) throws DukeException {
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
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
