

import java.util.HashSet;
import java.util.Set;

public class dovolenkaBackTrack {

    private int[] pole;
    private int M;
    private int H;
    private int m;
    private int h;

    private Set<String> moznosti = new HashSet<String>();

    public dovolenkaBackTrack(int m, int h, int M, int H) {
        this.M = M;
        this.h = h;
        this.m = m;
        this.H = H;
        this.pole = new int[M + H];
        generuj(0);

    }

    public int  vytvorMoznosti() {
        int pocetM = 0;
        int pocetH = 0;
        int poSebem =0;
        int poSebeh = 0;
        int pocetMoznosti = 0;
        String moznost= "";
        for (int i = 0; i < pole.length; i++) {
            if(pole[i] == 0 && pocetM < M){
                if(poSebem < m) {
                    poSebeh = 0;
                    moznost += "more,";
                    pocetMoznosti++;
                    pocetM++;
                    poSebem++;
                }
            }else if (pole[i] == 1 && pocetH < H) {
                if (poSebeh < h) {
                    poSebem = 0;
                    moznost += "hory,";
                    pocetMoznosti++;
                    pocetH++;
                    poSebeh++;
                }
            }
        }
        if(pocetMoznosti == M+H) {
            moznosti.add(moznost);
        }

//        System.out.println(moznosti + "     "  + moznosti.size());
        return moznosti.size();
    }

    public void generuj(int k) {
        if (k == pole.length) {
            vytvorMoznosti();
            return;
        }
        pole[k] = 0;
        generuj(k + 1);
        pole[k] = 1;
        generuj(k + 1);
    }

    public static void main(String[] args) {
        dovolenkaBackTrack dovolenka = new dovolenkaBackTrack(1, 2, 2, 3);
    }

}