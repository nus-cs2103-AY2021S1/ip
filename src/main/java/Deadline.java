import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")";
    }

    @Override
    public String write() {
        return "\ndeadline," + super.write() + "," + by;
    }

//    public static void main(String[] args) {
//        Task deadline = new Deadline("return book", "22/08/2020 2200");
//        System.out.println(deadline);
//    }
}