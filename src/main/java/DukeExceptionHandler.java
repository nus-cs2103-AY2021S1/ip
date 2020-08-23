public class DukeExceptionHandler {

//    public static String checkForException(String[] stringArr) {
//        System.out.println(Arrays.toString(stringArr));
//        if (stringArr.length >= 1 && stringArr[0].length() > 0) {
//            if (stringArr[0].contains("todo") ||
//                    stringArr[0].contains("deadline") ||
//                    stringArr[0].contains("event") ||
//                    stringArr[0].contains("list") ||
//                    stringArr[0].contains("done") ||
//                    stringArr[0].contains("delete")) {
//                if (stringArr.length == 1 && !stringArr[0].equals("list")) {
//                    MissingDescriptionException errorMessage = new MissingDescriptionException("", stringArr[0]);
//                    return errorMessage.toString();
//                    // if there's no error
//                } else {
//                    return null;
//                }
//            // gibberish input
//            } else {
//                return new UnexpectedInputException().toString();
//            }
//        } else {
//            return new EmptyTaskException().toString();
//        }
//    }

    public static DukeException checkForException(String reply) {
        String[] replyArr = null;
        if (reply.length() > 0) {
            replyArr = reply.split(" ");
        }
        DukeException exception = null;
        if (reply.length() == 0) {
            exception = new EmptyTaskException();

        // shortest reply allowed is "list"
        // also unexpected if it doesn't contain any of the keywords: "todo, deadline, event, done, delete, list, find"
        } else if (reply.length() < 4 ||
                (!reply.contains("todo") && !reply.contains("deadline")
                    && !reply.contains("event") && !reply.contains("done")
                        && !reply.contains("delete") && !reply.contains("list")
                            && !reply.contains("find"))) {
            exception = new UnexpectedInputException();

        // if the reply has the task keywords but not description
        // future: need to check if deadline and event have "/by" and "/at"
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

        // if the reply has the action keywords but no task number
        } else if (replyArr.length == 1 && (replyArr[0].equals("done") || replyArr[0].equals("delete"))) {
            if (replyArr[0].equals("done")) {
                exception = new MissingTaskIndexException(InputType.DONE);
            } else {
                exception = new MissingTaskIndexException(InputType.DELETE);
            }
        }

        return exception;
    }
}
