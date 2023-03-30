import java.util.ArrayList;
import java.util.Arrays;

public class Factory {
    private static ArrayList<ProductsGroup> productsGroups;
    private static ArrayList<Product> allProducts;
    private static int numberOfProductsGroups = 0;
    private static int numberOfProducts = 0;

    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    private ProductsGroup beds = new ProductsGroup("Ліжка", "Зручні ліжка для дому", new ArrayList<>(Arrays.asList(
            new Product("Pollen", "Металеве ліжко білого кольору. Розміри: ширина: 95 см, довжина: 205 см, висота: 36 см", "Швеція", 5, 4000),
            new Product("Saline", "Ліжко вироблене з масиву ялини. Розміри: ширина: 148 см, довжина: 208 см, висота: 50 см", "Польща", 7, 5600),
            new Product("Kongsberg", "Дерев'яне ліжко з тканинною оббивкою. Розміри: ширина: 176 см, висота: 218 см, висота: 101 см", "Швеція", 10, 12999)
    )));
    private ProductsGroup chairs = new ProductsGroup("Стільці", "Якісні обідні стільці", new ArrayList<>(Arrays.asList(
        new Product("Jonstrup","Стілець з каркасом із сталі, оббитий штучною шкірою","Польща", 12, 1250),
        new Product("Trusty","Стілець, виготовлений з масиву сосни","Польща",15,1750),
        new Product("Kooked","Стілець із основою виготовленою з фанери та сталі, оббитий тканиною","Швеція",20,1650)
    )));

    private ProductsGroup cupboards = new ProductsGroup("Шафи","Ергономічне зберігання - шафа у вашій оселі", new ArrayList<>(Arrays.asList(
        new Product("Appendage","Шафа білого кольору з меламіну та ДСП, ширина: 49 см, висота: 176 см, глибина: 50 см","Польща",10,3500),
        new Product("Jens-lev","Шафа з дубу, ширина: 96 см, висота: 176 см, глибина: 50 см","Швеція",9,6500),
        new Product("Leveller","Шафа виготовлена з ХДФ та меламіну. 4-дверна, із 3 шухлядами. Начиння шафи: 2 рейки для вішалок, 4 полиці. Розміри: 200х59 см, вис. 200 см","Швеція",13,18999)
    )));

    Factory(){
        this.productsGroups = new ArrayList<>();
        this.productsGroups.add(beds);
        this.productsGroups.add(chairs);
        this.productsGroups.add(cupboards);
        this.numberOfProductsGroups = productsGroups.size();
        updateStock();
    }

    public static void updateStock(){
        numberOfProducts=0;
        numberOfProductsGroups=0;
        //int nProducts = 0;
        for (ProductsGroup pg: productsGroups) {
            numberOfProducts += pg.getNumberOfProducts();
            numberOfProductsGroups++;
        }

        allProducts=new ArrayList<>(numberOfProducts);
        for (ProductsGroup pg: productsGroups) {
            ArrayList<Product> products = pg.getProducts();
            for (int i = 0; i<products.size(); i++) {
                allProducts.add(products.get(i));
            }
            //nProducts+=pg.getNumberOfProducts();

        }
    }


    /**
     * Method that adds a new products group to the factory
     * @param name name of the products group
     * @param description description of the products group
     * @return message to be displayed in a dialog window after finishing the operation
     */
    public boolean addProductsGroup(String name, String description){
        if (!productsGroups.contains(findProductsGroup(name))) {
            productsGroups.add(new ProductsGroup(name, description));
            updateStock();
            System.out.println("Групу товарів "+name+" додано!\n");
            return true;
        } else {
            System.out.println("Група товарів "+name+" вже існує на складі!\n");
            return false;
        }
    }

    /**
     * Method that edits a products group (name\description)
     * @param name name of the products group (case-insensitive)
     * @param newData new value of property to change
     * @param mode "name", "description" - property that will be reset with newData
     * @return message to be displayed in a dialog window after finishing the operation
     */
    public String editProductsGroup(String name, String newData, String mode){
        if (productsGroups.contains(findProductsGroup(name))) {
            ProductsGroup group = findProductsGroup(name);
            if (mode.equalsIgnoreCase("name")){
                group.setName(newData);
                for (Product p:group.getProducts()) {
                    p.setGroup(name);
                }
            } else if (mode.equalsIgnoreCase("description")) {
                group.setDescription(newData);
            } else {
                return "Invalid argument!\n";
            }

            updateStock();
            System.out.println("Групу товарів "+name+" відредаговано!\n");
            return "Групу товарів "+name+" відредаговано!\n";
        } else {
            System.out.println("Група товарів "+name+" не існує на складі!\n");
            return "Група товарів "+name+" не існує на складі!\n";
        }
    }

