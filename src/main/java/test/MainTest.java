package test;

import bot.*;
import bot.Command;
import bot.InvalidCommandException;
import bot.InvalidInputException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @org.junit.Test
    public void mainTest_testCases_success() throws Exception {
        String expectedOutput = "";
        File file;
        String filePath = "text-ui-test/input.txt";
        try {
            file = new File(filePath);
            File outputFile = new File("text-ui-test/test_assets.txt");
            if (outputFile.exists()) {
                outputFile.delete();
            }
            expectedOutput = Files.readString(Path.of("text-ui-test/EXPECTED-UNIX.txt"));
        } catch (IOException e) {
            throw new IOException();
        }

        Bot testBot = new Bot("Straw Bot", "text-ui-test/test_assets.txt");
        testBot.init(new Scanner(file));
        assertEquals(expectedOutput,
                outContent.toString().trim().replace("\r",""));
    }
}