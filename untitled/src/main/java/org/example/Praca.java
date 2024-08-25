


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Task{
    String popis;
    int hodiny;
    int cena;

    @Override
    public String toString() {
        return "Task{" +
                "popis='" + popis + '\'' +
                ", hodiny=" + hodiny +
                ", cena=" + cena +
                '}';
    }

    public Task(String popis, int hodiny, int cena){
        this.popis = popis;
        this.hodiny = hodiny;
        this.cena = cena;
    }
}

public class Praca {

    public static void main(String[] args) {
        List<Task> tasky = Arrays.asList(
                new Task("Grafika pre ikony", 4, 60),
                new Task("Oprava Unit testov", 3, 40),
                new Task("Meeting", 1, 15),
                new Task("Praca na issues", 2, 35),
                new Task("Teambuilding", 5, 50)
        );

        int pocetHodin = 10;

        int najVyplata = maximalnaVyplata(tasky,pocetHodin);
        System.out.println(najVyplata);

        List<Task> vysledok = Vypis(tasky, pocetHodin);
        System.out.println(vysledok);
    }

    public static int maximalnaVyplata(List<Task> tasky, int pocetHodin){

        int[] dp = new int[pocetHodin + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        boolean[] used = new boolean[tasky.size()];
        dp[0] = 0;

        for (int i = 0; i < tasky.size(); i++) {
            Arrays.fill(used, false);
            Task task = tasky.get(i);

            for (int j = pocetHodin; j >= task.hodiny; j--) {
                if(dp[j - task.hodiny] != Integer.MAX_VALUE && dp[j] > (dp[j - task.hodiny] + task.cena ) && !used[i]){
                    dp[j] = (dp[j - task.hodiny] + task.cena);
                    used[i] = true;
                    break;
                }
            }
        }

        return dp[pocetHodin];
    }

    public static List<Task> Vypis(List<Task> tasky, int pocetHodin){

        List<Task> spraveneUlohy = new ArrayList<>();
        int[] dp = new int[pocetHodin + 1];
        int[] poradie = new int [pocetHodin + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < tasky.size(); i++) {
            Task task = tasky.get(i);

            for (int j = pocetHodin; j >= task.hodiny; j--) {
                if(dp[j - task.hodiny] != Integer.MAX_VALUE && dp[j] > (dp[j - task.hodiny] + task.cena)){
                    dp[j] = (dp[j - task.hodiny] + task.cena);
                    poradie[j] = i;
                    break;
                }
            }
        }

        int hodiny = pocetHodin;
        while (hodiny > 0){
            spraveneUlohy.add(tasky.get(poradie[hodiny]));
            hodiny -= tasky.get(poradie[hodiny]).hodiny;

        }

        return spraveneUlohy;
    }
}
