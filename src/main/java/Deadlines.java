import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    private String deadline;
    private LocalDate date;

    public Deadlines(String description) {
        super(description);
        this.type = "Deadlines";
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public void setDateTime() {
        int index = this.deadline.indexOf(" ");
        String dateTemp = this.deadline.substring(0, index);
        /*
        int dateIndex = dateTemp.indexOf('/');
        String dateOnly = dateTemp.substring(0, dateIndex);
        if (dateOnly.length() < 2) {
            char adder = '0';
            dateOnly = adder + dateOnly;
        }
        int monIndex = dateTemp.indexOf('/', dateTemp.indexOf('/') + 1);
        String monOnly = dateTemp.substring(dateTemp.indexOf('/') + 1, monIndex);
        if (monOnly.length() < 2) {
            char adder = '0';
            monOnly = adder + monOnly;
        }
        String yearOnly = dateTemp.substring(monIndex + 1);
        dateTemp = dateOnly + "/" + monOnly + "/" + yearOnly;
        System.out.println(dateTemp);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateTemp, formatter);
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate date = LocalDate.parse(dateTemp);
        this.date = date;
        System.out.println(date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    public String toString() {
        return "  [D][" + this.getStatusIcon() + "] "
                + this.getDescription().substring(0,description.indexOf("/")) + "(by: " + this.getDeadline() + ")";
    }
}
