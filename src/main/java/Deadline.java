import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDateTime deadline;

    public Deadline(String name, boolean isDone, String end){
        super(name, isDone);
        /*String rest = end.substring(end.indexOf("/") + 1);
        String rest1 = rest.substring(rest.indexOf("/" ) + 6);

        this.deadline = LocalDate.of(Integer.parseInt(rest.substring(3,7)) ,
                Integer.parseInt(rest.substring(0, rest.indexOf("/"))) ,
                Integer.parseInt(end.substring(0,1)) ).atTime(
                Integer.parseInt(rest1.substring(0,3),
                        Integer.parseInt(rest1.substring(3));
        );*/
        this.deadline = LocalDate.parse(end.substring(0,10)).atTime(
                Integer.parseInt(end.substring(11,13)),
                Integer.parseInt(end.substring(13))
        );
    }

    public Deadline(String name, boolean isDone, LocalDateTime deadline) {
        super(name,isDone);
        this.deadline = deadline;
    }

    @Override
    public Task setToTrue(){
        return new Deadline(this.name, true, this.deadline);
    }

    @Override
    public String getType(){
        return "D";
    }

    @Override
    public String getEnd() {
        return this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString(){
        return isDone
                ? "[D][✓] " + this.getName() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")"
                : "[D][✗] " + this.getName() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
