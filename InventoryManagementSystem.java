import java.util.Scanner;

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

public class InventoryManagementSystem{
  public static Scanner input = new Scanner(System.in);
  public static void main(String[] args){
    System.out.println("Simple Inventory Management System by Ma. Angelica Pauleen R. De Chavez of C2A\n");

    boolean exitProgram = false;

    while(!exitProgram){
      System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
      System.out.println("                                          Menu Options");
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
        case 1:
          //add item
          break;
        case 2:
          //update item
          break;
        case 3:
          //remove item
          break;
        case 4:
          //display items by category
          break;
        case 5:
          //display all items
          break;
        case 6:
          //search item
          break;
        case 7:
          //sort items
          break;
        case 8:
          //display low stock items
          break;
        case 9:
          //exit
          exitProgram = true;
          break;
      }
    }
  }
}