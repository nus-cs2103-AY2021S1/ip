import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<String> list;
    private static final String starline = "**************************************************************************";
    private static final String logo =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    public Duke() {
        this.list = new ArrayList<>();
    }

    public static void greet() {
        System.out.println(starline +
                "\nWelcome! I am \n" + logo +
                "\nHere are some magic words to get you going: " +
                "\nIf you are done, feel free to say 'bye'!" +
                "\nIf you'd like to view your tasks, say 'list'." +
                "\nOtherwise, send me new tasks to add them to your todo list!\n" +
                starline);
    }

    public void awaitInputCommand() {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        while (!next.equals("bye")){
            if (next.equals("list")) {
                list();
            } else {
                add(next);
            }
            next = sc.nextLine();
        }
        sc.close();
    }

    public void echo(String input) {
        System.out.println("added: " + input);
    }

    public void add(String input) {
        this.list.add(input);
        echo(input);
    }

    public void list() {
        System.out.println(starline);
        for (int i=0; i < this.list.size(); i++) {
            System.out.println(String.format("%d. %s", i+1, this.list.get(i)));
        }
        System.out.println(starline);
    }

    public static void exit() {
        System.out.println(
                        " _____  ___  ___  _____   \n" +
                        "|  __ \\ \\  \\/  / |  ___| \n" +
                        "| | / /  \\    /  |  |__  \n" +
                        "| | \\ \\   |  |   |  ___| \n" +
                        "| |_/ /   |  |   |  |__  \n" +
                        "|____/    |__|   |_____| \n"
        );
        System.out.println("Hope you have a productive day ahead! :))");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        greet();
        duke.awaitInputCommand();
        exit();
    }
}
