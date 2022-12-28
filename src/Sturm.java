import java.awt.*;
import java.util.Arrays;

public class Sturm {
    Polynomial p;
    Rational[] intervals;



    Polynomial[] pSturm;
    public Sturm(Polynomial p, Rational[] intervals){
        this.p=p;
        this.intervals=intervals;

    }
    public Sturm(Polynomial p, int[] intervals){
        this(p, Arrays.stream(intervals).mapToObj(it->new Rational(it)).toArray(Rational[]::new));
    }

    public void findSeries(){
        int dg=p.getDegree();
        pSturm=new Polynomial[dg];
        pSturm[0]=p;
        pSturm[1]=p.derivative();
        int pt=2;
        while(pt<p.getDegree()){
            pSturm[pt-2].divide(pSturm[pt-1]);
            pSturm[pt]=pSturm[pt-2].remainder.multiply(-1);
            pt++;
        }
        int q=0;
    }
    public String toString(){
        return _toString(true);

    }
    public int[] getSignMinusInf(){
        int[] res=new int[pSturm.length];
        for(int i=0;i<res.length;i++){
            Polynomial p=pSturm[i];
            Rational r= p.getCoefficientOfDegree(p.getDegree()-1);
            int deg=p.getDegree()-1;
            int ss= deg%2==0 ? 1: -1;
            long v= r.num * ss;
            res[i]= v>0 ? 1: -1;
        }
        return res;
    }
    public int[] getSignInf(){
        int[] res=new int[pSturm.length];
        for(int i=0;i<res.length;i++){
            Polynomial p=pSturm[i];
            Rational r= p.getCoefficientOfDegree(p.getDegree()-1);
            res[i]= r.num>0 ? 1: -1;
        }
        return res;
    }
    public int[] getSign(Rational r){
        int[] res=new int[pSturm.length];
        for(int i=0;i<res.length;i++){
            Polynomial p=pSturm[i];
            long num= p.evaluate(r).num;
            if(num>0){
                res[i]=1;
            }else if(num==0){
                res[i]=0;
            }else{
                res[i]=-1;
            }
        }
        return res;
    }
    private int signCount(int[] s){
        int signCount=0;
        boolean isFirstSignSet= false;
        boolean isPrevPlus= false;
        for(int i=0;i<s.length;i++){
            if(s[i]!=0){
                if(isFirstSignSet){
                    boolean isPlus=s[i]>0;
                    if(isPlus !=isPrevPlus){
                        signCount++;
                        isPrevPlus=isPlus;
                    }
                }else{
                    isPrevPlus = s[i]>0;
                    isFirstSignSet=true;
                }
            }
        }
        return signCount;
    }

    private String latexNL="\\\\\n\n";
    private String[] sDetail={"P(x)","P'(x)"};
    private String _toString(boolean withDolar){
        //String s="\n\\\\";

        StringBuilder sb=new StringBuilder();
        int[] sCount=new int[intervals.length+2];
        sb.append(sturmToString(withDolar));
        sb.append("Sign at x= $-\\infty$: ");
        int[] rMinus=getSignMinusInf();
        sCount[0]=signCount(rMinus);
        sb.append("   "+ signsToString(rMinus)+"\n"+latexNL);
        for(int i=0;i< intervals.length;i++){
            Rational rat= intervals[i];
            int[] r= getSign(rat);
            sCount[i+1]=signCount(r);
            sb.append("Sign at x= "+rat+" ");
            sb.append("   "+signsToString(r)+"\n"+latexNL);
        }
        int[] rPlus= getSignInf();
        sCount[sCount.length-1]=signCount(rPlus);
        sb.append("Sign at x= $\\infty$: ");
        sb.append("   "+signsToString(rPlus)+"\n"+latexNL);
        sb.append("between $-\\infty$ and "+intervals[0]+ signChangesToStatement(sCount[0]-sCount[1])+latexNL);
        for(int i=1;i< sCount.length-2;i++){
            sb.append("between " +intervals[i-1]+ " and "+intervals[i]+ signChangesToStatement(sCount[i]-sCount[i+1])+latexNL);

        }
        sb.append("between "+intervals[intervals.length-1] +" and $\\infty$ " + signChangesToStatement(sCount[sCount.length-2]-sCount[sCount.length-1])+latexNL);

        return sb.toString();
    }
    private String signChangesToStatement(int sc){
        if(sc==0) return " there are no real roots.\n";
        if(sc==1) return " there is 1 real root.\n";
        return " there are "+ sc +" real roots.\n";

    }
    private String sturmToString(boolean withDolar){
        StringBuilder sb=new StringBuilder();
        String dolar= (withDolar)?"$":"";
        sb.append(dolar+ "P(x)= "+p.toString()+dolar+"\n"+latexNL);
        for(int i=0;i<pSturm.length;i++){
            String sdet= (i<2) ? sDetail[i]+ "= " : "-Remainder(P_"+(i-2)+"(x), P_"+(i-1)+"(x))= ";
            sb.append(dolar+"P_"+i+"(x)= "+sdet +pSturm[i].toLatex() +dolar+"\n"+latexNL);
        }
        return sb.toString();
    }

    private String signsToString(int[] r){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<r.length;i++){
            String sign= r[i]==1 ? "+" : "-";
            sb.append(sign);
        }
        sb.append("  sign changes: "+ signCount(r));
        return  sb.toString();
    }








}
