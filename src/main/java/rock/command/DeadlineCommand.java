package rock.command;

import java.util.ArrayList;
import java.util.Arrays;

import rock.admin.TaskList;
import rock.exception.RockException;
import rock.tag.CommandTag;
import rock.task.Deadline;
import rock.utility.DateFormatter;
import rock.utility.RockResponse;

public class DeadlineCommand {
    /**
     * get information of deadline.
     * @param cmd User's command
     * @return list of (getName, getDeadline)
     * @throws RockException If the command invalid
     */
    private static ArrayList<String> parseDeadline(String cmd) throws RockException {
        String getName = "";
        String getDeadline = "";
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.startsWith(CommandTag.BY_TAG, i)) {
                getName = cmd.substring(CommandTag.DEADLINE.length(), i).trim();
                getDeadline = cmd.substring(i + CommandTag.BY_TAG.length()).trim();
                break;
            }
        }
        if (getName.length() == 0) {
            throw new RockException("The description of a deadline cannot be empty.");
        }
        if (getDeadline.length() == 0) {
            throw new RockException("The time of a deadline cannot be empty.");
        }
        return new ArrayList<>(Arrays.asList(getName, getDeadline));
    }

    /**
     * Handle command: deadline.
     * @param cmd User's command
     * @param response Rock's response that need updated
     */
    public static void handle(String cmd, RockResponse response, TaskList tasks) {
        try {
            ArrayList<String> arr = parseDeadline(cmd);
            String getName = arr.get(0);
            String getDeadline = arr.get(1);
            getDeadline = DateFormatter.formatDate(getDeadline);
            Deadline deadline = new Deadline(getName, getDeadline);

            response.addNewLines("Got it. I've added this task: ");
            response.addNewLines("  " + deadline.getStatus());

            tasks.add(deadline);

            response.addNewLines("Now you have " + tasks.getSize() + " tasks in the list.");
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }
}
