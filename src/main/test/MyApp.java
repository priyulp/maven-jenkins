package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyAppTest {

    @Test
    void testExampleMethod() {
        MyApp app = new MyApp();
        int result = app.add(5, 3);
        assertEquals(8, result, "Addition should return 8");
    }
}
