public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String write() {
        return "\ndeadline," + super.write() + "," + by;
    }

//    public static void main(String[] args) {
//        Task deadline = new Deadline("return book", "monday");
//        System.out.println(deadline);
//    }
}