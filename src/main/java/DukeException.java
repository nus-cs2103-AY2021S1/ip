public class DukeException {
    private static final String divider =
            "\t---------------------------------------------------\n";

    protected static void toDoInvalidDescription() {
        System.out.print(divider
                + "\tThe description of a todo cannot be empty!\n"
                + divider);
    }

    protected static void deadlineInvalidDescription() {
        System.out.print(divider
                + "\tThe description of a deadline cannot be empty!\n"
                + divider);
    }

    protected static void eventInvalidDescription() {
        System.out.print(divider
                + "\tThe description of an event cannot be empty!\n"
                + divider);
    }

    protected static void invalidInput() {
        System.out.print(divider
                + "\tOops! I'm not sure what you meant! Please try again!\n"
                + divider);
    }
}
