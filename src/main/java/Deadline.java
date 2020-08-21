public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = Duke.strToDate(by);
    }

    public static String parseFileBy(String desc) {
        int idx = desc.lastIndexOf("(by:");
        return desc.substring(idx+5, desc.length()-1);
    }

    public static String parseFileDesc(String desc) {
        int idx = desc.lastIndexOf("(by:");
        return desc.substring(0, idx-1);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}