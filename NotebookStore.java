import java.util.*;
import java.util.stream.Collectors;

public class NotebookStore {
    private List<Notebook> notebooks;

    public NotebookStore() {
        notebooks = new ArrayList<>();
        notebooks.add(new Notebook("Lenovo", 8, 256, "Windows", "Black"));
        notebooks.add(new Notebook("HP", 16, 512, "Linux", "Silver"));
        notebooks.add(new Notebook("Huawei", 4, 128, "Windows", "White"));
        notebooks.add(new Notebook("Macbook", 32, 1024, "MacOS", "Gray"));
        notebooks.add(new Notebook("Asus ROG", 64, 2048, "Windows", "Black"));
        notebooks.add(new Notebook("Asrock", 24, 512, "Linux", "Silver"));
        notebooks.add(new Notebook("Samsung", 64, 128, "Windows", "White"));
        notebooks.add(new Notebook("Macbook", 4, 256, "MacOS", "Gray"));
    }

    public void filterNotebooks() {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> criteria = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ (ГБ)");
        System.out.println("2 - Объем ЖД (ГБ)");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("0 - Завершить ввод критериев");

        while (true) {
            System.out.print("Выберите критерий: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Пожалуйста, введите цифру.");
                scanner.next(); // Пропустить некорректный ввод
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // чтобы поглотить новую строку после ввода числа
            if (choice == 0) break;

            String criterion = null;
            switch (choice) {
                case 1:
                    criterion = "ram";
                    System.out.print("Введите минимальное значение ОЗУ (ГБ): ");
                    break;
                case 2:
                    criterion = "storage";
                    System.out.print("Введите минимальное значение Объема ЖД (ГБ): ");
                    break;
                case 3:
                    criterion = "os";
                    System.out.print("Введите значение для операционной системы: ");
                    break;
                case 4:
                    criterion = "color";
                    System.out.print("Введите значение для цвета: ");
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
                    continue;
            }

            String value = scanner.nextLine();
            criteria.put(criterion, value);
        }

        List<Notebook> filteredNotebooks = notebooks.stream()
                .filter(notebook -> criteria.entrySet().stream().allMatch(entry -> {
                    String criterion = entry.getKey();
                    String value = entry.getValue();
                    switch (criterion) {
                        case "ram":
                            return notebook.getRam() >= Integer.parseInt(value);
                        case "storage":
                            return notebook.getStorage() >= Integer.parseInt(value);
                        case "os":
                            return notebook.getOs().equalsIgnoreCase(value);
                        case "color":
                            return notebook.getColor().equalsIgnoreCase(value);
                        default:
                            return true;
                    }
                }))
                .collect(Collectors.toList());

        System.out.println("Найденные ноутбуки:");
        filteredNotebooks.forEach(System.out::println);
    }

    public static void main(String[] args) {
        NotebookStore store = new NotebookStore();
        store.filterNotebooks();
    }
}
