import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

public enum DukeCommand {
    LIST("list", 100),

    TODO("todo", 200),DEADLINE("deadline", 201),EVENT("event", 201),

    DONE("done", 300),DELETE("delete", 301),SAVE("save", 302),

    BYE("bye", 400);

    public static String gap = "        ";
    public static List<Task> tasks;

    private String command;
    private int index;

    private DukeCommand(String command, int index) {
        this.command = command;
        this.index = index;
    }

    public String getCommand() { return command; }

    public void setCommand(String command) { this.command = command; }

    public int getIndex() { return index; }

    public void setIndex(int index) { this.index = index; }

    public static void listComm(String input) throws DukeException{

        if(input.split(" ").length != 1) {
            throw new DukeException("\u2639 OOPS!!! Wrong 'list' command parameters!");
        }

        System.out.println("        Here are the tasks in your list:");
        int ctr = 1;
        StringBuilder sb;
        for(Task task: tasks) {
            sb = new StringBuilder();
            sb.append(gap).append(ctr).append(".").append(task);
            System.out.println(sb.toString());
            ctr++;
        }
    }

    public static void todoComm(String input) throws DukeException{

        Pattern pattern = Pattern.compile("todo ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*)");
        if(!pattern.matcher(input).matches()) {
            throw new DukeException("\u2639 OOPS!!! Wrong 'todo' command format!");
        } else {
            Todo todo = new Todo(input.substring(5));
            tasks.add(todo);

            System.out.println("        Got it. I've added this task:");
            System.out.println("          " + todo);
            System.out.println("        Now you have " + tasks.size() + " task(s) in the list.");
        }
    }

    public static void deadlineComm(String input) throws DukeException{

        Pattern pattern = Pattern.compile("deadline ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*) /by ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*)");
        if(!pattern.matcher(input).matches()) {
            throw new DukeException("\u2639 OOPS!!! Wrong 'deadline' command format!");
        } else {
            String[] s = input.substring(9).split(" /by ");
            Deadline deadline = new Deadline(s[0], s[1]);
            tasks.add(deadline);

            System.out.println("        Got it. I've added this task:");
            System.out.println("          " + deadline);
            System.out.println("        Now you have " + tasks.size() + " task(s) in the list.");
        }
    }

    public static void eventComm(String input) throws DukeException{

        Pattern pattern = Pattern.compile("event ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*) /at ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*)");
        if(!pattern.matcher(input).matches()) {
            throw new DukeException("\u2639 OOPS!!! Wrong 'event' command format!");
        } else {
            String[] s = input.substring(6).split(" /at ");
            Event event = new Event(s[0], s[1]);
            tasks.add(event);

            System.out.println("        Got it. I've added this task:");
            System.out.println("          " + event);
            System.out.println("        Now you have " + tasks.size() + " task(s) in the list.");
        }
    }

    public static void doneComm(String input) throws DukeException{

        Pattern pattern = Pattern.compile("done [1-9][0-9]{0,}");
        if(!pattern.matcher(input).matches()) {
            throw new DukeException("\u2639 OOPS!!! Wrong 'done' command format!");
        } else {
            int index = Integer.parseInt(""+input.charAt(5)) - 1;
            if(tasks.size() >= index && index >= 0) {
                Task tas = tasks.get(index);
                tas.markAsDone();

                System.out.println("        Nice! I've marked this task as done: \n");
                System.out.println("          " + tas);
            }
        }
    }

    public static void deleteComm(String input) throws DukeException{

        Pattern pattern = Pattern.compile("delete [1-9][0-9]{0,}");
        if(!pattern.matcher(input).matches()) {
            throw new DukeException("\u2639 OOPS!!! Wrong 'delete' command format!");
        } else {
            int index = Integer.parseInt(""+input.charAt(7)) - 1;
            if(tasks.size() > index && index >= 0) {
                Task tas = tasks.get(index);
                tasks.remove(index);

                System.out.println("        Noted. I've removed this task:");
                System.out.println("          " + tas);
            } else {
                throw new DukeException("\u2639 OOPS!!! There isn't a task with that index!");
            }
        }
    }

    public static void saveComm(String input) throws DukeException{

        try{
            String pathName = "." + File.separator + "data" + File.separator;
            String fileName = "saved.duke";

            File f = new File(pathName);
            if(!f.exists()){
                f.mkdirs();
            }

            File file = new File(pathName + fileName);
            file.delete();
            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            byte[] data = SerializeUtil.serialize(tasks);
            fos.write(data);

            System.out.println("        Saved!");
        } catch(Exception e) {
            throw new DukeException("Oops! Something went wrong!");
        }

    }
}
