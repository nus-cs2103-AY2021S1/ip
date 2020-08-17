// LEVEL 2

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


// The chat bot class to handle the internal logic
public class ChatBot {
    List<Task> list = new LinkedList<>();
    Scanner sc = new Scanner(System.in);

    // The entry point to run the chat bot
    public void start() {
        String welcome = " Hello! I'm Duke \n     What can I do for you?";
        sendChat(welcome);
        String response = receiveChat();
        while (!response.equals("bye")) {
            if (response.equals("list")) {
                printList();
            } else if (response.startsWith("done")) {
                int index = Integer.parseInt(response.split(" ")[1]) - 1;
                list.get(index).markAsDone();
                printHorizontal();
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       "+list.get(index));
                printHorizontal();
            } else {
                addTask(response);
            }
            response = receiveChat();
        }
        sendChat(" Bye. Hope to see you again soon!");
    }

    private void addTask(String command) {
        printHorizontal();
        System.out.println("     Got it. I've added this task:");
        Task newTask;
        if (command.startsWith("todo")) {
            newTask = new Todo(command.substring(5));
        } else if (command.startsWith("deadline")) {
            newTask = new Deadline(command.substring(9).split(" /by ")[0], command.substring(9).split(" /by ")[1]);
        } else if (command.startsWith("event")) {
            newTask = new Event(command.substring(6).split(" /at ")[0], command.substring(6).split(" /at ")[1]);
        } else {
            newTask = new Task(command);
        }
        list.add(newTask);
        System.out.println("       " + newTask);
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
        printHorizontal();
    }

    private void printHorizontal() {
        System.out.println("    ____________________________________________________________");
    }

    // Return the list of tasks
    private void printList() {
        printHorizontal();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println("     " + i + "." + list.get(i - 1));
        }
        printHorizontal();
    }

    // Send specified content in a chat box
    private void sendChat(String content) {
        printHorizontal();
        System.out.println("    " + content);
        printHorizontal();
    }

    // Scan for user's response and return the string
    private String receiveChat() {
//        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
