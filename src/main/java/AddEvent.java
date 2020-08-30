import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AddEvent extends AddCommand {
    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy kkmm", Locale.ENGLISH);

    public AddEvent(String[] words) {
        super(words);
    }

    @Override
    public void execute(TaskList ls, Ui ui) {
        String[] stuff = words[1].split(" /by ");
        LocalDateTime day = LocalDateTime.parse(stuff[1], FORMATTER);
        Event newE = new Event(stuff[0], day, false);
        ls.add(newE);
        String thing = "Alright then, add more things to your ever-growing list of tasks:\n"
                + newE.getStatus().replaceAll("(?m)^", "\t")
                + "\nNow you have " + ls.size() + " tasks in the list.";
        ui.printResult(thing);
    }
}
