import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Chatbot {
    private int lengthOfLine = 55;
    private ArrayList<Task> tasks = new ArrayList<>();
    private String directoryPath = "data";
    private String filePath = directoryPath + "/duke.txt";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    public String getHorizontalLine() {
        String line = "";
        for (int i = 0; i < lengthOfLine; i++) {
            line = line + "-";
        }
        return line;
    }

    public void readFile() {
        // load the file first
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File f = new File(filePath);
        try {
            if (f.createNewFile()) {
                // file does not exist
                System.out.println("A data file has been created for you");
            } else {
                // file exist
                System.out.println("Here are your existing tasks");
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String taskStr = s.nextLine();
                    System.out.println(taskStr);
                    // load the task
                    Task savedTask;
                    String[] split = taskStr.split(Pattern.quote(" | "));
                    String description = split[2];
                    boolean isDone = (Integer.parseInt(split[1]) == 1);
                    if (split[0].equals("T")) {
                        // create a todo
                        savedTask = new Todo(description, isDone);
                    } else if (split[0].equals("D")) {
                        // create a deadline
                        String dateStr = split[3];
                        LocalDate date = LocalDate.parse(dateStr, formatter);
                        savedTask = new Deadline(description, isDone, date);
                    } else {
                        // create an event
                        String dateStr = split[3];
                        LocalDate date = LocalDate.parse(dateStr, formatter);
                        savedTask = new Event(description, isDone, date);
                    }
                    tasks.add(savedTask);
                }
                System.out.println("What do you feel like doing today?");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTasks() {
        for (Task t: tasks) {
            String taskData = t.getData();
            appendToFile(filePath, taskData);
        }
    }

    public void writeToFile(String filePath, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void appendToFile(String filePath, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAdd + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String[] parseString(String str) throws DukeException {
        String[] split1 = str.split(" ", 2);
        if (split1.length < 2 || split1[1].equals("")) {
            if (split1[0].equals("list") || split1[0].equals("bye")) {
                String[] res = {split1[0]};
                return res;
            } else if (split1[0].equals("done")) {
                throw new DukeException("Please tell me the task number that you have completed.");
            } else if (split1[0].equals("date")) {
                throw new DukeException("Please provide a date.");
            } else if (split1[0].equals("delete")) {
                throw new DukeException("Please tell me the task number that you want to delete.");
            } else if (split1[0].equals("todo")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } else if (split1[0].equals("deadline")) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            } else if (split1[0].equals("event")) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } else {
            String type = split1[0];
            String temp = split1[1];
            if (temp.contains("/")) {
                // deadline, event
                String[] split2;
                if (temp.contains("/by")) {
                    split2 = temp.split(" /by", 2);
                } else {
                    split2 = temp.split(" /at", 2);
                }
                String taskDescription = split2[0];
                String time = split2[1];
                String[] res = {type, taskDescription, time};
                return res;
            } else {
                // todo or date command
                String taskDescription = temp;
                String[] res = {type, taskDescription};
                return res;
            }
        }
    }

    public void listTasks() throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException("You don't have any tasks.");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int taskNo = i + 1;
            Task task = tasks.get(i);
            System.out.println(taskNo + "." + task);
        }
    }

    public void listTasksOn(LocalDate date) throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException("You don't have any tasks.");
        }
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task: tasks) {
            LocalDate taskDate = task.getDate();
            if (taskDate != null) {
                if (taskDate.isEqual(date)) {
                    taskList.add(task);
                }
            }
        }

        if (taskList.size() > 0) {
            System.out.println("Here are the tasks happening on: " + date.format(formatter));
            for (Task task: taskList) {
                System.out.println(task);
            }
        } else {
            System.out.println("You don't have anything on: " + date.format(formatter) + " :)))");
        }
    }

    public void doneTasks(int taskNo) {
        System.out.println("Nice! I've marked this task as done:");
        Task completedTask = tasks.get(taskNo - 1);
        completedTask.markAsDone();
        System.out.println(" " + " " +
                "[" + completedTask.getStatusIcon() + "] " +
                completedTask.description);
    }

    public void addTask(String type, String description) {
        // todo
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addTask(String type, String description, LocalDate time) {
        // deadline and event
        Task newTask;
        if (type.equals("deadline")) {
            newTask = new Deadline(description, time);
        } else {
            newTask = new Event(description, time);
        }
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void deleteTask(int taskNo) throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException("Nothing to delete.");
        }
        System.out.println("Noted. I've removed this task:");
        Task taskToBeDeleted = tasks.get(taskNo - 1);
        tasks.remove(taskNo - 1);
        System.out.println(" " + " " +
                "[" + taskToBeDeleted.getStatusIcon() + "] " +
                taskToBeDeleted.description);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void greet() {
        String logo =
                "                 .   ,\n" +
                "               .';_.';\n" +
                "                    _   \\\n" +
                "             .     (.) (.)--._\n" +
                "            .       \"   \"     `.\n" +
                "           .                   :\n" +
                "          .           `\"-.___.\"\n" +
                "         .   .         `.\n" +
                "         .    .  `.      .\n" +
                ",,.      .      ` . `.    .\n" +
                "\\W;      .         \"`     .\n" +
                "   `--'    ,    __,..-   '  .\n" +
                "          .   .'     `.   `' ;\n" +
                "          `.   `,      `.  .'\n" +
                "            \"._.'        `'";
        System.out.println("Hello from Moomin\n" + logo);
        System.out.println("I'm your personal assistant.");
    }
}
