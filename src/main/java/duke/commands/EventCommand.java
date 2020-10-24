package duke.commands;

import duke.*;

import java.time.LocalDate;
import java.util.HashMap;

public class EventCommand extends Command {

    @Override
    public String execute(String userInput, Ui ui, TaskList tasks, boolean isLoading) {
        String response = "";
        try {
            // parse instruction
            HashMap<String, Object> parsedData = Parser.parseAddEventInstr(userInput);
            String description = (String) parsedData.get("description");
            LocalDate localTime = (LocalDate) parsedData.get("time");

            // execute
            Task event = new Event(description, localTime);
            tasks.addTask(event);
            assert tasks.taskList.contains(event) : "event is not added to taskList";

            // show message on UI if not Loading
            if (!isLoading) {
                response = ui.showAddedTask(event, tasks.taskList);
            }
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
        }

        return response;
    }
}
