import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Parser {
    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    public void parser(TaskList tasks, String filePath) {
        Scanner scanner = new Scanner(System.in);
        Path path = Paths.get(filePath);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                ui.bye();
                break;
            } else if (line.equals("list")) {
                ui.list(tasks);
            } else {
                String[] words = line.split("\\s+");
                if (words[0].equals("done")) {
                    try {
                        int index = completeDelete(line, tasks.size());
                        tasks.get(index).setDone();
                        Storage.fileUpdate(tasks, path);
                        ui.done(tasks, index);
                    } catch (DukeException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (words[0].equals("delete")) {
                    try {
                        int index = completeDelete(line, tasks.size());
                        ui.remove(tasks, index);
                        tasks.delete(index);
                        Storage.fileUpdate(tasks, path);
                    } catch (DukeException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        Task task = taskClassify(line);
                        tasks.add(task);
                        Storage.fileUpdate(tasks, path);
                        ui.add(task, tasks.size());
                    } catch (DukeException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    public static int completeDelete(String str, int numTask) throws DukeException {
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

        switch (words[0]) {
            case "todo":
                if (len == 1) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    String desc = "";
                    for (int i = 1; i < len; i++) {
                        if (i == len - 1) {
                            desc += words[i];
                            break;
                        }
                        desc += words[i] + " ";
                    }
                    return new ToDo(desc);
                }
            case "deadline":
                if (len == 1) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    String desc = "";
                    String time = "";
                    int count = 0;
                    for (int i = 1; i < len; i++) {
                        if (words[i].equals("/by")) {
                            count = i + 1;
                            desc = desc.substring(0, desc.length() - 1);
                            break;
                        }
                        desc += words[i] + " ";
                    }
                    if (count == 0 || count == len) {
                        throw new DukeException("OOPS!!! The date/time of a deadline cannot be empty.");
                    }
                    for (int j = count; j < len; j++) {
                        if (j == len - 1) {
                            time += words[j];
                            break;
                        }
                        time += words[j] + " ";
                    }
                    return new Deadline(desc, time);
                }
            case "event":
                if (len == 1) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    String desc = "";
                    String time = "";
                    int count = 0;
                    for (int i = 1; i < len; i++) {
                        if (words[i].equals("/at")) {
                            count = i + 1;
                            desc = desc.substring(0, desc.length() - 1);
                            break;
                        }
                        desc += words[i] + " ";
                    }
                    if (count == 0 || count == len) {
                        throw new DukeException("OOPS!!! The date/time of a deadline cannot be empty.");
                    }
                    for (int j = count; j < len; j++) {
                        if (j == len - 1) {
                            time += words[j];
                            break;
                        }
                        time += " " + words[j];
                    }
                    return new Event(desc, time);
                }
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
