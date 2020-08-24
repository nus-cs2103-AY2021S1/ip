package Duke;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    public static boolean equal(String s1, String s2) throws FileNotFoundException {
        File exp = new File(s1);
        File act = new File(s2);
        Scanner e = new Scanner(exp);
        Scanner a = new Scanner(act);
        boolean equal = true;
        while(e.hasNext() && a.hasNext()){
            if(!e.next().equals(a.next())){
                equal = false;
                break;
            }
        }
        if(equal) {
            if (e.hasNext() && !a.hasNext()) {
                equal = false;
            }
            if (!e.hasNext() && a.hasNext()) {
                equal = false;
            }
        }
        return equal;
    }
    @Test
    public void Test1()  {
        try {
            FileWriter fw = new FileWriter("text-ui-test/input.txt");
            fw.write("");
            fw.close();
            FileWriter fileWriter = new FileWriter("text-ui-test/EXPECTED.TXT");
            fileWriter.write("Task file is empty!");
            fileWriter.close();
            Duke duke = new Duke("text-ui-test/input.txt");
            String[] args = new String[2];
            duke.main(args);
            boolean equal = equal("text-ui-test/EXPECTED.TXT", "text-ui-test/ACTUAL.TXT");
            assertTrue(equal);
        }catch (IOException f){

        }
    }
    @Test
    public void Test2()  {
        try {
            FileWriter fw = new FileWriter("text-ui-test/input.txt", true);
            FileWriter fileWriter = new FileWriter("text-ui-test/EXPECTED.TXT");
            fw.append("event book club /at 12:00-14:00\n");
            fw.append("todo read book\n");
            fw.append("deadline submission /by 2019 12 12\n");
            fw.append("done 1\n");
            fw.append("delete 4\n");
            fw.append("list\n");
            fw.append("bye\n");
            fw.close();
            File f = new File("text-ui-test/input.txt");
            Scanner sc = new Scanner(f);
            fileWriter.write("  ____________________________________________________________\n" +
                    "  Hello! I'm Duke\n" +
                    "  What can I do for you?\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "event book club /at 12:00-14:00\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "  Got it. I've added this task:\n" +
                    "  [E][✗] book club(at: 12:00-14:00)\n" +
                    "  Now you have 1 tasks in the list.\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "todo read book\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "  Got it. I've added this task:\n" +
                    "  [T][✗] read book\n" +
                    "  Now you have 2 tasks in the list.\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "deadline submission /by 2019 12 12\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "  Got it. I've added this task:\n" +
                    "  [D][✗] submission(by: 12 Dec 2019)\n" +
                    "  Now you have 3 tasks in the list.\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "done 1\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "   Nice! I've marked this task as done:\n" +
                    "   [E][✓] book club(at: 12:00-14:00)\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "delete 4\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "  '☹' OOPS!!! The ID is not yet defined.\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "list\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "   Here are the tasks in your list:\n" +
                    "  1.[E][✓] book club(at: 12:00-14:00)\n" +
                    "  2.[T][✗] read book\n" +
                    "  3.[D][✗] submission(by: 12 Dec 2019)\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "bye\n" +
                    "  ____________________________________________________________\n" +
                    "\n" +
                    "  Bye. Hope to see you again soon!\n" +
                    "  ____________________________________________________________\n" +
                    "\n");
            //System.out.println(sc.nextLine());
             Duke.main(new String[10]);
            //boolean equal = equal("text-ui-test/EXPECTED.TXT", "text-ui-test/ACTUAL.TXT");
            //assertTrue(equal);
        }catch (IOException f){

        }
    }

}
