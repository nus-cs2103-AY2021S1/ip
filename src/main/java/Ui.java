import java.util.Scanner;

/**
 * Encapsulates the chatBot and its behavior.
 */
public class ChatBot {
    TaskList<Task> toDoList;

    /**
     * Instantiates a chatBot with a name.
     */
    ChatBot() {
        toDoList = new TaskList<>();
    }

    /**
     * Prints a welcome message for the user.
     */
    void welcome() {
         String welcomeMessage = "Konichiwa! Welcome to Kaizen\n"
                 + "I am Kai, what can I do for you today?\n";

        System.out.println(welcomeMessage);
    }

    /**
     * Interacts with the user based on his input.
     */
    void getInput() {

        Scanner sc = new Scanner(System.in);
        boolean exitProgram = false;

        while (!exitProgram && sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                String[] inputArray = input.split(" ", 2); // separates the first word from the rest
                String command = inputArray[0].toLowerCase();
                if (command.equals(UserCommand.BYE.getCommand())) {
                    bye();
                    exitProgram = true;
                } else if (command.equals(UserCommand.LIST.getCommand())) {
                    showList();
                } else if (command.equals(UserCommand.DONE.getCommand())) {
                    makeTaskDone(inputArray);
                } else if (command.equals(UserCommand.TODO.getCommand())) {
                    makeTodo(inputArray);
                } else if (command.equals(UserCommand.DEADLINE.getCommand())) {
                    makeDeadline(inputArray);
                } else if (command.equals(UserCommand.EVENT.getCommand())) {
                    makeEvent(inputArray);
                } else if (command.equals(UserCommand.DELETE.getCommand())) {
                    deleteTask(inputArray);
                } else {
                    throw new DukeException("NANI??! Please say something that I can understand!\n");
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Presents the user with the list of tasks
     */
    void showList() {
        // guard clause
        if (this.toDoList.isEmpty()) {
            System.out.println("No tasks at the moment! Good job!\n");
            return;
        }

        System.out.println("Here are your tasks!");
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println((i+1) + ". "
                    + toDoList.get(i));
        }
        System.out.println();
    }

    /**
     * Produce a done Task
     * @param taskStringArray the position of the task in the arraylist
     * @throws DukeException when there is an error with task
     */
    void makeTaskDone(String[] taskStringArray) throws DukeException {
        try {
            // if length is not 2, nothing was passed in after 'done'
            if (taskStringArray.length != 2) {
                throw new DukeException("Which task are you done with? Please key in the task number!\n");
            }
            // check if taskNumber is a number
            int taskNumber = Integer.parseInt(taskStringArray[1]);
            // check if taskNumber is valid
            if (taskNumber <= 0 || taskNumber > this.toDoList.size()) {
                throw new DukeException("Sorry, no such task to mark as done!\n");
            }

            Task currentTask = this.toDoList.get(taskNumber - 1);
            Task newTask = currentTask.markAsDone();
            this.toDoList.set(taskNumber - 1, newTask);
            System.out.println("Sugoi! This task is done!");
            System.out.println(newTask + "\n");

        } catch (NumberFormatException e) {
            throw new DukeException(taskStringArray[1] + " is not an integer!\n");
        }
    }

    /**
     * Make a todo task
     * @param inputArray the string that the user input
     * @throws DukeException to handle input errors
     */
    void makeTodo(String[] inputArray) throws DukeException {
        // if length is not 2, nothing was passed in after 'todo'
        if (inputArray.length != 2) {
            throw new DukeException("NANI??! Enter a description for your todo!\n");
        }

        String description = inputArray[1];
        Task taskToAdd = new ToDo(description);
        addTask(taskToAdd);
    }

    /**
     * Make a deadline task
     * @param inputArray the string that the user input
     * @throws DukeException to handle input errors
     */
    void makeDeadline(String[] inputArray) throws DukeException {
        // if length is not 2, nothing was passed in after 'deadline'
        if (inputArray.length != 2) {
            throw new DukeException("NANI??! Enter a description for your deadline!\n");
        }

        // if description is lacking a /by keyword
        String description = inputArray[1];
        if (description.indexOf("/by") < 0) {
            throw new DukeException("Please enter a valid deadline! Remember to add '/by'\n");
        }

        String[] descriptionArray = description.split("/by");
        if (descriptionArray.length != 2) {
            throw new DukeException("NANI??! Enter your deadline name & an end-time!\n");
        }

        String deadlineName = descriptionArray[0];
        String deadlineEndTime = descriptionArray[1];

        Task taskToAdd = new Deadline(deadlineName, deadlineEndTime);
        addTask(taskToAdd);
    }

    /**
     * Make an event task
     * @param inputArray the string that the user input
     * @throws DukeException to handle input errors
     */
    void makeEvent(String[] inputArray) throws DukeException {
        // if length is not 2, nothing was passed in after 'makeEvent'
        if (inputArray.length != 2) {
            throw new DukeException("NANI??! Enter a description for your event!\n");
        }

        // if event is lacking a /at keyword
        String description = inputArray[1];
        if (description.indexOf("/at") < 0) {
            throw new DukeException("Please enter a valid event! Remember to add '/at'\n");
        }

        String[] descriptionArray = description.split("/at");
        if (descriptionArray.length != 2) {
            throw new DukeException("NANI??! Enter your event name & an event timing!\n");
        }

        String eventName = descriptionArray[0];
        String eventTiming = descriptionArray[1];

        Task taskToAdd = new Event(eventName, eventTiming);
        addTask(taskToAdd);
    }

    /**
     * Adds a task to the todoList
     * @param taskToAdd the task to add to the todoList
     */
    void addTask(Task taskToAdd) {
        this.toDoList.add(taskToAdd);
        System.out.println("Hai! I have added this task to your list:\n"
                + taskToAdd);
        printToDoListSize();
    }

    /**
     * Delete a particular task from the todo list
     * @param inputArray the string that the user input
     * @throws DukeException to handle input errors
     */
    void deleteTask(String[] inputArray) throws DukeException {
        try {
            // if length is not 2, nothing was passed in after 'done'
            if (inputArray.length != 2) {
                throw new DukeException("Which task do you want to delete? Please key in the task number!\n");
            }
            // check if taskNumber is a number
            int taskNumber = Integer.parseInt(inputArray[1]);
            // check if taskNumber is valid
            if (taskNumber <= 0 || taskNumber > this.toDoList.size()) {
                throw new DukeException("Sorry, no such task to delete!\n");
            }

            Task currentTask = this.toDoList.get(taskNumber - 1);
            System.out.println("Hai! This task has been deleted!");
            this.toDoList.remove(currentTask);
            System.out.println(currentTask);
            printToDoListSize();

        } catch (NumberFormatException e) {
            throw new DukeException("'" + inputArray[1] + "'" + " is not an integer!\n");
        }
    }

    /**
     * Abstracted method to print out the list size
     */
    void printToDoListSize() {
        System.out.println("You now have "
                + this.toDoList.size()
                + " tasks in your list. Gambatte!\n");
    }

    /**
     * Says 'bye' to the user.
     */
    void bye() {
        System.out.println("Sayonara! See you again my friend!");
    }
}
