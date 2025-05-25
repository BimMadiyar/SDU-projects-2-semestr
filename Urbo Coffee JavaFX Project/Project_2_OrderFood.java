package com.example.myjavafxapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import java.util.*;
import java.util.List;

public class Project_2_OrderFood extends Application {
    private Scene scene;
    private HBox hBox;
    private Menu booking;
    private Menu menu;
    private Menu contact;

//----------FOOD QUANTITIES--------//
    private int quantity1 = 0;
    private int quantity2 = 0;
    private int quantity3 = 0;
    private int quantity4 = 0;
    private int quantity5 = 0;
    private int quantity6 = 0;
    private int quantity7 = 0;
    private int quantity8 = 0;
    private int quantity9 = 0;

//---------BOOKING ATTRIBUTES--------//
    private String nameInBooking = "";
    private TextField nameTextFieldInBooking = new TextField("");
    private String emailInBooking = "";
    private TextField emailTextFieldInBooking = new TextField("");
    private String phone = "";
    private TextField phoneTextField = new TextField("");
    private LocalDate localDate = LocalDate.now();
    private DatePicker datePicker = new DatePicker(LocalDate.now());
    private String time = "09:00";
    private ComboBox<String> timePicker = new ComboBox<>();
    private String numberOfPeople = " 2 people";
    private ComboBox<String> peoplePicker = new ComboBox<>();
    private TextArea foodsTextArea = new TextArea();

//---------CONTACT ATTRIBUTES--------//
    private TextField nameTextFieldInContact = new TextField();
    private TextField emailTextFieldInContact = new TextField();
    private TextArea opinionTextArea = new TextArea();
    private String nameInContact;
    private String emailInContact;
    private String opinion;
    private String stars = "☆☆☆☆☆";
    private int numOfStars = 0;

//    private List<String> ppl = Arrays.asList(" 1 person", " 2 people", " 3 people")

    public void start(Stage primaryStage){
        peoplePicker.setValue(" 2 people");

        Image urbo_icon = new Image("file:C:/Program Files/Java/urbo_icon.jpg");
        primaryStage.getIcons().add(urbo_icon);
        primaryStage.setResizable(false);

        booking = new Menu("Booking");
        booking.setStyle("-fx-color: black; -fx-min-height: 30px;");
        MenuItem booking1 = new MenuItem("Booking");
        booking1.setStyle("-fx-color: white;");
        booking.getItems().add(booking1);

        Menu dotMenuItem = new Menu(".");
        dotMenuItem.setStyle("-fx-padding: 0 1 0 1; -fx-font-weight: bold; -fx-color: black; -fx-min-height: 24px;");

        menu = new Menu("Menu");
        menu.setStyle("-fx-color: black; -fx-min-height: 30px;");
        MenuItem menu1 = new MenuItem("Menu");
        menu1.setStyle("-fx-color: white;");
        menu.getItems().add(menu1);

        contact = new Menu("Contact");
        contact.setStyle("-fx-color: black; -fx-min-height: 30px;");
        MenuItem contact1 = new MenuItem("Contact");
        contact1.setStyle("-fx-color: white;");
        contact.getItems().add(contact1);

        Menu space = new Menu(" ");
        space.setStyle("-fx-padding: 0 160 0 160; -fx-min-height: 30px");
        space.setDisable(true);

        Menu home = new Menu("Home  ·  Urbo Coffee");
        home.setStyle("-fx-min-height: 30px; -fx-color: black; -fx-font-weight: bold; -fx-font-posture: italic;");
        MenuItem homeItem = new MenuItem("Home");
        homeItem.setStyle("-fx-color: white;");
        home.getItems().add(homeItem);
        home.setOnAction(e -> {
            saveContactData();
            saveBookingData();
            start(primaryStage);
        });

        MenuBar menuBar = new MenuBar(contact, dotMenuItem,
                menu, dotMenuItem, booking);
        menuBar.setStyle("-fx-background-color: black;");
        menuBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        menuBar.setMinHeight(30);

        MenuBar menuBar1 = new MenuBar(home);
        menuBar1.setStyle("-fx-background-color: black;");

        hBox = new HBox(menuBar1, menuBar);
        hBox.setSpacing(355);
        hBox.setStyle("-fx-background-color: black;");

//------------------------------------------EAT DRINK VISIT----------------------------------------------------//

        Image eatDrinkVisit = new Image("file:C:/Program Files/Java/eatDrinkVisit.jpg/");
        ImageView imageViewVisit = new ImageView(eatDrinkVisit);
        imageViewVisit.setFitWidth(700);
        imageViewVisit.setFitHeight(270);

        Text visit = new Text("EAT DRINK VISIT");
        visit.setFont(Font.font("Impact", FontWeight.BOLD, 50));
        visit.setFill(Color.BLACK);
        visit.setStroke(Color.WHITE);

        StackPane stackPane = new StackPane(imageViewVisit, visit);

//------------------------------------------WELCOME TO URBO COFFEE---------------------------------------------//

        Pane pane3 = new Pane();
        pane3.setPrefSize(700, 10);

        Line line5 = new Line(0, 0, 698, 0);
        line5.setStrokeWidth(2.5);

        Label welcome = new Label("Welcome to URBO Coffee");
        welcome.setTextFill(Color.BLACK);
        welcome.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 25));
        Text modernDishes = new Text("Modern dishes that capture the flavors of the season");
        modernDishes.setFont(Font.font("Times New Roman", 16));
        modernDishes.setFill(Color.BLACK);

        Line line6 = new Line(0, 0, 698, 0);
        line6.setStrokeWidth(2.5);

        Pane pane4 = new Pane();
        pane4.setPrefSize(700, 10);

        VBox vBox6 = new VBox(pane3, line5, welcome, modernDishes, line6, pane4);
        vBox6.setAlignment(Pos.CENTER);
        vBox6.setSpacing(10);

//-------------------------------------DISCOVER MENU and SAMPLE MENU-------------------------------------------//

        Image discoverMenu = new Image("file:C:/Program Files/Java/discoverMenu.jpg");
        ImageView imageViewDiscover = new ImageView(discoverMenu);
        imageViewDiscover.setFitWidth(250);
        imageViewDiscover.setFitHeight(437.5);

        Text discoverOurMenu = new Text("Discover Our Menu");
        discoverOurMenu.setFont(Font.font("Impact", 30));
        discoverOurMenu.setFill(Color.BLACK);
        discoverOurMenu.setStroke(Color.WHITE);

        StackPane stackPane1 = new StackPane(imageViewDiscover, discoverOurMenu);

        Text sampleMenu = new Text("Sample Menu");
        sampleMenu.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 25));
        sampleMenu.setFill(Color.BLACK);

        Line line1 = new Line(0, 0, 0, 79);
        line1.setStrokeWidth(2.5);
        Text food1 = new Text("Oatmeal Porridge");
        food1.setFont(Font.font("Times New Roman", 20));
        Text ingredient1 = new Text("OATMEAL PORRIDGE WITH MILK/WATER WITH BUTTER AND" +
                "\nBAKED APPLE IN HONEY");
        ingredient1.setFont(Font.font("Times New Roman", 12));
        Text price1 = new Text("900 KZT");
        price1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
        VBox vBox8 = new VBox(food1, ingredient1, price1);
        vBox8.setSpacing(8);
        HBox hBox4 = new HBox(line1, vBox8);
        hBox4.setSpacing(10);

        Line line2 = new Line(0, 0, 0, 79);
        line2.setStrokeWidth(2.5);
        Text food2 = new Text("English Breakfast");
        food2.setFont(Font.font("Times New Roman", 20));
        Text ingredient2 = new Text("BREAKFAST WITH FRIED EGGS, HUNTING SAUSAGES, SMOKED ZHAYA," +
                "\nBEANS AND MUSHROOMS");
        ingredient2.setFont(Font.font("Times New Roman", 12));
        Text price2 = new Text("2990 KZT");
        price2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
        VBox vBox9 = new VBox(food2, ingredient2, price2);
        vBox9.setSpacing(8);
        HBox hBox5 = new HBox(line2, vBox9);
        hBox5.setSpacing(10);

        Line line3 = new Line(0, 0, 0, 79);
        line3.setStrokeWidth(2.5);
        Text food3 = new Text("Cheesecakes");
        food3.setFont(Font.font("Times New Roman", 20));
        Text ingredient3 = new Text("COUNTRY COTTAGE CHEESE PANCAKES WITH CHERRY JAM AND" +
                "\nSOUR CREAM");
        ingredient3.setFont(Font.font("Times New Roman", 12));
        Text price3 = new Text("1900 KZT");
        price3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
        VBox vBox10 = new VBox(food3, ingredient3, price3);
        vBox10.setSpacing(8);
        HBox hBox6 = new HBox(line3, vBox10);
        hBox6.setSpacing(10);

        Rectangle rectangle1 = new Rectangle(98, 48);
        rectangle1.setFill(Color.rgb(220, 29, 29));
        rectangle1.setArcWidth(25);
        rectangle1.setArcHeight(25);
        Rectangle rectangle = new Rectangle(92, 42);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        Button mainMenu = new Button("Main Menu");
        mainMenu.setTextFill(Color.BLACK);
        mainMenu.setPrefSize(85, 35);
        mainMenu.setFont(Font.font(null, FontWeight.BOLD, 12));
        mainMenu.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 200%, repeat, maroon 0%, red 75%); -fx-background-radius: 8;");
        mainMenu.setOnMouseEntered(e -> {
            mainMenu.setTextFill(Color.DARKGREY);
            rectangle.setFill(Color.DARKGREY);
        });
        mainMenu.setOnMouseExited(e -> {
            mainMenu.setTextFill(Color.BLACK);
            rectangle.setFill(Color.BLACK);
        });
        mainMenu.setOnAction(e -> menuPage());
        StackPane stackPane2 = new StackPane(rectangle1, rectangle, mainMenu);
        HBox hBox7 = new HBox(stackPane2);
        hBox7.setAlignment(Pos.CENTER);

        VBox vBox11 = new VBox(sampleMenu, hBox4, hBox5, hBox6, hBox7);
        vBox11.setAlignment(Pos.CENTER_LEFT);
        vBox11.setSpacing(25);

        HBox hBox3 = new HBox(stackPane1, vBox11);
        hBox3.setSpacing(25);

