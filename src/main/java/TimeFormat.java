public class TimeFormat {
    private final String date;
    private final String time;

    public TimeFormat(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public boolean checkIfIsTimePeriod() {
        return time.contains("-");
    }

    @Override
    public String toString() {
        return date + " " + time;
    }
}

