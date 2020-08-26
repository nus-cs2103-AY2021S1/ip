import java.time.LocalDate;

// partial solution copied from iP
public class Deadline extends Task {

    protected LocalDate date;
    protected Integer time;

    public Deadline(String description, String by, boolean isDone) {
        super(description.trim(), isDone);
        String[] timing = by.trim().split("/");
        String[] timingDay = timing[2].split(" ");
        this.date = LocalDate.of(Integer.parseInt(timingDay[0]), Integer.parseInt(timing[1]),
                Integer.parseInt(timing[0]));
        if (timingDay.length == 2 && Integer.parseInt(timing[1]) >= 0 && Integer.parseInt(timingDay[1]) < 2359) {
            this.time = Integer.parseInt(timingDay[1]);
        }
    }

    @Override
    public String toString() {
        return "  [D]" + super.toString() + " (by: " + date.getMonth().toString().toLowerCase() + " " +
                date.getDayOfMonth() + " " + date.getYear() + (time == null ? ")" : " " + time + "hrs)");
    }
}
