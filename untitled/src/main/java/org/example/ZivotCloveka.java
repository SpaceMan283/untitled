

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Sen{

    String nazov;

    int zaciatok;

    int dobaTrvania;

    int skusenosti;

    @Override
    public String toString() {
        return "Sen{" +
                "nazov='" + nazov + '\'' +
                ", zaciatok=" + zaciatok +
                ", dobaTrvania=" + dobaTrvania +
                ", skusenosti=" + skusenosti +
                '}';
    }

    public Sen(String nazov, int zaciatok, int dobaTrvania, int skusenosti){
        this.nazov = nazov;
        this.zaciatok = zaciatok;
        this.dobaTrvania = dobaTrvania;
        this.skusenosti = skusenosti;
    }

    public int Koniec(){
        return zaciatok + dobaTrvania;
    }
}
public class ZivotCloveka {

    public static void main(String[] args) {
        List<Sen> sny = Arrays.asList(
                new Sen("absolvovanie VŠ",19, 5, 10000),
                new Sen("cesta okolo sveta",24, 1, 4000),
                new Sen("učenie sa hrať na cimbal",22, 2, 2000),
                new Sen("založenie start-up",20, 3, 6000)
        );

        int najXP = najviacSkusenosti(sny);
        System.out.println(najXP);
    }

    private static int najviacSkusenosti(List<Sen> sny) {
        sny.sort(Comparator.comparingInt(p -> p.zaciatok));

        int n = sny.size();
        int[] dp = new int[n];
        int[] vybrateSny = new int[n];
        Arrays.fill(vybrateSny, -1);

        int aktualnySen = 0;

        dp[0] = sny.get(0).skusenosti;


        for (int i = 1; i < n; i++) {
            int xp = sny.get(i).skusenosti;

            if(sny.get(aktualnySen).Koniec() <= sny.get(i).zaciatok){
                xp += dp[aktualnySen];
                vybrateSny[i] = aktualnySen;
            }


            for (int j = aktualnySen; j < i; j++) {
                if(sny.get(j).Koniec() <= sny.get(i).zaciatok && dp[j] > dp[aktualnySen]){
                    aktualnySen = j;
                    xp = sny.get(i).skusenosti + dp[j];
                    vybrateSny[i] = j;

                }

            }

            if(xp > dp[i -1]){
                dp[i] = xp;
            } else {
                dp[i] = dp[i - 1];
            }

        }


        List<Sen> realizovaneSny = new ArrayList<>();
        int idx = n - 1;
        while (idx >= 0 ){
            if(idx == 0 || dp[idx] != dp[idx - 1]){
                realizovaneSny.add(sny.get(idx));
                idx = vybrateSny[idx];
            } else {
                idx--;
            }
        }

        for (Sen sen : realizovaneSny) {
            System.out.println(sen.nazov);

        }

        return dp[n - 1];
    }
}
