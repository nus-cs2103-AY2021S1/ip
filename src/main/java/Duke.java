import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static Task[] tasks = new Task[100];

    public static void greeting() {
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greeting);
    }

    public static void printAllTask() {
        int numTask = 0;
        System.out.println("Here are the tasks in your list:");
        while (tasks[numTask] != null) {
            System.out.println(Integer.valueOf(numTask + 1) + "." + tasks[numTask]);
            numTask++;
        }
    }

    public static void printAddedTask(Task task) {
        int len;
        for(len = 0; len < tasks.length; len++) {
            if (tasks[len] == null) {
                break;
            }
        }
        System.out.println("Got it. I've added this task: \n" + task +
                "\nNow you have " + String.valueOf(len)  + " tasks in the list.");
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
        for(int i = 0; i < arr.length; i++) {
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
        for(int i = 0; i < arr.length; i++) {
            if (arr[i].equals(word)) {
                counter = i;
                break;
            }
        }
        counter++;
        for(int i = 0; (counter + i) < arr.length; i++) {
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

    public static void addNewTask(String[] task, int pos) {
        if (task[0].equals("todo")) {
            String[] modifiedTask = removeFirst(task);
            tasks[pos] = new Todo(joinString(modifiedTask));
            printAddedTask(tasks[pos]);
        } else if (task[0].equals("deadline")) {
            String[] modifiedTask = removeFirst(task);
            String[] upper = removeAfterWord(modifiedTask, "/by");
            String[] lower = keepAfterWord(modifiedTask, "/by");
            tasks[pos] = new Deadline(joinString(upper), joinString(lower));
            printAddedTask(tasks[pos]);
        } else if (task[0].equals("event")) {
            String[] modifiedTask = removeFirst(task);
            String[] upper = removeAfterWord(modifiedTask, "/at");
            String[] lower = keepAfterWord(modifiedTask, "/at");
            tasks[pos] = new Event(joinString(upper), joinString(lower));
            printAddedTask(tasks[pos]);
        } else {
            System.out.println("error");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numTask = 0;

        greeting();
        String input;
        String[] inputArr;

        while(true) {
            input = sc.nextLine(); // original input line
            inputArr = input.split(" "); // split input into string array

            if (inputArr[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                break;
            } else if (inputArr[0].equals("list")) {
                printAllTask();
            } else if (inputArr[0].equals("done")) {
                int counter =  Integer.parseInt(inputArr[1]);
                tasks[counter - 1].markAsDone();
            } else {
                addNewTask(input.split(" "), numTask);
                numTask++;
            }
        }
    }
}
