public class Main {
    public static void main(String[] args) {
       Factory factory = new Factory();
        System.out.println(factory);
        //factory.allProductsByGroup("group3");

        //testing
        /*
        factory.addProductsGroup("group3",  "this is product1");
        factory.addProduct("group3", "product4", "this is product1", "producer", 5, 100);
        System.out.println(factory);
        factory.showAllProductsCost();
        factory.showAllProductsByGroup("group4");
        factory.showAllProductsCostByGroup("group4");
        */
    }
}