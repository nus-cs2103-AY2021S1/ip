import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task{

    String at;

    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    public boolean isDate() {
        try {
            new SimpleDateFormat ("yyyy-MM-dd").parse(at);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public String convertDate() {
        String s = "";
        try {
            if (isDate()) {
                Date d = new SimpleDateFormat("yyyy-MM-dd").parse(at);
                s = new SimpleDateFormat("MMM dd yyyy").format(d);
            } else {
                s = at;
            }
        } catch (ParseException e) {
            System.out.println("Please specify date in the form yyyy-MM-dd");
        }
        return s;
    }


    @Override
    public String toString() {
        if (done) {
            return String.format ("[E][DONE] %s(at: %s)", this.task, convertDate());
        } else {
            return String.format ("[E][NOT DONE] %s(at: %s)", this.task, convertDate());
        }
    }
}