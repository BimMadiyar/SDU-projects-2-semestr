package JavaProject_1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;

public class PizzaDashBoard{

    public static void deleteTextFromFile(String filePath, String textToDelete) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File("tempfile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while ((line = reader.readLine()) != null) {
            String escapedText = java.util.regex.Pattern.quote(textToDelete);
            line = line.replaceAll(escapedText, "");
            writer.write(line + System.getProperty("line.separator"));
        }

        reader.close();
        writer.close();

        if (!inputFile.delete()) {
            throw new IOException("Failed to delete the original file.");
        }


        if (!tempFile.renameTo(inputFile)) {
            throw new IOException("Failed to rename the temporary file.");
        }
    }

    public static void writeToFile(String fileName, String data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {
            pw.println(data);
            pw.close();
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        PizzaStore pizzaStore = new PizzaStore();
        PizzaInterface pizzaService = new PizzaService(pizzaStore);

        System.out.println("Hey Brother, Welcome to Madik's Pizza Business!!!");
        while(true){
            System.out.println("\n{M} Choose an option: {M}");
            System.out.println("1. Administrator");
            System.out.println("2. Client");
            System.out.println("3. Exit");

            try{
                String input = scanner.nextLine();
                int option = Integer.parseInt(input);
                if (option > 3 || option < 1){
                    System.out.println("Please enter the Number 1 or 2!");
                }
                else if (option == 1){
                    openAdminRole(scanner, pizzaService);
                }
                else if (option == 2){
                    openClientRole(scanner, pizzaService);
                } else {
                    return;
                }
            } catch (NumberFormatException e){
                System.out.println("Please enter the Number instead of String!");
            }
        }
    }

    public static void openAdminRole(Scanner scanner, PizzaInterface pizzaService){
        System.out.println("\nRight now you are the Admin!");

        while(true){
            System.out.println("Choose an option: ");
            System.out.println("1 -> Add Pizza");
            System.out.println("2 -> Update Price");
            System.out.println("3 -> Delete Pizza");
            System.out.println("4 -> View All Pizza");
            System.out.println("5 -> Search Pizza");
            System.out.println("6 -> Back to the Role Menu");
            try{
                String input = scanner.next();
                scanner.nextLine();
                int option = Integer.parseInt(input);
                if (option == 1){
                    System.out.println("\n{M} Add New Pizza Window {M}");

                    System.out.println("\nEnter the Pizza Topping Details:");
                    System.out.print("Topping Name: ");
                    String toppingName = scanner.nextLine();

                    System.out.print("Topping Spice Level: ");
                    String toppingSpiceLevel = scanner.nextLine();

                    System.out.print("Topping Description: ");
                    String toppingDescription = scanner.nextLine();

                    Topping topping = new Topping(toppingName, toppingSpiceLevel, toppingDescription);

                    System.out.println("\nEnter the PizzaBase Details:");
                    System.out.print("Pizza Base Name: ");
                    String baseName = scanner.nextLine();

                    System.out.print("Pizza Base Type (Thin/Thick): ");
                    String baseType = scanner.nextLine();

                    System.out.print("Pizza Base Description: ");
                    String baseDescription = scanner.nextLine();

                    PizzaBase pizzaBase = new PizzaBase(baseName, baseType, baseDescription);

                    System.out.println("\nEnter the Pizza Details: ");
                    System.out.print("Pizza Name: ");
                    String pizzaName = scanner.nextLine();

                    double pizzaPrice = 0;
                    while(true){
                        System.out.print("Pizza Price: ");
                        try{
                            String priceInput = scanner.nextLine();
                            pizzaPrice = Double.parseDouble(priceInput);
                            if (pizzaPrice < 0){
                                System.out.println("\nPlease enter the Valid Price!\n");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e){
                            System.out.println("\nPlease enter the Double Number, not String!\n");
                        }
                    }

                    System.out.print("Pizza Size (Small/Medium/Large): ");
                    String pizzaSize = scanner.nextLine();

                    Pizza pizza = new Pizza(pizzaService.getAllPizzas().size()+1, pizzaName, pizzaPrice, pizzaSize, topping, pizzaBase);
                    pizzaService.addJanaPizza(pizza);
                    System.out.println("New Pizza is Added!!!");

                } else if (option == 2){
                    if (pizzaService.getAllPizzas().isEmpty()) {
                        System.out.println("\nThere is no Pizza in the MENU!\n");
                    } else{
                        System.out.println("\n{M} Update Pizza Price Window {M}\n");

                        System.out.println("There is a List of Pizzas: ");
                        for (Pizza pizza: pizzaService.getAllPizzas()){
                            System.out.println((pizzaService.getAllPizzas().indexOf(pizza)+1) + ". " + pizza.getPizzaAty() + " -> " + pizza.getBaga());
                        }
                        if (pizzaService.getAllPizzas().size() == 1){
                            int numOfPizza = 0;
                            while(true){
                                System.out.print("Choose the Number of Pizza (1): ");
                                try{
                                    String updateInput = scanner.nextLine();
                                    numOfPizza = Integer.parseInt(updateInput);
                                    if (numOfPizza != 1){
                                        System.out.println("\nPlease just write the Number '1'!\n");
                                    } else{
                                        break;
                                    }
                                } catch(NumberFormatException e){
                                    System.out.println("\nPlease enter the Number, not String!\n");
                                }
                            } double changePrice = 0;
                            while(true){
                                System.out.print("Enter the Price you want to change: ");
                                try{
                                    String changeInput = scanner.nextLine();
                                    changePrice = Double.parseDouble(changeInput);
                                    if (changePrice < 0) {
                                        System.out.println("\nPlease enter the Valid Price!\n");
                                    } else if (pizzaService.getAllPizzas().get(0).getBaga() == changePrice){
                                        System.out.println("\nThe Prices are SAME!\n");
                                    } else {
                                        break;
                                    }
                                } catch(NumberFormatException e){
                                    System.out.println("\nPlease enter the Double Number, not String!\n");
                                }
                            } pizzaService.updateBaga(pizzaService.getAllPizzas().get(0), changePrice);
                            System.out.println("The Pizza Price is Updated!");

                        } else if (pizzaService.getAllPizzas().size() > 1){
                            int numOfPizza = 0;
                            while(true){
                                System.out.print("Choose the Number of Pizza (1-" + pizzaService.getAllPizzas().size() + "): ");
                                try{
                                    String updateInput = scanner.nextLine();
                                    numOfPizza = Integer.parseInt(updateInput);
                                    if (numOfPizza < 1 || numOfPizza > pizzaService.getAllPizzas().size()){
                                        System.out.println("\nPlease enter the Number between (1-" + pizzaService.getAllPizzas().size() + "):\n");
                                    } else{
                                        break;
                                    }
                                } catch(NumberFormatException e){
                                    System.out.println("\nPlease enter the Number, not String!\n");
                                }
                            } double changePrice = 0;
                            while(true){
                                System.out.print("Enter the Price you want to change: ");
                                try{
                                    String changeInput = scanner.nextLine();
                                    changePrice = Double.parseDouble(changeInput);
                                    if (changePrice < 0) {
                                        System.out.println("\nPlease enter the Valid Price!\n");
                                    } else if (pizzaService.getAllPizzas().get(0).getBaga() == changePrice){
                                        System.out.println("\nThe Prices are SAME!\n");
                                    } else {
                                        break;
                                    }
                                } catch(NumberFormatException e){
                                    System.out.println("\nPlease enter the Double Number, not String!\n");
                                }
                            }  pizzaService.updateBaga(pizzaService.getAllPizzas().get(numOfPizza-1), changePrice);
                            System.out.println("The Pizza Price is Updated!");
                        }

                    }

                } else if (option == 3){
                    if (pizzaService.getAllPizzas().isEmpty()) {
                        System.out.println("\nThere is no Pizza in the MENU!\n");
                    } else{
                        System.out.println("\n{M} Delete Pizza Window {M}\n");

                        System.out.print("Type the Name of Pizza you want to Delete: ");
                        String pizzaName = scanner.next();
                        try{
                            pizzaService.deletePizza(pizzaService.getPizzaByAty(pizzaName));
                            System.out.println("The Pizza is Deleted!");
                        } catch (PizzaNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                    }

                } else if (option == 4){
                    if (pizzaService.getAllPizzas().isEmpty()){
                        System.out.println("\nThere is no Pizza in the MENU!\n");
                    } else{
                        System.out.println("\n{M} View All Pizza {M}\n");

                        System.out.println("There is a List of Pizzas: ");
                        for (Pizza pizza: pizzaService.getAllPizzas()){
                            System.out.println(pizza + "\n");
                        } System.out.println();
                    }

                } else if (option == 5){
                    searchPizza(scanner, pizzaService);

                } else if (option == 6){
                    break;

                } else {
                    System.out.println("Please enter the Number between (1-6)!");
                }
            } catch(NumberFormatException e){
                System.out.println("Please enter the Number, not String!");
            }
        }
    }

    public static void openClientRole(Scanner scanner, PizzaInterface pizzaService){
        Customer client = new Customer();
        if (client.getEmail() == null){
            int clientId = 0;
            while(true){
                System.out.print("Enter your ID: ");
                try{
                    String idInput = scanner.next();
                    scanner.nextLine();
                    clientId = Integer.parseInt(idInput);
                    if (clientId < 0){
                        System.out.println("\nPlease enter the Valid ID!\n");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e){
                    System.out.println("Please enter the ID (Number), not String!");
                }
            }

            System.out.print("Enter your Name: ");
            String clientName = scanner.nextLine();

            String clientEmail = null;
            while(true){
                System.out.print("Enter your Email address: ");
                try{
                    clientEmail = scanner.nextLine();
                    checkEmail(clientEmail);
                    break;
                } catch (InvalidEmailException e){
                    System.out.println("\n" + e.getMessage());
                    System.out.println("Sample email: String@String.String\n");
                }
            }

            client = new Customer(clientId, clientName, clientEmail);
        }

        System.out.println("\nRight now you are the Client!");

        while(true){
            System.out.println("Choose an option: ");
            System.out.println("1 -> Order Pizza");
            System.out.println("2 -> Pay Bill");
            System.out.println("3 -> View All Pizza");
            System.out.println("4 -> View your Orders");
            System.out.println("5 -> Search Pizza ");
            System.out.println("6 -> Exit");
            System.out.println("7 -> Delete Pizza from the Order ");
            try{
                String input = scanner.next();
                scanner.nextLine();
                int option = Integer.parseInt(input);
                if (option == 1){
                    System.out.println("\n{M} Order Pizza Window {M}\n");

                    if (pizzaService.getAllPizzas().isEmpty()){
                        System.out.println("There is no Pizza in the MENU!\n");
                    }else {
                        System.out.println("There is a List of Pizzas: ");
                        for (Pizza pizza: pizzaService.getAllPizzas()){
                            System.out.println(pizza + "\n");
                        }

                        while(true){
                            System.out.print("\nEnter the Pizza Name to order: ");
                            String pizzaName = scanner.nextLine();
                            try{
                                client.getOrder().getPizzas().add(pizzaService.getPizzaByAty(pizzaName));
                                System.out.println("The Pizza is added to the Order Successfully!");

                                writeToFile("Orders", pizzaService.getPizzaByAty(pizzaName).toString());

                                break;
                            } catch (PizzaNotFoundException e){
                                System.out.println("There is no such Pizza in the MENU!\n");
                            }
                        }
                    }

                } else if (option == 2){
                    System.out.println("\n{M} Pay Bill Window {M}\n");

                    if (client.getOrder().getPizzas().isEmpty()){
                        System.out.println("There is no Pizza in the ORDER!\n");
                        System.out.println("At first add some Pizza to your Order!");
                    } else{
                        double payBillAmount = 0;
                        for (Pizza pizza : client.getOrder().getPizzas()){
                            payBillAmount += pizza.getBaga();
                        }
                        System.out.println("\nYour Pay Bill Amount: " + payBillAmount);
                    }

                } else if (option == 3){
                    if (pizzaService.getAllPizzas().isEmpty()){
                        System.out.println("There is no Pizza in the MENU!\n");
                    } else{
                        System.out.println("\n{M} View All Pizzas {M}\n");

                        System.out.println("There is a List of Pizzas: ");
                        for (Pizza pizza: pizzaService.getAllPizzas()){
                            System.out.println(pizza);
                        } System.out.println();
                    }

                } else if (option == 4){
                    if (client.getOrder().getPizzas().isEmpty()) {
                        System.out.println("There is no Pizza in the ORDER!\n");
                    } else{
                        System.out.println("\n{M} View Your Orders {M}\n");

                        for (Pizza pizza : client.getOrder().getPizzas()){
                            System.out.println(pizza + "\n");
                        }
                    }

                } else if (option == 5){
                    searchPizza(scanner, pizzaService);

                } else if (option == 6){
                    break;
                } else if (option == 7){

                    if (client.getOrder().getPizzas().isEmpty()){
                        System.out.println("There is no Pizza in the ORDER!\n");
                    } else{
                        System.out.println("\nThere is a List of Pizzas from the Order: \n");
                        for (Pizza pizza: client.getOrder().getPizzas()){
                            System.out.println(pizza + "\n");
                        }

                        while(true){
                            System.out.print("\nEnter the Pizza Name to Delete from the Order: ");
                            String pizzaName = scanner.nextLine();
                            try{
                                client.getOrder().getPizzas().remove(pizzaService.getPizzaByAty(pizzaName));
                                System.out.println("The Pizza is Deleted from the Order Successfully!");

                                try {
                                    deleteTextFromFile("Orders", pizzaService.getPizzaByAty(pizzaName).toString());
                                    System.out.println("Text deleted successfully from the file.");
                                } catch (IOException e) {
                                    System.out.println("An error occurred while deleting text from the file: " + e.getMessage());
                                }

                                break;
                            } catch (PizzaNotFoundException e){
                                System.out.println("There is no such Pizza in the MENU!\n");
                            }
                        }
                    }

                } else {
                    System.out.println("Please enter the Number between (1-7)");
                }
            } catch(NumberFormatException e){
                System.out.println("Please enter the Number, not String!");
            }
        }
    }

    public static void searchPizza(Scanner scanner, PizzaInterface pizzaService) {
        if (pizzaService.getAllPizzas().isEmpty()) {
            System.out.println("\nThere is no Pizza in the MENU!\n");
        } else {
            while (true) {
                System.out.println("\n{M} Search Pizza Window {M}\n");

                System.out.println("1 -> Find Pizza with the Name");
                System.out.println("2 -> Find Pizza with the Size");
                System.out.println("3 -> Find Pizza with the ID");
                System.out.println("4 -> Find Pizza with the Price Range");
                System.out.println("5 -> Back to the Admin Menu");

                try {
                    String updateInput = scanner.next();
                    scanner.nextLine();
                    int updateOption = Integer.parseInt(updateInput);

                    if (updateOption == 1) {
                        String pizzaName = null;
                        while(true){
                            System.out.print("Enter the Pizza Name: ");
                            pizzaName = scanner.nextLine();
                            try {
                                pizzaService.getPizzaByAty(pizzaName);
                                break;
                            } catch (PizzaNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        try {
                            System.out.println(pizzaService.getPizzaByAty(pizzaName));
                        } catch (PizzaNotFoundException e) {
                            System.out.println(e.getMessage());
                        }

                    } else if (updateOption == 2) {
                        System.out.print("Enter the Pizza Size: ");
                        String pizzaSize = scanner.nextLine();

                        try {
                            System.out.println(pizzaService.getPizzaByRazmer(pizzaSize));
                        } catch (PizzaNotFoundException e) {
                            System.out.println(e.getMessage());
                        }

                    } else if (updateOption == 3) {
                        int pizzaId = 0;
                        while (true) {
                            System.out.print("Enter the Pizza ID: ");
                            try {
                                String idInput = scanner.next();
                                pizzaId = Integer.parseInt(idInput);
                                if (pizzaId < 0) {
                                    System.out.println("Please enter the Valid ID!");
                                } else {
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter the Number(ID), not String!");
                            }
                        }
                        try {
                            System.out.println(pizzaService.getPizzaById(pizzaId));
                        } catch (PizzaNotFoundException e) {
                            System.out.println(e.getMessage());
                        }

                    } else if (updateOption == 4) {
                        System.out.println("Enter the Pizza Price Range (Start Price and End Price): ");

                        int startPrice = 0;
                        while (true) {
                            System.out.print("Start Price: ");
                            try {
                                String startPriceInput = scanner.next();
                                startPrice = Integer.parseInt(startPriceInput);
                                if (startPrice < 0) {
                                    System.out.println("Please enter the Valid Price!");
                                } else {
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter the Price Number, not String!");
                            }
                        }

                        int endPrice = 0;
                        while (true) {
                            System.out.print("End Price: ");
                            try {
                                String endPriceInput = scanner.next();
                                endPrice = Integer.parseInt(endPriceInput);
                                if (endPrice < 0) {
                                    System.out.println("Please enter the Valid Price!");
                                } else {
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter the Price Number, not String!!");
                            }
                        }

                        int pizzaNumber = 0;
                        try {
                            System.out.println("Pizza List between the Price Range: ");
                            for (Pizza pizza : pizzaService.getPizzaByBagaRange(startPrice, endPrice)) {
                                System.out.println((pizzaService.getPizzaByBagaRange(startPrice, endPrice).indexOf(pizza) + 1) + ". " + pizza.getPizzaAty() + ": " + pizza.getBaga());
                            }
                            while (true) {
                                System.out.print("Choose one Pizza from List (1-" + pizzaService.getPizzaByBagaRange(startPrice, endPrice).size() + "): ");
                                try {
                                    String pizzaNumberInput = scanner.next();
                                    pizzaNumber = Integer.parseInt(pizzaNumberInput);
                                    if (pizzaNumber < 1 || pizzaNumber > pizzaService.getPizzaByBagaRange(startPrice, endPrice).size()) {
                                        System.out.println("Please enter the Number between (1-" + pizzaService.getPizzaByBagaRange(startPrice, endPrice).size() + ")!");
                                    } else {
                                        break;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Please enter the Number (1-" + pizzaService.getPizzaByBagaRange(startPrice, endPrice).size() + "), not String!");
                                }
                            }
                            System.out.println(pizzaService.getPizzaByBagaRange(startPrice, endPrice).get(pizzaNumber - 1));

                        } catch (PizzaNotFoundException e) {
                            System.out.println(e.getMessage());
                        }


                    } else if (updateOption == 5) {
                        break;

                    } else {
                        System.out.println("Please enter the number between (1-5)!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter the Number, not String!!");
                }
            }
        }
    }

    public static void checkEmail(String email) throws InvalidEmailException{
        if (!(email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))){
            throw new InvalidEmailException("This is invalid email: " + email);
        }
    }
}

class Order{
    private ArrayList<Pizza> pizzas = new ArrayList<>();
    private String zakazDescription;
    private double payableBillAmount;
    private String zakazDate;
    private int zakazId;

    public Order(){}
    public Order(int orderId, String orderDate, double payableBillAmount, String orderDescription){
        zakazId = orderId;
        zakazDate = orderDate;
        this.payableBillAmount = payableBillAmount;
        zakazDescription = orderDescription;
    }

    public void addPizza(String fileName, Pizza pizza){
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {
            pw.println(pizza.toString());
            pw.close();
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    public int getZakazId(){
        return zakazId;
    }
    public void setZakazId(int janaZakazId){
        zakazId = janaZakazId;
    }

    public String getZakazDate(){
        return zakazDate;
    }
    public void setZakazDate(String janaZakazDate){
        zakazDate = janaZakazDate;
    }

    public double getPayableBillAmount(){
        return payableBillAmount;
    }
    public void setPayableBillAmout(double janaPayableBillAmount){
        payableBillAmount = janaPayableBillAmount;
    }

    public String getZakazDescription(){
        return zakazDescription;
    }
    public void setZakazDescription(String janaZakazDescription){
        zakazDescription = janaZakazDescription;
    }

    public ArrayList<Pizza> getPizzas(){
        return pizzas;
    }
    public void setPizzas(ArrayList<Pizza> janaPizzas){
        pizzas = janaPizzas;
    }

    public String toString() {
        return "Order{" +
                "pizzas=" + pizzas +
                ", zakazDescription='" + zakazDescription + '\'' +
                ", payableBillAmount=" + payableBillAmount +
                ", zakazDate='" + zakazDate + '\'' +
                ", zakazId=" + zakazId +
                '}';
    }
}

class PizzaService implements PizzaInterface{
    private PizzaStore pizzaStore;

    public PizzaService(PizzaStore pizzaStore){
        this.pizzaStore = pizzaStore;
    }

    public void addJanaPizza(Pizza janaPizza){
        pizzaStore.addPizza(janaPizza);
    }
    public void deletePizza(Pizza currentPizza){
        pizzaStore.deletePizza(currentPizza);
    }

    public void updateBaga(Pizza pizza, double janaBaga){
        pizza.setBaga(janaBaga);
    }

    public ArrayList<Pizza> getAllPizzas(){
        return pizzaStore.getPizzas();
    }

    public Pizza orderJanaPizza(String fileName, Pizza pizza, Customer client){
        client.getOrder().addPizza(fileName, pizza);
        return pizza;
    }

    public Pizza getPizzaByAty(String pizzaAty) throws PizzaNotFoundException{
        Pizza pizza = null;
        for (int i = 0; i < pizzaStore.getPizzas().size(); i++){
            if (pizzaStore.getPizzas().get(i).getPizzaAty().equals(pizzaAty)){
                pizza = pizzaStore.getPizzas().get(i);
            }
        }
        if (pizza == null) {
            throw new PizzaNotFoundException("Sorry, Pizza with this Name Not Found!");
        }
        return pizza;
    }

    public Pizza getPizzaById(int pizzaId) throws PizzaNotFoundException{
        Pizza pizza = null;
        for (int i = 0; i < pizzaStore.getPizzas().size(); i++){
            if (pizzaStore.getPizzas().get(i).getPizzaNomer() == pizzaId){
                pizza = pizzaStore.getPizzas().get(i);
            }
        }
        if (pizza == null){
            throw new PizzaNotFoundException("Sorry, Pizza with this ID Not Found!");
        }
        return pizza;
    }

    public Pizza getPizzaByRazmer(String razmer) throws PizzaNotFoundException{
        Pizza pizza = null;
        for (int i = 0; i < pizzaStore.getPizzas().size(); i++){
            if (pizzaStore.getPizzas().get(i).getRazmer().equals(razmer)){
                pizza = pizzaStore.getPizzas().get(i);
            }
        }
        if (pizza == null){
            throw new PizzaNotFoundException("Sorry, Pizza with this Size Not Found!");
        }
        return pizza;
    }

    public ArrayList<Pizza> getPizzaByBagaRange(int startPrice, int endPrice) throws PizzaNotFoundException{
        ArrayList<Pizza> pizzas = new ArrayList<>();

        for (int i = 0; i < pizzaStore.getPizzas().size(); i++){
            if (pizzaStore.getPizzas().get(i).getBaga() >= startPrice && pizzaStore.getPizzas().get(i).getBaga() <= endPrice){
                pizzas.add(pizzaStore.getPizzas().get(i));
            }
        }
        if (pizzas.isEmpty()){
            throw new PizzaNotFoundException("Sorry, Pizza in this Price Range Not Found!");
        }
        return pizzas;
    }
}

interface PizzaInterface{
    public abstract Pizza getPizzaByRazmer(String razmer) throws PizzaNotFoundException;

    public abstract Pizza getPizzaById(int pizzaId) throws PizzaNotFoundException;

    public abstract Pizza getPizzaByAty(String currentPizzaAty) throws PizzaNotFoundException;

    public abstract ArrayList<Pizza> getPizzaByBagaRange(int startPrice, int endPrice) throws PizzaNotFoundException;

    public abstract Pizza orderJanaPizza(String fileName, Pizza janaPizza, Customer client);

    public abstract ArrayList<Pizza> getAllPizzas();

    public abstract void updateBaga(Pizza pizza, double janaBaga);

    public abstract void deletePizza(Pizza currentPizza);

    public abstract void addJanaPizza(Pizza janaPizza);
}

class Customer{
    private Order zakaz = new Order();
    private Address address;
    private long telephone;
    private String email;
    private String clientAty;
    private int clientNomer;

    public Customer(){}

    public Customer(int clientId, String clientName, String email){
        clientNomer = clientId;
        clientAty = clientName;
        this.email = email;
    }

    public double getPayableAmount(){
        return 0;
    }

    public int getClientNomer(){
        return clientNomer;
    }
    public void setClientNomer(int janaClientNomer){
        clientNomer = janaClientNomer;
    }

    public String getClientAty(){
        return clientAty;
    }
    public void setClientAty(String janaClientAty){
        clientAty = janaClientAty;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String janaEmail){
        email = janaEmail;
    }

    public long getTelephone(){
        return telephone;
    }
    public void setTelephone(long janaTelephone){
        telephone = janaTelephone;
    }

    public Address getAddress(){
        return address;
    }
    public void setAddress(Address janaAddress){
        address = janaAddress;
    }

    public Order getOrder(){
        return zakaz;
    }
    public void setOrder(Order janaZakaz){
        zakaz = janaZakaz;
    }

    public String toString(){
        return "";
    }
}

class Pizza{
    private PizzaBase pizzaBaza;
    private Topping topchik;
    private String razmer;
    private double baga;
    private String pizzaAty;
    private int pizzaNomer;

    public Pizza(int pizzaId, String pizzaName, double price, String size, Topping topping, PizzaBase pizzaBase){
        pizzaNomer = pizzaId;
        pizzaAty = pizzaName;
        baga = price;
        razmer = size;
        topchik = topping;
        pizzaBaza = pizzaBase;
    }

    public int getPizzaNomer(){
        return pizzaNomer;
    }
    public void setPizzaNomer(int janaPizzaNomer){
        pizzaNomer = janaPizzaNomer;
    }

    public String getPizzaAty(){
        return pizzaAty;
    }
    public void setPizzaAty(String janaPizzaAty){
        pizzaAty = janaPizzaAty;
    }

    public double getBaga(){
        return baga;
    }
    public void setBaga(double janaBaga){
        baga = janaBaga;
    }

    public String getRazmer(){
        return razmer;
    }
    public void setRazmer(String janaRazmer){
        razmer = janaRazmer;
    }

    public Topping getTopchik(){
        return topchik;
    }
    public void setTopchik(Topping janaTopchik){
        topchik = janaTopchik;
    }

    public PizzaBase getPizzaBaza(){
        return pizzaBaza;
    }
    public void setPizzaBaza(PizzaBase janaPizzaBaza){
        pizzaBaza = janaPizzaBaza;
    }

    public String toString() {
        return "Pizza{" +
                "pizzaBase=" + pizzaBaza.toString() +
                "\n Topping=" + topchik.toString() +
                "\n size='" + razmer + '\'' +
                ", price=" + baga +
                ", pizzaAty='" + pizzaAty + '\'' +
                ", pizzaId=" + pizzaNomer +
                '}';
    }
}

class PizzaBase{
    private String sipattama;
    private String bazaType;
    private String bazaAty;

    public PizzaBase(String baseName, String baseType, String description){
        bazaAty = baseName;
        bazaType = baseType;
        sipattama = description;
    }

    public String getBazaAty(){
        return bazaAty;
    }
    public void setBazaAty(String janaBazaAty){
        bazaAty = janaBazaAty;
    }

    public String getBazaType(){
        return bazaType;
    }
    public void setBazaType(String janaBazaType){
        bazaType = janaBazaType;
    }

    public String getSipattama(){
        return sipattama;
    }
    public void setSipattama(String janaSipattama){
        sipattama = janaSipattama;
    }

    public String toString() {
        return "PizzaBase{" +
                "sipattama='" + sipattama + '\'' +
                ", bazaType='" + bazaType + '\'' +
                ", bazaAty='" + bazaAty + '\'' +
                '}';
    }
}

class Topping{
    private String sipattama;
    private String spiceLevel;
    private String topchikAty;

    public Topping(String topchikAty, String spiceLevel, String sipattama){
        this.topchikAty = topchikAty;
        this.spiceLevel = spiceLevel;
        this.sipattama = sipattama;
    }

    public String getTopchikAty(){
        return topchikAty;
    }
    public void setTopchikAty(String janaTopchikAty){
        topchikAty = janaTopchikAty;
    }

    public String getSpiceLevel(){
        return spiceLevel;
    }
    public void setSpiceLevel(String janaSpiceLevel){
        spiceLevel = janaSpiceLevel;
    }

    public String getSipattama(){
        return sipattama;
    }
    public void setSipattama(String janaSipattama){
        sipattama = janaSipattama;
    }

    public String toString() {
        return "Topping{" +
                "sipattama='" + sipattama + '\'' +
                ", spiceLevel='" + spiceLevel + '\'' +
                ", topchikAty='" + topchikAty + '\'' +
                '}';
    }
}

class PizzaStore{
    private ArrayList<Customer> clientter = new ArrayList<>();
    private ArrayList<Pizza> pizzas = new ArrayList<>();
    private String storeLocation;
    private String storeAty;
    private int storeId;

    public PizzaStore(){}

    public PizzaStore(int storeId, String storeName, String storeLocation){
        this.storeId = storeId;
        storeAty = storeName;
        this.storeLocation = storeLocation;
    }

    public void addPizza(Pizza janaPizza){
        pizzas.add(janaPizza);
    }

    public void addClient(Customer janaClient){
        clientter.add(janaClient);
    }

    public void deletePizza(Pizza currentPizza){
        pizzas.remove(currentPizza);
    }

    public int getStoreId(){
        return storeId;
    }
    public void setStoreId(int janaStoreId){
        storeId = janaStoreId;
    }

    public String getStoreAty(){
        return storeAty;
    }
    public void setStoreAty(String janaStoreAty){
        storeAty = janaStoreAty;
    }

    public String getStoreLocation(){
        return storeLocation;
    }
    public void setStoreLocation(String janaStoreLocation){
        storeLocation = janaStoreLocation;
    }

    public ArrayList<Pizza> getPizzas(){
        return pizzas;
    }
    public void setPizzas(ArrayList<Pizza> janaPizzas){
        pizzas = janaPizzas;
    }

    public ArrayList<Customer> getClientter(){
        return clientter;
    }
    public void setClientter(ArrayList<Customer> janaClientter){
        clientter = janaClientter;
    }

    public String toString(){
        return "";
    }
}

class Address{
    private String state;
    private String district;
    private String gorod;
    private String koshe;
    private int esikNumber;

    public Address(int doorNumber, String street, String city, String district, String state){
        esikNumber = doorNumber;
        koshe = street;
        gorod = city;
        this.district = district;
        this.state = state;
    }

    public int getEsikNumber(){
        return esikNumber;
    }
    public void setEsikNumber(int janaEsikNumber){
        esikNumber = janaEsikNumber;
    }

    public String getKoshe(){
        return koshe;
    }
    public void setKoshe(String janaKoshe){
        koshe = janaKoshe;
    }

    public String getGorod(){
        return gorod;
    }
    public void setGorod(String janaGorod){
        gorod = janaGorod;
    }

    public String getDistrict(){
        return district;
    }
    public void setDistrict(String janaDistrict){
        district = janaDistrict;
    }

    public String getState(){
        return state;
    }
    public void setState(String janaState){
        state = janaState;
    }

    public String toString(){
        return "";
    }
}

class PizzaNotFoundException extends Exception{
    public PizzaNotFoundException(String habar){
        super(habar);
    }
}

class InvalidEmailException extends Exception{
    public InvalidEmailException(String habar){
        super(habar);
    }
}