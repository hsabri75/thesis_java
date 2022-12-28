import java.util.ArrayList;

public class CF {
    ArrayList<Long> element=new ArrayList<>();
    long num;
    long den;
    public CF(long num, long den){
        this.num=num;
        this.den=den;
        fillCF();
    }
    public CF(Rational r){
        this(r.num,r.den);
    }
    private void fillCF(){
        long n=num;
        long d=den;
        long quot= Math.floorDiv(n,d);
        long rem= n- d*quot;
        while(rem!=0){
            element.add(quot);
            n=d;
            d=rem;
            quot= Math.floorDiv(n,d);
            rem= n- d*quot;
        }
        element.add(quot);
        //System.out.println(element);

    }

    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("[");
        for(int i=0;i<element.size()-1;i++){
            sb.append(element.get(i)+", ");
        }
        sb.append(element.get(element.size()-1)+"]");
        return sb.toString();
    }
}
