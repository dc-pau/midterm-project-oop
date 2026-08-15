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

  //check if category exists
  public boolean isCatExists(String itemCat){
    for(String item : itemCategories){
      if(item.equalsIgnoreCase(itemCat)){
        return true;
      }
    }
    System.out.printf("\nCategory %s does not exist!", itemCat);
    return false;
  }

  //add items
  public void addItem(AbstractItems item, String id){
    //checks for duplicates using item id
    for(AbstractItems itemHolder : items){
      if(itemHolder.getId().equalsIgnoreCase(id)){
        System.out.printf("Item with %s id already exists. Please input another item.\n", id);
      }
    }
    System.out.println("Item added successfully!");
    items.add(item);
    }
  }

public class InventoryManagementSystem{
  public static Scanner input = new Scanner(System.in);
  public static int menuChecker(String prompt){
    boolean isValidMenu = false;
    int menu = 0;

    while(!isValidMenu){
      System.out.print(prompt);
      String number = input.nextLine();

      if(number.length() > 1 && !(Character.isLetterOrDigit(number.charAt(0)))){
        System.out.println("Invalid input. Number cannot start with a symbol or space.");
        continue;
      }

      if(number.length() > 1 && number.charAt(0) == '0'){
        System.out.println("Invalid input. Number cannot start with 0.");
        continue;
      }

      try{
        menu = Integer.parseInt(number);

        if(menu >= 1 && menu <= 9){
          isValidMenu = true;
        }else{
          System.out.println("Invalid input. Please enter a number between 1 to 9.");
        }
      }catch(NumberFormatException e){
        System.out.println("Invalid input. You must enter an integer number.");
      }
    }
    return menu;
  }

  public static String stringEmptyChecker(String prompt, String regex, int type){
    boolean hasInput = false;
    String str = " ";

    while(!hasInput){
      System.out.print(prompt);
      str = input.nextLine().trim().replaceAll("\\s+", " ");

      if(str.isEmpty()){
        System.out.println("You cannot leave this field empty.");
        hasInput = false;
      }else{
        hasInput = true;
      }
    }

    if(type == 1){
      str = idValidation(prompt, str, regex);
    }

    return str;
  }

  public static String idValidation(String prompt, String id, String regex){
    if(!id.matches(regex)){
      System.out.println("Invalid id format. Please use this format instead: First letter of the Category + three 0's and one unique number. (Ex. C0001)");
      id = stringEmptyChecker(prompt, regex, 1);
    }else{
      return id;
    }

    return id;
  }

  public static int qtyValidation(String prompt){
    boolean isValidQty = false;
    int qty = 0;

    while(!isValidQty){
      System.out.print(prompt);
      String qtyStr = input.nextLine();

      if(qtyStr.length() > 1 && !(Character.isLetterOrDigit(qtyStr.charAt(0)))){
        System.out.println("Invalid input. Number cannot start with a symbol or space.");
        continue;
      }

      if (qtyStr.length() > 1 && qtyStr.charAt(0) == '0') {
        System.out.println("Invalid input. Number cannot start with 0.");
        continue;
      }

      try{
        qty = Integer.parseInt(qtyStr);

        if(qty >= 1){
          isValidQty = true;
        }else{
          System.out.println("Invalid input. Please enter a number greater than 0.");
        }
      }catch(NumberFormatException e){
        System.out.println("Invalid input. You must enter an integer number.");
      }
    }
    return qty;
  }

  public static double priceValidation(String prompt){
    boolean isValidPrice = false;
    double price = 0;

    while(!isValidPrice){
      System.out.print(prompt);
      String priceStr = input.nextLine();

      if(priceStr.length() > 1 && !(Character.isLetterOrDigit(priceStr.charAt(0)))){
        System.out.println("Invalid input. Number cannot start with a symbol or space.");
        continue;
      }

      if (priceStr.length() > 1 && priceStr.charAt(0) == '0') {
        System.out.println("Invalid input. Number cannot start with 0.");
        continue;
      }

      try{
        price = Integer.parseInt(priceStr);

        if(price >= 1){
          isValidPrice = true;
        }else{
          System.out.println("Invalid input. Please enter a number greater than 0.");
        }
      }catch(NumberFormatException e){
        System.out.println("Invalid input. You must enter an integer number.");
      }
    }
    return price;
  }
  public static void addItemMain(Inventory inventory){
          System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          System.out.println("                                            ADD ITEM");
          System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
          
          System.out.println("\n                                          ITEM CATEGORY");
          System.out.println("                                - - - - - - - - - - - - - - - -");
          System.out.println("""

                                   Clothing                         Electronics                   Entertainment
                        """);
          System.out.println("       - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"); 
          String itemCat = stringEmptyChecker("Enter Item Category: ", null, 0);

          while(inventory.isCatExists(itemCat)){
            System.out.println(itemCat + " Category is found!");
            System.out.println("\nPlease enter necessary information about the item:");
            System.out.println("NOTE: For ID format: First letter of the Category (C/E) + three 0's and one unique number. (Ex. C0001)");
            String clothingID = stringEmptyChecker("\nEnter Item ID: ", "^C000\\d$", 1);
            String clothingName = stringEmptyChecker("Enter Name: ", null, 0);
            int clothingQty = qtyValidation("Enter Quantity: ");
            double clothingPrice = priceValidation("Enter Price: ");
            
            AbstractItems newAbstractItems = addAbstractItem(itemCat, clothingID, clothingName, clothingQty, clothingPrice);
            inventory.addItem(newAbstractItems, clothingID);
            break;
          }
          
          
  }

  public static AbstractItems addAbstractItem(String cat, String id, String name, int qty, double price){
    if(cat.equalsIgnoreCase("Clothing")){
      return new Clothing(id, name, qty, price);
    }else if(cat.equalsIgnoreCase("Electronics")){
      return new Electronics(id, name, qty, price);
    }else{
      return new Entertainment(id, name, qty, price);
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

      int menu = menuChecker("Enter Menu Choice: ");

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