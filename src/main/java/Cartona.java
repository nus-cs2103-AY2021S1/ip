import java.util.ArrayList;
import java.util.Scanner;

public class Cartona {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    private static String line = "    ____________________________________________________________\n";

    /**
     * Prints the current list of tasks to the console.
     */
    private static void printList() {
        int counter = 0;

        System.out.printf(line);

        for (Task task: taskList) {
            counter++;
            System.out.printf("     %d.%s%n", counter, task);
        }

        System.out.println(line);
    }

    /**
     * Marks a task as completed
     */
    private static void finishTask(Task task) {
        task.complete();
        String completion = "     Nice! I've marked this task as done:\n" +
                            String.format("       %s%n", task);
        System.out.printf(line + completion + line);
    }

    /**
     * Helper function that parses the "add ..." argument into the console to add a Task to the taskList
     * @param consoleArg the exact string entered into the console
     */
    private static void addTask(String consoleArg) {
        String[] parsedArr = consoleArg.substring(4).split(" ");
        String keyword = parsedArr[0];
        if (keyword.equals("todo")) {
            String name = "";

            for (int i = 1; i < parsedArr.length; i++) {
                name += parsedArr[i] + " ";
            }
            taskList.add(new Todo(name));
        } else if (keyword.equals("deadline")) {
            String name = "";
            String time = "";
            boolean nameOrTime = true;
            for (int i = 1; i < parsedArr.length; i++) {
                if (nameOrTime) {
                    if (parsedArr[i].contains("/by")) {
                        nameOrTime = false;
                        continue;
                    } else {
                        name += parsedArr[i] + " ";
                    }
                } else {
                    if (i != parsedArr.length - 1) {
                        time += parsedArr[i] + " ";
                    } else {
                        time += parsedArr[i];
                    }
                }
            }

            taskList.add(new Deadline(name, time));

        } else if (keyword.equals("event")) {
            String name = "";
            String timeRange = "";
            boolean nameOrTime = true;
            for (int i = 1; i < parsedArr.length; i++) {
                if (nameOrTime) {
                    if (parsedArr[i].contains("/at")) {
                        nameOrTime = false;
                        continue;
                    } else {
                        name += parsedArr[i] + " ";
                    }
                } else {
                    if (i != parsedArr.length - 1) {
                        timeRange += parsedArr[i] + " ";
                    } else {
                        timeRange += parsedArr[i];
                    }
                }
            }

            taskList.add(new Event(name, timeRange));
        } else {
            System.out.println("Invalid keyword after \"add\"");
        }
    }

    public static void main(String[] args) {
        /**
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         System.out.println("Hello from\n" + logo);
         **/

        // Print welcome statement
        String welcome = line + "     Hello! I'm Cartona.\n" +
                         "     What can I do for you?\n" + line;

        System.out.println(welcome);

        String nextInput = "";
        while (true) {
            nextInput = sc.nextLine();

            if (nextInput.equals("bye")) {
                break;
            } else if (nextInput.equals("list")) {
                printList();
            } else if (nextInput.substring(0, 4).equals("done")) {
                String[] doneArr = nextInput.split(" ");
                int taskNum = Integer.parseInt(doneArr[1]);
                finishTask(taskList.get(taskNum - 1));
            } else if (nextInput.substring(0, 3).equals("add")) {
                addTask(nextInput);
                System.out.printf("%s     Got it. I've added this task:%n       %s%n     " +
                                  "Now you have %d tasks in the list.%n%s", line, taskList.get(taskList.size() - 1),
                                    taskList.size(), line);
            }
        }

        String goodbye = line + "     Bye. Hope to see you again soon!\n" + line;
        System.out.println(goodbye);
    }
}