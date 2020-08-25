import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task{
    protected String by;

    //Constructors
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public static Deadline create(String input) throws DeadlineException {
        //to check the completion of the command
        //create task only when the command is complete
        //else throw exception
        String[] arr = input.split("\\s");
        int index = 0;
        for(int i = 1; i < arr.length; i++) {
            if (arr[i].equals("/by")) {
                index = i;
                break;
            }
        }
        if (arr.length == 1 || index == 1) { //no description
            throw new DeadlineException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (index == arr.length - 1 || index == 0) { //no time
            throw new DeadlineException(" ☹ OOPS!!! The time of a deadline cannot be empty.");
        }
        String description = "";
        String time = "";
        for(int i = 1; i < index; i++) {
            description = description + arr[i] + " ";
        }
        for(int i = index + 1; i < arr.length; i++) {
            time = time + arr[i] + " ";
        }
        
        Date date;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy hhmm");
            date = formatter.parse(time.trim());
        } catch (ParseException e) {
            throw new DeadlineException(" ☹ OOPS!!! The time of a deadline must be in the format of dd/M/yyyy hhmm.");
        }
        
        return new Deadline(description.trim(), new SimpleDateFormat("MMM dd yyyy HH:mm").format(date));
    }

    @Override
    public Deadline markAsDone() {
        return new Deadline(this.description, true, this.by);
    }

    @Override
    public String stringify() {
        String number = isDone ? "1" : "0";
        return "D | " + number + " | " + super.description + " | " + this.by;
    }
    
    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + by + ")";
    }
}
