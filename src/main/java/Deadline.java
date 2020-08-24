public class Deadline extends Task{

    public Deadline(String description, String date) {
        super(description, date);
    }

    public String writeToFile() {
        return "D|" + super.writeToFile();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by :" + this.date + ")";
    }
}
