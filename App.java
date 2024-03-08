// last modified version

package com.mycompany.apui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * .
 * JavaFX App
 */
public class App extends Application {

    Scene StartScene, SignUpScene, LogInScene, SearchScene,  SeatSelectScene, FoodMenuScene, PaymentScene, ConfScene;
    private static final String BACKGROUND_IMAGE_PATH = "file:///Users/khadeejhgheliwi/Downloads/photos/Screenshot%202024-01-27%20at%204.47.51%E2%80%AFPM.png";
    private static final String SEARCH_ICON_PATH = "file:///Users/khadeejhgheliwi/Downloads/photos/serchicon.png";
    private static final String LOGO_IMAGE_PATH = "file:///Users/khadeejhgheliwi/Downloads/photos/photo_2024-01-28%2020.12.20.png";
    public double upFoodPrice;
    public double upSeatPrice;
    public double totalPrice;
    
    public void updateSeatPrice(double cSeatPrice){
       upSeatPrice = cSeatPrice;
    }
    
    public void updateFoodPrice(double cFoodPrice){ 
       upFoodPrice = cFoodPrice;
    }

    User user = new User();
    Moive movie;
    seat seat1;

    @Override
    public void start(Stage stage) {
        //Colors
        Color MyRed = Color.rgb(141, 54, 59);
        Color MyOrange = Color.rgb(215, 163, 66);

        //lable massage 
        Label lblmsg = new Label();
        lblmsg.setTextFill(Color.DARKRED);
        lblmsg.setMaxHeight(30);
        lblmsg.setMaxWidth(500);
        lblmsg.setMinHeight(30);
        lblmsg.setMinWidth(500);
        lblmsg.setFont(new Font(13.0));
        lblmsg.setAlignment(Pos.TOP_CENTER);

        /////////Start Scene\\\\\\\\\\ Mawaddah
        ImageView bg = new ImageView(BACKGROUND_IMAGE_PATH);
        bg.setFitHeight(650);
        bg.setFitWidth(1200);

        ImageView Startlogo = new ImageView(LOGO_IMAGE_PATH);
        Startlogo.setFitWidth(100);
        Startlogo.setFitHeight(100);

        Text Title = new Text("CINEMA BOOKING");
        Title.setFont(Font.font("Impact", FontWeight.BOLD, 80));
        Title.setFill(MyRed);
        Title.setStroke(Color.WHITE);
        Title.setStrokeWidth(2);

        Text welcome = new Text("To make your experinse easier!");
        welcome.setFont(Font.font("Impact", FontWeight.NORMAL, 20));
        welcome.setFill(MyOrange);
        welcome.setStroke(Color.WHITE);

        Button logIn = new Button("logIn");
        logIn.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        logIn.setPadding(new Insets(10, 30, 10, 30));
        logIn.setOnAction(e -> {
            stage.setScene(PaymentScene);
        });

        Button signUp = new Button("Sign up");
        signUp.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        signUp.setPadding(new Insets(10, 30, 10, 30));
        signUp.setOnAction(e -> {
            stage.setScene(SignUpScene); //SeatSelectScene
        });

        HBox buttons = new HBox(30, signUp, logIn);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(40));

        VBox mainTitleBox = new VBox(10, Startlogo, Title, welcome, buttons);
        mainTitleBox.setAlignment(Pos.CENTER);

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(mainTitleBox);

        StackPane stack = new StackPane(bg, mainPane);
        StartScene = new Scene(stack, 1200, 650);
        ///////////////////////////////////////////////////////////////////////

        /////////Sign Up Scene\\\\\\\\\\ Shaimaa
        ImageView SUbg = new ImageView(BACKGROUND_IMAGE_PATH);
        SUbg.setFitHeight(650);
        SUbg.setFitWidth(1200);

        HBox Sign = new HBox(70);
        VBox Lv = new VBox(50);
        VBox Rv = new VBox(50);
        VBox SignIn = new VBox(10);
        BorderPane SUpane = new BorderPane();
        StackPane STstack = new StackPane();

        //Sign in text
        Text SIGN_IN = new Text("SIGN Up");
        SIGN_IN.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        SIGN_IN.setFill(Color.rgb(92, 110, 194));
        SIGN_IN.setStrokeWidth(2);
        BorderPane.setAlignment(SIGN_IN, Pos.CENTER);
        SUpane.setCenter(SIGN_IN);

