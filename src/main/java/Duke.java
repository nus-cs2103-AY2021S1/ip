import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private List<Task> todos;

    public Duke() {
        todos = new ArrayList<Task>();
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
            String[] cmd = command.split(" ");

            if (cmd[0].equals("list")) {
                listItems();

            } else if (cmd[0].equals("done")) {
                Integer taskNum = Integer.parseInt(cmd[1]);
                System.out.println(taskNum);
                markAsDone(taskNum);

            } else if (cmd[0].equals("bye")){
                sc.close();
                System.out.println("baii~ see you!");
                System.exit(0);
                return;
            } else {
                Task task = new Task(command);
                saveToList(task);

            }
        }
    }

    public void listItems() {
        StringBuilder todoList = new StringBuilder("");
        int num = 1;
        for(Task item : this.todos) {
            todoList.append(num + ". [" + item.getStatusIcon() + "] " + item.toString() + "\n");
            num++;
        }
        System.out.println(todoList);
    }

    public void saveToList(Task todo) {
        this.todos.add(todo);
        System.out.println("added: " + todo.toString());
    }

    public void markAsDone(int taskNum) {
        int index = taskNum - 1;
        Task oldTask = this.todos.get(index);
        Task newTask = new Task(oldTask.toString(), true);
        this.todos.remove(oldTask);
        this.todos.add(index, newTask);
        System.out.println("Nice job! I've marked this task as done : \n"
                            + "[" + newTask.getStatusIcon() + "] " + newTask.toString());
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
