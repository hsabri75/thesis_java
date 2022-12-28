import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Thesis {


    public static void CFForThesis(){
        CF c1= new CF(7,3);
        CF c2= new CF(2,11);
        CFOperation op1= new CFOperation(c1,c2, CFOperation.opType.addition);
        op1.calculate();


    }
    public static void SturmForThesis(){
        Polynomial p=new Polynomial(new int[]{2,-10,-20,0,5,1});
        int[] intervals=new int[]{-1,0,1};
        Sturm st=new Sturm(p,intervals);
        st.findSeries();
        System.out.println(st);/*
        Rational r= new Rational(-3,2);
        System.out.println(r+ ": "+ p.evaluate(r));
        for(int i=0;i<intervals.length;i++){
            int v= intervals[i];
            System.out.println(v+ ": "+ p.evaluate(v));
        }*/
    }
    public static void PolynomialLongDivision(){
        Polynomial dividend= new Polynomial(new int[]{-3,4,-1,1});
        Polynomial divisor= new Polynomial(new int[]{-1,0,1});
        dividend.divide(divisor);
        System.out.println("\\begin{aligned}");
        String latexTab="";
        String latexNL="\n\\\\";
        System.out.println(latexTab+"&\\underline"+ dividend.quotient.toLatex() +""+latexNL);
        System.out.println(""+divisor.toLatex()+"    &"+dividend.toLatex()+""+latexNL);
        for(int i=0;i<dividend.divisionDetails.size();i++){
            String und= (i%2==0) ?"\\underline" :"";
            System.out.println(latexTab+"&"+und+dividend.divisionDetails.get(i).toLatex()+""+latexNL);
        }
        System.out.println("\\end{aligned}");
        //System.out.println(dividend.remainder);

    }
    public static void toImage(){
        try {
            int width = 200, height = 200;

            // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
            // into integer pixels
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);


            Graphics2D ig2 = bi.createGraphics();
            ig2.setPaint ( new Color ( 255,255,255 ) );
            ig2.fillRect ( 0, 0, bi.getWidth(), bi.getHeight() );

            Font font = new Font("TimesRoman", Font.BOLD, 20);
            ig2.setFont(font);
            String message = "www.java2s.com!";
            FontMetrics fontMetrics = ig2.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(message);
            int stringHeight = fontMetrics.getAscent();
            ig2.setPaint(Color.black);
            ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);
            ig2.drawLine(10,10,30,30);

            ImageIO.write(bi, "PNG", new File("yourImageName.PNG"));
            //ImageIO.write(bi, "JPEG", new File("c:\\yourImageName.JPG"));
            //ImageIO.write(bi, "gif", new File("c:\\yourImageName.GIF"));
            //ImageIO.write(bi, "BMP", new File("c:\\yourImageName.BMP"));

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }


}
