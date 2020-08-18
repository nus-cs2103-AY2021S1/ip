import java.util.Scanner;

public class Duke {

    private static String horizontalLine = "    ____________________________________________________________";
    private static String textIndentation = "     ";
    private static String greetMessage = " Hello! I'm Duke\n" +
            Duke.textIndentation + " What can I do for you?";
    private static String exitMessage = "Bye. Hope to see you again soon!";

    private static TextList textList = new TextList();

    public static void main(String[] args) {

        Duke.textList = new TextList();

        Duke.printMessage(Duke.greetMessage);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String inputMessage = sc.next();

            if (inputMessage.toLowerCase().equals("bye")) {
                Duke.printMessage(exitMessage);
                break;
            } else if (inputMessage.toLowerCase().equals("list")) {
                Duke.printList();
            } else {
                textList.addToList(inputMessage);
                Duke.printMessage(inputMessage);
            }
        }
    }

    static void printMessage(String message) {
        System.out.println(Duke.horizontalLine);
        System.out.println(Duke.textIndentation + message);
        System.out.println(Duke.horizontalLine);
    }

    static void printList() {
        if (Duke.textList.isEmpty()) {
            Duke.printMessage("No tasks found");
        } else {
            System.out.println(Duke.horizontalLine);
            Duke.textList.printList();
            System.out.println(Duke.horizontalLine);
        }
    }
}
