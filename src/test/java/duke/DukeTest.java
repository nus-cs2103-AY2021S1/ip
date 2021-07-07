package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class DukeTest {

    private static final String TEST_PATH_TASKS = "DukeTestList.txt";
    private static final String TEST_PATH_NOTES = "NoteTestPath.txt";
    private Duke duke;


    @BeforeEach
    public void init() {

        //initialize file and store default task list
        duke = new Duke("DukeTestList.txt", "NoteTestPath.txt");
        try {
            FileWriter fw = new FileWriter(TEST_PATH_TASKS);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(TEST_PATH_NOTES);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void resetFilePath() {
        try {
            FileWriter fw = new FileWriter(TEST_PATH_TASKS);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(TEST_PATH_NOTES);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getResponseTest() {


        assertEquals("Shutting down... Hope to see you again soon!", duke.getResponse("bye"));

        assertEquals("Got it. I've added this task:\n"
                + "[T][✘] valid todo\n"
                + "Now you have 1 tasks in the list.", duke.getResponse("todo valid todo"));

        assertEquals("Got it. I've added this task:\n"
                + "[D][✘] valid deadline (by: Dec 1 2019 00:11)\n"
                + "Now you have 2 tasks in the list.",
                duke.getResponse("deadline valid deadline /by 2019-12-01 00:11"));

        assertEquals("Got it. I've added this task:\n"
                + "[E][✘] valid event (at: Aug 22 2020 23:32)\n"
                + "Now you have 3 tasks in the list.", duke.getResponse("event valid event /at 2020-08-22 23:32"));

        assertEquals("1.[T][✘] valid todo\n"
                + "2.[D][✘] valid deadline (by: Dec 1 2019 00:11)\n"
                + "3.[E][✘] valid event (at: Aug 22 2020 23:32)", duke.getResponse("list"));

        assertEquals("Nice! I've marked this task as done:\n"
                + "[E][✓] valid event (at: Aug 22 2020 23:32)", duke.getResponse("done 3"));

        assertEquals("Noted. I've removed this task:\n"
                + "[E][✓] valid event (at: Aug 22 2020 23:32)\n"
                + "Now you have 2 tasks in the list.", duke.getResponse("delete 3"));

        assertEquals("Here are the matching tasks in your list:\n"
                + "1.[T][✘] valid todo", duke.getResponse("find todo"));

        assertEquals("Got it. Your note has been added:\n"
                + "test1", duke.getResponse("note test1"));

        assertEquals("1.test1", duke.getResponse("notes"));

        assertEquals("Noted. I've removed this note:\n"
                + "test1\nNow you have 0 notes in your program.", duke.getResponse("RemoveNote 1"));

    }

    @Test
    public void getGreetingTest() {
        assertEquals("Hello! Dukenizer is back!\nWhat can I do for you?\n\n"
                + "Some things you can do:\n"
                + "1) list: see your task list!\n"
                + "2) notes: see all your notes!\n"
                + "3) todo <description>: add a new task!\n"
                + "4) note <description>: add a new note!\n\n"
                + "Type 'help' to see the full list of commands!", duke.getGreeting());
    }

}
