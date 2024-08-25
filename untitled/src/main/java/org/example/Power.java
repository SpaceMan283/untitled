

import java.util.*;

class Hlava{

    String nazov;
    int zaciatok;
    int trvanie;
    int sila;

    public Hlava(String nazov, int zaciatok, int trvanie, int sila){
        this.nazov = nazov;
        this.zaciatok = zaciatok;
        this.trvanie = trvanie;
        this.sila = sila;
    }

    public int Koniec(){
        return zaciatok + trvanie;
    }
}


public class Power {

    public static void main(String[] args) {
        List<Hlava> hlavy = Arrays.asList(
                new Hlava("absolvovanie VŠ", 19, 5, 1000),
                new Hlava("cesta okolo sveta", 24, 1, 40000),
                new Hlava("aučenie sa hrať na cimbal", 22, 2, 2000),
                new Hlava("založenie start-up", 20, 2, 6000)
        );

        int maxSila = ziskatMaxSilu(hlavy);
        System.out.println("Maximalna sila:" + maxSila);

    }

    private static int ziskatMaxSilu(List<Hlava> hlavy) {

        hlavy.sort(Comparator.comparingInt(p -> p.zaciatok));

        int n = hlavy.size();
        int[] dp = new int[n];
        int[] predchadajuci = new int[n];
        Arrays.fill(predchadajuci, -1);

        int aktualnaHlava = 0;

        dp[0] = hlavy.get(0).sila;

        for (int i = 1; i < n ; i++) {

            int silas = hlavy.get(i).sila;

            if (hlavy.get(aktualnaHlava).Koniec() <= hlavy.get(i).zaciatok) {
                silas += dp[aktualnaHlava];
                predchadajuci[i] = aktualnaHlava;
            }


            for (int j = aktualnaHlava; j < i; j++) {
                if (hlavy.get(aktualnaHlava).Koniec() <= hlavy.get(i).zaciatok && dp[j] > dp[aktualnaHlava]) {
                    aktualnaHlava = j;
                    silas = hlavy.get(i).sila;
                    predchadajuci[i] = j;
                }

            }

            if (silas > dp[i - 1]) {
                dp[i] = silas;
            } else {
                dp[i] = dp[i - 1];
            }

        }

            List<Hlava> pouziteHlavy = new ArrayList<>();
            int index = n - 1;
            while (index >= 0){
                if(index == 0 || dp[index] != dp[index - 1]){
                    pouziteHlavy.add(hlavy.get(index));
                    index = predchadajuci[index];
                } else {
                    index--;
                }
            }

            Collections.reverse(pouziteHlavy);
            System.out.println("Pouzi tieto hlavy :");
            for (Hlava hlav : pouziteHlavy) {
                System.out.println(hlav.nazov);
            }

            return dp[n - 1];


    }

}
