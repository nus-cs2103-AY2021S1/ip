import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Deadline extends Task {
    private Date dueDate;

    Deadline(String name, String dueDate) throws DukeException {
        super(name);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        try {        
            this.dueDate = formatter.parse(dueDate);
        } catch (ParseException e) {
            throw new DukeException("Date format is invalid!");
        }
    }

    @Override
    public String saveText() {
        String completeStatus = super.isCompleted() ? "1" : "0";
;        return "D," + completeStatus + "," + super.getName() + "," + dueDate;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("[D]%s (due: %s)", super.toString(), dateFormat.format(dueDate));
    }
}
