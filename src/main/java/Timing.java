public class Timing {
    private final int minute;
    private final int hour;
    private final boolean meridiemCheck;

    public Timing(String timeString) {
        int timeInt = Integer.parseInt(timeString);
        this.minute = timeInt % 100;
        this.hour = timeInt / 100;

        if (this.hour < 12) {
            meridiemCheck = false;
        } else {
            meridiemCheck = true;
        }
    }

    @Override
    public String toString() {
        String formattedTiming;
        String AMPM;

        if (hour == 0 || hour == 12) {
            formattedTiming = "12";
        } else {
            formattedTiming = hour % 12 + "";
        }

        if (minute != 0) {
            formattedTiming = formattedTiming + ":" + String.format("%02d",minute);
        }

        if (meridiemCheck) {
            AMPM = "pm";
        } else {
            AMPM = "am";
        }
        return formattedTiming + AMPM;
    }
}