        //------left VBox
        Label firstNameLabel = new Label("First name");
        firstNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        TextField firstNameField = new TextField();

        Label emailLabel = new Label("Email");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        PasswordField passwordField = new PasswordField();

        Lv.getChildren().addAll(firstNameLabel, firstNameField, emailLabel, emailField, passwordLabel, passwordField);
        Lv.setAlignment(Pos.CENTER);
        Lv.setSpacing(10);

        //------right VBox
        Label lastNameLabel = new Label("Last name");
        lastNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        TextField lastNameField = new TextField();

        Label numberLabel = new Label("Phone number");
        numberLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        TextField numberField = new TextField();
           

        Label confirmLabel = new Label("Confirm password");
        confirmLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        PasswordField confirmField = new PasswordField();

        Rv.getChildren().addAll(lastNameLabel, lastNameField, numberLabel, numberField, confirmLabel, confirmField);
        Rv.setAlignment(Pos.CENTER);
        Rv.setSpacing(10);

        // Combine left and right VBoxes in HBox
        Sign.getChildren().addAll(Lv, Rv);
        Sign.setAlignment(Pos.CENTER);
        SignIn.getChildren().addAll(SIGN_IN, Sign);
        SignIn.setAlignment(Pos.CENTER);
        SUpane.setCenter(SignIn);

        //Sign Up Observable Lists
        ObservableList<String> nameob = FXCollections.observableArrayList();
        ObservableList<String> phoneob = FXCollections.observableArrayList();
        ObservableList<String> emailob = FXCollections.observableArrayList();
        ObservableList<String> passob = FXCollections.observableArrayList();
//        ObservableList<String> conpassob = FXCollections.observableArrayList();
        

        // Error message label
        Label errorMessageLabel = new Label();
        errorMessageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        errorMessageLabel.setTextFill(Color.RED);
        SignIn.getChildren().addAll(errorMessageLabel);

