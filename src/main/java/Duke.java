import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum ListChange
{
    ADD, DELETE
}

public class Duke {
    public static String getFilePath() {
        try {
            boolean doesDataExist = new File("./data").exists();
            if (!doesDataExist) {
                new File("./data").mkdir();
                new File("./data/duke.txt").createNewFile();
            }
        } catch (IOException e){
            System.out.println("Something went wrong in creating files");
        }
        return "./data/duke.txt";
    }
    
    public static List<Task> getTasks(String filePath) {
        List<Task> tasks = new ArrayList<>();
        
        try {
            ReadFile file = new ReadFile(filePath);
            String[] dataArr = file.openFile();
            for (int i = 0; i < dataArr.length; ++i) {
                tasks.add(Task.taskify(dataArr[i]));
            }
        } catch (IOException e) {
            System.out.println("Something went wrong in reading data...");
        }
        
        return tasks;
    }

    public static String numTasks(List<Task> lst) {
        int numTasks = lst.size();
        return numTasks == 1 ? "1 task" : numTasks + " tasks";
    }
    
    public static void listChangePrint(Task task, List<Task> taskList, ListChange change) {
        String keyword = "";
        switch (change) {
            case ADD:
                keyword = "added";
                break;
            case DELETE:
                keyword = "removed";
        }

        System.out.println("Noted. I've " + keyword + " this task:\n" + task.toString());
        System.out.println("Now you have " + numTasks(taskList) + " in the list.");
    }

    public static void main(String[] args) {
        String filePath = getFilePath();
        List<Task> tasks = getTasks(filePath);
        
        System.out.println("————————————————————————————————————————————————————————————");
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?");
        System.out.println("————————————————————————————————————————————————————————————");

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println("————————————————————————————————————————————————————————————");
            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).toString());
                }
            } else {
                String[] command_lst = command.split(" ", 2);
                String action = command_lst[0];
                
                switch (action) {
                    case "done":
                        try {
                            int taskId = Integer.parseInt(command.split(" ")[1]) - 1;
                            tasks.get(taskId).markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(tasks.get(taskId).toString());
                        } catch (Exception e) {
                            new DukeException("invalidMarkingDone");
                        }
                        break;
                    case "todo":
                        try {
                            Task newTask = new ToDo(command_lst[1]);
                            tasks.add(newTask);
                            listChangePrint(newTask, tasks, ListChange.ADD);
                        } catch (Exception e) {
                            new DukeException("invalidTodo");
                        }
                        break;
                    case "deadline":
                        try {
                            String desc = command_lst[1].split(" /by ")[0];
                            String by = command_lst[1].split(" /by ")[1];
                            Task newTask = new Deadline(desc, by);
                            tasks.add(newTask);
                            listChangePrint(newTask, tasks, ListChange.ADD);
                        } catch (Exception e) {
                            new DukeException("invalidDeadlineTask");
                        }
                        break;
                    case "event":
                        try {
                            String desc = command_lst[1].split(" /at ")[0];
                            String at = command_lst[1].split(" /at ")[1];
                            Task newTask = new Event(desc, at);
                            tasks.add(newTask);
                            listChangePrint(newTask, tasks, ListChange.ADD);
                        } catch (Exception e) {
                            new DukeException("invalidEvent");
                        }
                        break;
                    case "delete":
                        try {
                            int taskId = Integer.parseInt(command.split(" ")[1]) - 1;
                            Task removedTask = tasks.remove(taskId);
                            listChangePrint(removedTask, tasks, ListChange.DELETE);
                        } catch (Exception e) {
                            new DukeException("invalidDelete");
                        }
                        break;
                    default:
                        new DukeException("invalidCommand");
                }
                
                // Save tasks in hard disk
                try {
                    if (tasks.size() == 0) {
                        WriteFile emptyData = new WriteFile(filePath);
                        emptyData.writeToFile("");
                    } else {
                        WriteFile firstData = new WriteFile(filePath);
                        firstData.writeToFile(tasks.get(0).toString());

                        if (tasks.size() > 1) {
                            WriteFile appendData = new WriteFile(filePath, true);
                            for (int i = 1; i < tasks.size(); ++i) {
                                appendData.writeToFile(tasks.get(i).toString());
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Something went wrong in writing data...");
                }
            }
            
            System.out.println("————————————————————————————————————————————————————————————");
            command = sc.nextLine();
        }

        System.out.println("————————————————————————————————————————————————————————————");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("————————————————————————————————————————————————————————————");
        sc.close();
    }
}
