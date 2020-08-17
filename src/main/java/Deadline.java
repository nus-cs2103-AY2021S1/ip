public class Deadline extends Task {
    private String date;


    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return "["+ getTaskType() +"]" +"["+ getStatusIcon()+ "]" + description + " " + "(by: " + date + ")";
    }
}
