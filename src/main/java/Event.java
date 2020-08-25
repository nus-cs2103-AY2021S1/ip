import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public static Event create(String input) throws EventException {
        //to check the completion of the command
        //create task only when the command is complete
        //else throw exception
        String[] arr = input.split("\\s");
        int index = 0;
        for(int i = 1; i < arr.length; i++) {
            if (arr[i].equals("/at")) {
                index = i;
                break;
            }
        }
        if (arr.length == 1 || index == 1) { // no description
            throw new EventException(" ☹ OOPS!!! The description of a event cannot be empty.");
        } else if (index == arr.length - 1 || index == 0) { //no time
            throw new EventException(" ☹ OOPS!!! The time of a event cannot be empty.");
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
            throw new EventException(" ☹ OOPS!!! The time of a deadline must be in the format of dd/M/yyyy hhmm.");
        }

        return new Event(description.trim(), new SimpleDateFormat("MMM dd yyyy HH:mm").format(date));
    }

    @Override
    public Event markAsDone() {
        return new Event(this.description, true, this.at);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (at: " + at + ")";
    }
}
