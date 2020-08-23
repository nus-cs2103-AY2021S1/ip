import constants.DukeConstants;
import services.DukeService;

import java.util.*;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);

    private static final DukeService dukeService = new DukeService();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String input;
        dukeService.printResponse(DukeConstants.INITIAL_RESPONSE);

        while (true) {
            input = scanner.nextLine();
            if (input.equals(DukeConstants.EXIT_INPUT)) {
                dukeService.printResponse(DukeConstants.EXIT_RESPONSE);
                break;
            } else if (input.equals(DukeConstants.LIST_COMMAND)) {
                dukeService.printList();
            } else if (input.split("\\s+")[0].equals(DukeConstants.DONE_COMMAND)) {
                dukeService.handleDoneCommand(input);
            } else if (input.split("\\s+")[0].equals(DukeConstants.DELETE_COMMAND)) {
                dukeService.handleDeleteCommand(input);
            }
            else if (input.split("\\s+")[0].equals(DukeConstants.TODO_COMMAND)) {
                dukeService.addToDoEvent(input);
            } else if (input.split("\\s+")[0].equals(DukeConstants.DEADLINE_COMMAND)) {
                dukeService.addDeadlineEvent(input);
            } else if (input.split("\\s+")[0].equals(DukeConstants.EVENT_COMMAND)) {
                dukeService.addEventTask(input);
            }
            else {
                dukeService.printResponse("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