    /**
     * Method that deletes a products group (and its products)
     * @param name name of the products group
     * @return message to be displayed in a dialog window after finishing the operation
     */
    public String deleteProductsGroup(String name){
        if (productsGroups.contains(findProductsGroup(name))) {
            productsGroups.remove(findProductsGroup(name));
            updateStock();
            System.out.println("Групу товарів "+name+" видалено!\n");
            return "Групу товарів "+name+" видалено!\n";
        } else {
            System.out.println("Група товарів "+name+" не існує на складі!\n");
            return "Група товарів "+name+" не існує на складі!\n";
        }
    }

    /**
     * Method that adds a new product to a group and factory
     * @param groupName name of group to which the product is to be added
     * @param name name of the product
     * @param description description of the product
     * @param producer producer of the product
     * @param quantity quantity of the product
     * @param price price of the product
     * @return message to be displayed in a dialog window after finishing the operation
     */
    public String addProduct(String groupName, String name, String description, String producer, int quantity, int price) {
        ProductsGroup pg = findProductsGroup(groupName);
        if (pg!=null){
            if (!allProducts.contains(findProduct(name))) {
                pg.addProduct(name, description, producer, quantity, price);
                updateStock();
                return "Товар "+name+" додано до групи товарів "+groupName+"\n";
            } else {
                System.out.println("Товар "+name+" вже існує на складі!\n");
                return "Товар "+name+" вже існує на складі!\n";
            }
        } else {
            System.out.print("Групи товарів "+groupName+" немає на складі.\n");
            return "Групи товарів "+groupName+" немає на складі.\n";
        }

    }

    /**
     * Method that edits a product (name\description\producer\price\group)
     * @param name name of the product (case-insensitive)
     * @param newData new value of property to change
     * @param mode "name", "description", "producer", "price", "group" - property that will be reset with newData
     * @return message to be displayed in a dialog window after finishing the operation
     */
    public String editProduct(String name, String newData, String mode) {
            if (allProducts.contains(findProduct(name))) {
                Product product = findProduct(name);
                if (mode.equalsIgnoreCase("name")) {
                    product.setName(newData);
                } else if (mode.equalsIgnoreCase("description")) {
                    product.setDescription(newData);
                } else if (mode.equalsIgnoreCase("producer")) {
                    product.setProducer(newData);
                } else if (mode.equalsIgnoreCase("price")) {
                    product.setPrice(Integer.parseInt(newData));
                }  else if (mode.equalsIgnoreCase("group")) {
                    if (productsGroups.contains(findProductsGroup(newData))) {
                        deleteProduct(name);
                        addProduct(newData, product.getName(), product.getDescription(), product.getProducer(), product.getQuantity(), product.getPrice());
                        //product.setGroup(newData);
                    } else{
                        System.out.println("Група товарів "+newData+" не існує на складі! Переміщення неможливе\n");
                        return "Група товарів "+newData+" не існує на складі! Переміщення неможливе\n";
                    }
                }else {
                    return "Invalid argument!\n";
                }

                updateStock();
                System.out.println("Товар " + name + " відредаговано!\n");
                return "Товар " + name + " відредаговано!\n";
            } else {
                System.out.println("Товар "+name+" не існує на складі!\n");
                return "Товар "+name+" не існує на складі!\n";
            }
        }
    /**
     * Method that edits product's price
     * @param name name of the product (case-insensitive)
     * @param newData new value of property to change
     * @return message to be displayed in a dialog window after finishing the operation
     */
    public String editProduct(String name, int newData) {
        if (allProducts.contains(findProduct(name))) {
            Product product = findProduct(name);
            product.setPrice(newData);

            updateStock();
            System.out.println("Товар " + name + " відредаговано!\n");
            return "Товар " + name + " відредаговано!\n";
        } else {
            System.out.println("Товар "+name+" не існує на складі!\n");
            return "Товар "+name+" не існує на складі!\n";
        }
    }

