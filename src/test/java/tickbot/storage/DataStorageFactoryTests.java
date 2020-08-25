package tickbot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DataStorageFactoryTests {
    @Test
    public void testSingleton() {
        DataStorage s1 = DataStorageFactory.getInstance();
        DataStorage s2 = DataStorageFactory.getInstance();
        assertEquals(s1, s2); // same instance
    }
}