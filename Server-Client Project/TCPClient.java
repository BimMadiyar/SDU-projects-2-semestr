package Project_2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPClient implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;
    String greenColorCode = "\u001B[38;2;0;255;0m";
    String italicColorCode = "\u001B[3m";
    String redColorCode = "\u001B[31m";
    String resetColorCode = "\u001B[0m";
    String blueColorCode = "\u001B[34m";
    String clientIndex;



    public void run(){
        try {
            client = new Socket("localhost", 2024);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println(blueColorCode + displayTime() + " Connected to the Server successfully!" + resetColorCode);
            out = new PrintWriter(client.getOutputStream(), true);

            InputHandler inHandler = new InputHandler();
            Thread t = new Thread(inHandler);
            t.start();

        } catch (IOException e){
            // QUIT
        }
    }

    class InputHandler implements Runnable{

        public void run(){
            try {
                Scanner scanner = new Scanner(System.in);
                String adminUsername = in.readLine();
                String adminPassword = in.readLine();
                while (true) {
                    try {
                        System.out.println("Choose the one: ");
                        System.out.println("\t1. Admin");
                        System.out.println("\t2. Client");
                        System.out.print("Enter the number (1 / 2): ");
                        int choose = Integer.parseInt(scanner.nextLine());

                        if (choose == 1){ // +++
                            System.out.print("Enter the username: ");
                            String username = scanner.nextLine().strip();

                            if (username.equals(adminUsername)){ // +++
                                System.out.print("Enter the password: ");
                                String password = scanner.nextLine().strip();

//-----------------------------------------------------ADMIN WINDOW-----------------------------------------------------------------------------------------------------
                                if (password.equals(adminPassword)){ // +++
                                    out.println(adminUsername);
                                    out.println("Admin (" + username + ")");
                                    System.out.println(blueColorCode + italicColorCode + displayTime() + " Successfull login! Now you are Admin!" + resetColorCode);

                                    while (!done) {
                                        displayAdminMenu();
                                        String message = scanner.nextLine();
                                        String[] messages = message.split("\\s+");
                                        out.println(message);

                                        if (message.startsWith("GET")) { // +++
                                            if (messages.length == 2) { // +++
                                                System.out.println(in.readLine());
                                            } else { // +++
                                                System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                            }
                                        } else if (message.startsWith("PUT")) { // +++
                                            if (messages.length == 3) { // +++
                                                System.out.println(in.readLine());
                                            } else { // +++
                                                System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                            }
                                        } else if (message.startsWith("DELETE ALL DATA")){ // +++
                                            if (messages.length == 3) { // +++
                                                System.out.println(in.readLine());
                                            } else { // +++
                                                System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                            }
                                        } else if (message.startsWith("DELETE")) { // +++
                                            if (messages.length == 2) { // +++
                                                System.out.println(in.readLine());
                                            } else { // +++
                                                System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                            }

                                        } else if (message.startsWith("CHANGE")){ // +++
                                            if (messages.length == 3){ // +++
                                                System.out.println(in.readLine());
                                            } else { // +++
                                                System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                            }
                                        }
                                        else if (message.startsWith("KEYS")) { // +++
                                            if (messages.length == 1){ // +++
                                                try{ // +++
                                                    int mapSize = Integer.parseInt(in.readLine());
                                                    for (int i = 0; i < mapSize; i++){
                                                        System.out.println(in.readLine());
                                                    }
                                                } catch (NumberFormatException e){ // +++
                                                    System.out.println(redColorCode + italicColorCode + displayTime() + " The Map is Empty right now!" + resetColorCode);
                                                }
                                            } else { // +++
                                                System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                            }

                                        } else if (message.startsWith("DISCONNECT CLIENT")){ // +++
                                            if (messages.length == 3){ // +++
                                                System.out.println(in.readLine());
                                            } else { // +++
                                                System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                            }
                                        } else if (message.startsWith("VIEW CLIENTS")){ // +++
                                            if (messages.length == 2){ // +++
                                                System.out.println(in.readLine());
                                            } else { // +++
                                                System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                            }
                                        }
                                        else if (message.startsWith("QUIT")) { // +++
                                            if (messages.length == 1){ // +++
                                                System.out.println(in.readLine());
                                                System.exit(1);
                                            } else { // +++
                                                System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                            }
                                        } else { // +++
                                            System.out.println(redColorCode + italicColorCode + displayTime() + " There is no such Command!" + resetColorCode);
                                        }
                                    }
                                } else { // +++
                                    System.out.println(redColorCode + italicColorCode + displayTime() + " The provided Password is Incorrect!" + resetColorCode);
                                }
                            } else { // +++
                                System.out.println(redColorCode + italicColorCode + displayTime() + " The provided Username is not Correct!" + resetColorCode);
                            }

//--------------------------------------------------CLIENT WINDOW----------------------------------------------------------------------------------------------------------------
                        } else if (choose == 2){
                            System.out.print("Enter your Nickname: ");
                            String clientName = scanner.nextLine();
                            out.println(clientName.strip());
                            out.println("Client (" + clientName + ")");
                            System.out.println(blueColorCode + italicColorCode + displayTime() + " Good! Now you are Client (" + clientName + ")!" + resetColorCode);

                            while (!done) {
                                displayClientMenu();
                                String message = scanner.nextLine();
                                String[] messages = message.split("\\s+");
                                out.println(message);

                                if (message.startsWith("GET")) { // +++
                                    if (messages.length == 2) { // +++
                                        System.out.println(in.readLine());
                                    } else { // +++
                                        System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                    }
                                } else if (message.startsWith("PUT")) { // +++
                                    if (messages.length == 3) { // +++
                                        System.out.println(in.readLine());
                                    } else { // +++
                                        System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                    }
                                } else if (message.startsWith("DELETE")) { // +++
                                    if (messages.length == 2) { // +++
                                        System.out.println(in.readLine());
                                    } else { // +++
                                        System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                    }

                                } else if (message.startsWith("KEYS")) { // +++
                                    if (messages.length == 1){ // +++
                                        try{ // +++
                                            int mapSize = Integer.parseInt(in.readLine());
                                            for (int i = 0; i < mapSize; i++){
                                                System.out.println(in.readLine());
                                            }
                                        } catch (NumberFormatException e){ // +++
                                            System.out.println(redColorCode + italicColorCode + displayTime() + " The Map is Empty right now!" + resetColorCode);
                                        }
                                    } else { // +++
                                        System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                    }

                                } else if (message.startsWith("QUIT")) { // +++
                                    if (messages.length == 1){ // +++
                                        System.out.println(in.readLine());
                                        System.exit(1);
                                    } else { // +++
                                        System.out.println(redColorCode + italicColorCode + displayTime() + " The Format is Incorrect!" + resetColorCode);
                                    }
                                } else { // +++
                                    System.out.println(redColorCode + italicColorCode + displayTime() + " There is no such Command!" + resetColorCode);
                                }
                            }
                        } else { // +++
                            System.out.println(redColorCode + italicColorCode + displayTime() + " Please enter the Number between (1 / 2)!" + resetColorCode);
                        }
                    } catch (NumberFormatException e){ // +++
                        System.out.println(redColorCode + italicColorCode + displayTime() + " Please enter the Integer, not String!" + resetColorCode);
                    }
                }
            } catch (IOException e){ // +++
                System.out.println(blueColorCode + italicColorCode + displayTime() + " The Server already Closed!" + resetColorCode);
            }
        }
    }


    public static void main(String[] args){
        TCPClient client = new TCPClient();
        client.run();
    }

    public static String displayTime(){
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss.SSS]");
        return dateFormat.format(currentDate);
    }

    public static void displayClientMenu(){
        System.out.println("Please Input Command in either of the following forms:");
        System.out.println("\tGET <key>");
        System.out.println("\tPUT <key> <value>");
        System.out.println("\tDELETE <key>");
        System.out.println("\tKEYS");
        System.out.println("\tQUIT");
        System.out.print("Enter Command: ");
    }

    public static void displayAdminMenu(){
        System.out.println("Please Input Command in either of the following forms:");
        System.out.println("\tGET <key>");
        System.out.println("\tPUT <key> <value>");
        System.out.println("\tDELETE <key>");
        System.out.println("\tDELETE ALL DATA");
        System.out.println("\tCHANGE <key> <value>");
        System.out.println("\tKEYS");
        System.out.println("\tDISCONNECT CLIENT <username>");
        System.out.println("\tVIEW CLIENTS");
        System.out.println("\tQUIT");
        System.out.print("Enter Command: ");
    }
}