package rock.command;

import java.util.ArrayList;
import java.util.Arrays;

import rock.admin.TaskList;
import rock.exception.RockException;
import rock.tag.CommandTag;
import rock.task.Event;
import rock.utility.DateFormatter;
import rock.utility.RockResponse;

public class EventCommand {
    /**
     * get information on event.
     * @param cmd User's command
     * @return List of (Name, Time)
     * @throws RockException if invalid command
     */
    private static ArrayList<String> parseEvent(String cmd) throws RockException {
        String getName = "";
        String getTime = "";
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.startsWith(CommandTag.AT_TAG, i)) {
                getName = cmd.substring(CommandTag.EVENT.length(), i).trim();
                getTime = cmd.substring(i + CommandTag.AT_TAG.length()).trim();
                break;
            }
        }
        if (getName.length() == 0) {
            throw new RockException("The description of a event cannot be empty.");
        }
        if (getTime.length() == 0) {
            throw new RockException("The time of a event cannot be empty.");
        }
        return new ArrayList<>(Arrays.asList(getName, getTime));
    }

    /**
     * Handle command: event.
     * @param cmd User's command
     * @param response Rock's response that need updated
     */
    public static void handle(String cmd, RockResponse response, TaskList tasks) {
        try {
            ArrayList<String> arr = parseEvent(cmd);
            String getName = arr.get(0);
            String getTime = arr.get(1);
            getTime = DateFormatter.formatDate(getTime);
            Event event = new Event(getName, getTime);

            response.addNewLines("Got it. I've added this task: ");
            response.addNewLines("  " + event.getStatus());

            tasks.add(event);

            response.addNewLines("Now you have " + tasks.getSize() + " tasks in the list.");
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }
}