    /**
     * Method that deletes a product (from its group and factory)
     * @param name name of the product (case-insensitive)
     * @return message to be displayed in a dialog window after finishing the operation
     */
    public String deleteProduct(String name){
        if (allProducts.contains(findProduct(name))){
            ProductsGroup group = findProductsGroup(findProduct(name).getGroup());
            ArrayList<Product> products = group.getProducts();

            products.remove(findProduct(name));
            group.setNumberOfProducts(products.size());
            updateStock();

            System.out.println("Товар " + name + " видалено!\n");
            return "Товар " + name + " видалено!\n";
        } else {
            System.out.println("Товар "+name+" не існує на складі!\n");
            return "Товар "+name+" не існує на складі!\n";
        }
    }


    public static Product findProduct(String name) {
        Product product = null;
        for (Product pr:allProducts) {
            if (pr.getName().equalsIgnoreCase(name)){
                product = pr;
                //System.out.println(product);
            }
        }
        //System.out.println("no matches found");
        return product;

    }
    public static ArrayList<Product> findProduct(String search, String mode) {
        ArrayList<Product> products = new ArrayList<>();
        for (Product pr:allProducts) {
            if (pr.getGroup().equalsIgnoreCase(search) && mode.equalsIgnoreCase("group") || pr.getProducer().equalsIgnoreCase(search) && mode.equalsIgnoreCase("producer")){
                products.add(pr);
                //System.out.println(pr);
            }
        }
        //System.out.println("no matches found");
        return products;

    }
    public ArrayList<Product> findProduct(int search, String mode) {
        ArrayList<Product> products = new ArrayList<>();
        for (Product pr:allProducts) {
            if (pr.getPrice()==search && mode.equalsIgnoreCase("price") || pr.getQuantity()==search && mode.equalsIgnoreCase("quantity")){
                products.add(pr);
                //System.out.println(pr);
            }
        }
        //System.out.println("no matches found");
        return products;

    }
    public ProductsGroup findProductsGroup(String name) {
        ProductsGroup group = null;
        for (ProductsGroup pg:productsGroups) {
            if (pg.getName().equalsIgnoreCase(name)) {
                group = pg;
                break;
            }
        }
        return group;
    }

    /**
     * Method that returns String with data on a group
     * @param groupName name of the products group
     * @return String with group's data
     */
    public boolean showAllProductsByGroup(String groupName) {
        ProductsGroup pg = findProductsGroup(groupName);
        if (pg!=null) {
            String s="Всі товари групи товарів "+groupName+": \n";
            for (int i=0; i<pg.getProducts().size();i++){
                s+="  "+(i+1)+") "+pg.getProducts().get(i)+"\n";
            }
//            System.out.println("Інформація по групі товарів "+groupName+": ");
//            System.out.println(pg);
            System.out.println(s);
            return true;
        } else {
            System.out.print("Групи товарів "+groupName+" немає на складі.\n");
            return false;
        }
    }

    /**
     * Method that returns the cost of all the products on factory
     * @return String with message of all products cost
     */
    public String showAllProductsCost() {
        int totalCost = 0;
        for (ProductsGroup pg:productsGroups){
            for (Product p:pg.getProducts()) {
                totalCost+=(p.getQuantity()*p.getPrice());
            }
        }
        System.out.println("Загальна вартість товару на складі становить "+totalCost+"грн\n");
        return "Загальна вартість товару на складі становить "+totalCost+"грн\n";
    }

    /**
     * Method that returns the cost of all the products among a group
     * @param groupName name of the products group
     * @return String with message of all group's products cost
     */
    public String showAllProductsCostByGroup(String groupName) {
        ProductsGroup pg = findProductsGroup(groupName);
        if (pg!=null) {
            int totalCost = 0;
            for (Product p:pg.getProducts()) {
                totalCost+=(p.getQuantity()*p.getPrice());
            }
            System.out.println("Загальна вартість товару групи товарів "+pg.getName()+" становить "+totalCost+" грн\n");
            return "Загальна вартість товару групи товарів "+pg.getName()+" становить "+totalCost+" грн\n";
        } else {
            System.out.print("Групи товарів "+groupName+" немає на складі.\n");
            return "Групи товарів "+groupName+" немає на складі.\n";
        }

    }
    /**
     * Method that adds to factory certain amount of product and returns String with message about it
     * @param name name of the product
     * @param amount amount of product that was received
     * @return message about operation
     */
    public String addMoreOfProduct(String name, int amount){
        if (allProducts.contains(findProduct(name))){
            Product product = findProduct(name);
            product.setQuantity(product.getQuantity()+amount);
            updateStock();

            System.out.println("Прийшло на склад товару " + name + " - "+amount+" штук.\n");
            return "Прийшло на склад товару " + name + " - "+amount+" штук.\n";
        } else {
            System.out.println("Товар "+name+" не існує на складі!\n");
            return "Товар "+name+" не існує на складі!\n";
        }
    }

