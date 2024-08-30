package edu.gonzaga.Farkle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DieTest {
    @Test
    void testDefaultValue() {
        Integer expectedValue = 1;
        Die die = new Die(6);
        System.out.println("Default value should be 1");
        assertEquals(expectedValue, die.getSideUp());
    }

    @Test
    void testInitializedValue() {
        Integer expectedValue = 4;
        Die die = new Die(6, expectedValue);
        System.out.println("Initialized value should be 4");
        assertEquals(expectedValue, die.getSideUp());
    }

    @Test
    void testGreaterThan() {
        Integer die1Value = 6;
        Integer die2Value = 4;
        Die die1 = new Die(6, die1Value);
        Die die2 = new Die(6, die2Value);
        boolean expected = true;

        boolean actual = die1.compareTo(die2) > 0;

        assertEquals(expected, actual);

    }

    @Test
    void testLessThan() {
        Integer die1Value = 1;
        Integer die2Value = 2;
        Die die1 = new Die(6, die1Value);
        Die die2 = new Die(6, die2Value);
        assertTrue(die1.compareTo(die2) < 0 );
    }

    @Test
    void testEquals() {
        Integer die1Value = 3;
        Integer die2Value = 3;
        Die die1 = new Die(6, die1Value);
        Die die2 = new Die(6, die2Value);
        assertTrue(die1.compareTo(die2) == 0);
    }
}