//------------------------------------------BOOK TABLE IMAGE---------------------------------------------------//

        Pane pane = new Pane();
        pane.setPrefSize(700, 0);
        Pane pane1 = new Pane();
        pane1.setPrefSize(700, 0);

        Line line = new Line(0, 0, 698, 0);
        line.setStrokeWidth(2.5);

        Image bookTable = new Image("file:C:/Program Files/Java/bookTable.jpg");
        ImageView imageViewBookTable = new ImageView(bookTable);
        imageViewBookTable.setFitWidth(700);
        imageViewBookTable.setFitHeight(300);

        Text bookYourTable = new Text("Book Your Table");
        bookYourTable.setFont(Font.font("Impact", FontPosture.ITALIC, 50));
        bookYourTable.setFill(Color.BLACK);
        bookYourTable.setStroke(Color.WHITE);

        StackPane stackPane3 = new StackPane(imageViewBookTable, bookYourTable);

        Rectangle rectangle2 = new Rectangle(98, 48);
        rectangle2.setFill(Color.rgb(220, 29, 29));
        rectangle2.setArcWidth(25);
        rectangle2.setArcHeight(25);
        Rectangle rectangle3 = new Rectangle(92, 42);
        rectangle3.setArcWidth(20);
        rectangle3.setArcHeight(20);
        Button bookingButton = new Button("Booking");
        bookingButton.setTextFill(Color.BLACK);
        bookingButton.setPrefSize(85, 35);
        bookingButton.setFont(Font.font(null, FontWeight.BOLD, 14));
        bookingButton.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 200%, repeat, maroon 0%, red 75%); -fx-background-radius: 8;");
        bookingButton.setOnMouseEntered(e -> {
            bookingButton.setTextFill(Color.DARKGREY);
            rectangle3.setFill(Color.DARKGREY);
        });
        bookingButton.setOnMouseExited(e -> {
            bookingButton.setTextFill(Color.BLACK);
            rectangle3.setFill(Color.BLACK);
        });
        bookingButton.setOnAction(e -> bookingPage());
        StackPane stackPane4 = new StackPane(rectangle2, rectangle3, bookingButton);
        HBox hBox8 = new HBox(stackPane4);
        hBox8.setAlignment(Pos.CENTER);

        Line line4 = new Line(0, 0, 698, 0);
        line4.setStrokeWidth(2.5);

        VBox vBox12 = new VBox(pane, line, stackPane3, hBox8, line4, pane1);
        vBox12.setSpacing(25);

        VBox vBox = new VBox(stackPane, vBox6, hBox3, vBox12, leaveAReply(30));

        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setVvalue(-1.3);


        VBox vBox7 = new VBox(hBox, scrollPane);

        scene = new Scene(vBox7, 715, 750);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Urbo Coffee");
        primaryStage.show();

//---------------------------------------------BOOKING----------------------------------------------------------//

        booking.setOnAction(e -> {
            bookingPage();
        });

//-----------------------------------------------MENU-----------------------------------------------------------//

        menu.setOnAction(e -> {
            menuPage();
        });

