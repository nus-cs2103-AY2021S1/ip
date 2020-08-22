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
        String text = this.date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("en")));
        if (haveTime) {
            text += " " + this.time.format(DateTimeFormatter.ofPattern("h:mma", new Locale("en")));
        }
        return text;
    }

    @Override
    public String toSaveString() {
        String text = this.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("en")));
        if (haveTime) {
            text += " " + this.time.format(DateTimeFormatter.ofPattern("HH:mm", new Locale("en")));
        }
        return text;
    }

}
