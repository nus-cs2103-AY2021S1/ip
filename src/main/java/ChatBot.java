import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


// The chat bot class to handle the internal logic
public class ChatBot {
    LinkedList<Task> list = new LinkedList<>();
    Scanner sc = new Scanner(System.in);

    private void welcome() {
        printHorizontal();
        System.out.println("    " + " Hello! I'm Duke!");
        try {
            list = Storage.readList();
            System.out.println("     Previous data found!\n     Now you have " + list.size() + " task in the list!");
        } catch (NoDataFileException e) {
            System.out.println("     Did not find any previous stored data and new data file created! Welcome!");
        } catch (IOException e) {
            System.out.println("     Oops! Cannot access your data file and no new data file has been created!");
        }
        System.out.println("     What can I do for you?");
        printHorizontal();
    }

    // The entry point to run the chat bot
    public void start() {
        welcome();
        String response = receiveChat();
        while (!response.equals("bye")) {
            if (response.equals("list")) {
                printList();
            } else if (response.split(" ")[0].equals("done")) {
                try {
                    markAsDone(response);
                } catch (Exception e) {
                    System.out.println("     " + e.getMessage());
                    printHorizontal();
                }
            } else if (response.split(" ")[0].equals("delete")) {
                try {
                    delete(response);
                } catch (Exception e) {
                    System.out.println("     " + e.getMessage());
                    printHorizontal();
                }
            } else {
                try {
                    addTask(response);
                } catch (Exception e) {
                    System.out.println("     " + e.getMessage());
                    printHorizontal();
                }
            }
            Storage.save(list);
            response = receiveChat();
        }
        sendChat(" Bye. Hope to see you again soon!");
    }

    private void markAsDone(String command) throws Exception {
        // here we already know that the first word is "done"
        printHorizontal();
        if (command.split(" ").length == 1) {
            throw new NoDescriptionException("done");
        }
        if (command.split(" ").length != 2) {
            throw new IllegalDoneArgument();
        }
        if (!command.split(" ")[1].matches("\\d+")) {
            throw new IllegalDoneArgument();
        }
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        if (index < 0 || index >= list.size()) {
            throw new IllegalDoneArgument();
        }
        list.get(index).markAsDone();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       "+list.get(index));
        printHorizontal();
    }

    private void delete(String command) throws Exception {
        // here we already know that the first word is "done"
        printHorizontal();
        if (command.split(" ").length == 1) {
            throw new NoDescriptionException("delete");
        }
        if (command.split(" ").length != 2) {
            throw new IllegalDeleteArgument();
        }
        if (!command.split(" ")[1].matches("\\d+")) {
            throw new IllegalDeleteArgument();
        }
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        if (index < 0 || index >= list.size()) {
            throw new IllegalDeleteArgument();
        }
        Task deleted = list.remove(index);
        System.out.println("     Noted! I've removed this task:");
        System.out.println("       " + deleted);
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
        printHorizontal();
    }

    private void addTask(String command) throws Exception {
        printHorizontal();
        String firstCmd = command.split(" ")[0];
        Task newTask;
        switch (firstCmd) {
            case "todo":
                if (command.split(" ").length == 1) {
                    throw new NoDescriptionException("todo");
                }
                newTask = new Todo(command.substring(5));
                break;
            case "deadline": {
                if (command.split(" ").length == 1) {
                    throw new NoDescriptionException("deadline");
                }
                String nameAndTime = command.substring(9);
                if (nameAndTime.split(" /by ").length == 1) {
                    throw new NoTimeException("deadline");
                }
                newTask = new Deadline(command.substring(9).split(" /by ")[0], command.substring(9).split(" /by ")[1]);
                break;
            }
            case "event": {
                if (command.split(" ").length == 1) {
                    throw new NoDescriptionException("event");
                }
                String nameAndTime = command.substring(6);
                if (nameAndTime.split(" /at ").length == 1) {
                    throw new NoTimeException("event");
                }
                newTask = new Event(command.substring(6).split(" /at ")[0], command.substring(6).split(" /at ")[1]);
                break;
            }
            default:
                throw new IllegalCommandException(command);
        }
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newTask);
        list.add(newTask);
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
        printHorizontal();
    }

    private void saveList() {
        Storage.save(list);
    }

    private void printHorizontal() {
        System.out.println("    ____________________________________________________________");
    }

    // Return the list of tasks
    private void printList() {
        printHorizontal();
        if (list.size() == 0) {
            System.out.println("     You have no tasks in your list now! Type todo, event or deadline to add some!");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println("     " + i + "." + list.get(i - 1));
            }
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
