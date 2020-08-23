public class Timing {
    private final int MINUTE, HOUR;
    private final boolean isPM;
    protected String timing;

    /**
     * Instantiates Timing object.
     * @param timing Timing read from input text file.
     */
    public Timing(String timing) {
        this.timing = timing;
        int timeInt = Integer.parseInt(timing);
        this.MINUTE = timeInt % 100;
        this.HOUR = timeInt / 100;

        if (this.HOUR < 12) {
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
        String AMPM;

        if (HOUR == 0 || HOUR == 12) {
            formattedTiming = "12";
        } else {
            formattedTiming = HOUR % 12 + "";
        }

        if (MINUTE != 0) {
            formattedTiming = formattedTiming + ":" + String.format("%02d", MINUTE);
        }

        if (isPM) {
            AMPM = "pm";
        } else {
            AMPM = "am";
        }
        return formattedTiming + AMPM;
    }
}
