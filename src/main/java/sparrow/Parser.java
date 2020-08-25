package sparrow;

import java.time.format.DateTimeParseException;

/**
 * Parses user input and calls the relevant methods.
 */
public class Parser {
    private TaskList tasks;
    private Storage storage;

    /**
     * Creates a Parser that handles user input.
     * @param tasks TaskList for methods to be called on
     * @param storage Storage and retrieval of TaskList
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Returns boolean to tell program whether to exit.
     * Parses a line of user input and calls relevant methods.
     * @param commandLine entire line of user input to be handled
     * @return whether to exit program
     */
    public boolean parse(String commandLine) {
        String[] commandArr = commandLine.trim().split("\\s+", 2);
        String command = commandArr[0];
        try {
            switch (command) {
            case "bye":
                Ui.reply("Bye. Hope t' see ye again soon!");
                return false;
            case "list":
                if (commandArr.length == 1) {
                    tasks.displayList();
                    break;
                } else if (commandArr.length == 2) {
                    try {
                        tasks.filterList(commandArr[1]);
                        tasks.displayList();
                    } catch (DateTimeParseException e) {
                        System.out.println(Sparrow.standardExceptionMessage() + "Please enter a date in this format: yyyy-mm-dd");
                    }
                    break;
                }
            case "done":
                try {
                    tasks.markAsDone(commandArr[1]);
                    storage.saveTaskList(tasks.getTasks());
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new MissingTaskNumberException("No task number passed to done command.", e);
                }
            case "todo":
                try {
                    tasks.addTask("todo", commandArr[1]);
                    storage.saveTaskList(tasks.getTasks());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyTodoDescriptionException("No description provided for todo.", e);
                }
                break;
            case "deadline":
                try {
                    tasks.addTask("deadline", commandArr[1]);
                    storage.saveTaskList(tasks.getTasks());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDeadlineDescriptionException("No description provided for deadline.", e);
                }
                break;
            case "event":
                try {
                    tasks.addTask("event", commandArr[1]);
                    storage.saveTaskList(tasks.getTasks());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyEventDescriptionException("No description provided for event.", e);
                }
                break;
            case "delete":
                try {
                    tasks.deleteTask(commandArr[1]);
                    storage.saveTaskList(tasks.getTasks());
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new MissingTaskNumberException("No task number passed to delete command.", e);
                }
            default:
                throw new UnknownCommandException(commandArr[0]);
            }
        } catch(MissingTaskNumberException e){
            System.out.println(Sparrow.standardExceptionMessage() + "️ Please enter a task number after the \"done\"/\"delete\" command.");
        } catch(EmptyTodoDescriptionException e){
            System.out.println(Sparrow.standardExceptionMessage() + "️ The description of a todo cannot be empty.");
        } catch(EmptyDeadlineDescriptionException e){
            System.out.println(Sparrow.standardExceptionMessage() + "️ The description of a deadline cannot be empty.");
        } catch(EmptyEventDescriptionException e){
            System.out.println(Sparrow.standardExceptionMessage() + "️ The description of an event cannot be empty.");
        } catch(UnknownCommandException e){
            System.out.println(Sparrow.standardExceptionMessage() + "️ What be the meaning of this?");
        }
        return true;
    }
}
