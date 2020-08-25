public class DukeExceptionHandler {

    /** Checks for Duke exception in the user input.
     * @param userInput A String that represent the user's input
     * @return A DukeException if an exception is found, null if there is no found exception
     */
    public static DukeException checkForException(String userInput) {
        String[] replyArr = null;
        DukeException exception = null;
        if (userInput.length() > 0) {
            replyArr = userInput.split(" ");
        }
        if (userInput.length() == 0) {
            exception = new EmptyTaskException();
        // shortest userInput allowed is "list"
        // also unexpected if it doesn't contain any of the keywords:
        // "todo, deadline, event, done, delete, list, find"
        } else if (userInput.length() < 4 ||
                (!userInput.contains("todo") && !userInput.contains("deadline")
                    && !userInput.contains("event") && !userInput.contains("done")
                        && !userInput.contains("delete") && !userInput.contains("list")
                            && !userInput.contains("find"))) {
            exception = new UnexpectedInputException();
        // if the userInput has the task keywords but not description
        // future: need to check if deadline and event have "/by" and "/at"
        // can try to use string.startsWith
        } else if (replyArr.length == 1 && (replyArr[0].equals("todo") ||
                                            replyArr[0].equals("deadline") ||
                                            replyArr[0].equals("event"))) {
            if (replyArr[0].equals("todo")) {
                exception = new MissingDescriptionException(InputType.TODO);
            } else if (replyArr[0].equals("deadline")) {
                exception = new MissingDescriptionException(InputType.DEADLINE);
            } else {
                exception = new MissingDescriptionException(InputType.EVENT);
            }
        // if the userInput has the action keywords but no task number
        } else if (replyArr.length == 1 && (replyArr[0].equals("done")
                || replyArr[0].equals("delete"))) {
            if (replyArr[0].equals("done")) {
                exception = new MissingTaskIndexException(InputType.DONE);
            } else {
                exception = new MissingTaskIndexException(InputType.DELETE);
            }
        }
        return exception;
    }
}
