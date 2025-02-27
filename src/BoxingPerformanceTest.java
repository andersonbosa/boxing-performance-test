import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoxingPerformanceTest {
    public static void main(String[] args) {
        int tamanho = 10_000_000;
        Runtime runtime = Runtime.getRuntime();

        // Usando array com primitivos
        BoxingPerformanceTest.pressToContinue("Pressione ENTER para CONTINUAR...");
        long startTime = System.nanoTime();
        int[] array = new int[tamanho];
        long memAntesArray = runtime.totalMemory() - runtime.freeMemory();
        for (int i = 0; i < tamanho; i++) {
            array[i] = i; // Sem boxing
        }
        long memDepoisArray = runtime.totalMemory() - runtime.freeMemory();
        long somaArray = 0;
        for (int num : array) {
            somaArray += num; // Sem unboxing
        }
        long endTime = System.nanoTime();
        System.out.println("Tempo com array (primitivo): " + (endTime - startTime) / 1_000_000 + " ms");
        System.out.println("Memória usada pelo array primitivo: " + (memDepoisArray - memAntesArray) / (1024 * 1024) + " MB");


        // Usando wrapper
        BoxingPerformanceTest.pressToContinue("Pressione ENTER para CONTINUAR...");
        startTime = System.nanoTime();
        List<Integer> lista = new ArrayList<>();
        long memAntesLista = runtime.totalMemory() - runtime.freeMemory();
        for (int i = 0; i < tamanho; i++) {
            lista.add(i); // Autoboxing
        }
        long memDepoisLista = runtime.totalMemory() - runtime.freeMemory();
        long somaLista = 0;
        for (Integer num : lista) {
            somaLista += num; // Autounboxing
        }
        endTime = System.nanoTime();
        System.out.println("----------------------------------------");
        System.out.println("Tempo com ArrayList (boxing): " + (endTime - startTime) / 1_000_000 + " ms");
        System.out.println("Memória usada pelo ArrayList: " + (memDepoisLista - memAntesLista) / (1024 * 1024) + " MB");


        BoxingPerformanceTest.pressToContinue("Pressione ENTER para FINALIZAR...");
    }

    // Mantém o programa rodando até o usuário pressionar ENTER
    // para analisar o PID com VisualVM
    public static void pressToContinue(String msg) {
        System.out.println("----------------------------------------");
        System.out.println(msg);
        new Scanner(System.in).nextLine();
    }
}