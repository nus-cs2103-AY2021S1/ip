package duke.commands;

import duke.*;

import java.util.ArrayList;

public class TodoCommand extends Command{

    @Override
    public String execute(String userInput, Ui ui, TaskList tasks, boolean isLoading) {
        String response = "";
        try {
            // parse instruction
            String description = Parser.parseAddTodoInstr(userInput);

            // execute
            Task todo = new Todo(description);
            tasks.addTask(todo);
            assert tasks.taskList.contains(todo) : "todo is not added to taskList";

            // show message on UI if not Loading
            if (!isLoading){
                response = ui.showAddedTask(todo, tasks.taskList);
            }
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
        }

        return response;
    }
}
