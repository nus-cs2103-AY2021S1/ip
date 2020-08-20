import main.java.Deadline;
import main.java.Event;
import main.java.Task;
import main.java.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> listOfTaskEntered;

    public Duke() {
        this.listOfTaskEntered = new ArrayList<>(100);
    }

    //print welcome message
    public void printWelcomeMessage() {
        String emoji = new String(Character.toChars(0x1F423));
        String welcomeMessage = "    ____________________________________________________________\n"
                + "    Hello! I'm ByteMe " + emoji + emoji + emoji + "\n"
                + "    What can I do for you? (Don't bite me!)\n"
                + "    ____________________________________________________________\n";

        System.out.println(welcomeMessage);
    }

    //respond to different user input
    public void respond() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();
            if (instruction.equals("list")) {
                String msgForList = "    ____________________________________________________________\n"
                        + "    list \n"
                        + "    ____________________________________________________________\n";
                System.out.println(msgForList);
            } else if (instruction.equals("blah")) {
                String msgForBlah = "    ____________________________________________________________\n"
                        + "    blah \n"
                        + "    ____________________________________________________________\n";
                System.out.println(msgForBlah);
            } else if (instruction.equals("bye")) {
                String msgForBye = "    ____________________________________________________________\n"
                        + "    Bye. Hope to see you again soon! \n"
                        + "    ____________________________________________________________\n";
                System.out.println(msgForBye);
                break;
            }
        }
        sc.close();
    }

    //mark a task as done
    public void markAsDone(int num) {
        listOfTaskEntered.get(num - 1).markAsDone();
        String msgForDone = "    ____________________________________________________________\n"
                + "    Nice! I 've marked this task as done: \n"
                + "       " + listOfTaskEntered.get(num - 1).toString() + "\n"
                + "    ____________________________________________________________\n";
        System.out.println(msgForDone);
    }

    //count number of tasks
    public String countNum() {
        int num = listOfTaskEntered.size();
        return "    Now you have " + num + " tasks in the list.";
    }

    //add new to-do to the list
    public void addToDo(Todo newToDo) {
        this.listOfTaskEntered.add(newToDo);
        String msgForToDo = "    ____________________________________________________________\n"
                + "    Got it. I 've added this task: \n"
                + "      " + newToDo.toString() + "\n"
                + this.countNum() + "\n"
                + "    ____________________________________________________________\n";
        System.out.println(msgForToDo);
    }

    //add new deadline to the list
    public void addDeadline(Deadline newDdl) {
        this.listOfTaskEntered.add(newDdl);
        String msgForDdl = "    ____________________________________________________________\n"
                + "    Got it. I 've added this task: \n"
                + "      " + newDdl.toString() + "\n"
                + this.countNum() + "\n"
                + "    ____________________________________________________________\n";
        System.out.println(msgForDdl);
    }

    //add new event to the list
    public void addEvent(Event newEvent) {
        this.listOfTaskEntered.add(newEvent);
        String msgForEvent = "    ____________________________________________________________\n"
                + "    Got it. I 've added this task: \n"
                + "      " + newEvent.toString() + "\n"
                + this.countNum() + "\n"
                + "    ____________________________________________________________\n";
        System.out.println(msgForEvent);
    }

    //add new task to the list
    public void addTask(Task newTask) {
        this.listOfTaskEntered.add(newTask);
        String msgForTask = "    ____________________________________________________________\n"
                + "    Got it. I 've added this task: \n"
                + "      " + newTask.toString() + "\n"
                + this.countNum() + "\n"
                + "    ____________________________________________________________\n";
        System.out.println(msgForTask);
    }

    //check for invalid input
    public void check() {
        String invalidInput = "    ____________________________________________________________\n"
                + "    Instructions not found, please try again. \n"
                + this.countNum() + "\n"
                + "    ____________________________________________________________\n";
        System.out.println(invalidInput);
    }

    //store user input and respond to different input
    public void run() {
        this.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();
            int len = instruction.length();
            if (instruction.equals("list")) {
                String msgForList = "    ____________________________________________________________\n";
                msgForList += "    Here are the tasks in your list: \n";
                for (int i = 0; i < listOfTaskEntered.size(); i++) {
                    msgForList += "    " + (i + 1) + ". "
                            + listOfTaskEntered.get(i).toString() + "\n";
                }
                msgForList += "    ____________________________________________________________\n";
                System.out.println(msgForList);

            } else if (instruction.equals("bye")) {
                String msgForBye = "    ____________________________________________________________\n"
                        + "    Bye. Hope to see you again soon! \n"
                        + "    ____________________________________________________________\n";
                System.out.println(msgForBye);
                break;

            } else if (len >= 5 && instruction.substring(0, 5).equals("done ")) {
                int num = Integer.parseInt(instruction.substring(5));
                this.markAsDone(num);

            } else if (len >= 5 && instruction.substring(0, 5).equals("todo ")) {
                String toDoTitle = instruction.substring(5);
                Todo newTodo = new Todo(toDoTitle);
                this.addToDo(newTodo);

            } else if (len >= 9 && instruction.substring(0, 9).equals("deadline ")) {
                int index = instruction.indexOf("/by");
                String by = instruction.substring(index + 3);
                String description = instruction.substring(9, index);
                Deadline deadline = new Deadline(description, by);
                this.addDeadline(deadline);

            } else if (len >= 6 && instruction.substring(0, 6).equals("event ")) {
                int index = instruction.indexOf("/at");
                String time = instruction.substring(index + 3);
                String description = instruction.substring(6, index);
                Event event = new Event(description, time);
                this.addEvent(event);

            } else {
                this.check();
            }
        }
        sc.close();
    }

    //run bot
    public static void main(String[] args) {
        Duke myBot = new Duke();
        //myBot.respond();
        myBot.run();
    }
}
