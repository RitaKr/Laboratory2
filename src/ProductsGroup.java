public class ProductsGroup {
  private String name;
  private String description;
  private Product[] products;
  
  public ProductsGroup(String name, String description, Product[] products) {
    this.name = name;
    this.description = description;
    this.products = products;
  }
  public ProductsGroup(String name, String description) {
    this.name = name;
    this.description = description;
    this.products = new Product[0];
  }
  
  public String getName() {
    return name;
  }
  
  public String getDescription() {
    return description;
  }
  
  public Product[] getProducts() {
    return products;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public void setProducts(Product[] products) {
    this.products = products;
  }
  
  
}