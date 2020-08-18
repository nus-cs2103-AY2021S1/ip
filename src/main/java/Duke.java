import java.util.Scanner;

public class Duke {

    private static String horizontalLine = "    ____________________________________________________________";
    private static String textIndentation = "     ";
    private static String greetMessage = Duke.textIndentation + "Hello! I'm Duke\n" +
            Duke.textIndentation + "What can I do for you?";
    private static String exitMessage = Duke.textIndentation + "Bye. Hope to see you again soon!";
    private static String addTaskMessage = Duke.textIndentation + "Got it. I've added this task:";
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

            } else if (inputMessage.toLowerCase().startsWith("todo ")) {
                ToDo newToDo = new ToDo(inputMessage);
                Duke.taskList.addToList(newToDo);
                Duke.printText(Duke.addTaskMessage + "\n  " + Duke.textIndentation + newToDo);

            } else if (inputMessage.toLowerCase().startsWith("deadline ")) {
                Deadline newDeadline = new Deadline(inputMessage);
                Duke.taskList.addToList(newDeadline);
                Duke.printText(Duke.addTaskMessage + "\n  " + Duke.textIndentation + newDeadline);

            } else if (inputMessage.toLowerCase().startsWith("event ")) {
                Event newEvent = new Event(inputMessage);
                Duke.taskList.addToList(newEvent);
                Duke.printText(Duke.addTaskMessage + "\n  " + Duke.textIndentation + newEvent);

            }
        }
    }

    static void printText(String text) {
        System.out.println(Duke.horizontalLine);
        System.out.println(text);
        System.out.println(Duke.horizontalLine);
    }
}