    /**
     * Method that sells certain amount of product and returns String with message about it
     * @param name name of the product
     * @param amount amount of product that was sold
     * @return message about operation
     */
    public String soldProduct(String name, int amount){
        if (allProducts.contains(findProduct(name))){
            Product product = findProduct(name);
            if (product.getQuantity()>=amount) {
                product.setQuantity(product.getQuantity() - amount);
                updateStock();

                System.out.println("Продали товару " + name + " - " + amount + " штук.\n");
                return "Продали товару " + name + " - " + amount + " штук.\n";
            } else {
                System.out.println("Неможливо продати " + amount + " штук товару " + name + ". Всього на складі - " + product.getQuantity() + "\n");
                return "Неможливо продати " + amount + " штук товару " + name + ". Всього на складі - " + product.getQuantity() + "\n";
            }
        } else {
            System.out.println("Товар "+name+" не існує на складі!\n");
            return "Товар "+name+" не існує на складі!\n";
        }
    }
    private boolean contains(ArrayList<ProductsGroup> arr, String item){
        boolean contains = false;
        for (ProductsGroup i:arr) if (i.getName().equalsIgnoreCase(item)) {
            contains = true;
            break;
        }
        return contains;
    }
    private boolean contains(ArrayList<Product> arr, int item, String mode){
        boolean contains = false;
        for (Product i:arr) {
            if (i.getPrice()==item && mode.equalsIgnoreCase("price") || i.getQuantity()==item && mode.equalsIgnoreCase("quantity")) {
                contains = true;
                break;
            }
        }
        return contains;
    }
    private boolean contains(ArrayList<Product> arr, String item, String mode){
        boolean contains = false;
        for (Product i:arr) {
            if (i.getName().equalsIgnoreCase(item) && mode.equalsIgnoreCase("name") || i.getProducer().equalsIgnoreCase(item) && mode.equalsIgnoreCase("producer")) {
                contains = true;
                break;
            }
        }
        return contains;
    }
    public String normalizeCase(String string) {
        String res="";
        char ch=string.charAt(0);
        if (ch>='а' && ch<='я') {
            ch = (char) (ch-'а'+'А');
        } else if(ch=='і' || ch=='ї'  || ch=='є'){
            ch = (char) (ch-'є'+'Є');
        }
        res+=ch;
        for (int i=1; i<string.length(); i++) {
            if (string.charAt(i-1)==' ' && (string.charAt(i)>='а' && string.charAt(i)<='я')){
                res+=(char)(string.charAt(i)-'а'+'А');
            } else if (string.charAt(i-1)==' ' && (string.charAt(i)=='і' || string.charAt(i)=='ї'  || string.charAt(i)=='є')){
                res+=(char)(string.charAt(i)-'є'+'Є');
            }else {
                res+=string.charAt(i);
            }

        }
        return res;
    }


    public String toString(){
        String pgString="";
        for (int i = 0; i<numberOfProductsGroups; i++){
            pgString+=(i+1)+". "+ productsGroups.get(i)+"\n";
        }
        String ending="а";
        if (numberOfProductsGroups %10>1 && numberOfProductsGroups %10<5 && !(numberOfProductsGroups >=12 && numberOfProductsGroups <=14)) ending="и";
        else if ((numberOfProductsGroups >=5 && numberOfProductsGroups %10!=1) || numberOfProductsGroups ==11) ending="";
        if (numberOfProductsGroups >0)
            return "На складі є "+
                    numberOfProductsGroups +" груп"+ending+" товарів. А саме: \n"+pgString;
        else return  "На складі поки нема товарів";
    }
}
