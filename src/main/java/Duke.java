package main.java;
import java.util.Scanner;

public class Duke {

    private static void chat(){
        Scanner scanner = new Scanner(System.in);
        String word;
        while(!(word = scanner.nextLine()).equals("bye")){
                System.out.print(Chat.chatBox(word));
        }
        System.out.print(Chat.chatBox("Bye! Hope to see you again soon!"));
        scanner.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        chat();
    }
}
