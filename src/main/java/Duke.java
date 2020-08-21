import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm duke. What can I do for you? \n" + logo);

        ArrayList<Task> tasks = new ArrayList<>();
        loadTaskList(tasks);

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            line();
            command(str, tasks);
            line();
            str = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    private static void command(String str, ArrayList<Task> tasks) {
        try {
            if (str.equals("list")) {
                displayTasks(tasks);
            } else {
                if (str.startsWith("done")) {
                    completeTask(str, tasks);
                } else if (str.startsWith("delete")) {
                    deleteTask(str, tasks);
                } else {
                    addTask(str, tasks);
                }
                saveTaskList(tasks);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loadTaskList(ArrayList<Task> tasks) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ip/../data/duke.txt"));
            String str;
            while ((str = reader.readLine()) != null) {
                Task T;
                if (str.startsWith("T")) {
                    T = ToDo.load(str);
                } else if (str.startsWith("E")) {
                    T = Event.load(str);
                } else if (str.startsWith("D")) {
                    T = Deadline.load(str);
                } else {
                    break;
                }
                tasks.add(T);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Starting a new task list");
            File file = new File("./data");
            file.mkdir();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveTaskList(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            for (Task t : tasks) {
                fw.write(t.store() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("error in saving");
        }
    }

    public static void line() {
        for (int i = 0; i < 75; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }

    private static void displayTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task t : tasks) {
            System.out.println(i + "." + t.toString());
            i++;
        }
    }

    private static void addTask(String s, ArrayList<Task> tasks) throws InvalidCommandException, EmptyCommandException, MissingTimeException {
        String str = s.trim();
        if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {
            throw new EmptyCommandException(str);
        }
        if (str.contains(" ")) {
            String[] arr = str.split(" ", 2);
            String str2 = arr[1];
            switch (arr[0]) {
                case "todo":
                    ToDo td = new ToDo(str2);
                    insert(td, tasks);
                    break;
                case "deadline":
                    if (str2.contains("/by")) {
                        String[] arr2 = str2.split("/by", 2);
                        if (arr2[0].isBlank()) {
                            throw new EmptyCommandException("deadline");
                        }
                        if (arr2[1].isBlank()) {
                            throw new MissingTimeException("deadline");
                        }
                        Deadline dl = new Deadline(arr2[0], arr2[1].trim());
                        insert(dl, tasks);
                    } else {
                        throw new MissingTimeException("deadline");
                    }
                    break;
                case "event":
                    if (str2.contains("/at")) {
                        String[] arr2 = str2.split("/at", 2);
                        if (arr2[0].isBlank()) {
                            throw new EmptyCommandException("event");
                        }
                        if (arr2[1].isBlank()) {
                            throw new MissingTimeException("event");
                        }
                        Event ev = new Event(arr2[0], arr2[1].trim());
                        insert(ev, tasks);
                    } else {
                        throw new MissingTimeException("event");
                    }
                    break;
                default:
                    throw new InvalidCommandException();
            }
        } else {
            throw new InvalidCommandException();
        }
    }

    public static void insert(Task T, ArrayList<Task> tasks) {
        tasks.add(T);
        System.out.println("Task has been added:");
        System.out.println(T.toString());
        System.out.println("You now have " + tasks.size() + " tasks in the list");
    }

    public static void delete(int i, ArrayList<Task> tasks) {
        Task t = tasks.get(i - 1);
        tasks.remove(i - 1);
        System.out.println("Task has been removed.");
        System.out.println(t.toString());
        System.out.println("You now have " + tasks.size() + " tasks in the list");
    }

    private static void completeTask(String str, ArrayList<Task> tasks) throws TaskCompletionException {
        if (!str.startsWith("done ")) {
            throw new TaskCompletionException(tasks.size());
        }
        String val = str.substring(5);
        if (isInteger(val)) {
            int i = Integer.parseInt(val);
            if (i > 0 && i <= tasks.size()) {
                tasks.get(i - 1).complete();
            } else {
                throw new TaskCompletionException(tasks.size());
            }
        } else {
            throw new TaskCompletionException(tasks.size());
        }
    }

    private static void deleteTask(String str, ArrayList<Task> tasks) throws TaskDeletionException {
        if (!str.startsWith("delete ")) {
            throw new TaskDeletionException(tasks.size());
        }
        String val = str.substring(7);
        if (isInteger(val)) {
            int i = Integer.parseInt(val);
            if (i > 0 && i <= tasks.size()) {
                delete(i, tasks);
            } else {
                throw new TaskDeletionException(tasks.size());
            }
        } else {
            throw new TaskDeletionException(tasks.size());
        }
    }


    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
