import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Клас Factory.java, що містить основну інформацію про склад та методи для роботи
 * з ним (додавання, редагування, видалення груп товарів та самих товарів та ін.)
 */
public class Factory {
    private static ArrayList<ProductsGroup> productsGroups;
    private static ArrayList<Product> allProducts;
    private static int numberOfProductsGroups = 0;
    private static int numberOfProducts = 0;

    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public static ArrayList<ProductsGroup> getProductsGroups() {
        return productsGroups;
    }

    public static int getNumberOfProductsGroups() {
        return numberOfProductsGroups;
    }

    public static int getNumberOfProducts() {
        return numberOfProducts;
    }

    private ProductsGroup beds = new ProductsGroup("Ліжка", "Зручні ліжка для дому", new ArrayList<>(Arrays.asList(
            new Product("Pollen", "Металеве ліжко білого кольору. Розміри: ширина: 95 см, довжина: 205 см, висота: 36 см", "Швеція", 5, 4000),
            new Product("Saline", "Ліжко вироблене з масиву ялини. Розміри: ширина: 148 см, довжина: 208 см, висота: 50 см", "Польща", 7, 5600),
            new Product("Kongsberg", "Дерев'яне ліжко з тканинною оббивкою. Розміри: ширина: 176 см, висота: 218 см, висота: 101 см", "Швеція", 10, 12999),
            new Product("Markskel","Дерев'яне ліжко з масиву дуба. Розміри: ширина: 140 см, висота: 200 см, висота: 100 см", "Швеція", 9, 12000),
            new Product("Limfjorden","Дерев'яне ліжко зроблене з масиву ялини. Розміри: ширина: 90 см, висота: 200 см, висота: 90 см", "Швеція", 15, 7500)
    )));
    private ProductsGroup chairs = new ProductsGroup("Стільці", "Якісні обідні стільці", new ArrayList<>(Arrays.asList(
        new Product("Jonstrup","Стілець з каркасом із сталі, оббитий штучною шкірою","Польща", 12, 1250),
        new Product("Trusty","Стілець, виготовлений з масиву сосни","Польща",15,1750),
        new Product("Kooked","Стілець із основою виготовленою з фанери та сталі, оббитий тканиною","Швеція",20,1650),
        new Product("Ejby","Білий стілець із масиву сосни","Швеція",15,1850),
        new Product("Kooked","Стілець із дубу","Польща",25,2500)
    )));

    private ProductsGroup cupboards = new ProductsGroup("Шафи","Ергономічне зберігання - шафа у вашій оселі", new ArrayList<>(Arrays.asList(
        new Product("Appendage","Шафа білого кольору з меламіну та ДСП. Ширина: 49 см, висота: 176 см, глибина: 50 см","Польща",10,3500),
        new Product("Jens-lev","Шафа з дубу. Ширина: 96 см, висота: 176 см, глибина: 50 см","Швеція",9,6500),
        new Product("Leveller","Шафа виготовлена з ХДФ та меламіну. 4-дверна, із 3 шухлядами. Начиння шафи: 2 рейки для вішалок, 4 полиці. Ширина: 200 см, глибина: 59 см, висота 200 см","Швеція",13,18999),
        new Product("Evetofte","Шафа з дубу, ширина: 143 см, висота: 220 см, глибина: 60 см","Швеція",9,11000),
        new Product("Odda","Ламінат. З розсувними дверима. 2-дверна, 1 рейка для вішалок. Ширина: 145 см, глибина: 61 см, висота: 216 см","Україна",12,10000)
    )));

    Factory(){
        this.productsGroups = new ArrayList<>();
        this.productsGroups.add(beds);
        this.productsGroups.add(chairs);
        this.productsGroups.add(cupboards);
        this.numberOfProductsGroups = productsGroups.size();
        updateStock();
    }

