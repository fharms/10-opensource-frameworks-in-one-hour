import java.util.Scanner;

public class CacheApplication {

    public static void main(String[] args) throws Exception {
        DawaDataSource dataSource = new DawaDataSource();

        System.out.println("Enter (partial, danish) address to search for:");

        Scanner scanner = new Scanner(System.in);
        String query = scanner.nextLine();
        while (query != null) {
            long start = System.currentTimeMillis();
            String[] result = dataSource.search(query);
            for (String each : result) {
                System.out.println(" - " + each);
            }
            System.out.println("(total of " + result.length + ", in " + (System.currentTimeMillis() - start) + "ms)");

            System.out.println("\n\nEnter NEXT (partial, danish) address to search for:");
            query = scanner.nextLine();
        }

    }
}
