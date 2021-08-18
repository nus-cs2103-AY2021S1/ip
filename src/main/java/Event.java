import java.util.IllegalFormatException;
import java.util.IllegalFormatFlagsException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {

    private static final Pattern format = Pattern.compile("event [^/]* /on .*");
    private String on;

    public Event(String description, String on) {
        super(description);
        this.on = on;
    }

    private static boolean checkFormat(String formattedString) {
        Matcher matcher = format.matcher(formattedString);
        return matcher.matches();
    }

    //Format: "Event: [description] /by [on]
    public static Event create(String formattedString) throws IllegalArgumentException {
        if (!checkFormat(formattedString))
            throw new IllegalFormatFlagsException("Invalid Event format");

        int onIndex = formattedString.indexOf("/on");
        return new Event(formattedString.substring(6, onIndex),
                formattedString.substring(onIndex + 4));
    }

    @Override
    public String toString() {
        char statusIcon = this.isDone ? 'X' : ' ';
        return String.format("[%c] Event: %s(on: %s)", statusIcon, this.description, this.on);
    }
}
