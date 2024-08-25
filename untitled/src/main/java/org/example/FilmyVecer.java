

import java.util.Arrays;
import java.util.List;

public class FilmyVecer {

    private static class Film{

        String nazov;
        int dlzka;


        public Film(String nazov, int dlzka){
            this.nazov = nazov;
            this.dlzka = dlzka;
        }
    }


    private Film[] filmy;

    private int limitFilmov;

    private int limitDobyPozerania;

    private int[] triedenieNaDni;

    private int[] najRozdelenie;

    private int najPocetDni = Integer.MAX_VALUE;

    public FilmyVecer(List<Film> filmy, int LimitFilmov, int LimitDobyPozerania){
        this.filmy = filmy.toArray(new Film[0]);
        this.limitFilmov = LimitFilmov;
        this.limitDobyPozerania = LimitDobyPozerania;
        this.triedenieNaDni = new int[filmy.size()];
        this.najRozdelenie = new int[filmy.size()];

        for (Film film: filmy) {
            if(film.dlzka > limitDobyPozerania){
                System.out.println("Nie je mozne si tento zoznam filmov pozriet");
                return;
            }
        }

        generuj(0,0);
        System.out.println(najPocetDni);
        vypisFilmov();

    }

    private void generuj(int odIdx, int pocetDni) {
        if(odIdx == filmy.length){
            if(pocetDni < najPocetDni){
                najPocetDni = pocetDni;
                for (int i = 0; i < triedenieNaDni.length; i++) {
                    najRozdelenie[i] = triedenieNaDni[i];
                }
            }
            return;
        }

        for (int den = 1; den <= pocetDni + 1; den++) {
            int pocetOdsledovanychMinutVDanyDen = 0;
            int pocetPozrenychFilmovVDanyDen = 0;


            for (int i = 0; i < odIdx; i++) {
                if(triedenieNaDni[i] == den){
                    pocetOdsledovanychMinutVDanyDen += filmy[i].dlzka;
                    pocetPozrenychFilmovVDanyDen++;
                }
            }

            if(pocetOdsledovanychMinutVDanyDen + filmy[odIdx].dlzka <= limitDobyPozerania && pocetPozrenychFilmovVDanyDen < limitFilmov){
                triedenieNaDni[odIdx] = den;
                generuj(odIdx + 1, Math.max(den,pocetDni));
            }
        }
    }

    public void vypisFilmov(){
        for (int den = 1; den <= najPocetDni; den++) {
            System.out.println("den :" + den + ":");
            for (int i = 0; i < najRozdelenie.length; i++) {
                if(najRozdelenie[i] == den){
                    System.out.println("nazov filmu je : " + filmy[i].nazov + "je dlzka je : " + filmy[i].dlzka);
                }
            }
        }
    }




    public static void main(String[] args) {
        List<Film> listFilmov = Arrays.asList(
                new Film("Krstny otec", 300),
                new Film("Inside", 150),
                new Film("Budapest", 200),
                new Film("Duna", 250),
                new Film("Apache", 350),
                new Film("kokotina", 451)

        );


        int LimitFilmov = 2;
        int LimitDobyPozerania = 450;


        new FilmyVecer(listFilmov, LimitFilmov, LimitDobyPozerania);
    }
}
