

import java.util.*;

public class ZabyBacktracking {

    private static class Lokalita {
        String identifikator;
        int velkost;

        public Lokalita(String identifikator, int velkost) {
            this.identifikator = identifikator;
            this.velkost = velkost;
        }
    }

    private List<Lokalita> lokality;
    private int maxLokalityNaDen;
    private int maxArovNaDen;
    private int minPocetDni = Integer.MAX_VALUE;
    private List<List<Lokalita>> najlepsieRiesenie;

    public ZabyBacktracking(List<Lokalita> lokality, int maxLokalityNaDen, int maxArovNaDen) {
        this.lokality = lokality;
        this.maxLokalityNaDen = maxLokalityNaDen;
        this.maxArovNaDen = maxArovNaDen;
        this.najlepsieRiesenie = new ArrayList<>();
        generuj(0, new ArrayList<>(), 0);
        System.out.println("Minimálny počet dní: " + minPocetDni);
        vypisHarmonogram();
    }

    private void spracuj(List<List<Lokalita>> dny) {
        if (dny.size() < minPocetDni) {
            minPocetDni = dny.size();
            najlepsieRiesenie = new ArrayList<>();
            for (List<Lokalita> den : dny) {
                najlepsieRiesenie.add(new ArrayList<>(den));
            }
        }
    }

    private void generuj(int odIdx, List<List<Lokalita>> dny, int aktualnyDen) {
        if (odIdx == lokality.size()) {
            spracuj(dny);
            return;
        }

        if (dny.size() <= aktualnyDen) {
            dny.add(new ArrayList<>());
        }

        for (int i = aktualnyDen; i < dny.size(); i++) {
            List<Lokalita> den = dny.get(i);
            if (den.size() < maxLokalityNaDen && pocetArov(den) + lokality.get(odIdx).velkost <= maxArovNaDen) {
                den.add(lokality.get(odIdx));
                generuj(odIdx + 1, dny, aktualnyDen);
                den.remove(den.size() - 1);
            }
        }

        List<Lokalita> novyDen = new ArrayList<>();
        novyDen.add(lokality.get(odIdx));
        dny.add(novyDen);
        generuj(odIdx + 1, dny, aktualnyDen + 1);
        dny.remove(dny.size() - 1);
    }

    private int pocetArov(List<Lokalita> den) {
        return den.stream().mapToInt(l -> l.velkost).sum();
    }

    private void vypisHarmonogram() {
        for (int i = 0; i < najlepsieRiesenie.size(); i++) {
            System.out.println("Deň " + (i + 1) + ": ");
            for (Lokalita lokalita : najlepsieRiesenie.get(i)) {
                System.out.println(" - " + lokalita.identifikator + " (" + lokalita.velkost + " árov)");
            }
        }
    }

    public static void main(String[] args) {
        List<Lokalita> lokality = Arrays.asList(
                new Lokalita("A", 10),
                new Lokalita("B", 20),
                new Lokalita("C", 15),
                new Lokalita("D", 25),
                new Lokalita("E", 10)
        );

        int maxLokalityNaDen = 2;
        int maxArovNaDen = 30;

        new ZabyBacktracking(lokality, maxLokalityNaDen, maxArovNaDen);
    }
}
