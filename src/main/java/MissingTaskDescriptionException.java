public class MissingTaskDescriptionException extends Exception{
    public MissingTaskDescriptionException() {
        String horizontalLine = "_______________________________________________________";
        System.out.println(horizontalLine
                + "\r\n"
                + "Oops! The description cannot be empty :("
                + "\r\n"
                + horizontalLine);
    }
}
