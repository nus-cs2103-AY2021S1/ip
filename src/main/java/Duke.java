import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

public class Duke {

    public static void main(String[] args) {
        greet();
        userCommand();
        exit();
    }

    public static void greet() {
        String logo = "       \\:.             .:/\n" +
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
        System.out.print(logo);
        System.out.println("Pikachu: Hello, I am Pikachu! My pika service creates a to-do list for you!\n\n" +
                "1. type 'list' and I list all that you said, along if it is completed\n" +
                "2. type either 'event', 'deadline', 'todo', followed by the task!\n" +
                "   2.1. if 'event', type the task followed by a '/at <duration>' to indicate duration\n" +
                "   2.2. if 'deadline', type the task followed by a '/by <deadline>' to indicate deadline\n" +
                "   2.3. if 'delete' type the task followed by a number within the list index to delete\n" +
                "   2.4. if 'done' type the task followed by a number within the list index to mark it\n" +
                "3. type 'done x' where x is the index of the item you want to be indicated done\n" +
                "4. or you can say 'bye' to end us </3 Type your command:");
    }

    public static void userCommand() {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<Task> storage = new ArrayList<>();

        while (!input.equals("bye")) {
            try {
                String command = input.split(" ")[0];
                int size = storage.size();
                if (command.equals("list")) {
                    commandList(size, storage);
                } else if (command.equals("done") || command.equals("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    commandDoneDelete(index, storage, command);
                } else if (commandTaskChecker(command)) {
                    String message = input.substring(command.length());
                    commandTasks(storage, command, message);
                } else {
                    String errorMessage = "Command is wrong. Please start with delete, list, done, deadline, todo or event.";
                    throw new DukeCommandException(errorMessage);
                }
            } catch (DukeCommandException | DukeIndexException | DukeTaskException | DukeListException e) {
                System.out.print(String.format("  ERROR: %s\n", e.getMessage()));
            } catch (DateTimeParseException e) {
                System.out.print(String.format("  ERROR: INPUT DATE TIME FORMAT IS WRONG. \n"));
            }
            input = sc.nextLine();
        }
    }

    public static void commandList(int index, List<Task> storage) throws DukeListException {
        if (storage.size() != 0) {
            System.out.print("Retrieving your list, patient ah!\n");
            for (int i = 0; i < storage.size(); i++) {
                System.out.print(String.format("   %2d. %s\n", i + 1, storage.get(i)));
            }
        } else {
            throw new DukeListException("Your list is empty.");
        }
    }

    public static void commandDoneDelete(int index, List<Task> storage, String mode) throws DukeIndexException {
        if (index > storage.size() - 1 || index < 0) {
            String errorMessage = "Wrong list number input. " +
                    "Please put a number between 1 and " + storage.size();
            throw new DukeIndexException(errorMessage);
        }

        if (mode.equals("done")) {
            storage.get(index).makeDone();
            System.out.print(String.format("    Swee la, task done liao!:\n" +
                    "       %s\n", storage.get(index)));
        } else {
            System.out.print(String.format("    Delete liao boss:\n" +
                    "       %s\n    Remaining Tasks: %d\n", storage.get(index), storage.size()));
            storage.remove(index);
        }

    }


    public static void commandTasks(List<Task> storage, String tag, String message) throws DukeTaskException {

        String[] parsedMessage = null;
        Task newTask = null;
        int size = storage.size();

        try {

            if (message.isEmpty()) {
                throw new IndexOutOfBoundsException();
            } else if (tag.equals("todo")) {
                newTask = new Todo(message);
            } else if (tag.equals("deadline")) {
                parsedMessage = message.split("/by ");
                newTask = new Deadline(parsedMessage[0], parsedMessage[1]);
            } else if (tag.equals("event")) {
                parsedMessage = message.split("/at ");
                newTask = new Deadline(parsedMessage[0], parsedMessage[1]);
            }

            storage.add(newTask);

            System.out.print("    Steady! I add... wait ah...\n");
            System.out.print(String.format("        ADDED: %s\n", newTask));
            System.out.print(String.format("    Now you got %d tasks in list, " +
                    "don't procrastinate hor\n", size));
        } catch (IndexOutOfBoundsException e) {
            String errorMessage = "You might have left your message or duration empty.";
            throw new DukeTaskException(errorMessage);
        }
    }

    public static boolean commandTaskChecker(String command) throws DukeCommandException {
        return command.equals("event") || command.equals("deadline") || command.equals("todo");
    }

    public static void exit() {
        String exitMessage = "Pikachu: Pika byebye! THUNDERBOLT!\n";
        String bolt = "\n" +
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
        System.out.print(exitMessage + bolt);
    }

}
