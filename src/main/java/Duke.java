package main.java;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> items = new ArrayList<>();

    private static void chat(){
        Scanner scanner = new Scanner(System.in);
        String word;
        while(!(word = scanner.nextLine()).equals("bye")){
            if (word.equals("list")){
                System.out.println(Chat.numberedListChatBox(items));
            } else if(word.startsWith("done ")){
                String[] tokens = word.split(" ");
                try {
                    int itemNo = Integer.parseInt(tokens[1]) - 1;
                    Task item = items.get(itemNo);
                    item.markAsDone();
                    System.out.println(Chat.chatBox(
                            "Nice! I've marked this task as done:\n"
                            + "\t\t" + item.toString()
                    ));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(Chat.chatBox("Item " + tokens[1] + " not found."));
                } catch (Exception e){
                    System.out.println(Chat.chatBox("Item not found."));
                }
            } else if (word.startsWith("todo ")) {
                String item = word.substring(6);
                ToDo todo = new ToDo(word);
                items.add(todo);
                System.out.print(Chat.addItemChatBox(todo.toString(),items.size()));
            } else if (word.startsWith("event ")) {
                word = word.substring(6);
                String[] tokens = word.split(" /at ");
                if (tokens.length == 2) {
                    Event event = new Event(tokens[0],tokens[1]);
                    items.add(event);
                    System.out.print(Chat.addItemChatBox(event.toString(),items.size()));
                } else {
                    System.out.print(Chat.chatBox("I did not understand you!"));
                }
            } else if (word.startsWith("deadline ")) {
                word = word.substring(9);
                String[] tokens = word.split(" /by ");
                if (tokens.length == 2) {
                    Deadline deadline = new Deadline(tokens[0],tokens[1]);
                    items.add(deadline);
                    System.out.print(Chat.addItemChatBox(deadline.toString(),items.size()));
                } else {
                    System.out.print(Chat.chatBox("I did not understand you!"));
                }
            } else {
                items.add(new Task(word));
                System.out.print(Chat.chatBox("added: " + word));
            }
        }
        System.out.print(Chat.chatBox("Bye. Hope to see you again soon!"));
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
