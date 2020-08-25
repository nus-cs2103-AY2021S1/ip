import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> listOfTaskEntered;

    public Duke() {
        this.listOfTaskEntered = new ArrayList<>(100);
    }

    //print welcome message
    public void printWelcomeMessage() {
        String emoji = Emoji.CHICKEN.toString();
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
    public void markAsDone(int num) throws DukeException {
        if (num > 0 && num <= listOfTaskEntered.size()) {
            listOfTaskEntered.get(num - 1).markAsDone();
            String msgForDone = "    ____________________________________________________________\n"
                    + "    Nice! I 've marked this task as done: \n"
                    + "       " + listOfTaskEntered.get(num - 1).toString() + "\n"
                    + "    ____________________________________________________________\n";
            System.out.println(msgForDone);
        } else {
            throw new DukeException(
                    "OOPS!!! The task is not found. Please try again."
            );
        }
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
    
    //list all the tasks
    public void list() {
        String msgForList = "    ____________________________________________________________\n";
        msgForList += "    Here are the tasks in your list: \n";
        for (int i = 0; i < listOfTaskEntered.size(); i++) {
            msgForList += "    " + (i + 1) + ". "
                    + listOfTaskEntered.get(i).toString() + "\n";
        }
        msgForList += "    ____________________________________________________________\n";
        System.out.println(msgForList);
    }

    //delete a task
    public void delete(int num) throws DukeException {
        if (num > 0 && num <= listOfTaskEntered.size()) {
            String msgForDelete = "    ____________________________________________________________\n"
                    + "    Noted. I've removed this task: \n"
                    + "       " + listOfTaskEntered.get(num - 1).toString() + "\n";
            listOfTaskEntered.remove(num - 1);
            msgForDelete += this.countNum() + "\n"
                        + "    ____________________________________________________________\n";
            System.out.println(msgForDelete);
        } else {
            throw new DukeException(
                    "OOPS!!! The task is not found. Please try again."
            );
        }
    }
    
    //send bye message
    public void stop() {
        String msgForBye = "    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon! \n"
                + "    ____________________________________________________________\n";
        System.out.println(msgForBye);
    }
    
    //handle to-do 
    public void handleTodo(String instruction) throws DukeException {
        if (instruction.substring(4).isBlank()) {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = "OOPS!!! I'm sorry, but the description cannot be empty. \n"
                    + "    You can do it by adding description after 'todo '." + emoji ;
            throw new DukeException(exceptionMsg);
        }
        String toDoTitle = instruction.substring(5);
        Todo newTodo = new Todo(toDoTitle, false);
        this.addToDo(newTodo);
    }
    
    //handle deadline
    public void handleDeadline(String instruction) throws DukeException {
        int index = instruction.indexOf("/by");
        if (index == 8) {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = "OOPS!!! I'm sorry, but the description cannot be empty. \n"
                    + "    You can do it by adding description after 'event '." + emoji ;
            throw new DukeException(exceptionMsg);
        }
        
        if (index != -1) {
            String by = instruction.substring(index + 3);
            String description = instruction.substring(9, index);
            if (description.isBlank()) {
                String emoji = Emoji.SMILE.toString();
                String exceptionMsg = "OOPS!!! I'm sorry, but the description cannot be empty. \n"
                        + "    You can do it by adding description after 'deadline '." + emoji ;
                throw new DukeException(exceptionMsg);
            } else if (by.isBlank()) {
                String emoji = Emoji.SMILE.toString();
                String exceptionMsg = "OOPS!!! I'm sorry, but the deadline cannot be empty. \n"
                        + "    You can do it by adding deadline after '/by '." + emoji ;
                throw new DukeException(exceptionMsg);
            }
            Deadline deadline = new Deadline(description, by, false);
            this.addDeadline(deadline);
        } else {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = "OOPS!!! I'm sorry, but you have to indicate the deadline. \n"
                    + "    You can do it by adding '/by' after the description." + emoji ;
            throw new DukeException(exceptionMsg);
        }
    }
    
    //handle event
    public void handleEvent(String instruction) throws DukeException {
        int index = instruction.indexOf("/at");
        if (index == 5) {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = "OOPS!!! I'm sorry, but the description cannot be empty. \n"
                    + "    You can do it by adding description after 'event '." + emoji ;
            throw new DukeException(exceptionMsg);
        }
        
        if (index != -1) {
            String time = instruction.substring(index + 3);
            String description = instruction.substring(6, index);
            if (description.isBlank()) {
                String emoji = Emoji.SMILE.toString();
                String exceptionMsg = "OOPS!!! I'm sorry, but the description cannot be empty. \n"
                        + "    You can do it by adding description after 'event '." + emoji ;
                throw new DukeException(exceptionMsg);
            } else if (time.isBlank()) {
                String emoji = Emoji.SMILE.toString();
                String exceptionMsg = "OOPS!!! I'm sorry, but the time cannot be empty. \n"
                        + "    You can do it by adding time after '/at '." + emoji ;
                throw new DukeException(exceptionMsg);
            }
            Event event = new Event(description, time, false);
            this.addEvent(event);
        } else {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = "OOPS!!! I'm sorry, but you have to indicate the time of the event. \n"
                    + "    You can do it by adding '/at' after the description." + emoji ;
            throw new DukeException(exceptionMsg);
        }
    }
    
    //handle invalid input
    public void invalidInput() throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    //store user input and respond to different input
    public void run() {
        this.printWelcomeMessage();
        Storage storage = new Storage("data/data.txt");
        this.listOfTaskEntered = storage.load();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();
            int len = instruction.length();
            try {
                if (instruction.equals("list")) {
                    this.list();
                } else if (instruction.equals("bye")) {
                    storage.writeData(this.listOfTaskEntered);
                    this.stop();
                    break;
                } else if (len >= 5 && instruction.substring(0, 5).equals("done ")) {
                    int num = Integer.parseInt(instruction.substring(5));
                    this.markAsDone(num);
                } else if (len >= 7 && instruction.substring(0, 7).equals("delete ")) {
                    int num = Integer.parseInt(instruction.substring(7));
                    this.delete(num);
                } else if (len >= 4 && instruction.substring(0, 4).equals("todo")) {
                    this.handleTodo(instruction);
                } else if (len >= 8 && instruction.substring(0, 8).equals("deadline")) {
                    this.handleDeadline(instruction);
                } else if (len >= 5 && instruction.substring(0, 5).equals("event")) {
                    this.handleEvent(instruction);
                } else {
                    this.invalidInput();
                }
            } catch (NumberFormatException ex) {
                System.out.println(new DukeException("OOPS!!! I' m sorry, but you have to enter an integer."));
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }
        sc.close();
    }

    //run bot
    public static void main(String[] args) {
        Duke myBot = new Duke();
        myBot.run();
    }
}
