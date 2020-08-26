package main.java.duke;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static String memory_file_dir = "./data/";
    public static String memory_file_name = "task_list.txt";

    public Duke(String memory_file_dir, String memory_file_name) {
        this.storage = new Storage(memory_file_dir, memory_file_name);
        try {
            this.tasks = new TaskList(storage.readMemoTasks(), memory_file_dir, memory_file_name);
        } catch (Exception ex) {
            HandleException.handleException(DukeException.ExceptionType.read_file);
            tasks = new TaskList();
            System.out.println(SpecialFormat.indent + "Let's start first without the task records!");
        }
        this.ui = new Ui(memory_file_dir, memory_file_name, tasks.task_collections);
    }

    public void run() {
        ui.processRequests();
    }

    public static void main(String[] args) {
        new Duke(memory_file_dir, memory_file_name).run();
    }

}

//compile when current directory is at IndividualProject/text-ui-test
//javac -cp ../src/ ../src/main/java/Task.java   etc. (Task, Deadline, Event, Todo, Duke)
//sh runtest.sh