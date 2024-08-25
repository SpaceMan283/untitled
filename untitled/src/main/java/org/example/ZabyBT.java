

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZabyBT {

    private static class Lokalita {
        String nazov;
        int velkost;

        public Lokalita(String nazov,int velkost){
            this.nazov = nazov;
            this.velkost = velkost;
        }
    }

    private Lokalita[] lokalitas;

    private int MaxPocetNavstivenychLokalitVJedenDen;

    private int MaxPocetAraovvJedenDen;

    private int[] rozdelenie;

    private int[] NajRiesenie;

    private int MinPocetDni = Integer.MAX_VALUE;

    public ZabyBT(List<Lokalita> lokalitas, int maxPocetAraovvJedenDen, int maxPocetNavstivenychLokalitVJedenDen){
        this.lokalitas = lokalitas.toArray(new Lokalita[0]);
        this.MaxPocetNavstivenychLokalitVJedenDen = maxPocetNavstivenychLokalitVJedenDen;
        this.MaxPocetAraovvJedenDen = maxPocetAraovvJedenDen;
        this.rozdelenie = new int[lokalitas.size()];
        this.NajRiesenie = new int[lokalitas.size()];

        for (Lokalita loka: lokalitas) {
            if(loka.velkost > maxPocetAraovvJedenDen){
                System.out.println("Lokalita je priliz velka");
                return;
            }
        }

        generuj(0,0);

        System.out.println("minimalny pocet dni:" + MinPocetDni);
        vypisMiest();
    }

    private void generuj(int odIdx, int pocetDni) {
        if(odIdx == lokalitas.length){
            if(pocetDni < MinPocetDni){
                MinPocetDni = pocetDni;
                for (int i = 0; i < rozdelenie.length; i++) {
                    NajRiesenie[i] = rozdelenie[i];
                }
            }
            return;
        }


        for (int den = 1; den <= pocetDni + 1; den++) {
            int areDen = 0;
            int miestaDen = 0;

            for (int i = 0; i < odIdx; i++) {
                if(rozdelenie[i] == den){
                    areDen += lokalitas[i].velkost;
                    miestaDen++;
                }
            }


            if(areDen + lokalitas[odIdx].velkost <= MaxPocetAraovvJedenDen && miestaDen < MaxPocetNavstivenychLokalitVJedenDen){
                rozdelenie[odIdx] = den;
                generuj(odIdx + 1, Math.max(pocetDni, den));
            }
        }
    }


    private void vypisMiest(){
        for (int den = 1; den <= MinPocetDni; den++) {
            System.out.println("Den:" + den + ":");
            for (int i = 0; i < lokalitas.length; i++) {
                if(NajRiesenie[i] == den){
                    System.out.println(" - " + lokalitas[i].nazov + " (" + lokalitas[i].velkost + " velkost)");
                }
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

        int MaxPocetLokalit = 2;
        int MaxPocetArov = 30;


        new ZabyBT(lokality, MaxPocetArov, MaxPocetLokalit);


    }
}
