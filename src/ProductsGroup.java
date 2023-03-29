import java.util.ArrayList;

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