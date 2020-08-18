import java.util.Scanner;

public class Duke {

    private static void printNice(String s) {
        System.out.println("________________________________________");
        System.out.println("    " + s);
        System.out.println("________________________________________");
    }

    private static void greet() {
        String greeting = "Hello! I'm duckmoon99's Duke\n    What can I do for you?";
        printNice(greeting);
    }

    private static void bye() {
        String exit = "Bye. Hope to see you again soon!";
        printNice(exit);
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input;
        while(true){
            input = scanner.nextLine();
            if (input.equals("bye"))
                break;
            else
                printNice(input);
        }
        bye();
    }

}
