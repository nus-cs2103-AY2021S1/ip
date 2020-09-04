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
        try {
            String[] taskDetails = this.command.split(" ");
            String scheduleDate = getScheduleFor(taskDetails[1]);
            String dukeResponse;
            if (inputDateIsMonth(scheduleDate)) {
                Month targetMonth = monthsHashMap.get(scheduleDate);
                dukeResponse = dukeUI.findScheduleForMonth(tasks.getTaskList(), targetMonth);
            } else {
                LocalDate dateToFind = LocalDate.parse(scheduleDate);
                dukeResponse = dukeUI.findScheduleOnDate(tasks.getTaskList(), dateToFind);
            }
            return dukeResponse;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("error");
        }
    }

    private String getScheduleFor(String date) {
        try {
            LocalDate taskDate = LocalDate.parse(date);
            return date;
        } catch (DateTimeParseException e) {
            return "month: " + date;
        }
    }

    private boolean inputDateIsMonth(String date) {
        return date.split(" ")[0].equals("month:");
    }
}
