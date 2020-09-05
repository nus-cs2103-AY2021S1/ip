import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.Month;
import java.util.HashMap;
import java.util.Optional;
import static java.time.Month.*;

/**
 * Class to handle schedule commands entered by user.
 * @author vanGoghhh
 */
public class ScheduleCommand extends Command {

    private String command;

    /**
     * HashMap containing storing months in String and Month.
     */
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

    /**
     * Constructor for the scheudle command.
     * @param command Complete line of command entered by the user.
     */
    public ScheduleCommand(String command) {
        this.command = command;
    }

    /**
     * Method to execute entirely when a schedule command is entered by the user.
     * @param tasks TaskList containing all the tasks.
     * @param dukeUI UI to print string responses by the bot.
     * @return the String response for this command.
     * @throws DukeException when an invalid user input is entered.
     */
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
            dukeResponse =  Optional.ofNullable(monthsHashMap.get(taskDetails[1]))
                    .map(month -> dukeUI.findScheduleForMonth(tasks, month)).orElse("Invalid Date!");
            return dukeResponse;
        }
    }
}
