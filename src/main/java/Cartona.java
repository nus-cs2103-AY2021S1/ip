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

        System.out.printf(line + "     Here are the tasks in your list:%n");
        for (Task task: taskList) {
            counter++;
            System.out.printf("     %d.%s%n", counter, task);
        }

        System.out.printf(line);
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

    private static void deleteTask(int taskNum) {
        Task toDelete = taskList.get(taskNum - 1);
        taskList.remove(taskNum - 1);
        String deleteMsg = String.format("     Noted. I've removed this task:%n       %s%n", toDelete);
        String countMsg = String.format("     Now you have %d tasks in the list.%n", taskList.size());
        System.out.printf(line + deleteMsg + countMsg + line);
    }

    /**
     * Helper function that parses the "add ..." argument into the console to add a Task to the taskList
     * @param consoleArg the exact string entered into the console
     */
    private static void addTask(String consoleArg) throws EmptyTaskDescriptionException, InvalidTaskTimeException, UnknownCommandException {
        String[] parsedArr = consoleArg.substring(4).split(" ");
        String keyword = parsedArr[0];
        if (keyword.equals("todo")) {
            String name = "";

            for (int i = 1; i < parsedArr.length; i++) {
                if (i != parsedArr.length - 1) {
                    name += parsedArr[i] + " ";
                } else {
                    name += parsedArr[i];
                }
            }
            if (name.equals("")) {
                throw new EmptyTaskDescriptionException("");
            }
            taskList.add(new Todo(name));
        } else if (keyword.equals("deadline")) {
            String name = "";
            String time = "";
            boolean nameOrTime = true;
            for (int i = 1; i < parsedArr.length; i++) {
                if (nameOrTime) {
                    if (parsedArr[i].contains("/by")) {
                        if (name.equals("")) {
                            throw new EmptyTaskDescriptionException("");
                        }
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
            if (name.equals("")) {
                throw new EmptyTaskDescriptionException("");
            }
            if (time.equals("")) {
                throw new InvalidTaskTimeException("");
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

            if (name.equals("")) {
                throw new EmptyTaskDescriptionException("");
            }
            if (timeRange.equals("")) {
                throw new InvalidTaskTimeException("");
            }

            taskList.add(new Event(name, timeRange));
        } else {
            throw new UnknownCommandException("");
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

        System.out.printf(welcome);

        String nextInput = "";
        while (true) {
            nextInput = sc.nextLine();

            if (nextInput.equals("bye")) {
                break;
            } else if (nextInput.equals("list")) {
                printList();
            } else if (nextInput.length() < 4) {
                System.out.printf("%s     Error: Invalid keyword! Please try again%n%s",
                        line, line);
            } else if (nextInput.substring(0, 4).equals("done")) {
                try {
                    String[] doneArr = nextInput.split(" ");
                    int taskNum = Integer.parseInt(doneArr[1]);
                    finishTask(taskList.get(taskNum - 1));
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("%s     Error: Invalid input! Did you mean: \"done TASK_NUM\"%n%s",
                                        line, line);
                }
            } else if (nextInput.substring(0, 3).equals("add")) {
                try {
                    addTask(nextInput);
                    System.out.printf("%s     Got it. I've added this task:%n       %s%n     " +
                                    "Now you have %d tasks in the list.%n%s", line, taskList.get(taskList.size() - 1),
                            taskList.size(), line);
                } catch (EmptyTaskDescriptionException e1) {
                    System.out.printf("%s     Error: Empty task description!%n%s",
                            line, line);
                } catch (InvalidTaskTimeException e2) {
                    System.out.printf("%s     Error: Empty deadline/event time - please enter the time after /by " +
                                    "or /at keywords respectively%n%s",
                            line, line);
                } catch (UnknownCommandException e3) {
                    System.out.printf("%s     Error: Unknown keyword after \"add\"%n%s",
                            line, line);
                }
            } else if (nextInput.substring(0, 3).equals("del")) {
                try {
                    String[] doneArr = nextInput.split(" ");
                    int taskNum = Integer.parseInt(doneArr[1]);
                    deleteTask(taskNum);
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("%s     Error: Invalid input! Did you mean: \"del TASK_NUM\"%n%s",
                            line, line);
                }
            } else {
                System.out.printf("%s     Error: Invalid keyword! Please try again%n%s",
                        line, line);
            }
        }

        String goodbye = line + "     Bye. Hope to see you again soon!\n" + line;
        System.out.printf(goodbye);
    }
}