        //Buttons
        Button Submit = new Button("Submit");
        Submit.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        Submit.setPadding(new Insets(10, 30, 10, 30));
        Submit.setOnAction(e -> {
            //save data 
            int counter = 0;
            String fname = firstNameField.getText();
            String lname = lastNameField.getText();
            String email = emailField.getText();
            String pass = passwordField.getText();
            String conpass = confirmField.getText();
            String phoneNum = numberField.getText();

            if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || pass.isEmpty() || conpass.isEmpty() || phoneNum.isEmpty()) {
                errorMessageLabel.setText("Error: Please fill in all fields.");
            } else if (emailob.contains(email)) {
                errorMessageLabel.setText("Error: This email is already in use.");
            } else if (phoneob.contains(phoneNum)) {
                errorMessageLabel.setText("Error: This phone number is already in use.");
            }    
              else if (!phoneNum.matches("\\d+")) {
               errorMessageLabel.setText("The quantity must be a digit only.");
               return;         
            } 
              else {
                errorMessageLabel.setText(""); // Clear any previous error message
                nameob.add("Name: " + fname + " " + lname);
                phoneob.add(phoneNum);
                emailob.add(email);
                passob.add("Password: " + pass);
                //switch scenes
                stage.setScene(SearchScene);
            }
            counter += 1;
            //insert data to user table 
            try {
//               User user = new User(firstNameField.getText(),lastNameField.getText(), numberField.getText(), emailField.getText(),emailField.getText());
               user.setF_name(firstNameField.getText());
               user.setL_name(lastNameField.getText());
               user.setPhoneNo(numberField.getText());
               user.setPassword(pass);
               user.setEmail(email);
               Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                Serializable identifier = session.save(user);  // Use Serializable instead of Integer
                tx.commit();
                session.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                
            }
          

        });

        Button SUBack = new Button("Back");
        SUBack.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        SUBack.setPadding(new Insets(10, 30, 10, 30));
        SUBack.setOnAction(e -> {
            stage.setScene(StartScene);
        });

        HBox SUbuttons = new HBox(20);
        SUbuttons.getChildren().addAll(SUBack, Submit);
        SUbuttons.setAlignment(Pos.CENTER);
        SUpane.setBottom(SUbuttons);

        STstack.setPadding(new Insets(50, 50, 50, 50));
        STstack.getChildren().addAll(SUbg, SUpane);
        SignUpScene = new Scene(STstack, 1200, 650);
        ///////////////////////////////////////////////////////////////////////

        /////////LogIn Scene\\\\\\\\\\ Shaimaa
        ImageView Lbg = new ImageView(BACKGROUND_IMAGE_PATH);
        Lbg.setFitHeight(650);
        Lbg.setFitWidth(1200);

        VBox login = new VBox(30);
        login.setAlignment(Pos.CENTER);
        HBox email = new HBox(30);
        email.setAlignment(Pos.CENTER);
        HBox password = new HBox(30);
        password.setAlignment(Pos.CENTER);
        HBox Lbuttons = new HBox(30);
        Lbuttons.setAlignment(Pos.CENTER);
        StackPane stackPane = new StackPane();

        //Booking text
        Text booking = new Text("Log In");
        booking.setFont(Font.font("Arial", FontWeight.BOLD, 80));
        booking.setFill(Color.rgb(92, 110, 194));
        login.getChildren().addAll(booking);

        //------email HBox
        Label emailLabe = new Label("Email");
        emailLabe.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        TextField LemailField = new TextField();

        email.getChildren().addAll(emailLabe, LemailField);
        login.getChildren().addAll(email);

        //------password HBox
        Label LpasswordLabel = new Label("Password");
        LpasswordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        PasswordField LpasswordField = new PasswordField();

        password.getChildren().addAll(LpasswordLabel, LpasswordField);
        login.getChildren().addAll(password);

        // Error message label
        Label LerrorMessageLabel = new Label();
        LerrorMessageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        LerrorMessageLabel.setTextFill(Color.RED);
        login.getChildren().addAll(LerrorMessageLabel);

        //Buttons
        Button Lsubmit = new Button("login");
        Lsubmit.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        Lsubmit.setPadding(new Insets(10, 30, 10, 30));

        Lsubmit.setOnAction(e -> {
            String userEmail = LemailField.getText();
            String userPassword = LpasswordField.getText();


            //retrive from User table 
        try {
                Session session = HibernateUtil.getSessionFactory().openSession();
                List<User> userList = null ;
                String queryStr = "from User";
                Query query = session.createQuery(queryStr);
                userList = query.list();
              
                session.close();



                for (User u : userList) {
                    if (!u.getEmail().equals(userEmail) && !u.getPassword().equals(userPassword)) {
                         LerrorMessageLabel.setText("User does not exist. Please check your credentials.");
//                         Lsubmit.setDisable(false);
                         return;
                 }
                    else {
                    stage.setScene(SearchScene);
//                    Lsubmit.setDisable(true);
                }
                    
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button Back = new Button("Back");
        Back.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        Back.setPadding(new Insets(10, 30, 10, 30));
        Back.setOnAction(e -> {
            stage.setScene(StartScene);
        });

        Lbuttons.getChildren().addAll(Back, Lsubmit);
        login.getChildren().addAll(Lbuttons);

        stackPane.setPadding(new Insets(50, 50, 50, 50));
        stackPane.getChildren().addAll(Lbg, login);
        LogInScene = new Scene(stackPane, 1200, 650);
        ///////////////////////////////////////////////////////////////////////

        /////////Search Scene\\\\\\\\\\ Jana
        ImageView Sbg = new ImageView(BACKGROUND_IMAGE_PATH);
        Sbg.setFitHeight(650);
        Sbg.setFitWidth(1200);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        backButton.setOnAction(e -> {
            stage.setScene(StartScene);
        });

        Button SNext = new Button("Next");
        SNext.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        SNext.setOnAction(e -> {
            stage.setScene(SeatSelectScene);
        });

        ImageView searchIcon = new ImageView(SEARCH_ICON_PATH);
        searchIcon.setFitHeight(35);
        searchIcon.setFitWidth(35);

        ImageView SLogo = new ImageView(LOGO_IMAGE_PATH);
        SLogo.setFitHeight(100);
        SLogo.setFitWidth(100);

        HBox SButtons = new HBox(30, backButton, SNext);
        SButtons.setAlignment(Pos.CENTER);
        SButtons.setPadding(new Insets(40));

        GridPane Spane = new GridPane();
        Spane.setAlignment(Pos.CENTER);
        Spane.setVgap(15);
        Spane.setHgap(20);
        
        ObservableList<String> movieList = FXCollections.observableArrayList("Wonka", "Wish", "Aqua man", "Barbie","Super Mario","Aladdin","Mickey Mouse","Toy Story","The Last 10 Years");
        ObservableList<String> locationList = FXCollections.observableArrayList("Vox , red sea mall", "amc , stars avenue", "muvi , mall of arabia");

      
        ComboBox<String> movieComboBox = new ComboBox();
        ComboBox<String> locationComboBox = new ComboBox();
        Label resultLabel = new Label();

        resultLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-font-size: 20;");
        
        ComboBox SelectTimeComboBox = new ComboBox();
        SelectTimeComboBox.getItems().addAll(" 10:00Am", " 12:00Pm", " 6:00Pm",
                "S 11:00Am", " 1:00Pm", " 4:00Pm", " 8:00Pm");
        SelectTimeComboBox.setEditable(true);
        SelectTimeComboBox.setPrefWidth(250);
        SelectTimeComboBox.setPrefHeight(40);
        SelectTimeComboBox.setPromptText("Select Time");
        SelectTimeComboBox.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14;");
        SelectTimeComboBox.setVisibleRowCount(3);
        locationComboBox.getItems().addAll("Vox , red sea mall", "amc , stars avenue", "muvi , mall of arabia");
        

        Button submitButton = new Button("Submit");
        HBox submit = new HBox(5, submitButton, searchIcon);
        submitButton.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
         submitButton.setOnAction(event -> {
            String selectedMovie = movieComboBox.getValue();
            String selectedLocation = locationComboBox.getValue();
            String selectedtime = (String)SelectTimeComboBox.getValue();

            if (selectedMovie != null && selectedLocation != null && selectedtime!=null) {
                resultLabel.setText(selectedMovie + " , " + selectedLocation + " , "+selectedtime);

            } else {
                resultLabel.setText("Please select both movie,location and time");
            }
            try {
                movie = new Moive(selectedMovie,selectedLocation,selectedtime);
                Session session2 = HibernateUtil.getSessionFactory().openSession();
                Transaction tx2 = session2.beginTransaction();
                Serializable identifier2 = session2.save(movie);  // Use Serializable instead of Integer
                tx2.commit();
                session2.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                
            }
            
        });

        searchIcon.setOnMouseClicked(event -> {
            movieComboBox.show();
        });
        movieComboBox.getItems().addAll("Wonka", "Wish", "Aqua man", "Barbie","Super Mario","Aladdin","Mickey Mouse","Toy Story","The Last 10 Years");
        movieComboBox.setPromptText("Movie name...");
        movieComboBox.setEditable(true);
        movieComboBox.setPrefWidth(250);
        movieComboBox.setPrefHeight(40);
        movieComboBox.setVisibleRowCount(2);

        locationComboBox.setPromptText("Select Location");
        locationComboBox.setPrefWidth(250);
        locationComboBox.setPrefHeight(40);
        locationComboBox.setVisibleRowCount(2);
        locationComboBox.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14;");

        // Set styles for movieComboBox
        movieComboBox.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 14;");

        searchIcon.setOnMouseClicked(event -> {
            String selectedMovie = movieComboBox.getValue();
            String selectedLocation = locationComboBox.getValue();
            String selectedtime = (String)SelectTimeComboBox.getValue();

            if (selectedMovie != null && selectedLocation != null && selectedtime!=null) {
                resultLabel.setText(selectedMovie + " , " + selectedLocation + " , "+selectedtime);

            } else {
                resultLabel.setText("Please select both movie,location and time");
            }
            try {
                movie = new Moive(selectedMovie,selectedLocation,selectedtime);
                Session session2 = HibernateUtil.getSessionFactory().openSession();
                Transaction tx2 = session2.beginTransaction();
                Serializable identifier2 = session2.save(movie);  // Use Serializable instead of Integer
                tx2.commit();
                session2.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                
            }
            
        });

        Label selectMovieLabel = new Label("Select Movie:");
        selectMovieLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-font-size: 30;");

        Label selectLocationLabel = new Label("Select Location:");
        selectLocationLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-font-size: 30;");
        
        Label selectTimeLabel = new Label("Select Time:");
        selectTimeLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-font-size: 30;");

// Add empty rows above 
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(10);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(10);

        Spane.getRowConstraints().addAll(row1, row2);

// Add separate row at the top center
        Spane.add(SLogo, 0, 2, 2, 1);
        GridPane.setHalignment(SLogo, HPos.CENTER);

// Set the horizontal alignment for the columns
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.CENTER);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.CENTER);

        Spane.getColumnConstraints().addAll(col1, col2);

        Spane.add(selectMovieLabel, 0, 3);
        Spane.add(movieComboBox, 1, 3);
        Spane.add(selectLocationLabel, 0, 4);
        Spane.add(locationComboBox, 1, 4);
        Spane.add(submit, 0, 7, 2, 2);
        Spane.add(resultLabel, 0, 6, 2, 1);
        Spane.add(SButtons, 0, 10, 2, 1);
        Spane.add(SelectTimeComboBox, 1, 5);
        Spane.add(selectTimeLabel, 0, 5);

        StackPane Searchstack = new StackPane(Sbg, Spane);
        SearchScene = new Scene(Searchstack, 1200, 650);
        ///////////////////////////////////////////////////////////////////////      

   
        ///////////////////////////////////////////////////////////////////////  

        /////////Seat Select Scene\\\\\\\\\\ Mawaddah
        Label LblSelect = new Label("SELECT YOUR SEAT  ");
        LblSelect.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        LblSelect.setContentDisplay(ContentDisplay.TOP);
        LblSelect.setTextFill(MyRed);

        ImageView SeatBg = new ImageView(BACKGROUND_IMAGE_PATH);
        SeatBg.setFitHeight(650);
        SeatBg.setFitWidth(1200);

        Image seats = new Image("file:///Users/khadeejhgheliwi/Downloads/photos/seatspng.png");
        ImageView ivSeats = new ImageView(seats);
        ivSeats.setFitHeight(300);
        ivSeats.setFitWidth(300);

        //row and column settings
        Label lRow = new Label("Row");
        ComboBox cbRow = new ComboBox();
        cbRow.getItems().addAll("A", "B", "C", "D", "E");
        cbRow.setEditable(true);
        cbRow.setPrefWidth(70);
        cbRow.setPrefHeight(30);

        Label lColumn = new Label("Column");
        ComboBox cbCol = new ComboBox();
        cbCol.getItems().addAll("1", "2", "3", "4", "5", "6", "7");
        cbCol.setVisibleRowCount(3);
        cbCol.setEditable(true);
        cbCol.setPrefWidth(70);
        cbCol.setPrefHeight(30);

        ObservableList<String> obAllSeatNo = FXCollections.observableArrayList();//all useres seats
        ObservableList<String> obSeatNo = FXCollections.observableArrayList();//all useres seats

        ListView lvSeats = new ListView();
        lvSeats.setMaxSize(300, 200);

        Button btSelect = new Button("Select");
        btSelect.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");

        btSelect.setOnAction(e -> {
            double seatPrice;

            String SeatNumber = (String) cbRow.getValue() + (String) cbCol.getValue();

            if (cbRow.getValue() == null || cbCol.getValue() == null) {
                lblmsg.setText("Select your seat");
                return;
            }//end if
            else if (obAllSeatNo.contains(SeatNumber)) {
                lblmsg.setText("Seat not available");
                return;
            }//end if 
            else {
                obSeatNo.add(SeatNumber);
                obAllSeatNo.add(SeatNumber);
                lvSeats.setItems(FXCollections.observableArrayList(obSeatNo));
                seatPrice = obSeatNo.size()*50;
                lblmsg.setText(seatPrice+"sar");
                updateSeatPrice(seatPrice);
            }//end else
            
            try {
                seat1 = new seat(SeatNumber);
                Session session2 = HibernateUtil.getSessionFactory().openSession();
                Transaction tx2 = session2.beginTransaction();
                Serializable identifier2 = session2.save(seat1);  // Use Serializable instead of Integer
                tx2.commit();
                session2.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                
            }
            
            
        });

        Button btDelet = new Button("Delet");
        btDelet.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");

        btDelet.setOnAction(e -> {
            double seatPrice;
            String SeatNumber = (String) cbRow.getValue() + (String) cbCol.getValue();
            if (obAllSeatNo.contains(SeatNumber)) {
                obAllSeatNo.remove(SeatNumber);
                obSeatNo.remove(SeatNumber);
                lvSeats.setItems(FXCollections.observableArrayList(obSeatNo));
                seatPrice = obSeatNo.size()*50;
                lblmsg.setText(seatPrice+"sar");
                return;
            }//end if 
        });

        //Selections buttons box
        HBox btsealection = new HBox(13, btDelet, btSelect);
        btsealection.setAlignment(Pos.CENTER);

        //View boxes
        HBox seatCor = new HBox(10, lRow, cbRow, lColumn, cbCol);

        VBox selectVB = new VBox(13, LblSelect, seatCor, btsealection, lvSeats);
        selectVB.setAlignment(Pos.CENTER);

        HBox seatsHBox = new HBox(20, selectVB, ivSeats);
        seatsHBox.setAlignment(Pos.CENTER);

        //control buttons
        Button btBack = new Button("Back");
        btBack.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        btBack.setOnAction(e -> {
            stage.setScene(SearchScene);
        });

        Button btNext = new Button("Next");
        btNext.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        btNext.setOnAction(e -> {
            if (!(obSeatNo.isEmpty())) {
                stage.setScene(FoodMenuScene);
            } else {
                lblmsg.setText("Select your seat");
            }
        });

        HBox SeatButtons = new HBox(15, btBack, btNext);
        SeatButtons.setAlignment(Pos.BOTTOM_CENTER);
        SeatButtons.setPadding(new Insets(40));

        VBox seatVBox = new VBox(20, lblmsg, seatsHBox, SeatButtons);
        seatVBox.setAlignment(Pos.BOTTOM_CENTER);

        StackPane Seatstack = new StackPane(SeatBg, seatVBox);
        SeatSelectScene = new Scene(Seatstack, 1200, 650);
        ///////////////////////////////////////////////////////////////////////       

        /////////Food Menu Scene\\\\\\\\\\ Dahy
        ComboBox selectFood = new ComboBox();
        selectFood.getItems().addAll("hot dog", "crispy fries", "onion rings", "Salt popcorn", "Caramel Popcorn", "Cheese Popcorn"
                + "mozzarella fingers");
        selectFood.setEditable(true);
        selectFood.setPrefWidth(160);
        selectFood.setPrefHeight(40);
        selectFood.setVisibleRowCount(3);
        
        //price message
        Label lblmsgFood = new Label();
        lblmsgFood.setTextFill(Color.DARKRED);
        lblmsgFood.setMaxHeight(30);
        lblmsgFood.setMaxWidth(500);
        lblmsgFood.setMinHeight(30);
        lblmsgFood.setMinWidth(500);
        lblmsgFood.setFont(new Font(13.0));
        lblmsgFood.setAlignment(Pos.TOP_CENTER);

        ObservableList<String> obFood = FXCollections.observableArrayList();

        ListView<String> lvFood = new ListView<>();
        lvFood.setMaxSize(200, 200);

        ComboBox selectDrinks = new ComboBox();
        selectDrinks.getItems().addAll("Pepsi", "Sprite", "Orange juice", "Iced tea");
        selectDrinks.setEditable(true);
        selectDrinks.setPrefWidth(160);
        selectDrinks.setPrefHeight(40);
        selectDrinks.setVisibleRowCount(3);
        //ObservableList<String> obSnackDr = FXCollections.observableArrayList();

        Button btSelectSnack = new Button("Select");
        btSelectSnack.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        btSelectSnack.setOnAction(e -> {
            double foodPrice=0;
            String snackStringDr = (String) selectDrinks.getValue();
            String snackString = (String) selectFood.getValue();

            if (selectDrinks == null || selectFood == null) {
                /// lblmsg.setText("Select your snack");
                return;
            } else {
                obFood.add(snackStringDr);
                // lvFood.setItems(FXCollections.observableArrayList(obSnackDr));

                obFood.add(snackString);
                lvFood.setItems(FXCollections.observableArrayList(obFood));
                foodPrice = obFood.size()*20;
                lblmsgFood.setText(foodPrice+"sar");
                updateFoodPrice(foodPrice);
            }
            ////////////////////////////
            try {
                Snacks s1 = new Snacks (selectFood.getValue().toString(),selectDrinks.getValue().toString());
                Session session1 = HibernateUtil.getSessionFactory().openSession();
                Transaction tx1 = session1.beginTransaction();
                Serializable identifier1 = session1.save(s1);  // Use Serializable instead of Integer
                tx1.commit();
                session1.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                
            }
        });

        ImageView FMbg = new ImageView(BACKGROUND_IMAGE_PATH);
        FMbg.setFitHeight(650);
        FMbg.setFitWidth(1200);

        ImageView FMVOX = new ImageView(LOGO_IMAGE_PATH);
        FMVOX.setFitHeight(130);
        FMVOX.setFitWidth(140);

        Button FMBack = new Button("Back");
        FMBack.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        FMBack.setOnAction(e -> {
            stage.setScene(SeatSelectScene);
        });

        Button FMNext = new Button("Next");
        FMNext.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        FMNext.setOnAction(e -> {

            stage.setScene(PaymentScene);

        });

        Label menu = new Label("chooce from the menu : ");
        menu.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        menu.setTextFill(Color.RED);

        GridPane FMpane = new GridPane();
        FMpane.setAlignment(Pos.CENTER);
        FMpane.setVgap(2);
        FMpane.setHgap(0);

        HBox FMbuttons = new HBox(30, FMBack, FMNext, btSelectSnack );
        FMbuttons.setAlignment(Pos.BOTTOM_CENTER);

        VBox FMtop1 = new VBox(30, selectFood, selectDrinks);

        FMtop1.setAlignment(Pos.CENTER);

        VBox FMtop3 = new VBox(10,menu,lblmsgFood,lvFood);
        FMtop3.setAlignment(Pos.CENTER);

        FMtop3.setAlignment(Pos.CENTER);
//        HBox menuHB = new HBox(menu);
//        menuHB.setAlignment(Pos.TOP_RIGHT);
//        FMpane.add(menuHB, 0, 0);
        FMpane.add(FMtop1, 0, 4);

        FMpane.add(FMtop3, 1, 4);
        FMpane.add(FMbuttons, 0, 20, 2, 6);

        StackPane FMstack = new StackPane(FMbg, FMpane);

        FoodMenuScene = new Scene(FMstack, 1200, 650);
        ///////////////////////////////////////////////////////////////////////

        /////////Payment Scene\\\\\\\\\\ Khadijah
        BorderPane Ppane = new BorderPane();

        ImageView Pbg = new ImageView(BACKGROUND_IMAGE_PATH);
        Pbg.setFitHeight(650);
        Pbg.setFitWidth(1200);

        ImageView logo = new ImageView(LOGO_IMAGE_PATH);
        logo.setFitWidth(100);
        logo.setFitHeight(100);

        HBox logoBox = new HBox(20, logo);
        logoBox.setAlignment(Pos.TOP_CENTER);
        Ppane.setTop(logoBox);
        //calculate price - Mawaddah
//        totalPrice = (upSeatPrice + upFoodPrice)*0.15;
//        Text txtPrice = new Text(String.valueOf(totalPrice+"sar"));
//        txtPrice.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        txtPrice.setFill(MyRed);
//        txtPrice.setStrokeWidth(2);
        

        // text payment 
        Text paymentLabel = new Text("PAYMENT");
        paymentLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        paymentLabel.setFill(MyRed);
        paymentLabel.setStrokeWidth(2);

        // TextFields
        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Name");

        TextField cardNumberTextField = new TextField();
        cardNumberTextField.setPromptText("Card Number");

        TextField expirationDateTextField = new TextField();
        expirationDateTextField.setPromptText("Expiration Date");

        TextField cvvTextField = new TextField();
        cvvTextField.setPromptText("CVV");

        // First Line Box
        HBox firstLineBox = new HBox(20, nameTextField, cardNumberTextField);
        firstLineBox.setAlignment(Pos.CENTER);

        // Second Line Box
        HBox secondLineBox = new HBox(20, expirationDateTextField, cvvTextField);
        secondLineBox.setAlignment(Pos.CENTER);

      

        Button PBack = new Button("Back");
        PBack.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        PBack.setOnAction(e -> {
            stage.setScene(FoodMenuScene);
        });
        
        // Error message label
        Label lblmsgPayment = new Label();
        lblmsgPayment.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        lblmsgPayment.setTextFill(Color.RED);
        
        Button confirm = new Button("Confirm");
        confirm.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        confirm.setOnAction(e -> {
//            String CheckNo = cardNumberTextField.getText();
//            String CheckNo1 = nameTextField.getText();
//            String CheckNo2 = expirationDateTextField.getText();
//            String CheckNo3 = cvvTextField.getText();
//            
//            if (CheckNo.isEmpty() || CheckNo1.isEmpty() || CheckNo2.isEmpty() || CheckNo3.isEmpty()) {
//                errorMessageLabel.setText("Error: Please fill in all fields.");
//            }
//                else if (!CheckNo.matches("\\d+") || !(CheckNo3.matches("\\d+"))) {
//               errorMessageLabel.setText("The quantity must be a digit only.");
                 
               stage.setScene(ConfScene);
            
                
                
                //switch scenes
//         stage.setScene(ConfScene);
             
           
        });
        
          // Text Field Box (Center of BorderPane)
        VBox DataBox = new VBox(10, logoBox, paymentLabel,  errorMessageLabel,firstLineBox, secondLineBox);
        DataBox.setAlignment(Pos.CENTER);
        Ppane.setCenter(DataBox);

        HBox PButtons = new HBox(30, PBack, confirm);
        PButtons.setAlignment(Pos.CENTER);
        PButtons.setPadding(new Insets(40));

        Ppane.setBottom(PButtons);
        StackPane Pstack = new StackPane(Pbg, DataBox, Ppane);
        PaymentScene = new Scene(Pstack, 1200, 650);
        ///////////////////////////////////////////////////////////////////////

        /////////Confirmation Scene\\\\\\\\\\ khadijah
        ImageView Cbg = new ImageView(BACKGROUND_IMAGE_PATH);
        Cbg.setFitHeight(650);
        Cbg.setFitWidth(1200);

        Text mainTitle = new Text("CINEMA BOOKING");
        mainTitle.setFont(Font.font("Impact", FontWeight.BOLD, 80));
        mainTitle.setFill(MyRed);
        mainTitle.setStroke(Color.WHITE);
        mainTitle.setStrokeWidth(2);

        Text thankYouMessage = new Text("Thank you for trusting us");
        thankYouMessage.setFont(Font.font("Impact", FontWeight.NORMAL, 20));
        thankYouMessage.setFill(MyOrange);
        thankYouMessage.setStroke(Color.WHITE);

        VBox titleBox = new VBox(10, mainTitle, thankYouMessage);
        titleBox.setAlignment(Pos.CENTER);

        Button CmainMenue = new Button("main Menue");
        CmainMenue.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        CmainMenue.setOnAction(e -> {
            stage.setScene(StartScene);
        });

        Button CClose = new Button("Close");
        CClose.setStyle("-fx-background-color: #FCD24F; -fx-border-color: black; -fx-border-width: 1px; -fx-text-fill: black; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-font-weight: bold;");
        CClose.setOnAction(e -> {
            
            java.io.PrintWriter output = null;
            try {
                Platform.exit();
                java.io.File file = new java.io.File("UserInfo.txt");
                if (file.exists()) {
                    System.out.println("File already exists");
                    System.exit(0);
                }
                // Create a file
                output = new java.io.PrintWriter(file);
                // Write formatted output to the file
                output.write("Ticket info: "+firstNameField.getText()+" ");
                output.write(lastNameField.getText()+":\n");
                output.write("E-mail: "+emailField.getText()+"\n");
                output.write("Phone no: "+numberField.getText()+"\n");
                output.write("Seat Number: "+seat1.getSeatLocation().toString()+"\n");
                // Close the file
                output.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                output.close();
            }
        });

        HBox Cbuttons = new HBox(30, CClose, CmainMenue);
        Cbuttons.setAlignment(Pos.CENTER);
        Cbuttons.setPadding(new Insets(40));
        BorderPane Cpane = new BorderPane();
        Cpane.setBottom(Cbuttons);

        StackPane Confstack = new StackPane(Cbg, titleBox, Cpane);
        ConfScene = new Scene(Confstack, 1200, 650);
        ///////////////////////////////////////////////////////////////////////        

        /////////Stage sittings\\\\\\\\\\       
        stage.setTitle("Cinema Booking");
        stage.setScene(StartScene);
        stage.show();

    }//end Start method

    public static void main(String[] args) {
        launch();
    }

}
 