//----------------------------------------------CONTACT US------------------------------------------------------//

        contact.setOnAction(e -> {
            saveBookingData();
            booking.setStyle("-fx-color: black; -fx-min-height: 30px; -fx-font-weight: normal;");
            menu.setStyle("-fx-color: black; -fx-min-height: 30px; -fx-font-weight: normal;");
            contact.setStyle("-fx-color: black; -fx-min-height: 30px; -fx-font-weight: bold; -fx-font-size: 11.5px");

            DropShadow shadow = new DropShadow();
            shadow.setOffsetY(3.0f);
            shadow.setColor(Color.color(0.4f, 0.4f, 0.4f));
            Label contactLabel = new Label("Contact");
            contactLabel.setEffect(shadow);
            contactLabel.setCache(true);
            contactLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
            HBox hBox1 = new HBox(contactLabel);
            hBox1.setAlignment(Pos.CENTER);
            hBox1.setPadding(new Insets(30));

            Label contactUs = new Label("Contact Us");
            contactUs.setFont(Font.font("Times New Roman", FontWeight.BOLD, 35));
            contactUs.setTextFill(Color.WHITE);

            Image insta_logo = new Image("file:C:/Program Files/Java/insta_logo.png");
            ImageView imageView = new ImageView(insta_logo);
            imageView.setFitWidth(35);
            imageView.setFitHeight(35);
            imageView.setOnMouseClicked(event -> {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.instagram.com/urbocoffee/"));
                } catch (IOException | URISyntaxException t) {
                    t.printStackTrace();
                }
            });
            imageView.setOnMouseEntered(er -> {
                imageView.setFitWidth(42);
                imageView.setFitHeight(42);
            });
            imageView.setOnMouseExited(err -> {
                imageView.setFitWidth(35);
                imageView.setFitHeight(35);
            });


            Image link_logo = new Image("file:/C:/Program Files/Java/link_logo.png");
            ImageView imageView1 = new ImageView(link_logo);
            imageView1.setFitWidth(35);
            imageView1.setFitHeight(35);
            imageView1.setOnMouseClicked(event -> {
                try {
                    Desktop.getDesktop().browse(new URI("https://urbocoffee.kz/"));
                } catch (IOException | URISyntaxException t) {
                    t.printStackTrace();
                }
            });
            imageView1.setOnMouseEntered(er -> {
                imageView1.setFitWidth(42);
                imageView1.setFitHeight(42);
            });
            imageView1.setOnMouseExited(err -> {
                imageView1.setFitWidth(35);
                imageView1.setFitHeight(35);
            });

            HBox instaHBox = new HBox(contactUs, imageView, imageView1);
            instaHBox.setSpacing(10);

            Text donTHesitate = new Text("Don't hesitate to reach out with the contact" +
                    " information below,\nor send a message using the form.");
            donTHesitate.setFill(Color.WHITE);
            donTHesitate.setFont(Font.font("Family", FontWeight.BOLD, 12));

            Text requiredName = new Text("Name");
            requiredName.setFill(Color.WHITE);
            requiredName.setFont(Font.font("Family", FontWeight.BOLD, 12));
            nameTextFieldInContact = new TextField(nameInContact);
            nameTextFieldInContact.setPromptText("Enter your name here");
            nameTextFieldInContact.setMinSize(240, 30);
            VBox vBox2 = new VBox(requiredName, nameTextFieldInContact);
            vBox2.setSpacing(5);

            Text requiredEmail = new Text("Email");
            requiredEmail.setFill(Color.WHITE);
            requiredEmail.setFont(Font.font("Family", FontWeight.BOLD, 12));
            emailTextFieldInContact = new TextField(emailInContact);
            emailTextFieldInContact.setPromptText("Enter your email here");
            emailTextFieldInContact.setMinSize(240, 30);
            VBox vBox3 = new VBox(requiredEmail, emailTextFieldInContact);
            vBox3.setSpacing(5);

            HBox hBox2 = new HBox(vBox2, vBox3);
            hBox2.setSpacing(25);

            Text opinionText = new Text("Opinion about URBO");
            opinionText.setFill(Color.WHITE);
            opinionText.setFont(Font.font("Family", FontWeight.BOLD, 12));
            opinionTextArea = new TextArea(opinion);
            opinionTextArea.setPromptText("Enter your opinion about URBO here");
            VBox vBox4 = new VBox(opinionText, opinionTextArea);
            vBox4.setSpacing(5);

            Text star1 = new Text("☆");
            star1.setFont(Font.font(null, 45));
            Text star2 = new Text("☆");
            star2.setFont(Font.font(null, 45));
            Text star3 = new Text("☆");
            star3.setFont(Font.font(null, 45));
            Text star4 = new Text("☆");
            star4.setFont(Font.font(null, 45));
            Text star5 = new Text("☆");
            star5.setFont(Font.font(null, 45));
            HBox starHBox = new HBox(star1, star2, star3, star4, star5);

            star1.setOnMouseEntered(event -> {
                star1.setText("★");
                star2.setText("☆");
                star3.setText("☆");
                star4.setText("☆");
                star5.setText("☆");
                star1.setOnMouseExited(event1 -> {
                    star1.setText("☆");
                    star2.setText("☆");
                    star3.setText("☆");
                    star4.setText("☆");
                    star5.setText("☆");
                    stars = "☆☆☆☆☆";
                    numOfStars = 0;
                });
            });
            star2.setOnMouseEntered(event -> {
                star1.setText("★");
                star2.setText("★");
                star3.setText("☆");
                star4.setText("☆");
                star5.setText("☆");
                star2.setOnMouseExited(event1 -> {
                    star1.setText("☆");
                    star2.setText("☆");
                    star3.setText("☆");
                    star4.setText("☆");
                    star5.setText("☆");
                    stars = "☆☆☆☆☆";
                    numOfStars = 0;
                });
            });
            star3.setOnMouseEntered(event -> {
                star1.setText("★");
                star2.setText("★");
                star3.setText("★");
                star4.setText("☆");
                star5.setText("☆");
                star3.setOnMouseExited(event1 -> {
                    star1.setText("☆");
                    star2.setText("☆");
                    star3.setText("☆");
                    star4.setText("☆");
                    star5.setText("☆");
                    stars = "☆☆☆☆☆";
                    numOfStars = 0;
                });
            });
            star4.setOnMouseEntered(event -> {
                star1.setText("★");
                star2.setText("★");
                star3.setText("★");
                star4.setText("★");
                star5.setText("☆");
                star4.setOnMouseExited(event1 -> {
                    star1.setText("☆");
                    star2.setText("☆");
                    star3.setText("☆");
                    star4.setText("☆");
                    star5.setText("☆");
                    stars = "☆☆☆☆☆";
                    numOfStars = 0;
                });
            });
            star5.setOnMouseEntered(event -> {
                star1.setText("★");
                star2.setText("★");
                star3.setText("★");
                star4.setText("★");
                star5.setText("★");
                star5.setOnMouseExited(event1 -> {
                    star1.setText("☆");
                    star2.setText("☆");
                    star3.setText("☆");
                    star4.setText("☆");
                    star5.setText("☆");
                    stars = "☆☆☆☆☆";
                    numOfStars = 0;
                });
            });


            star1.setOnMouseClicked(event -> {
                star1.setText("★");
                star2.setText("☆");
                star3.setText("☆");
                star4.setText("☆");
                star5.setText("☆");
                star1.setOnMouseExited(null);
                stars = "★☆☆☆☆";
                numOfStars = 1;
            });
            star2.setOnMouseClicked(event -> {
                star1.setText("★");
                star2.setText("★");
                star3.setText("☆");
                star4.setText("☆");
                star5.setText("☆");
                star2.setOnMouseExited(null);
                stars = "★★☆☆☆";
                numOfStars = 2;
            });
            star3.setOnMouseClicked(event -> {
                star1.setText("★");
                star2.setText("★");
                star3.setText("★");
                star4.setText("☆");
                star5.setText("☆");
                star3.setOnMouseExited(null);
                stars = "★★★☆☆";
                numOfStars = 3;
            });
            star4.setOnMouseClicked(event -> {
                star1.setText("★");
                star2.setText("★");
                star3.setText("★");
                star4.setText("★");
                star5.setText("☆");
                star4.setOnMouseExited(null);
                stars = "★★★★☆";
                numOfStars = 4;
            });
            star5.setOnMouseClicked(event -> {
                star1.setText("★");
                star2.setText("★");
                star3.setText("★");
                star4.setText("★");
                star5.setText("★");
                star5.setOnMouseExited(null);
                stars = "★★★★★";
                numOfStars = 5;
            });


            Button contactUsButton = new Button("Submit");
            contactUsButton.setPrefSize(100, 20);
            contactUsButton.setStyle("-fx-background-color: white; -fx-background-radius: 15");
            contactUsButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            contactUsButton.setTextFill(Color.BLACK);
            HBox contactButtonHBox = new HBox(starHBox, contactUsButton);
            contactButtonHBox.setSpacing(175);
            contactButtonHBox.setAlignment(Pos.CENTER_RIGHT);
            contactUsButton.setOnMouseEntered(event -> contactUsButton.setTextFill(Color.rgb(220, 29, 29)));
            contactUsButton.setOnMouseExited(event -> contactUsButton.setTextFill(Color.BLACK));
            contactUsButton.setOnAction(event -> {
                saveContactData();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Opinion about URBO");
                alert.setHeaderText("Your opinion about URBO confirmed successfully!");
                alert.setContentText("Name:\n" + nameInContact +
                        "\n\nEmail:\n" + emailInContact +
                        "\n\nOpinion:\n" + opinion +
                        "\n\nStars:\n" + stars + "\n" + numOfStars + " stars");
                alert.showAndWait();
                deleteContactData();
            });

            VBox vBox1 = new VBox(instaHBox, donTHesitate, hBox2, vBox4, contactButtonHBox);
            vBox1.setSpacing(20);

            Text schedule  = new Text("Schedule: ");
            schedule.setFill(Color.WHITE);
            schedule.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
            Text monday    = new Text("Monday         9:00 - 19:00");
            monday.setFill(Color.WHITE);
            monday.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
            Text tuesday   = new Text("Tuesday         9:00 - 19:00");
            tuesday.setFill(Color.WHITE);
            tuesday.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
            Text wednesday = new Text("Wednesday    9:00 - 19:00");
            wednesday.setFill(Color.WHITE);
            wednesday.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
            Text thursday  = new Text("Thursday       9:00 - 19:00");
            thursday.setFill(Color.WHITE);
            thursday.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
            Text friday    = new Text("Friday            9:00 - 19:00");
            friday.setFill(Color.WHITE);
            friday.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
            Text saturday  = new Text("Saturday                Closed");
            saturday.setFill(Color.WHITE);
            saturday.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
            Text sunday    = new Text("Sunay                     Closed");
            sunday.setFill(Color.WHITE);
            sunday.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));

            VBox vBox5 = new VBox(schedule, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
            vBox5.setSpacing(10);
            vBox5.setAlignment(Pos.CENTER_LEFT);

            HBox mainHBox = new HBox(vBox1, vBox5);
            mainHBox.setSpacing(10);
            mainHBox.setStyle("-fx-background-color: rgb(220, 29, 29);");
            mainHBox.setPadding(new Insets(30));
            mainHBox.setLayoutY(400);

            VBox subVBox = new VBox(hBox1, mainHBox, leaveAReply(40));

            VBox mainVBox = new VBox(hBox, subVBox);
            hBox.setAlignment(Pos.TOP_CENTER);

            scene.setRoot(mainVBox);
        });
    }

