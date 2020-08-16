import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private List<String> todos;

    public Duke() {
        todos = new ArrayList<String>();
        this.todos = todos;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(duke.toString());
        //echo();
        duke.receiveInput();
    }

    @Override
    public String toString() {
        String donLogo = "  ______      _______     ____    __    \n"
                + " |   _  \\    /   _   \\   |    \\  |  |\n"
                + " |  | |  |  |   | |   |  |  |\\ \\ |  |\n"
                + " |  |_|  |  |   |_|   |  |  | \\ \\|  |\n "
                + "|_____ /    \\______ /   |__|  \\____|\n";
        String msg = "Hola! I'm Don \n" +
                "How can I help you?";
        return donLogo + "\n" + msg;
    }

    public void receiveInput() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String command = sc.nextLine();
            if(!command.equals("list") && !command.equals("bye")) {
                saveToList(command);
            } else if (!command.equals("bye")){
                listItems();
            } else {
                sc.close();
                System.out.println("Bye. Hope to see you again!");
                System.exit(0);
                return;
            }
        }
    }

    public void listItems() {
        StringBuilder todoList = new StringBuilder("");
        int num = 1;
        for(String item : this.todos) {
            todoList.append(num + ". " + item + "\n");
            num++;
        }
        System.out.println(todoList);
    }

    public void saveToList(String todo) {
        this.todos.add(todo);
        System.out.println("added: " + todo);
    }

//    public static void echo() {
//        Scanner sc = new Scanner(System.in);
//        String command;
//        while (sc.hasNextLine()) {
//            command = sc.nextLine();
//            if (!command.equals("bye")) {
//                System.out.println(command);
//            } else {
//                sc.close();
//                System.out.println("bai~ see you!");
//                System.exit(0);
//                return;
//            }
//        } //lvl 1
}
