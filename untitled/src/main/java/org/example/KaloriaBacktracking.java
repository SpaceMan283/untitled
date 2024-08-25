

import java.util.Arrays;
import java.util.List;

public class KaloriaBacktracking {

    private static class Cvik {
        String nazov;
        int kalorie;

        public Cvik(String nazov, int kalorie) {
            this.nazov = nazov;
            this.kalorie = kalorie;
        }
    }

    private Cvik[] cviky;
    private int maxKalorieNaDen;
    private int maxCvikovNaDen;
    private int[] rozdelenie;
    private int[] najlepsieRiesenie;
    private int minPocetDni = Integer.MAX_VALUE;

    public KaloriaBacktracking(List<Cvik> cviky, int maxKalorieNaDen, int maxCvikovNaDen) {
        this.cviky = cviky.toArray(new Cvik[0]);
        this.maxKalorieNaDen = maxKalorieNaDen;
        this.maxCvikovNaDen = maxCvikovNaDen;
        this.rozdelenie = new int[cviky.size()];
        this.najlepsieRiesenie = new int[cviky.size()];

        for (Cvik cvik: cviky) {
            if(cvik.kalorie > maxKalorieNaDen){
                System.out.println("Nie je mozne odcvicit vsetky cviky");
                return;
            }

        }

        // Spustenie backtracking algoritmu
        generuj(0, 0);

        // Vypis najlepšieho riešenia
        System.out.println("Minimálny počet dní: " + minPocetDni);
        vypisHarmonogram();
    }

    private void generuj(int odIdx, int pocetDni) {
        if (odIdx == cviky.length) {
            if (pocetDni < minPocetDni) {
                minPocetDni = pocetDni;
                for (int i = 0; i < rozdelenie.length; i++) {
                    najlepsieRiesenie[i] = rozdelenie[i];
                }
            }
            return;
        }

        for (int den = 1; den <= pocetDni + 1; den++) {
            int kalorieDen = 0;
            int pocetCvikovDen = 0;

            for (int i = 0; i < odIdx; i++) {
                if (rozdelenie[i] == den) {
                    kalorieDen += cviky[i].kalorie;
                    pocetCvikovDen++;
                }
            }

            if (kalorieDen + cviky[odIdx].kalorie <= maxKalorieNaDen && pocetCvikovDen < maxCvikovNaDen) {
                rozdelenie[odIdx] = den;
                generuj(odIdx + 1, Math.max(pocetDni, den));
            }
        }
    }

    private void vypisHarmonogram() {
        for (int den = 1; den <= minPocetDni; den++) {
            System.out.println("Deň " + den + ": ");
            for (int i = 0; i < cviky.length; i++) {
                if (najlepsieRiesenie[i] == den) {
                    System.out.println(" - " + cviky[i].nazov + " (" + cviky[i].kalorie + " kalórií)");
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Cvik> cviky = Arrays.asList(
                new Cvik("fit", 600),
                new Cvik("Zumba", 500),
                new Cvik("Tanec", 300),
                new Cvik("Crossfit", 600),
                new Cvik("Beh", 400),
                new Cvik("Kliky", 150)
        );

        int maxKalorieNaDen = 1000;
        int maxCvikovNaDen = 2;

        new KaloriaBacktracking(cviky, maxKalorieNaDen, maxCvikovNaDen);
    }
}
