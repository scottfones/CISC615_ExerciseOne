package roman;

public class RomanConverterImplA implements RomanConverter {
    // From:
    // https://www.geeksforgeeks.org/converting-roman-numerals-decimal-lying-1-3999/
    static int value(char r) {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        if (r == 'D')
            return 500;
        if (r == 'M')
            return 1000;
        return -1;
    }

    // From:
    // https://www.geeksforgeeks.org/converting-roman-numerals-decimal-lying-1-3999/
    // Change: Function name
    // Change: Removed comments
    // Change: Added invalid char error
    public int fromRoman(String str) {
        int res = 0;

        if (str.length() == 0) {
            throw new IllegalArgumentException("Invalid roman numeral: %d".formatted(str));
        }

        for (int i = 0; i < str.length(); i++) {
            int s1 = value(str.charAt(i));

            if (s1 == -1) {
                throw new IllegalArgumentException("Invalid roman numeral: %d".formatted(s1));
            }

            if (i + 1 < str.length()) {
                int s2 = value(str.charAt(i + 1));

                if (s2 == -1) {
                    throw new IllegalArgumentException("Invalid roman numeral: %d".formatted(s1));
                }

                if (s1 >= s2) {
                    res = res + s1;
                } else {
                    res = res + s2 - s1;
                    i++;
                }
            } else {
                res = res + s1;
            }
        }

        return res;
    }

    // From: https://algorithms.tutorialhorizon.com/convert-integer-to-roman/
    // Change: Method name
    // Change: Return a string rather than print output
    // Change: Added check to return "Invalid" with non-positive input
    public String toRoman(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Input must be > 0: %d".formatted(num));
        }

        System.out.println("Integer: " + num);
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] romanLiterals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }

    public static void main(String[] args) {
    }
}
