public class Deadline extends TimedTask {

    public Deadline(String description, String by) {
        super(description, by);
        this.connecting = " (by: ";
        this.firstLetter = "[D]";
    }
}
