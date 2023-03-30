public class Main {
    public static void main(String[] args) {
       Factory factory = new Factory();
        System.out.println(factory);
        //factory.allProductsByGroup("group3");

        //testing

//        factory.addProductsGroup("group3",  "this is group3");
//        factory.addProduct("group3", "product4", "this is product1", "producer", 5, 100);
//        System.out.println(factory);
//        factory.showAllProductsCost();
        //factory.showAllProductsByGroup("Ліжка");
//        factory.showAllProductsCostByGroup("Ліжка");
        factory.deleteProduct("Appendage");
        //factory.deleteProductsGroup("Шафи");
        ///factory.editProductsGroup("Стільці", "Stiltsi", "name");
        //factory.editProductsGroup("Stiltsi", "These are cool stiltsi", "description");
        //factory.editProduct("product4", "this is sofa", "description");
        //factory.editProduct("product4", "Стільці", "group");
        //System.out.println(factory);
        factory.addMoreOfProduct("Pollen", 6);
        factory.showAllProductsByGroup("Ліжка");
        factory.showAllProducts();

    }

}