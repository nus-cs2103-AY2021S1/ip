import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.Month;
import java.util.HashMap;
import static java.time.Month.*;

public class ScheduleCommand extends Command {

    private String command;

    HashMap<String, Month> monthsHashMap = new HashMap<>() {
        {
            put("January", JANUARY);
            put("Febuary", FEBRUARY);
            put("March", MARCH);
            put("April", APRIL);
            put("May", MAY);
            put("June", JUNE);
            put("July", JULY);
            put("August", AUGUST);
            put("September", SEPTEMBER);
            put("October", OCTOBER);
            put("November", NOVEMBER);
            put("December", DECEMBER);
        }
    };

    public ScheduleCommand(String command) {
        this.command = command;
    }

    @Override
    String execute(TaskList tasks, UI dukeUI) throws DukeException {
        String dukeResponse;
        try {
            String[] taskDetails = this.command.split(" ");
            LocalDate dateToFind = LocalDate.parse(taskDetails[1]);
            dukeResponse = dukeUI.findScheduleOnDate(tasks, dateToFind);
            return dukeResponse;
        } catch (IndexOutOfBoundsException e) {
            throw new WrongDateFormatException();
        } catch (DateTimeParseException e) {
            String[] taskDetails = this.command.split(" ");
            Month targetMonth = monthsHashMap.get(taskDetails[1]);
            dukeResponse = dukeUI.findScheduleForMonth(tasks, targetMonth);
            return dukeResponse;
        }
    }
}
