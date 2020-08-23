public class Date {
    private final int DAY, MONTH, YEAR;
    protected String date;
    private static final String[] ARRAY_MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August",
                                                  "September", "October", "November", "December"};

    public Date(String date) {
        this.date = date;
        String[] dateElements = date.split("/");
        this.DAY = Integer.parseInt(dateElements[0]);
        this.MONTH = Integer.parseInt(dateElements[1]);
        this.YEAR = Integer.parseInt(dateElements[2]);
    }

    @Override
    public String toString() {
        String formattedDate;
        if (DAY % 10 == 1 && DAY != 11) {
            formattedDate = DAY + "st";
        } else if ((DAY % 10 == 2 && DAY != 12)) {
            formattedDate = DAY + "nd";
        } else if (DAY % 10 == 3 && DAY != 13) {
            formattedDate = DAY + "rd";
        } else {
            formattedDate = DAY + "th";
        }
        formattedDate = formattedDate + " of " + ARRAY_MONTHS[MONTH - 1] + " " + YEAR;
        return formattedDate;
    }
}
