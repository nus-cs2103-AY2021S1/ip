import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Parser {
    private Ui ui;
    private TaskList tasks;

    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Processes inputs from the user from terminal.
     * Parses and generates output to terminal.
     *
     * @param filePath Path of file as string.
     */
    public void parser(String filePath) {
        Scanner scanner = new Scanner(System.in);
        Path path = Paths.get(filePath);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                ui.displayBye();
                break;
            } else if (line.equals("list")) {
                ui.displayList(tasks);
            } else {
                String[] words = line.split("\\s+");
                if (words[0].equals("done")) {
                    performDone(line, path);
                } else if (words[0].equals("delete")) {
                    performDelete(line, path);
                } else if (words[0].equals("find")) {
                    performFind(words, line);
                } else {
                    performClassify(line, path);
                }
            }
        }
    }

    /**
     * Processes the "done" command and displays msg in terminal.
     *
     * @param line Input from user.
     * @param path Path of file as string.
     */
    public void performDone(String line, Path path) {
        try {
            int index = findIndex(line, tasks.size());
            tasks.get(index).setDone();
            Storage.updateFile(tasks, path);
            ui.displayDone(tasks, index);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Processes the "delete" command and displays msg in terminal.
     *
     * @param line Input from user.
     * @param path Path of file as string.
     */
    public void performDelete(String line, Path path) {
        try {
            int index = findIndex(line, tasks.size());
            ui.displayRemove(tasks, index);
            tasks.delete(index);
            Storage.updateFile(tasks, path);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Processes the "find" command and displays msg in terminal.
     *
     * @param words Array containing words in input from user.
     * @param line Input from user.
     */
    public void performFind(String[] words, String line) {
        if (words.length > 1) {
            String keyword = line.substring(5);
            TaskList matches = findMatches(tasks, keyword);
            ui.displayMatches(matches);
        }
    }


    /**
     * Classifies and adds the task based on user input and displays msg in terminal.
     *
     * @param line Input from user.
     * @param path Path of file as string.
     */
    public void performClassify(String line, Path path) {
        try {
            Task task = classifyTasks(line);
            tasks.add(task);
            Storage.updateFile(tasks, path);
            ui.displayAdd(task, tasks.size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Processes inputs from the user from GUI.
     * Parses and generates output to GUI.
     *
     * @param line Input from user..
     * @param tasks TaskList containing pre-existing tasks.
     */
    public String parser(String line, TaskList tasks) {
        Path path = Paths.get("./duke.txt");
        if (line.equals("bye")) {
            return "Bye! Hope to see you again ;)";
        } else if (line.equals("list")) {
            return giveStringList();
        } else {
            String[] words = line.split("\\s+");
            switch (words[0]) {
                case "done":
                    return giveStringDone(line, path);
                case "delete":
                    return giveStringDelete(line, path);
                case "find":
                    return giveStringFind(words, line, path);
                case "update":
                    return giveStringUpdate(words, line, path);
                case "updateTime":
                    return giveStringUpdateTime(words, line, path);
                case "updateDesc":
                    return giveStringUpdateDesc(words, line, path);
                default:
                    return giveStringClassify(line, path);
            }
        }
    }

    public String giveStringList() {
        int count = 1;
        String string = "";
        for (Task task : tasks.getList()) {
            if (count == tasks.size()) {
                string += count + "." + task.toString();
            } else {
                string += count + "." + task.toString() + "\n";
                count++;
            }
        }
        assert count > 0 : "Something went wrong with the Parser.giveStringList() function";
        return string;
    }

    public String giveStringDone(String line, Path path) {
        try {
            int index = findIndex(line, tasks.size());
            assert index > -1 : "An exception should have been caught before this";
            tasks.get(index).setDone();
            Storage.updateFile(tasks, path);
            String string = "Nice! I've marked this task as done:" + "\n";
            string += tasks.get(index).toString();
            return string;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String giveStringDelete(String line, Path path) {
        try {
            int index = findIndex(line, tasks.size());
            assert index > -1 : "An exception should have been caught before this";
            String string = "Noted. I've removed this task:" + "\n";
            string += tasks.get(index).toString() + "\n";
            string += "Now you have " + (tasks.size() - 1) + " tasks in the list.";
            tasks.delete(index);
            Storage.updateFile(tasks, path);
            return string;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String giveStringFind(String[] words, String line, Path path) {
        String keyword = line.substring(5);
        TaskList matches = findMatches(tasks, keyword);
        String string = "Here are the matching tasks in your list:" + "\n";
        int counter = 1;
        for (Task task : matches.getList()) {
            if (counter == tasks.size()) {
                string += counter + "." + task.toString();
            } else {
                string += counter + "." + task.toString() + "\n";
                counter++;
            }
        }
        if (counter == 1) {
            return "There are no matching tasks in your list :(";
        }
        return string;
    }

    public String giveStringClassify(String line, Path path) {
        try {
            Task task = classifyTasks(line);
            tasks.add(task);
            Storage.updateFile(tasks, path);
            String string = "Got it. I've added this task:" + "\n";
            string += "  " + task.toString() + "\n";
            string += "Now you have " + tasks.size() + " tasks in the list.";
            return string;

        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String giveStringUpdate(String[] words, String line, Path path) {
        try {
            int index = findIndex(line, tasks.size());
            Task current = tasks.get(index);
            String newline = buildString(words);
            Task updated = classifyTasks(newline);
            tasks.update(index, updated);
            Storage.updateFile(tasks, path);
            return current + "\n" + " updated to " + "\n" + updated;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String giveStringUpdateTime(String[] words, String line, Path path) {
        try {
            int index = findIndex(line, tasks.size());
            Task current = tasks.get(index);
            String newline = buildString(words);
            Task updated = updateTime(current, newline);
            tasks.update(index, updated);
            Storage.updateFile(tasks, path);
            return current + "\n" + " updated to \n" + updated;
        } catch (DukeException e) {
            return  e.getMessage();
        }
    }

    public String giveStringUpdateDesc(String[] words, String line, Path path) {
        try {
            int index = findIndex(line, tasks.size());
            Task current = tasks.get(index);
            String newline = buildString(words);
            Task updated = updateDesc(current, newline);
            tasks.update(index, updated);
            Storage.updateFile(tasks, path);
            return current + "\n" + " updated to \n" + updated;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String buildString(String[] words) {
        String newline = "";
        for (int i = 2; i < words.length; i++) {
            if (i == words.length - 1) {
                newline += words[i];
            } else {
                newline += words[i] + " ";
            }
        }
        return newline;
    }

    public Task updateTime(Task task, String line) throws DukeException {
        if (task instanceof ToDo) {
            throw new DukeException("OOPS! Todo has no time parameter to update!");
        } else if (task instanceof Event) {
            return new Event(task.desc, line);
        } else if (task instanceof Deadline) {
            return new Deadline(task.desc, line);
        } else {
            throw new DukeException("OOPS! That's an invalid task! Something might have went wrong!");
        }
    }

    public Task updateDesc(Task task, String line) throws DukeException {
        if (task instanceof ToDo) {
            return new ToDo(line);
        } else if (task instanceof Event) {
            return new Event(line, ((Event) task).at);
        } else if (task instanceof Deadline) {
            return new Deadline(line, ((Deadline) task).by);
        } else {
            throw new DukeException("OOPS! That's an invalid task! Something might have went wrong!");
        }
    }

    /**
     * Returns the index of task to be deleted/completed if possible.
     *
     * @param str Input line from user.
     * @param numTask Total number of tasks.
     * @return Index of task in TaskList.
     * @throws DukeException If input is invalid or out of bounds.
     */
    public static int findIndex(String str, int numTask) throws DukeException {
        assert numTask > -1 : "completeDelete function was passed with negative numTask value";
        String[] words = str.split("\\s+");
        int len = words.length;
        if (len > 1) {
            String num = words[1];
            boolean result = num.matches(".*\\d.*");
            if (result) {
                int index = Integer.parseInt(num) - 1;
                if (index >= numTask || index < 0) {
                    throw new DukeException("OOPS!!! Out of bounds of the list of tasks.");
                }
                assert index > -1 && index < numTask : "An exception should have been thrown.";
                return index;
            }
        }
        throw new DukeException("OOPS!!! Invalid task provided.");
    }

    /**
     * Reads the data in file.
     *
     * @param file File to be read from.
     * @return TaskList containing all the tasks in the file.
     */
    public static TaskList reader(File file) {
        try {
            Scanner s = new Scanner(file);
            TaskList tasks = new TaskList();
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String splitOn = "\\s*@\\s*";
                String[] words = line.split(splitOn);
                int done = Integer.parseInt(words[1]);
                processData(words, done, tasks);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("OOPS!! Something went wrong :(");
            return new TaskList();
        }
    }

    /**
     * Processes the data in the file based on its syntax and adds tasks to TaskList.
     *
     * @param words Array containing words in a single line.
     * @param done Int that represents whether the task is done.
     * @param tasks TaskList where tasks are to be added to.
     */
    public static void processData(String[] words, int done, TaskList tasks) {
        if (words.length == 3) {
            ToDo toDo = new ToDo(words[2]);
            if (done == 1) {
                toDo.setDone();
            }
            tasks.add(toDo);
        } else {
            if (words[0].equals("[E]")) {
                Event event = new Event(words[2], words[3]);
                if (done == 1) {
                    event.setDone();
                }
                tasks.add(event);
            } else {
                Deadline deadline = new Deadline(words[2], words[3]);
                if (done == 1) {
                    deadline.setDone();
                }
                tasks.add(deadline);
            }
        }
    }

    /**
     * Finds matching tasks in TaskList based on keywords.
     *
     * @param tasks TaskList containing all current tasks.
     * @param keyword Keyword.
     * @return TaskLst containing only matching tasks.
     */
    public static TaskList findMatches(TaskList tasks, String keyword) {
        TaskList matches = new TaskList();
        for (Task task : tasks.getList()) {
            String desc = task.desc;
            if (desc.contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }

    /**
     * Returns the task of correct type based on input.
     *
     * @param str Input by user.
     * @return Task.
     * @throws DukeException For any invalid inputs.
     */
    public static Task classifyTasks(String str) throws DukeException {
        String[] words = str.split("\\s+");
        int len = words.length;
        assert len > -1 : "Something went wrong with the Parser.classifyTasks() function";
        switch (words[0]) {
            case "todo":
                return giveTodo(words, len);
            case "deadline":
                return giveDeadline(words, len);
            case "event":
                return giveEvent(words, len);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


    /**
     * Creates a ToDo based on user input.
     *
     * @param words Array containing words in input of user.
     * @param len Number of current tasks.
     * @return ToDo.
     * @throws DukeException For any invalid input.
     */
    public static ToDo giveTodo(String[] words, int len) throws DukeException {
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
    }

    /**
     * Creates a Deadline based on user input.
     *
     * @param words Array containing words in input of user.
     * @param len Number of current tasks.
     * @return Deadline.
     * @throws DukeException For any invalid input.
     */
    public static Deadline giveDeadline(String[] words, int len) throws DukeException {
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
    }

    /**
     * Creates an Event based on user input.
     *
     * @param words Array containing words in input of user.
     * @param len Number of current tasks.
     * @return Event.
     * @throws DukeException For any invalid input.
     */
    public static Event giveEvent(String[] words, int len) throws DukeException {
        if (len == 1) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
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
                throw new DukeException("OOPS!!! The date/time of a event cannot be empty.");
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
    }

}
