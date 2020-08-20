public class CommandNotRecognisedException extends Exception{
    public CommandNotRecognisedException() {

        String horizontalLine = "_______________________________________________________";
        System.out.println(horizontalLine
                + "\r\n"
                + "Oops! I couldn't understand what you mean :("
                + "\r\n"
                + horizontalLine);
    }
}
