import task.Todo;
import task.Event;
import task.Deadline;
import task.TaskManager;

import commands.CommandTypes;

import utils.Formatter;
import utils.Colour;
import utils.ResourceHandler;

import exceptions.DukeException;

import java.util.ResourceBundle;
import java.util.Scanner;

public class CommandLineInterface {
    private static final int dividerLength = 70;
    private static final int leftPadding = 7;
    private static final String goodbyeMessage = "Bye. Hope my service has been satisfactory."
            + "Hope to see you again soon.";
    private static final String invalidTaskIndexErrorMessage = "Invalid task index! Please choose another index.";
    private static final String invalidCommandInput = "OOPS!!!! I'm sorry, but I don't know what that means";

    private static final Scanner scanner = new Scanner(System.in);
    private static final Formatter formatter = new Formatter(dividerLength, leftPadding);
    private static final TaskManager taskManager = new TaskManager();


    public static void run() {
        formatter.print(ResourceHandler.getMessage("commandline.welcomeMessage"));
        while (scanner.hasNext()) {
            try {
                String userInput = scanner.nextLine();
                String[] words = userInput.toLowerCase().split(" ");
                CommandTypes commandType = CommandTypes.valueOf(words[0].toUpperCase());
                commandType.checkInput(userInput.toLowerCase());
                String content, dateTime;
                String[] userInputArgs;
                switch (commandType) {
                    case LIST:
                        formatter.print(taskManager.getAllTasks());
                        break;

                    case BYE:
                        formatter.print(ResourceHandler.getMessage("commandline.farewellMessage"));
                        return;

                    case TODO:
                        content = userInput.replaceFirst("^(?i)todo", "");
                        String trimmedContent = content.trim();
                        Todo newTodoTask = new Todo(trimmedContent);
                        formatter.print(taskManager.addTask(newTodoTask));
                        break;

                    case DEADLINE:
                        userInput = userInput.replaceFirst("^(?i)deadline", "");
                        userInputArgs = userInput.split("\\s*/by\\s*");
                        content = userInputArgs[0].trim();
                        dateTime = userInputArgs[1];
                        Deadline newDeadlineTask = new Deadline(content, dateTime);
                        formatter.print(taskManager.addTask(newDeadlineTask));
                        break;

                    case EVENT:
                        userInput = userInput.replaceFirst("^(?i)event", "");
                        userInputArgs = userInput.split("\\s*/at\\s*");
                        content = userInputArgs[0].trim();
                        dateTime = userInputArgs[1];
                        Event newEventTask = new Event(content, dateTime);
                        formatter.print(taskManager.addTask(newEventTask));
                        break;

                    case DONE:
                        try {
                            int taskIndex = Integer.parseInt(words[1]);
                            formatter.print(taskManager.markTaskAsDone(taskIndex));
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException(
                                    ResourceHandler.getMessage("commandline.invalidTaskIndexErrorMessage"));
                        }
                        break;

                    case DELETE:
                        try {
                            int taskIndex = Integer.parseInt(words[1]);
                            formatter.print(taskManager.deleteTask(taskIndex));
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException(
                                    ResourceHandler.getMessage("commandline.invalidTaskIndexErrorMessage"));
                        }
                }
            } catch (DukeException e) {
                formatter.print(e.toString());
            } catch (IllegalArgumentException e) {
                formatter.print(Colour.Red(ResourceHandler.getMessage("commandline.invalidCommandInputMessage")));
            }
        }
        scanner.close();
    }

}
