public class Event extends Task {

    private String on;

    public Event(String description, String on) {
        super(description);
        this.on = on;
    }

    private static void checkFormat(String formattedString) throws DukeException {
        int onIndex = formattedString.indexOf("/on ");
        if (onIndex == -1)
            onIndex = formattedString.length();

        String keyword = formattedString.split(" ", 2)[0];

        if (!keyword.startsWith("event"))
            throw new DukeException("I can't seem to find the event keyword");
        else if (formattedString.length() <= 6 || formattedString.substring(6, onIndex).isEmpty())
            throw new DukeException("the description of event cannot be empty");
        else if (onIndex == formattedString.length() || formattedString.length() < onIndex + 5)
            throw new DukeException("the [/on] time of event cannot be empty");
    }

    //Format: "Event: [description] /by [on]
    public static Event create(String formattedString) throws DukeException {
        checkFormat(formattedString);

        int onIndex = formattedString.indexOf("/on ");
        return new Event(formattedString.substring(6, onIndex),
                formattedString.substring(onIndex + 4));
    }

    @Override
    public String toString() {
        char statusIcon = this.isDone ? 'X' : ' ';
        return String.format("[%c] Event: %s(on: %s)", statusIcon, this.description, this.on);
    }
}
