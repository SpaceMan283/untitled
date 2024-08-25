

import java.util.*;

class Podujatie {
    String nazov;
    int zaciatok;
    int koniec;
    int navstevnici;

    public Podujatie(String nazov, int zaciatok, int koniec, int navstevnici) {
        this.nazov = nazov;
        this.zaciatok = zaciatok;
        this.koniec = koniec;
        this.navstevnici = navstevnici;
    }
}

public class PutovnaVystava {

    public static void main(String[] args) {
        List<Podujatie> podujatia = Arrays.asList(
                new Podujatie("Filmový festival v Cannes", 100, 107, 60000),
                new Podujatie("Festival vedy a techniky", 101, 105, 30000),
                new Podujatie("Sabinovský jarmok", 106, 108, 40000),
                new Podujatie("Deň otvorených dverí UPJŠ", 45, 45, 1000)
        );

        int maxNavstevnici = najdiMaximalnyNavstevnikov(podujatia);
        System.out.println("Maximálny počet návštevníkov: " + maxNavstevnici);
    }

    public static int najdiMaximalnyNavstevnikov(List<Podujatie> podujatia) {
        // Usporiadame podujatia podľa konca
        podujatia.sort(Comparator.comparingInt(p -> p.koniec));

        int n = podujatia.size();
        int[] dp = new int[n];
        int[] predchodca = new int[n];
        Arrays.fill(predchodca, -1);

        dp[0] = podujatia.get(0).navstevnici;

        for (int i = 1; i < n; i++) {
            int zisk = podujatia.get(i).navstevnici;
            int predchadzajuci = -1;

            for (int j = i - 1; j >= 0; j--) {
                if (podujatia.get(j).koniec < podujatia.get(i).zaciatok) {
                    zisk += dp[j];
                    predchadzajuci = j;
                    break;
                }
            }

            if (zisk > dp[i - 1]) {
                dp[i] = zisk;
                predchodca[i] = predchadzajuci;
            } else {
                dp[i] = dp[i - 1];
            }
        }

        // Rekonštrukcia zoznamu podujatí
        List<Podujatie> realizovanePodujatia = new ArrayList<>();
        int index = n - 1;
        while (index >= 0) {
            if (index == 0 || dp[index] != dp[index - 1]) {
                realizovanePodujatia.add(podujatia.get(index));
                index = predchodca[index];
            } else {
                index--;
            }
        }

        // Výpis odporúčaných podujatí na účasť
        Collections.reverse(realizovanePodujatia);
        System.out.println("Odporúčané podujatia na účasť:");
        for (Podujatie podujatie : realizovanePodujatia) {
            System.out.println("Podujatie: " + podujatie.nazov + ", začiatok: " + podujatie.zaciatok + ", koniec: " + podujatie.koniec + ", návštevníci: " + podujatie.navstevnici);
        }

        return dp[n - 1];
    }
}
