/**
 * ExitCommand inherits from Command and is called to
 * terminate the program when user inputs "bye".
 */
class ByeCommand extends Command {
    static String farewellMsg = "Bye. Hope to see you again soon!";
    /**
     * Prints a goodbye message and terminates Duke.
     *
     * @param tasks This is the saved TaskList in saved file.
     * @return A boolean to indicate whether the program should be exited.
     */
    @Override
    boolean execute(TaskList tasks) {
        Ui.simplePrint(farewellMsg);
        return true;
    }
}