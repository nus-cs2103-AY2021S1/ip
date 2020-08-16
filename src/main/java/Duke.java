import java.util.Scanner;

public class Duke {
    public static void replyUser() {
        String exitMessage = "BYEEE!! SEE YOU AGAIN!!! >O<";

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.next();

            if (input.toLowerCase().equals("bye")) {
                printReply(exitMessage);
                sc.close();
                break;
            } else {
                printReply(input + " =D");
            }
        }
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

        replyUser();
    }
}
