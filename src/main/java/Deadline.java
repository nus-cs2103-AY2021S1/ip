public class Deadline extends Task{
    String date;

    Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public String toString() {
        return "[D][" + getStatusIcon() + "] " + this.description + "(by: " + this.date + ")";
    }
}


