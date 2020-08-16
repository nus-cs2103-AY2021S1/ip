import java.util.ArrayList;

public class Taskbot {
    private String logo;
    private final String name = "TaskBot";
    private ArrayList<Task> tasks;
    private int index;

    /**
     * Constructor for the Taskbot class
     * @param logo The logo to be displayed for the title
     */
    public Taskbot(String logo) {
        this.logo = logo;
        this.tasks = new ArrayList<>();
        this.index = 0;
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
        String message = "Bye! Hope to see you again soon!!";
        borderString(message);
    }

    /**
     * Adds a new Task to the list of tasks
     * @param task The task to be added
     */
    public void addTask(String task) {
        //Adds a new task to the list
        tasks.add(new Task(task));
        //Informs the user that the task has been added
        borderString("added: " + task);
    }

    /**
     * Lists all the tasks added so far
     */
    public void listTasks() {
        StringBuilder sb = new StringBuilder();
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

    /**
     * Helper function to wrap the given string in lines
     * @param s
     */
    private void borderString(String s) {
        System.out.println("------------------------------------------");
        System.out.println(s);
        System.out.println("------------------------------------------\n");
    }
}
