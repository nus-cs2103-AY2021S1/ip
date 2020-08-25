public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String encode() {
        return String.format("D|%s|%s|%s", super.isDone ? "Y" : "N", this.by, super.description);
    }

    public static Deadline decode(String code) throws DukeException {
        if (code.charAt(0) == 'D') {
            String[] content = code.split("\\|", 4);
            if (content.length != 4) {
                throw new Error("data string is not equal to 4");
            }
            Deadline newDeadline = new Deadline(content[3], content[2]);
            if (content[1].equals("Y")) {
                newDeadline.markAsDone();
            }
            return newDeadline;
        } else {
            throw new DukeException("Unable to decode Deadline");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}