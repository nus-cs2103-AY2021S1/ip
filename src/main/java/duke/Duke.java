import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class Constructor specifying filepath.
     * @param filepath
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main logic of Duke.
     * @throws DukeException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void run() throws DukeException, IOException, ClassNotFoundException {
        ui.start(storage);
    }

    public static void main(String[] args) throws DukeException, IOException, ClassNotFoundException {
        new Duke("store.ser").run();
        /*
        ArrayList<Task> store = new ArrayList<Task>();
        try {
            Path relativePath = Paths.get("data/store.ser");
            Path absolutePath = relativePath.toAbsolutePath();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("" + absolutePath));
            ArrayList<Task> array = (ArrayList<Task>) in.readObject();
            in.close();
            for (Task task : array) {
                store.add(task);
            }
        } catch (EOFException e) {

        } catch (IOException e) {
            System.out.println(e);
            throw new IOException(e);
        }
        Scanner scan = new Scanner(System.in);
        String intro = "-------------------------\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "-------------------------";
        System.out.println(intro);
        String next = scan.nextLine();
        if (next.equals("blah")) {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        while (next != null) {
            if (next.equals("list")) {
                int counter = 1;
                System.out.println("-------------------------");
                for (Task task : store) {
                    if (task.getDescription() != null) {
                        System.out.println(counter + ". [" + task.getStatusIcon() + "] " + task.getDescription());
                        counter++;
                    }
                }
                System.out.println("-------------------------");
                next = scan.nextLine();
            } else if (next.contains("done")) {
                if (next.length() == 4) {
                    throw new DukeException("\u2639 OOPS!!! Please state what task has been completed.");
                }
                int num = Integer.parseInt(next.substring(5)) - 1;
                Task curr = store.get(num);
                curr.markAsDone();
                System.out.println("-------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + curr.getStatusIcon() + "] " + curr.getDescription());
                System.out.println("-------------------------");
                next = scan.nextLine();
            } else if (next.contains("todo")) {
                try {
                    if (next.length() == 4) {
                        throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                    }
                    ToDos curr = new ToDos(next.substring(5));
                    store.add(curr);
                    System.out.println("-------------------------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [" + curr.getType() + "][" + curr.getStatusIcon() + "] "
                            + curr.getDescription());
                    System.out.println("Now you have " + store.size() + " tasks in the list.");
                    System.out.println("-------------------------");
                    Path relativePath = Paths.get("data/duke.txt");
                    Path absolutePath = relativePath.toAbsolutePath();
                    BufferedWriter writer = new BufferedWriter(new FileWriter("" + absolutePath));
                    writer.append('\n');
                    StringBuilder result = new StringBuilder("");
                    for (Task task : store) {
                        if (task.getDescription() != null) {
                            result.append("[" + task.getType() + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription());
                            result.append("\n");
                        }
                    }
                    writer.append(result);
                    writer.close();
                    next = scan.nextLine();
                } catch (IOException e) {
                    System.out.println(e);
                    throw new IOException("Please create a folder called \"data\" in the project root!");
                }
            } else if (next.contains("deadline")) {
                try {
                    Deadlines curr = new Deadlines(next.substring(9));
                    String description = curr.getDescription();
                    int index = description.indexOf("/") + 4;
                    curr.setDeadline(description.substring(index));
                    curr.setDateTime();
                    store.add(curr);
                    System.out.println("-------------------------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [" + curr.getType() + "][" + curr.getStatusIcon() + "] "
                            + curr.getDescription().substring(0, description.indexOf("/")) + "(by: " + curr.getDeadline() + ")");
                    System.out.println("Now you have " + store.size() + " tasks in the list.");
                    System.out.println("-------------------------");
                    Path relativePath = Paths.get("data/duke.txt");
                    Path absolutePath = relativePath.toAbsolutePath();
                    BufferedWriter writer = new BufferedWriter(new FileWriter("" + absolutePath));
                    writer.append('\n');
                    StringBuilder result = new StringBuilder("");
                    for (Task task : store) {
                        if (task.getDescription() != null) {
                            result.append("[" + task.getType() + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription());
                            result.append("\n");
                        }
                    }
                    writer.append(result);
                    writer.close();
                    next = scan.nextLine();
                } catch (IOException e) {
                    throw new IOException("Please create a folder called \"data\" in the project root!");
                }
            } else if (next.contains("event")) {
                try {
                    Events curr = new Events(next.substring(6));
                    String description = curr.getDescription();
                    int index = description.indexOf("/") + 4;
                    curr.setStart(description.substring(index));
                    curr.setDateTime();
                    store.add(curr);
                    System.out.println("-------------------------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [" + curr.getType() + "][" + curr.getStatusIcon() + "] "
                            + curr.getDescription().substring(0, description.indexOf("/")) + "(at: " + curr.getStart() + ")");
                    System.out.println("Now you have " + store.size() + " tasks in the list.");
                    System.out.println("-------------------------");
                    Path relativePath = Paths.get("data/duke.txt");
                    Path absolutePath = relativePath.toAbsolutePath();
                    BufferedWriter writer = new BufferedWriter(new FileWriter("" + absolutePath));
                    writer.append('\n');
                    StringBuilder result = new StringBuilder("");
                    for (Task task : store) {
                        if (task.getDescription() != null) {
                            result.append("[" + task.getType() + "]" + "[" + task.getStatusIcon() + "] |" + task.getDescription());
                            result.append("\n");
                        }
                    }
                    writer.append(result);
                    writer.close();
                    next = scan.nextLine();
                } catch (IOException e) {
                    throw new IOException("Please create a folder called \"data\" in the project root!");
                }
            } else if (next.contains("delete")) {
                if (next.length() == 6) {
                    throw new DukeException("\u2639 OOPS!!! Please specify what task to delete.");
                }
                int num = Integer.parseInt(next.substring(7)) - 1;
                Task curr = store.get(num);
                store.remove(curr);
                System.out.println("-------------------------");
                System.out.println("Noted! I've removed this task:");
                System.out.println(curr);
                System.out.println("Now you have " + store.size() + " tasks in the list.");
                System.out.println("-------------------------");
                next = scan.nextLine();
            } else if (!next.equals("bye")) {
                System.out.println("-------------------------");
                System.out.println("added: " + next);
                System.out.println("-------------------------");
                store.add(new Task(next));
                next = scan.nextLine();
            }  else {
                System.out.println("-------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-------------------------");
                scan.close();
                Path relativePath = Paths.get("data/store.ser");
                Path absolutePath = relativePath.toAbsolutePath();
                ObjectOutputStream out = new ObjectOutputStream(
                        new FileOutputStream("" + absolutePath)
                );
                out.writeObject(store);
                out.flush();
                out.close();
                break;
            }
        }
        */
    }
}

