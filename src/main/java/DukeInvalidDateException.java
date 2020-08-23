public class DukeInvalidDateException extends DukeException {

    public DukeInvalidDateException(String command) {
        super("Please enter a valid date and time for " + command + "!"
                + "\nDate and time format: DD/MM/YYYY [24-hour time]"
                + "\neg. 26/09/2020 1800 (26 Sep 2020, 06:00pm)");
    }
}
