package duke.commands;

import duke.*;

import java.time.LocalDate;
import java.util.HashMap;

public class DeadlineCommand extends Command{

    @Override
    public String execute(String userInput, Ui ui, TaskList tasks, boolean isLoading) {
        String response = "";
        try {
            // parse instruction
            HashMap<String, Object> parsedData = Parser.parseAddDeadlineInstr(userInput);
            String description = (String) parsedData.get("description");
            LocalDate localTime = (LocalDate) parsedData.get("time");

            // execute
            Task deadline = new Deadline(description, localTime);
            tasks.addTask(deadline);
            assert tasks.taskList.contains(deadline) : "deadline is not added to taskList";

            // show message on UI if not Loading
            if (!isLoading) {
                response = ui.showAddedTask(deadline, tasks.taskList);
            }
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
        }

        return response;
    }
}
