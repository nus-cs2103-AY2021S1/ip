public class Deadline extends Task{
    String deadline;
    String preposition;
    Deadline(String title, String preposition, String deadline) {
        super(title);
        this.preposition = preposition;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(" + preposition + ": " + deadline + ")";
    }
}
