import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;

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

  public abstract String getCategory();
}

//clothing class
class Clothing extends AbstractItems{

  //constructor
  public Clothing(String id, String name, int qty, double price){
    super(id, name, qty, price);
  }

  @Override
  public String getCategory(){
    return "Clothing";
  }
}

//electronics class
class Electronics extends AbstractItems{

  //constructor
  public Electronics(String id, String name, int qty, double price){
    super(id, name, qty, price);
  }

  @Override
  public String getCategory(){
    return "Electronics";
  }
}

//entertainment class
class Entertainment extends AbstractItems{

  //constructor
  public Entertainment(String id, String name, int qty, double price){
    super(id, name, qty, price);
  }

  @Override
  public String getCategory(){
    return "Entertainment";
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
    System.out.printf("Category %s does not exist!\n\n", itemCat);
    return false;
  }

  //check if id already exists
  public boolean idExists(String id){
    for(AbstractItems item : items){
      if(item.getId().equalsIgnoreCase(id)){
        return true;
      }
    }
    return false;
  }

  //add items
  public void addItem(AbstractItems item, String id){
    System.out.println("Item added successfully!");
    items.add(item);
    }
    
    //get item using id
    public AbstractItems getItemByID(String id){
      for(AbstractItems item : items){
        if(item.getId().equalsIgnoreCase(id)){
          return item;
        }
      }
      return null;
    }

    //remove item using id
    public AbstractItems removeItemByID(String id){
      AbstractItems item = getItemByID(id);
      items.remove(item);
      return item;
    }

    //store items by category
    public ArrayList<AbstractItems> catItems(String itemCat){
    ArrayList<AbstractItems> catItems = new ArrayList<>();

    for(AbstractItems item : items){
      if(item.getCategory().equalsIgnoreCase(itemCat)){
        catItems.add(item);
      }
    }
    return catItems;
  }

    //store items that is sorted based on the preference of the user
    public ArrayList<AbstractItems> getSortedItems(char sort, char order){
      ArrayList<AbstractItems> sortItems = new ArrayList<>(items);

      //set default and for initializing
      //sort by qty
      Comparator<AbstractItems> comparator = Comparator.comparingInt(AbstractItems::getQty);

      //sort by price
      if(sort == 'b'){
        comparator = Comparator.comparingDouble(AbstractItems::getPrice);
      }

      //sort by descending
      if(order == 'b'){
        comparator = comparator.reversed();
      }

      sortItems.sort(comparator);
      return sortItems;
    }

    //check if inventory is empty
    public boolean isEmpty(){
      return items.isEmpty();
    }

    //display everything
    public void displayAll(Inventory inventory){

      System.out.printf("\n%-20s %-20s %-20s %-20s %-20s%n", "Item ID", "Item Name", "Quantity", "Price", "Category");
      System.out.printf("%-20s %-20s %-20s %-20s %-20s%n", "-------------", "-------------", "-------------", "-------------", "-------------");

      for(AbstractItems item : items){
        String priceStr = String.format("%,.2f", item.getPrice());
        System.out.printf("%-20s %-20s %-20d %-20s %-20s%n", item.getId(), item.getName(), item.getQty(), priceStr, item.getCategory());
      }
    }

    //store low stock items
    public ArrayList<AbstractItems> getLowStockItems(){
      ArrayList<AbstractItems> lowStocks = new ArrayList<>();
      for(AbstractItems item : items){
        if(item.getQty() <= 5){
          lowStocks.add(item);
        }
      }
      return lowStocks;
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
      System.out.println("Invalid id format. Please use this format instead: First two letter of the Category (CL/EL/EN) + three 0's and three random numbers. (Ex. CL000111)");
      id = stringEmptyChecker(prompt, regex, 1);
    }else{
      return id;
    }