//--------------------------------------BOOKING PAGE METHOD---------------------------------------------------//
    private void bookingPage(){
        saveContactData();
        booking.setStyle("-fx-font-weight: bold; -fx-color: black; -fx-min-height: 30px; -fx-font-size: 11.5px");
        menu.setStyle("-fx-color: black; -fx-min-height: 30px; -fx-font-weight: normal;");
        contact.setStyle("-fx-color: black; -fx-min-height: 30px; -fx-font-weight: normal;");

        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(3.0f);
        shadow.setColor(Color.color(0.4f, 0.4f, 0.4f));
        Text bookingLabel = new Text("B O O K I N G");
        bookingLabel.setStroke(Color.BLACK);
        bookingLabel.setEffect(shadow);
        bookingLabel.setCache(true);
        bookingLabel.setFont(Font.font("Impact", FontWeight.BOLD, 40));
        bookingLabel.setFill(Color.WHITE);
        HBox hBox1 = new HBox(bookingLabel);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setPadding(new Insets(20));

        Line line = new Line(0, 0, 715, 0);
        line.setStrokeWidth(2.5);


        Text nameText = new Text("Name (required)");
        nameText.setFont(Font.font(null, FontWeight.BOLD, 15));
        nameText.setFill(Color.WHITE);
        nameTextFieldInBooking = new TextField(nameInBooking);
        nameTextFieldInBooking.setPromptText("Enter your name here");
        nameTextFieldInBooking.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        nameTextFieldInBooking.setPrefSize(350, 30);
        VBox vBox2 = new VBox(nameText, nameTextFieldInBooking);
        vBox2.setSpacing(5);

        Text emailText = new Text("Email (required)");
        emailText.setFill(Color.WHITE);
        emailText.setFont(Font.font(null, FontWeight.BOLD, 15));
        emailTextFieldInBooking = new TextField(emailInBooking);
        emailTextFieldInBooking.setPromptText("Enter your email here");
        emailTextFieldInBooking.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        emailTextFieldInBooking.setPrefSize(350, 30);
        VBox vBox3 = new VBox(emailText, emailTextFieldInBooking);
        vBox3.setSpacing(5);

        Text phoneText = new Text("Phone (required)");
        phoneText.setFill(Color.WHITE);
        phoneText.setFont(Font.font(null, FontWeight.BOLD, 15));
        phoneTextField = new TextField(phone);
        phoneTextField.setPromptText("Enter your phone number here");
        phoneTextField.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        phoneTextField.setPrefSize(700, 30);
        VBox vBox4 = new VBox(phoneText, phoneTextField);
        vBox4.setSpacing(5);

        HBox hBox2 = new HBox(vBox2, vBox3);
        hBox2.setSpacing(10);
        VBox vBox1 = new VBox(hBox2, vBox4);
        vBox1.setSpacing(5);
        vBox1.setPadding(new Insets(20));


        Line line1 = new Line(0, 0, 715, 0);
        line1.setStrokeWidth(2.5);


        Text dateText = new Text("Date");
        dateText.setFill(Color.WHITE);
        dateText.setFont(Font.font(null, FontWeight.BOLD, 15));
        datePicker = new DatePicker();
        datePicker.setValue(localDate);
        datePicker.setEditable(false);
        datePicker.setStyle("-fx-font-family: Arial; -fx-font-weight: bold; -fx-font-size: 13;");
        datePicker.setPrefSize(350, 30);
        VBox vBox5 = new VBox(dateText, datePicker);
        vBox5.setSpacing(5);

        Image iconImage1 = new Image("file:C:/Program Files/Java/clock_icon.png");
        ImageView iconImageView1 = new ImageView(iconImage1);
        iconImageView1.setFitWidth(22);
        iconImageView1.setFitHeight(20);
        Text timeText = new Text("Time (required)");
        timeText.setFill(Color.WHITE);
        timeText.setFont(Font.font(null, FontWeight.BOLD, 15));
        timePicker = new ComboBox<>();
        timePicker.setValue(time);
        timePicker.setButtonCell(new IconListCell<>("09:00", iconImageView1));
        timePicker.setPromptText("09:00");
        timePicker.getItems().addAll("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
                "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
                "18:00", "18:30");
        timePicker.setStyle("-fx-font-family: Arial; -fx-font-weight: bold; -fx-font-size: 13; -fx-color: white;");
        timePicker.setPrefSize(350, 30);
        VBox vBox6 = new VBox(timeText, timePicker);
        vBox6.setSpacing(5);

        Image iconImage = new Image("file:C:/Program Files/Java/person_icon.png");
        ImageView iconImageView = new ImageView(iconImage);
        iconImageView.setFitWidth(17);
        iconImageView.setFitHeight(20);
        Text peopleText = new Text("Number of People");
        peopleText.setFill(Color.WHITE);
        peopleText.setFont(Font.font(null, FontWeight.BOLD, 15));
        peoplePicker = new ComboBox<>();
        peoplePicker.setValue(numberOfPeople);
        peoplePicker.setButtonCell(new IconListCell<>("2 people", iconImageView));
        peoplePicker.setPromptText(" 2 people");
        peoplePicker.getItems().addAll(" 1 person", " 2 people", " 3 people", " 4 people", " 5 people",
                " 6 people", " 7 people", " 8 people", " 9 people", " 10 people");
        peoplePicker.setStyle("-fx-font-family: Arial; -fx-font-weight: bold; -fx-font-size: 13; -fx-color: white");
        peoplePicker.setPrefSize(700, 30);
        VBox vBox7 = new VBox(peopleText, peoplePicker);
        vBox7.setSpacing(5);

        HBox hBox3 = new HBox(vBox5, vBox6);
        hBox3.setSpacing(10);
        VBox vBox8 = new VBox(hBox3, vBox7);
        vBox8.setSpacing(5);
        vBox8.setPadding(new Insets(20));

        Line line2 = new Line(0, 0, 715, 0);
        line2.setStrokeWidth(2.5);

        Text foodsText = new Text("Total Cost of Menu and Booking");
        foodsText.setFill(Color.WHITE);
        foodsText.setFont(Font.font(null, FontWeight.BOLD, 15));
        foodsTextArea = new TextArea(getMenuAndBooking());
        peoplePicker.setOnAction(e -> foodsTextArea.setText(getMenuAndBooking()));
        foodsTextArea.setPromptText("You can choose your Food in Menu Page!");
        foodsTextArea.setEditable(false);
        foodsTextArea.setPrefSize(700, 160);
        foodsTextArea.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        VBox textAreaVBox = new VBox(foodsText, foodsTextArea);
        textAreaVBox.setSpacing(5);
        textAreaVBox.setPadding(new Insets(20));

        Line line3 = new Line(0, 0, 715, 0);
        line3.setStrokeWidth(2.5);

        Text vipText = new Text("FOR  BOOKING  VIP  CABINS  (URBO): ");
        vipText.setFont(Font.font("Impact", 30));
        vipText.setStroke(Color.BLACK);
        vipText.setFill(Color.WHITE);
        Image link = new Image("file:C:/Program Files/Java/link_logo1.png");
        ImageView imageView = new ImageView(link);
        imageView.setFitWidth(42);
        imageView.setFitHeight(42);
        Rectangle clip = new Rectangle(42, 42);
        clip.setArcHeight(30);
        clip.setArcWidth(30);
        imageView.setClip(clip);
        imageView.setOnMouseClicked(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://urbo.restoplace.ws/"));
            } catch (IOException | URISyntaxException t) {
                t.printStackTrace();
            }
        });
        imageView.setOnMouseEntered(e -> {
            imageView.setFitWidth(46);
            imageView.setFitHeight(46);
            clip.setWidth(46);
            clip.setHeight(46);
        });
        imageView.setOnMouseExited(e -> {
            imageView.setFitWidth(42);
            imageView.setFitHeight(42);
            clip.setWidth(42);
            clip.setHeight(42);
        });
        Line line4 = new Line(0, 0, 0, 40);
        line4.setStrokeWidth(2.5);
        Button bookButton = new Button("B O O K");
        bookButton.setFont(Font.font("Impact", 20));
        bookButton.setStyle("-fx-background-color: white; -fx-background-radius: 25;");
        bookButton.setPrefSize(150, 40);
        bookButton.setOnMouseEntered(e -> bookButton.setTextFill(Color.rgb(200, 10, 10)));
        bookButton.setOnMouseExited(e -> bookButton.setTextFill(Color.BLACK));
        bookButton.setOnAction(e -> {
            saveBookingData();
            if (nameInBooking == null || emailInBooking == null || phone == null || nameInBooking.strip().isEmpty() || emailInBooking.strip().isEmpty() || phone.strip().isEmpty()) {
                showErrorAlert();
            } else showBookAlert();
        });
        HBox vipHBox = new HBox(vipText, imageView, line4, bookButton);
        vipHBox.setAlignment(Pos.CENTER_LEFT);
        vipHBox.setSpacing(20);
        vipHBox.setPadding(new Insets(20));


        VBox vBox = new VBox(hBox, hBox1, line, vBox1, line1, vBox8, line2, textAreaVBox, line3, vipHBox);
        vBox.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 200%, repeat, rgb(96,3,3) 0%, red 80%)");

        scene.setRoot(vBox);
    }

