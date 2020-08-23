package main.java;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {

    static ArrayList<Task> taskList = new ArrayList<Task>();

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
        String command = query.toLowerCase().stripLeading().stripTrailing();
        switch (command){
            case "list":
                return listOfTasks();
            default:
                ChatBot.addTask(query);
                return "added: " + query;
        }
    }

    private static String listOfTasks(){
        String acc = "";
        int i = 0;
        for (Task t: taskList){
            i++;
            acc = acc + String.format("%d. %s\n",i,t);
        }
        return acc;
    }

    private static void addTask(String query){
        Task newTask = new Task(query);
        taskList.add(newTask);
    }

    private static boolean isEnd(String query){
        String editedQuery = query.stripLeading().stripTrailing();
        return "bye".equalsIgnoreCase(editedQuery);
    }
}
