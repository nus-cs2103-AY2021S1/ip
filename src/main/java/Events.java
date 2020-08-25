import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    private String start;
    private LocalDate date;

    /**
     * Class constructor specifying the Task start date.
     * @param description
     */
    public Events(String description) {
        super(description);
        this.type = "Events";
    }

    /**
     * Sets the start time to the specified string.
     * @param start
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Returns the start time of the Event.
     * @return the start time in String.
     */
    public String getStart() {
        return this.start;
    }

    /**
     * Sets the LocalDate of the Event.
     */
    public void setDateTime() {
        int index = this.start.indexOf(" ");
        String dateTemp = this.start.substring(0, index);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate date = LocalDate.parse(dateTemp);
        this.date = date;
        /*
        int index = this.start.indexOf(" ");
        String dateTemp = this.start.substring(0, index);
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
        int yearIndex = dateTemp.indexOf('/', monIndex + 1 );
        String yearOnly = dateTemp.substring(monIndex + 1,yearIndex);
        dateTemp = dateOnly + "/" + monOnly + "/" + yearOnly;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateTemp, formatter);
        this.date = date;
        System.out.println(date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
         */
    }

    public String toString() {
        return "  [" + this.getType() + "][" + this.getStatusIcon() + "] "
                + this.getDescription().substring(0,description.indexOf("/")) + "(at: " + this.getStart() + ")";
    }
}
