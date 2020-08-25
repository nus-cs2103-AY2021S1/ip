
public class Deadline extends Task {
    protected String date;

    public Deadline(String task_info,String date) {
        super(task_info);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + " (%s)", date);
    }

}

