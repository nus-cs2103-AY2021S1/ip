import java.util.Scanner;

public class Duke {
    public static String longLine = "________________________________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        echo_input();

    }

    public static void echo_input(){
        lineFormatter("Hello!!!! I'm Duke \n What can I do for you?!?!?!" );
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String input = scanner.nextLine();
            if(input.toLowerCase().equals("bye")){
                lineFormatter("Bye! Hope to see you soon again?!");
                break;
            } else {
                lineFormatter(input);
            }
        }
    }
    public static void lineFormatter (String printable){
        System.out.println(longLine);
        System.out.println(printable);
        System.out.println(longLine);
    }


}
