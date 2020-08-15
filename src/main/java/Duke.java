import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.hello();
        Scanner sc = new Scanner(System.in);
        String next;
        do {
            next = sc.nextLine();
            duke.command(next);
        } while(!next.equals("bye"));
        duke.goodbyeMessage();
    }

    public void goodbyeMessage(){
        System.out.println("********************************************");
        System.out.println("GoodBye, Hope to see you back soon.");
        System.out.println("********************************************");
    }

    public void hello(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }
    public void command(String input){
        System.out.println("********************************************");
        System.out.println(input);
        System.out.println("********************************************");
        System.out.println();
    }
}
