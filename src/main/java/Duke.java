import java.util.Scanner;

public class Duke {

    private static void printNice(String s) {
        System.out.println("________________________________________");
        System.out.println("    " + s);
        System.out.println("________________________________________");
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n    What can I do for you?";
        printNice(greeting);
        Scanner scanner = new Scanner(System.in);
        String input;
        while(true){
            input = scanner.nextLine();
            if (input.equals("bye"))
                break;
            else
                printNice(input);
        }
        String exit = "Bye. Hope to see you again soon!";
        printNice(exit);
    }

}
