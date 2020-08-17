import java.util.Scanner;

public class Duke {
    public static void handleInput() {
        Task[] list = new Task[100];
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.toLowerCase().equals("bye")) {
                replyBye();
                sc.close();
                break;
            } else if (input.toLowerCase().equals("list")) {
                replyList(list, Task.totalTasks);
            } else if (input.toLowerCase().split(" ")[0].equals("done")) {
                replyDone(input, list, Task.totalTasks);
            } else {
                String inputTask;
                Task task;
                if (input.toLowerCase().split(" ")[0].equals("todo")) {
                    inputTask = input.substring(5);
                    task = new Todo(inputTask);
                    list[Task.totalTasks - 1] = task;
                    replyTask(task);
                } else if (input.toLowerCase().split(" ")[0].equals("event")) {
                    inputTask = input.substring(6);
                    String[] arr = inputTask.split("/");
                    task = new Event(arr[0].trim(), arr[1].substring(3));
                    list[Task.totalTasks - 1] = task;
                    replyTask(task);
                } else if (input.toLowerCase().split(" ")[0].equals("deadline")) {
                    inputTask = input.substring(9);
                    String[] arr = inputTask.split("/");
                    task = new Deadline(arr[0].trim(), arr[1].substring(3));
                    list[Task.totalTasks - 1] = task;
                    replyTask(task);
                } else {
                    printReply("Sorry! I don't understand what you are saying! D=");
                }
            }
        }
    }

    public static void replyBye() {
        String exitMessage = "BYEEE!! SEE YOU AGAIN!!! >O<";
        printReply(exitMessage);
    }

    public static void replyDone(String input, Task[] list, int end) {
        String doneMessage = "Nicee!! You've completed this task! \n";
        String error = "Sorry! You don't have a task with that number! ><\n" +
                "Can you try a different number?";
        int i = input.charAt(5) - 48;

        if (i == 0 || i > end) {
            printReply(error);
        } else {
            list[i - 1].markDone();
            doneMessage += list[i - 1];
            printReply(doneMessage);
        }
    }

    public static void replyList(Task[] list, int end) {
        String emptyList = "You don't have any tasks at the moment!";
        String numberedList = "Here are your tasks! JIAYOU! =D";

        if (end == 0) {
            printReply(emptyList);
            return;
        }

        for (int i = 0; i < end; i++) {
                int num = i + 1;
                numberedList += "\n" + num + ". " + list[i];
        }

        printReply(numberedList);
    }

    public static void replyTask(Task task) {
        String addedMessage = "Oki! I have added this task: \n" +
                task + "\n" + "Now you have " + Task.totalTasks +
                " tasks in your list!";
        printReply(addedMessage);
    }

    public static void printReply(String reply) {
        System.out.println("--------------------------------------");
        System.out.println(reply);
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("hElLoOOoOOoO! Welcome to \n" + logo);
        System.out.println("How can I help you today? : D");

        handleInput();
    }
}
