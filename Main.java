import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод числа строк
        System.out.print("Введите количество строк N: ");
        int N = scanner.nextInt();
        List<List<Double>> array = new ArrayList<>();

        // Ввод массива
        for (int i = 0; i < N; i++) {
            System.out.print("Введите количество элементов в строке " + (i + 1) + ": ");
            int Mi = scanner.nextInt();
            List<Double> row = new ArrayList<>();
            System.out.println("Введите элементы строки " + (i + 1) + ": ");
            for (int j = 0; j < Mi; j++) {
                row.add(scanner.nextDouble());
            }
            array.add(row);
        }

        // Сортировка строк
        array.sort((row1, row2) -> {
            long negatives1 = row1.stream().filter(x -> x < 0).count();
            long negatives2 = row2.stream().filter(x -> x < 0).count();
            if (negatives1 != negatives2) {
                return Long.compare(negatives1, negatives2);
            }
            double positivesSum1 = row1.stream().filter(x -> x > 0).mapToDouble(Double::doubleValue).sum();
            double positivesSum2 = row2.stream().filter(x -> x > 0).mapToDouble(Double::doubleValue).sum();
            return Double.compare(positivesSum1, positivesSum2);
        });

        // Нахождение максимального элемента
        double max = Double.NEGATIVE_INFINITY;
        int maxRow = -1, maxCol = -1;
        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.get(i).size(); j++) {
                if (array.get(i).get(j) > max) {
                    max = array.get(i).get(j);
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        // Вывод массива в формате матрицы
        int maxRowLength = array.stream().mapToInt(List::size).max().orElse(0);
        System.out.println("Отсортированный массив в виде матрицы:");
        for (List<Double> row : array) {
            for (int j = 0; j < maxRowLength; j++) {
                if (j < row.size()) {
                    System.out.printf("%.2f ", row.get(j));
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }

        // Вывод максимального элемента
        System.out.println("Максимальный элемент: " + max + " (строка " + (maxRow + 1) + ", позиция " + (maxCol + 1) + ")");

        // Преобразование массива (1/число)
        System.out.println("Массив после замены на обратные значения:");
        for (List<Double> row : array) {
            for (int j = 0; j < row.size(); j++) {
                row.set(j, 1 / row.get(j));
            }
        }
        for (List<Double> row : array) {
            for (int j = 0; j < maxRowLength; j++) {
                if (j < row.size()) {
                    System.out.printf("%.2f ", row.get(j));
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}