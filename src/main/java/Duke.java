import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static Scanner input = new Scanner(System.in);
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String line = "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
    static TaskList taskList = new TaskList();



    private static class Task {

        private final String name;
        private boolean done;

        public Task(String name, TaskType taskType) throws DukeEmptyDescException {
            if (isBlankString(name)) {
                throw new DukeEmptyDescException(taskType);
            } else {
                this.name = name;
                this.done = false;
            }
        }

        public String getStatusIcon() {
            return (done ? "\u2713" : "\u2718"); //return tick or X symbols
        }

        public void complete() {
            this.done = true;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", this.getStatusIcon(), this.name);
        }

    }

    private static class Todo extends Task {

        public Todo(String name) throws DukeEmptyDescException {
            super(name, TaskType.TODO);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static class Deadline extends Task {

        protected String by;

        public Deadline(String name, String by) throws DukeEmptyDescException {
            super(name, TaskType.DEADLINE);
            this.by = by;
        }

        @Override
        public String toString() {
            return String.format("[D]%s(by:%s)", super.toString(), by);
        }
    }

    private static class Event extends Task {

        protected String at;

        public Event(String name, String at) throws DukeEmptyDescException {
            super(name, TaskType.EVENT);
            this.at = at;
        }

        @Override
        public String toString() {
            return String.format("[E]%s(at:%s)", super.toString(), at);
        }

    }



    private static class TaskList extends ArrayList<Task> {

        public void printList() {
            printWithLines(this.toString());
        }

        public void completeTask(int i) {
            String prefix = "Roger roger! I'm gonna mark this task as done:\n";
            Task task = super.get(i);
            task.complete();
            printWithLines(String.format("  %s%s\n", prefix, task));
        }

        public void addTask(Task newTask) {
            super.add(newTask);
            String prefix = "Okay! I shall add this task:\n";
            String suffix = String.format("Now you got a total of %s task%s in your list!\n", super.size(),
                    super.size() == 1 ? "" : "s");
            printWithLines(prefix + newTask + "\n" + suffix);
        }

        public void deleteTask(int index) {
            Task task = taskList.get(index);
            taskList.remove(index);
            String prefix = "Very well! I shall delete this task:\n";
            printWithLines(prefix + task + "\n");
        }

        @Override
        public String toString() {
            StringBuilder list = new StringBuilder();
            StringBuilder prefix = new StringBuilder("Here are the tasks in your list:\n");
            int l = super.size();
            for (int i = 0; i < l; i++) {
                list.append(i + 1).append(".").append(super.get(i).toString()).append("\n");
            }
            return prefix.append(list).toString();
        }
    }


    public static void main(String[] args) {
        printWithLines("Hello! My name is Duketh Puketh III, but you can call me\n" + logo +
                "\n How may I help you today? :)\n");
        processInput();
        printWithLines("Bye! I'll see you again next time!\n");
    }

    private static void processInput() {

        String nextInput = input.nextLine();
        String[] inputParts = nextInput.split(" ", 2);
        String inputPrefix = inputParts[0];
        String inputSuffix = inputParts.length == 1 ? "" : inputParts[1];

        while (!nextInput.equals("bye")) {

            try {
                handleInput(inputPrefix, inputSuffix);
            } catch (DukeException dukeException) {
                printWithLines(dukeException + "\n");
            }


            nextInput = input.nextLine();
            inputParts = nextInput.split(" ", 2);
            inputPrefix = inputParts[0];
            inputSuffix = inputParts.length == 1 ? "" : inputParts[1];

        }

    }

    private static void printWithLines(String output) {
        System.out.println(line + "\n" + output + line);
    }

    private static void handleInput(String inputPrefix, String inputSuffix) throws DukeException {
        switch (inputPrefix) {
            case "list":
                taskList.printList();
                break;
            case "done":
                taskList.completeTask(Integer.parseInt(inputSuffix) - 1);
                break;
            case "todo":
                taskList.addTask(new Todo(inputSuffix));
                break;
            case "deadline":
                String[] deadlineParts = inputSuffix.split("/by",2);
                String deadlineName = deadlineParts[0];
                if (deadlineParts.length == 1) {
                    throw new DukeEmptyDescException(TaskType.EVENT);
                } else {
                    String by = deadlineParts[1];
                    if (isBlankString(by)) {
                        throw new DukeEmptyDescException(TaskType.EVENT);
                    } else {
                        taskList.addTask(new Deadline(deadlineName, by));
                        break;
                    }
                }
            case "event":
                String[] eventParts = inputSuffix.split("/at",2);
                String eventName = eventParts[0];
                if (eventParts.length == 1) {
                    throw new DukeEmptyDescException(TaskType.EVENT);
                } else {
                    String at = eventParts[1];
                    if (isBlankString(at)) {
                        throw new DukeEmptyDescException(TaskType.EVENT);
                    } else {
                        taskList.addTask(new Event(eventName, at));
                        break;
                    }
                }
            case "delete":
                taskList.deleteTask(Integer.parseInt(inputSuffix) - 1);
                break;
            default:
                throw new DukeNoSuchInputException();
        }
    }



    public static boolean isBlankString(String string) {
        if (string.length() != 0) {
            for (char c : string.toCharArray()) {
                if (c != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}