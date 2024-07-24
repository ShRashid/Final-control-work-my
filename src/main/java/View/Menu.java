package View;

import Controller.*;
import Exceptions.UncorrectDataException;
import Model.PetType;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Menu {
    Controller controller;

    public Menu(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        Counter count = new Counter();
        System.out.println("  ");
        System.out.println("\n  ПЕРЕЧЕНЬ ДОМАШНИХ ПИТОМЦЕВ");
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            boolean flag = true;
            int id;
            String command;
            while (flag) {
                System.out.println("\n1) Вывести перечень питомцев" +
                                   "\n2) Добавить нового питомца" +
                                   "\n3) Изменить данные о питомце" +
                                   "\n4) Показать список команд питомца" +
                                   "\n5) Обучить питомца новым командам" +
                                   "\n6) Удалить питомца из перечня" +
                                   "\n0) Выход из программы");
                System.out.print("\nВведите число, соответствующее команде : ");
                String key = scanner.next();
                switch (key) {
                    case "1":
                        controller.getAllPet();
                        break;
                    case "2":
                        PetType type = petChoice(scanner);
                        if (type != null) {
                            try {
                                controller.createPet(type);
                                count.add();
                                System.out.println("Добавление питомца прошло успешно!");
                            } catch (UncorrectDataException error) {
                                System.out.println(error.getMessage());
                            }
                        }
                        break;
                    case "3":
                        while (true) {
                            id = idChoicePet(scanner);
                            if (id != 0)
                                try {
                                    controller.updatePet(id);
                                } catch (UncorrectDataException e) {
                                    System.out.println(e.getMessage());
                                }
                            else
                                break;
                        }
                    case "4":
                        while (true) {
                            id = idChoicePet(scanner);
                            if (id != 0)
                                controller.getCommands(id);
                            else
                                break;
                        }
                    case "5":
                        id = idChoicePet(scanner);
                        command = scanner.nextLine();
                        if (id != 0)
                            controller.trainPet(id, command);
                        break;
                    case "6":
                        id = idChoicePet(scanner);
                        if (id != 0)
                            controller.delete(id);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Команды с таким номером нет, введите число от 1 до 7");
                        break;
                }
            }
        }
    }

    private PetType petChoice(Scanner scanner) {
        System.out.println("Выберите питомца для добавления:\n1 - Кошка\n2 - Собака\n3 - Хомяк\n0 - Возврат в основное меню");

        while (true) {
            String key = scanner.next();
            switch (key) {
                case "1":
                    return PetType.Cat;
                case "2":
                    return PetType.Dog;
                case "3":
                    return PetType.Hamster;
                case "0":
                    return null;
                default:
                    System.out.println("Питомца с таким номером нет, введите число от 0 до 3");
                    break;
            }
        }
    }
    private Integer idChoicePet(Scanner scanner) {
        System.out.print("\nВведите номер питомца, 0 для возврата в основное меню: ");
        while(true) {
            int id = scanner.nextInt();
            scanner.nextLine();
            if (id == 0)
                return id;
            if (controller.getById(id) == null) {
                System.out.println("\nПитомца с таким номером нет, попробуйте еще раз, 0 для возврата в основное меню");
            } else
                return id;
        }
    }
}
