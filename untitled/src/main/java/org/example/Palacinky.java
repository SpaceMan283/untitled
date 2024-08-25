

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Cesto{

    int hrubka;
    int cena;

    public Cesto(int hrubka, int cena){
        this.cena = cena;
        this.hrubka = hrubka;
    }
}
public class Palacinky {

    public static void main(String[] args) {
        List<Cesto> cesta = Arrays.asList(
                new Cesto(6, 130),
                new Cesto(3, 40),
                new Cesto(2, 20),
                new Cesto(5, 120),
                new Cesto(4, 60)
        );

        int zvolenaHrubka = 8;

        int najHrubka = najdiNajKombinaciu(zvolenaHrubka, cesta);
        List<Cesto> zvoleneCesta = najdiZvoleneKombo(zvolenaHrubka, cesta);
        System.out.println("Naj cesta su :" + zvoleneCesta + "s pozadovanou hrubkou : " + najHrubka);
    }

    private static int najdiNajKombinaciu(int zvolenaHrubka, List<Cesto> cesta) {

        int[] dp = new int[zvolenaHrubka + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 0; i < cesta.size(); i++) {

            Cesto cesto = cesta.get(i);

            for (int j = zvolenaHrubka; j >= cesto.hrubka ; j--) {
                if(dp[j - cesto.hrubka] != Integer.MAX_VALUE && dp[j] > (dp[j - cesto.hrubka] + cesto.cena)){
                    dp[j] = (dp[j - cesto.hrubka] + cesto.cena);
                }
            }

        }

        return dp[zvolenaHrubka];


    }

    private static List<Cesto> najdiZvoleneKombo(int zvolenaHrubka, List<Cesto> cesta) {
        List<Cesto> zvoleneCesta = new ArrayList<>();

        int[] dp = new int[zvolenaHrubka + 1];
        int[] vybrate = new int[zvolenaHrubka + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 0; i < cesta.size(); i++) {

            Cesto cesto = cesta.get(i);

            for (int j = zvolenaHrubka; j >= cesto.hrubka ; j--) {
                if(dp[j - cesto.hrubka] != Integer.MAX_VALUE && dp[j] > (dp[j - cesto.hrubka] + cesto.cena)){
                    dp[j] = (dp[j - cesto.hrubka] + cesto.cena);
                    vybrate[j] = i;
                }
            }

        }


        int cm = zvolenaHrubka;
        while (cm > 0){
            int idx = vybrate[cm];
            zvoleneCesta.add(cesta.get(idx));
            cm -= cesta.get(idx).hrubka;
        }
        return zvoleneCesta;
    }

}
