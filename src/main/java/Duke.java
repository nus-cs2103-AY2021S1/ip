import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {

    public static void greet() {
        System.out.println("Hello! I'm Jia Le :)");
        System.out.println("How may I be of assistance to you?");
    }

    public static int compDel(String str, int numTask) throws DukeException {
        String[] words = str.split("\\s+");
        int len = words.length;
        if (len == 2) {
            String num = words[1];
            boolean result = num.matches(".*\\d.*");
            if (result) {
                int index = Integer.parseInt(num) - 1;
                if (index >= numTask || index < 0) {
                    throw new DukeException("OOPS!!! Out of bounds of the list of tasks.");
                }
                return index;
            }
        }
        throw new DukeException("OOPS!!! Invalid task provided.");
    }

    public static Task taskClassify(String str) throws DukeException {
        String[] words = str.split("\\s+");
        int len = words.length;

        if (words[0].equals("todo")) {
            if (len == 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } else {
                String desc = "";
                for (int i = 1; i < len; i++) {
                    desc += " " + words[i];
                }
                return new ToDo(desc);
            }
        } else if (words[0].equals("deadline")) {
            if (len == 1) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            } else {
                String desc = "";
                String time = "";
                int count = 0;
                for (int i = 1; i < len; i++) {
                    if (words[i].equals("/by")) {
                        count = i + 1;
                        break;
                    }
                    desc += " " + words[i];
                }
                if (count == 0 || count == len) {
                    throw new DukeException("OOPS!!! The date/time of a deadline cannot be empty.");
                }
                for (int j = count; j < len; j++) {
                    time += " " + words[j];
                }
                return new Deadline(desc, time);
            }
        } else if (words[0].equals("event")) {
            if (len == 1) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            } else {
                String desc = "";
                String time = "";
                int count = 0;
                for (int i = 1; i < len; i++) {
                    if (words[i].equals("/at")) {
                        count = i + 1;
                        break;
                    }
                    desc += " " + words[i];
                }
                if (count == 0 || count == len) {
                    throw new DukeException("OOPS!!! The date/time of a deadline cannot be empty.");
                }
                for (int j = count; j < len; j++) {
                    time += " " + words[j];
                }
                return new Event(desc, time);
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static ArrayList<Task> reader(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        ArrayList<Task> list = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String splitOn = "\\s*-\\s*";
            String[] words = line.split(splitOn);
            int done = Integer.parseInt(words[1]);
            if (words.length == 3) {
                ToDo toDo = new ToDo(words[2]);
                if (done == 1) {
                    toDo.done();
                }
                list.add(toDo);
            } else {
                if (words[0].equals("[E]")) {
                    Event event = new Event(words[2], words[3]);
                    if (done == 1) {
                        event.done();
                    }
                    list.add(event);
                } else {
                    Deadline deadline = new Deadline(words[2], words[3]);
                    if (done == 1) {
                        deadline.done();
                    }
                    list.add(deadline);
                }
            }
        }
        return list;
    }

    public static void fileUpdate(ArrayList<Task> list, Path path) throws IOException {
        FileWriter fw = new FileWriter(path.toString());
        for (Task t : list) {
            int done = t.isDone ? 1 : 0;
            if (t instanceof Event) {
                fw.write("[E]-" + done + "-" + t.desc + "-" +
                        ((Event) t).at + System.getProperty("line.separator"));
            } else if (t instanceof Deadline) {
                fw.write("[D]-" + done + "-" + t.desc + "-" +
                        ((Deadline) t).by + System.getProperty("line.separator"));
            } else {
                fw.write("[T]-" + done + "-" + t.desc +
                        System.getProperty("line.separator"));
            }
        }
        fw.close();
    }

    public static void echo() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        Path path = Paths.get("src/main/java/data/duke.txt");
        if (Files.exists(path)) {
            ArrayList<Task> current = reader(new File(path.toString()));
            list.addAll(current);
        } else {
            File f = new File(path.toString());
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye! Hope to see you again ;)");
                break;
            } else if (line.equals("list")) {
                int count = 1;
                for (Task task : list) {
                    System.out.println(count + "." + task.toString());
                    count++;
                }
            } else {
                String[] words = line.split("\\s+");
                if (words[0].equals("done")) {
                    try {
                        int index = compDel(line, list.size());
                        list.get(index).done();
                        fileUpdate(list, path);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(index).toString());
                    } catch (DukeException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (words[0].equals("delete")) {
                    try {
                        int index = compDel(line, list.size());
                        Task task = list.get(index);
                        list.remove(index);
                        fileUpdate(list, path);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    } catch (DukeException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        Task task = taskClassify(line);
                        list.add(task);
                        fileUpdate(list, path);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + task.toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    } catch (DukeException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
    }
}
