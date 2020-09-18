/**
 * Formats timing for output.
 */
public class Timing {
    protected String timing;
    private final int minute;
    private final int hour;
    private final boolean isPM;

    /**
     * Instantiates Timing object.
     * @param timing Timing read from input text file.
     */
    public Timing(String timing) throws DukeException {
        assert !timing.equals("") : "Date cannot be empty.";
        this.timing = timing;
        int timeInt = Integer.parseInt(timing);
        this.minute = timeInt % 100;
        this.hour = timeInt / 100;

        if (minute < 0 || minute >= 60) {
            throw new DukeException("Oops. Minute field should be between 0 and 59!");
        }

        if (hour < 0 || hour >= 24) {
            throw new DukeException("Oops. Hour field should be between 0 and 23!");
        }

        if (this.hour < 12) {
            isPM = false;
        } else {
            isPM = true;
        }
    }

    /**
     * Overrides toString method so as to customize output string format.
     * @return String in our desired format.
     */
    @Override
    public String toString() {
        String formattedTiming;
        String meridiem;

        if (hour == 0 || hour == 12) {
            formattedTiming = "12";
        } else {
            formattedTiming = hour % 12 + "";
        }

        if (minute != 0) {
            formattedTiming = formattedTiming + ":" + String.format("%02d", minute);
        }

        if (isPM) {
            meridiem = "pm";
        } else {
            meridiem = "am";
        }
        return formattedTiming + meridiem;
    }
}
