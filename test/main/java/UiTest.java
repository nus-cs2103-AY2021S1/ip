package main.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    @Test
    void invalidOrder() {
        assertEquals("\n-> Oops, there is an error...\n" +
                "-> please add correct description to \"Todo\" order\n", new Ui().invalidOrder("Todo"));
    }
}