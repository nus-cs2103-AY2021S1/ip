import java.util.ArrayList;

public class Taskbot {
    private String logo;
    private final String name = "TaskBot";
    private ArrayList<Task> tasks;

    /**
     * Constructor for the Taskbot class
     * @param logo The logo to be displayed for the title
     */
    public Taskbot(String logo) {
        this.logo = logo;
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints out the title using the given logo
     */
    public void printTitle() {
        System.out.println(logo);
    }

    /**
     * Method invoked to greet the user
     */
    public void greet() {
        System.out.printf("Hello there, my name is %s.\nHow may I be of assistance today?\n", name);
    }

    /**
     * Method invoked to say goodbye to the user
     */
    public void sayBye() {
        String message = "Goodbye, I await your next visit.";
        borderString(message);
    }

    public void addTodoTask(String task) {
        //Makes a new Todo task
        Todo newTask = new Todo(task);
        //Adds the new task to the list
        tasks.add(newTask);
        //Informs the user that the task has been added
        borderString("I have added a Todo:\n" + newTask +
                "\n" + listTaskSize());
    }

    public void addEventTask(String input) {
        //splits the input according to whitespace
        String[] parsedString = input.split("/at");

        //Makes a new Event task
        Event newTask = new Event(parsedString[0], parsedString[1].stripLeading());
        //Adds the new task to the list
        tasks.add(newTask);
        //Informs the user that the task has been added
        borderString("I have added an Event:\n" + newTask +
                "\n" + listTaskSize());
    }

    public void addDeadlineTask(String input) {
        //splits the input according to whitespace
        String[] parsedString = input.split("/by");

        //Makes a new Deadline task
        Deadline newTask = new Deadline(parsedString[0], parsedString[1].stripLeading());
        //Adds the new task to the list
        tasks.add(newTask);
        //Informs the user that the task has been added
        borderString("I have added a Deadline:\n" + newTask +
                "\n" + listTaskSize());
    }

    /**
     * Informs the user of the number of tasks in the current list
     */
    public String listTaskSize() {
        return "You now have " + tasks.size() + " task(s) in the list";
    }

    /**
     * Lists all the tasks added so far
     */
    public void listTasks() {
        StringBuilder sb = new StringBuilder("These are the following task(s) to complete:\n");
        //size of the tasks
        int size = tasks.size();
        //Builds the list of tasks
        for (int i = 0; i < size - 1; i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        sb.append(size).append(". ").append(tasks.get(size - 1));
        //Prints the string
        borderString(sb.toString());
    }

    public void completeTask(int taskIndex) {
        tasks.get(taskIndex).completeTask();
        String message = "Understood. The following task is now marked as done:\n";
        message += "    " + tasks.get(taskIndex);
        borderString(message);
    }

    /**
     * Helper function to wrap the given string in lines
     * @param s
     */
    private void borderString(String s) {
        System.out.println("----------------------------------------------");
        System.out.println(s);
        System.out.println("----------------------------------------------\n");
    }
}
