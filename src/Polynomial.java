import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polynomial {

    public static final String var="x";
    //int[] coefficients;
    Rational[] coefficients;
    private long[][] intCoef;

    public Polynomial quotient=null;
    public Polynomial remainder=null;

    public List<Polynomial> divisionDetails;

    public Polynomial(int[] coefficients){
        Rational[] r= new Rational[coefficients.length];
        for(int i=0;i<coefficients.length;i++){
            r[i]=new Rational(coefficients[i],1);
        }
        this.setPolynomial(r);
    }
    public int getDegree(){
        return coefficients.length;
    }
    public Polynomial(int[][] coefficients){
        Rational[] r= new Rational[coefficients.length];
        for(int i=0;i<coefficients.length;i++){
            r[i]=new Rational(coefficients[i][0],coefficients[i][1]);
        }
        this.setPolynomial(r);
    }

    public Polynomial(Rational[] coefficients){
        this.setPolynomial(coefficients);
    }
    private void setPolynomial(Rational[] coefficients){
        if(coefficients[coefficients.length-1].num==0){
            this.coefficients=filterLeadingZeroes(coefficients);
        }else{
            this.coefficients=coefficients;
        }
    }

    private static Rational[] filterLeadingZeroes(Rational[] cf){
        int pt=cf.length-1;
        while(pt>0 && cf[pt].num==0){
            pt--;
        }
        if (pt<0) return new Rational[]{new Rational(0)};
        Rational[] filt=new Rational[pt+1];
        for(int i=0;i<filt.length;i++){
            filt[i]= cf[i];
        }
        return filt;
    }
    public long[][] getCoefficients(){
        if(intCoef==null){
            intCoef=new long[coefficients.length][];
        }
        for(int i=0;i<coefficients.length;i++){
            intCoef[i]=new long[]{coefficients[i].num, coefficients[i].den};
        }
        return intCoef;
    }
    public Rational getLeading(){
        return coefficients[coefficients.length-1];
    }


    public Polynomial derivative(){
        Rational[] nc=new Rational[coefficients.length-1];
        for(int i=1;i<coefficients.length;i++){
            nc[i-1]=coefficients[i].multiply(i);
        }
        return new Polynomial(nc);
    }
    public void divide(Polynomial divisor){
        divisionDetails= new ArrayList<>();
        if(this.coefficients.length<=divisor.coefficients.length){
            throw new RuntimeException();
        }
        Polynomial pQuotient= new Polynomial(new int[]{0});
        Polynomial pDividend=this;
        int deg= pDividend.coefficients.length-divisor.coefficients.length+1;
        //sb.append("-------------");

        //sb.append(divisor+" | "+ this);
        while(deg>0){
            Rational[] cf=new Rational[deg];
            Rational rd= pDividend.getLeading().divide(divisor.getLeading());
            for(int i=0;i<deg-1;i++){
                cf[i]=new Rational(0);
            }
            cf[deg-1]=rd;
            Polynomial pTerms=new Polynomial(cf);
            pQuotient=pQuotient.add(pTerms);
            Polynomial pSil=divisor.multiply(pTerms);
            Polynomial px= pDividend.subtract(pSil);
            divisionDetails.add(pSil);
            divisionDetails.add(px);
            pDividend=px;
            deg= pDividend.coefficients.length-divisor.coefficients.length+1;
        }
        int d;
        this.remainder=pDividend;
        this.quotient=pQuotient;
    }



    public boolean equals(Polynomial p2) {
        if(p2.coefficients.length!=this.coefficients.length) return false;
        for(int i=0;i<this.coefficients.length;i++){
            System.out.println(this.toString());
            System.out.println("comparing"+ this.coefficients[i]+ " "+p2.coefficients[i]);
            if (this.coefficients[i] != p2.coefficients[i]) return false;
        }
        return true;
    }
    @Override
    public boolean equals(Object obj) {
        Polynomial p2=(Polynomial) obj;
        return equals(p2);
    }
    public Polynomial subtract(Polynomial p2){
        return addSubtract(p2,-1);
    }
    private Polynomial addSubtract(Polynomial p2, int sign){
        int maxDegree= Math.max(this.coefficients.length, p2.coefficients.length);
        Rational[] ca= new Rational[maxDegree];
        for(int i=0;i<maxDegree;i++){
            ca[i] = this.getCoefficientOfDegree(i).add(p2.getCoefficientOfDegree(i).multiply(sign));//   +sign *p2.getCoefficientOfDegree(i);
        }
        Polynomial pp=new Polynomial(ca);
        return pp;
    }
    public Polynomial add(Polynomial p2){
        return addSubtract(p2,1);
    }

    public Rational getCoefficientOfDegree(int degree){
        if(degree>=0 && degree<coefficients.length){
            return coefficients[degree];
        }
        return new Rational(0,1);
    }

    public Polynomial multiply(Rational r){
        Rational[] cp=new Rational[this.coefficients.length];
        for(int i=0;i<this.coefficients.length;i++){
            cp[i]=coefficients[i].multiply(r);
        }
        return new Polynomial(cp);
    }
    public Polynomial multiply(int s){
        Rational[] cp=new Rational[this.coefficients.length];
        for(int i=0;i<this.coefficients.length;i++){
            cp[i]=coefficients[i].multiply(s);
        }
        return new Polynomial(cp);
    }
    public Polynomial multiply(Polynomial p2){
        Rational[] cp=new Rational[this.coefficients.length + p2.coefficients.length-1];
        for(int i=0;i<cp.length;i++){
            cp[i]=new Rational(0,1);
        }
        for(int i=0;i<this.coefficients.length;i++){
            for(int j=0;j<p2.coefficients.length;j++){
                int mp=i+j;
                cp[mp]=cp[mp].add(this.coefficients[i].multiply(p2.coefficients[j]));
            }
        }
        return new Polynomial(cp);
    }

    public Rational evaluate(Rational r){
        int deg=coefficients.length-1;
        Rational tot=new Rational(0);
        while(deg>0){
            tot=(tot.add(coefficients[deg])).multiply(r);
            deg--;

        }
        tot=tot.add(coefficients[0]);// +coefficients[0];
        return tot;
    }

    public Rational evaluate(int x){
            return evaluate(new Rational(x,1));
    }

    public String toLatex() {
        StringBuilder sb = new StringBuilder();
        boolean isFirstTerm=true;
        for (int i = this.getDegree()-1; i >=0; i--) {
            if(coefficients[i].num!=0){
                sb.append(termToString(coefficients[i],i,isFirstTerm,true));
                isFirstTerm=false;
            }
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean isFirstTerm=true;
        for (int i = this.getDegree()-1; i >=0; i--) {
            if(coefficients[i].num!=0){
                sb.append(termToString(coefficients[i],i,isFirstTerm,false));
                isFirstTerm=false;
            }
        }
        return sb.toString();
    }

    private String termToString(Rational coefficient, int degree,boolean isFirstTerm, boolean latex){
        if(coefficient.num==0) return "";
        String blanksBetweenTerms= isFirstTerm ? "" : " " ;
        String sign= coefficient.num<0 ? "-" : (isFirstTerm ? "" :"+") ;
        long absCoefficient= Math.abs(coefficient.num);
        Rational rDummy=new Rational(absCoefficient,coefficient.den);

        String ratPart= (coefficient.den!=1 && latex) ? ("\\frac{"+ absCoefficient +"}{"+coefficient.den +"}") : rDummy.toString() ;
        String sCoefficient= (absCoefficient==1 && coefficient.den==1 && degree!=0) ? "": ratPart;
        String xPart= (degree==0) ? "" : var;
        String caret= (latex) ? "^" : "";
        String degreePart= (degree<=1) ? "" : caret+ degree+"";
        return blanksBetweenTerms+ sign+ sCoefficient+ xPart+ degreePart;
    }
    public static Polynomial getPolynomialWithRoots(int[] roots){
        Polynomial p= new Polynomial(new int[] {-roots[0],1});
        for(int i=1;i<roots.length;i++){
            p= p.multiply(new Polynomial(new int[]{-roots[i],1}));
        }
        return p;
    }

}
