package duke.parser;

import java.io.FileNotFoundException;

import duke.Ui;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Parser deals with users' commands, differentiates each type of task.
 */
public class Parser {
    /**
     * Command enum lists out all the types of commands.
     */
    public enum Command {
        BYE("bye"), LIST("list"), DONE("done"), TODO("todo"), DEADLINE("deadline"),
              EVENT("event"), DELETE("delete"), FILTER("filter"), FIND("find");
        private String value;
        Command(String value) {
            this.value = value;
        }
    }

    /**
     * Executes user's commands.
     *
     * @param tasks list of current tasks.
     * @param ui UI for client.
     * @param storage save and display data from database.
     * @param userInput user's command.
     * @return boolean terminate when it returns true.
     * @throws FileNotFoundException If the file path cannot be found.
     */
    public static boolean execute(TaskList tasks, Ui ui, Storage storage, String userInput)
            throws FileNotFoundException {
        if (userInput.equals(Command.BYE.value)) {
            System.out.println(ui.sayGoodbye());
            return true;

        } else if (userInput.equals(Command.LIST.value)) {
            System.out.println(tasks.showTasksAsString());

        } else if (userInput.startsWith(Command.DONE.value)) {
            try {
                int taskPosition = Integer.parseInt(userInput.substring(5));
                System.out.println(tasks.setDoneAsString(taskPosition));

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ui.showInvalidFormatCommandDescription());
            } catch (NumberFormatException e) {
                System.out.println(ui.showInvalidFormatCommandDescription());
            }

        } else if (userInput.startsWith(Command.TODO.value)) {
            try {
                String description = userInput.trim().substring(5);
                System.out.println(tasks.addToDoAsString(description));
                storage.saveTasks(tasks.getTasksList());
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ui.showInvalidFormatCommandDescription());
            }

        } else if (userInput.startsWith(Command.DEADLINE.value)) {
            try {
                int spacePosition = userInput.indexOf(" ");
                int keywordPosition = userInput.indexOf("/by");
                String description = userInput.substring(spacePosition + 1, keywordPosition - 1);
                String by = userInput.substring(keywordPosition + 4);
                System.out.println(tasks.addDeadlineAsString(description, by));
                storage.saveTasks(tasks.getTasksList());
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ui.showInvalidFormatCommandDescription());
            }

        } else if (userInput.startsWith(Command.EVENT.value)) {
            try {
                int spacePosition = userInput.indexOf(" ");
                int keywordPosition = userInput.indexOf("/at");
                String description = userInput.substring(spacePosition + 1, keywordPosition - 1);
                String at = userInput.substring(keywordPosition + 4);
                System.out.println(tasks.addEventAsString(description, at));
                storage.saveTasks(tasks.getTasksList());
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ui.showInvalidFormatCommandDescription());
            }

        } else if (userInput.startsWith(Command.DELETE.value)) {
            try {
                int taskPosition = Integer.parseInt(userInput.substring(7));
                System.out.println(tasks.deleteTaskAsString(taskPosition));
                storage.saveTasks(tasks.getTasksList());

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ui.showInvalidFormatCommandDescription());
            }

        } else if (userInput.startsWith(Command.FILTER.value)) {
            String[] inputArray = userInput.split(" ");
            System.out.println(tasks.filterTask(inputArray));
        } else if (userInput.startsWith(Command.FIND.value)) {
            try {
                String keyword = userInput.substring(6);
                System.out.println(tasks.findTasks(keyword.trim()));

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ui.showInvalidFormatCommandDescription());
            }
        } else {
            System.out.println(ui.showMeaninglessCommandDescription());
        }
        return false;
    }

    /**
     * Displays reply to users' commands
     *
     * @param tasks list of current tasks
     * @param ui UI for client.
     * @param storage save and display data from database.
     * @param userInput user's command.
     * @return boolean terminate when it returns true.
     * @throws FileNotFoundException If the file path cannot be found.
     */
    public static String getUiReply(TaskList tasks, Ui ui, Storage storage, String userInput)
            throws FileNotFoundException {
        if (userInput.equals(Command.BYE.value)) {
            return ui.sayGoodbye();
        } else if (userInput.equals(Command.LIST.value)) {
            return tasks.showTasksAsString();
        } else if (userInput.startsWith(Command.DONE.value)) {
            try {
                int taskPosition = Integer.parseInt(userInput.substring(5));
                String response = tasks.setDoneAsString(taskPosition);
                assert response.length() > 0 : "Response should not be empty";
                storage.saveTasks(tasks.getTasksList());
                return response;

            } catch (StringIndexOutOfBoundsException e) {
                return ui.showInvalidFormatCommandDescription();
            } catch (NumberFormatException e) {
                return ui.showInvalidFormatCommandDescription();
            }
        } else if (userInput.startsWith(Command.TODO.value)) {
            try {
                String description = userInput.trim().substring(5);
                String response = tasks.addToDoAsString(description);
                storage.saveTasks(tasks.getTasksList());
                assert response.length() > 0 : "Response should not be empty";
                return response;
            } catch (StringIndexOutOfBoundsException e) {
                return ui.showInvalidFormatCommandDescription();
            }

        } else if (userInput.startsWith(Command.DEADLINE.value)) {
            try {
                int spacePosition = userInput.indexOf(" ");
                int keywordPosition = userInput.indexOf("/by");
                String description = userInput.substring(spacePosition + 1, keywordPosition - 1);
                String by = userInput.substring(keywordPosition + 4);
                String response = tasks.addDeadlineAsString(description, by);
                storage.saveTasks(tasks.getTasksList());
                assert response.length() > 0 : "Response should not be empty";
                return response;
            } catch (StringIndexOutOfBoundsException e) {
                return ui.showInvalidFormatCommandDescription();
            }

        } else if (userInput.startsWith(Command.EVENT.value)) {
            try {
                int spacePosition = userInput.indexOf(" ");
                int keywordPosition = userInput.indexOf("/at");
                String description = userInput.substring(spacePosition + 1, keywordPosition - 1);
                String at = userInput.substring(keywordPosition + 4);
                String response = tasks.addEventAsString(description, at);
                storage.saveTasks(tasks.getTasksList());
                assert response.length() > 0 : "Response should not be empty";
                return response;

            } catch (StringIndexOutOfBoundsException e) {
                return ui.showInvalidFormatCommandDescription();
            }
        } else if (userInput.startsWith(Command.DELETE.value)) {
            try {
                int taskPosition = Integer.parseInt(userInput.substring(7));
                String response = tasks.deleteTaskAsString(taskPosition);
                storage.saveTasks(tasks.getTasksList());
                assert response.length() > 0 : "Response should not be empty";
                return response;

            } catch (StringIndexOutOfBoundsException e) {
                return ui.showInvalidFormatCommandDescription();
            }
        } else if (userInput.startsWith(Command.FIND.value)) {
            try {
                String keyword = userInput.substring(6);
                String response = tasks.findTasks(keyword.trim());
                assert response.length() > 0 : "Response should not be empty";
                return response;

            } catch (StringIndexOutOfBoundsException e) {
                return ui.showInvalidFormatCommandDescription();
            }
        } else if (userInput.startsWith(Command.FILTER.value)) {
            String[] inputArray = userInput.split(" ");
            return tasks.filterTask(inputArray);
        }
        return ui.showMeaninglessCommandDescription();
    }
}
