public class Date {
    private final int day;
    private final int month;
    private final int year;
    private static final String[] arrMonths = {"January", "February", "March", "April", "May", "June", "July", "August",
                                               "September", "October", "November", "December"};


    public Date(String dateString) {
        String[] tokens = dateString.split("/");
        this.day = Integer.parseInt(tokens[0]);
        this.month = Integer.parseInt(tokens[1]);
        this.year = Integer.parseInt(tokens[2]);
    }

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
