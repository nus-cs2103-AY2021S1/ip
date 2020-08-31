package test;

import bot.Bot;

import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Scanner;

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
    public void mainTest_givenInputs_success() throws Exception {
        String expectedOutput = "";
        File file;
        String inputFilePath = "text-ui-test/input.txt";
        String assetFilePath = "text-ui-test/test_assets.txt";
        String expectedOutputFilePath = "text-ui-test/EXPECTED-UNIX.txt";

        try {
            file = new File(inputFilePath);
            File outputFile = new File(assetFilePath);
            if (outputFile.exists()) {
                outputFile.delete();
            }
            expectedOutput = Files.readString(Path.of(expectedOutputFilePath));
        } catch (IOException e) {
            throw new IOException();
        }

        Bot testBot = new Bot("Straw Bot", assetFilePath);
        testBot.init(new Scanner(file));
        assertEquals(expectedOutput,
                outContent.toString().strip().replace("\r",""));
    }
}