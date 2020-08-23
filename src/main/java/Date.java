public class Date {
    private final int day, month, year;
    protected String date;
    private static final String[] arrMonths = {"January", "February", "March", "April", "May", "June", "July", "August",
                                               "September", "October", "November", "December"};

    /**
     * Instantiates Date object.
     * @param date Date from input file.
     */
    public Date(String date) {
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
        formattedDate = formattedDate + " of " + arrMonths[month - 1] + " " + year;
        return formattedDate;
    }
}
