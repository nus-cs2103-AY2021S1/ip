import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

// checkstyle was a struggle and a half
public class Duke {
    static class Task {
        private boolean completion;
        private String name;
        private Type type;

        enum Type {
            TODO("todo"),
            DEADLINE("deadline"),
            EVENT("event");

            private String name;

            Type(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return name;
            }
        }

        Task(String type, String name) {
            this.name = name;
            this.completion = false;
            this.type = Type.valueOf(type.toUpperCase());
        }

        void complete() {
            completion = true;
        }

        public String getName() {
            return name;
        }

        public String getCompletion() {
            if (completion) {
                return "[✓]";
            } else {
                return "[✗]";
            }
        }

        public String getType() {
            switch (type) {
            case TODO:
                return "[T]";
            case EVENT:
                return "[E]";
            case DEADLINE:
                return "[D]";
            default:
                // this should not happen?
                return "something went wrong";
            }
        }

        @Override
        public String toString() {
            return completion + name + type;
        }
    }

    static class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }

        static DukeException empty(String type) {
            String message = "The description of " + type + " cannot be empty.";
            return new DukeException(message);
        }

        static DukeException invalid(String order) {
            String message = "Sorry, '" + order + "' is not a recognised order.";
            return new DukeException(message);
        }

        static DukeException outOfBounds(int index) {
            String message = "There is no task number " + index + " , there are only " + storage.size() + " task(s).";
            return new DukeException(message);
        }
    }

    // Task storage
    private static ArrayList<Task> storage = new ArrayList<>();

    // border line
    public static void line() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Pads a given String with 2 lines, top and bottom
     * @param output the String to pad
     */
    public static void echo(String output) {
        line();
        System.out.println(output);
        line();
    }

    /**
     * Adds a task to the task storage
     * @param type the type of task to be added
     * @param name the name of the task
     */
    public static void store(String type, String name) {
        Task taskToAdd = new Task(type, name);
        storage.add(taskToAdd);
        taskAdded(taskToAdd);
    }

    /**
     * Prints a message after adding a task
     * @param task the task added
     */
    public static void taskAdded(Task task) {
        String toEcho = "Task added: \n"
                + task.getType() + task.getCompletion() + " " + task.getName() + "\n"
                + "You now have " + storage.size() + " task(s).";
        echo(toEcho);
    }

    /**
     * Prints the list of tasks in storage
     */
    public static void listOut() {
        line();
        System.out.println("Here's your tasks");
        for (int i = 0; i < storage.size(); i++) {
            // list starts from 1
            Task currentTask = storage.get(i);
            System.out.println((i + 1) + ". "
                    + currentTask.getType()
                    + currentTask.getCompletion() + " "
                    + currentTask.getName());
        }
        line();
    }

    /**
     * Marks a certain task as complete
     * @param taskNumber the index number of the task
     */
    public static void markComplete(int taskNumber) {
        Task currentTask = storage.get(taskNumber);
        currentTask.complete();
        line();
        System.out.println("Marked task " + (taskNumber + 1) + " as complete.");
        System.out.println(currentTask.getType() + "[✓] " + storage.get(taskNumber).getName());
        line();
    }

    /**
     * Deletes a certain task
     * @param taskNumber the index number of the task
     */
    public static void delete(int taskNumber) {
        Task currentTask = storage.get(taskNumber);
        line();
        System.out.println("Deleted task:");
        System.out.println(currentTask.getType() + "[✓] " + storage.get(taskNumber).getName());
        storage.remove(taskNumber);
        System.out.println("There are now " + storage.size() + " task(s) remaining.");
        line();
    }

    /**
     * Saves contents of storage to a text file
     * @param storage the storage to be saved
     */
    public static void save(ArrayList<Task> storage) {
        try {
            FileWriter writer = new FileWriter("./data/duke.txt");
            for (Task task: storage) {
                writer.write(task.toString());
            }
            writer.close();
        } catch (Exception e) {
            File folder = new File("./data");
            if (folder.mkdir()) {
                save(storage);
            } else {
                System.out.println("folder does not exist, but making folder failed");
            }
        }
    }

    /**
     * It's psvm, what do you want?
     * @param args Please it's just psvm
     */
    public static void main(String[] args) {
        String logo =
                          " ____             _     \n"
                        + "|  _ \\           | |    \n"
                        + "| |_) |_ __ _   _| |__\n"
                        + "|  _ <| '__| | | | '_ \\ \n"
                        + "| |_) | |  | |_| | | | |\n"
                        + "|____/|_|   \\__,_|_| |_|\n";

        echo(logo + "What's up?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String output = scanner.nextLine();
            // special 1 word cases
            // bye exits while
            if (output.equals("bye")) {
                echo("Ciao!");
                break;
            } else if (output.equals("list")) {
                listOut();
            } else {
                Scanner multiWord = new Scanner(output);
                // can't use enums here, firstWord can be literally anything
                String firstWord = multiWord.next();
                // wow intelliJ is a better programmer than i'll ever be
                switch (firstWord) {
                case "done": {
                    String index = multiWord.next();
                    int intIndex = Integer.parseInt(index);
                    try {
                        if (intIndex <= storage.size() && intIndex > 0) {
                            markComplete(intIndex - 1);
                        } else {
                            throw DukeException.outOfBounds(intIndex);
                        }
                    } catch (DukeException e) {
                        echo(e.getMessage());
                    }

                    break;
                }
                case "delete": {
                    String index = multiWord.next();
                    int intIndex = Integer.parseInt(index);
                    try {
                        if (intIndex <= storage.size() && intIndex > 0) {
                            delete(intIndex - 1);
                        } else {
                            throw DukeException.outOfBounds(intIndex);
                        }
                    } catch (DukeException e) {
                        echo(e.getMessage());
                    }
                    break;
                }
                // it's a task
                case "todo":
                case "deadline":
                case "event":
                    try {
                        // whitespace in front of nextLine
                        if (multiWord.hasNextLine()) {
                            String remainingWords = multiWord.nextLine().trim();
                            store(firstWord, remainingWords);
                        } else {
                            throw DukeException.empty(firstWord);
                        }
                    } catch (DukeException e) {
                        echo(e.getMessage());
                    }
                    break;
                // invalid order
                default:
                    // skip the try catch block
                    echo(DukeException.invalid(output).getMessage());
                }
            }
        }
    }
}
