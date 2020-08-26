package duke.time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTime extends TimePoint{

    private LocalDate date;
    private LocalTime time;
    private boolean haveTime;

    public DateTime(LocalDate date) {
        this.date = date;
        this.haveTime = false;
    }

    public DateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
        this.haveTime = true;
    }

    @Override
    public String toString() {

        // String format for date
        String text = this.date.format(DateTimeFormatter
                .ofPattern("dd MMMM yyyy", new Locale("en")));

        // If time data available, add to text string
        if (haveTime) {
            text += " " + this.time.format(DateTimeFormatter
                    .ofPattern("h:mma", new Locale("en")));
        }

        return text;
    }

    @Override
    public String toSaveString() {

        // Save String format for data
        String saveString = this.date.format(DateTimeFormatter
                .ofPattern("dd/MM/yyyy", new Locale("en")));

        // If time data available, add to save string
        if (haveTime) {
            saveString += " " + this.time.format(DateTimeFormatter
                    .ofPattern("HH:mm", new Locale("en")));
        }

        return saveString;
    }

}
