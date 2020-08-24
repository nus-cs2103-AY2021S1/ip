public class DeadlineInvalidDate extends InvalidDateTimeException {
    public DeadlineInvalidDate() {
        super("OOPS. You need to put \"/by [DateTimeFormat]\"\n" +
                "after a Deadline.");
    }
}
