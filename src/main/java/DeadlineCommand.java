import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DeadlineCommand extends Command {

    public DeadlineCommand(Category category, String description) throws IllegalArgumentException {
        super(category, description);
        if(description == null) {
            throw new IllegalArgumentException("-------------------------------------------\n" +
                                                "☹ OOPS!!! The description of a deadline cannot be empty. Try again!\n"
                                                +"-------------------------------------------");
        } else {
           System.out.println("-------------------------------------------\n" +
                    "Got it. I've added this task:");
        }
    }
}
