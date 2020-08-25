package Duke.Commands;

import Duke.Errors.DukeException;
import Duke.Errors.EventException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;

import Duke.Tasks.event;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * handles cases when event is keyword
 */
public class EventCommand extends AddCommand{
    /**
     * assigns string to a value of string
     * @param string assigns string to this this.string
     */
    public EventCommand(String string) {
        super(string);
    }
    private static event provide(String name, String string, String end) throws DukeException {
        event e;
        try{
            LocalDate parsedDate = localDate(string);
            LocalDate endDate = localDate(end);
            if(parsedDate.isAfter(endDate)){
                throw new EventException(false, false, true, false);
            }
            e = new event(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")),
                    endDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")));
        }catch (EventException event){
            throw new EventException(false, false, true, false);
        }catch (DateTimeException d) {
            try {
                LocalDateTime parsedDate = localDateTime(string);
                LocalDateTime endDate = localDateTime(end);
                if(parsedDate.isAfter(endDate)){
                    throw new EventException(false, false, true, false);
                }
                e = new event(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")),
                        endDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")));
            } catch (EventException event){
                throw new EventException(false, false, true, false);
            }
            catch (DateTimeException g) {
                try {
                    LocalTime parsedDate = localTime(string);
                    LocalTime endDate = localTime(end);
                    if(parsedDate.isAfter(endDate)){
                        throw new EventException(false, false, true, false);
                    }
                    e = new event(name, parsedDate.format(DateTimeFormatter.ofPattern("HH:mm")),
                            endDate.format(DateTimeFormatter.ofPattern("HH:mm")));
                }catch (EventException y){
                    throw new EventException(false, false, true, false);
                }catch (DateTimeException z) {
                    throw new EventException(false, false, false, true);
                }
            } }
        return e;
    }

    /**
     * is used to add event task or handle exceptions
     * @param tasks to change the taskList if necessary when no error
     * @param ui
     * @param storage to change the file in the if necessary when no erro
     * @throws DukeException if there no description after event no time or time is wrong format
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (string.length() == 5 || string.length() == 6) {
            throw new EventException(true, false, false, false);
        }
        String s = "";
        int index = -1;
        int end = -1;
        boolean duration = false;
        boolean time = false;
        String start = "";
        for (int i = 5; i < string.length(); i++) {
            if (string.charAt(i) == '/') {
                index = i;
                time = true;
                break;
            }
            s = s + string.charAt(i);
        }
        for (int i = index + 1; i < string.length(); i++) {
            if (string.charAt(i) == '-' && i != string.length() - 1) {
                end = i;
                duration = true;
                break;
            }
            start = start + string.charAt(i);
        }
        if (!time) {
            throw new EventException(false, false, false, false);
        }
        if (!duration) {
            throw new EventException(false, true, false, false);
        }
        event d = provide(s.substring(1, s.length() - 1), string.substring(index + 4, end), string.substring(end + 1));
        try {
            update(storage, d, tasks);

        } catch (IOException i) {

        }
    }
}
