public class DeadlineInvalidDate extends InvalidDateException {
    public DeadlineInvalidDate() {
        super("OOPS. You need to put \"/by [date]\" after inputting a Deadline.");
    }
}
