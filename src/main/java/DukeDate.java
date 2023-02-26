import java.time.LocalDate;

public class DukeDate {
    private LocalDate localDate;

    public DukeDate(String datetimeString) {
        System.out.println(datetimeString);
        String[] datetimeArray = datetimeString.split(" ");
        System.out.println(datetimeArray[1].trim());
        this.localDate = LocalDate.parse(datetimeArray[1].trim());
    }

    @Override
    public String toString() {
        String day = String.valueOf(localDate.getDayOfMonth());
        String monthString = String.valueOf(localDate.getMonth());
        String year = String.valueOf(localDate.getYear());
        return day + " " + monthString + " " + year;
    }
}