//-----------------------------------------MENU PAGE METHOD---------------------------------------------------//
    private void menuPage(){
        saveContactData();
        saveBookingData();
        booking.setStyle("-fx-color: black; -fx-min-height: 30px; -fx-font-weight: normal;");
        menu.setStyle("-fx-color: black; -fx-min-height: 30px;-fx-font-weight: bold; -fx-font-size: 12px");
        contact.setStyle("-fx-color: black; -fx-min-height: 30px; -fx-font-weight: normal;");

        ScrollPane scrollPane = new ScrollPane();

        Button breakfastButton = new Button("Breakfasts");
        breakfastButton.setTextFill(Color.WHITE);
        breakfastButton.setFont(Font.font(null, FontWeight.BOLD, 12));
        breakfastButton.setStyle("-fx-background-color: orangered; -fx-background-radius: 15;");
        breakfastButton.setOnAction(e -> {
            scrollPane.setVvalue(0);
        });

        Button porridgeButton = new Button("Porridge");
        porridgeButton.setTextFill(Color.BLACK);
        porridgeButton.setFont(Font.font(null, FontWeight.BOLD, 12));
        porridgeButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
        porridgeButton.setOnAction(e -> {
            scrollPane.setVvalue(0.302);
        });

        Button soupButton = new Button("Soups");
        soupButton.setTextFill(Color.BLACK);
        soupButton.setFont(Font.font(null, FontWeight.BOLD, 12));
        soupButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
        soupButton.setOnAction(e -> {
            scrollPane.setVvalue(0.6);
        });

        Button drinkButton = new Button("Drinks");
        drinkButton.setTextFill(Color.BLACK);
        drinkButton.setFont(Font.font(null, FontWeight.BOLD, 12));
        drinkButton.setStyle("-fx-color: silver; -fx-background-radius: 15");
        drinkButton.setOnAction(e -> {
            scrollPane.setVvalue(0.87);
        });

        scrollPane.vvalueProperty().addListener((observable, oldValue, newValue) -> {
            if (scrollPane.getVvalue() >= 0 && scrollPane.getVvalue() <= 0.17) {
                breakfastButton.setTextFill(Color.WHITE);
                breakfastButton.setStyle("-fx-background-color: orangered; -fx-background-radius: 15;");
                porridgeButton.setTextFill(Color.BLACK);
                porridgeButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
                soupButton.setTextFill(Color.BLACK);
                soupButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
                drinkButton.setTextFill(Color.BLACK);
                drinkButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
            } else if (scrollPane.getVvalue() > 0.17 && scrollPane.getVvalue() <= 0.43) {
                breakfastButton.setTextFill(Color.BLACK);
                breakfastButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
                porridgeButton.setTextFill(Color.WHITE);
                porridgeButton.setStyle("-fx-background-color: orangered; -fx-background-radius: 15;");
                soupButton.setTextFill(Color.BLACK);
                soupButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
                drinkButton.setTextFill(Color.BLACK);
                drinkButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
            } else if (scrollPane.getVvalue() > 0.43 && scrollPane.getVvalue() <= 0.73) {
                breakfastButton.setTextFill(Color.BLACK);
                breakfastButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
                porridgeButton.setTextFill(Color.BLACK);
                porridgeButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
                soupButton.setTextFill(Color.WHITE);
                soupButton.setStyle("-fx-background-color: orangered; -fx-background-radius: 15;");
                drinkButton.setTextFill(Color.BLACK);
                drinkButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
            } else if (scrollPane.getVvalue() > 0.73 && scrollPane.getVvalue() <= 1) {
                breakfastButton.setTextFill(Color.BLACK);
                breakfastButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
                porridgeButton.setTextFill(Color.BLACK);
                porridgeButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
                soupButton.setTextFill(Color.BLACK);
                soupButton.setStyle("-fx-color: silver; -fx-background-radius: 15;");
                drinkButton.setTextFill(Color.WHITE);
                drinkButton.setStyle("-fx-background-color: orangered; -fx-background-radius: 15;");
            }
        });

        HBox buttonsHBox = new HBox(breakfastButton, porridgeButton, soupButton, drinkButton);
        buttonsHBox.setSpacing(15);
        buttonsHBox.setPadding(new Insets(7));

        Pane pane = new Pane();
        pane.setPrefSize(700, 0);
        Line breakfastStartLine = new Line(0, 0, 698, 0);
        breakfastStartLine.setStrokeWidth(2.5);
        Text breakfastText = new Text("   Breakfasts");
        breakfastText.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.ITALIC, 40));


        TextField totalCost = new TextField(getTotalCost() + " KZT");
        totalCost.setFont(Font.font(null, FontWeight.BOLD, 18));
        totalCost.setPrefSize(105, 38);
        totalCost.setEditable(false);

        Image breakfast1 = new Image("file:C:/Program Files/Java/breakfastFood1.jpg");
        ImageView imageView1 = new ImageView(breakfast1);
        imageView1.setFitWidth(200);
        imageView1.setFitHeight(200);
        Line line1 = new Line(0, 0, 0, 200);
        line1.setStrokeWidth(2.5);
        Text breakfastName1 = new Text("English Breakfast");
        breakfastName1.setFont(Font.font("Impact", 30));
        Text breakfastIng1 = new Text("BREAKFAST WITH FRIED EGGS, HUNTING SAUSAGES," +
                "\nSMOKED ZHAYA, BEANS AND MUSHROOMS");
        breakfastIng1.setFont(Font.font("Times New Roman", 17));
        Text breakfastPrice1 = new Text("2990 KZT");
        Button plusButton1 = new Button("+");
        plusButton1.setFont(Font.font("Impact"));
        plusButton1.setPrefSize(25, 20);
        breakfastPrice1.setFont(Font.font("Times New Roman", 20));
        Button minusButton1 = new Button("-");
        minusButton1.setFont(Font.font("Impact"));
        minusButton1.setPrefSize(25, 17);
        TextField textField1 = new TextField(Integer.toString(quantity1));
        textField1.setPrefSize(50, 17);
        textField1.setEditable(false);
        TextField textField11 = new TextField((quantity1 * 2990) + " KZT");
        textField11.setPrefSize(70, 17);
        textField11.setEditable(false);
        plusButton1.setOnAction(e -> {
            quantity1++;
            textField1.setText(Integer.toString(quantity1));
            textField11.setText((quantity1 * 2990) + " KZT");
            totalCost.setText(getTotalCost() + " KZT");
        });
        minusButton1.setOnAction(e -> {
            if (quantity1 > 0){
                quantity1--;
                textField1.setText(Integer.toString(quantity1));
                textField11.setText((quantity1 * 2990) + " KZT");
                totalCost.setText(getTotalCost() + " KZT");
            }
        });
        HBox kzhBox1 = new HBox(minusButton1, breakfastPrice1, plusButton1, textField1, textField11);
        kzhBox1.setSpacing(10);
        VBox vBox1 = new VBox(breakfastName1, breakfastIng1, kzhBox1);
        vBox1.setSpacing(25);
        vBox1.setAlignment(Pos.CENTER_LEFT);
//        HBox hBox1 = new HBox(imageView1, line1, vBox1);
//        hBox1.setSpacing(20);
//        hBox1.setAlignment(Pos.CENTER_LEFT);


        Image breakfast2 = new Image("file:C:/Program Files/Java/breakfastFood2.jpg");
        ImageView imageView2 = new ImageView(breakfast2);
        imageView2.setFitWidth(200);
        imageView2.setFitHeight(200);
        Line line2 = new Line(0, 0, 0, 200);
        line2.setStrokeWidth(2.5);
        Text breakfastName2 = new Text("Avocado Toast with Salmon");
        breakfastName2.setFont(Font.font("Impact", 30));
        Text breakfastIng2 = new Text("AVOCADO TOAST ON TARTINE WITH CREAM CHEESE," +
                "\nHOME-CURED SALMON AND POACHED EGG");
        breakfastIng2.setFont(Font.font("Times New Roman", 17));
        Text breakfastPrice2 = new Text("3990 KZT");
        breakfastPrice2.setFont(Font.font("Times New Roman", 18));
        Button plusButton2 = new Button("+");
        plusButton2.setFont(Font.font("Impact"));
        plusButton2.setPrefSize(25, 20);
        breakfastPrice2.setFont(Font.font("Times New Roman", 20));
        Button minusButton2 = new Button("-");
        minusButton2.setFont(Font.font("Impact"));
        minusButton2.setPrefSize(25, 17);
        TextField textField2 = new TextField(Integer.toString(quantity2));
        textField2.setPrefSize(50, 17);
        textField2.setEditable(false);
        TextField textField22 = new TextField((quantity2 * 3990) + " KZT");
        textField22.setPrefSize(70, 17);
        textField22.setEditable(false);
        plusButton2.setOnAction(e -> {
            quantity2++;
            textField2.setText(Integer.toString(quantity2));
            textField22.setText((quantity2 * 3990) + " KZT");
            totalCost.setText(getTotalCost() + " KZT");
        });
        minusButton2.setOnAction(e -> {
            if (quantity2 > 0){
                quantity2--;
                textField2.setText(Integer.toString(quantity2));
                textField22.setText((quantity2 * 3990) + " KZT");
                totalCost.setText(getTotalCost() + " KZT");
            }
        });
        HBox kzhBox2 = new HBox(minusButton2, breakfastPrice2, plusButton2, textField2, textField22);
        kzhBox2.setSpacing(10);
        VBox vBox2 = new VBox(breakfastName2, breakfastIng2, kzhBox2);
        vBox2.setSpacing(25);
        vBox2.setAlignment(Pos.CENTER_LEFT);
