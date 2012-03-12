package mz.ukp.util;

/**
 * Greatest common divisor
 *
 * @author stanislav.chizhov
 */
public class GCD {

    public static int gcd(int[] input) {
        int result = input[0];
        for (int i = 1; i < input.length; i++) {
            result = gcd(result, input[i]);
        }
        return result;
    }

    public static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b; // % is remainder 
            a = temp;
        }
        return a;
    }
}
