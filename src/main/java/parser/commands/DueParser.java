package parser.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import data.task.Task;
import tasklist.TaskList;


public class DueParser {


    /**
     * parses user input
     * @param input Scanner format of user input
     * @param tl duke's tasklist
     * @return lists all due tasks
     */
    public static String parse(Scanner input, TaskList tl) {
        assert input != null;
        try {
            LocalDate dueDate = LocalDate.parse(input.nextLine().trim());
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
            StringBuilder response = new StringBuilder("These tasks are due: \n");
            if (tl.getDateStorage().get(dueDate.format(dateFormat)) != null) {
                for (Task task: tl.getDateStorage().get(dueDate.format(dateFormat))) {
                    response.append(task).append("\n");
                }
            } else {
                return "No tasks are due.\n";
            }
            return response.toString();
        } catch (Exception e) {
            // time can't be parsed
            return "Error: " + e.getMessage();
        }
    }
}
