import java.util.Scanner;

public class Duke {

    public interface Printable{
        public String print();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Printable greeting = () -> "Hello! I'm Duke \nWhat can I do for you?";
        Printable goodbye = () -> "Bye. Hope to see you again soon!";
        speak(greeting);
        Printable input;
        Scanner sc = new Scanner(System.in);
        do {
            input = getUserInput(sc);
            if (input.print().equals("bye")) {
                speak(goodbye);
                break;
            } else {
                speak(input);
            }
        } while (true);
    }

    public static void speak(Printable printable) {
        System.out.println("------------------------------------------------------");
        System.out.println(printable.print() + "\n");
        System.out.println("------------------------------------------------------");
    }

    public static Printable getUserInput(Scanner sc) {
        Printable input = () -> sc.nextLine().toLowerCase();
        return input;
    }

}
