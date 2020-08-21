import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
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
            DukeUnknownInputException, DukeInvalidDateTimeInputException {
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
            try {
                tokens = t.split(" /by ");
                des = tokens[0].substring(9);
                LocalDate byDate;
                LocalTime byTime;
                try {
                    List<LocalDateTime> dtList = customDateTimeFormatter(tokens[1]);
                    byDate = dtList.get(0).toLocalDate();
                    if (dtList.size() == 2) {
                        LocalDateTime time = dtList.get(1);
                        byTime = LocalTime.of(time.getHour(), time.getMinute());
                    } else {
                        byTime = null;
                    }
                } catch (DukeInvalidDateTimeInputException e) {
                    throw e;
                }
                toBeAdded = new Deadline(des, byDate, byTime);
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
                LocalDate atDate;
                LocalTime atTime;
                try {
                    List<LocalDateTime> dtList = customDateTimeFormatter(tokens[1]);
                    atDate = dtList.get(0).toLocalDate();
                    if (dtList.size() == 2) {
                        LocalDateTime time = dtList.get(1);
                        atTime = LocalTime.of(time.getHour(), time.getMinute());
                    } else {
                        atTime = null;
                    }
                } catch (DukeInvalidDateTimeInputException e) {
                    throw e;
                }
                toBeAdded = new Event(des, atDate, atTime);
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

    private List<LocalDateTime> customDateTimeFormatter(String dateTimeString) throws DukeInvalidDateTimeInputException {
        //dateTimeString should be given in "dd/mm/yyyy hhmm"
        //will use manual parser to check for invalid date time inputs
        List<LocalDateTime> results = new ArrayList<>();
        //results returns index 0 as date, index 1 as time
        String[] tokens = dateTimeString.split(" ");
        String dateString = tokens[0];
        String[] dateTokens = dateString.split("/");
        if (dateTokens.length != 3) {
            throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid date format!");
        } else {
            int year = Integer.parseInt(dateTokens[2]);
            int month = Integer.parseInt(dateTokens[1]);
            int day = Integer.parseInt(dateTokens[0]);
            try {
                LocalDateTime date = LocalDateTime.of(year, month, day, 0, 0);
                results.add(date);
            } catch (DateTimeException e) {
                throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid date. Date do not exist!");
            }
        }
        if (tokens.length == 1) {
        } else {
            String timeString = tokens[1];
            if (timeString.length() != 4) {
                throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid time format!");
            }
            try {
                int hr = Integer.parseInt(timeString.substring(0, 2));
                int min = Integer.parseInt(timeString.substring(2));
                results.add(LocalDateTime.of(LocalDate.now(), LocalTime.of(hr, min)));
            } catch (DateTimeException e) {
                throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid time. You can only input up to 23hr and 59min.");
            }
        }
        return results;
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

    private String convertList(List<Task> tasks, String alternative) {
        String s = "";
        for (int i = 0; i < tasks.size(); i ++) {
            s += (i + 1) + "." + tasks.get(i);
            if (i != tasks.size() - 1) {
                s += '\n' + INDENT;
            }
        }
        if (s.equals("")) {
            return alternative;
        } else {
            return s;
        }
    }

    public void printList() {
        printWindow(convertList(list, "There is nothing in the list!"));
    }

    public void printTaskOn(String dateString) throws DukeInvalidDateTimeInputException {
        //dateString given in dd/MM/yyyy
        String[] tokens = dateString.split("/");
        LocalDate date;
        if (tokens.length != 3) {
            throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid date format!");
        } else {
            int year = Integer.parseInt(tokens[2]);
            int month = Integer.parseInt(tokens[1]);
            int day = Integer.parseInt(tokens[0]);
            try {
                date = LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid date. Date do not exist!");
            }
        }
        List<Task> taskOnDate = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Deadline) {
                Deadline d = (Deadline) list.get(i);
                if (d.getDate().isEqual(date)) {
                    taskOnDate.add(d);
                }
            } else if (list.get(i) instanceof Event) {
                Event e = (Event) list.get(i);
                if (e.getDate().isEqual(date)) {
                    taskOnDate.add(e);
                }
            }
        }
        printWindow(convertList(taskOnDate, "There is no upcoming tasks on that date."));
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
            } else if (nextLine.startsWith("list ")) {
                try {
                    duke.printTaskOn(nextLine.substring(5));
                } catch (DukeInvalidDateTimeInputException e) {
                    printWindow(e.getMessage());
                }
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
                } catch (DukeUnknownInputException | DukeEmptyByException | DukeEmptyAtException | DukeEmptyDescriptionException | DukeInvalidDateTimeInputException e) {
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
