import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AddDeadline extends AddCommand {
    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy kkmm", Locale.ENGLISH);

    public AddDeadline(String[] words) {
        super(words);
    }

    @Override
    public void execute(TaskList ls, Ui ui) throws DateTimeException {
        String[] stuff = words[1].split(" /by ");
        LocalDateTime day = LocalDateTime.parse(stuff[1], FORMATTER);
        Deadline newDL = new Deadline(stuff[0], day, false);
        ls.add(newDL);
        String thing = "Alright then, add more things to your ever-growing list of tasks:\n"
                + newDL.getStatus().replaceAll("(?m)^", "\t")
                + "\nNow you have " + ls.size() + " tasks in the list.";
        ui.printResult(thing);
    }
}
