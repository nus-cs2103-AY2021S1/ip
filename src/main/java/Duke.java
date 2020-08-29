import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {
    public static void invalidInput() throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

    public static void printMessage(String s) {
        String output = String.format("____________________________________________________________\n%s\n" +
                "____________________________________________________________\n",s);
        System.out.println(output);
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void saveToDisk(ArrayList<Task> lst) {
        String filePath = "./data/data.txt";
        String info = "";
        for (int i = 0; i < lst.size(); i ++) {
            Task currentTask = lst.get(i);
            if (i == lst.size() - 1){
                info += currentTask.toText();
            }
            else {
                info += currentTask.toText() + System.lineSeparator();
            }
        }
        try {
            writeToFile(filePath, info);
        } catch (IOException e) {
            System.out.println("Error saving data to disk");
            e.printStackTrace();
        }
    }

    public static void saveDataToList(String content, ArrayList<Task> lst) {
        Scanner s = new Scanner(content);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] arr = line.split("\\|");
            String type = arr[0].trim();
            int status = Integer.parseInt(arr[1].trim());
            String description = arr[2].trim();
            if (type.equals("T")) {
                lst.add(new ToDo(description, status == 1));
            } else {
                String additionalInfo = arr[3].trim();
                if (type.equals("D")) {
                    lst.add(new Deadline(description, additionalInfo, status == 1));
                } else {
                    lst.add(new Event(description, additionalInfo, status == 1));
                }
            }
        }
    }
    public static String readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        String content = "";
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            content += s.nextLine() + System.lineSeparator();
        }
        return content;
    }
    public static Task createTask(String firstWord, String input) throws DukeException {
        Task newTask;
        if (firstWord.equals("todo")) {
            String[] arr = input.split("todo ");
            if (arr.length == 1) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else {
                String tsk = arr[1];
                newTask = new ToDo(tsk);
            }
        } else if (firstWord.equals("deadline")) {
            String[] arr = input.split("deadline ");
            if (arr.length == 1) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else {
                String[] split = input.split("/by ");
                if (split.length == 1) {
                    throw new DukeException("By when??? You didn't include your deadline.");
                } else {
                    String deadline = split[1];
                    String tsk = split[0].split("deadline ")[1];
                    newTask = new Deadline(tsk, deadline);
                }
            }
        } else {
            String[] arr = input.split("event ");
            if (arr.length == 1) {
                throw new DukeException("The description of an event cannot be empty.");
            } else {
                String[] split = input.split("/at ");
                if (split.length == 1) {
                    throw new DukeException("At??? You didn't include the time of the event.");
                } else {
                    String at = split[1];
                    String tsk = split[0].split("event ")[1];
                    newTask = new Event(tsk, at);
                }
            }
        }
        return newTask;
    }
    public static void mainLogic(Scanner sc, ArrayList<Task> lst) {

        while (true) {
            String input = sc.nextLine();
            String firstWord = input.split(" ")[0];
            String output = "";
            if (input.equals("bye")) {
                saveToDisk(lst);
                output = "Bye. Take care!";
            } else if (input.equals("list")) {
                output += "Here are the tasks in your list:";
                for (int i = 0; i < lst.size(); i ++) {
                    Task currentTask = lst.get(i);
                    String num = Integer.toString(i + 1);
                    output += "\n" + num + "." + currentTask;
                }
            } else if (firstWord.matches("done|delete")) {
                String[] splitted = input.split("\\s+");
                int taskIndex = Integer.parseInt(splitted[1]) - 1;
                Task selectedTask = lst.get(taskIndex);
                if (firstWord.equals("done")) {
                    selectedTask.markAsDone();
                    output += "Nice! I've marked this task as done:\n  " + selectedTask;
                } else {
                    lst.remove(taskIndex);
                    output += "Noted. I've removed this task:\n  " + selectedTask;
                }
                output += "\nNow you have " + lst.size() + " tasks in the list.";
            } else if (firstWord.matches("todo|deadline|event")) {
                Task newTask;
                try {
                    newTask = createTask(firstWord, input);
                    lst.add(newTask);
                    output += "Got it. I've added this task:\n  " + newTask;
                    output += "\nNow you have " + lst.size() + " tasks in the list.";
                    printMessage(output);
                } catch (DukeException e) {
                    output += "☹ OOPS!!! " + e.getMessage();
                    printMessage(output);
                } finally {
                    mainLogic(sc, lst);
                }
            } else {
                try {
                    invalidInput();
                } catch (DukeException e) {
                    output += "☹ OOPS!!! " + e.getMessage();
                    printMessage(output);
                } finally {
                    mainLogic(sc, lst);
                }
            }
            printMessage(output);
        }
    }
    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke, your personal assistant.\nWhat can I do for you?";
        printMessage(greetings);

        ArrayList<Task> lst = new ArrayList<>();

        String filePath = "./data/data.txt";
        try {
            File f = new File(filePath);
            if (f.exists()) {
                String content = readFileContents(filePath);
                saveDataToList(content, lst);
            } else {
                f.createNewFile();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read contents. File not found");
        } catch (IOException e) {
            System.out.println("Unable to create new file.");
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        mainLogic(sc, lst);
    }
}
