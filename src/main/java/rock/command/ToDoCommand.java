package rock.command;

import rock.admin.TaskList;
import rock.exception.RockException;
import rock.tag.CommandTag;
import rock.task.Todo;
import rock.utility.RockResponse;

public class ToDoCommand {
    /**
     * get description of the taks
     * @param cmd User's command
     * @return getName
     * @throws RockException if invalid command
     */
    private static String parseToDo(String cmd) throws RockException {
        String getName = cmd.substring(CommandTag.TODO.length()).trim();
        if (getName.length() == 0) {
            throw new RockException("The description of a todo cannot be empty.");
        }
        return getName;
    }

    /**
     * Handle command: todo
     * @param cmd User's command
     * @param response Rock's response that need updated
     */
    public static void handle(String cmd, RockResponse response, TaskList tasks) {
        try {
            String getName = parseToDo(cmd);
            Todo todo = new Todo(getName);

            response.addNewLines("Got it. I've added this task: ");
            response.addNewLines("  " + todo.getStatus());

            tasks.add(todo);

            response.addNewLines("Now you have " + tasks.getSize() + " tasks in the list.");
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }
}
