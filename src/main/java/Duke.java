import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
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
    
    public static void printAllTask() {
        int numTask = 0;
        System.out.println("Here are the tasks in your list:");
        while (numTask < tasks.size()) {
            System.out.println(Integer.valueOf(numTask + 1) + "." + tasks.get(numTask));
            numTask++;
        }
    }

    public static void printAddedTask(Task task) {
        System.out.println("Got it. I've added this task:\n" + task +
                "\nNow you have " + String.valueOf(tasks.size()) + " tasks in the list.");
    }

    public static String[] removeFirst(String[] arr) {
        String[] tempArr = new String[arr.length];
        for (int i = 0; i < arr.length - 1; i++) {
            tempArr[i] = arr[i + 1];
        }
        return tempArr;
    }

    public static String[] removeAfterWord(String[] arr, String word) {
        String[] temp = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(word)) {
                break;
            } else {
                temp[i] = arr[i];
            }
        }
        return temp;
    }

    public static String[] keepAfterWord(String[] arr, String word) {
        String[] temp = new String[arr.length];
        int counter = 0;
        // find position of the word
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(word)) {
                counter = i;
                break;
            }
        }
        counter++;
        for (int i = 0; (counter + i) < arr.length; i++) {
            temp[i] = arr[counter + i];
        }
        return temp;
    }

    public static String joinString(String[] arr) {
        String text = arr[0];
        if (arr.length == 1) {
            return text;
        } else {
            for (int i = 1; i < arr.length && arr[i] != null; i++) {
                text = text + " " + arr[i];
            }
        }
        return text;
    }

    public static void addNewTask(String[] task, int pos)
            throws InvalidTodoDescripDukeException, InvalidDeadlineDescripDukeException, InvalidEventDescripDukeException,
            InvalidFirstDukeException, IOException {


        if (task[0].equals("todo")) {
            if (task.length == 1) {
                throw new InvalidTodoDescripDukeException();
            }
            String[] modifiedTask = removeFirst(task);
            tasks.add(new Todo(joinString(modifiedTask)));
            printAddedTask(tasks.get(pos));
        } else if (task[0].equals("deadline")) {
            if (task.length == 1) {
                throw new InvalidDeadlineDescripDukeException();
            }
            String[] modifiedTask = removeFirst(task);
            String[] upper = removeAfterWord(modifiedTask, "/by");
            String[] lower = keepAfterWord(modifiedTask, "/by");
            tasks.add(new Deadline(joinString(upper), joinString(lower)));
            printAddedTask(tasks.get(pos));
        } else if (task[0].equals("event")) {
            if (task.length == 1) {
                throw new InvalidEventDescripDukeException();
            }
            String[] modifiedTask = removeFirst(task);
            String[] upper = removeAfterWord(modifiedTask, "/at");
            String[] lower = keepAfterWord(modifiedTask, "/at");
            tasks.add(new Event(joinString(upper), joinString(lower)));
            printAddedTask(tasks.get(pos));
        } else {
            throw new InvalidFirstDukeException();
        }
        DukeFileWriter.writeToFile("data/duke.txt", tasks);
    }

    public static void deleteTask(int pos) {
        System.out.println("Noted. I've removed this task: \n" + tasks.get(pos) +
                "\n" + "Now you have " + Integer.valueOf(tasks.size() - 1) + " tasks in the list.");
        tasks.remove(pos);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int numTask = 0;

        greeting();
        String input;
        String[] inputArr;

        while (true) {
            input = sc.nextLine(); // original input line
            inputArr = input.split(" "); // split input into string array

            try {
                if (inputArr[0].equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    sc.close();
                    break;
                } else if (inputArr[0].equals("list")) {
                    printAllTask();
                } else if (inputArr[0].equals("done")) {
                    int counter = Integer.parseInt(inputArr[1]);
                    tasks.get(counter - 1).markAsDone();
                } else if (inputArr[0].equals("delete")) {
                    int counter = Integer.parseInt(inputArr[1]);
                    deleteTask(counter - 1);
                } else {
                    addNewTask(input.split(" "), numTask);
                    numTask++;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
