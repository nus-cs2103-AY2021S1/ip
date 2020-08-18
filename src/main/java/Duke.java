import java.io.InputStreamReader;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today?");

        //Level 1, echo bot that echos everything except bye
        Scanner scanner = new Scanner(new InputStreamReader(System.in));

        while(true){
            String input = scanner.nextLine();
            if(input.contentEquals("bye")){
                System.out.println("\t" + "Bye. Hope to see you again soon!");
                break;
            }
            else{
                System.out.println("\t" + input);
            }
        }
    }
}
