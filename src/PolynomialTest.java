import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @Test
    void derivative() {
        assertArrayEquals(new Polynomial(new int[]{3,6,2}).derivative().getCoefficients(), new int[][]{{6,1},{4,1}});
        assertArrayEquals(new Polynomial(new int[]{0,-7}).derivative().getCoefficients(), new int[][]{{-7,1}});
        assertArrayEquals(new Polynomial(new int[]{1,3}).derivative().getCoefficients(), new int[][]{{3,1}});
        assertArrayEquals(new Polynomial(new int[]{2,3,5,-2,1}).derivative().getCoefficients(), new int[][]{{3,1},{10,1},{-6,1},{4,1}});
        assertArrayEquals(new Polynomial(new int[]{3,-2,0,-5,1}).derivative().getCoefficients(), new int[][]{{-2,1},{0,1},{-15,1},{4,1}});

         }

    @Test
    void evaluate() {

        assertEquals(new Polynomial(new int[]{3,7,4}).evaluate(1), new Rational (14));
        assertEquals(new Polynomial(new int[]{3,7,4}).evaluate(2), new Rational (33));
        assertEquals(new Polynomial(new int[]{3,7,4}).evaluate(0), new Rational (3));
        assertEquals(new Polynomial(new int[]{3,7,4}).evaluate(-1), new Rational (0));

        assertEquals(new Polynomial(new int[]{15,-23,0,3,1}).evaluate(-2), new Rational (53));
        assertEquals(new Polynomial(new int[]{15,-23,0,3,1}).evaluate(-1), new Rational (36));
        assertEquals(new Polynomial(new int[]{15,-23,0,3,1}).evaluate(0), new Rational (15));
        assertEquals(new Polynomial(new int[]{15,-23,0,3,1}).evaluate(1), new Rational (-4));
        assertEquals(new Polynomial(new int[]{15,-23,0,3,1}).evaluate(2), new Rational (9));
    }

    @Test
    void scalarMultiply() {
        assertArrayEquals(new Polynomial(new int[]{1,7,3}).multiply(2).getCoefficients(), new int[][]{{2,1},{14,1},{6,1}});
        assertArrayEquals(new Polynomial(new int[]{1,-7,3}).multiply(2).getCoefficients(), new int[][]{{2,1},{-14,1},{6,1}});
        assertArrayEquals(new Polynomial(new int[]{1,7,-3,0,8}).multiply(2).getCoefficients(), new int[][]{{2,1},{14,1},{-6,1},{0,1},{16,1}});
        assertArrayEquals(new Polynomial(new int[]{1,7,-3,0,8}).multiply(0).getCoefficients(), new int[][]{{0,1}});

    }

        @Test
    void multiply() {
        assertArrayEquals(new Polynomial(new int[]{-1}).multiply(new Polynomial(new int[]{3})).getCoefficients(), new int[][]{{-3,1}});
        assertArrayEquals(new Polynomial(new int[]{1,1}).multiply(new Polynomial(new int[]{1,1})).getCoefficients(), new int[][]{{1,1},{2,1},{1,1}});
        assertArrayEquals(new Polynomial(new int[]{1,1}).multiply(new Polynomial(new int[]{-1,1})).getCoefficients(), new int[][]{{-1,1},{0,1},{1,1}});
        assertArrayEquals(new Polynomial(new int[]{2,1}).multiply(new Polynomial(new int[]{-2,1})).getCoefficients(), new int[][]{{-4,1},{0,1},{1,1}});
        assertArrayEquals(new Polynomial(new int[]{5,3,1}).multiply(new Polynomial(new int[]{-7,2})).getCoefficients(), new int[][]{{-35,1},{-11,1},{-1,1},{2,1}});
        assertArrayEquals(new Polynomial(new int[]{-17,5}).multiply(new Polynomial(new int[]{11,-8,3})).getCoefficients(), new int[][]{{-187,1},{191,1},{-91,1},{15,1}});
        assertArrayEquals(new Polynomial(new int[]{11,-8,3}).multiply(new Polynomial(new int[]{-17,5})).getCoefficients(), new int[][]{{-187,1},{191,1},{-91,1},{15,1}});
    }
    @Test
    void add(){
        assertArrayEquals(new Polynomial(new int[]{1}).add(new Polynomial(new int[]{1})).getCoefficients(), new int[][]{{2,1}});
        assertArrayEquals(new Polynomial(new int[]{1}).add(new Polynomial(new int[]{5})).getCoefficients(), new int[][]{{6,1}});
        assertArrayEquals(new Polynomial(new int[]{2,-3,7}).add(new Polynomial(new int[]{-4,3,2})).getCoefficients(), new int[][]{{-2,1},{0,1},{9,1}});
        assertArrayEquals(new Polynomial(new int[]{2}).add(new Polynomial(new int[]{5,-3,8})).getCoefficients(), new int[][]{{7,1},{-3,1},{8,1}});
        assertArrayEquals(new Polynomial(new int[]{2}).add(new Polynomial(new int[]{-2})).getCoefficients(), new int[][]{{0,1}});
        assertArrayEquals(new Polynomial(new int[]{2,5,3}).add(new Polynomial(new int[]{-2,-5,-3})).getCoefficients(), new int[][]{{0,1}});
        assertArrayEquals(new Polynomial(new int[]{0}).add(new Polynomial(new int[]{-2,-5})).getCoefficients(), new int[][]{{-2,1},{-5,1}});

    }

    @Test
    void subtract(){
        assertArrayEquals(new Polynomial(new int[]{1}).subtract(new Polynomial(new int[]{1})).getCoefficients(), new int[][]{{0,1}});
        assertArrayEquals(new Polynomial(new int[]{5,7,13}).subtract(new Polynomial(new int[]{5,7,13})).getCoefficients(), new int[][]{{0,1}});
        assertArrayEquals(new Polynomial(new int[]{1}).subtract(new Polynomial(new int[]{5})).getCoefficients(), new int[][]{{-4,1}});
        assertArrayEquals(new Polynomial(new int[]{2,-3,7}).subtract(new Polynomial(new int[]{-4,3,2})).getCoefficients(), new int[][]{{6,1},{-6,1},{5,1}});

        assertArrayEquals(new Polynomial(new int[]{2}).subtract(new Polynomial(new int[]{5,-3,8})).getCoefficients(), new int[][]{{-3,1},{3,1},{-8,1}});
        assertArrayEquals(new Polynomial(new int[]{2}).subtract(new Polynomial(new int[]{-2})).getCoefficients(), new int[][]{{4,1}});
        assertArrayEquals(new Polynomial(new int[]{2,5}).subtract(new Polynomial(new int[]{-2,-5})).getCoefficients(), new int[][]{{4,1},{10,1}});
        assertArrayEquals(new Polynomial(new int[]{0}).subtract(new Polynomial(new int[]{-2,-5})).getCoefficients(), new int[][]{{2,1},{5,1}});

        assertArrayEquals(
                new Polynomial(new int[][]{{5,1},{430,17}}).subtract(
                new Polynomial(new int[][]{{4730,289},{430,17}})).getCoefficients(),
                new int[][]{{3285,289}});

    }
    @Test
    void filterLeadingZeroes(){
        assertArrayEquals(new Polynomial(new int[]{1,2,3}).getCoefficients(), new int[][]{{1,1},{2,1},{3,1}});
        assertArrayEquals(new Polynomial(new int[]{0,2,3}).getCoefficients(), new int[][]{{0,1},{2,1},{3,1}});
        assertArrayEquals(new Polynomial(new int[]{1,2,3,0}).getCoefficients(), new int[][]{{1,1},{2,1},{3,1}});
        assertArrayEquals(new Polynomial(new int[]{0,0,0}).getCoefficients(), new int[][]{{0,1}});
    }

    @Test
    void divide(){
        Polynomial p2=new Polynomial(new int[]{3,8,2,-6,-10});
        p2.divide(new Polynomial(new int[]{2,2,2}));
        assertArrayEquals(p2.quotient.getCoefficients(),new int[][]{{4,1},{2,1},{-5,1}});
        assertArrayEquals(p2.remainder.getCoefficients(),new int[][]{{-5,1},{-4,1}});

        Polynomial p3=new Polynomial(new int[]{-10,1,-5,2});
        p3.divide(new Polynomial(new int[]{1,-4,1}));
        assertArrayEquals(p3.quotient.getCoefficients(),new int[][]{{3,1},{2,1}});
        assertArrayEquals(p3.remainder.getCoefficients(),new int[][]{{-13,1},{11,1}});

        Polynomial p4=new Polynomial(new int[]{-42,0,-12,1});
        p4.divide(new Polynomial(new int[]{1,-2,1}));
        assertArrayEquals(p4.quotient.getCoefficients(),new int[][]{{-10,1},{1,1}});
        assertArrayEquals(p4.remainder.getCoefficients(),new int[][]{{-32,1},{-21,1}});

        Polynomial p5=new Polynomial(new int[]{5,35,15});
        p5.divide(new Polynomial(new int[][]{{44,9},{68,9}}));
        assertArrayEquals(p5.quotient.getCoefficients(),new int[][]{{-10,1},{1,1}});
        assertArrayEquals(p5.remainder.getCoefficients(),new int[][]{{-32,1},{-21,1}});


    }


        @Test
    void testToString() {
        assertEquals(new Polynomial(new int[]{7,0,12,1,-2}).toString(),"7 +12x2 +x3 -2x4");
        assertEquals(new Polynomial(new int[]{7,0,12,1,-2}).toString(),"7 +12x2 +x3 -2x4");
        assertEquals(new Polynomial(new int[]{7,0,12,1,0}).toString(),"7 +12x2 +x3");
            assertEquals(new Polynomial(new int[]{7,0,12,1,0}).multiply(new Rational(1,2)) .toString(),"7/2 +6x2 +1/2x3");

        assertEquals(new Polynomial(new int[]{1}).toString(),"1");
        assertEquals(new Polynomial(new int[]{-1}).toString(),"-1");
        assertEquals(new Polynomial(new int[]{-5}).toString(),"-5");
        assertEquals(new Polynomial(new int[]{0,1}).toString(),"x");
        assertEquals(new Polynomial(new int[]{0,-1}).toString(),"-x");
        assertEquals(new Polynomial(new int[]{0,0,1}).toString(),"x2");
        assertEquals(new Polynomial(new int[]{0,0,-1}).toString(),"-x2");
    }


}