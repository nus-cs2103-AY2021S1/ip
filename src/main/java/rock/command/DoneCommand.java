package rock.command;

import rock.admin.TaskList;
import rock.exception.RockException;
import rock.tag.CommandTag;
import rock.utility.RockResponse;
import rock.utility.StringToInt;

public class DoneCommand {
    /**
     * get index
     * @param cmd user's command
     * @return index
     * @throws RockException
     */
    private static int parseDone(String cmd, int limit) throws RockException {
        String value = cmd.substring(CommandTag.DONE.length()).trim();
        return StringToInt.stringToIndex(value, limit);
    }

    /**
     * Handle command: done
     * @param cmd
     * @param response
     */
    public static void handle(String cmd, RockResponse response, TaskList tasks) {
        try {
            int index = parseDone(cmd, tasks.getSize());

            response.addNewLines("Nice! I've marked this task as done:");

            tasks.get(index - 1).done();

            response.addNewLines(tasks.get(index - 1).getStatus());
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }
}
