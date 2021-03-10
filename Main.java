import java.util.Scanner;

public class Main{ 
    public static final int size = 20;
    public static void main(String[] args) {
            Shop ourshop = new Shop();
            ourshop = Shop.input(ourshop);
            ourshop.output(ourshop);
            System.out.printf("Sum price = %d", Shop.add(ourshop));
            System.out.print("\nProgram is over. Press any key to exit.");
    }
}

// Create class
class Item { // Основной класс
    private String country;
    private String name;
    private double price;

    public Item() {
        country = "";
        name = "";
        price = 0;
    }
    public Item(String newCountry, String newName, double newPrice) {
        country = newCountry;
        name = newName;
        price = newPrice;
    }

    void setPrice(double newPrice) {
        price = newPrice;
    }

    double getPrice() {
        return(price);
    }

    // Read Функция ввода
    static Item input(int i) {
        String newCountry;
        String newName;
        double newPrice;
        Scanner scan = new Scanner(System.in); 
        do {
            System.out.printf("Input country of %d product: ", i+1);
            newCountry = scan.nextLine();
        } while (newCountry.charAt(0) == '\0' || newCountry.charAt(0) == ' ');

        do {
            System.out.printf("Input name of %d product: ", i+1);
            newName = scan.nextLine();
        } while (newName.charAt(0) == '\0' || newName.charAt(0) == ' ');

        do {
            System.out.printf("Input price of %d product: ", i + 1);
            while (!scan.hasNextInt()) {
                System.out.printf("Input price of %d product: ", i + 1);
                scan.next();
            }
            newPrice = scan.nextInt();
        } while (newPrice <= 0);


        System.out.print("\n");
        Item newItem = new Item(newCountry, newName, newPrice);
        return(newItem);
    }

    // Display Функция вывода
    public void output(Item prod, int i) {
        System.out.printf("%d product:", i+1);
        System.out.print("\n");
        System.out.printf("Country: %s", prod.country);
        System.out.print("\n");
        System.out.printf("Product: %s", prod.name);
        System.out.print("\n");
        int price = (int)prod.price;
        System.out.printf("Price = %d", price);
        System.out.print("\n");
    }

    // Add Функция суммирования
    static int add(Item item, Item secondItem) {
        int sum;
        sum = (int)item.price + (int)secondItem.price;
        return sum;
    }

    // Add sale
    void sale(Item item, int num) {
        for (int i = 0; i < num; i++)
            item.setPrice(item.getPrice()*0.5);
    }

    // Add markup
    void markup(Item item, int num) {
        for (int i = 0; i < num; i++)
            item.setPrice(item.getPrice()*2);
    }
};

class Shop { // Вспомогательный класс
    private Item[] prod = new Item[Main.size];
    private int quantityProd;

    public void Shop() {
        quantityProd = 0;
    }
    public void Shop(int newQuantityProd) {
        quantityProd = newQuantityProd;
    }

    public int getQuantityProd() {
        return(quantityProd);
    }

    public static Shop input(Shop ourshop) { // Демонстрируем принцип ассоциации
        int quantityProd;
        Item[] prod = new Item[Main.size];
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("How many products do you have in your shop?");
                while (!scan.hasNextInt()) {
                    System.out.printf("How many products do you have in your shop?");
                    scan.next();
                }
                quantityProd = scan.nextInt();
        } while (quantityProd <= 0 || quantityProd > Main.size);

        ourshop.quantityProd = quantityProd;

        for (int i = 0; i < quantityProd; i++) {
            ourshop.prod[i] = Item.input(i);
            System.out.println("If you want to have the same products - enter their quantity without the one just entered.");
            System.out.println("If you don't want to - enter anything and click enter.");
            int num;
            if(!scan.hasNextInt())
                num = 0;
            else
                num = scan.nextInt();
            if (num > 0 && num < (quantityProd - i)) {
                for (int j = i + 1; j <= (i + num); j++) {
                    ourshop.prod[j] = ourshop.prod[i];
                }
                i = i + num;
            }
        }
        return(ourshop);
    }

    public void output(Shop ourshop) { //Вывод
        for (int i = 0; i < quantityProd; i++) {
            ourshop.prod[i].output(ourshop.prod[i], i);
        }
    }

    public static int add(Shop ourshop) { //Складываем стоимость первого товара
        return(Item.add(ourshop.prod[0], ourshop.prod[0]));
    }

    public static int add(Item item, Item secondItem) {
        int sum;
        sum = (int)item.getPrice() + (int)secondItem.getPrice();
        return sum;
    }
};
