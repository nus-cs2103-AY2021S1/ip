public class Deadline extends Task {
    private String dateAndTime;

    public Deadline(String description) {
        super(description.split("/by ")[0]);
        this.dateAndTime = description.split("/by ")[1];

    }

    public String dateAndTimeBracket() {
        return String.format("(by: %s)", this.dateAndTime);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s %s",
                this.getStatusIcon(), this.description, this.dateAndTimeBracket());
    }
}
