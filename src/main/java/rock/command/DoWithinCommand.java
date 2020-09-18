package rock.command;

import java.util.ArrayList;
import java.util.Arrays;

import rock.admin.TaskList;
import rock.exception.RockException;
import rock.tag.CommandTag;
import rock.task.DoWithinPeriodTasks;
import rock.utility.RockResponse;

public class DoWithinCommand {
    /**
     * get information on dowithin
     * @param cmd User's command
     * @return List of (Name, from, to)
     * @throws RockException if command invalid
     */
    private static ArrayList<String> parseDoWithin(String cmd) throws RockException {
        String getName = "";
        String from = "";
        String to = "";
        for (int i = CommandTag.DO_WITHIN.length(); i < cmd.length(); ++i) {
            if (cmd.startsWith(CommandTag.BETWEEN_TAG, i)) {
                getName = cmd.substring(CommandTag.DO_WITHIN.length(), i).trim();
                for (int j = i + CommandTag.DO_WITHIN.length(); j < cmd.length(); ++j) {
                    if (!cmd.startsWith(CommandTag.AND_TAG, j)) {
                        continue;
                    }
                    from = cmd.substring(i + CommandTag.DO_WITHIN.length(), j).trim();
                    to = cmd.substring(j + CommandTag.AND_TAG.length()).trim();
                    break;
                }
                break;
            }
        }
        if (getName.length() == 0) {
            throw new RockException("The description of a dowithin cannot be empty.");
        }
        if (from.length() == 0) {
            throw new RockException("The start time of a dowithin cannot be empty.");
        }
        if (to.length() == 0) {
            throw new RockException("The end time of a dowithin cannot be empty.");
        }
        return new ArrayList<>(Arrays.asList(getName, from, to));
    }

    /**
     * handle dowithin
     * @param cmd User's command
     * @param response Rock's response that need updated
     */
    public static void handle(String cmd, RockResponse response, TaskList tasks) {
        try {
            ArrayList<String> arr = parseDoWithin(cmd);
            String getName = arr.get(0);
            String from = arr.get(1);
            String to = arr.get(2);
            DoWithinPeriodTasks tmp = new DoWithinPeriodTasks(getName, from, to);

            response.addNewLines("Got it. I've added this task: ");
            response.addNewLines("  " + tmp.getStatus());

            tasks.add(tmp);

            response.addNewLines("Now you have " + tasks.getSize() + " tasks in the list.");
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }
}
