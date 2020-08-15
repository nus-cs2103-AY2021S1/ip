import java.util.Scanner;

public class Duke {

    public static void greet(){
        String greeting = "\n   _________________________________________________________________"
                + "\n   Hi, I'm Duke!"
                + "\n   How can I help you today?"
                + "\n   ______________________________________________________________________";
        System.out.println(greeting);
    }

    public static void echo(String input){
        String output = "   ______________________________________________________________"
                        + "\n   " + input
                        + "\n   ______________________________________________________________";
        System.out.println(output);
    }

    public static void bye(){
        String output = "   ______________________________________________________________"
                + "\n   " + "Bye. Hope to see you again soon!"
                + "\n   ______________________________________________________________";
        System.out.println(output);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            if(input.equals("bye") || input.equals("Bye")){
                bye();
                break;
            } else {
                echo(input);
            }
        }

    }
}
