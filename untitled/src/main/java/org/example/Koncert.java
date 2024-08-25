

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Stadiony{

    String nazov;
    int zaciatok;
    int koniec;
    int kapacita;

    public Stadiony(String nazov, int zaciatok, int koniec, int kapacita){
        this.nazov = nazov;
        this.zaciatok = zaciatok;
        this.koniec = koniec;
        this.kapacita = kapacita;
    }

    @Override
    public String toString() {
        return "Stadion{" +
                "nazov=" + nazov +
                " kapacita=" + kapacita;
    }
}


public class Koncert {

    public static void main(String[] args) {
        List<Stadiony> stadionies = Arrays.asList(
                new Stadiony("Mecedes Benz Berlin", 69, 69, 5000),
                new Stadiony("Krakow zaporr", 80, 85, 30000),
                new Stadiony("Novosad karkarpor", 84, 87, 15000),
                new Stadiony("Kerestur", 86, 88, 20000)
        );


        int maxPocet = najlepsiPocetKapacity(stadionies);
        List<Stadiony> stadionies1 = vybrateStadiony(stadionies);
        System.out.println("pocet : " + maxPocet + " list : " + stadionies1);
    }

    private static int najlepsiPocetKapacity(List<Stadiony> stadionies) {
        stadionies.sort(Comparator.comparingInt(p -> p.koniec));

        int n = stadionies.size();
        int[] dp = new int[n];
        int[] predchodca = new int[n];

        Arrays.fill(predchodca, -1);

        dp[0] = stadionies.get(0).kapacita;

        for (int i = 1; i < n; i++) {

            int aktualnaKapacita = stadionies.get(i).kapacita;

            for (int j = i - 1; j >=0 ; j--) {
                if(stadionies.get(j).koniec < stadionies.get(i).zaciatok){
                    aktualnaKapacita += dp[j];
                    break;
                }

            }

            if(aktualnaKapacita > dp[i -1]){
                dp[i] = aktualnaKapacita;
            } else {
                dp[i] = dp[i - 1];
            }

        }


        return dp[n - 1];
    }

    private static List<Stadiony> vybrateStadiony(List<Stadiony> stadionies) {
        List<Stadiony> stadionyList = new ArrayList<>();
        return stadionies;
    }
}
