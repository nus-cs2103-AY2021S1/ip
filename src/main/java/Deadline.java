import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }
    
    public boolean isDate() {
        SimpleDateFormat date = new SimpleDateFormat ("yyyy-mm-dd");
        date.setLenient(false);
        try {
            date.parse(by.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public String convertDate() {
        String s = "";
        try {
            if (isDate()) {
                Date d = new SimpleDateFormat("yyyy-MM-dd").parse(by);
                s = new SimpleDateFormat("MMM dd yyyy").format(d);
            } else {
                s = by;
            }
        } catch (ParseException e) {
            System.out.println("Please specify date in the form yyyy-MM-dd");
        }
        return s;
    }

    @Override
    public String toString() {
        if (done) {
            return String.format ("[D][DONE] %s(by: %s)", this.task, convertDate());
        } else {
            return String.format ("[D][NOT DONE] %s(by: %s)", this.task, convertDate());
        }
    }
}