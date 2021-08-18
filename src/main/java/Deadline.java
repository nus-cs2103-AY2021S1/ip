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


    private static void checkFormat(String formattedString) throws DukeException {
        int byIndex = formattedString.indexOf("/by ");
        if (byIndex == -1)
            byIndex = formattedString.length();

        String keyword = formattedString.split(" ", 1)[0];

        if (!keyword.startsWith("deadline"))
            throw new DukeException("I can't seem to find the deadline keyword");
        else if (formattedString.length() <= 9 || formattedString.substring(9, byIndex).isEmpty())
            throw new DukeException("the description of deadline cannot be empty");
        else if (byIndex == formattedString.length() || formattedString.length() < byIndex + 5)
            throw new DukeException("the [/by] time of deadline cannot be empty");
    }

    //Format: "Deadline: [description] /by [on]
    public static Deadline create(String formattedString) throws DukeException {
        checkFormat(formattedString);

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
