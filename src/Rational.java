public class Rational {

    long num;
    long den;
    public Rational(int num, int den){
        if(den==0) throw new RuntimeException();
        setNumDen((long)num,(long)den);
    }
    public Rational(int num){        this((long)num,1);    }
    public Rational(long num){
        this(num,1);
    }
    public Rational(long num, long den){
        if(den==0) throw new RuntimeException("Zero denominator");
        setNumDen(num,den);
    }
    private void setNumDen(long n, long d){
        if(n==0){
            this.num=0;
            this.den=1;
            return;
        }
        int sn= n>0 ? 1:-1;
        int sd= d>0 ? 1:-1;
        int sign = (sn*sd)>0 ? 1: -1;
        long nm=Math.abs(n);
        long dn=Math.abs(d);
        long gcd=gcd(nm,dn);
        this.num=sign * nm/gcd;
        this.den=dn/gcd;
    }

    public Rational add(Rational r2){
        return new Rational(num * r2.den + r2.num * den,  den* r2.den);
    }

    public Rational subtract(Rational r2){
        return new Rational(num * r2.den - r2.num * den,  den* r2.den);
    }

    public Rational multiply(Rational r2){
        return new Rational(num *  r2.num ,  den* r2.den);
    }

    public Rational divide(Rational r2){
        long numResult= num *  r2.den;
        long denResult= den* r2.num;
        if(numResult==0) return new Rational(0);
        int sign = (numResult*denResult>0) ? 1:-1;
        numResult =sign*Math.abs(numResult);
        denResult=Math.abs(denResult);
        return new Rational(numResult ,  denResult);
    }
    public Rational absolute(){
        return new Rational(Math.abs(this.num), this.den);
    }

    public Rational multiply(int n){
        return new Rational(num *  n ,  den);
    }

    @Override
    public boolean equals(Object obj) {
        Rational r2=(Rational) obj;
        boolean res=num==r2.num && den==r2.den;
        return res;
    }

    public static long gcd(long n1, long n2){
        if(n2==0) throw new RuntimeException();
        //if(n1==n2) return n1;
        if(n1==1) return 1;
        //if(n2==1) return n2;
        long num1= Math.max(n1,n2);
        long num2=Math.min(n1,n2);
        //int div= num1/num2;
        long rem= num1%num2;
        do{
            rem= num1%num2;
            num1=num2;
            num2=rem;
        }while(rem>0);
        return num1;
    }
    public String toString(){
        if(den==1){
            return num+"";
        }else{
            return num+"/"+den;
        }

    }



}
