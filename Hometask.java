package Seminar6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Seminar6.Exceptions.*;

class PhoneBook {
    private HashMap<String, HashSet<String>> book;
    private Scanner scanner;

    public PhoneBook(Scanner scanner) {
        this.book = new HashMap<>();
        this.scanner = scanner;
    }

    public void add(String name, String phoneNumber) {
        HashSet<String> phoneNumbers = book.getOrDefault(name, new HashSet<>());
        phoneNumbers.add(phoneNumber);
        book.put(name, phoneNumbers);
    }

    public void print() {

        List<Map.Entry<String, HashSet<String>>> sortedEntries = new ArrayList<>(book.entrySet());
        if (sortedEntries.size() == 0) {
            System.out.println("\nТелефонная книга не содержит ни одной записи.\n");
        } else {
            sortedEntries.sort((entry1, entry2) -> entry2.getValue().size() - entry1.getValue().size());
            for (Map.Entry<String, HashSet<String>> entry : sortedEntries) {
                System.out.println("\n" + entry.getKey() + ": " + entry.getValue().size() + " номер(а)");
                for (String phoneNumber : entry.getValue()) {
                    System.out.println("\t" + phoneNumber);
                }
                System.out.println();
            }

        }
    }

    public String dataInput(String message) {
        System.out.println(message);
        String input = scanner.nextLine();
        return input;
    }

    public int menu() {
        try {
            int input = Integer.parseInt(dataInput(
                    "1. Ввод новой записи в телефонную книгу\n2. Распечатать телефонную книгу\n3. Выход\nВыберите действие: "));
            return input;
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ввода. Повторите выбор.");
            return 0;
        }

    }
}

public class Hometask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Создаем Scanner
        PhoneBook phoneBook = new PhoneBook(scanner);
        String input;

        int menuchoose = phoneBook.menu();
        Exceptions me = new Exceptions();

        while (menuchoose != 3) {
            try {
                if (menuchoose == 1) {
                    input = phoneBook.dataInput("Введите данные через пробел (Фамилия Имя Отчество  Номер_телефона):");
                    me.dataLength(input, 4);

                    ResultWrapper result = me.parseInput(input);
                    String[] personalInfo = result.getResult();
                    Long phoneNumber = result.getPhone();
                    phoneBook.add(String.join(" ", personalInfo), String.valueOf(phoneNumber));
                } else if (menuchoose == 2) {
                    phoneBook.print();
                } else if (menuchoose == 3) {
                    scanner.close();
                }
            } catch (InvalidArgumentCountException e) {
                System.out.println(e.getMessage());
            } catch (InvalidPhoneNumberException e) {
                System.out.println(e.getMessage());
            } catch (InvalidNameException e) {
                System.out.println(e.getMessage());
            }

            menuchoose = phoneBook.menu();
        }
    }
}
