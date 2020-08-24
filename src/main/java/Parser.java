import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public Parser() { }

    public void parser(TaskList tasks, String filePath) {
        Scanner scanner = new Scanner(System.in);
        Path path = Paths.get(filePath);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye! Hope to see you again ;)");
                break;
            } else if (line.equals("list")) {
                int count = 1;
                for (Task task : tasks.list) {
                    System.out.println(count + "." + task.toString());
                    count++;
                }
            } else {
                String[] words = line.split("\\s+");
                if (words[0].equals("done")) {
                    try {
                        int index = compDel(line, tasks.size());
                        tasks.get(index).done();
                        Storage.fileUpdate(tasks, path);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks.get(index).toString());
                    } catch (DukeException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (words[0].equals("delete")) {
                    try {
                        int index = compDel(line, tasks.size());
                        Task task = tasks.get(index);
                        tasks.delete(index);
                        Storage.fileUpdate(tasks, path);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } catch (DukeException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        Task task = taskClassify(line);
                        tasks.add(task);
                        Storage.fileUpdate(tasks, path);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + task.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } catch (DukeException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
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
}
