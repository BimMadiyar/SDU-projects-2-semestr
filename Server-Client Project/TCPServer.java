package Project_2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer implements Runnable{
    private ServerSocket server;
    private boolean done;
    private Map<String, String> hashMap;
    private ArrayList<ConnectionHandler> connections;
    private ArrayList<Socket> connectedSockets = new ArrayList<>();
    private ExecutorService pool;
    private String adminUsername = "Bimuratov Madiyar";
    private String adminPassword = "Madik2006";
    private Map<String, Integer> usernames = new HashMap<>();


    public TCPServer(){
        hashMap = new HashMap<>();
        connections = new ArrayList<>();
        done = false;
    }

    public void run(){
        try {
            server = new ServerSocket(2024);
            System.out.println("Server started. Listening for Clients on port 2024...");
            pool = Executors.newCachedThreadPool();
            while(!done) {
                Socket client = server.accept();
                connectedSockets.add(client);
                ConnectionHandler connection = new ConnectionHandler(client);
                connections.add(connection);
                pool.execute(connection);
            }
        } catch (IOException e){
            // QUIT
        }
    }

    class ConnectionHandler implements Runnable{
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        String greenColorCode = "\u001B[38;2;0;255;0m";
        String italicColorCode = "\u001B[3m";
        String redColorCode = "\u001B[31m";
        String resetColorCode = "\u001B[0m";
        String blueColorCode = "\u001B[34m";
        String clientName = "";



        public ConnectionHandler(Socket client){
            this.client = client;
        }

        public void run(){
            try {
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(), true);
                out.println(adminUsername);
                out.println(adminPassword);
                String username = in.readLine();
                if (!username.equals(adminUsername)) {
                    usernames.put(username, connectedSockets.size() - 1);
                    clientName = "Client (" + username + ")";
                } else {
                    clientName = "Admin (" + username + ")";
                }
                String choose = italicColorCode + in.readLine() + resetColorCode + blueColorCode;
                System.out.println(blueColorCode + TCPClient.displayTime() + " " + choose + " [" + client.getLocalAddress() + " : " + client.getPort() + "] connected successfully!" + resetColorCode);

                String inMessage;
                while ((inMessage = in.readLine()) != null) {
                    String[] messages = inMessage.split("\\s+");
                    String successfullCommand = greenColorCode + italicColorCode + clientName + ": " + inMessage + " -> The Command is Successfull!";
                    String incorrectFormat = redColorCode + italicColorCode + clientName + ": " + inMessage + " -> The Format is Incorrect!" + resetColorCode;

                    if (inMessage.startsWith("GET")){ // +++
                        if (messages.length == 2){ // +++
                            if (hashMap.containsKey(messages[1])){ // +++
                                out.println(greenColorCode + italicColorCode + TCPClient.displayTime() + " The Value of Key: " + hashMap.get(messages[1]) + resetColorCode);
                                System.out.println(successfullCommand);
                            } else { // +++
                                out.println(redColorCode + italicColorCode + TCPClient.displayTime() + " This Key doesn't exist in the Map!" + resetColorCode);
                                System.out.print(redColorCode + italicColorCode + clientName + ": " + inMessage);
                                System.out.println(" -> This Key doesn't exist in the Map!" + resetColorCode);
                            }
                        } else { // +++
                            System.out.println(incorrectFormat);
                        }
                    } else if (inMessage.startsWith("PUT")){ // +++
                        if (messages.length == 3){ // +++
                            if (hashMap.containsKey(messages[1])){ // +++
                                out.println(redColorCode + italicColorCode + TCPClient.displayTime() + " This Key already exists in the Map! You can use CHANGE <key> <value> Command to update the value if you are ADMIN!" + resetColorCode);
                                System.out.println(redColorCode + italicColorCode + clientName + ": " + inMessage + " -> This Key already exists in the Map!");
                            } else { // +++
                                out.println(greenColorCode + italicColorCode + TCPClient.displayTime() + " The Key <" + messages[1] + "> with the Value <" + messages[2] + "> is stored to the Map!" + resetColorCode);
                                hashMap.put(messages[1], messages[2]);
                                System.out.println(successfullCommand);
                            }
                        } else { // +++
                            System.out.println(incorrectFormat);
                        }
                    } else if (inMessage.startsWith("DELETE ALL DATA") && clientName.startsWith("Admin")){ // +++
                        if (messages.length == 3){ // +++
                            if (hashMap.isEmpty()){ // +++
                                System.out.println(redColorCode + italicColorCode + clientName + ": " + inMessage + " -> The Map is Empty right now!" + resetColorCode);
                                out.println(redColorCode + italicColorCode + TCPClient.displayTime() + " The Map is Empty right now!" + resetColorCode);
                            } else { // +++
                                hashMap = new HashMap<>();
                                out.println(greenColorCode + italicColorCode + TCPClient.displayTime() + " The all Data Deleted from Map!" + resetColorCode);
                                System.out.println(successfullCommand);
                            }
                        } else { // +++
                            System.out.println(incorrectFormat);
                        }
                    } else if (inMessage.startsWith("DELETE")){ // +++
                        if (messages.length == 2){ // +++
                            if (hashMap.containsKey(messages[1])){
                                out.println(greenColorCode + italicColorCode + TCPClient.displayTime() + " The Key <" + messages[1] + "> with the Value <" + hashMap.get(messages[1]) + "> is Deleted!" + resetColorCode);
                                hashMap.remove(messages[1]);
                                System.out.println(successfullCommand);
                            } else { // +++
                                out.println(redColorCode + italicColorCode + TCPClient.displayTime() + " This Key doesn't exist in the Map!" + resetColorCode);
                                System.out.println(redColorCode + italicColorCode + clientName + ": " + inMessage + " -> This Key doesn't exist in the Map!" + resetColorCode);
                            }
                        } else { // +++
                            System.out.println(incorrectFormat);
                        }

                    } else if (inMessage.startsWith("CHANGE") && clientName.startsWith("Admin")){ // +++
                        if (messages.length == 3){ // +++
                            if (hashMap.containsKey(messages[1])){ // +++
                                out.println(greenColorCode + italicColorCode + TCPClient.displayTime() + " This Key value updated in the Map!" + resetColorCode);
                                hashMap.put(messages[1], messages[2]);
                                System.out.println(successfullCommand);
                            } else { // +++
                                out.println(redColorCode + italicColorCode + TCPClient.displayTime() + " This Key doesn't exist in the Map! You can use PUT <key> <value> Command to add new Key!"+ resetColorCode);
                                System.out.println(redColorCode + italicColorCode + clientName + ": " + inMessage + " -> This Key doesn't exist in the Map!");
                            }
                        } else { // +++
                            System.out.println(incorrectFormat);
                        }
                    }
                    else if (inMessage.startsWith("KEYS")) { // +++
                        if (messages.length == 1) { // +++
                            if (hashMap.isEmpty()) { // +++
                                System.out.println(redColorCode + italicColorCode + clientName + ": " + inMessage + " -> The Map is Empty right now!" + resetColorCode);
                                out.println(redColorCode + italicColorCode + TCPClient.displayTime() + " The Map is Empty right now!" + resetColorCode);
                            } else { // +++
                                System.out.println(successfullCommand);
                                out.println(hashMap.size());
                                for (String key : hashMap.keySet()) {
                                    out.println(greenColorCode + italicColorCode + "<" + key + "> -> <" + hashMap.get(key) + ">" + resetColorCode);
                                }
                            }
                        } else { // +++
                            System.out.println(incorrectFormat);
                        }
                    } else if (inMessage.startsWith("DISCONNECT CLIENT") && clientName.startsWith("Admin")){
                        if (messages.length == 3) {
                            if (usernames.containsKey(messages[2])) {
                                out.println(greenColorCode + italicColorCode + TCPClient.displayTime() + " The Client (" + messages[2] + ") successfully Kicked from the Server!" + resetColorCode);
                                connectedSockets.get(usernames.get(messages[2])).close(); // Kicking the Client
                                System.out.println(blueColorCode + italicColorCode + TCPClient.displayTime() + " Client (" + messages[2] + ")" + resetColorCode + blueColorCode + " [" + client.getLocalAddress() + " : " + client.getPort() + "]  Kicked by the Admin!" + resetColorCode);
                                usernames.remove(messages[2]);
                                System.out.println(successfullCommand);
                            } else { // +++
                                out.println(redColorCode + italicColorCode + TCPClient.displayTime() + " There is no such Client in the Server!" + resetColorCode);
                                System.out.println(redColorCode + italicColorCode + clientName + ": " + inMessage + " -> There is no such Client in the Server!" + resetColorCode);
                            }
                        } else { // +++
                            System.out.println(incorrectFormat);
                        }
                    } else if (inMessage.startsWith("VIEW CLIENTS") && clientName.startsWith("Admin")){ // +++
                        if (messages.length == 2){ // +++
                            out.println(greenColorCode + italicColorCode + TCPClient.displayTime() + " Clients in the Server: " + usernames.keySet() + resetColorCode);
                            System.out.println(successfullCommand);
                        } else { // +++
                            System.out.println(incorrectFormat);
                        }
                    }
                    else if (inMessage.startsWith("QUIT")){ // +++
                        if (messages.length == 1){ // +++
                            usernames.remove(username);
                            System.out.println(blueColorCode + TCPClient.displayTime() + clientName + " [" + client.getLocalAddress() + " : " + client.getPort() + "] Disconnected!" + resetColorCode);
                            out.println(blueColorCode + TCPClient.displayTime() + clientName + " [" + client.getLocalAddress() + " : " + client.getPort() + "] Disconnected!" + resetColorCode);
                        } else { // +++
                            System.out.println(incorrectFormat);
                        }
                    } else { // +++
                        System.out.println(redColorCode + italicColorCode + clientName + ": " + inMessage + " -> There is no such Command!" + resetColorCode);
                    }
                }
            } catch (IOException e){
                // QUIT
            }
        }

        public void sendMessage(String message){
            out.println(message);
        }
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        server.run();
    }
}