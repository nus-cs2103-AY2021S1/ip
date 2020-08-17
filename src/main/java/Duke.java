import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String lineIndentation = "    ";
        String horizontalLine = lineIndentation + "____________________________________________________________\n";

        String textIndentation = "     ";
        String greetMessage = textIndentation + " Hello! I'm Duke\n" +
                textIndentation + " What can I do for you?\n";
        String exitMessage = textIndentation + "Bye. Hope to see you again soon!";

        System.out.println(horizontalLine + greetMessage + horizontalLine);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String inputMessage = sc.next();
            String echoMessage = textIndentation + inputMessage;

            if (inputMessage.toLowerCase().equals("bye")) {
                System.out.println(horizontalLine + exitMessage + "\n" + horizontalLine);
                break;
            } else {
                System.out.println(horizontalLine + echoMessage + "\n" + horizontalLine);
            }
        }
    }
}
