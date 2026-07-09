public class Product {
    String name; // all these must be added up here so that it doesn't die
    double price;
    char currency;
    int quantity;
    boolean inStock;
    char foodGrade;

    public Product(String name, double price, char currency, int quantity, boolean inStock, char foodGrade) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.quantity = quantity;
        this.inStock = inStock;
        this.foodGrade = foodGrade;
    }
}
