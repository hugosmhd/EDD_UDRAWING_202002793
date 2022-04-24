package funciones;

public class DispersionHash {
    static final double R = 0.618034;

    public static long transformaClave(String clave) {
        long d;
        d = 0;
        for (int j = 0; j < Math.min(clave.length(), 10); j++) {
            d = d * 27 + (int)clave.charAt(j);
        }

        if (d < 0) d = -d;
        return d;
    }

    public static int dispersion(long x, int M) {
        double t;
        int v;
        t = R*x-Math.floor(R*x);
        v=(int)(M*t);
        return v;
    }
}
