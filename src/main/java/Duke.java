import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args) {
        String name = "Omega";
        Duke.printHorizontalLine();
        System.out.println("Hi! I am " + name + ", your personal assistant.");
        System.out.println("How may I help you today?");
        Duke.printHorizontalLine();
        Duke.interactWithUser();
    }

    private static void interactWithUser() {
        boolean exitProgram = false;
        List<Task> listOfTasks = new ArrayList<>();
        Scanner scn = new Scanner(System.in);
        while (!exitProgram) {
            System.out.println();
            if (!scn.hasNext()) {
                exitProgram = true;
            } else {
                String userInput = scn.nextLine();
                String[] userInputArgs = userInput.split(" ");
                Duke.printHorizontalLine();
                try {
                    if (userInput.equals("bye")) {
                        System.out.println("Goodbye! Shutting down now...");
                        exitProgram = true;
                    } else if (userInput.equals("list")) {
                        int n = listOfTasks.size();
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < n; i++) {
                            System.out.println(String.format("%d.%s", i + 1, listOfTasks.get(i)));
                        }
                    } else if (userInputArgs[0].equals("done")) {
                        Task task = markTaskDone(listOfTasks, userInputArgs);
                        System.out.println("Nice! I've marked this task as done:");
                        Duke.printWithIndent(task.toString());
                    } else {
                        Task task = createTask(userInputArgs);
                        listOfTasks.add(task);
                        System.out.println("Got it. I've added this task:");
                        Duke.printWithIndent(task.toString());
                        System.out.println(String.format("Now you have %d tasks in the list.", listOfTasks.size()));
                    }
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
                Duke.printHorizontalLine();
            }
        }
    }

    private static Task markTaskDone(List<Task> listOfTasks, String[] userInputArgs) throws DukeException {
        try {
            int idx = Integer.parseInt(userInputArgs[1]);
            if (idx <= 0 || idx > listOfTasks.size()) {
                throw new DukeException("The task cannot be found.");
            }
            Task task = listOfTasks.get(idx - 1);
            task.markAsDone();
            return task;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The number of the task to be marked as done has to be provided.");
        }

    }

    private static Task createTask(String[] userInputArgs) throws DukeException {
        if (userInputArgs[0].equals("todo")) {
            String description = Duke.reassembleString(userInputArgs, 1, userInputArgs.length);
            if (description.equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new Todo(description);
        } else if (userInputArgs[0].equals("deadline")) {
            int byIdx = Arrays.asList(userInputArgs).indexOf("/by");
            if (byIdx < 0) {
                throw new DukeException("The deadline date has to be provided to the deadline task.");
            }
            String description = Duke.reassembleString(userInputArgs, 1, byIdx);
            String by = Duke.reassembleString(userInputArgs, byIdx + 1, userInputArgs.length);
            if (description.equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else if (by.equals("")) {
                throw new DukeException("The date of a deadline cannot be empty.");
            } else {
                return new Deadline(description, by);
            }
        } else if (userInputArgs[0].equals("event")) {
            int atIdx = Arrays.asList(userInputArgs).indexOf("/at");
            if (atIdx < 0) {
                throw new DukeException("The event date has to be provided to the event task.");
            }
            String description = Duke.reassembleString(userInputArgs, 1, atIdx);
            String at = Duke.reassembleString(userInputArgs, atIdx + 1, userInputArgs.length);
            if (description.equals("")) {
                throw new DukeException("The description of an event cannot be empty.");
            } else if (at.equals("")) {
                throw new DukeException("The date of an event cannot be empty.");
            } else {
                return new Event(description, at);
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means...");
        }
    }

    private static String reassembleString(String[] arr, int from, int to) {
        return String.join(" ", Arrays.copyOfRange(arr, from, to));
    }

    private static void printHorizontalLine() {
        String horizontalLine = "---------------------------------------------";
        System.out.println(horizontalLine);
    }

    private static void printWithIndent(String str) {
        System.out.println("  " + str);
    }
}
