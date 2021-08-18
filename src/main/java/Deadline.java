import java.util.IllegalFormatFlagsException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {

    private static final Pattern format = Pattern.compile("deadline [^/]* /by .*");
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    private static boolean checkFormat(String formattedString) {
        Matcher matcher = format.matcher(formattedString);
        return matcher.matches();
    }

    //Format: "Deadline: [description] /by [on]
    public static Deadline create(String formattedString) throws IllegalArgumentException {
        if (!checkFormat(formattedString))
            throw new IllegalFormatFlagsException("Invalid Event format");

        int onIndex = formattedString.indexOf("/by");
        return new Deadline(formattedString.substring(9, onIndex),
                formattedString.substring(onIndex + 4));
    }

    @Override
    public String toString() {
        char statusIcon = this.isDone ? 'X' : ' ';
        return String.format("[%c] Deadline: %s(by: %s)", statusIcon, this.description, this.by);
    }
}
