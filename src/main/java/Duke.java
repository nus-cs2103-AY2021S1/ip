package main.java;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static void chat(){
        ArrayList<String> items = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String word;
        while(!(word = scanner.nextLine()).equals("bye")){
            if (word.equals("list")){
                System.out.println(Chat.numberedListChatBox(items));
            } else {
                items.add(word);
                System.out.print(Chat.singleLineChatBox("added: " + word));
            }
        }
        System.out.print(Chat.singleLineChatBox("Bye. Hope to see you again soon!"));
        scanner.close();
    }

    public static void main(String[] args) {
        String logo =
                " ____  __.__\n" +
                "|    |/ _|__| ____    ____\n" +
                "|      < |  |/    \\  / ___\\ \n" +
                "|    |  \\|  |   |  \\/ /_/  >\n" +
                "|____|__ \\__|___|  /\\___  /\n" +
                "        \\/       \\//_____/\n";
        System.out.println(logo);
        System.out.println("Hello! I'm King");
        System.out.println("What can I do for you?");
        chat();
    }
}
