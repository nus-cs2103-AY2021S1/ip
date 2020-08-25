import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sparkles {

    private static final String DASH = "     ____________________________________________________________";
    private static final List<Task> STORAGE = new ArrayList<>();

    private void respond(String str) {
        String lowerCase = str.toLowerCase();

        printDASH();

        if (lowerCase.equals("list")) {
            showList();
        } else if (lowerCase.startsWith("done")) {
            markAsDone(str);
        } else if (lowerCase.equals("bye")) {
            exit();
        } else if (lowerCase.startsWith("delete")) {
            delete(str);
        } else {
            addToList(str);
        }

        printDASH();
        updateFile();
    }

    private void updateFile() {
        String userDir = new File("").getAbsolutePath();
        String taskFilePath = userDir + File.separator + "Sparkles.txt";

        FileWriter fileWriter;

        try {
            fileWriter = new FileWriter(taskFilePath);
            StringBuilder append = new StringBuilder();
            for (Task task : STORAGE) {
                append.append(task.diskFormat()).append("\n");
            }

            fileWriter.write(append.toString());
            fileWriter.close();
        } catch (IOException e) {
            print("Task file does not exist");
        }
    }
    
    private void showList() {
        if (STORAGE.size() == 0) {
            print("     Tasks list is Empty.");
        } else {
            for (int i = 1; i < STORAGE.size() + 1; i++) {
                Task task = STORAGE.get(i - 1);
                task.printTask(i);
            }
        }
    }

    private void markAsDone(String str) {
        int index;

        try {
            index = Integer.parseInt(String.valueOf(str.charAt(5)));
            Task task = STORAGE.get(index - 1);
            task.markAsDone();
            print("     Nice! I have marked this task as done :-)");
        } catch (Exception ex) {
            if (ex instanceof IndexOutOfBoundsException) {
                if(STORAGE.isEmpty()) {
                    print("     OOPS!! Task list is empty!");
                } else {
                    print("     OOPS!! Task does not exist!");
                }
            } else {
                print("     OOPS!! Task in the list to be marked as done is not specified!");
            }
        }
    }

    private void exit() {
        print("     Bye. Hope to see you again!");
        printDASH();
        System.exit(0);
    }

    private void delete(String str) {
        int index;

        try {
            index = Integer.parseInt(String.valueOf(str.charAt(7)));
            Task task = STORAGE.get(index - 1);
            print("     Noted, I have removed this task:");
            task.printTask();
            STORAGE.remove(index - 1);
            printListSize();
        } catch (Exception ex) {
                if (ex instanceof IndexOutOfBoundsException) {
                    if(STORAGE.isEmpty()) {
                        print("     OOPS!! Task list is empty!");
                    } else {
                        print("     OOPS!! Task does not exist!");
                    }
                } else {
                    print("     OOPS!! Task in the list to be deleted is not specified!");
                }
        }
    }

    private void addToList(String str) {
        String lowerCase = str.toLowerCase();

        String[] arr;

        if (lowerCase.startsWith("deadline")) {
            arr = str.split(" /by ");

            addDeadline(arr);
        } else if (lowerCase.startsWith("event")) {
            arr = str.split(" /at ");

            addEvent(arr);
        } else if ((lowerCase.startsWith("todo"))) {
            addTodo(str);
        } else {
            print("     Task need to be more specific!");
        }
    }

    private void addTodo(String str) {
        String desc;
        Task task;
        try {
            desc = str.substring(5).trim();
            task = new Todo(desc);
            print("     Got it. I've added this task");
            task.printTask();

            STORAGE.add(task);
            printListSize();
        } catch (Exception ex) {
            print("     OOPS!! The description of a todo cannot be empty!");
        }
    }

    private void addEvent(String[] arr) {
        String desc;
        Task task;
        String at;

        try {
            desc = arr[0];
            at = arr[1];
            task = new Event(desc, at);
            print("     Got it. I've added this task");
            task.printTask();

            STORAGE.add(task);
            printListSize();
        } catch (Exception ex) {
            print("     OOPS!! The description and time of an Event cannot be empty!");
        }
    }

    private void addDeadline(String[] arr) {
        String desc;
        Task task;
        String by;

        try {
            desc = arr[0];
            by = arr[1];
            task = new Deadline(desc, by);
            print("     Got it. I've added this task");
            task.printTask();

            STORAGE.add(task);
            printListSize();
        } catch (Exception ex) {
            print("     OOPS!! The description and deadline of a Deadline cannot be empty!");
        }
    }
    
    private void printListSize() {
        String output = "     Now you have " + STORAGE.size() + " task(s) in your list.";
        print(output);
    }

    private void run(String str) {
        printResult(str);
    }

    private void printResult(String str){
        respond(str);
    }

    private void print(String str) {
        System.out.println(str);
    }

    private void printDASH() {
        print(DASH);
    }

    private void welcome() throws IOException {
        System.out.println(DASH);
        System.out.println("     *Hello, I am Sparkles*\n\n     How can I help you?\n");
        readFromFile();
        showList();
        System.out.println(DASH);
    }

    private void readFromFile() throws IOException {
        String userDir = new File("").getAbsolutePath();
        String taskFilePath = userDir + File.separator + "Sparkles.txt";

        File taskFile = new File(taskFilePath);

        try {
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNextLine()) {
                Task task = decodeTask(sc.nextLine());
                STORAGE.add(task);
            }
        } catch (FileNotFoundException ex) {
            taskFile.createNewFile();
        }
    }

    private Task decodeTask(String next) {
        Task task;

        next = next.substring(5);
        String[] arr = next.split(" \\| ");

        if (arr[0].equals("T")) {
            task = new Todo(arr[2]);
        } else if (arr[0].equals("D")) {
            task = new Deadline(arr[2], arr[3]);
        } else {
            task = new Event(arr[2], arr[3]);
        }
        if (arr[1].equals("\u2713")) {
            task.markAsDone();
        }
        return task;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Sparkles sparkles = new Sparkles();
        sparkles.welcome();
        while (sc.hasNext()) {
            sparkles.run(sc.nextLine());
        }
    }
}
