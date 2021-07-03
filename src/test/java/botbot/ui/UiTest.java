package botbot.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void greet() {
        assertEquals("helluu! I'm\n\n.-. .-')                .-') _  .-. .-')                .-') _\n"
                + "\\  ( OO )              (  OO) ) \\  ( OO )              (  OO) )\n"
                + " ;-----.\\  .-'),-----. /     '._ ;-----.\\  .-'),-----. /     '._\n"
                + " | .-.  | ( OO'  .-.  '|'--...__)| .-.  | ( OO'  .-.  '|'--...__)\n"
                + " | '-' /_)/   |  | |  |'--.  .--'| '-' /_)/   |  | |  |'--.  .--'\n"
                + " | .-. `. \\_) |  | |  |   |  |   | .-. `. \\_) |  | |  |   |  |\n"
                + " | |  \\  |  \\ |  | |  |   |  |   | |  \\  |  \\ |  | |  |   |  |\n"
                + " | '--'  /   `'  '-'  '   |  |   | '--'  /   `'  '-'  '   |  |\n"
                + " `------'      `-----'    `--'   `------'      `-----'    `--'\n"
                + "\nwhat would you like me to do?\n", Ui.greet());
    }

    @Test
    public void showAddResponse() {
        assertEquals("ok! I've added this task:\n  task\nyou now have 1 task in your list\n",
                new UiStub().showAddResponse(null, 1));
        assertEquals("ok! I've added this task:\n  task\nyou now have 3 tasks in your list\n",
                new UiStub().showAddResponse(null, 3));
    }

    @Test
    public void showDeleteResponse() {
        assertEquals("ok! I've removed this task:\n  task\nyou now have 0 task in your list\n",
                new UiStub().showDeleteResponse(null, 0));
        assertEquals("ok! I've removed this task:\n  task\nyou now have 5 tasks in your list\n",
                new UiStub().showDeleteResponse(null, 5));
    }

    @Test
    public void showEditResponse() {
        assertEquals("ok! I've edited the task to:\n  task",
                new UiStub().showEditResponse(null));
    }

    @Test
    public void showErrorResponse() {
        assertEquals("oops! test!\n", new Ui().showErrorResponse("test!"));
        assertEquals("oops! sample!\n", new Ui().showErrorResponse("sample!"));
    }

    @Test
    public void showMarkAsDoneResponse() {
        assertEquals("nice! I've marked this task as done:\n  task\n",
                new UiStub().showMarkAsDoneResponse(null));
    }
}
