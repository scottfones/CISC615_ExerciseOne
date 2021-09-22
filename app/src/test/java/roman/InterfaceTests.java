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
        assertNotNull(converter.fromRoman(null));
    }

    @Test
    public void testFromRomanNotNull() {
        String[] valArray = { "a", "A", "9", "-", " " };

        for (String s0 : valArray) {
            for (String s1 : valArray) {
                for (String s2 : valArray) {
                    String testString = s0 + s1 + s2;
                    assertNotNull(converter.fromRoman(testString));
                }
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = { Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE })
    public void testToRoman(int arabicIn) {
        assertNotNull(converter.toRoman(arabicIn));
    }
}
