package duck;

public class Deadline extends  Task {

    private String date;

    public Deadline(String desc, String date) {
        super(desc);
        this.date = date;
    }

    @Override
    public String getStatus() {
        return Colour.Magenta("[D]") + super.getStatus() + " (by: " + this.date + ")";
    }
}
