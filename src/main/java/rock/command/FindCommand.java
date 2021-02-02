package rock.command;

import rock.admin.TaskList;
import rock.exception.RockException;
import rock.tag.CommandTag;
import rock.utility.RockResponse;

public class FindCommand {
    /**
     * get information on find.
     * @param cmd User command
     * @return Pattern needed
     * @throws RockException if invalid command
     */
    private static String parseFind(String cmd) throws RockException {
        String pattern = cmd.substring(CommandTag.FIND.length()).trim();
        if (pattern.length() == 0) {
            throw new RockException("The pattern of a find cannot be empty.");
        }
        return pattern;
    }

    /**
     * handle command: find
     * @param cmd User's command
     * @param response Rock's response that need updated
     */
    public static void handle(String cmd, RockResponse response, TaskList tasks) {
        try {
            String pattern = parseFind(cmd);

            response.addNewLines("Here are the matching tasks in your list:");
            // show find with index
            for (int i = 1; i <= tasks.getSize(); ++i) {
                if (tasks.get(i - 1).getDescription().contains(pattern)) {
                    response.addNewLines(i + "." + tasks.get(i - 1).getStatus());
                }
            }
            /*
            // show find without index
            List<Task> containsList = getArrayList().stream().filter(n -> n.description.contains(pattern))
                                                    .collect(Collectors.toList());
             */
        } catch (RockException ex) {
            response.addNewLines(ex.getMessage());
        }
    }
}
