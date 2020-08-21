import java.io.File;
import java.util.Scanner;

public class Duke {
    static final String SOCCAT =
            "                                              .                         .                             \n" +
            "                                             ...                       ...                            \n" +
            "                                            /@@@&*                   (@@@@@&*                         \n" +
            "                                           /@@@@@@@@&*            /@@@@@@@@@@#                        \n" +
            "                                          *&@@@@@@@@@@@@*       /@@@@@@@@@@@@&/                       \n" +
            "                                           %&@@@@@@@@@@@@@@%.  *@@@@@@@@@@@@@@@&/                     \n" +
            "                                          /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#                    \n" +
            "                                        /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                   \n" +
            "                                      .@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                 \n" +
            "                                     *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.               \n" +
            "                                    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,              \n" +
            "                                   %@@@@@@@@@@@@*,#@@@@@@@@@@@@@@@@@@@%..,&@@@@@@@@@@@@@/             \n" +
            "                                  (@@@@@@@@@@@%    *@@@@@@@@@@@@@@@@@@    ,@@@@@@@@@@@@@&             \n" +
            "                                  @@@@@@@@@@@@@@@   &@@@@@@@@@@@@@@@@@@&.  %@@@@@@@@@@@@@(            \n" +
            "                                 (@@@@@@@@@@@@(     #@@@@@@@@@@@@@@@@@.    /@@@@@@@@@@@@&%            \n" +
            "                                 *@&&&&&@@@@@@(     /@@@@@@@@@@@@@@@@@.    *@@@@@@&&&&&&&(            \n" +
            "                                  %&&&&&&&&@@@&     (@@@@@@@@@@@@@@@@@*    #@@@&@&&&&&&&%             \n" +
            "                                  .&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&,             \n" +
            "                                    ,&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&(               \n" +
            "                                      .(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&/                 \n" +
            "                                          ,#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%*.                    \n" +
            "                                                 ,/#@@@@@@@@@@@@@@@@@@@@@@#                           \n" +
            "                                                   #&@@@@@@@@@@@@@@@@@@@&&&&.                         \n" +
            "                                                  ,&@@@@@@@@@@@@@@@@@@@@@@@@@/                        \n" +
            "                                                  #@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                      \n" +
            "                                                 ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(                     \n" +
            "                                                 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&.                   \n" +
            "                                                /@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@&,                  \n" +
            "                                                %@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@&.                 \n" +
            "                                                @@@@@@@@@@@@@@@@@@@@@%&@@@@@@@@@@@@&&#*,.             \n" +
            "                                               *@@@@@@@@@@@@@@@@@@@@@&&@@@@@@@@@@@&&*.,#&&@@@*        \n" +
            "                                               ,@@@@@@@@@&@@@@@@@@@@&@@@@@@@@@@@@&&(       .(&@@(     \n" +
            "                                                 ./#%&&%..,***////**(@@@@@@@@@&&%*            ,@@@*   \n" +
            "                                                                                                 @@(  \n" +
            "                                                                                    *%#*      &@&.    \n" +
            "                                                                               /&@@@%##/&@@@@&,       \n" +
            "                                                                             @@@@@@#&@@@@@@@@@        \n" +
            "                                                                        .(@@&#&@&&&&&%@@@@@@@*        \n" +
            "                                                                        &%&&&%&&&&&&&&&@@#            \n" +
            "                                                                         .*%@@@%@@%, ..               \n" +
            "                                                                               .                      ";
    static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    static final String INDENT = "    ";
    static final String EMPTY_DESCRIPTION_ERROR(String task) {
        return String.format("☹ OOPS!!! The description of a %s cannot be empty.", task);
    }
    static final String UNKNOWN_TASK_ERROR = "☹ OOPS!!! I'm sorry, but I don't know what that meows :-(";
    static final String EMPTY_BY_ERROR = "☹ OOPS!!! The deadline cannot be empty.";
    static final String EMPTY_AT_ERROR = "☹ OOPS!!! The event time cannot be empty.";
    static final String NO_SUCH_TASK = "☹ OOPS!!! There is no such task.";
    static final String EMPTY_TASK_INDEX = "☹ OOPS!!! The task index cannot be empty.";

    private Data data;

    public Duke(Data data) {
        this.data = data;
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
                data.add(toBeAdded);
                return "Got it. I've added this task:\n" +
                        INDENT + "  " + toBeAdded + "\n" +
                        INDENT +
                        String.format("Now you have %d tasks in the in the list", data.getTasksList().size());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException(EMPTY_DESCRIPTION_ERROR("todo"));
            }
        } else if (t.startsWith("deadline")) {
            tokens = t.split(" /by ");
            try {
                des = tokens[0].substring(9);
                toBeAdded = new Deadline(des, tokens[1]);
                data.add(toBeAdded);
                return "Got it. I've added this task:\n" +
                        INDENT + "  " + toBeAdded + "\n" +
                        INDENT +
                        String.format("Now you have %d tasks in the in the list", data.getTasksList().size());
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
                data.add(toBeAdded);
                return "Got it. I've added this task:\n" +
                        INDENT + "  " + toBeAdded + "\n" +
                        INDENT +
                        String.format("Now you have %d tasks in the in the list", data.getTasksList().size());
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
            data.markDone(index - 1);
            Task task = data.getTasksList().get(index - 1);
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
            Task removedTask = data.getTasksList().get(index - 1);
            data.delete(index - 1);
            return "Noted. I've removed this task: \n" +
                    "      " + removedTask +
                    String.format("\n     Now you have %d tasks in the list.", data.getTasksList().size());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyDeleteIndexException(EMPTY_TASK_INDEX);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidDeleteIndexException(NO_SUCH_TASK);
        }
    }

    public void printList() {
        String s = convertToList();
        printWindow(s);
    }

    String convertToList() {
        String s = "";
        for (int i = 0; i < data.getTasksList().size(); i ++) {
            s += (i + 1) + "." + data.getTasksList().get(i);
            if (i != data.getTasksList().size() - 1) {
                s += '\n' + INDENT;
            }
        }
        if (s.equals("")) {
            return "There is nothing in the list!";
        } else {
            return s;
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

    public static void main(String[] args) throws DukeInvalidData {
        Scanner sc = new Scanner(System.in);

        String homePath = System.getProperty("user.home");
        homePath += "\\data";
        File file = new File(homePath);
        boolean bool = file.mkdir(); //make directory if directory does not exist!
        homePath += "\\duke.txt";
        Data data;
        System.out.printf("%b, %s", bool, homePath);
        try {
            data = new Data(homePath);
        } catch (DukeInvalidData e) {
            System.out.println(e.getMessage());
            throw e;
        }
        Duke duke = new Duke(data);
        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println("    Hello! I'm Soccat Duke.");
        System.out.println(duke.SOCCAT);
        if (bool) {
            System.out.println("    I have made a directory for you at "
                    + homePath);
        } else {
            System.out.println("    This is your current list:");
            System.out.println(INDENT + duke.convertToList());
        }
        System.out.println("\n    What do you meow?");
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
