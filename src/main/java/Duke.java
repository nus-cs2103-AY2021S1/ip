import java.util.Scanner;

public class Duke {
    private static final String logo =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static String getLogo() {
        return logo;
    }

    public static void greet() {
        System.out.println("Welcome! I am copycat\n" + getLogo());
        System.out.println("If you are sick of me, feel free to say 'bye'!");
        System.out.println("In the meantime, what can I repeat for you?");
    }

    public static void awaitInputCommand(Scanner sc) {
        String next = sc.nextLine();
        while (!next.equals("bye")){
            echo(next);
            next = sc.nextLine();
        }
        exit();
    }

    public static void echo(String input) {
        System.out.println(input);
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
        System.out.println("just kidding! it's sad to see you go!! hope to talk to you again soon!");
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        awaitInputCommand(sc);
        sc.close();
    }
}
