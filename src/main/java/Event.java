import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Event extends Task {
    private Date start;
    private Date end;

    Event(String name, String start, String end) throws DukeException {
        super(name);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm");
        try {        
            this.start = formatter.parse(start);
            this.end = formatter.parse(end);
        } catch (ParseException e) {
            throw new DukeException("Date format is invalid!");
        }
    }

    @Override
    public String saveText() {
        String completeStatus = super.isCompleted() ? "1" : "0";
;        return "E," + completeStatus + "," + super.getName() + "," + start + "," + end;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return String.format("[E]%s (start: %s, end: %s)", super.toString(), dateFormat.format(start), dateFormat.format(end));
    }
}
