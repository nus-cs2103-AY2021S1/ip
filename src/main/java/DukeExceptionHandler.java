public class DukeExceptionHandler {

    public static String handleException(String text) {

        if (text.equals("todo") || text.equals("deadline") || text.equals("event")) {
            //System.out.println("error1");
            NoDescriptionException error = new NoDescriptionException(text);
            return error.toString();

        } else if (!text.contains("todo") && !text.contains("deadline") && !text.contains("event")
            && !text.contains("done") && !text.contains("delete") && !text.equals("list")) {
            //System.out.println("error2");
            InvalidInputException error = new InvalidInputException(text);
            return error.toString();
        }

        return null;
    }
}
