public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static String parseFileAt(String desc) {
        int idx = desc.lastIndexOf("(at:");
        return desc.substring(idx+5, desc.length()-1);
    }

    public static String parseFileDesc(String desc) {
        int idx = desc.lastIndexOf("(at:");
        return desc.substring(0, idx-1);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}