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
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            String[] cmd = command.split(" ", 2);
            String firstWord = cmd[0];

            if (firstWord.equals("list")) {
                listItems();

            } else if (firstWord.equals("bye")) {
                sc.close();
                System.out.println("baii~ have a great day!");
                System.exit(0);
                return;

            } else {
                String theRest = cmd[1];

                if (firstWord.equals("done")) {
                    processDone(theRest);
                } else if (firstWord.equals("todo")) {
                    processTodo(theRest);
                } else if (firstWord.equals("deadline")) {
                    processDeadline(theRest);
                } else if (firstWord.equals("event")) {
                    processEvent(theRest);
                }
            }
        }
    }

    public void processDone(String theRest) {
        Integer taskNum = Integer.parseInt(theRest);
        System.out.println(taskNum);
        markTaskAsDone(taskNum);
    }

    public void processTodo(String theRest) {
        Todo todo = new Todo(theRest);
        saveToList(todo);
    }

    public void processDeadline(String theRest) {
        String[] taskAndDeadline = theRest.split(" /by", 2);
        String task = taskAndDeadline[0];
        String date = taskAndDeadline[1];

        Deadline deadline = new Deadline(task, date);
        saveToList(deadline);
    }

    public void processEvent(String theRest) {
        String[] eventAndDate = theRest.split(" /at", 2);
        String eventDesc = eventAndDate[0];
        String eventDate = eventAndDate[1];

        Event event = new Event(eventDesc, eventDate);
        saveToList(event);
    }

    public void listItems() {
        StringBuilder todoList = new StringBuilder("Here are the tasks in your list: \n");
        int num = 1;
        for (Task item : this.todos) {
            todoList.append(num + ". " + item.toString() + "\n");
            num++;
        }
        System.out.println(todoList);
    }

    public void saveToList(Task todo) {
        this.todos.add(todo);
        System.out.println("Got it. I've added this task: \n" + todo.toString());
        System.out.println("Now you have " + this.todos.size() + " tasks in the list.");
    }

    public void markTaskAsDone(int taskNum) {
        int index = taskNum - 1;
        Task oldTask = this.todos.get(index);
        Task newTask = oldTask.markAsDone();
        this.todos.remove(oldTask);
        this.todos.add(index, newTask);
        System.out.println("Nice job! I've marked this task as done : \n"
                + newTask.toString());
    }
}
