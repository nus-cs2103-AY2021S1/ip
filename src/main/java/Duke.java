import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    public static void errorMsg(String msg) {
        System.out.println("⚠ " + msg + " ⚠");
    }
    public static void print(String msg) {
        System.out.println("✰ " + msg + " ✰");
    }
    public static void save(List<Task> taskList) {
        String toWrite = "";
        for (Task t : taskList) {
            toWrite += t.saveText();
            toWrite += "\n";
        }
        try {
            File tasks = new File("duke.txt");
            tasks.createNewFile();
            FileWriter myWriter = new FileWriter(tasks);
            myWriter.write(toWrite);
            myWriter.close();
        } catch (IOException e) {
            errorMsg("An error occurred while saving the task list! D:");
            e.printStackTrace();
        }
    }
    public static List<Task> read() {
        List<Task> result = new ArrayList<Task>();
        File myObj = new File("duke.txt");
        Scanner myReader;
        try {
            if (!myObj.exists()) {
                try {
                    myObj.createNewFile();
                } catch (IOException f) {
                    errorMsg("An error occurred while creating the task list file! D:");
                    f.printStackTrace();
                }
            }
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                result.add(Task.readText(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to read the tasklist save file!");
            e.printStackTrace();
        }
        return result; 
    }
    public static Task getTask(String in) {
        String taskName;
        Task temp = null;
        if (in.startsWith("todo ")){
            taskName = in.substring(5);
            if (taskName.length() == 0) {
                errorMsg("the task description cannot be nothing D:");
            } else {
                temp = new ToDo(taskName);
            }
        } else if (in.startsWith("deadline ")){
            int ind = in.indexOf("/by ");
            if (ind < 0 || ind == in.length() - 4) {
                errorMsg("you haven't entered a time that this task is due by. you can do that by typing \"deadline xxx /by dd/mm/yy hhmm\". \n" +
                        "e.g.: deadline read textbook /by 12/3/20 1500");
            } else if (ind - 1 <= 9) {
                errorMsg("the task description cannot be nothing D:");
            } else {
                taskName = in.substring(9,ind - 1);
                LocalDateTime dead = LocalDateTime.parse(in.substring(ind + 4), DateTimeFormatter.ofPattern("d/M/yy HHmm"));
                temp = new Deadline(taskName,dead);
            }
        } else if (in.startsWith("event ")){
            int ind = in.indexOf("/at ");
            if (ind < 0 || ind == in.length() - 4) {
                errorMsg("you haven't entered a time that this task happens at. you can do that by typing \"event xxx /at dd/mm/yy hhmm\". \n" +
                        "e.g.: event read textbook /at 12/3/20 1500");
            } else if (ind - 1 <= 6) {
                errorMsg("the task description cannot be nothing D:");
            } else {
                taskName = in.substring(6,ind - 1);
                LocalDateTime time = LocalDateTime.parse(in.substring(ind + 4), DateTimeFormatter.ofPattern("d/M/yy HHmm"));
                temp = new Event(taskName,time);
            }
        }
        return temp;
    }
    public static void main(String[] args) {
        print("hi! im conundrum boy :)");
        List<Task> things = read();
        Scanner input = new Scanner(System.in);
        String in;
        while (true) {
            in = input.nextLine();
            if ("bye".equals(in)) {
                break;
            } else if ("".equals(in)) {
                continue;
            } else if ("list".equals(in)) {
                if (things.size() == 0) {
                    print("you haven't added any tasks to the list yet!");
                }
                for (int i = 1; i <= things.size(); i++) {
                    print(i + ". " + things.get(i - 1));
                }
            } else if (in.startsWith("done ")){
                int current;
                try {
                    current = Integer.parseInt(in.substring(5));
                } catch (Exception e){
                    errorMsg("you haven't entered a task number to complete!");
                    continue;
                }
                current--;
                Task task;
                if (current < 0 || current >= things.size()) {
                    errorMsg("that is not the number of a task in the list!");
                } else {
                    task = things.get(current);
                    if (task.done) {
                        errorMsg("you have already completed " + task.task + "!");
                        continue;
                    }
                    task.complete();
                    print("congrats on finishing your task :) it's marked as done:\n\t" + task);

                }
            } else if (in.startsWith("delete ")){
                int current;
                try {
                    current = Integer.parseInt(in.substring(7));
                } catch (Exception e){
                    errorMsg("you haven't entered a task number to delete!");
                    continue;
                }
                current--;
                Task task;
                if (current < 0 || current >= things.size()) {
                    errorMsg("that is not the number of a task in the list!");
                } else {
                    task = things.get(current);
                    things.remove(current);
                    print("i've removed the following task from the list:\n\t" + task + "\nnow you have " + things.size() + " items in your tasklist.");

                }
            } else if (in.startsWith("todo ") || in.startsWith("deadline ") || in.startsWith("event ")){
                Task temp = getTask(in);
                if (temp != null) {
                    things.add(temp);
                    print("i've added this task for you: \n\t" + temp + "\nnow you have " + things.size() + " items in your tasklist.");
                }
            } else {
                errorMsg("i don't know what that means :(");
            }
            save(things);

        }
        print("bye bye!");

    }
}
