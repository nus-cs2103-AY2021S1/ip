import java.time.LocalDate;
import java.time.LocalTime;

public class DateAndTime {

    private final LocalDate date;
    private final LocalTime time;

    public DateAndTime(){
        this.date = null;
        this.time = null;
    }

    public DateAndTime(String dateAndTime){

        String date = dateAndTime.substring(0, "2020-02-02".length());
        String time = dateAndTime.substring("2020-02-02".length() + 1);

        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);

    }

    @Override
    public String toString(){
        return date.getYear() + " " +
                date.getMonth() + " " +
                date.getDayOfMonth() + " "  +
                date.getDayOfWeek() + " " +
                time.toString() + "hr";
    }


}
