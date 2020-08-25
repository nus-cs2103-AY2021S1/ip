import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Duke {

    private static final String INDENT = "    ";
    private static final String LINE = "____________________________________________________________";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static void formatResponse(ArrayList<String> response) {
        System.out.println(INDENT + LINE);
        for (String resp: response) {
            System.out.println(INDENT + " " + resp);
        }
        System.out.println(INDENT + LINE);
        System.out.println();
    }

    private static void formatResponse(String ...response) {
        ArrayList<String> lst = new ArrayList<>();
        for (String resp: response) {
            lst.add(resp);
        }
        formatResponse(lst);
    }

    private static void formatList(ArrayList<Task> response) {
        ArrayList<String> lst = new ArrayList<>();
        lst.add("Here are the tasks in your list:");
        for (int i = 0; i < response.size(); i++) {
            lst.add((i + 1) + ". " + response.get(i).toString());
        }
        formatResponse(lst);
    }

    private static void formatListByDate(Date date) {
        ArrayList<String> lst = new ArrayList<>();
        lst.add("Here are the tasks in your list that occur on " + (new SimpleDateFormat("MMM d yyyy")).format(date) + ":");
        int i = 1;
        for (Task task: tasks) {
            if (task.isOccuringOn(date)) {
                lst.add((i++) + ". " + task.toString());
            }
        }
        formatResponse(lst);
    }

    private static void formatDoneTask(Task task) {
        formatResponse("Nice! I've marked this task as done:", INDENT + task.toString());
    }

    private static void formatDeletedTask(Task task) {
        formatResponse("Noted. I've removed this task: ", INDENT + task, "Now you have " + (tasks.size() - 1) + " task" + (tasks.size() == 2 ? "" : "s") + " in the list.");
    }

    public static void saveList() {
        File dir = new File("src/data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            File file = new File("src/data/duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            String contents = "";
            for (Task task : tasks) {
                contents += task.getSavedString() + "\n";
            }
            fw.write(contents);
            fw.close();
        } catch (IOException ex) {
            formatResponse("Could not save tasks.");
        }
    }

    // adapted from https://stackoverflow.com/questions/4024544/how-to-parse-dates-in-multiple-formats-using-simpledateformat
    private static Date parseDate(String str) {
        List<String> formatStrings = Arrays.asList("yyyy-M-dd", "dd/M/yyyy HHmm", "dd/M/yyyy");

        for (String formatString : formatStrings) {
            try {
                return new SimpleDateFormat(formatString).parse(str);
            } catch (ParseException e) {}
        }

        return null;
    }

    private static void addTask(String display) {
        try {
            if (display.length() >= 4 && display.substring(0, 4).equals("todo")) {
                if (display.length() == 4 || display.substring(4).isBlank()) {
                    throw new TaskException(TaskType.TODO, "description",  "cannot be empty.");
                } else {
                    tasks.add(new ToDo(display.substring(5)));
                }
            } else if (display.length() >= 8 && display.substring(0, 8).equals("deadline")) {
                int idx = display.indexOf(" /by ");
                if (idx == -1 || display.length() == idx + 5 || display.substring(idx + 5).isBlank()) {
                    throw new TaskException(TaskType.DEADLINE, "time", "cannot be identified.");
                } else if (display.substring(9, idx).isBlank()) {
                    throw new TaskException(TaskType.DEADLINE, "description", "cannot be empty.");
                } else {
                    if (parseDate(display.substring(idx + 5)) == null) {
                        throw new TaskException(TaskType.DEADLINE, "time", "format is wrong.");
                    } else {
                        tasks.add(new Deadline(display.substring(9, idx), parseDate(display.substring(idx + 5))));
                    }
                }
            } else if (display.length() >= 5 && display.substring(0, 5).equals("event")) {
                int idx = display.indexOf(" /at ");
                if (idx == -1 || display.length() < idx + 5 || display.substring(idx + 5).isBlank()) {
                    throw new TaskException(TaskType.EVENT, "time", "cannot be identified.");
                } else if (display.substring(6, idx).isBlank()) {
                    throw new TaskException(TaskType.EVENT, "description", "cannot be empty.");
                } else {
                    if (parseDate(display.substring(idx + 5)) == null) {
                        throw new TaskException(TaskType.EVENT, "time", "format is wrong.");
                    } else {
                        tasks.add(new Event(display.substring(6, idx), parseDate(display.substring(idx + 5))));
                    }
                }
            } else {
                throw new DukeException("I don't know what that means");
            }

            Task task = tasks.get(tasks.size() - 1);
            formatResponse("Got it. I've added this task: ", INDENT + task.toString(), "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.");
        } catch (TaskException |  DukeException err) {
            formatResponse(err.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        formatResponse("Hello! I'm Duke", "What can I do for you?");
        String display = "";
        while (!display.equals("bye")) {
            display = sc.nextLine();
            if (display.equals("list")) {
                formatList(tasks);
            } else if (display.length() >= 4 && display.substring(0, 4).equals("done")) {
                try {
                    int idx = Integer.parseInt(String.valueOf(display.charAt(5))) - 1;
                    tasks.get(idx).markAsDone();
                    formatDoneTask(tasks.get(idx));
                    saveList();
                } catch (IndexOutOfBoundsException ex) {
                    formatResponse("Task index is empty / out of bounds.");
                }
            } else if (display.length() >= 6 && display.substring(0, 6).equals("delete")) {
                try {
                    int idx = Integer.parseInt(String.valueOf(display.charAt(7))) - 1;
                    formatDeletedTask(tasks.get(idx));
                    tasks.remove(idx);
                    saveList();
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Task index is empty / out of bounds.");
                }
            } else if (display.length() >= 12 && display.substring(0, 12).equals("Tasks due on")){
                if (parseDate(display.substring(13)) == null) {
                    formatResponse("Time is of the wrong format");
                } else {
                    formatListByDate(parseDate(display.substring(13)));
                }
            } else if (!display.equals("bye")) {
                addTask(display);
                saveList();
            }
        }
       formatResponse("Bye. Hope to see you again soon!");
       sc.close();
    }
}
