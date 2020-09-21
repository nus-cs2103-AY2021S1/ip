package Duke.UI;

public class UI {
    private static boolean isStillGoing = true;
    private enum Messages {
        WRONG_INPUT("Bark bark? (This doesn't make sense?)"),
        MISSING_INPUT("Bork bark?? Bark bark woof. (What does this command mean?? Try again with todo *task*, " +
                "*task* /at *start date/time* *end date/time*, or *task* by *deadline.)"),
        DONE_ERROR("Bork. (Sorry, can't be done.)"),
        BYE("bye"),
        LIST("list"),
        DONE("done"),
        DELETE("delete"),
        FIND("find"),
        GOODBYE_MSG("BARK! (Come back soon!)"),
        WELCOME_MSG("BARK BARK WOOF! (Welcome! Tell me your tasks and I'll help you keep track of them!)");


        private final String value;

        Messages(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

    /**
     * Stops the programme from running.
     */
    public static void stop() {
        isStillGoing = false;
    }

    /**
     * Retrieves messages of the title cmd.
     * @param cmd
     * @return String value stored in Messages.cmd.toString()
     */
    public static String getMessage(String cmd) {
        return Messages.valueOf(cmd).toString();
    }
}
