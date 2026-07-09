void main() {
    Cart mycart = new Cart();


    mycart.add("DCBB", 2);
    mycart.add("MISH", 4);



    HashMap<String, Integer> cart = mycart.cart;




    IO.println(cart);
}