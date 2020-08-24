import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String path = currentDir + File.separator + "data" + File.separator + "duke.txt";

        new Duke(path).run();
    }

//    static ArrayList<Task> tasks = new ArrayList<>();
//    static int total = 0;
//
//    public static void readFile() {
//        String currentDir = System.getProperty("user.dir");
//        String path = currentDir + File.separator + "data" + File.separator + "duke.txt";
//        File f = new File(path);
//        if (f.exists() && !f.isDirectory()) {
//            try {
//                for (String line : Files.readAllLines(Paths.get(path))) {
//                    String[] data = line.split(" \\| ");
//                    String taskType = data[0];
//
//                    switch (taskType) {
//                        case "T":
//                            Todo todo = new Todo(data[2]);
//                            if (data[1].equals("1")) {
//                                todo.markAsDone();
//                            }
//                            tasks.add(todo);
//                            total++;
//                            break;
//                        case "D":
//                            Deadline deadline = new Deadline(data[2], data[3]);
//                            if (data[1].equals("1")) {
//                                deadline.markAsDone();
//                            }
//                            tasks.add(deadline);
//                            total++;
//                            break;
//                        case "E":
//                            Event event = new Event(data[2], data[3]);
//                            if (data[1].equals("1")) {
//                                event.markAsDone();
//                            }
//                            tasks.add(event);
//                            total++;
//                            break;
//                    }
//                }
//            } catch (IOException ex) {
//                System.out.println("Error reading file" + ex);
//            }
//        } else {
//            try {
//                f.getParentFile().mkdirs();
//                f.createNewFile();
//            } catch (IOException ex) {
//                System.out.println("Error creating file" + ex);
//            }
//        }
//    }
//
//    public static void getInputs() {
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            String input = sc.nextLine();
//
//            if (input.equals("bye")) {
//                System.out.println("    ___________________________________________________________");
//                System.out.println("    Bye. Hope to see you again soon!");
//                System.out.println("    ___________________________________________________________");
//                return;
//            }
//
//            if (input.equals("list")) {
//                handleList();
//            } else {
//                try {
//                    if (input.contains("done")) {
//                        handleDone(input);
//                    } else if (input.contains("todo")) {
//                        handleTodo(input);
//                    } else if (input.contains("deadline")) {
//                        handleDeadline(input);
//                    } else if (input.contains("event")) {
//                        handleEvent(input);
//                    } else if (input.contains("delete")) {
//                        handleDelete(input);
//                    } else {
//                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                    }
//                } catch (DukeException ex) {
//                    System.out.println("    ___________________________________________________________");
//                    System.out.println("    " + ex);
//                    System.out.println("    ___________________________________________________________");
//                }
//            }
//        }
//    }
//
//    public static void handleList() {
//        System.out.println("    ___________________________________________________________");
//        System.out.println("    Here are the tasks in your list:");
//        for (int i = 0; i < tasks.size(); i++) {
//            Task task = tasks.get(i);
//            System.out.println("    " + (i + 1) + "." + task);
//        }
//        System.out.println("    ___________________________________________________________");
//    }
//
//    public static void handleDone(String input) throws DukeException {
//        String[] splitStr = input.split(" ");
//        if (splitStr.length == 1) {
//            throw new DukeException("☹ OOPS!!! I don't know which task to mark as done.");
//        }
//        int taskIndex = Integer.parseInt(splitStr[1]) - 1;
//        Task task = tasks.get(taskIndex);
//        task.markAsDone();
//        if (task.getTaskType().equals("T")) {
//            editCurrentDataInFile(taskIndex, task.getTaskType(), "1", task.getDescription(), "");
//        } else if (task.getTaskType().equals("D")) {
//            Deadline deadline = (Deadline) task;
//            editCurrentDataInFile(taskIndex + 1, task.getTaskType(), "1", task.getDescription(),
//                    deadline.getBy());
//        } else {
//            Event event = (Event) task;
//            editCurrentDataInFile(taskIndex, task.getTaskType(), "1", task.getDescription(),
//                    event.getTime());
//        }
//        System.out.println("    ___________________________________________________________");
//        System.out.println("    Nice! I've marked this task as done:\n      " + task);
//        System.out.println("    ___________________________________________________________");
//    }
//
//    public static void handleTodo(String input) throws DukeException {
//        if (input.length() <= 5) {
//            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
//        }
//        Todo todo = new Todo(input.substring(5));
//        tasks.add(todo);
//        total++;
//        writeNewDataToFile("T", "0", todo.getDescription(), "");
//        System.out.println("    ___________________________________________________________");
//        System.out.println("    Got it. I've added this task:\n      " + todo + "\n    Now you have "
//                + total + " tasks in the list.");
//        System.out.println("    ___________________________________________________________");
//    }
//
//    public static void handleDeadline(String input) throws DukeException {
//        if (input.length() <= 9 || !input.contains("/by")) {
//            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
//        }
//        String[] splitStr = input.split("/by ");
//        String description = splitStr[0].substring(9).trim();
//        Deadline deadline = new Deadline(description, splitStr[1]);
//        tasks.add(deadline);
//        total++;
//        writeNewDataToFile("D", "0", deadline.getDescription(), deadline.getBy());
//        System.out.println("    ___________________________________________________________");
//        System.out.println("    Got it. I've added this task:\n      " + deadline + "\n    Now you have "
//                + total + " tasks in the list.");
//        System.out.println("    ___________________________________________________________");
//    }
//
//    public static void handleEvent(String input) throws DukeException {
//        if (input.length() <= 6 || !input.contains("/at")) {
//            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
//        }
//        String[] splitStr = input.split("/at ");
//        String description = splitStr[0].substring(6).trim();
//        Event event = new Event(description, splitStr[1]);
//        tasks.add(event);
//        total++;
//        writeNewDataToFile("E", "0", event.getDescription(), event.getTime());
//        System.out.println("    ___________________________________________________________");
//        System.out.println("    Got it. I've added this task:\n      " + event + "\n    Now you have "
//                + total + " tasks in the list.");
//        System.out.println("    ___________________________________________________________");
//    }
//
//    public static void handleDelete(String input) throws DukeException {
//        String[] splitStr = input.split(" ");
//        if (splitStr.length == 1) {
//            throw new DukeException("☹ OOPS!!! I don't know which task to delete.");
//        }
//        int taskIndex = Integer.parseInt(splitStr[1]) - 1;
//        Task task = tasks.get(taskIndex);
//        tasks.remove(taskIndex);
//        total--;
//        deleteCurrentDataInFile(taskIndex + 1);
//        System.out.println("    ___________________________________________________________");
//        System.out.println("    Noted. I've removed this task:\n      " + task + "\n    Now you have "
//                + total + " tasks in the list.");
//        System.out.println("    ___________________________________________________________");
//    }
//
//    public static void writeNewDataToFile(String taskType, String done, String description, String deadline) {
//        String currentDir = System.getProperty("user.dir");
//        String path = currentDir + File.separator + "data" + File.separator + "duke.txt";
//        try {
//            FileWriter myWriter = new FileWriter(path, true);
//            switch (taskType) {
//                case "T":
//                    myWriter.write("\n" + taskType + " | " + done + " | " + description);
//                    myWriter.close();
//                    break;
//                case "D":
//                case "E":
//                    myWriter.write("\n" + taskType + " | " + done + " | " + description + " | " + deadline);
//                    myWriter.close();
//            }
//        } catch (IOException ex) {
//            System.out.println("Problem writing to file" + ex);
//        }
//    }
//
//    public static void editCurrentDataInFile(int taskNumber, String taskType, String done,
//                                             String description, String deadline) {
//        try {
//            String currentDir = System.getProperty("user.dir");
//            String pathToFile = currentDir + File.separator + "data" + File.separator + "duke.txt";
//            BufferedReader br = new BufferedReader(new FileReader(pathToFile));
//
//            //String buffer to store contents of the file
//            StringBuffer sb = new StringBuffer("");
//
//            //Keep track of the line number
//            int linenumber = 1;
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                //Store each valid line in the string buffer
//                if (linenumber != taskNumber && linenumber != total) {
//                    sb.append(line + "\n");
//                } else if (linenumber != taskNumber) {
//                    sb.append(line);
//                } else {
//                    String data;
//                    if (taskType.equals("T")) {
//                        data = taskType + " | " + done + " | " + description;
//                    } else {
//                        data = taskType + " | " + done + " | " + description + " | " + deadline;
//                    }
//
//                    if (linenumber == total) {
//                        sb.append(data);
//                    } else {
//                        sb.append(data + "\n");
//                    }
//                }
//                linenumber++;
//            }
//            br.close();
//
//            FileWriter fw = new FileWriter(new File(pathToFile));
//            //Write entire string buffer into the file
//            fw.write(sb.toString());
//            fw.close();
//        }
//        catch (IOException e) {
//            System.out.println("Error editing file: " + e.getMessage());
//        }
//    }
//
//    public static void deleteCurrentDataInFile(int taskNumber) {
//        try {
//            String currentDir = System.getProperty("user.dir");
//            String pathToFile = currentDir + File.separator + "data" + File.separator + "duke.txt";
//            BufferedReader br = new BufferedReader(new FileReader(pathToFile));
//
//            //String buffer to store contents of the file
//            StringBuffer sb = new StringBuffer("");
//
//            //Keep track of the line number
//            int linenumber = 1;
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                //Store each valid line in the string buffer
//                if (linenumber != taskNumber && linenumber == total + 1) {
//                    sb.append(line);
//                } else if (linenumber != taskNumber) {
//                    sb.append(line + "\n");
//                }
//                linenumber++;
//            }
//            br.close();
//
//            FileWriter fw = new FileWriter(new File(pathToFile));
//            //Write entire string buffer into the file
//            fw.write(sb.toString());
//            fw.close();
//        }
//        catch (Exception ex) {
//            System.out.println("Error deleting line: " + ex.getMessage());
//        }
//    }

//    public static void main(String[] args) {
//        readFile();
//        System.out.println("    ___________________________________________________________");
//        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
//        System.out.println("    ___________________________________________________________");
//        getInputs();
//    }
}
