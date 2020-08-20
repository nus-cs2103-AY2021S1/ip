import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String border = "\n^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
        Scanner sc = new Scanner(System.in);
        String command;
        ArrayList<String> lst = new ArrayList<>();

        String logo = " _       _ \n"
                + "| |  _  | |_   _ ____ ___\n"
                + "| | | | | | |_/ |  _ \\  _ \\ \n"
                + "| |_| |_| |\\___ |    <  __/\n"
                + "\\___/\\___/ \\____|_| \\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(border + "Hi! I'm Wyre, your Personal Assistant Chatbot! :>\nWhat can I do for you today?" + border);
        while(true) {
            command = sc.nextLine();
            if(command.equals("bye")) {
                System.out.println(border + "Bye. Hope to see you again!" + border);
                break;
            } else if (command.equals("list")) {
                System.out.println(border);
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println((i + 1) + ". " + lst.get(i));
                }
                System.out.println(border);
            } else {
                lst.add(command);
                System.out.println(border + "added: " + command + border);
            }
        }

    }
}
