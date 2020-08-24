public class Deadline extends Task {

    private String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due + ")";
    }

    public String encode() {
        return String.format("D|%s|%s|%s", super.completed ? "Y" : "N", this.due, super.description);
    }

    public static Deadline decode(String code) throws DukeException {
        if (code.charAt(0) == 'T') {
            String[] content = code.split("\\|", 4);
            if (content.length != 4) {
                throw new Error("Your data is corrupt.");
            }
            Deadline newDeadline = new Deadline(content[3], content[2]);
            if (content[1].equals("Y")) {
                newDeadline.setCompleted();
            }
            return newDeadline;
        } else {
            throw new DukeException("Unable to decode Deadline.");
        }
    }
}
