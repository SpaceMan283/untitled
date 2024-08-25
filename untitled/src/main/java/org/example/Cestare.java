


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Cesta{

    int dlzka;
    int cena;

    @Override
    public String toString() {
        return "Cesta{" +
                "dlzka=" + dlzka +
                ", cena=" + cena +
                '}';
    }

    public Cesta(int dlzka, int cena){
        this.dlzka = dlzka;
        this.cena = cena;
    }

}
public class Cestare {

    public static void main(String[] args) {
        List<Cesta> cesty = Arrays.asList(
                new Cesta(4, 700),
                new Cesta(3, 500),
                new Cesta(5, 1200),
                new Cesta(2, 300)
        );

        int pozdovaneKm = 7;

        int cena = najdiSumuPresne(pozdovaneKm, cesty);
        List<Cesta> vybraneCesty = vybraneSpravneCesty(pozdovaneKm,cesty);
        System.out.println(cena + "Vyrane cesty : " + vybraneCesty);
    }


    private static int najdiSumuPresne(int pozdovaneKm, List<Cesta> cesty) {

        List<Cesta> zvoleneCesty = new ArrayList<>();
        int[] dp = new int[pozdovaneKm + 1];
        int[] pouzite = new int[pozdovaneKm + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < cesty.size(); i++) {
            Cesta cesta = cesty.get(i);

            for (int j = pozdovaneKm; j >= cesta.dlzka ; j--) {
                if(dp[j - cesta.dlzka] != Integer.MAX_VALUE && dp[j] > (dp[j - cesta.dlzka] + cesta.cena)){
                    dp[j] = (dp[j - cesta.dlzka] + cesta.cena);
                    pouzite[j] = i;

                }
            }
        }

        return dp[pozdovaneKm];


    }

    private static List<Cesta> vybraneSpravneCesty(int pozdovaneKm, List<Cesta> cesty) {

        List<Cesta> zvoleneCesty = new ArrayList<>();
        int[] dp = new int[pozdovaneKm + 1];
        int[] pouzite = new int[pozdovaneKm + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < cesty.size(); i++) {
            Cesta cesta = cesty.get(i);

            for (int j = pozdovaneKm; j >= cesta.dlzka ; j--) {
                if(dp[j - cesta.dlzka] != Integer.MAX_VALUE && dp[j] > (dp[j - cesta.dlzka] + cesta.cena)){
                    dp[j] = (dp[j - cesta.dlzka] + cesta.cena);
                    pouzite[j] = i;

                }
            }
        }


        int km = pozdovaneKm;
        while (km > 0){
            zvoleneCesty.add(cesty.get(pouzite[km]));
            km -= cesty.get(pouzite[km]).dlzka;
        }


        return zvoleneCesty;
    }


}
