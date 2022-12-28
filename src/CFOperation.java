import java.util.ArrayList;

public class CFOperation {
    long a = 0;
    long ax = 0;
    long ay = 0;
    long axy = 0;
    long b = 0;
    long bx = 0;
    long by = 0;
    long bxy = 0;
    CF X;
    CF Y;
    //boolean isFinished=false;
    ArrayList<Long> terms = new ArrayList<>();
    boolean inputFinished = false;
    private int pX;
    private int pY;

    public CFOperation(CF X, CF Y, opType type) {
        this.X = X;
        this.Y = Y;
        if (type == opType.addition) {
            ax = 1;
            ay = 1;
            b = 1;
        } else if (type == opType.subtraction) {
            ax = 1;
            ay = -1;
            b = 1;
        } else if (type == opType.multiplication) {
            axy = 1;
            b = 1;
        } else {
            ax = 1;
            by = 1;
        }
    }

    public void calculate() {
        System.out.println("$x = \\frac{" + X.num + "}{" + X.den + "} = " +X.toString() + " $\\\\");
        System.out.println();
        System.out.println("$y = \\frac{" + Y.num + "}{" + Y.den + "} = " +Y.toString() + " $\\\\");
        System.out.println();
        System.out.println("$z = x + y $\\\\");
        System.out.println();
        System.out.println("Matrix representation of addition");
        System.out.println();
        System.out.println("$$");
        System.out.println("\\begin{bmatrix}");
        System.out.println("0 & 1 & 1 & 0\\\\");
        System.out.println("1 & 0 & 0 & 0");
        System.out.println("\\end{bmatrix}");
        System.out.println("$$");
        System.out.println();


        while (!(b == 0 & bx == 0 & by == 0 & bxy == 0)) {
            nextStep();
        }
        Rational rx = new Rational(X.num,X.den);
        Rational ry = new Rational(Y.num,Y.den);
        Rational rz= rx.add(ry);
        System.out.println("So continued fraction representation of z is {\\color{red}"+terms+"}, which is equal to $\\frac{"+rz.num+"}{"+rz.den+"}$");
        //System.out.println(this.terms);
    }

    private void nextStep() {
        long e = checkZTerm();
        if (e != -1) {
            System.out.println("$r = \\lfloor\\cfrac{"+a+"}{"+b+"}\\rfloor = \\lfloor\\cfrac{"+ax+"}{"+bx+"}\\rfloor =\\lfloor\\cfrac{"+ay+"}{"+by+"}\\rfloor = \\lfloor\\cfrac{"+axy+"}{"+bxy+"}\\rfloor = "+e+"   \\Rightarrow $");
            egest(e);
            return;
        } else {
            boolean chooseX = false;
            if (b != 0 && bx != 0 && by != 0) {
                Rational r0 = new Rational(a, b);
                Rational rx = new Rational(ax, bx);
                Rational ry = new Rational(ay, by);
                Rational sx = rx.subtract(r0).absolute();
                Rational sy = ry.subtract(r0).absolute();
                Rational dif = sx.subtract(sy);
                chooseX = dif.num > 0;
            } else {
                chooseX = (b == 0 & bx != 0 & by == 0) | (b != 0 & bx == 0 & by != 0);
            }
            if (chooseX) {
                System.out.println("$|\\frac{"+a+"}{"+b+"} - \\frac{"+ax+"}{"+bx+"}| > |\\frac{"+a+"}{"+b+"} - \\frac{"+ay+"}{"+by+"}|  \\Rightarrow $");
                ingestX();
                pX++;
            } else {
                System.out.println("$|\\frac{"+a+"}{"+b+"} - \\frac{"+ax+"}{"+bx+"}| \\ngtr |\\frac{"+a+"}{"+b+"} - \\frac{"+ay+"}{"+by+"}|  \\Rightarrow $");

                ingestY();
                pY++;
            }
        }

    }

    private long checkZTerm() {
        if (inputFinished) return a / b;
        if (b == 0 || bx == 0 || by == 0 || bxy == 0) {
            return -1;
        }
        long f = a / b;
        long fx = ax / bx;
        long fy = ay / by;
        long fxy = axy / bxy;
        if (f == fx && f == fy && f == fxy) {
            return f;
        }
        return -1;
    }

    private void ingestX() {
        if (pX >= X.element.size()) {
            a = ax;
            b = bx;
            ay = axy;
            by = bxy;
            System.out.println("Substitute $\\infty$ for x\n\\\\\n" + getOp());
        } else {
            long p = X.element.get(pX);

            long tempa = a;
            a = ax;
            ax = tempa + p * ax;
            long tempay = ay;
            ay = axy;
            axy = tempay + p * axy;

            long tempb = b;
            b = bx;
            bx = tempb + p * bx;
            long tempby = by;
            by = bxy;
            bxy = tempby + p * bxy;
            System.out.println("Substitute $" + p + "+\\frac{1}{x}$ for x\n\\\\\n" + getOp());
        }

    }

    private void ingestY() {
        if (pY >= Y.element.size()) {
            a = ay;
            b = by;
            ax = axy;
            bx = bxy;
            System.out.println("Substitute $\\infty$ for y\n\\\\\n" + getOp());
        } else {
            long q = Y.element.get(pY);
            long tempa = a;
            a = ay;
            ay = tempa + q * ay;
            long tempax = ax;
            ax = axy;
            axy = tempax + q * axy;

            long tempb = b;
            b = by;
            by = tempb + q * by;
            long tempbx = bx;
            bx = bxy;
            bxy = tempbx + q * bxy;
            System.out.println("Substitute $" + q + "+\\frac{1}{y}$ for y\n\\\\\n" + getOp());
        }
    }

    private void egest(long r) {
        terms.add(r);
        long temp = a;
        a = b;
        b = temp - r * b;

        temp = ax;
        ax = bx;
        bx = temp - r * bx;

        temp = ay;
        ay = by;
        by = temp - r * by;

        temp = axy;
        axy = bxy;
        bxy = temp - r * bxy;
        System.out.println("Extract {\\color{red}" + r + "} and substitute $\\frac{1}{z-"+r+"}$\n\\\\\n" + getOp());


    }

    public String getOp_() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + a + " " + ax + " " + ay + " " + axy + "\\");
        sb.append(b + " " + bx + " " + by + " " + bxy + "]");
        return sb.toString();
    }

    public String getOp() {
        StringBuilder sb = new StringBuilder();
        sb.append("$$\n");
        sb.append("\\begin{bmatrix}\n");
        sb.append(a + " & " + ax + " & " + ay + " & " + axy + "\\\\ \n");
        sb.append(b + " & " + bx + " & " + by + " & " + bxy + " \n");
        sb.append("\\end{bmatrix}\n");
        sb.append("$$\n");
        return sb.toString();
    }
    /*
    $$
\begin{bmatrix}
0 & 1 & 1 & 0\\
1 & 0 & 0 & 0
\end{bmatrix}
$$
     */


    public enum opType {
        addition,
        subtraction,
        multiplication,
        division

    }

}
