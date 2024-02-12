package by.aston;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
        System.out.println("1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).");
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        // 2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        System.out.println("2. Вывести список неповторяющихся городов, в которых работают трейдеры.");
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        // 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        System.out.println("3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.");
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        // 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
        System.out.println("4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.");
        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .forEach(System.out::println);

        // 5. Выяснить, существует ли хоть один трейдер из Милана.
        System.out.println("5. Выяснить, существует ли хоть один трейдер из Милана.");
        Optional<Transaction> milan = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Milan"))
                .findAny();
        if(milan.isPresent()) {
            System.out.println("Существуют трейдеры из Милана. Например: " + milan.get());
        } else {
            System.out.println("Не существует трейдеров из Милана");
        }

        // 6. Вывести суммы всех транзакций трейдеров из Кембриджа.
        System.out.println("6. Вывести суммы всех транзакций трейдеров из Кембриджа.");
        System.out.println(transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum)
                .orElse(0)
        );

        // 7. Какова максимальная сумма среди всех транзакций?
        System.out.println("7. Какова максимальная сумма среди всех транзакций?");
        System.out.println(transactions.stream()
                .map(Transaction::getValue)
                .max(Comparator.comparingInt(value -> value))
                .orElse(0)
        );

        // 8. Найти транзакцию с минимальной суммой.
        System.out.println("8. Найти транзакцию с минимальной суммой.");
        System.out.println(transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .orElse(null)
        );
    }
}
