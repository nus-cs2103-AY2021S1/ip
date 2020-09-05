/**
 * Formats date for output.
 */
public class Date {
    private static final String[] ARRAY_MONTHS = {"January", "February", "March", "April", "May", "June",
                                                  "July", "August", "September", "October", "November", "December"};
    protected String date;
    private final int day;
    private final int month;
    private final int year;

    /**
     * Instantiates Date object.
     * @param date Date from input file.
     */
    public Date(String date) {
        assert !date.equals(""): "Date should not be empty!";
        this.date = date;
        String[] dateElements = date.split("/");
        this.day = Integer.parseInt(dateElements[0]);
        this.month = Integer.parseInt(dateElements[1]);
        this.year = Integer.parseInt(dateElements[2]);
    }

    /**
     * Overrides toString method so as to customize output string format.
     * @return String in our desired format.
     */
    @Override
    public String toString() {
        String formattedDate;
        if (day % 10 == 1 && day != 11) {
            formattedDate = day + "st";
        } else if ((day % 10 == 2 && day != 12)) {
            formattedDate = day + "nd";
        } else if (day % 10 == 3 && day != 13) {
            formattedDate = day + "rd";
        } else {
            formattedDate = day + "th";
        }
        formattedDate = formattedDate + " of " + ARRAY_MONTHS[month - 1] + " " + year;
        return formattedDate;
    }
}
