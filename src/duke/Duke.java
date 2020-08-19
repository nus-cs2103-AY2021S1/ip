package duke;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

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
//        String donLogo = "  ______      _______     ____    __    \n"
//                + " |   _  \\    /   _   \\   |    \\  |  |\n"
//                + " |  | |  |  |   | |   |  |  |\\ \\ |  |\n"
//                + " |  |_|  |  |   |_|   |  |  | \\ \\|  |\n "
//                + "|_____ /    \\______ /   |__|  \\____|\n";
        String donLogo = "   ___     ___    _  _     ___     ___ \n"
                + "  |   \\   / _ \\  | \\| |   / __|   / _ \\  \n"
                + "  | |) | | (_) | | .` |  | (_ |  | (_) | \n"
                + "  |___/   \\___/  |_|\\_|   \\___|   \\___/  \n"
                + "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n"
                + "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' \n";
        String msg = "Hola! I'm Dongo :) \n" +
                "How can I help you?";
        return donLogo + "\n" + msg;
    }

    public void receiveInput() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();

            try {
            String[] cmd = command.split(" ", 2);
            String firstWord = cmd[0];
            Commands defCommand = null;

                try {
                    defCommand = Commands.valueOf(firstWord.toUpperCase()); //input to upper case
                } catch (IllegalArgumentException e) {
                    throw new WrongInputException();
                }

                switch (defCommand) {
                    case LIST:
                        listItems();
                        break;
                    case BYE:
                        sc.close();
                        System.out.println("Time to say goodbye :( \n" +
                                "Have a great day!");
                        System.exit(0);
                        return;
                    case DONE:
                        processDone(command);
                        break;
                    case TODO:
                        processTodo(command);
                        break;
                    case DEADLINE:
                        processDeadline(command);
                        break;
                    case EVENT:
                        processEvent(command);
                        break;
                    case DELETE:
                        processDelete(command);
                        break;
                    default:
                        WrongInputException wrong = new WrongInputException();
                        System.out.println(wrong.getMessage());
                        break;
                }
            } catch (DukeException exc) {
                System.out.println(exc.getMessage());
            }
        }

//            if (firstWord.equals("list")) {
//                listItems();
//
//            } else if (firstWord.equals("bye")) {
//                sc.close();
//                System.out.println("Time to say goodbye :( \n" +
//                                    "Have a great day!");
//                System.exit(0);
//                return;
//
//            } else {
//
//                if (firstWord.equals("done")) {
//                    processDone(command);
//                } else if (firstWord.equals("todo")) {
//                    processTodo(command);
//                } else if (firstWord.equals("deadline")) {
//                    processDeadline(command);
//                } else if (firstWord.equals("event")) {
//                    processEvent(command);
//                } else if (firstWord.equals("delete")) {
//                    processDelete(command);
//                } else {
//                    WrongInputException wrong = new WrongInputException();
//                    System.out.println(wrong.getMessage());
//                }
//            }
//        }
    }

    public String removeFirstWord(String command) throws DukeException {
        try {
            String[] cmd = command.split(" ", 2);
            return cmd[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void processDelete(String command) throws DeleteException {
        try {
            String theRest = removeFirstWord(command);
            Integer taskNum = Integer.parseInt(theRest);
            int index = taskNum - 1;
            deleteTask(index);

        } catch (DukeException d) {
           throw new DeleteException("Please enter a number. I cannot delete nothing :(");
        }
    }
    public void deleteTask(int taskNum) throws DeleteException {
        if(taskNum <= 0 || taskNum >= todos.size()) {
            throw new DeleteException("Please enter a valid task number.");
        } else {
            Task task = this.todos.get(taskNum);
            this.todos.remove(task);
            System.out.println("Noted. I've removed this task for you: \n"
                    + task.toString() + "\n"
                    + "Now you have " + this.todos.size() + " task(s) in the list.");
        }

    }

    public void processDone(String command) throws DoneException {
        try {
            String theRest = removeFirstWord(command);
            Integer taskNum = Integer.parseInt(theRest);
            markTaskAsDone(taskNum);

        } catch (DukeException d) {
            throw new DoneException("Please specify what you have already done.");
        }
    }

    public void processTodo(String command) throws TodoException {
        try {
            String theRest = removeFirstWord(command);
            Todo todo = new Todo(theRest);
            saveToList(todo);
        } catch (DukeException e) {
            throw new TodoException();
        }
    }


    public void processDeadline(String command) throws DeadlineException {
        try {
            String theRest = removeFirstWord(command);
            String[] taskAndDeadline = theRest.split(" /by", 2);

            try {
                String task = taskAndDeadline[0];
                String date = taskAndDeadline[1];
                Deadline deadline = new Deadline(task, date);
                saveToList(deadline);
            } catch (IndexOutOfBoundsException e) {
                throw new DeadlineException("Please specify the task and deadline.");
            }

        } catch (DukeException d) {
            throw new DeadlineException("Please specify the task and deadline.");
        }
    }

    public void processEvent(String command) throws EventException {
        try {
            String theRest = removeFirstWord(command);
            String[] eventAndDate = theRest.split(" /at", 2);
            try {
                String eventDesc = eventAndDate[0];
                String eventDate = eventAndDate[1];
                Event event = new Event(eventDesc, eventDate);
                saveToList(event);
            } catch (IndexOutOfBoundsException e) {
                throw new EventException("Please specify the event name and date.");
            }

        } catch (DukeException d) {
            throw new EventException("Please specify the event name and date.");
        }
    }

    public void listItems() {
        if(this.todos.size() == 0) {
            System.out.println("You don't have any task in your list.");
        } else {
            StringBuilder todoList = new StringBuilder("Here are the task(s) in your list: \n");
            int num = 1;
            for (Task item : this.todos) {
                todoList.append(num + ". " + item.toString() + "\n");
                num++;
            }
            System.out.println(todoList);
        }
    }

    public void saveToList(Task todo) {
        this.todos.add(todo);
        System.out.println("Okay~ I've added this task: \n" + todo.toString());
        System.out.println("Now you have " + this.todos.size() + " task(s) in the list.");
    }

    public void markTaskAsDone(int taskNum) throws DoneException {
        if (taskNum <= 0 || taskNum >= todos.size() ) {
            throw new DoneException("Please enter a valid task number.");
        } else {
            int index = taskNum - 1;
            Task oldTask = this.todos.get(index);
            Task newTask = oldTask.markAsDone();
            this.todos.remove(oldTask);
            this.todos.add(index, newTask);
            System.out.println("YAYY! I've marked this task as done : \n"
                    + newTask.toString());
        }
    }
}
