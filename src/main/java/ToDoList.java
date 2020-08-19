import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    ArrayList<String> toDos;
    String welcomeMessage = "____________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________\n";
    String goodbyeMessage = "____________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
            "____________________________________________________\n";

    public ToDoList() {
        toDos = new ArrayList<>();
        System.out.println(welcomeMessage);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        boolean active = true;
        while (active) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                this.view();
            } else if (input.equals("bye")) {
                active = false;
                System.out.println(goodbyeMessage);
            } else {
                this.addToDo(input);
            }
        }
        sc.close();
    }

    public void addToDo(String newToDo) {
        toDos.add(newToDo);
        String addText = "____________________________________________________\n" +
                         "added: " + newToDo + "\n" +
                         "____________________________________________________\n";
        System.out.println(addText);
    }

    public void view() {
        String list = "____________________________________________________\n";
        for (int i = 0; i < toDos.size(); i++) {
            String item = String.valueOf(i + 1) + ". " + toDos.get(i) + "\n";
            list += item;
        }
        list += "____________________________________________________\n";
        System.out.println(list);
    }
}
