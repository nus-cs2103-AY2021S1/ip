import java.util.*;
import java.util.regex.*;

public class Duke {

    public enum TaskType {
        TODO("todo") {
            @Override
            public void addToTasks(List<Task> tasks, String taskName, String taskDate) throws DukeException {
                if (taskName.isBlank()) {
                    throw new NullTaskNameException("todo");
                }
                tasks.add(new ToDo(taskName));
            }
        },
        DEADLINE("deadline") {
            @Override
            public void addToTasks(List<Task> tasks, String taskName, String taskDate) throws DukeException {
                if (taskName.isBlank()) {
                    throw new NullTaskNameException("deadline");
                }
                if (taskDate.isBlank()) {
                    throw new NullTaskDateException("deadline");
                }
                tasks.add(new Deadline(taskName, taskDate));
            }
        },
        EVENT("event") {
            @Override
            public void addToTasks(List<Task> tasks, String taskName, String taskDate) throws DukeException {
                if (taskName.isBlank()) {
                    throw new NullTaskNameException("event");
                }
                if (taskDate.isBlank()) {
                    throw new NullTaskDateException("event");
                }
                tasks.add(new Event(taskName, taskDate));
            }
        };

        private static final Map<String, TaskType> nameToValueMap = new HashMap<String, TaskType>();
        private final String lowerCase;

        TaskType(String lowerCase) {
            this.lowerCase = lowerCase;
        }

        public String getLowerCase() {
            return lowerCase;
        }

        static {
            for (TaskType type : EnumSet.allOf(TaskType.class)) {
                nameToValueMap.put(type.getLowerCase(), type);
            }
        }

        public static boolean isMember(String name) {
            return nameToValueMap.containsKey(name);
        }

        public static TaskType getTaskType(String lowerCase) {
            return nameToValueMap.get(lowerCase);
        }

        public abstract void addToTasks(List<Task> tasks, String taskName, String taskDate) throws DukeException;
    }

    public static void displayStarLine() {
        System.out.println("––––––––––––––––––––– *** –––––––––––––––––––––");
    }
    public static void displayMessage(String message) {
        displayStarLine();
        System.out.println(message);
        displayStarLine();
    }

    public static int getNumIfValid(List<Task> tasks, String commandLine) throws DukeException {

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(commandLine);
        Integer id = m.find() ? Integer.parseInt(m.group()) : null;

        if (tasks.isEmpty()) {
            throw new EmptyTasksException(commandLine);
        }

        if (id == null) {
            throw new NullDoneIndexException(commandLine);
        }

        if (id < 1 || id > tasks.size()) {
            throw new RangeIndexException(commandLine);
        }

        return id - 1;
    }

    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String welcome = "Duke at your service!\n" +
                // logo + "\n" +
                // commented out due to runtest failures, for some reason.
                "How can I help you?\n" +
                "Type in your orders below.\n\n" +
                "(command list: 'list', 'deadline', 'event', 'todo', 'done', 'bye')";

        displayMessage(welcome);
        System.out.println();
    }
    public static void bye() {
        displayMessage("Alright, see you soon!");
    }
    public static void list(List<Task> tasks, int pending) {
        int currId = 1;

        displayStarLine();
        System.out.println("TO-DO LIST:");
        System.out.println(String.format("%d pending", pending));
        for (Task t : tasks) {
            System.out.println(String.format("   %d. %s", currId, t));
            currId++;
        }
        displayStarLine();
    }
    public static void done(List<Task> tasks, String commandLine) throws DukeException {
        int target = getNumIfValid(tasks, commandLine);
        Task targetTask = tasks.get(target);
        if (targetTask.isDone) {
            throw new AlreadyDoneIndexException(commandLine);
        }
        tasks.get(target).markAsDone();

        displayStarLine();
        System.out.println("Good job! This task is now marked done:");
        System.out.println(tasks.get(target));
        displayStarLine();
    }
    public static boolean delete(List<Task> tasks, String commandLine) throws DukeException {
        int target = getNumIfValid(tasks, commandLine);
        Task targetTask = tasks.get(target);
        tasks.remove(target);

        displayStarLine();
        System.out.println("Alright! This task is now deleted:");
        System.out.println(targetTask);
        displayStarLine();

        return !targetTask.isDone;
    }
    public static void add (List<Task> tasks, String commandLine) throws DukeException {
        String[] inputs = commandLine.split("/");
        String[] taskDetails = inputs[0].split(" ", 2);
        String taskType = taskDetails[0];

        if (!TaskType.isMember(taskType)) {
            throw new DukeException(taskType);
        } else {
            TaskType task = TaskType.getTaskType(taskType);
            String taskName = taskDetails.length == 1 ? "" : taskDetails[1].trim();
            String taskDate = inputs.length == 1 ? "" : inputs[1].split(" ", 2)[1];
            task.addToTasks(tasks, taskName, taskDate);

            int size = tasks.size();

            displayStarLine();
            System.out.println("'" + taskName + "' added to list!");
            System.out.println(tasks.get(size - 1));
            System.out.println("\nYou now have " + size + " task(s) in your list.\n");
            System.out.println("(Use 'list' command to see your updated list.)");
            displayStarLine();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>(100);
        int pending = 0;

        welcome();

        while (sc.hasNextLine()) {

            String commandLine = sc.nextLine();

            if (commandLine.startsWith("bye")) {
                bye();
                sc.close();
                break;
            } else if (commandLine.startsWith("list")) {
                list(tasks, pending);
            } else if (commandLine.startsWith("done")){
                try {
                    done(tasks, commandLine);
                    pending--;
                } catch (DukeException e) {
                    displayMessage(e.toString());
                }
            } else if (commandLine.startsWith("delete")) {
                try {
                    boolean pendingIsDeleted = delete(tasks, commandLine);
                    if (pendingIsDeleted) {
                        pending--;
                    }
                } catch (DukeException e) {
                    displayMessage(e.toString());
                }
            } else {
                try {
                    add(tasks, commandLine);
                    pending++;
                } catch (DukeException e) {
                    displayMessage(e.toString());
                }
            }
            System.out.println();
        }
    }
}

