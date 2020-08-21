import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String tab = "     ";
        chatPrint("Hello! I'm Duke\n" +
                tab + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        loadTasks(tasks);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String command = input.split(" ")[0];
            try {
                switch (command) {
                case "list":
                    int id = 1;
                    String output = "Here are the tasks in your list:";
                    for (Task task : tasks) {
                        output += "\n" + tab + id + ". " + task;
                        id++;
                    }
                    chatPrint(output);
                    break;
                case "done":
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (idx >= tasks.size()) {
                        throw new DukeException("Oh dear! That task doesn't exist!");
                    }
                    Task doneTask = tasks.get(idx).setDone();
                    tasks.set(idx, doneTask);
                    saveTasks(tasks);
                    chatPrint("Nice! I've marked this task as done:\n" +
                            tab + "   " + doneTask);
                    break;
                case "delete":
                    int idx2 = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (idx2 >= tasks.size()) {
                        throw new DukeException("Oh dear! That task doesn't exist!");
                    }
                    Task rmTask = tasks.get(idx2);
                    tasks.remove(idx2);
                    saveTasks(tasks);
                    chatPrint("Noted. I've removed this task:\n" +
                            tab + "   " + rmTask + "\n" +
                            tab + "Now you have " + tasks.size() + " tasks in the list.");
                    break;
                case "todo":
                case "deadline":
                case "event":
                    int idxSpace = input.indexOf(' ');
                    int idxMeta = input.indexOf('/');
                    int infoLength = idxMeta - (idxSpace + 1);
                    if (idxSpace == -1 ||
                            (idxMeta != -1 && infoLength < 1)) {
                        throw new DukeException("Oh dear! A task description cannot be empty!");
                    }
                    Task newTask = addTask(tasks, command, false, input.substring(input.indexOf(' ') + 1));
                    saveTasks(tasks);
                    chatPrint("Got it. I've added this task:\n" +
                            tab + "   " + newTask + "\n" +
                            tab + "Now you have " + tasks.size() + " tasks in the list.");
                    break;
                default:
                    throw new DukeException("Oh dear! I'm sorry, but I don't know what that means :((");
                }
            } catch (DukeException ex) {
                chatPrint(ex.toString());
            }
            input = sc.nextLine();
        }
        chatPrint("Bye. Hope to see you again soon!");
    }

    public static void chatPrint(String toPrint) {
        String tab = "     ";
        String line = "____________________________________________________________";
        System.out.println(tab + line);
        System.out.println(tab + toPrint);
        System.out.println(tab + line + "\n");
    }

    public static Task addTask(List<Task> tasks, String type, boolean isDone, String info) throws DukeException {
        Task newTask;
        switch (type) {
        case "todo":
            if (info.contains("/")) {
                throw new DukeException("Oh dear! A todo shouldn't contain a timestamp!");
            }
            newTask = new Todo(info, isDone);
            break;
        case "deadline":
            if (!info.contains("/by")) {
                throw new DukeException("Oh dear! A deadline must contain '/by'!");
            }
            String dDesc = info.substring(0, info.indexOf('/') - 1);
            String by = info.substring(info.indexOf('/') + 4);
            newTask = new Deadline(dDesc, isDone, by);
            break;
        default:
            if (!info.contains("/at")) {
                throw new DukeException("Oh dear! An event must contain '/at'!");
            }
            String eDesc = info.substring(0, info.indexOf('/') - 1);
            String at = info.substring(info.indexOf('/') + 4);
            newTask = new Event(eDesc, isDone, at);
            break;
        }
        tasks.add(newTask);
        return newTask;
    }

    public static void saveTasks(List<Task> tasks) {
        String taskData = "";
        for (Task ts : tasks) {
            taskData += ts.formatTask() + "\n";
        }
        try {
            writeToFile("./data/tasks.txt", taskData);
        } catch (IOException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void loadTasks(List<Task> tasks) {
        try {
            Files.createDirectories(Paths.get("./data"));
            File file = new File("./data/tasks.txt");
            if (!file.createNewFile()) {
                loadFromFile("./data/tasks.txt", tasks);
            }
        } catch (IOException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }

    private static void loadFromFile(String filePath, List<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int lineCounter = 0;
        while (s.hasNext()) {
            lineCounter++;
            String line = s.nextLine();
            String[] attr = line.split(" \\| ");
            try {
                if (attr.length < 3 || (!attr[1].equals("V") && !attr[1].equals("X"))) {
                    throw new DukeException("Corrupt file - Invalid task in line " + lineCounter);
                }
                switch (attr[0]) {
                case "T":
                    addTask(tasks, "todo", attr[1] == "V", attr[2]);
                    break;
                case "D":
                    if (attr.length != 4) {
                        throw new DukeException("Corrupt file - Invalid task in line " + lineCounter);
                    }
                    addTask(tasks, "deadline", attr[1] == "V",attr[2] + " /by " + attr[3]);
                    break;
                case "E":
                    if (attr.length != 4) {
                        throw new DukeException("Corrupt file - Invalid task in line " + lineCounter);
                    }
                    addTask(tasks, "event", attr[1] == "V", attr[2] + " /at " + attr[3]);

                    break;
                default:
                    throw new DukeException("Corrupt file - invalid task format");
                }
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }
    }
}
