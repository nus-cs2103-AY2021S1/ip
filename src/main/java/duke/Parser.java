package duke;

import java.time.LocalDate;

public class Parser {

    public static void parseUserInput(String userInput, Ui ui, TaskList taskList, Storage storage) {
        if (userInput.startsWith("bye")) {
            ui.printMessage("Bye! See you next time :)");
            System.exit(0);
        } else if (userInput.startsWith("list")) {
            String message = "";
            for (int i = 0; i < taskList.getTasks().size(); i++) {
                message += (i + 1) + ": " + taskList.getTask(i) + "\n";
            }
            ui.printMessage(message);
        } else if (userInput.matches("done ([0-9]+)")) {
            int number = Integer.parseInt(userInput.split(" ")[1]);
            if (number > taskList.getTasks().size()) {
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
                storage.saveToFile(task);
                ui.printMessage("Added: " + task
                        + String.format("\nNow you have %d tasks in the list", taskList.getTasks().size()));
            }
        } else if (userInput.startsWith("deadline")) {
            if (userInput.contains("/by")) {
                String description = userInput.replace("deadline ", "").split("/by")[0].trim();
                String dueDate = userInput.replace("deadline", "").split("/by")[1].trim();

                Task task = new Deadline(description, LocalDate.parse(dueDate));
                taskList.addTask(task);
                storage.saveToFile(task);
                ui.printMessage("Added " + task
                        + String.format("\nNow you have %d tasks in the list", taskList.getTasks().size()));
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
                storage.saveToFile(task);
                ui.printMessage("Added " + task
                        + String.format("\nNow you have %d tasks in the list", taskList.getTasks().size()));
            } else {
                System.out.println(new EventMissingDateException());
            }
        } else if (userInput.startsWith("delete")) {
            int taskToDelete = Integer.parseInt(userInput.replace("delete ", "")) - 1;

            ui.printMessage("I have removed this task:\n" + taskList.getTask(taskToDelete)
                    + String.format("\nNow you have %d tasks in the list", taskList.getTasks().size() - 1));
            taskList.deleteTask(taskToDelete);
        } else if (userInput.startsWith("find")) {
            String keyword = userInput.replace("find ", "");
            String message = "Here are the matching tasks in your list\n";
            for (Task task : taskList.getTasks()) {
                if (task.description.contains(keyword)) {
                    message += task.toString() + "\n";
                }
            }
            ui.printMessage(message);
        }
        else {
            System.out.println(new InvalidInputException());
        }
    }
}
