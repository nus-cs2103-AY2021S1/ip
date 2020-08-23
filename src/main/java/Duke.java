import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    static final String SOCCAT =
    "                 .                         .                             \n" +
            "                ...                       ...                            \n" +
            "               /@@@&*                   (@@@@@&*                         \n" +
            "              /@@@@@@@@&*            /@@@@@@@@@@#                        \n" +
            "             *&@@@@@@@@@@@@*       /@@@@@@@@@@@@&/                       \n" +
            "             %&@@@@@@@@@@@@@@%.  *@@@@@@@@@@@@@@@&/                      \n" +
            "            /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#                     \n" +
            "           /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                   \n" +
            "         .@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                 \n" +
            "        *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.               \n" +
            "       #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,              \n" +
            "      %@@@@@@@@@@@@*,#@@@@@@@@@@@@@@@@@@@%..,&@@@@@@@@@@@@@/             \n" +
            "     (@@@@@@@@@@@%    *@@@@@@@@@@@@@@@@@@    ,@@@@@@@@@@@@@&             \n" +
            "     @@@@@@@@@@@@@@@   &@@@@@@@@@@@@@@@@@@&.  %@@@@@@@@@@@@@(            \n" +
            "    (@@@@@@@@@@@@(     #@@@@@@@@@@@@@@@@@.    /@@@@@@@@@@@@&%            \n" +
            "    *@&&&&&@@@@@@(     /@@@@@@@@@@@@@@@@@.    *@@@@@@&&&&&&&(            \n" +
            "     %&&&&&&&&@@@&     (@@@@@@@@@@@@@@@@@*    #@@@&@&&&&&&&%             \n" +
            "     .&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&,             \n" +
            "       ,&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&(               \n" +
            "         .(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&/                 \n" +
            "             ,#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%*.                    \n" +
            "                    ,/#@@@@@@@@@@@@@@@@@@@@@@#                           \n" +
            "                      #&@@@@@@@@@@@@@@@@@@@&&&&.                         \n" +
            "                     ,&@@@@@@@@@@@@@@@@@@@@@@@@@/                        \n" +
            "                     #@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                      \n" +
            "                    ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(                     \n" +
            "                    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&.                   \n" +
            "                   /@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@&,                  \n" +
            "                   %@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@&.                 \n" +
            "                   @@@@@@@@@@@@@@@@@@@@@%&@@@@@@@@@@@@&&#*,.             \n" +
            "                  *@@@@@@@@@@@@@@@@@@@@@&&@@@@@@@@@@@&&*.,#&&@@@*        \n" +
            "                  ,@@@@@@@@@&@@@@@@@@@@&@@@@@@@@@@@@&&(       .(&@@(     \n" +
            "                    ./#%&&%..,***////**(@@@@@@@@@&&%*            ,@@@*   \n" +
            "                                                                    @@(  \n" +
            "                                                       *%#*@#/@#(&@&.    \n" +
            "                                                  /&@@@%##/&@@@@&,       \n" +
            "                                                @@@@@@#&@@@@@@@@@        \n" +
            "                                           .(@@&#&@&&&&&%@@@@@@@*        \n" +
            "                                           &%&&&%&&&&&&&&&@@#            \n" +
            "                                            .*%@@@%@@%, ..               \n" +
            "                                                  .                      ";
    static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    static final String INDENT = "    ";
    static final String EMPTY_DESCRIPTION_ERROR(String task) {
        return String.format("☹ OOPS!!! The description of %s cannot be empty.", task);
    }
    static final String UNKNOWN_TASK_ERROR = "☹ OOPS!!! I'm sorry, but I don't know what that meows :-(";
    static final String EMPTY_BY_ERROR = "☹ OOPS!!! The deadline cannot be empty.";
    static final String EMPTY_AT_ERROR = "☹ OOPS!!! The event time cannot be empty.";
    static final String NO_SUCH_TASK = "☹ OOPS!!! There is no such task.";
    static final String EMPTY_TASK_INDEX = "☹ OOPS!!! The task index cannot be empty.";

    private List<Task> list;

    public Duke() {
        this.list = new ArrayList<Task>();
    }

    public String addTask(String t) throws DukeEmptyDescriptionException,
            DukeEmptyByException, DukeEmptyAtException,
            DukeUnknownInputException{
        Task toBeAdded;
        String des;
        String[] tokens;
        if (t.startsWith("todo")) {
            try {
                des = t.substring(5);
                toBeAdded = new Todo(des);
                list.add(toBeAdded);
                return "Got it. I've added this task:\n" +
                        INDENT + "  " + toBeAdded + "\n" +
                        INDENT +
                        String.format("Now you have %d tasks in the in the list", list.size());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException(EMPTY_DESCRIPTION_ERROR("todo"));
            }
        } else if (t.startsWith("deadline")) {
            tokens = t.split(" /by ");
            try {
                des = tokens[0].substring(9);
                toBeAdded = new Deadline(des, tokens[1]);
                list.add(toBeAdded);
                return "Got it. I've added this task:\n" +
                        INDENT + "  " + toBeAdded + "\n" +
                        INDENT +
                        String.format("Now you have %d tasks in the in the list", list.size());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException(EMPTY_DESCRIPTION_ERROR("deadline"));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeEmptyByException(EMPTY_BY_ERROR);
            }
        } else if (t.startsWith("event")) {
            try {
                tokens = t.split(" /at ");
                des = tokens[0].substring(6);
                toBeAdded = new Event(des, tokens[1]);
                list.add(toBeAdded);
                return "Got it. I've added this task:\n" +
                        INDENT + "  " + toBeAdded + "\n" +
                        INDENT +
                        String.format("Now you have %d tasks in the in the list", list.size());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException(EMPTY_DESCRIPTION_ERROR("event"));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeEmptyAtException(EMPTY_AT_ERROR);
            }
        } else {
            throw new DukeUnknownInputException(UNKNOWN_TASK_ERROR);
        }
    }

    public String markDone(String md) throws DukeInvalidDoneIndexException, DukeEmptyDoneIndexException {
        int index;
        try {
            String[] tokens = md.split(" ");
            index = Integer.parseInt(tokens[1]);
            Task task = list.get(index - 1);
            task.markAsDone();
            return "Nice! I've marked this task as done: \n" +
                    "      " + task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyDoneIndexException(EMPTY_TASK_INDEX);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidDoneIndexException(NO_SUCH_TASK);
        }
    }

    public String deleteTask(String dt) throws DukeInvalidDeleteIndexException, DukeEmptyDeleteIndexException {
        int index;
        try {
            String[] tokens = dt.split(" ");
            index = Integer.parseInt(tokens[1]);
            Task removedTask = list.remove(index - 1);
            return "Noted. I've removed this task: \n" +
                    "      " + removedTask +
                    String.format("\n     Now you have %d tasks in the list.", list.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyDeleteIndexException(EMPTY_TASK_INDEX);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidDeleteIndexException(NO_SUCH_TASK);
        }
    }

    public void printList() {
        String s = "";
        for (int i = 0; i < list.size(); i ++) {
            s += (i + 1) + "." + list.get(i);
            if (i != list.size() - 1) {
                s += '\n' + INDENT;
            }
        }
        if (s.equals("")) {
            printWindow("There is nothing in the list!");
        } else {
            printWindow(s);
        }
    }

    public static void printWindow(String s) {
        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println(INDENT + s);
        System.out.println(INDENT + HORIZONTAL_LINE);
    }

    public static void printBye() {
        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.print(INDENT + HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();

        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println(duke.SOCCAT);
        System.out.println("    Hello! I'm Soccat Duke\n" +
                "    What do you meow?");
        System.out.println(INDENT + HORIZONTAL_LINE);
        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            if (nextLine.equals("bye")) {
                printBye();
                break;
            } else if (nextLine.equals("list")) {
                duke.printList();
            } else if (nextLine.startsWith("done")) {
                try {
                    printWindow(duke.markDone(nextLine));
                } catch (DukeEmptyDoneIndexException | DukeInvalidDoneIndexException e) {
                    printWindow(e.getMessage());
                }
            } else if (nextLine.startsWith("delete")) {
                try {
                    printWindow(duke.deleteTask(nextLine));
                } catch (DukeInvalidDeleteIndexException | DukeEmptyDeleteIndexException e) {
                    printWindow(e.getMessage());
                }
            } else {
                String printed;
                try {
                    printed = duke.addTask(nextLine);
                    printWindow(printed);
                } catch (DukeUnknownInputException |
                        DukeEmptyByException |
                        DukeEmptyAtException |
                        DukeEmptyDescriptionException e) {
                    printWindow(e.getMessage());
                }
            }
        }
    }
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
}
