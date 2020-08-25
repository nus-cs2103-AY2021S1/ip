import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Chatbot {
    private ArrayList<Task> tasksList;
    private int totalTasks;
    private Scanner scanner;
    String filePath = "data/duke.txt";

    public Chatbot(Scanner scanner) {
        this.tasksList = new ArrayList<>();
        this.totalTasks = 0;
        this.scanner = scanner;
    }

    private void writeToFile(String filePath, String textToAdd, boolean isAppending) throws IOException {
        FileWriter fw = new FileWriter(filePath, isAppending);
        fw.write(textToAdd);
        fw.close();
    }

    private void add(Task task) {
        tasksList.add(task);
        totalTasks++;
    }

    private void delete(int taskNumber) {
        tasksList.remove(taskNumber - 1);
        totalTasks--;
    }

    private void listTasks() {
        for (int i = 1; i <= totalTasks; i++) {
            System.out.println(i + "." + tasksList.get(i - 1));
        }
    }

    private void initialise() throws FileNotFoundException {
        try {
            File f = new File("data/duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String nextLine = s.nextLine();
                Scanner s2 = new Scanner(nextLine);
                String taskType = s2.next();
                boolean isDone = false;
                Task currTask;
                if (taskType.equals("T")) {
                    s2.useDelimiter(" ~ ");
                    if (s2.nextInt() == 1) {
                        isDone = true;
                    }
                    currTask = new Todo(s2.next());
                    this.add(currTask);
                    if (isDone) {
                        currTask.markAsDone();
                    }
                } else if (taskType.equals("D")) {
                    s2.useDelimiter(" ~ ");
                    if (s2.nextInt() == 1) {
                        isDone = true;
                    }
                    currTask = new Deadline(s2.next(), s2.next());
                    this.add(currTask);
                    if (isDone) {
                        currTask.markAsDone();
                    }
                } else {
                    s2.useDelimiter(" ~ ");
                    if (s2.nextInt() == 1) {
                        isDone = true;
                    }
                    currTask = new Event(s2.next(), s2.next());
                    this.add(currTask);
                    if (isDone) {
                        currTask.markAsDone();
                    }
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public void start() throws FileNotFoundException {
        try {
            this.initialise();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        String line = this.scanner.nextLine();
        while(!line.equals("bye")) {
            try {
                if (line.contains("done")) {
                    Scanner s2 = new Scanner(line);
                    s2.skip("done");
                    int taskNumber = s2.nextInt();
                    tasksList.get(taskNumber - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" +
                            tasksList.get(taskNumber - 1));
                } else if (line.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    this.listTasks();
                } else if (line.contains("delete")) {
                    Scanner s2 = new Scanner(line);
                    s2.skip("delete");
                    int taskNumber = s2.nextInt();
                    Task taskToDelete = tasksList.get(taskNumber - 1);
                    this.delete(taskNumber);
                    System.out.println("Noted. I've removed this task:\n" +
                            taskToDelete +
                            "\nNow you have " + this.totalTasks + " tasks in the list.");
                } else {
                    Task currTask;
                    if (line.contains("todo")) {
                        Scanner s2 = new Scanner(line);
                        s2.skip("todo");
                        if (s2.hasNext()) {
                            s2.skip(" ");
                            currTask = new Todo(s2.nextLine());
                        } else {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                    } else if (line.contains("deadline")) {
                        Scanner s2 = new Scanner(line);
                        s2.skip("deadline ");
                        s2.useDelimiter(" /by ");
                        currTask = new Deadline(s2.next(), s2.next());
                    } else {
                        if (line.contains("event")) {
                            Scanner s2 = new Scanner(line);
                            s2.skip("event ");
                            s2.useDelimiter(" /at ");
                            currTask = new Event(s2.next(), s2.next());
                        } else {
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        }
                    }
                    this.add(currTask);
                    System.out.println("Got it. I've added this task:\n" +
                            currTask +
                            "\nNow you have " + this.totalTasks + " tasks in the list.");
                }
            } catch (DukeException ex) {
                System.out.println(ex);
            }
            line = this.scanner.nextLine();
        }
        try {
            for (int i = 0; i < totalTasks; i++) {
                if (i == 0) {
                    writeToFile(filePath, tasksList.get(i).getStoringFormat() + System.lineSeparator(), false);
                } else {
                    writeToFile(filePath, tasksList.get(i).getStoringFormat() + System.lineSeparator(), true);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
