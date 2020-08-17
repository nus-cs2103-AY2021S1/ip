import java.util.Scanner;

public class Duke {
    public static void handleInput() {
        Task[] list = new Task[100];
        int i = 0;

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.toLowerCase().equals("bye")) {
                replyBye();
                sc.close();
                break;
            } else if (input.toLowerCase().equals("list")) {
                replyList(list, i);
            } else if (input.toLowerCase().substring(0, 4).equals("done")) {
                replyDone(input, list, i);
            } else {
                list[i] = new Task(input);
                i++;
                printReply("added: \"" + input + "\" to list! =D");
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
