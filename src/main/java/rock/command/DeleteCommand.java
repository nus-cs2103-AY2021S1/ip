package rock.command;

import rock.admin.TaskList;
import rock.exception.RockException;
import rock.tag.CommandTag;
import rock.utility.RockResponse;
import rock.utility.StringToInt;

public class DeleteCommand {
    /**
     * get information on delete.
     * @param cmd User's command
     * @return index
     * @throws RockException If the index invalid
     */
    private static int parseDelete(String cmd, int limit) throws RockException {
        String value = cmd.substring(CommandTag.DELETE.length()).trim();
        return StringToInt.stringToIndex(value, limit);
    }

    /**
     * handle command: delete.
     * @param cmd User's command
     * @param response Rock's response that need updated
     */
    public static void handle(String cmd, RockResponse response, TaskList tasks) {
        try {
            int index = parseDelete(cmd, tasks.getSize());

            response.addNewLines("Noted. I've removed this task: ");
            response.addNewLines(tasks.get(index - 1).getStatus());

            tasks.remove(index - 1);

            response.addNewLines("Now you have " + tasks.getSize() + " tasks in the list.");
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }
}
