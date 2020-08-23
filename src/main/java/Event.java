public class Event extends Task {
    private static String format(String s) {
        String[] split = s.split("/");
        return split[0] +  "(" + split[1].split(" ")[0] + ":" + split[1].substring(split[1].indexOf(' ')) + ")";
    }

    public Event(String contents) {
        super(Event.format(contents));
        super.inputString = this.getClass().getSimpleName().toLowerCase() + " " + contents;
    }

    public String toString() {
        return "[E]" + super.toString();
    }
}