//        HBox hBox2 = new HBox(imageView2, line2, vBox2);
//        hBox2.setSpacing(20);
//        hBox2.setAlignment(Pos.CENTER_LEFT);

        GridPane gridPane = new GridPane();
        gridPane.add(imageView1, 0, 0);
        gridPane.add(line1, 1, 0);
        gridPane.add(vBox1, 2, 0);
        gridPane.add(imageView2, 0, 1);
        gridPane.add(line2, 1, 1);
        gridPane.add(vBox2, 2, 1);
        gridPane.setHgap(20);
        gridPane.setVgap(30);

        Line kashaStartLine = new Line(0, 0, 698, 0);
        kashaStartLine.setStrokeWidth(2.5);

        Text kashaText = new Text("   Porridge");
        kashaText.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.ITALIC, 40));


        Image food3 = new Image("file:C:/Program Files/Java/porridge1.jpg");
        ImageView imageView3 = new ImageView(food3);
        imageView3.setFitWidth(200);
        imageView3.setFitHeight(200);
        Line line3 = new Line(0, 0, 0, 200);
        line3.setStrokeWidth(2.5);
        Text foodName3 = new Text("Oatmeal Porridge");
        foodName3.setFont(Font.font("Impact", 30));
        Text foodIng3 = new Text("OATMEAL PORRIDGE WITH MILK/WATER WITH" +
                "\nBUTTER AND BAKED APPLE IN HONEY");
        foodIng3.setFont(Font.font("Times New Roman", 17));
        Text foodPrice3 = new Text("990 KZT");
        foodPrice3.setFont(Font.font("Times New Roman", 18));
        Button plusButton3 = new Button("+");
        plusButton3.setFont(Font.font("Impact"));
        plusButton3.setPrefSize(25, 20);
        foodPrice3.setFont(Font.font("Times New Roman", 20));
        Button minusButton3 = new Button("-");
        minusButton3.setFont(Font.font("Impact"));
        minusButton3.setPrefSize(25, 17);
        TextField textField3 = new TextField(Integer.toString(quantity3));
        textField3.setPrefSize(50, 17);
        textField3.setEditable(false);
        TextField textField33 = new TextField((quantity3 * 990) + " KZT");
        textField33.setPrefSize(70, 17);
        textField33.setEditable(false);
        plusButton3.setOnAction(e -> {
            quantity3++;
            textField3.setText(Integer.toString(quantity3));
            textField33.setText((quantity3 * 990) + " KZT");
            totalCost.setText(getTotalCost() + " KZT");
        });
        minusButton3.setOnAction(e -> {
            if (quantity3 > 0){
                quantity3--;
                textField3.setText(Integer.toString(quantity3));
                textField33.setText((quantity3 * 990) + " KZT");
                totalCost.setText(getTotalCost() + " KZT");
            }
        });
        HBox kzhBox3 = new HBox(minusButton3, foodPrice3, plusButton3, textField3, textField33);
        kzhBox3.setSpacing(10);
        VBox vBox3 = new VBox(foodName3, foodIng3, kzhBox3);
        vBox3.setSpacing(25);
        vBox3.setAlignment(Pos.CENTER_LEFT);
        HBox hBox3 = new HBox(imageView3, line3, vBox3);
        hBox3.setSpacing(20);
        hBox3.setAlignment(Pos.CENTER_LEFT);


        Image food4 = new Image("file:C:/Program Files/Java/porridge2.jpg");
        ImageView imageView4 = new ImageView(food4);
        imageView4.setFitWidth(200);
        imageView4.setFitHeight(200);
        Line line4 = new Line(0, 0, 0, 200);
        line4.setStrokeWidth(2.5);
        Text foodName4 = new Text("Cheesecakes");
        foodName4.setFont(Font.font("Impact", 30));
        Text foodIng4 = new Text("COUNTRY COTTAGE CHEESE PANCAKES WITH" +
                "\nCHERRY JAM AND SOUR CREAM");
        foodIng4.setFont(Font.font("Times New Roman", 17));
        Text foodPrice4 = new Text("1990 KZT");
        foodPrice4.setFont(Font.font("Times New Roman", 18));
        Button plusButton4 = new Button("+");
        plusButton4.setFont(Font.font("Impact"));
        plusButton4.setPrefSize(25, 20);
        foodPrice4.setFont(Font.font("Times New Roman", 20));
        Button minusButton4 = new Button("-");
        minusButton4.setFont(Font.font("Impact"));
        minusButton4.setPrefSize(25, 17);
        TextField textField4 = new TextField(Integer.toString(quantity4));
        textField4.setPrefSize(50, 17);
        textField4.setEditable(false);
        TextField textField44 = new TextField((quantity4 * 1990) + " KZT");
        textField44.setPrefSize(70, 17);
        textField44.setEditable(false);
        plusButton4.setOnAction(e -> {
            quantity4++;
            textField4.setText(Integer.toString(quantity4));
            textField44.setText((quantity4 * 1990) + " KZT");
            totalCost.setText(getTotalCost() + " KZT");
        });
        minusButton4.setOnAction(e -> {
            if (quantity4 > 0){
                quantity4--;
                textField4.setText(Integer.toString(quantity4));
                textField44.setText((quantity4 * 1990) + " KZT");
                totalCost.setText(getTotalCost() + " KZT");
            }
        });
        HBox kzhBox4 = new HBox(minusButton4, foodPrice4, plusButton4, textField4, textField44);
        kzhBox4.setSpacing(10);
        VBox vBox4 = new VBox(foodName4, foodIng4, kzhBox4);
        vBox4.setSpacing(25);
        vBox4.setAlignment(Pos.CENTER_LEFT);
        HBox hBox4 = new HBox(imageView4, line4, vBox4);
        hBox4.setSpacing(20);
        hBox4.setAlignment(Pos.CENTER_LEFT);

        Line soupStartLine = new Line(0, 0, 698, 0);
        soupStartLine.setStrokeWidth(2.5);

        Text soupText = new Text("   Soups");
        soupText.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.ITALIC, 40));


        Image food5 = new Image("file:C:/Program Files/Java/soup1.jpg");
        ImageView imageView5 = new ImageView(food5);
        imageView5.setFitWidth(200);
        imageView5.setFitHeight(200);
        Line line5 = new Line(0, 0, 0, 200);
        line5.setStrokeWidth(2.5);
        Text foodName5 = new Text("Chicken Noddle Soup");
        foodName5.setFont(Font.font("Impact", 30));
        Text foodIng5 = new Text("HOMEMADE CHICKEN, NOODLE AND EGG SOUP");
        foodIng5.setFont(Font.font("Times New Roman", 17));
        Text foodPrice5 = new Text("990 KZT");
        foodPrice5.setFont(Font.font("Times New Roman", 18));
        Button plusButton5 = new Button("+");
        plusButton5.setFont(Font.font("Impact"));
        plusButton5.setPrefSize(25, 20);
        foodPrice5.setFont(Font.font("Times New Roman", 20));
        Button minusButton5 = new Button("-");
        minusButton5.setFont(Font.font("Impact"));
        minusButton5.setPrefSize(25, 17);
        TextField textField5 = new TextField(Integer.toString(quantity5));
        textField5.setPrefSize(50, 17);
        textField5.setEditable(false);
        TextField textField55 = new TextField((quantity5 * 990) + " KZT");
        textField55.setPrefSize(70, 17);
        textField55.setEditable(false);
        plusButton5.setOnAction(e -> {
            quantity5++;
            textField5.setText(Integer.toString(quantity5));
            textField55.setText((quantity5 * 990) + " KZT");
            totalCost.setText(getTotalCost() + " KZT");
        });
        minusButton5.setOnAction(e -> {
            if (quantity5 > 0){
                quantity5--;
                textField5.setText(Integer.toString(quantity5));
                textField55.setText((quantity5 * 990) + " KZT");
                totalCost.setText(getTotalCost() + " KZT");
            }
        });
        HBox kzhBox5 = new HBox(minusButton5, foodPrice5, plusButton5, textField5, textField55);
        kzhBox5.setSpacing(10);
        VBox vBox5 = new VBox(foodName5, foodIng5, kzhBox5);
        vBox5.setSpacing(25);
        vBox5.setAlignment(Pos.CENTER_LEFT);
        HBox hBox5 = new HBox(imageView5, line5, vBox5);
        hBox5.setSpacing(20);
        hBox5.setAlignment(Pos.CENTER_LEFT);


        Image food6 = new Image("file:C:/Program Files/Java/soup2.jpg");
        ImageView imageView6 = new ImageView(food6);
        imageView6.setFitWidth(200);
        imageView6.setFitHeight(200);
        Line line6 = new Line(0, 0, 0, 200);
        line6.setStrokeWidth(2.5);
        Text foodName6 = new Text("Chicken Ramen");
        foodName6.setFont(Font.font("Impact", 30));
        Text foodIng6 = new Text("RICH, SPICY CHICKEN BROTH WITH NOODLES," +
                "\nCHICKEN, EGG, SPINACH AND NORI");
        foodIng6.setFont(Font.font("Times New Roman", 17));
        Text foodPrice6 = new Text("2890 KZT");
        foodPrice6.setFont(Font.font("Times New Roman", 18));
        Button plusButton6 = new Button("+");
        plusButton6.setFont(Font.font("Impact"));
        plusButton6.setPrefSize(25, 20);
        foodPrice6.setFont(Font.font("Times New Roman", 20));
        Button minusButton6 = new Button("-");
        minusButton6.setFont(Font.font("Impact"));
        minusButton6.setPrefSize(25, 17);
        TextField textField6 = new TextField(Integer.toString(quantity6));
        textField6.setPrefSize(50, 17);
        textField6.setEditable(false);
        TextField textField66 = new TextField((quantity6 * 2890) + " KZT");
        textField66.setPrefSize(70, 17);
        textField66.setEditable(false);
        plusButton6.setOnAction(e -> {
            quantity6++;
            textField6.setText(Integer.toString(quantity6));
            textField66.setText((quantity6 * 2890) + " KZT");
            totalCost.setText(getTotalCost() + " KZT");
        });
        minusButton6.setOnAction(e -> {
            if (quantity6 > 0){
                quantity6--;
                textField6.setText(Integer.toString(quantity6));
                textField66.setText((quantity6 * 2890) + " KZT");
                totalCost.setText(getTotalCost() + " KZT");
            }
        });
        HBox kzhBox6 = new HBox(minusButton6, foodPrice6, plusButton6, textField6, textField66);
        kzhBox6.setSpacing(10);
        VBox vBox6 = new VBox(foodName6, foodIng6, kzhBox6);
        vBox6.setSpacing(25);
        vBox6.setAlignment(Pos.CENTER_LEFT);
        HBox hBox6 = new HBox(imageView6, line6, vBox6);
        hBox6.setSpacing(20);
        hBox6.setAlignment(Pos.CENTER_LEFT);

        Line drinkStartLine = new Line(0, 0, 698, 0);
        drinkStartLine.setStrokeWidth(2.5);

        Text drinkText = new Text("   Drinks");
        drinkText.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.ITALIC, 40));


        Image food7 = new Image("file:C:/Program Files/Java/drink1.jpg");
        ImageView imageView7 = new ImageView(food7);
        imageView7.setFitWidth(200);
        imageView7.setFitHeight(200);
        Line line7 = new Line(0, 0, 0, 200);
        line7.setStrokeWidth(2.5);
        Text foodName7 = new Text("Latte 0.4");
        foodName7.setFont(Font.font("Impact", 30));
        Text foodIng7 = new Text("AN ESPRESSO-BASED COFFEE DRINK WITH THE" +
                "\nADDITION OF MILK AND THICK MILK FOAM");
        foodIng7.setFont(Font.font("Times New Roman", 17));
        Text foodPrice7 = new Text("1190 KZT");
        foodPrice7.setFont(Font.font("Times New Roman", 18));
        Button plusButton7 = new Button("+");
        plusButton7.setFont(Font.font("Impact"));
        plusButton7.setPrefSize(25, 20);
        foodPrice7.setFont(Font.font("Times New Roman", 20));
        Button minusButton7 = new Button("-");
        minusButton7.setFont(Font.font("Impact"));
        minusButton7.setPrefSize(25, 17);
        TextField textField7 = new TextField(Integer.toString(quantity7));
        textField7.setPrefSize(50, 17);
        textField7.setEditable(false);
        TextField textField77 = new TextField((quantity7 * 1190) + " KZT");
        textField77.setPrefSize(70, 17);
        textField77.setEditable(false);
        plusButton7.setOnAction(e -> {
            quantity7++;
            textField7.setText(Integer.toString(quantity7));
            textField77.setText((quantity7 * 1190) + " KZT");
            totalCost.setText(getTotalCost() + " KZT");
        });
        minusButton7.setOnAction(e -> {
            if (quantity7 > 0){
                quantity7--;
                textField7.setText(Integer.toString(quantity7));
                textField77.setText((quantity7 * 1190) + " KZT");
                totalCost.setText(getTotalCost() + " KZT");
            }
        });
        HBox kzhBox7 = new HBox(minusButton7, foodPrice7, plusButton7, textField7, textField77);
        kzhBox7.setSpacing(10);
        VBox vBox7 = new VBox(foodName7, foodIng7, kzhBox7);
        vBox7.setSpacing(25);
        vBox7.setAlignment(Pos.CENTER_LEFT);
        HBox hBox7 = new HBox(imageView7, line7, vBox7);
        hBox7.setSpacing(20);
        hBox7.setAlignment(Pos.CENTER_LEFT);


        Image food8 = new Image("file:C:/Program Files/Java/drink2.jpg");
        ImageView imageView8 = new ImageView(food8);
        imageView8.setFitWidth(200);
        imageView8.setFitHeight(200);
        Line line8 = new Line(0, 0, 0, 200);
        line8.setStrokeWidth(2.5);
        Text foodName8 = new Text("Cappuccino 0.4");
        foodName8.setFont(Font.font("Impact", 30));
        Text foodIng8 = new Text("AN ESPRESSO-BASED COFFEE DRINK WITH THE" +
                "\nADDITION OF MILK AND THICK MILK FOAM");
        foodIng8.setFont(Font.font("Times New Roman", 17));
        Text foodPrice8 = new Text("1190 KZT");
        foodPrice8.setFont(Font.font("Times New Roman", 18));
        Button plusButton8 = new Button("+");
        plusButton8.setFont(Font.font("Impact"));
        plusButton8.setPrefSize(25, 20);
        foodPrice8.setFont(Font.font("Times New Roman", 20));
        Button minusButton8 = new Button("-");
        minusButton8.setFont(Font.font("Impact"));
        minusButton8.setPrefSize(25, 17);
        TextField textField8 = new TextField(Integer.toString(quantity8));
        textField8.setPrefSize(50, 17);
        textField8.setEditable(false);
        TextField textField88 = new TextField((quantity8 * 1190) + " KZT");
        textField88.setPrefSize(70, 17);
        textField88.setEditable(false);
        plusButton8.setOnAction(e -> {
            quantity8++;
            textField8.setText(Integer.toString(quantity8));
            textField88.setText((quantity8 * 1190) + " KZT");
            totalCost.setText(getTotalCost() + " KZT");
        });
        minusButton8.setOnAction(e -> {
            if (quantity8 > 0){
                quantity8--;
                textField8.setText(Integer.toString(quantity8));
                textField88.setText((quantity8 * 1190) + " KZT");
                totalCost.setText(getTotalCost() + " KZT");
            }
        });
        HBox kzhBox8 = new HBox(minusButton8, foodPrice8, plusButton8, textField8, textField88);
        kzhBox8.setSpacing(10);
        VBox vBox8 = new VBox(foodName8, foodIng8, kzhBox8);
        vBox8.setSpacing(25);
        vBox8.setAlignment(Pos.CENTER_LEFT);
        HBox hBox8 = new HBox(imageView8, line8, vBox8);
        hBox8.setSpacing(20);
        hBox8.setAlignment(Pos.CENTER_LEFT);


        Image food9 = new Image("file:C:/Program Files/Java/drink3.jpg");
        ImageView imageView9 = new ImageView(food9);
        imageView9.setFitWidth(200);
        imageView9.setFitHeight(200);
        Line line9 = new Line(0, 0, 0, 200);
        line9.setStrokeWidth(2.5);
        Text foodName9 = new Text("Strawberry-Banana Smoothie");
        foodName9.setFont(Font.font("Impact", 30));
        Text foodIng9 = new Text("""
                A DRINK BASED ON FRESH ORANGE JUICE WITH
                THE ADDITION OF FRESH BERRIES,
                STRAWBERRIES AND FRESH BANANA""");
        foodIng9.setFont(Font.font("Times New Roman", 17));
        Text foodPrice9 = new Text("2090 KZT");
        foodPrice9.setFont(Font.font("Times New Roman", 18));
        Button plusButton9 = new Button("+");
        plusButton9.setFont(Font.font("Impact"));
        plusButton9.setPrefSize(25, 20);
        foodPrice9.setFont(Font.font("Times New Roman", 20));
        Button minusButton9 = new Button("-");
        minusButton9.setFont(Font.font("Impact"));
        minusButton9.setPrefSize(25, 17);
        TextField textField9 = new TextField(Integer.toString(quantity9));
        textField9.setPrefSize(50, 17);
        textField9.setEditable(false);
        TextField textField99 = new TextField((quantity9 * 2090) + " KZT");
        textField99.setPrefSize(70, 17);
        textField99.setEditable(false);
        plusButton9.setOnAction(e -> {
            quantity9++;
            textField9.setText(Integer.toString(quantity9));
            textField99.setText((quantity9 * 2090) + " KZT");
            totalCost.setText(getTotalCost() + " KZT");
        });
        minusButton9.setOnAction(e -> {
            if (quantity9 > 0){
                quantity9--;
                textField9.setText(Integer.toString(quantity9));
                textField99.setText((quantity9 * 2090) + " KZT");
                totalCost.setText(getTotalCost() + " KZT");
            }
        });
        HBox kzhBox9 = new HBox(minusButton9, foodPrice9, plusButton9, textField9, textField99);
        kzhBox9.setSpacing(10);
        VBox vBox9 = new VBox(foodName9, foodIng9, kzhBox9);
        vBox9.setSpacing(25);
        vBox9.setAlignment(Pos.CENTER_LEFT);
        HBox hBox9 = new HBox(imageView9, line9, vBox9);
        hBox9.setSpacing(20);
        hBox9.setAlignment(Pos.CENTER_LEFT);

        Line drinkEndLine = new Line(0, 0, 698, 0);
        drinkEndLine.setStrokeWidth(2.5);
        Pane pane1 = new Pane();
        pane1.setPrefSize(700, 0);

        Text costText = new Text("   Total Cost: ");
        costText.setFont(Font.font("Impact", 30));
        HBox costHBox = new HBox(costText, totalCost);
        costHBox.setSpacing(15);


        VBox vBox = new VBox(pane, breakfastStartLine, breakfastText, gridPane, kashaStartLine, kashaText, hBox3, hBox4, soupStartLine, soupText, hBox5, hBox6, drinkStartLine, drinkText, hBox7, hBox8, hBox9, drinkEndLine, costHBox, pane1);
        vBox.setSpacing(30);

        scrollPane.setContent(vBox);

        VBox primaryvBox = new VBox(hBox, buttonsHBox, scrollPane);

        scene.setRoot(primaryvBox);
    }

