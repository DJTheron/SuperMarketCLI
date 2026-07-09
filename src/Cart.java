import java.io.IOException;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Map;

public class Cart {
    HashMap<String, Integer> cart;

    public Cart() {
        cart = new HashMap<>();
        File cartf = new File("Cart.csv");

        try {
            if (cartf.createNewFile()) {
            } else {
                Scanner Reader = new Scanner(cartf);
                while (Reader.hasNextLine()) {
                    String data = Reader.nextLine();
                    String[] partsdata = data.split(",");
                    cart.put(partsdata[0], Integer.parseInt(partsdata[1]));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(HashMap<String, Integer> cart) {
        try (FileWriter fw = new FileWriter("Cart.csv")) {
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                fw.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(String productName, int quantity) {
        cart.put(productName, quantity);
        write(cart);
    }

    public void remove(String productName) {
        cart.remove(productName);
        write(cart);
    }

    public void clear() {
        cart = new HashMap<>();
        write(cart);
    }
}
