package roman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class InterfaceTests {
    private RomanConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new RomanConverterImplB();
    }

    @Test
    public void testFromRomanNull() {
        assertThrows(NullPointerException.class, () -> converter.fromRoman(null));
    }

    @Test
    public void testFromRomanEmpty() {
        assertThrows(IllegalArgumentException.class, () -> converter.fromRoman(""));
    }


    @Test
    public void testFromRomanInvalidLengthThree() {
        String[] valArray = { "a", "A", "9", "-", " " };

        for (String s0 : valArray) {
            for (String s1 : valArray) {
                for (String s2 : valArray) {
                    String testStr = s0 + s1 + s2;
                    assertThrows(IllegalArgumentException.class, () -> converter.fromRoman(testStr));
                }
            }
        }
    }

    @Test
    public void testToRomanValid() {
        assertNotNull(converter.toRoman(1));
    }

    @ParameterizedTest
    @ValueSource(ints = { Integer.MIN_VALUE, -1, 0, Integer.MAX_VALUE })
    public void testToRomanInvalid(int intVal) {
        assertThrows(IllegalArgumentException.class, () -> converter.toRoman(intVal));
    }
}
