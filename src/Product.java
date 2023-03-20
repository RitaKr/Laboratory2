public class Product {
  private String name;
  private String description;
  private String producer;
  private int quantity;
  private int price;
  
  public Product(String name, String description, String producer, int quantity, int price) {
    this.name = name;
    this.description = description;
    this.producer = producer;
    this.quantity = quantity;
    this.price = price;
  }
  
  public String getName() {
    return name;
  }
  
  public String getDescription() {
    return description;
  }
  
  public String getProducer() {
    return producer;
  }
  
  public int getQuantity() {
    return quantity;
  }
    
  public int getPrice() {
    return price;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public void setProducer(String producer) {
    this.producer = producer;
  }
  
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
    
  public void setPrice(int price) {
    this.price = price;
  }


  public String toString() {
    return "Назва: " + name + "," +
            "\n  Опис: "+description+"," +
            "\n  Виробник: "+producer+"," +
            "\n  Кількість: "+quantity+","+
            "\n  Ціна за одиницю: "+price+"грн";
  }
}