package duke;

import java.time.LocalDate;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input and performs corresponding actions
     * through ui, taskList and storage.
     * @param userInput The line of text keyed in by the user.
     * @param ui User interface displayed by the application.
     * @param taskList Collection of the list of tasks.
     * @param storage Storage to save and load files.
     */
    public static void parseUserInput(String userInput, Ui ui, TaskList taskList, Storage storage) {
        if (userInput.startsWith("bye")) {
            ui.printMessage("Bye! See you next time :)");
            System.exit(0);
        } else if (userInput.startsWith("list")) {
            String message = "";
            for (int i = 0; i < taskList.getTaskList().size(); i++) {
                message += (i + 1) + ": " + taskList.getTask(i) + "\n";
            }
            ui.printMessage(message);
        } else if (userInput.matches("done ([0-9]+)")) {
            int number = Integer.parseInt(userInput.split(" ")[1]);
            if (number > taskList.getTaskList().size()) {
                ui.printMessage("duke.Task not found please choose another number!");
            }
            else if (number < 100 && number > 0) {
                taskList.getTask(number - 1).markAsDone();
                ui.printMessage("This task is done, great job!\n" + taskList.getTask(number - 1));
            }
        } else if (userInput.startsWith("todo")) {
            String description = userInput.replace("todo", "").trim();
            if (description.length() == 0) {
                System.out.println(new ToDoMissingDescriptionException());
            } else {
                Task task = new ToDo(description);
                taskList.addTask(task);
                storage.save(task);
                ui.printMessage("Added: " + task
                        + String.format("\nNow you have %d tasks in the list", taskList.getTaskList().size()));
            }
        } else if (userInput.startsWith("deadline")) {
            if (userInput.contains("/by")) {
                String description = userInput.replace("deadline ", "").split("/by")[0].trim();
                String dueDate = userInput.replace("deadline", "").split("/by")[1].trim();

                Task task = new Deadline(description, LocalDate.parse(dueDate));
                taskList.addTask(task);
                storage.save(task);
                ui.printMessage("Added " + task
                        + String.format("\nNow you have %d tasks in the list", taskList.getTaskList().size()));
            } else {
                System.out.println(new DeadlineMissingDateException());
            }
        } else if (userInput.startsWith("event")) {
            if (userInput.contains("/at")) {
                String description = userInput.replace("event ", "").split("/at")[0].trim();
                String time = userInput.replace("event ", "").split("/at")[1].trim();
                System.out.println(time);
                Task task = new Event(description, LocalDate.parse(time));
                taskList.addTask(task);
                storage.save(task);
                ui.printMessage("Added " + task
                        + String.format("\nNow you have %d tasks in the list", taskList.getTaskList().size()));
            } else {
                System.out.println(new EventMissingDateException());
            }
        } else if (userInput.startsWith("delete")) {
            int taskToDelete = Integer.parseInt(userInput.replace("delete ", "")) - 1;

            ui.printMessage("I have removed this task:\n" + taskList.getTask(taskToDelete)
                    + String.format("\nNow you have %d tasks in the list", taskList.getTaskList().size() - 1));
            taskList.deleteTask(taskToDelete);
        } else {
            System.out.println(new InvalidInputException());
        }
    }
}
