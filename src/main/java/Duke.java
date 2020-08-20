import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String border = "\n^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
        Scanner sc = new Scanner(System.in);
        String command;

        String logo = " _       _ \n"
                + "| |  _  | |_   _ ____ ___\n"
                + "| | | | | | |_/ |  _ \\  _ \\ \n"
                + "| |_| |_| |\\___ |    <  __/\n"
                + "\\___/\\___/ \\____|_| \\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(border + "Hi! I'm Wyre, your Personal Assistant Chatbot! :>\nWhat can I do for you today?" + border);
        while(true) {
            command = sc.next();
            if(command.equals("bye")) {
                System.out.println(border + "Bye. Hope to see you again!" + border);
                break;
            } else {
                System.out.println(border + command + border);
            }
        }

    }
}
