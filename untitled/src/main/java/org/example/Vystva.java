


import java.util.*;

class Miesto{

    String nazov;
    int zaciatok;
    int koniec;
    int pocetNavstevnikov;


    public Miesto(String nazov, int zaciatok, int koniec, int pocetNavstevnikov){
        this.nazov = nazov;
        this.zaciatok = zaciatok;
        this.koniec = koniec;
        this.pocetNavstevnikov = pocetNavstevnikov;
    }
}
public class Vystva {

    public static void main(String[] args) {
        List<Miesto> miesta = Arrays.asList(
                new Miesto("Filmový festival v Cannes", 100, 107, 60000),
                new Miesto("Festival vedy a techniky", 101, 105, 30000),
                new Miesto("Sabinovský jarmok", 106, 108, 40000),
                new Miesto("Deň otvorených dverí UPJŠ", 45, 45, 1000)
        );

        int maxPocet = maxPocetOslovenych(miesta);
        System.out.println("Maximalny pocet je : " + maxPocet);

    }

    private static int maxPocetOslovenych(List<Miesto> miesta) {

        miesta.sort(Comparator.comparingInt(p -> p.koniec));

        int n = miesta.size();
        int[] dp = new int[n];
        int[] predchadajuci = new int[n];
        Arrays.fill(predchadajuci, -1);

        dp[0] = miesta.get(0).pocetNavstevnikov;


        for (int i = 1; i < n; i++) {

            int pocet = miesta.get(i).pocetNavstevnikov;

            for (int j = i - 1; j >= 0; j--) {
                if(miesta.get(j).koniec < miesta.get(i).zaciatok){
                    pocet += dp[j];
                    predchadajuci[i] = j;
                    break;
                }
            }

            if(pocet > dp[i - 1]){
                dp[i] = pocet;
            } else {
                dp[i] = dp[i -1];
            }

        }


        List<Miesto> predchadajuco = new ArrayList<>();
        int index = n - 1;
        while (index >= 0){
            if(index == 0 || dp[index] != dp[index-1]){
                predchadajuco.add(miesta.get(index));
                index = predchadajuci[index];
            } else {
                index--;
            }
        }

        // Výpis odporúčaných podujatí na účasť
        Collections.reverse(predchadajuco);
        System.out.println("Odporúčané podujatia na účasť:");
        for (Miesto podujatie : predchadajuco) {
            System.out.println("Podujatie: " + podujatie.nazov + ", začiatok: " + podujatie.zaciatok + ", koniec: " + podujatie.koniec + ", návštevníci: " + podujatie.pocetNavstevnikov);
        }

        return dp[n - 1];
    }
}
