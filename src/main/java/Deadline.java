public class Deadline extends Task {
    protected String date;

    protected Deadline(String name, boolean isCompleted, String date) {
        super(name, isCompleted);
        this.date = date;
    }

    public static Deadline newDeadline(String name, String date){
        return new Deadline(name, false, date);
    }

    public static Deadline existingDeadline(String name, boolean isCompleted, String date){
        return new Deadline(name, isCompleted, date);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    public String toSaveString(){
        return "D" + " | " + super.toSaveString() + " | " + this.date + "\n";
    }
}
