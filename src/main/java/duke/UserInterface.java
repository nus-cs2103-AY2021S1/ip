package duke;

/**
 * Class to represent the User Interface of which a user interacts with.
 * @author Kor Ming Soon
 */
public class UserInterface {

    private static final String LOGO =
            "       \\:.             .:/\n" +
            "        \\``._________.''/ \n" +
            "         \\             / \n" +
            " .--.--, / .':.   .':. \\\n" +
            "/__:  /  | '::' . '::' |\n" +
            "   / /   |`.   ._.   .'|\n" +
            "  / /    |.'         '.|\n" +
            " /___-_-,|.\\  \\   /  /.|\n" +
            "      // |''\\.;   ;,/ '|\n" +
            "      `==|:=         =:|\n" +
            "         `.          .'\n" +
            "           :-._____.-:\n" +
            "          `''       `''\n";

    private static final String THUNDERBOLT =
            "                  .-~*~--,.   .-.\n" +
            "          .-~-. ./OOOOOOOOO\\.'OOO`9~~-.\n" +
            "        .`OOOOOO.OOM.OLSONOOOOO@@OOOOOO\\\n" +
            "       /OOOO@@@OO@@@OO@@@OOO@@@@@@@@OOOO`.\n" +
            "       |OO@@@WWWW@@@@OOWWW@WWWW@@@@@@@OOOO).\n" +
            "     .-'OO@@@@WW@@@W@WWWWWWWWOOWW@@@@@OOOOOO}\n" +
            "    /OOO@@O@@@@W@@@@@OOWWWWWOOWOO@@@OOO@@@OO|\n" +
            "   lOOO@@@OO@@@WWWWWWW\\OWWWO\\WWWOOOOOO@@@O.'\n" +
            "    \\OOO@@@OOO@@@@@@OOW\\     \\WWWW@@@@@@@O'.\n" +
            "     `,OO@@@OOOOOOOOOOWW\\     \\WWWW@@@@@@OOO)\n" +
            "      \\,O@@@@@OOOOOOWWWWW\\     \\WW@@@@@OOOO.'\n" +
            "        `~c~8~@@@@WWW@@W\\       \\WOO|\\UO-~'\n" +
            "             (OWWWWWW@/\\W\\    ___\\WO)\n" +
            "               `~-~''     \\   \\WW=*'\n" +
            "                         __\\   \\\n" +
            "                         \\      \\\n" +
            "                          \\    __\\\n" +
            "                           \\  \\\n" +
            "                            \\ \\\n" +
            "                             \\ \\\n" +
            "                              \\\\\n" +
            "                               \\\\\n" +
            "                                \\\n" +
            "                                 \\\n";

    private static final String BORDER = "      ";

    private static final String ERROR = "    ERROR: ";

    private static final String NEWLINE = "\n";

    /**
     * To print out the task that is just added to the list.
     * @param task Task of which to be added.
     * @param totalNumber The prevailing number after a task is added.
     */
    public void printAddTask(String task, int totalNumber) {
        System.out.print(BORDER + "Steady! I add... wait ah.." + NEWLINE);
        System.out.print(BORDER + BORDER + "ADDED: " + task + NEWLINE);
        System.out.print(BORDER + "Now you got " + totalNumber + " tasks" + NEWLINE);
    }

    /**
     * Preamble while the chat bot retrieves list of the tasks.
     */
    public void listTask() {
        System.out.print(BORDER + "Retrieving your list, patient ah!\n");
    }

    /**
     * To print out the list of tasks in an ordered fashion.
     * @param listNumber Index of the tasking in the list.
     * @param task The details of the task itself.
     */
    public void printTask(int listNumber, String task) {
        String toPrint = String.format("%2d. %s\n", listNumber, task);
        System.out.print(BORDER + toPrint);
    }

    /**
     * To print the preamble as well as the details of the task marked as done.
     * @param task Task of which to be marked as done.
     */
    public void printDone(String task) {
        System.out.print(BORDER + "Swee la, task done liao:" + NEWLINE);
        System.out.print(BORDER + BORDER + task + NEWLINE);
    }

    /**
     * Preamble as well as printing of the sequence of deletion.
     * @param task Task to be deleted.
     * @param remaining Remaining number of tasks after deletion.
     */
    public void printDelete(String task, int remaining) {
        System.out.print(BORDER + "Delete liao boss:" + NEWLINE);
        System.out.print(BORDER + "Remaining Tasks: " + remaining + NEWLINE);
    }

    /**
     * Standard error message to be printed.
     * @param errorMessage The details of the error message itself.
     */
    public void printError(String errorMessage) {
        System.out.print(ERROR + errorMessage + NEWLINE);
    }

    /**
     * Preamble for the initialisation of the Pikachu Chatbot.
     */
    public void welcomeMessage() {
        String startingMessage = "Pikachu: Hello, I am Pikachu! My pika service creates a to-do list for you!\n\n" +
                "1. type 'list' and I list all that you said, along if it is completed\n" +
                "2. type either 'event', 'deadline', 'todo', followed by the task!\n" +
                "   2.1. if 'event', type the task followed by a '/at <duration>' to indicate duration\n" +
                "   2.2. if 'deadline', type the task followed by a '/by <deadline>' to indicate deadline\n" +
                "   2.3. if 'delete' type the task followed by a number within the list index to delete\n" +
                "   2.4. if 'done' type the task followed by a number within the list index to mark it\n" +
                "3. type 'done x' where x is the index of the item you want to be indicated done\n" +
                "4. or you can say 'bye' to end us </3 Type your command:\n";
        System.out.print(LOGO + startingMessage);
    }

    /**
     * Exit messsage when the chat bot terminates.
     */
    public void exitMessage() {
        String exitMessage = "Pikachu: Pika byebye! THUNDERBOLT!\n";
        System.out.print(THUNDERBOLT + exitMessage);
    }

}
