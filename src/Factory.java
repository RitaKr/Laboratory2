public class Factory {
    private static ProductsGroup[] productsGroups;
    private static Product[] allProducts;
    private static int numberOfProductsGroups = 0;
    private static int numberOfProducts = 0;
    private ProductsGroup default1 = new ProductsGroup("group1", "this is group1", new Product[] {
         new Product("product1", "this is product1", "producer", 5, 100),
         new Product("product2", "this is product2", "producer", 4, 10)
    });
    private ProductsGroup default2 = new ProductsGroup("group2", "this is group2", new Product[] {

    });

    Factory(){
        this.productsGroups = new ProductsGroup[]{default1, default2 };
        this.numberOfProductsGroups = productsGroups.length;
        updateStock();
    }

    public static void updateStock(){
        numberOfProducts=0;
        numberOfProductsGroups=0;
        int nProducts = 0;
        for (ProductsGroup pg: productsGroups) {
            numberOfProducts += pg.getNumberOfProducts();
            numberOfProductsGroups++;
        }

        allProducts=new Product[numberOfProducts];
        for (ProductsGroup pg: productsGroups) {
            Product[] products = pg.getProducts();
            for (int i = 0; i<products.length; i++) {
                allProducts[i+nProducts]=products[i];
            }
            nProducts+=pg.getNumberOfProducts();

        }
    }
    public boolean addProductsGroup(String name, String description){
        if (!contains(productsGroups, name)) {
            extendProductsGroupArray(numberOfProductsGroups + 1);
            //System.out.println(numberOfFaculties);
            productsGroups[numberOfProductsGroups - 1] = new ProductsGroup(name, description);
            return true;
        } else {
            System.out.println("Група товарів "+name+" вже існує на складі!\n");
            return false;
        }
    }
    public boolean addProduct(String groupName, String name, String description, String producer, int quantity, int price) {
        ProductsGroup pg = findProductsGroup(groupName);
        if (pg!=null){
            if (!contains(allProducts, name, "name")) {
                pg.addProduct(name, description, producer, quantity, price);
                return true;
            } else {
                System.out.println("Товар "+name+" вже існує на складі!\n");
                return false;
            }
        } else {
            System.out.print("Групи товарів "+groupName+" немає на складі.\n");
            return false;
        }

    }
    protected void extendProductsGroupArray(int n){
        ProductsGroup[] extendedArr = new ProductsGroup[n];
        for (int i = 0; i< numberOfProductsGroups; i++) {
            extendedArr[i] = productsGroups[i];
        }
        numberOfProductsGroups = n;
        productsGroups = extendedArr;
        //System.out.println(Arrays.toString(faculties));
    }
    public String toString(){
        String pgString="";
        for (int i = 0; i< numberOfProductsGroups; i++){
            pgString+=(i+1)+". "+ productsGroups[i]+"\n";
        }
        String ending="а";
        if (numberOfProductsGroups %10>1 && numberOfProductsGroups %10<5 && !(numberOfProductsGroups >=12 && numberOfProductsGroups <=14)) ending="и";
        else if ((numberOfProductsGroups >=5 && numberOfProductsGroups %10!=1) || numberOfProductsGroups ==11) ending="";
        if (numberOfProductsGroups >0)
            return "На складі є "+
                    numberOfProductsGroups +" груп"+ending+" товарів. А саме: \n"+pgString;
        else return  "На складі поки нема товарів";
    }
    public static Product findProduct(String search, String mode) {
        Product product = null;
        for (Product pr:allProducts) {
            if (pr.getName().equalsIgnoreCase(search) && mode.equalsIgnoreCase("name") || pr.getProducer().equalsIgnoreCase(search) && mode.equalsIgnoreCase("description")){
                product = pr;
                System.out.println(product);
            }
        }
        //System.out.println("no matches found");
        return product;

    }
    public Product findProduct(int search, String mode) {
        Product product = null;
        for (Product pr:allProducts) {
            if (pr.getPrice()==search && mode.equalsIgnoreCase("price") || pr.getQuantity()==search && mode.equalsIgnoreCase("quantity")){
                product = pr;
                System.out.println(product);
            }
        }
        //System.out.println("no matches found");
        return product;

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
    public boolean showAllProductsByGroup(String groupName) {
        ProductsGroup pg = findProductsGroup(groupName);
        if (pg!=null) {
            System.out.println("Інформація по групі товарів "+groupName+": ");
            System.out.println(pg);
            return true;
        } else {
            System.out.print("Групи товарів "+groupName+" немає на складі.\n");
            return false;
        }
    }
    public void showAllProductsCost() {
        int totalCost = 0;
        for (ProductsGroup pg:productsGroups){
            for (Product p:pg.getProducts()) {
                totalCost+=(p.getQuantity()*p.getPrice());
            }
        }
        System.out.println("Загальна вартість товару на складі становить "+totalCost+"грн\n");
    }
    public boolean showAllProductsCostByGroup(String groupName) {
        ProductsGroup pg = findProductsGroup(groupName);
        if (pg!=null) {
            int totalCost = 0;
            for (Product p:pg.getProducts()) {
                totalCost+=(p.getQuantity()*p.getPrice());
            }
            System.out.println("Загальна вартість товару групи товарів "+pg.getName()+" становить "+totalCost+"грн\n");
            return true;
        } else {
            System.out.print("Групи товарів "+groupName+" немає на складі.\n");
            return false;
        }



    }
    private boolean contains(ProductsGroup[] arr, String item){
        boolean contains = false;
        for (ProductsGroup i:arr) if (i.getName().equalsIgnoreCase(item)) {
            contains = true;
            break;
        }
        return contains;
    }
    private boolean contains(Product[] arr, int item, String mode){
        boolean contains = false;
        for (Product i:arr) {
            if (i.getPrice()==item && mode.equalsIgnoreCase("price") || i.getQuantity()==item && mode.equalsIgnoreCase("quantity")) {
                contains = true;
                break;
            }
        }
        return contains;
    }
    private boolean contains(Product[] arr, String item, String mode){
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
}
