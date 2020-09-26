package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class DukeTest {
    /**
     * Tests whether the 2 files at the filePaths given contain same content
     *
     * @param s1 filePath of expected result
     * @param s2 filePath of the actual result
     * @return true if both files contain the same values and false otherwise
     * @throws FileNotFoundException if file is not present in either scenario
     */
    private static boolean equal(String s1, String s2) throws FileNotFoundException {
        File exp = new File(s1);
        File act = new File(s2);
        Scanner e = new Scanner(exp);
        Scanner a = new Scanner(act);
        boolean equal = true;
        while (e.hasNext() && a.hasNext()) {
            if (!e.next().equals(a.next())) {
                equal = false;
                break;
            }
        }
        if (equal) {
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
    public void test1() throws IOException {
        try {
            FileWriter fw = new FileWriter("src/main/java/input.txt");
            fw.write("bye");
            fw.close();
            FileWriter fileWriter = new FileWriter("src/main/java/expected.txt");
            fileWriter.write("  Task file is empty!\n"
                    + "  ____________________________________________________________\n"
                    + "  Hello! I'm Duke\n" + "  What can I do for you?\n"
                    + "  ____________________________________________________________\n" + "\n"
                    + "  ____________________________________________________________\n" + "\n"
                    + "  Bye. Hope to see you again soon!\n"
                    + "  ____________________________________________________________\n" + "\n");
            fileWriter.close();
            String[] args = new String[2];
            Duke.main(args);
            boolean equal = equal("src/main/java/expected.txt", "src/main/java/output.txt");
            assertTrue(equal);
        } catch (IOException f) {
            throw f;
        }
    }
    @Test
    public void test2() throws IOException {
        try {
            FileWriter fw = new FileWriter("src/main/java/input.txt");
            fw.append("event book club /at 12:00-14:00\n");
            fw.append("todo read book\n");
            fw.append("deadline submission /by 2019 12 12\n");
            fw.append("done 1\n");
            fw.append("delete 4\n");
            fw.append("list\n");
            fw.append("bye\n");
            fw.close();
            FileWriter fileWriter = new FileWriter("src/main/java/expected.txt");
            fileWriter.write("Task file is empty!\n"
                    + "  ____________________________________________________________\n"
                    + "  Hello! I'm Duke\n"
                    + "  What can I do for you?\n"
                    + "  ____________________________________________________________\n"
                    + "\n" + "  ____________________________________________________________\n" + "\n"
                    + "  Got it. I've added this task:\n"
                    + "  [E][✗] book club(at: 12:00-14:00)\n"
                    + "  Now you have 1 tasks in the list.\n"
                    + "  ____________________________________________________________\n" + "\n"
                    + "  ____________________________________________________________\n" + "\n"
                    + "  Got it. I've added this task:\n" + "  [T][✗] read book\n"
                    + "  Now you have 2 tasks in the list.\n"
                    + "  ____________________________________________________________\n" + "\n"
                    + "  ____________________________________________________________\n" + "\n"
                    + "  Got it. I've added this task:\n" + "  [D][✗] submission(by: 12 Dec 2019)\n"
                    + "  Now you have 3 tasks in the list.\n"
                    + "  ____________________________________________________________\n" + "\n"
                    + "  ____________________________________________________________\n" + "\n"
                    + "   Nice! I've marked this task as done:\n"
                    + "   [E][✓] book club(at: 12:00-14:00)\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  ____________________________________________________________\n" + "\n"
                    + "  '☹' OOPS!!! The ID is not yet defined.\n"
                    + "  ____________________________________________________________\n"
                    + "\n" + "  ____________________________________________________________\n"
                    + "\n" + "[E][✓] book club(at: 12:00-14:00)\n"
                    + "[T][✗] read book\n" + "[D][✗] submission(by: 12 Dec 2019)\n"
                    + "  ____________________________________________________________\n"
                    + "\n" + "  ____________________________________________________________\n"
                    + "\n" + "  Bye. Hope to see you again soon!\n"
                    + "  ____________________________________________________________\n" + "\n");
            fileWriter.close();
            Duke.main(new String[10]);
            boolean equal = equal("src/main/java/expected.txt", "src/main/java/output.txt");
            assertTrue(equal);
        } catch (IOException f) {
            throw f;
        }
    }
    @Test
    public void test3() throws IOException {
        try {
            FileWriter fw = new FileWriter("src/main/java/input.txt");
            FileWriter fileWriter = new FileWriter("src/main/java/expected.txt");
            fw.write("event concert /at 12:00-10:00\n" + "todo buy book\n" + "done 1\n"
                    + "event book club /at 12:00-10:00\n"
                    + "delete 2\n"
                    + "deadline submission /by 2020 11 14\n"
                    + "list\n"
                    + "blah\n"
                    + "bye\n");
            fw.close();
            fileWriter.write("  Task file is empty!\n"
                    + "  ____________________________________________________________\n"
                    + "  Hello! I'm Duke\n"
                    + "  What can I do for you?\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  '☹' OOPS!!! Start should be less than end.\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  Got it. I've added this task:\n"
                    + "  [T][✗] buy book\n"
                    + "  Now you have 1 tasks in the list.\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "   Nice! I've marked this task as done:\n"
                    + "   [T][✓] buy book\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  '☹' OOPS!!! Start should be less than end.\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  '☹' OOPS!!! The ID is not yet defined.\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  Got it. I've added this task:\n"
                    + "  [D][✗] submission(by: 14 Nov 2020)\n"
                    + "  Now you have 2 tasks in the list.\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "[T][✓] buy book\n"
                    + "[D][✗] submission(by: 14 Nov 2020)\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  '☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  ____________________________________________________________\n"
                    + "\n"
                    + "  Bye. Hope to see you again soon!\n"
                    + "  ____________________________________________________________\n"
                    + "\n");
            fileWriter.close();
            Duke.main(new String[10]);
            boolean equal = equal("src/main/java/expected.txt", "src/main/java/output.txt");
            assertTrue(equal);
        } catch (IOException f) {
            throw f;
        }
    }
    public static void main(String[] args){

    }

}
