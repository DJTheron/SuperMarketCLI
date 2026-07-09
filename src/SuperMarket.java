import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;

public class SuperMarket {
    public static void main(String[] args) {


        HashMap<String, Product> products = new HashMap<>(); // TODO Make the product class to hold all the data
        products.put("DCBB", new Product("Dark Chocolate Brownie Bites", 79.99, 'R', 23, true, 'E'));
        products.put("MS", new Product("Millionares Shortbread", 76.99, 'R', 24, true, 'F'));
        products.put("FRAN", new Product("Woolworths Free Range Eggs 18s", 64.99, 'R', 45, true, 'A'));
        products.put("BILT", new Product("Woolworths Beef Biltong 200g", 119.99, 'R', 40, true, 'B'));
        products.put("ROOI", new Product("Woolworths Rooibos Tea 80s", 34.99, 'R', 60, true, 'A'));
        products.put("BOER", new Product("Woolworths Farmstyle Boerewors 1kg", 139.99, 'R', 15, true, 'B'));
        products.put("OUMA", new Product("Ouma Rusks Buttermilk 1kg", 64.99, 'R', 25, true, 'C'));
        products.put("MRSB", new Product("Mrs Ball's Original Chutney 470g", 44.99, 'R', 30, true, 'C'));
        products.put("STST", new Product("Woolworths Steri Stumpie Strawberry 350ml", 13.99, 'R', 100, true, 'D'));
        products.put("MELK", new Product("Woolworths Milk Tart 500g", 54.99, 'R', 10, true, 'D'));
        products.put("SIMB", new Product("Simba Chips Salt & Vinegar 120g", 24.99, 'R', 80, true, 'E'));
        products.put("BARO", new Product("Bar-One Chocolate Slab 55g", 19.99, 'R', 120, true, 'E'));

        Cart mycart = new Cart();

        if (args.length == 0) {
            System.out.println("No arguments were given.");
            System.out.println("Use -h or --help to access the help page.");
        } else if (args.length > 0) {
            switch (args[0]) {
                case "-h", "--help" -> {
                    System.out.println("Super Market CLI");
                    System.out.println("-l/--list\t\t\t\t\t\t\t\t\t\t\t: Lists all available products -> short-product-name, name, price");
                    System.out.println("-p/--product short-product-name\t\t\t\t\t\t\t\t\t: Returns product information");
                    System.out.println("-c/--cart Action[add, remove, clear, view, checkout] short-product-name quantity-to-purchase\t\t: Adds product in selected quantity to the cart, if quantity not specified then one product added"); // add proper description/ help page notation/ structure
                }
                case "-l", "--list" -> {
                    System.out.println("Super Market CLI");
                    System.out.println("Products:");
                    for (String productName : products.keySet()) {
                        System.out.println("\t"+productName+" "+products.get(productName).name+" "+products.get(productName).currency+products.get(productName).price); 
                    }
                }
                case "-p", "--product" -> {
                    if (args[1].isEmpty()) {
                        System.out.println("Second argument (Shorthand Product Name) not provided.");
                        System.out.println("Use -h or --help to access the help page.");
                    }
                    System.out.println(products.get(args[1]).name);
                    System.out.println(""+products.get(args[1]).currency + products.get(args[1]).price);
                    if (products.get(args[1]).inStock) {System.out.println("In Stock, " + products.get(args[1]).quantity + " " + "available");} else {System.out.println("Out of Stock :(");}
                    System.out.println("Food health grade" + " " + products.get(args[1]).foodGrade);
                }
                case "-c", "--cart" -> {
                    if (args[1].isEmpty()) {
                        System.out.println("Second argument (Action) not provided.");
                        System.out.println("Use -h or --help to access the help page.");
                    }
                    switch (args[1]) {
                        case "add" -> {
                            int quantity;
                            if (args.length <= 3) {
                                quantity = 1;
                            } else {
                                quantity = Integer.parseInt(args[3]);
                            }
                            mycart.add(args[2], quantity);
                        }
                        case "remove" -> {
                            mycart.remove(args[2]);
                        }
                        case "clear" -> {
                            mycart.clear();
                        }
                        case "view" -> {
                            HashMap<String, Integer> cart = mycart.cart;
                            double total = 0;
                            char currency = ' ';
                            DecimalFormat df = new DecimalFormat("#.##");
                            String format = "%-16s%-40s%-12s%s%n";

                            System.out.printf(format, "Shorthand Name", "Name", "Quantity", "Price");
                            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                                System.out.printf(format, entry.getKey(), products.get(entry.getKey()).name, entry.getValue(), products.get(entry.getKey()).currency + df.format(products.get(entry.getKey()).price * entry.getValue()));
                                total += products.get(entry.getKey()).price * entry.getValue();
                                currency = products.get(entry.getKey()).currency;
                            }
                            System.out.println("Total: " + currency + df.format(total));
                        }
                        case "checkout" -> { // TODO make it much more fun and satisfying
                            System.out.println("Products Purchased!");
                            mycart.clear();
                        }
                    }
                }

            }
        } else {
            System.out.println("Argument not recognised.");
            System.out.println("Use -h or --help to access the help page.");
        }
    }
}

/*
What I plan to add:
 - Cart + Simple One-Click Pay (with something satisftying)

TODO:
 - Cart class
  - Creates hashmap, saves to csv file, uses .split(",")
   - Hashmap contains product shorthand (string) and quantity purchased (int)
  - Add, Remove, Get, Clear (after purchase)
 - Satisfying checkout
 */