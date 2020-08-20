public class DukeExceptionHandler {

    public static String handleException(String input) {

        if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
            //tasks created with no description
            InvalidDescriptionException error = new InvalidDescriptionException(input);
            return error.toString();

        } else if (!input.contains("todo") && !input.contains("deadline") && !input.contains("event")
                && !input.contains("done") && !input.equals("list")) {
            WrongInputException error = new WrongInputException(input);
            return error.toString();
        }

        return null;
    }
}