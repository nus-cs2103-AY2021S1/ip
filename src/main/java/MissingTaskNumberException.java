public class MissingTaskNumberException extends Exception{
    public MissingTaskNumberException() {
        String horizontalLine = "_______________________________________________________";
        System.out.println(horizontalLine
                + "\r\n"
                + "Oops! The task number cannot be missing :("
                + "\r\n"
                + horizontalLine);
    }
}