//---------------------------------------LEAVE A REPLY METHOD-------------------------------------------------//
    private VBox leaveAReply(Integer padding){
        Label leaveReply = new Label("Leave a Reply");
        leaveReply.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));

        Label smallText = new Label("Enter your comment here");
        smallText.setStyle("-fx-font-size: 8px");
        TextField textField2 = new TextField("");
        textField2.setMinWidth(400);
        textField2.setMinHeight(35);
        textField2.setOnKeyPressed(e -> {
            if (e.getCode().getName().equals("Enter") && !textField2.getText().isBlank()){
                System.out.println("Your Comment: " + textField2.getText().strip());
                textField2.setText("");
            }
        });
        Pane stackVBox = new Pane(textField2, smallText);

        stackVBox.setLayoutX(400);
        stackVBox.setLayoutY(40);

        HBox justHBox = new HBox(stackVBox);
        justHBox.setAlignment(Pos.CENTER);


        VBox bottomVBox = new VBox(leaveReply, justHBox);
        bottomVBox.setAlignment(Pos.CENTER);
        bottomVBox.setSpacing(15);
        bottomVBox.setPadding(new Insets(padding));
        return bottomVBox;
    }

    private int getTotalCost(){
        return (quantity1 * 2990) + (quantity2 * 3990) + (quantity3 * 990) + (quantity4 * 1990) + (quantity5 * 990) + (quantity6 * 2890) + (quantity7 * 1190) + (quantity8 * 1190) + (quantity9 * 2090);
    }

    private int getTotalCostWithBooking(){
        int totalCostWithBooking = getTotalCost();
        if (peoplePicker.getValue().equals(" 1 person") || peoplePicker.getValue().equals(" 2 people") || peoplePicker.getValue().equals(" 3 people") || peoplePicker.getValue().equals(" 4 people")){
            totalCostWithBooking += 3000;
        }
        else if (peoplePicker.getValue().equals(" 5 people") || peoplePicker.getValue().equals(" 6 people") || peoplePicker.getValue().equals(" 7 people")){
            totalCostWithBooking += 6000;
        }
        else {
            totalCostWithBooking += 8000;
        }
        return totalCostWithBooking;
    }

    private static class IconListCell<T> extends ListCell<T> {
        private final ImageView icon;
        private final String defaultText;

        public IconListCell(String defaultText, ImageView icon) {
            this.icon = icon;
            this.defaultText = defaultText;
        }

        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                setText(defaultText);
            } else {
                setGraphic(icon);
                setText(item.toString());
            }
        }
    }

    private void saveContactData(){
        nameInContact = nameTextFieldInContact.getText();
        emailInContact = emailTextFieldInContact.getText();
        opinion = opinionTextArea.getText();
    }

    private void saveBookingData(){
        nameInBooking = nameTextFieldInBooking.getText();
        emailInBooking = emailTextFieldInBooking.getText();
        phone = phoneTextField.getText();

        localDate = datePicker.getValue();
        time = timePicker.getValue();
        numberOfPeople = peoplePicker.getValue();
    }

    private String getMenuAndBooking(){
        String orderText = "";
        if (quantity1 > 0){
            orderText += "English Breakfast                      |  " + quantity1 + " quantity  |  " + (quantity1*2990) + " KZT\n";
        }
        if (quantity2 > 0){
            orderText += "Avocado Toast with Salmon    |  " + quantity2 + " quantity  |  " + (quantity2*3990) + " KZT\n";
        }
        if (quantity3 > 0){
            orderText += "Oatmeal Porridge                      |  " + quantity3 + " quantity  |  " + (quantity3*990) + " KZT\n";
        }
        if (quantity4 > 0){
            orderText += "Cheesecakes                             |  " + quantity4 + " quantity  |  " + (quantity4*1990) + " KZT\n";
        }
        if (quantity5 > 0){
            orderText += "Chicken Noodle Soup               |  " + quantity5 + " quantity  |  " + (quantity5*990) + " KZT\n";
        }
        if (quantity6 > 0){
            orderText += "Chicken Ramen                         |  " + quantity6 + " quantity  |  " + (quantity6*2890) + " KZT\n";
        }
        if (quantity7 > 0){
            orderText += "Latte 0.4                                      |  " + quantity7 + " quantity  |  " + (quantity7*1190) + " KZT\n";
        }
        if (quantity8 > 0){
            orderText += "Cappuccino 0.4                          |  " + quantity8 + " quantity  |  " + (quantity8*1190) + " KZT\n";
        }
        if (quantity9 > 0){
            orderText += "Strawberry-Banana Smoothie  |  " + quantity9 + " quantity  |  " + (quantity9*2090) + " KZT\n";
        }

        if (peoplePicker.getValue().equals(" 1 person") || peoplePicker.getValue().equals(" 2 people") || peoplePicker.getValue().equals(" 3 people") || peoplePicker.getValue().equals(" 4 people")){
            orderText += "Booking Table                            |   " + peoplePicker.getValue().strip() + "   |  3000 KZT\n";
        }
        else if (peoplePicker.getValue().equals(" 5 people") || peoplePicker.getValue().equals(" 6 people") || peoplePicker.getValue().equals(" 7 people")){
            orderText += "Booking Table                            |   " + peoplePicker.getValue().strip() + "   |  6000 KZT\n";
        }
        else {
            orderText += "Booking Table                            |   " + peoplePicker.getValue().strip() + "   |  8000 KZT\n";
        }

        if (getTotalCostWithBooking() != 0){
            orderText += "Total Cost:   " + getTotalCostWithBooking() + " KZT";
        }
        return orderText;
    }

    private void showBookAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Booking Table");
        alert.setHeaderText("Your Booking has been completed successfully!");
        alert.setContentText("Name:  " + nameInBooking.strip() +
                "\nEmail:  " + emailInBooking.strip() +
                "\nPhone:  " + phone.strip() +
                "\nDate:  " + localDate.toString() +
                "\nTime:  " + time +
                "\nNumber of People:  " + numberOfPeople +
                "\nTotal Cost:  " + getTotalCostWithBooking() + " KZT");
        deleteAllData();
        saveBookingData();
        alert.showAndWait();
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Please enter your name, email, phone number!");
        alert.setContentText("Name, email, phone are required!");
        alert.showAndWait();
    }

    private void deleteAllData(){
        nameInBooking = "";
        nameTextFieldInBooking.setText(nameInBooking);
        emailInBooking = "";
        emailTextFieldInBooking.setText(emailInBooking);
        phone = "";
        phoneTextField.setText(phone);

        localDate = LocalDate.now();
        datePicker.setValue(localDate);
        time = "09:00";
        timePicker.setValue(time);
        numberOfPeople = " 2 people";
        peoplePicker.setValue(numberOfPeople);

        quantity1 = 0;
        quantity2 = 0;
        quantity3 = 0;
        quantity4 = 0;
        quantity5 = 0;
        quantity6 = 0;
        quantity7 = 0;
        quantity8 = 0;
        quantity9 = 0;
        foodsTextArea.setText(getMenuAndBooking());

        nameInContact = null;
        nameTextFieldInContact.setText(nameInContact);
        emailInContact = null;
        emailTextFieldInContact.setText(emailInContact);
        opinion = null;
        opinionTextArea.setText(opinion);

        deleteContactData();
    }

    private void deleteContactData(){
        nameInContact = "";
        nameTextFieldInContact.setText(nameInContact);
        emailInContact = "";
        emailTextFieldInContact.setText(emailInContact);
        opinion = "";
        opinionTextArea.setText(opinion);
    }
}