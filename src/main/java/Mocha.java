import java.util.Scanner;
import java.util.ArrayList;

public class Mocha {
    public static void main(String[] args) throws MissingTaskNumberException {
        // Contains all tasks
        ArrayList<Task> listOfTasks = new ArrayList<>();

        // Introduction of Mocha
        String horizontalLine = "_______________________________________________________";
        String nameIntro = "Hello, I'm Mocha!";
        String greeting = "What's up today!";
        System.out.println(horizontalLine + "\r\n" + nameIntro + "\r\n"
                + greeting + "\r\n" + horizontalLine + "\r\n");

        Scanner userCommand = new Scanner(System.in);

        while (userCommand.hasNext()) {
            // Any command other than "list", "bye" and "done" - add into listOfTasks
            if (!userCommand.hasNext("list")
                    && !userCommand.hasNext("bye")
                    && !userCommand.hasNext("done")) {
                String itemToBeAdded = userCommand.nextLine();
                Task newTask = new Task(itemToBeAdded);
                System.out.println(horizontalLine
                        + "\r\n"
                        + "added: "
                        + itemToBeAdded
                        + "\r\n"
                        + horizontalLine
                        + "\r\n");
                listOfTasks.add(newTask);
            }

            // "done" command - mark task as done
            if (userCommand.hasNext("done")) {
                String doneCommandString = userCommand.nextLine();
                String[] split = doneCommandString.split("\\s+");
                if (split.length < 2) {
                    throw new MissingTaskNumberException("Task Number needs to be stated");
                }
                int taskNumber = Integer.parseInt(split[1]) - 1;
                Task task = listOfTasks.get(taskNumber);
                task.markAsDone();
                System.out.println(horizontalLine
                        + "\r\n Nice! One task done: \r\n ["
                        + task.getStatusIcon()
                        + "] " + listOfTasks.get(taskNumber).getDescription()
                        + "\r\n" + horizontalLine);
            }

            // "list" command - displays all tasks
            if (userCommand.hasNext("list")) { // Process closing here
                System.out.println(horizontalLine + " \r\n" + "Here's all the tasks in your list: ");
                for (int i = 0; i < listOfTasks.size(); i++) {
                    System.out.println((i + 1)
                            + ".["
                            + listOfTasks.get(i).getStatusIcon()
                            + "] "
                            + listOfTasks.get(i).getDescription());
                }
                System.out.println(horizontalLine + " \r\n");
                userCommand.nextLine();
            }

            // Mocha's farewell
            if (userCommand.hasNext("bye")) {
                System.out.println(horizontalLine
                        + "\r\n"
                        + "Bye! See ya soon!"
                        + "\r\n"
                        + horizontalLine);
                userCommand.nextLine();
            }
        }
    }
}
