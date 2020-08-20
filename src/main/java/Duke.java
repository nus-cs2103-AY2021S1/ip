import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello im Eu Zin's Duke, he spent thursday afternoon creating me cuz he forgot abt the iP");

        Duke.echo(new Scanner(System.in));
    }

    static void echo(Scanner scanner) {
        String userInput = scanner.nextLine();
        String borders = "\n\\   / \\   / \\   / \\   / im not very creative \\   / \\   / \\   / \\   /\n \\ /   \\ /   \\ /   \\ /      EuZin's Duke      \\ /   \\ /   \\ /   \\ /\n";
        if (userInput.equals("bye")) {
            System.out.println(borders + "\n" + "Bye. Hope to see you again soon!" + "\n" + borders);
        } else {
            System.out.println(borders + "\n" + userInput + "\n" + borders);
            Duke.echo((new Scanner(System.in)));
        }
    }
}
