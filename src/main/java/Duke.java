import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";

    private static final Scanner s = new Scanner(System.in);
    private static final String line = "     _____________________________________";
    private static ArrayList<Task> listOfTasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        while (s.hasNextLine()) {
            String userCommand = s.nextLine();
            if (userCommand.equals("bye")) {
                exit();
                s.close();
                break;
            } else {
                if (userCommand.equals("list")) {
                    getList();
                } else {
                    String[] wordArray = userCommand.split(" ");
                    if (wordArray[0].equals("done")) {
                        Integer index = Integer.parseInt(wordArray[1]);
                        Task theTask = listOfTasks.get(index - 1);
                        theTask.setTaskToBeDone();
                        System.out.println(line);
                        System.out.println("     Great! The task: [" + theTask.getName()
                                + "] is marked as Done.\n" + "     [" + theTask.getStatusSymbol()
                                + "] " + theTask.getName());
                        System.out.println(line);
                    } else {
                        Task newTask = new Task(userCommand);
                        listOfTasks.add(newTask);
                        System.out.println(line);
                        System.out.println("     The task: [" + newTask.getName() + "] is added into the list!");
                        System.out.println(line);;
                    }
                }
            }
        }
    }
        private static void greet() {
            System.out.println(line);
            System.out.println("     Hi, I am\n" + logo);
            System.out.println("     Is there anything I could help with?");
            System.out.println(line);
        }

        private static void exit() {
            System.out.println(line);
            System.out.println("     Bye! I look forward to meeting you next time!");
            System.out.println(line);
        }

        private static void getList() {
            int noOfTasks = listOfTasks.size();
            if (noOfTasks == 0) {
                System.out.println(line);
                System.out.println("     There is no task in the list yet!");
                System.out.println(line);
            } else {
                System.out.println(line);
                for (int i = 0; i < noOfTasks; i++) {
                    Task task = listOfTasks.get(i);
                    System.out.println("     " + (i + 1) + "." + task);
                }
                System.out.println(line);
            }
        }

}
