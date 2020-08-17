import task.Task;
import task.Todo;
import task.Event;
import task.Deadline;
import task.TaskManager;
import utils.Formatter;
import utils.Colour;
import exceptions.DukeException;

import java.util.Scanner;

public class CommandLineInterface {
    private static final int dividerLength = 70;
    private static final int leftPadding = 7;
    private static final String welcomeMessage = "Hello! I'm Erica \n" + "How may I be of assistance?\n";
    private static final String goodbyeMessage = "Bye. Hope my service has been satisfactory."
            + "Hope to see you again soon.";
    private static final String todoErrorMessage = "OOPS!!! The description of a todo cannot be empty";
    private static final String eventErrorMessage = "OOPS!!! The description of an event cannot be empty.";
    private static final String deadlineErrorMessage = "OOPS!!! The description of a deadline cannot be empty.";
    private static final String noDateTimeErrorMessage = "OOPS!!! Missing deadline date/time";
    private static final String invalidTaskIndexErrorMessage = "Invalid task index! Please choose another index.";
    private static final String invalidCommandInput = "OOPS!!!! I'm sorry, but I don't know what that means";

    private static final Scanner scanner = new Scanner(System.in);
    private static final Formatter formatter = new Formatter(dividerLength, leftPadding);
    private static final TaskManager taskManager = new TaskManager();


    public static void run() throws DukeException {
        formatter.print(welcomeMessage);
        while (scanner.hasNext()) {
            try {
                String userInput = scanner.nextLine();
                String[] words = userInput.toLowerCase().split(" ");
                if (words[0].equals("done")) {
                    try {
                        int taskIndex = Integer.parseInt(words[1]);
                        formatter.print(taskManager.markTaskAsDone(taskIndex));
                    } catch (ArrayIndexOutOfBoundsException e){
                        throw new DukeException(invalidTaskIndexErrorMessage);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException(invalidTaskIndexErrorMessage);
                    }
                } else if (words[0].equals("todo")) {
                    String content = userInput.replaceFirst("^todo", "");

                    if (content.isBlank()) {
                        throw new DukeException(todoErrorMessage);
                    }
                    String trimmedContent = content.trim();
                    Todo newTodoTask = new Todo(trimmedContent);
                    formatter.print(taskManager.addTask(newTodoTask));
                } else if (words[0].equals("deadline")) {
                    userInput = userInput.replaceFirst("^deadline", "");
                    String[] userInputArgs = userInput.split("\\s*/by\\s*");
                    try {
                        String content = userInputArgs[0].trim();

                        if (content.isBlank()) {
                            throw new DukeException(deadlineErrorMessage);
                        }
                        String dateTime = userInputArgs[1];
                        Deadline newDeadlineTask = new Deadline(content, dateTime);
                        formatter.print(taskManager.addTask(newDeadlineTask));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException(noDateTimeErrorMessage);
                    }
                } else if (words[0].equals("event")) {
                    userInput = userInput.replaceFirst("^event", "");
                    String[] userInputArgs = userInput.split("\\s*/at\\s*");
                    try {
                        String content = userInputArgs[0].trim();

                        if (content.isBlank()) {
                            throw new DukeException(eventErrorMessage);
                        }
                        String dateTime = userInputArgs[1];
                        Event newEventTask = new Event(content, dateTime);
                        formatter.print(taskManager.addTask(newEventTask));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException(noDateTimeErrorMessage);
                    }
                } else if (userInput.toLowerCase().equals("bye")) {
                    formatter.print(goodbyeMessage);
                    break;
                } else if (userInput.toLowerCase().equals("list")) {
                    formatter.print(taskManager.getAllTasks());
                } else {
                    throw new DukeException(invalidCommandInput);
                }
            } catch (DukeException e) {
                formatter.print(e.toString());
            }
        }
        scanner.close();
    }

}
