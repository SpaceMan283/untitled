

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Cesta {
    int dlzka;
    int cena;

    public Cesta(int dlzka, int cena) {
        this.dlzka = dlzka;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Cesta{" +
                "dlzka=" + dlzka +
                " km, cena=" + cena +
                " eur}";
    }
}

public class Obchadzka {
    public static void main(String[] args) {
        // Príklad ciest (dlzka v km, cena v eurách)
        List<Cesta> cesty = Arrays.asList(
                new Cesta(6, 130),
                new Cesta(3, 40),
                new Cesta(2, 20),
                new Cesta(5, 120),
                new Cesta(4, 60)
        );

        int X = 9; // Požadovaný počet kilometrov
        System.out.println("Minimálna cena za presne " + X + " km: " + minimalnaCenaPresne(cesty, X));
        List<Cesta> zvoleneCestyPresne = ziskajZvoleneCestyPresne(cesty, X);
        System.out.println("Zvolené cesty pre presne " + X + " km: " + zvoleneCestyPresne);

        System.out.println("Minimálna cena za aspoň " + X + " km: " + minimalnaCenaAspon(cesty, X));
        List<Cesta> zvoleneCestyAspon = ziskajZvoleneCestyAspon(cesty, X);
        System.out.println("Zvolené cesty pre aspoň " + X + " km: " + zvoleneCestyAspon);
    }

    public static int minimalnaCenaPresne(List<Cesta> cesty, int X) {
        int[] dp = new int[X + 1];
        int[] cestaIndex = new int[X + 1]; // Index ciest pre sledovanie
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < cesty.size(); i++) {
            Cesta cesta = cesty.get(i);
            for (int j = X; j >= cesta.dlzka; j--) {
                if (dp[j - cesta.dlzka] != Integer.MAX_VALUE && dp[j] > dp[j - cesta.dlzka] + cesta.cena) {
                    dp[j] = dp[j - cesta.dlzka] + cesta.cena;
                    cestaIndex[j] = i; // Uložíme index cesty
                }
            }
        }

        return dp[X] == Integer.MAX_VALUE ? -1 : dp[X];
    }

    public static List<Cesta> ziskajZvoleneCestyPresne(List<Cesta> cesty, int X) {
        List<Cesta> zvoleneCesty = new ArrayList<>();
        int[] dp = new int[X + 1];
        int[] cestaIndex = new int[X + 1]; // Index ciest pre sledovanie
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < cesty.size(); i++) {
            Cesta cesta = cesty.get(i);
            for (int j = X; j >= cesta.dlzka; j--) {
                if (dp[j - cesta.dlzka] != Integer.MAX_VALUE && dp[j] > dp[j - cesta.dlzka] + cesta.cena) {
                    dp[j] = dp[j - cesta.dlzka] + cesta.cena;
                    cestaIndex[j] = i; // Uložíme index cesty
                }
            }
        }

        if (dp[X] == Integer.MAX_VALUE) return zvoleneCesty; // Vráti prázdny zoznam ak nie je riešenie

        int km = X;
        while (km > 0) {
            int idx = cestaIndex[km];
            zvoleneCesty.add(cesty.get(idx));
            km -= cesty.get(idx).dlzka;
        }

        return zvoleneCesty;
    }

    public static int minimalnaCenaAspon(List<Cesta> cesty, int X) {
        int maxKm = X + cesty.stream().mapToInt(c -> c.dlzka).sum();
        int[] dp = new int[maxKm + 1];
        int[] cestaIndex = new int[maxKm + 1]; // Index ciest pre sledovanie
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < cesty.size(); i++) {
            Cesta cesta = cesty.get(i);
            for (int j = maxKm; j >= cesta.dlzka; j--) {
                if (dp[j - cesta.dlzka] != Integer.MAX_VALUE && dp[j] > dp[j - cesta.dlzka] + cesta.cena) {
                    dp[j] = dp[j - cesta.dlzka] + cesta.cena;
                    cestaIndex[j] = i; // Uložíme index cesty
                }
            }
        }

        int minCena = Integer.MAX_VALUE;
        int km = X;
        for (int i = X; i <= maxKm; i++) {
            if (dp[i] < minCena) {
                minCena = dp[i];
                km = i;
            }
        }

        return minCena == Integer.MAX_VALUE ? -1 : minCena;
    }

    public static List<Cesta> ziskajZvoleneCestyAspon(List<Cesta> cesty, int X) {
        List<Cesta> zvoleneCesty = new ArrayList<>();
        int maxKm = X + cesty.stream().mapToInt(c -> c.dlzka).sum();
        int[] dp = new int[maxKm + 1];
        int[] cestaIndex = new int[maxKm + 1]; // Index ciest pre sledovanie
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < cesty.size(); i++) {
            Cesta cesta = cesty.get(i);
            for (int j = maxKm; j >= cesta.dlzka; j--) {
                if (dp[j - cesta.dlzka] != Integer.MAX_VALUE && dp[j] > dp[j - cesta.dlzka] + cesta.cena) {
                    dp[j] = dp[j - cesta.dlzka] + cesta.cena;
                    cestaIndex[j] = i; // Uložíme index cesty
                }
            }
        }

        int minCena = Integer.MAX_VALUE;
        int km = X;
        for (int i = X; i <= maxKm; i++) {
            if (dp[i] < minCena) {
                minCena = dp[i];
                km = i;
            }
        }

        if (minCena == Integer.MAX_VALUE) return zvoleneCesty; // Vráti prázdny zoznam ak nie je riešenie

        while (km > 0) {
            int idx = cestaIndex[km];
            zvoleneCesty.add(cesty.get(idx));
            km -= cesty.get(idx).dlzka;
        }

        return zvoleneCesty;
    }
}
