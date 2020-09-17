package rock.command;

import rock.admin.TaskList;
import rock.utility.RockResponse;

public class ListCommand {
    /**
     * Handle command: list
     * and update TaskList
     * @param response Rock response
     * @param tasks TaskList need to be updated
     */
    public static void handle(RockResponse response, TaskList tasks) {
        if (tasks.getSize() == 0) {
            response.addNewLines("You don't have any task left!");
            return;
        }
        response.addNewLines("Here are the tasks in your list:");
        // list with index
        for (int i = 1; i <= tasks.getSize(); ++i) {
            response.addNewLines(i + "." + tasks.get(i - 1).getStatus());
        }
        // list without index
        // tasks.getArrayList().forEach((n) -> System.out.println(n.getStatus()));
    }
}
