package duke;

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

    private static final String thunderbolt =
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

    private static final String newLine = "\n";

    public void printAddTask(String task, int totalNumber) {
        System.out.print(BORDER + "Steady! I add... wait ah.." + newLine);
        System.out.print(BORDER + BORDER + "ADDED: " + task + newLine);
        System.out.print(BORDER + "Now you got " + totalNumber + " tasks" + newLine);
    }

    public void listTask() {
        System.out.print(BORDER + "Retrieving your list, patient ah!\n");
    }

    public void printTask(int listNumber, String task) {
        String toPrint = String.format("%2d. %s\n", listNumber, task);
        System.out.print(BORDER + toPrint);
    }

    public void printDone(String task) {
        System.out.print(BORDER + "Swee la, task done liao:" + newLine);
        System.out.print(BORDER + BORDER + task + newLine);
    }

    public void printDelete(String task, int remaining) {
        System.out.print(BORDER + "Delete liao boss:" + newLine);
        System.out.print(BORDER + "Remaining Tasks: " + remaining + newLine);
    }

    public void printError(String errorMessage) {
        System.out.print(ERROR + errorMessage + newLine);
    }

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

    public void exitMessage() {
        String exitMessage = "Pikachu: Pika byebye! THUNDERBOLT!\n";
        System.out.print(thunderbolt + exitMessage);
    }

}