    /**
     * Метод, за допомогою якого інформація по складу оновлюється після кожної внесеної зміни
     */
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
            try {
                FileWriter writer = new FileWriter("Factory.txt");
                writer.write(toStr());
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Метод додавання нової групи товарів до складу
     * @param name назва групи товарів
     * @param description опис групи
     * @return повідомлення, що відображатиметься у діалоговому вікні після закінчення операції
     */
    public String addProductsGroup(String name, String description){
        if (!productsGroups.contains(findProductsGroup(name))) {
            productsGroups.add(new ProductsGroup(name, description));
            updateStock();
            System.out.println("Групу товарів "+name+" додано!\n");
            return "Групу товарів "+name+" додано!\n";
        } else {
            System.out.println("Група товарів "+name+" вже існує на складі! Групу не додано\n");
            return "Група товарів "+name+" вже існує на складі! Групу не додано\n";
        }
    }

    /**
     * Метод редагування групи товарів (назва/опис)
     * @param name назва групи товарів (case-insensitive)
     * @param newData нова інформація про групу
     * @param mode "назва", "опис" - значення, які будуть відредаговані
     * @return повідомлення, що відображатиметься у діалоговому вікні після закінчення операції
     */
    public String editProductsGroup(String name, String newData, String mode){
        if (productsGroups.contains(findProductsGroup(name))) {
            ProductsGroup group = findProductsGroup(name);
            if (mode.equalsIgnoreCase("name")){
                if (!productsGroups.contains(findProductsGroup(newData))) {
                    group.setName(newData);
                    for (Product p:group.getProducts()) {
                        p.setGroup(newData);
                    }
                } else {
                    return "Група товарів з назвою "+newData+" вже існує на складі! Неможливо застосувати зміну";
                }


            } else if (mode.equalsIgnoreCase("description")) {
                group.setDescription(newData);
            } else {
                return "Invalid argument!\n";
            }
            System.out.println(allProducts);
            updateStock();
            System.out.println("Групу товарів "+name+" відредаговано!\n");
            return "Групу товарів "+name+" відредаговано!\n";
        } else {
            System.out.println("Група товарів "+name+" не існує на складі!\n");
            return "Група товарів "+name+" не існує на складі!\n";
        }
    }

    /**
     * Метод видалення групи товарів та самих товарів у ній
     * @param name назва групи товарів
     * @return повідомлення, що відображатиметься у діалоговому вікні після закінчення операції
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
     * Метод додавання нового продукту на склад
     * @param groupName назва групи товарів, до якої треба додати товар
     * @param name назва товару
     * @param description опис товару
     * @param producer виробник
     * @param quantity кількість товару
     * @param price ціна товару
     * @return повідомлення, що відображатиметься у діалоговому вікні після закінчення операції
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
     * Метод редагування продукту (назва\опис\виробник\ціна\група)
     * @param name назва товару (case-insensitive)
     * @param newData нова інформація про товар
     * @param mode "назва", "опис", "виробник", "ціна", "група" - значення, які будуть відредаговані
     * @return повідомлення, що відображатиметься у діалоговому вікні після закінчення операції
     */
    public String editProduct(String name, String newData, String mode) {
            if (allProducts.contains(findProduct(name))) {
                Product product = findProduct(name);
                if (mode.equalsIgnoreCase("name")) {
                    if (!allProducts.contains(findProduct(newData))) {
                        product.setName(newData);
                    } else {
                        return "Товар з назвою "+newData+" вже існує на складі! Неможливо застосувати зміну";
                    }
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
     * Метод редагування назви продукту
     * @param name назва товару (case-insensitive)
     * @param newData нова назва товару
     * @return повідомлення, що відображатиметься у діалоговому вікні після закінчення операції
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
     * Метод видалення продукту (з групи товарів та складу загалом)
     * @param name назва товару (case-insensitive)
     * @return повідомлення, що відображатиметься у діалоговому вікні після закінчення операції
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

    /**
     * Метод знаходження товару за його назвою
     * @param name назва продукту
     * @return назва продукту
     */
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
            if (pr.getName().equalsIgnoreCase(search)&&mode.equalsIgnoreCase("name")||pr.getGroup().equalsIgnoreCase(search) && mode.equalsIgnoreCase("group") || pr.getProducer().equalsIgnoreCase(search) && mode.equalsIgnoreCase("producer")){
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

    /**
     * Метод знаходження групи товарів за назвою
     * @param name назва групи товарів
     * @return назва групи
     */
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
     * Метод, що повертає інформацію про всі продукти на складі
     * @return масив стрічок з інформацією про кожний продукт
     */
    public ArrayList<String> showAllProducts() {
        if (!allProducts.isEmpty()) {
            //String s="Всі товари групи товарів "+groupName+": \n";
            ArrayList<String> products = new ArrayList<>();
            for (int i=0; i<numberOfProducts;i++){
                products.add(""+(i+1)+") "+allProducts.get(i).toStringUI()+"<br><b>Належить до групи:</b> "+allProducts.get(i).getGroup());
            }
//            System.out.println("Інформація по групі товарів "+groupName+": ");
//            System.out.println(pg);
            //System.out.println(products);
            return products;
        } else {
            System.out.print("Склад пустий.\n");
            return null;
        }
    }

    /**
     * Метод, що повертає інформацію про всі товари у конкретній групі товарів
     * @param groupName назва групи товарів
     * @return масив стрічок з інформацією по кожній групі товарів
     */
    public ArrayList<String> showAllProductsByGroup(String groupName) {
        ProductsGroup pg = findProductsGroup(groupName);
        if (pg!=null) {
            //String s="Всі товари групи товарів "+groupName+": \n";
            ArrayList<String> products = new ArrayList<>();
            for (int i=0; i<pg.getProducts().size();i++){
               products.add(""+(i+1)+") "+pg.getProducts().get(i).toStringUI());
            }
//            System.out.println("Інформація по групі товарів "+groupName+": ");
//            System.out.println(pg);
            //System.out.println(products);
            return products;
        } else {
            System.out.print("Групи товарів "+groupName+" немає на складі.\n");
            return null;
        }
    }

    /**
     * Метод, що повертає загальну вартість усіх продуктів на складі
     * @return String з повідомленням про загальну вартість товарів
     */
    public String showAllProductsCost() {
        int totalCost = 0;
        for (ProductsGroup pg:productsGroups){
            for (Product p:pg.getProducts()) {
                totalCost+=(p.getQuantity()*p.getPrice());
            }
        }
        System.out.println("Загальна вартість товару на складі становить "+totalCost+"грн\n");
        return ""+totalCost+"грн\n";
    }

    /**
     * Метод, що повертає вартість усіх продуктів у межах групи
     * @param groupName назва групи товарів
     * @return String з повідомленням про вартість товарів у групі
     */
    public String showAllProductsCostByGroup(String groupName) {
        ProductsGroup pg = findProductsGroup(groupName);
        if (pg!=null) {
            int totalCost = 0;
            for (Product p:pg.getProducts()) {
                totalCost+=(p.getQuantity()*p.getPrice());
            }
            System.out.println("Загальна вартість товару групи товарів "+pg.getName()+" становить "+totalCost+" грн\n");
            return ""+totalCost+" грн\n";
        } else {
            System.out.print("Групи товарів "+groupName+" немає на складі.\n");
            return "Групи товарів "+groupName+" немає на складі.\n";
        }

    }
    /**
     * Метод додавання певної кількості товару до складу.
     * @param name назва товару
     * @param amount кількість товару, яку було отримано
     * @return повідомлення про операцію
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
     * Метод продажу товару зі складу.
     * @param name назва товару
     * @param amount кількість проданого товару
     * @return повідомлення про операцію (успішна/не успішна)
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

    /**
     * Метод виводу інформації по складу
     * @return інформація по складу
     */
    public static String toStr(){
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
