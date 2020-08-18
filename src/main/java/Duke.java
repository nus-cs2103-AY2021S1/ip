import java.util.Scanner;

public class Duke {

    private static String horizontalLine = "    ____________________________________________________________";
    private static String textIndentation = "     ";
    private static String greetMessage = Duke.textIndentation + "Hello! I'm Duke\n" +
            Duke.textIndentation + "What can I do for you?";
    private static String exitMessage = Duke.textIndentation + "Bye. Hope to see you again soon!";

    private static TaskList taskList;

    public static void main(String[] args) {

        Duke.taskList = new TaskList();

        Duke.printText(Duke.greetMessage);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String inputMessage = sc.next();

            if (inputMessage.toLowerCase().equals("bye")) {
                Duke.printText(exitMessage);
                break;
            } else if (inputMessage.toLowerCase().equals("list")) {
                Duke.printText(Duke.taskList.printTaskList());
            } else {
                taskList.addToList(new Task(inputMessage));
                Duke.printText(Duke.textIndentation + "added: " + inputMessage);
            }
        }
    }

    static void printText(String text) {
        System.out.println(Duke.horizontalLine);
        System.out.println(text);
        System.out.println(Duke.horizontalLine);
    }
}
