package org.loy.test2;

public class HashCode {

    public short a;

    public int b;

    public long c;

    public float d;

    public double e;

    public boolean f;

    public String g;


    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + a;
        result = prime * result + b;
        result = prime * result + (int) (c ^ (c >>> 32));
        result = prime * result + Float.floatToIntBits(d);
        long temp;
        temp = Double.doubleToLongBits(e);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (f ? 1231 : 1237);
        result = prime * result + ((g == null) ? 0 : g.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HashCode other = (HashCode) obj;
        if (a != other.a)
            return false;
        if (b != other.b)
            return false;
        if (c != other.c)
            return false;
        if (Float.floatToIntBits(d) != Float.floatToIntBits(other.d))
            return false;
        if (Double.doubleToLongBits(e) != Double.doubleToLongBits(other.e))
            return false;
        if (f != other.f)
            return false;
        if (g == null) {
            if (other.g != null)
                return false;
        } else if (!g.equals(other.g))
            return false;
        return true;
    }

}
