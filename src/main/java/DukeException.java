public class DukeException {
    private static final String divider =
            "\t----------------------------------------------------\n";

    protected static void toDoInvalidDescription() {
        System.out.print(divider
                + "\tThe description of a todo cannot be empty!\n"
                + divider);
    }

    protected static void deadlineInvalidDescription() {
        System.out.print(divider
                + "\tPlease input an appropriate description!\n"
                + "\tAn example would be:\n"
                + "\tdeadline return book /by Friday\n"
                + divider);
    }

    protected static void deadlineInvalidCommand() {
        System.out.print(divider
                + "\tPlease input the appropriate command!\n"
                + "\tAn example would be:\n"
                + "\tdeadline return book /by Friday\n"
                + divider);
    }

    protected static void deadlineInvalidDate() {
        System.out.print(divider
                + "\tPlease input the date!\n"
                + "\tAn example would be:\n"
                + "\tdeadline return book /by Friday\n"
                + divider);
    }

    protected static void eventInvalidDescription() {
        System.out.print(divider
                + "\tPlease input an appropriate description!\n"
                + "\tAn example would be:\n"
                + "\tevent project meeting /at Aug 6th 2-4pm\n"
                + divider);
    }

    protected static void eventInvalidCommand() {
        System.out.print(divider
                + "\tPlease input the appropriate command!\n"
                + "\tAn example would be:\n"
                + "\tevent project meeting /at Aug 6th 2-4pm\n"
                + divider);
    }

    protected static void eventInvalidDate() {
        System.out.print(divider
                + "\tPlease input the date!\n"
                + "\tAn example would be:\n"
                + "\tevent project meeting /at Aug 6th 2-4pm\n"
                + divider);
    }

    protected static void invalidIndex() {
        System.out.print(divider
                + "\tThere is no such task number.\n"
                + "\tPlease enter a valid one!\n"
                + divider);
    }

    protected static void invalidInput() {
        System.out.print(divider
                + "\tOops! I'm not sure what you meant!\n"
                + "\tPlease try again!\n"
                + divider);
    }
}