    return id;
  }

  public static int qtyValidation(String prompt, int min){
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

        if(qty >= min && qty <= 10000){
          isValidQty = true;
        }else{
          System.out.printf("Invalid input. Please enter a number greater than or equal to %d and less than or equal to 10000.\n", min);
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
        price = Double.parseDouble(priceStr);

        if(price >= 1 && price <= 1000000){
          isValidPrice = true;
        }else{
          System.out.println("Invalid input. Please enter a number ranging from 1 to 1000000.");
        }
      }catch(NumberFormatException e){
        System.out.println("Invalid input. You must enter an integer number.");
      }
    }
    return price;
  }

  public static char charValidation(String prompt, char option1, char option2){
        boolean isValidChar = false;
        char ans = ' ';

        while(!isValidChar){
          System.out.print(prompt);
          String txt = input.nextLine();

          if(txt.isEmpty()){
            System.out.println("Invalid input. You cannot leave this empty.");
            continue;
          }

          if(txt.length() > 1){
            System.out.println("Invalid input. Enter y or n only.");
            continue;
          }

          char char1 = Character.toLowerCase(txt.charAt(0));

          if(!((char1 == option1) || (char1 == option2))){
          System.out.println("Invalid input. Enter " + option1 + " or " + option2 + " only.");
          continue;
          }else{
            ans = char1;
            isValidChar = true;
          }
          
        }
        return ans;
    }

  public static void displayCurrentItem(String cat, String id, String name, int qty, double price){
    System.out.println("\n                        - - - - - - - - - - - - - - - - - - - - - - - -");
    System.out.println("                                         ITEM DETAILS");
    System.out.println("\n\t\t\t\t|Item Category:      " + cat); 
    System.out.println("\t\t\t\t|Item ID:            " + id); 
    System.out.println("\t\t\t\t|Item Name:          " + name); 
    System.out.println("\t\t\t\t|Item Quantity:      " + qty); 
    System.out.printf("\t\t\t\t|Item Price:         %,.2f%n", price); 
    System.out.println("                        - - - - - - - - - - - - - - - - - - - - - - - -");
  }

  public static void addItemMain(Inventory inventory){
    System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
    System.out.println("                                            ADD ITEM");
    System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
    
    System.out.println("\n                                          ITEM CATEGORY");
    System.out.println("                                - - - - - - - - - - - - - - - -");
    System.out.println("""

                              Clothing                       Electronics                   Entertainment
                  """);
    System.out.println("       - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"); 
    String itemCat = stringEmptyChecker("Enter Item Category: ", null, 0);

    while(!inventory.isCatExists(itemCat)){
      itemCat = stringEmptyChecker("Enter Item Category: ", null, 0);
    }

    System.out.println(itemCat + " Category is found!");
    System.out.println("\nPlease enter necessary information about the item:");
    System.out.println("NOTE: For ID format: First two letter of the Category (CL/EL/EN) + three 0's and three random numbers. (Ex. CL000111)");

    if(itemCat.equalsIgnoreCase("clothing")){
      addItems(inventory, itemCat, "^(?i)CL000\\d{3}$");
    }else if(itemCat.equalsIgnoreCase("electronics")){
      addItems(inventory, itemCat, "^(?i)EL000\\d{3}$");
    }else if(itemCat.equalsIgnoreCase("entertainment")){
      addItems(inventory, itemCat, "^(?i)EN000\\d{3}$");
    }    
  }

  public static void addItems(Inventory inventory, String cat, String regex){
    String ID = " ";
    String name = " ";
    int qty = 0;
    double price = 0;
    
    boolean isCorrect = false;
    while(!isCorrect){
      //check if ID exist before continuing
      ID = stringEmptyChecker("\nEnter Item ID: ", regex, 1);
      while(inventory.idExists(ID)){
        System.out.println("ID already exists. Please input another ID.");
        ID = stringEmptyChecker("\nEnter Item ID: ", regex, 1);
      }
      name = stringEmptyChecker("Enter Item Name: ", null, 0);
      qty = qtyValidation("Enter Quantity (1 - 10000): ", 1);
      price = priceValidation("Enter Price (1 - 1000000): ");

      displayCurrentItem(cat, ID, name, qty, price);

      char yOrN = charValidation("Are you sure that the information you entered is correct? (y/n): ", 'y', 'n');

      if (yOrN == 'y') {
        isCorrect = true;
      }
    }
    
    if(cat.equalsIgnoreCase("clothing")){
      inventory.addItem(new Clothing(ID, name, qty, price), ID);
    }else if(cat.equalsIgnoreCase("electronics")){
      inventory.addItem(new Electronics(ID, name, qty, price), ID);
    }else if(cat.equalsIgnoreCase("entertainment")){
      inventory.addItem(new Entertainment(ID, name, qty, price), ID);
    }
  }

  public static void updateItemMain(Inventory inventory){
    System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
    System.out.println("                                           UPDATE ITEM");
    System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");

    if(inventory.isEmpty()){
      System.out.println("No items available.");
      return;
    }

    String ID = stringEmptyChecker("\nEnter Item ID: ", null, 0);
    if(!inventory.idExists(ID)){
      System.out.println("Item with ID " + ID + " not found!");
      return;
    }



    AbstractItems item = inventory.getItemByID(ID);

    System.out.println("ID found!");
    displayCurrentItem(item.getCategory(), item.getId(), item.getName(), item.getQty(), item.getPrice());


    System.out.println("\n                                    CHOOSE WHAT TO UPDATE");
    System.out.println("                                - - - - - - - - - - - - - - - -");
    System.out.println("""

                                        [a] Quantity                         [b] Price               
                  """);
    System.out.println("       - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"); 

    char upd = charValidation("Enter chosen information to update: ", 'a', 'b');

    switch(upd){
      case 'a'://qty

        int oldQty = 0;
        int newQty = 0;
        oldQty = item.getQty();
        newQty = qtyValidation("\nEnter New Quantity (0 - 10000): ", 0);

        char yOrNQty = charValidation("Are you sure you want to update the item quantity? (y/n): ", 'y', 'n');

        if (yOrNQty == 'n') {
          System.out.println("Item quantity update cancelled.");
          return;
        }

        if(item.getQty() == newQty){
          System.out.println("Item quantity is the same as the current quantity.");
          return;
        }

        item.setQty(newQty);
        System.out.printf("Quantity of Item %s is updated from %d to %d.\n", item.getName(), oldQty, newQty);

        break;
      case 'b'://price
        double oldPrice = 0;
        double newPrice = 0;
        
          oldPrice = item.getPrice();
          newPrice = priceValidation("\nEnter New Price (1 - 1000000): ");

          char yOrNPrice = charValidation("Are you sure you want to update the item price? (y/n): ", 'y', 'n');

          if (yOrNPrice == 'n') {
            System.out.println("Item price update cancelled.");
            return;
          }
        
        if(item.getPrice() == newPrice){
          System.out.println("Item price is the same as the current price.");
          return;
        }

        item.setPrice(newPrice);
        System.out.printf("Price of Item %s is updated from %,.2f to %,.2f.\n", item.getName(), oldPrice, newPrice);
        break;
    }
  }

  public static void removeItemMain(Inventory inventory){
    System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
    System.out.println("                                           REMOVE ITEM");
    System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
    
    if(inventory.isEmpty()){
      System.out.println("No items available.");
      return;
    }

    String ID = stringEmptyChecker("\nEnter Item ID: ", null, 0);
    if(!inventory.idExists(ID)){
      System.out.println("Item with ID " + ID + " not found!");
      return;
    }

    AbstractItems item = inventory.getItemByID(ID);

    System.out.println("ID found!");
    displayCurrentItem(item.getCategory(), item.getId(), item.getName(), item.getQty(), item.getPrice());

    char rmv = charValidation("Are you sure you want to remove this item? (y/n): ", 'y', 'n');
    if (rmv == 'y') {
      inventory.removeItemByID(ID);
    System.out.println("Item " + item.getName() + " has been removed from the inventory'");
    }else{
      System.out.println("Item is not removed.");
    }
  }

  public static void displayByCatMain(Inventory inventory){
    System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
    System.out.println("                                     DISPLAY ITEMS BY CATEGORY");
    System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");

    if(inventory.isEmpty()){
      System.out.println("No items available.");
      return;
    }

    String itemCat = stringEmptyChecker("Enter Item Category (Clothing/Electronics/Entertainment): ", null, 0);

    while(!inventory.isCatExists(itemCat)){
      itemCat = stringEmptyChecker("Enter Item Category: ", null, 0);
    }

    if(!inventory.catItems(itemCat).isEmpty()){
      System.out.printf("\n%-20s %-20s %-20s %-20s%n", "Item ID", "Item Name", "Quantity", "Price");
      System.out.printf("%-20s %-20s %-20s %-20s%n", "-------------", "-------------", "-------------", "-------------");

      ArrayList <AbstractItems> display = inventory.catItems(itemCat);
      for(AbstractItems item : display){
        String priceStr = String.format("%,.2f", item.getPrice());
        System.out.printf("%-20s %-20s %-20d %-20s%n", item.getId(), item.getName(), item.getQty(), priceStr);
      }
    }else{
      System.out.println("No items available.");
      return;
    }
  }

  public static void displayAllItemsMain(Inventory inventory){
    System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
    System.out.println("                                         DISPLAY ALL ITEMS");
    System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");

    if(inventory.isEmpty()){
      System.out.println("No items available.");
      return;
    }

    inventory.displayAll(inventory);
  }

  public static void searchItemMain(Inventory inventory){
    System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
    System.out.println("                                          SEARCH ITEM");
    System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");

    if(inventory.isEmpty()){
      System.out.println("No items available.");
      return;
    }

    String ID = stringEmptyChecker("\nEnter Item ID: ", null, 0);
    if(!inventory.idExists(ID)){
      System.out.println("Item with ID " + ID + " not found!");
      return;
    }

    AbstractItems item = inventory.getItemByID(ID);

    System.out.println("ID found!");
    displayCurrentItem(item.getCategory(), item.getId(), item.getName(), item.getQty(), item.getPrice());
    
  }

  public static void sortItemsMain(Inventory inventory){
    System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
    System.out.println("                                           SORT ITEMS");
    System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");

    if(inventory.isEmpty()){
      System.out.println("No items available.");
      return;
    }

    System.out.println("\n                                          SORT BY");
    System.out.println("                                - - - - - - - - - - - - - - - -");
    System.out.println("""

                                        [a] Quantity                         [b] Price               
                  """);
    System.out.println("       - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"); 

    char sort = charValidation("Enter chosen information to sort: ", 'a', 'b');

    switch(sort){
      case 'a': //quantity
        sortItems(inventory, sort);
        break;
      case 'b': //price
        sortItems(inventory, sort);
        break;
    }
  }

  public static void sortItems(Inventory inventory, char sort){
    System.out.println("\n                                       SORTING ORDER");
    System.out.println("                                - - - - - - - - - - - - - - - -");
    System.out.println("""

                                        [a] Ascending                         [b] Descending               
                  """);
    System.out.println("       - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"); 
    char order = charValidation("Enter chosen information to sort: ", 'a', 'b');

    switch(order){
      case 'a': //ascending
        ArrayList<AbstractItems> sortedAsc = inventory.getSortedItems(sort, order);
        displayArrayList(sortedAsc);
        break;
      case 'b': //descending
        ArrayList<AbstractItems> sortedDesc = inventory.getSortedItems(sort, order);
        displayArrayList(sortedDesc);
        break;
    }
  }

  public static void displayArrayList(ArrayList<AbstractItems> sorted){
    if(sorted.isEmpty()){
      System.out.println("No items available.");
      return;
    }

    System.out.printf("\n%-20s %-20s %-20s %-20s %-20s%n", "Item ID", "Item Name", "Quantity", "Price", "Category");
    System.out.printf("%-20s %-20s %-20s %-20s %-20s%n", "-------------", "-------------", "-------------", "-------------", "-------------");

    for(AbstractItems item : sorted){
      String priceStr = String.format("%,.2f", item.getPrice());
      System.out.printf("%-20s %-20s %-20d %-20s %-20s%n", item.getId(), item.getName(), item.getQty(), priceStr, item.getCategory());
    }
  }

  public static void lowStockItems(Inventory inventory){
    System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
    System.out.println("                                      DISPLAY LOW STOCK ITEMS");
    System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");

    ArrayList<AbstractItems> lowStocks = inventory.getLowStockItems();
    displayArrayList(lowStocks);
  }
  public static void main(String[] args){
    System.out.println("\nSimple Inventory Management System by Ma. Angelica Pauleen R. De Chavez of C2A\n");

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
          updateItemMain(inventory);
          break;
        case 3: //remove item
          removeItemMain(inventory);
          break;
        case 4: //display items by category
          displayByCatMain(inventory);
          break;
        case 5: //display all items
          displayAllItemsMain(inventory);
          break;
        case 6: //search item
          searchItemMain(inventory);
          break;
        case 7: //sort items
          sortItemsMain(inventory);
          break;
        case 8: //display low stock items
          lowStockItems(inventory);
          break;
        case 9:
          //exit
          exitProgram = true;
          break;
      }
    }
    System.out.println("Thank you for using Simple Inventory Management System by Ma. Angelica Pauleen R. De Chavez of C2A\n");
  }
}