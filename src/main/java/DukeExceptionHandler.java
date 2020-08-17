import java.util.Arrays;

public class DukeExceptionHandler {

    public static String checkForException(String[] stringArr) {
        if (stringArr.length >= 1) {
            if (stringArr[0].contains("todo") ||
                    stringArr[0].contains("deadline") ||
                    stringArr[0].contains("event") ||
                    stringArr[0].contains("list") ||
                    stringArr[0].contains("done")) {
                if (stringArr.length == 1 && !stringArr[0].equals("list")) {
                    NoDescriptionException errorMessage = new NoDescriptionException("", stringArr[0]);
                    return errorMessage.toString();
                    // if there's no error
                } else {
                    return null;
                }
            // gibberish input
            } else {
                return new UnexpectedInputException().toString();
            }
        } else {
            return new EmptyTaskException().toString();
        }
    }
}
