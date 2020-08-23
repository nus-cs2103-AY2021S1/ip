import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Posh Version of Duke
public class Duke {
    //Characteristics of Duke
    private boolean isChatting;
    private ArrayList<Task> taskList;
    private String filepath;

    //Constants Command Enum
    enum Command {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        BYE,
        DELETE
    }

    //Constructor
    public Duke(String file) {
        //Characteristic of Duke
        this.isChatting = true;
        this.filepath = file;
        this.taskList = new ArrayList<>();
    }

    //Initialise Duke
    private void startChat() {
        //Initialisation Message
        String greeting = "Oh Golly! Who do we have here?\nThe name's Duke, how can I be of assistance?";
        System.out.println(greeting);
        loadTaskList();

        //Initialise scanner to prompt user
        Scanner sc = new Scanner(System.in);

        //Duke runs until user inputs "bye"
        while (this.isChatting) {
            //Obtain user input
            String user_input = sc.nextLine();

            //Obtain command keyword base on user_input
            try {
                Command user_command = getCommand(user_input.trim()); //trim to get rid of unnecessary whitespaces

                //Conditionals based on command keyword
                //list command
                if (user_command.equals(Command.LIST)) {
                    getToDoList();
                }
                //bye command
                else if (user_command.equals(Command.BYE)) {
                    exitDuke();
                }
                //to do command
                else if (user_command.equals(Command.TODO)) {
                    addToDo(user_input);
                }
                //deadline command
                else if (user_command.equals(Command.DEADLINE)) {
                    addDeadline(user_input);
                }
                //event command
                else if (user_command.equals(Command.EVENT)) {
                    addEvent(user_input);
                }
                //done command
                else if (user_command.equals(Command.DONE)) {
                    markTaskDone(user_input);
                }
                //delete command
                else if (user_command.equals(Command.DELETE)) {
                    deleteTask(user_input);
                }
                //To catch inappropriate commands that have not been identified
                else {
                    System.out.println("OH FIDDLESTICKS, WE SEEM TO HAVE HIT A BUMP ON THE ROAD HERE.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //Converts the tasklist arraylist into a labelled list message
    private void getToDoList() {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (i == taskList.size() - 1) {
                output = output + (i + 1) + "." + currentTask;
            } else {
                output = output + (i + 1) + "." + currentTask + "\n";
            }
        }
        String getListMsg = "Here are the tasks in your list:";
        String emptyListMsg = "Oh dear, it seems that your tasks list is empty!";
        if (taskList.size() < 1) {
            System.out.println(emptyListMsg);
        } else {
            System.out.println(getListMsg);
            System.out.println(output);
        }
    }

    private void getTotalTasksMsg() {
        System.out.println("Marvellous! Now you have " + this.taskList.size() + " tasks in your list!");
    }

    //Done command
    //Then, check if user input contains apt integer
    private void markTaskDone(String user_input) throws OutOfTaskListException, NotNumberException {
        //Get number after done keyword
        String int_substring = user_input.substring(5);
        try {
            int int_substring_converted = Integer.parseInt(int_substring);
            Task currentTask = this.taskList.get(int_substring_converted - 1);
            currentTask.markAsDone();
            String markDoneMsg = "Splendid! I've marked the following task as done:";
            System.out.println(markDoneMsg);
            System.out.println("  [" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
            saveTaskList(); //Overwrites current data.txt file
        } catch (NumberFormatException ex) {
            throw new NotNumberException();
        } catch (IndexOutOfBoundsException ex) {
            throw new OutOfTaskListException();
        }
    }

    //Delete command
    //Then, check if user input contains apt integer
    private void deleteTask(String user_input) throws OutOfTaskListException, NotNumberException{
        //Get number after done keyword
        String int_substring = user_input.substring(7);
        try {
            int int_substring_converted = Integer.parseInt(int_substring);
            Task currentTask = this.taskList.get(int_substring_converted - 1);
            String deleteMsg = "No worries, the following task has been deleted from your list:";
            System.out.println(deleteMsg);
            System.out.println("  [" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
            this.taskList.remove(currentTask);
            getTotalTasksMsg();
            saveTaskList(); //Overwrites current data.txt file
        } catch (NumberFormatException ex) {
            throw new NotNumberException();
        } catch (IndexOutOfBoundsException ex) {
            throw new OutOfTaskListException();
        }
    }

    //Adds a To Do task to the task list
    private void addToDo(String user_input) {
        ToDo newTask = new ToDo(user_input.substring(5));
        this.taskList.add(newTask);
        addedToListMsg();
        System.out.println("\t" + newTask);
        getTotalTasksMsg();
        saveTask(newTask);
    }

    //Adds a Deadline task to the task list
    private void addDeadline(String user_input) throws InaptFollowUpCommandException,
            MissingFollowUpCommandException, EmptyTaskException {
        String dateTime = getDateTime(user_input, Command.DEADLINE);
        String deadlineDescription = user_input.substring(9, user_input.indexOf("/")).trim();
        if (deadlineDescription.isEmpty()) {
            throw new EmptyTaskException("deadline");
        } else {
            Deadline newTask = new Deadline(deadlineDescription, dateTime);
            this.taskList.add(newTask);
            addedToListMsg();
            System.out.println("\t" + newTask);
            getTotalTasksMsg();
            saveTask(newTask);
        }
    }

    //Adds an Event task to the task list
    private void addEvent(String user_input) throws InaptFollowUpCommandException,
            MissingFollowUpCommandException, EmptyTaskException {
        String dateTime = getDateTime(user_input, Command.EVENT);
        String eventDescription = user_input.substring(6, user_input.indexOf("/")).trim();
        if (eventDescription.isEmpty()) {
            throw new EmptyTaskException("event");
        } else {
            Event newTask = new Event(eventDescription, dateTime);
            this.taskList.add(newTask);
            addedToListMsg();
            System.out.println("\t" + newTask);
            getTotalTasksMsg();
            saveTask(newTask);
        }
    }

    //To print out add to list message
    private void addedToListMsg() {
        String add_to_listMsg = "No worries, the following task has been added to your list:";
        System.out.println(add_to_listMsg);
    }

    //To obtain date and time that follows a /by or /at
    private String getDateTime(String user_input, Command command) throws InaptFollowUpCommandException,
            MissingFollowUpCommandException, EmptyTaskException {
        int slashIndex = user_input.indexOf("/");

        if (slashIndex != -1) {
            if (checkFollowUpCommand(user_input, slashIndex, command)) {
                String dateTime = user_input.substring(slashIndex + 4).trim();
                if (dateTime.isEmpty()) {
                    throw new EmptyTaskException("date and time");
                } else {
                    return dateTime;
                }
            } else {
                throw new InaptFollowUpCommandException();
            }
        } else {
            throw new MissingFollowUpCommandException();
        }
    }

    //Use to check whether commands such as event and deadline have follow up '/' command
    private boolean checkFollowUpCommand(String user_input, int slashIndex, Command command) throws  EmptyTaskException {
        try {
            if (user_input.charAt(slashIndex + 1) == 'b' && user_input.charAt(slashIndex + 2) == 'y'
                    && user_input.charAt(slashIndex + 3) == ' ' && command.equals(Command.DEADLINE)) {
                return true;
            } else if (user_input.charAt(slashIndex + 1) == 'a' && user_input.charAt(slashIndex + 2) == 't'
                    && user_input.charAt(slashIndex + 3) == ' ' && command.equals(Command.EVENT)) {
                return true;
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyTaskException("date and time");
        }
    }

    //To obtain first keyword of user input
    private Command getCommand(String user_input) throws InaptCommandException, EmptyTaskException,
            UnspecifiedItemException{
        //For commands that have text following a keyword
        if (user_input.contains(" ")) {
            int indexOfFirstSpace = user_input.indexOf(' ');
            String keyword;
            if (indexOfFirstSpace == 4) {
                keyword = user_input.substring(0, 4);
                if (keyword.equals("todo")) {
                    return Command.TODO;
                } else if (keyword.equals("done")) {
                    return Command.DONE;
                } else {
                    throw new InaptCommandException();
                }
            } else if (indexOfFirstSpace == 5) {
                keyword = user_input.substring(0, 5);
                if (keyword.equals("event")) {
                    return Command.EVENT;
                } else {
                    throw new InaptCommandException();
                }
            } else if (indexOfFirstSpace == 6) {
                keyword = user_input.substring(0, 6);
                if (keyword.equals("delete")) {
                    return Command.DELETE;
                } else {
                    throw new InaptCommandException();
                }
            } else if (indexOfFirstSpace == 8){
                keyword = user_input.substring(0, 8);
                if (keyword.equals("deadline")) {
                    return Command.DEADLINE;
                } else {
                    throw new InaptCommandException();
                }
            } else {
                throw new InaptCommandException();
            }
        }
        //For commands that do not have any text after keyword
        else {
            if (user_input.equals("list")) {
                return Command.LIST;
            } else if (user_input.equals("bye")) {
                return Command.BYE;
            } else if (user_input.equals("todo") || user_input.equals("deadline") || user_input.equals("event"))  {
                throw new EmptyTaskException(user_input);
            } else if (user_input.equals("delete") || user_input.equals("done")) {
                throw new UnspecifiedItemException(user_input);
            }
            else {
                throw new InaptCommandException();
            }
        }
    }

    private void saveTask(Task task) {
        File saveFile = new File(this.filepath);
        try {
            saveFile.getParentFile().mkdirs(); //to create directory 'data'
            saveFile.createNewFile(); //to create duke.txt file
            //Check to see whether duke.txt file exists
            if (saveFile.length() > 0) {
                FileWriter toSave = new FileWriter(saveFile, true);
                toSave.write(System.lineSeparator() + task.toTxtFormat());
                toSave.close();
            } else {
                FileWriter toSave = new FileWriter(saveFile);
                toSave.write(task.toTxtFormat());
                toSave.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadTaskList() {
        File saveFile = new File(this.filepath);
        try {
            Scanner s = new Scanner(saveFile);
            while (s.hasNext()) {
                Task toAdd = Task.parse(s.nextLine());
                this.taskList.add(toAdd);
            }
        } catch (FileNotFoundException e) {
        }
    }

    private void saveTaskList() {
        try {
            FileWriter overwriteFile = new FileWriter(this.filepath);
            if (this.taskList.size() > 0) {
                overwriteFile.write(this.taskList.get(0).toTxtFormat());
                overwriteFile.close();
                for (int i = 1 ; i < this.taskList.size() ; i++) {
                    saveTask(this.taskList.get(i));
                }
            } else {
                overwriteFile.write("");
                overwriteFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void exitDuke() {
        String parting = "Well, I'm utterly knackered! Cheerios!";
        System.out.println(parting);
        this.isChatting = false;
    }

    public static void main(String[] args) {
        //Initialise Duke
        Duke chatBot = new Duke("data/duke.txt");
        //Start chatting with Bot
        chatBot.startChat();
    }
}
