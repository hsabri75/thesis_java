import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalTest {

    @Test
    void gcd() {
        assertEquals(Rational.gcd(78, 66), 6);
        assertEquals(Rational.gcd(270, 192), 6);
        assertEquals(Rational.gcd(37, 37), 37);
        assertEquals(Rational.gcd(53, 1), 1);
        assertEquals(Rational.gcd(1220, 516), 4);
        assertEquals(Rational.gcd(173, 100), 1);
    }

    @Test
    void add() {
        assertTrue(new Rational(1, 2).add(new Rational(1, 2)).equals(new Rational(1, 1)));
        assertTrue(new Rational(1, 3).add(new Rational(1, 3)).equals(new Rational(2, 3)));
        assertTrue(new Rational(4, 7).add(new Rational(11, 3)).equals(new Rational(89, 21)));
        assertTrue(new Rational(4, 7).add(new Rational(-11, 3)).equals(new Rational(-65, 21)));
        assertTrue(new Rational(3, 4).add(new Rational(-6, 8)).equals(new Rational(0, 1)));

    }

    @Test
    void subtract() {
        assertTrue(new Rational(1, 2).subtract(new Rational(1, 2)).equals(new Rational(0, 1)));
        assertTrue(new Rational(1, 3).subtract(new Rational(2, 3)).equals(new Rational(-1, 3)));
        assertTrue(new Rational(4, 7).subtract(new Rational(11, 3)).equals(new Rational(-65, 21)));
        assertTrue(new Rational(4, 7).subtract(new Rational(-11, 3)).equals(new Rational(89, 21)));
        assertTrue(new Rational(3, 4).subtract(new Rational(6, 8)).equals(new Rational(0, 1)));
        assertTrue(new Rational(3, 4).subtract(new Rational(-6, 8)).equals(new Rational(3, 2)));

    }

    @Test
    void multiply() {
        assertTrue(new Rational(1, 2).multiply(new Rational(1, 2)).equals(new Rational(1, 4)));
        assertTrue(new Rational(1, 2).multiply(new Rational(0, 2)).equals(new Rational(0, 1)));
        assertTrue(new Rational(1, -7).multiply(new Rational(1, 1)).equals(new Rational(-1, 7)));
        assertTrue(new Rational(1, 7).multiply(new Rational(-1, 1)).equals(new Rational(-1, 7)));
        assertTrue(new Rational(3, 5).multiply(new Rational(4, 7)).equals(new Rational(12, 35)));
    }

    @Test
    void divide() {
        assertTrue(new Rational(1, 2).divide(new Rational(1, 2)).equals(new Rational(1, 1)));
        assertTrue(new Rational(3, -2).divide(new Rational(1, 2)).equals(new Rational(-3, 1)));
        assertTrue(new Rational(1, -7).divide(new Rational(1, 1)).equals(new Rational(-1, 7)));
        assertTrue(new Rational(4, 7).divide(new Rational(3, 5)).equals(new Rational(20, 21)));
        assertTrue(new Rational(224, 25).divide(new Rational(-282475, 12544)).equals(new Rational(-2809856, 7061875)));

    }

    @Test
    void scalarMultiply() {
        assertTrue(new Rational(1, 2).multiply(-1).equals(new Rational(-1, 2)));
        assertTrue(new Rational(1, 2).multiply(2).equals(new Rational(1, 1)));
        assertTrue(new Rational(3, -7).multiply(7).equals(new Rational(-3, 1)));
        assertTrue(new Rational(9, 13).multiply(new Rational(3)).equals(new Rational(27, 13)));

    }
}