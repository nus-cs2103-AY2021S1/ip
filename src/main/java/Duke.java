import java.util.Scanner;

public class Duke {
    public static void replyUser() {
        String exitMessage = "BYEEE!! SEE YOU AGAIN!!! >O<";
        String[] list = new String[100];
        int i = 0;

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.toLowerCase().equals("bye")) {
                printReply(exitMessage);
                sc.close();
                break;
            } else if (input.toLowerCase().equals("list")) {
                printReply(makeList(list, i));
            } else {
                list[i] = input;
                i++;
                printReply("added: \"" + input + "\" to list! =D");
            }
        }
    }

    public static void printReply(String reply) {
        System.out.println("--------------------------------------");
        System.out.println(reply);
        System.out.println("--------------------------------------");
    }

    public static String makeList(String[] list, int end) {
        String numberedList = "";
        for (int i = 0; i < end; i++) {
            if (i == 0) {
                numberedList = "1. " + list[i];
            } else {
                int num = i + 1;
                numberedList += "\n" + num + ". " + list[i];
            }
        }

        return numberedList;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("hElLoOOoOOoO! Welcome to \n" + logo);
        System.out.println("How can I help you today? : D");

        replyUser();
    }
}
