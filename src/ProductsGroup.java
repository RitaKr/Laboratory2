import java.util.ArrayList;

/**
 * Клас ProductsGroup.java, який містить опис групи товарів, а саме: назва групи, її опис та масив продуктів, що у ній знаходяться
 */
public class ProductsGroup {
  private String name;
  private String description;
  private ArrayList<Product> products;
  private int numberOfProducts = 0;

  public ProductsGroup(String name, String description, ArrayList<Product> products) {
    this.name = name;
    this.description = description;
    this.products = products;
    numberOfProducts = products.size();
    for (Product p:products) {
      p.setGroup(name);
    }
  }
  public ProductsGroup(String name, String description) {
    this.name = name;
    this.description = description;
    this.products = new ArrayList<>();
    for (Product p:products) {
      p.setGroup(name);
    }

  }
  
  public String getName() {
    return name;
  }
  
  public String getDescription() {
    return description;
  }
  
  public ArrayList<Product> getProducts() {
    return products;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public void setProducts(ArrayList<Product> products) {
    this.products = products;
  }
  public int getNumberOfProducts() {
    return numberOfProducts;
  }

  public void setNumberOfProducts(int numberOfProducts) {
    this.numberOfProducts = numberOfProducts;
  }
  public void addProduct(String productName, String description, String producer, int quantity, int price){
    //System.out.println(numberOfFaculties);
    products.add(new Product(productName, description, producer, quantity, price, name));
    numberOfProducts=products.size();
    Factory.updateStock();


  }
  /**
   * Метод, що повертає інформацію про групу товарів
   * @return масив стрічок з інформацією про групу товарів
   */
  public ArrayList<String> showAllProductsByGroup() {
      ArrayList<String> pr = new ArrayList<>();
      for (int i=0; i<numberOfProducts;i++){
        pr.add(""+(i+1)+") "+products.get(i).toStringUI());
      }
//            System.out.println("Інформація по групі товарів "+groupName+": ");
//            System.out.println(pg);
      //System.out.println(products);
      return pr;

  }
  /**
   * Метод, що повертає загальну вартість товарів у конкретній групі
   * @return стрічка з повідомленням про загальну вартість
   */
  public String getAllProductsCostByGroup() {

      int totalCost = 0;
      for (Product p:products) {
        totalCost+=(p.getQuantity()*p.getPrice());
      }
      System.out.println("Загальна вартість товару групи товарів "+name+" становить "+totalCost+" грн\n");
      return ""+totalCost+" грн\n";


  }

  /**
   * Метод виводу інформації по групі товарів
   * @return інформація по групі товарів
   */
  public String toString(){
    String productsString="";
    for (int i=0; i<numberOfProducts;i++){
      productsString+="  "+(i+1)+") "+products.get(i)+"\n";
    }
    String ending="";
    if (numberOfProducts%10>1 && numberOfProducts%10<5 && !(numberOfProducts>=12 && numberOfProducts<=14)) ending="и";
    else if ((numberOfProducts>=5 && numberOfProducts%10!=1) || numberOfProducts==11) ending="ів";
    if (numberOfProducts>0) return "Назва: "+name+",\nОпис: "+description+"\nВ наявності "+numberOfProducts+" товар"+ending+": \n"+ productsString;
    else  return "Назва: "+name+",\nОпис: "+description+"\nВ наявності наразі нема жодного товару\n";
  }

}