

import java.util.*;

class Festival{
    String nazov;

    int zaciatok;

    int koniec;

    int penaze;

    @Override
    public String toString() {
        return "Festival{" +
                "nazov='" + nazov + '\'' +
                ", zaciatok=" + zaciatok +
                ", koniec=" + koniec +
                ", penaze=" + penaze +
                '}';
    }

    public Festival(String nazov, int zaciatok, int koniec, int penaze){
        this.nazov = nazov;
        this.zaciatok = zaciatok;
        this.koniec = koniec;
        this.penaze = penaze;
    }
}

public class Leto {

    public static void main(String[] args) {
        List<Festival> festivaly = Arrays.asList(
                new Festival("Rolling Load", 7, 11, 10000),
                new Festival("Pohoda", 9, 13, 20000),
                new Festival("Ibiza", 13, 15, 50000),
                new Festival("Grape", 14, 16, 15000),
                new Festival("Sirava", 17, 20, 40)
        );


        int vyplata = maximalnyZarobok(festivaly);

        System.out.println(vyplata);
    }

    private static int maximalnyZarobok(List<Festival> festivaly) {
        festivaly.sort(Comparator.comparingInt(p -> p.koniec));


        int n = festivaly.size();
        int[] dp = new int[n];
        int[] vyber = new int[n];
        Arrays.fill(vyber, -1);

        dp[0] = festivaly.get(0).penaze;

        for (int i = 1; i < n; i++) {

            int penaze = festivaly.get(i).penaze;
            int predchadajuci = -1;

            for (int j = i - 1; j >= 0 ; j--) {
                if(festivaly.get(j).koniec < festivaly.get(i).zaciatok){
                    penaze += dp[j];
                    predchadajuci = j;
                    break;
                }
            }

            if(penaze > dp[i - 1]){
                dp[i] = penaze;
                vyber[i] = predchadajuci;
            } else {
                dp[i] = dp[i - 1];
            }

        }

        List<Festival> vybrateFestivaly = new ArrayList<>();
        int idx = n - 1;
        while (idx >= 0){
            if(idx == 0 || dp[idx] != dp[idx - 1]){
                vybrateFestivaly.add(festivaly.get(idx));
                idx = vyber[idx];
            }else {
                idx--;
            }
        }


        Collections.reverse(vybrateFestivaly);
        System.out.println("Vybrate Festivaly su : ");
        for (Festival festival: vybrateFestivaly) {
            System.out.println("Nazov : " + festival.nazov);

        }

        return dp[n - 1];
    }
}
