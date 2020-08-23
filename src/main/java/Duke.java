import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException, DukeException {
        Processor p = new Processor();
        Scanner sc = new Scanner(System.in);
        String logo = " ______  ___       __         __        _____\n"
                + "   |    /         /  \\       /  \\     /\n"
                + "   |    \\___     /____\\     /____\\   |\n"
                + "   |        \\   /      \\   /      \\   \\\n"
                + " ------  ___/  /        \\ /        \\    -----\n";
        System.out.println("Hello! I'm\n" + logo + "\nWhat can I do for you?");
        String next = "";
        p.readFile();
        while (!next.equals("bye")) {
            p.process(next);
            next = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}


