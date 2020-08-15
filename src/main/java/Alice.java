import java.util.ArrayList;
import java.util.Scanner;

public class Alice {
    private final ArrayList<String> ls;

    public Alice() {
        ls = new ArrayList<>();
        greet();
        prompt();
    }

    public boolean reply(String s) {
        if (s.equals("bye")) {
            display("Goodbye. Hope to see you again soon!");
            return false;
        } else if (s.equals("list")) {
            print_list();
            prompt();
            return true;
        } else {
            ls.add(s);
            display("added: " + s);
            prompt();
            return true;
        }
    }

    private void display(String s) {
        System.out.println("____________________________________________________________\n"
                + s
                + "\n____________________________________________________________");
    }

    private void print_list() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < ls.size(); i++) {
            System.out.println(i+1 + ". " + ls.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private void greet() {
        String logo = "\n _____  _     _____ _____  _____ \n" +
                "/  _  \\| |   |_   _/  __ \\|  ___|\n" +
                "| |_| || |     | | | /  \\/| |__  \n" +
                "|  _  || |     | | | |    |  __| \n" +
                "| | | || |_____| |_| \\__/\\| |___ \n" +
                "\\_| |_/\\_____/\\___/ \\____/\\____/ \n";

        System.out.println(logo +
                "\nHello! I'm Alice\n" +
                "How can I help you today?\n" +
                "____________________________________________________________");
    }

    private void prompt() {
        System.out.print(" > ");
    }

    public static void main(String[] args) {
        Alice alice = new Alice();
        Scanner sc = new Scanner(System.in);

        String cmd = sc.nextLine();
        while (alice.reply(cmd)) {
            cmd = sc.nextLine();
        }
    }
}
