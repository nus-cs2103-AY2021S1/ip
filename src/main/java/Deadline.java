public class Deadline extends Task  {
    public String date;

    Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    public static Deadline create(String description) {
        String[] keywords = description.split(" /by ", 2);
        return new Deadline(keywords[0], keywords[1]);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
