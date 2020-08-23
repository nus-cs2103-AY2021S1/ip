package main.java;
import java.util.Scanner;

public class ChatBot {

    public static void start(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello I'm Duke!\nWhat can I do for you?");
        boolean ended = false;
        while(!ended){
            String query = scanner.nextLine();
            String resp = ChatBot.response(query);
            ended = ChatBot.isEnd(query);
            if(!ended){
                System.out.println(resp);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static String response(String query){
        String command = query.toLowerCase();
        switch (command){
            default:
                return query;
        }
    }

    private static boolean isEnd(String query){
        String editedQuery = query.stripLeading().stripTrailing();
        return "bye".equalsIgnoreCase(editedQuery);
    }
}
