package main.java.duke;

import java.io.FileNotFoundException;

/**
 * Parser deals with users' commands, differentiates each type of task.
 */
public class Parser {
    /**
     * Command enum lists out all the types of commands.
     */
    public enum Command {
        BYE("bye"), LIST("list"), DONE("done"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), DELETE("delete"), FILTER("filter");
        public String value;
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
    public static boolean execute(TaskList tasks, Ui ui, Storage storage, String userInput) throws FileNotFoundException {
        if (userInput.equals(Command.BYE.value)) {
            ui.sayGoodbye();
            return true;
            //isExit = true;

        } else if (userInput.equals(Command.LIST.value)) {
            tasks.showTasks();

        }  else if (userInput.startsWith(Command.DONE.value)) {
            try {
                int taskPosition = Integer.parseInt(userInput.substring(5));
                tasks.setDone(taskPosition);

            } catch (StringIndexOutOfBoundsException e) {
                ui.showInvalidFormatCommandDescription();
            } catch (NumberFormatException e) {
                ui.showInvalidFormatCommandDescription();
            }

        } else if (userInput.startsWith(Command.TODO.value)) {
            try {
                String description = userInput.trim().substring(5);
                tasks.addToDo(description);
                storage.saveTasks(tasks.getTasksList());
            } catch (StringIndexOutOfBoundsException e) {
                ui.showInvalidFormatCommandDescription();
            }

        } else if (userInput.startsWith(Command.DEADLINE.value)) {
            try {
                int spacePosition = userInput.indexOf(" ");
                int keywordPosition = userInput.indexOf("/by");
                String description = userInput.substring(spacePosition + 1, keywordPosition - 1);
                String by = userInput.substring(keywordPosition + 4);
                tasks.addDeadline(description,by);
                storage.saveTasks(tasks.getTasksList());
            } catch (StringIndexOutOfBoundsException e) {
                ui.showInvalidFormatCommandDescription();
            }

        } else if (userInput.startsWith(Command.EVENT.value)) {
            try {
                int spacePosition = userInput.indexOf(" ");
                int keywordPosition = userInput.indexOf("/at");
                String description = userInput.substring(spacePosition + 1, keywordPosition - 1);
                String at = userInput.substring(keywordPosition + 4);
                tasks.addEvent(description, at);
                storage.saveTasks(tasks.getTasksList());
            } catch (StringIndexOutOfBoundsException e) {
                ui.showInvalidFormatCommandDescription();
            }

        } else if (userInput.startsWith(Command.DELETE.value)) {
            try {
                int taskPosition = Integer.parseInt(userInput.substring(7));
                tasks.deleteTask(taskPosition);
                storage.saveTasks(tasks.getTasksList());

            } catch (StringIndexOutOfBoundsException e) {
                ui.showInvalidFormatCommandDescription();
            }

        } else if (userInput.startsWith(Command.FILTER.value)) {
            String[] inputArray = userInput.split(" ");
            tasks.filterTask(inputArray);

        } else {
            ui.showMeaninglessCommandDescription();
        }
        return false;
    }
}
