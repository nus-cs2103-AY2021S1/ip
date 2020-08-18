import java.util.Scanner;

public class Duke {

    private static String horizontalLine = "    ____________________________________________________________";
    private static String textIndentation = "     ";
    private static String greetMessage = Duke.textIndentation + "Hello! I'm Duke\n" +
            Duke.textIndentation + "What can I do for you?";
    private static String exitMessage = Duke.textIndentation + "Bye. Hope to see you again soon!";
    private static String completeTaskMessage = Duke.textIndentation + "Nice! I've marked this task as done:";
    private static TaskList taskList;

    public static void main(String[] args) {

        Duke.taskList = new TaskList();

        Duke.printText(Duke.greetMessage);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String inputMessage = sc.nextLine();

            if (inputMessage.toLowerCase().equals("bye")) {
                Duke.printText(exitMessage);
                break;
            } else if (inputMessage.toLowerCase().equals("list")) {
                Duke.printText(Duke.taskList.toString());
            } else if (inputMessage.toLowerCase().startsWith("done")) {
                String taskQueryIndexString = inputMessage.substring(4).trim();

                try {
                    int taskQueryIndex = Integer.parseInt(taskQueryIndexString);
                    Task completedTask = Duke.taskList.getTaskAt(taskQueryIndex);
                    completedTask.completeTask();
                    Duke.printText(Duke.completeTaskMessage + "\n" + Duke.textIndentation + completedTask);
                } catch (Exception e) {
                    Duke.printText(Duke.textIndentation + "Please enter a valid task index");
                }

            } else {
                Duke.taskList.addToList(new Task(inputMessage));
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
