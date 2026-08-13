import java.util.Scanner;
import java.util.ArrayList;

//abstract class
abstract class AbstractItems{
  private String id;
  private String name;
  private int qty;
  private double price;

  //constructor
  public AbstractItems(String id, String name, int qty, double price){
    this.id = id;
    this.name = name;
    this.qty = qty;
    this.price = price;
  }

  //getters
  public String getId(){
    return this.id;
  }

  public String getName(){
    return this.name;
  }

  public int getQty(){
    return this.qty;
  }

  public double getPrice(){
    return this.price;
  }

  //setters
  public void setQty(int qty){
    this.qty = qty;
  }

  public void setPrice(double price){
    this.price = price;
  }
  
}

//clothing class
class Clothing extends AbstractItems{

  //constructor
  public Clothing(String id, String name, int qty, double price){
    super(id, name, qty, price);
  }
}

//electronics class
class Electronics extends AbstractItems{

  //constructor
  public Electronics(String id, String name, int qty, double price){
    super(id, name, qty, price);
  }
}

//entertainment class
class Entertainment extends AbstractItems{

  //constructor
  public Entertainment(String id, String name, int qty, double price){
    super(id, name, qty, price);
  }

}

//inventory class
class Inventory{
  private ArrayList<AbstractItems> items;

  public Inventory(){
    items = new ArrayList<>();
  }

  public static final String[] itemCategories = {"Clothing", "Electronics", "Entertainment"};

  public boolean isCatExists(String itemCat){
    for(String item : itemCategories){
      if(item.equalsIgnoreCase(itemCat)){
        return true;
      }
    }
    return false;
  }

  public void addItem(String id, String name, int qty, double price){
    
  }
}

public class InventoryManagementSystem{
  public static Scanner input = new Scanner(System.in);
  public static String idValidation(String prompt){

  }
  public static void addItemMain(Inventory inventory){
    System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          System.out.println("                                            ADD ITEM");
          System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          
          System.out.println("\n                                          ITEM CATEGORY");
          System.out.println("                                - - - - - - - - - - - - - - - -");
          System.out.println("""

                               [a] Clothing                     [b] Electronics               [c] Entertainment
                        """);
          System.out.println("       - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"); 
          System.out.print("Enter Item Category: ");
          String itemCat = input.nextLine();

          if(inventory.isCatExists(itemCat)){
            String clothingID = idValidation("Enter User ID: ");
            String clothingName = nameValidation("Enter User Name: ");
            int clothingQty = qtyValidation("Enter Quantity: ");
            double clothingPrice = priceValidation("Enter price: ");
            
            inventory.addItem(clothingID, clothingName, clothingQty, clothingPrice);
          }else{
            System.out.println("Invalid Item Category.");
          }
  }
  public static void main(String[] args){
    System.out.println("Simple Inventory Management System by Ma. Angelica Pauleen R. De Chavez of C2A\n");

    Inventory inventory = new Inventory();

    boolean exitProgram = false;

    while(!exitProgram){
      System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
      System.out.println("                                          MENU OPTIONS");
      System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");

      System.out.println("""

                                  [1] Add Item                                     [6] Search Items
                                  [2] Update Item                                  [7] Sort Items
                                  [3] Remove Item                                  [8] Display Low Stock Items
                                  [4] Display Items by Category                    [9] Exit
                                  [5] Display All Items                
                        """);
      System.out.println("       - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

      System.out.print("Enter Menu Choice: ");
      int menu = input.nextInt();

      switch(menu){
        case 1: //add item
          addItemMain(inventory);
          break;
        case 2: //update item
          System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          System.out.println("                                           UPDATE ITEM");
          System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          break;
        case 3: //remove item
          System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          System.out.println("                                           REMOVE ITEM");
          System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          break;
        case 4: //display items by category
          System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          System.out.println("                                     DISPLAY ITEMS BY CATEGORY");
          System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          break;
        case 5: //display all items
          System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          System.out.println("                                         DISPLAY ALL ITEMS");
          System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          break;
        case 6: //search item
          System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          System.out.println("                                          SEARCH ITEM");
          System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          break;
        case 7: //sort items
          System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          System.out.println("                                           SORT ITEMS");
          System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          break;
        case 8: //display low stock items
          System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          System.out.println("                                      DISPLAY LOW STOCK ITEMS");
          System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          break;
        case 9:
          //exit
          exitProgram = true;
          break;
      }
    }
  }
}