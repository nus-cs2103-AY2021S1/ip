public class DukeErrorsHandler {
    private static final String divider =
            "\t---------------------------------------------------\n";

    public static void toDoInvalidDescription() {
        System.out.println(divider
                + "\tThe description of a todo cannot be empty!\n"
                + divider);
    }

    public static void deadlineInvalidDescription() {
        System.out.println(divider
                + "\tThe description of a deadline cannot be empty!\n"
                + divider);
    }

    public static void eventInvalidDescription() {
        System.out.println(divider
                + "\tThe description of an event cannot be empty!\n"
                + divider);
    }

    public static void invalidInput() {
        System.out.println(divider
                + "\tOops! I'm not sure what you meant! Please try again!\n"
                + divider);
    }
}
