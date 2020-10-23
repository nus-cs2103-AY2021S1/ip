package duke.commands;

import duke.*;

import java.time.LocalDate;
import java.util.HashMap;

public class PriorityCommand extends Command {

    @Override
    public String execute(String userInput, Ui ui, TaskList tasks, boolean isLoading) {
        // ex) priority 1 to task 1 (priority levels are 1, 2, and 3)
        String response = "";
        try {
            // parse instruction
            HashMap<String, Object> parsedData = Parser.parseSetPriorityInstr(userInput);
            Integer priorityLevel = (Integer) parsedData.get("priorityLevel");
            Integer taskIndex = (Integer) parsedData.get("taskIndex");

            // execute
            Task task = tasks.getTask(taskIndex);
            task.setPriorityLevel(priorityLevel);
            assert task.getPriorityLevel() != null : "priority is not added to the task";

            // show message on UI if not Loading
            if (!isLoading) {
                response = ui.showSetPriorityOfTask(task);
            }
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
        }

        return response;
    }

}
