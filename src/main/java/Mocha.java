import java.util.Scanner;
import java.util.ArrayList;

public class Mocha {
    public static void main(String[] args) {
        // Contains all tasks
        ArrayList<Task> listOfTasks= new ArrayList<>();

        // Introduction of Mocha
        String horizontalLine = "_______________________________________________________";
        String nameIntro = "Hello, I'm Mocha!";
        String greeting = "What's up today!";
        System.out.println(horizontalLine
                + "\r\n"
                + nameIntro
                + "\r\n"
                + greeting
                + "\r\n"
                + horizontalLine
                + "\r\n");

        // Create new scanner
        Scanner userCommand = new Scanner(System.in);

        while (userCommand.hasNextLine()) {

            String nextCommand = userCommand.nextLine();
            String[] commandParts = nextCommand.split("\\s", 2);

            if (commandParts[0].contains("todo")) {
                Task newToDoTask = new ToDo(commandParts[1]);
                listOfTasks.add(newToDoTask);

                System.out.println(horizontalLine
                        + "\r\n"
                        + "One new ToDo Task added: "
                        + "\r\n"
                        + newToDoTask.toString()
                        + "\r\n"
                        + "Total number of tasks in list: "
                        + listOfTasks.size()
                        + "\r\n"
                        + horizontalLine);
            }

            if (commandParts[0].contains("deadline")) {
                String[] deadlineParts  = commandParts[1].split("/by");
                Task newDeadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                listOfTasks.add(newDeadline);
                System.out.println(horizontalLine
                        + "\r\n"
                        + "One new Deadline added: "
                        + "\r\n"
                        + newDeadline.toString()
                        + "\r\n"
                        + "Total number of tasks in list: "
                        + listOfTasks.size()
                        + "\r\n"
                        + horizontalLine);
            }

            if (commandParts[0].contains("event")) {
                String[] eventParts  = commandParts[1].split("/at");
                Task newEvent = new Event(eventParts[0].trim(), eventParts[1].trim());
                listOfTasks.add(newEvent);
                System.out.println(horizontalLine
                        + "\r\n"
                        + "One new Deadline Task added: "
                        + "\r\n"
                        + newEvent.toString()
                        + "\r\n"
                        + "Total number of tasks in list: "
                        + listOfTasks.size()
                        + "\r\n"
                        + horizontalLine);
            }

            if (commandParts[0].contains("list")) {
                System.out.println(horizontalLine + "\r\n" + "Here's all the tasks in your list: ");
                for (int i = 0; i < listOfTasks.size(); i++) {
                    System.out.println((i + 1) + "." + listOfTasks.get(i).toString());
                }
                System.out.println(horizontalLine + "\r\n");
            }


            if (commandParts[0].contains("done")) {
                int taskNumber = Integer.parseInt(commandParts[1].trim()) - 1;
                Task doneTask = listOfTasks.get(taskNumber);
                doneTask.markAsDone();
                System.out.println(horizontalLine
                        + "\r\n Nice! One thing done: \r\n"
                        + listOfTasks.get(taskNumber).toString()
                        + "\r\n"
                        + horizontalLine);
            }

            if (commandParts[0].contains("bye")) {
                System.out.println(horizontalLine
                        + "\r\n"
                        + "Bye! See ya soon!"
                        + "\r\n"
                        + horizontalLine);
            }
        }
    }
}

