

import java.util.ArrayList;
import java.util.List;

public class DovolenkaBT {

    private int M = 2;
    private int H = 3;
    private int m = 1;
    private int h = 2;

    private List<String> vyber = new ArrayList<String>();



    public void generuj(String moznost, int pocetDniPriMor, int pocetDniNaHor, int pocetDniPoSebeMor, int pocetDniPoSebeHor){

        if(pocetDniPriMor == M && pocetDniNaHor== H){
            vyber.add(moznost);
            System.out.println(vyber + " " + vyber.size());
            return;
        }

        if(pocetDniPriMor < M && pocetDniPoSebeMor < m){
            generuj(moznost + "More,", pocetDniPriMor + 1, pocetDniNaHor, pocetDniPoSebeMor + 1, 0);
        }

        if(pocetDniNaHor < H && pocetDniPoSebeHor < h){
            generuj(moznost + "Hora,", pocetDniPriMor,pocetDniNaHor + 1, 0, pocetDniPoSebeHor + 1);
        }
    }

    public static void main(String[] args) {
        DovolenkaBT dovolenkaBT = new DovolenkaBT();
        dovolenkaBT.generuj("",0,0,0,0);
    }

}
