import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args){
        init();
        String gap = "        ";

        String init = "        ____________________________________________________________\n" +
                "        Hello! I'm Duke\n" +
                "        What can I do for you?\n" +
                "        ____________________________________________________________\n";

        System.out.println(init);

        Scanner sc = new Scanner(System.in);
        String temp;

        while(!(temp = sc.nextLine()).equals("bye")) {
            System.out.println("        ____________________________________________________________");
            String comm = temp.split(" ")[0];
            boolean flag = false;

            for(DukeCommand command: DukeCommand.values()) {
                if(comm.equals(command.getCommand())) {
                    flag = true;
                    break;
                };
            }

            try {
                if(flag) {
                    Method[] methods = DukeCommand.class.getMethods();
                    for(Method method: methods) {
                        if(method.getName().equals(comm+"Comm")) {
                            method.invoke(null, temp);
                        }
                    }

                } else {
                    throw new DukeException("\u2639 OOPS!!! Unknown command!");
                }
            } catch (DukeException e) {
                System.out.println("        " + e.getMessage());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                if(e.getTargetException().getClass() == DateTimeParseException.class) {
                    System.out.println("        Invalid date.");
                } else {
                    System.out.println("        " + e.getTargetException().getMessage());
                }
            }
            System.out.println("        ____________________________________________________________\n");
        }

        String exit =
                "        ____________________________________________________________\n" +
                "        Bye. Hope to see you again soon!\n" +
                "        ____________________________________________________________\n";
        System.out.println(exit);
    }

    public static void init() {
        String pathName = "." + File.separator + "data" + File.separator;
        String fileName = "saved.duke";

        try{
            File file = new File(pathName + fileName);
            if(file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                List<Task> list = (List<Task>)SerializeUtil.deserialize(fis.readAllBytes());
                DukeCommand.tasks = list;
            } else {
                DukeCommand.tasks = new ArrayList<Task>();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

