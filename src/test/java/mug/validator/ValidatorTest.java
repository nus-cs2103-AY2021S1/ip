package mug.validator;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import mug.command.Command;
import mug.mugexception.MugException;

public class ValidatorTest {

    @Test
    public void inputTest() {
        try {
            Validator.input(Command.TODO, 1, true);
        } catch (MugException ex) {
            String expect = "HEY!!! Don't be stingy give mug.Mug more information >.<";
            assertEquals(expect, ex.getMessage());
        }
    }

    @Test
    public void infoTest() {
        try {
            Validator.info(Command.TODO, "", true);
        } catch (MugException ex) {
            String expect = "HEY!!! Don't be stingy give mug.Mug more information >.<";
            assertEquals(expect, ex.getMessage());
        }
    }

    @Test
    public void dateTest() {
        try {
            LocalDate actDate = Validator.date("2019-01-12");
            LocalDate expDate = LocalDate.parse("2019-01-12");
            assertEquals(expDate, actDate);
        } catch (MugException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void commandTest() {
        try {
            Command command = Validator.command("todo");
            assertEquals(Command.TODO, command);
        } catch (MugException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void indexTest() {
        try {
            int index = Validator.index("2", 4);
            assertEquals(2, index);
        } catch (MugException ex) {
            ex.printStackTrace();
        }
    }
}
