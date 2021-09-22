package roman;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FunctionalityTests {
    private RomanConverter converter;

    static String[] numeralOnes = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
    static String[] numeralTens = { "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
    static String[] numeralHunds = { "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
    static String[] numeralThous = { "M", "MM", "MMM" };

    public static int constructInt(int thous, int hunds, int tens, int ones) {
        return (thous + 1) * 1000 + (hunds + 1) * 100 + (tens + 1) * 10 + (ones + 1) * 1;
    }

    public static String constructStr(String[] numeralArray) {
        return String.join("", numeralArray);
    }

    @BeforeEach
    public void setUp() {
        converter = new RomanConverterImplB();
    }

    @Test
    public void testFromRomanValidUnits() {
        for (int i = 0; i < 9; i++) {
            assertEquals(i + 1, converter.fromRoman(numeralOnes[i]));
            assertEquals(10 * (i + 1), converter.fromRoman(numeralTens[i]));
            assertEquals(100 * (i + 1), converter.fromRoman(numeralHunds[i]));

            if (i < 3) {
                assertEquals(1000 * (i + 1), converter.fromRoman(numeralThous[i]));
            }
        }
    }

    @Test
    public void testFromRomanValidCombinations() {
        for (int thous = 0; thous < numeralThous.length; thous++) {
            for (int hunds = 0; hunds < numeralHunds.length; hunds++) {
                for (int tens = 0; tens < numeralTens.length; tens++) {
                    for (int ones = 0; ones < numeralOnes.length; ones++) {
                        int intVal = constructInt(thous, hunds, tens, ones);
                        String strVal = numeralThous[thous] + numeralHunds[hunds] + numeralTens[tens]
                                + numeralOnes[ones];

                        assertEquals(intVal, converter.fromRoman(strVal));
                    }
                }
            }
        }
    }

    @Test
    public void testFromRomanInvalidDoubleUnits() {
        for (int i = 4; i < 9; i++) {
            String onesNum = numeralOnes[i] + numeralOnes[i];
            assertThrows(IllegalArgumentException.class, () -> converter.fromRoman(onesNum));

            String tensNum = numeralTens[i] + numeralTens[i];
            assertThrows(IllegalArgumentException.class, () -> converter.fromRoman(tensNum));

            String hundsNum = numeralHunds[i] + numeralHunds[i];
            assertThrows(IllegalArgumentException.class, () -> converter.fromRoman(hundsNum));

        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "IIIIIV", "VX", "LC", "DM", "VL", "XD", "IM" })
    public void testFromRomanInvalidForm(String invNumeral) {
        assertThrows(IllegalArgumentException.class, () -> converter.fromRoman(invNumeral));
    }

    @Test
    public void testToRomanValid() {
        for (int thous = 0; thous < numeralThous.length; thous++) {
            for (int hunds = 0; hunds < numeralHunds.length; hunds++) {
                for (int tens = 0; tens < numeralTens.length; tens++) {
                    for (int ones = 0; ones < numeralOnes.length; ones++) {
                        int intVal = constructInt(thous, hunds, tens, ones);
                        String strVal = numeralThous[thous] + numeralHunds[hunds] + numeralTens[tens]
                                + numeralOnes[ones];

                        assertEquals(strVal, converter.toRoman(intVal));
                    }
                }
            }
        }
    }

    @Test
    public void testToRomanInvalid() {
        assertThrows(IllegalArgumentException.class, () -> converter.toRoman(0));
        assertThrows(IllegalArgumentException.class, () -> converter.toRoman(40000));
    }
}
