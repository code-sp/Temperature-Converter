package com.sp_hash.javafxapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MyMain extends Application {
	public static void main(String[] args) { // Main method is not the part of lifecycle javafx
		System.out.println("main");
		launch(args);
	}

	// Stage is top level container: Defines space for the application.
	// Scene is hosted by stage: Container for the elements like buttons and text fields etc.
	// JavaFx application lifecycle: init(), start(), stop().
	@Override
	public void init() throws Exception {
		System.out.println("Init"); // Initialize the application, optional to override.
		super.init();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.out.println("Start"); // Start the application, Must be overridden, show() to make application visible.

		// Use to connect java and fxml file
		FXMLLoader loader = new FXMLLoader(getClass().getResource("app_layout.fxml"));

		// Load the root none of fxml file which is pane in our case
		VBox rootNode = loader.load();

		MenuBar menuBar = createMenu();
		rootNode.getChildren().add(0, menuBar);
		// Scene contains all the layouts and widgets
		Scene scene = new Scene(rootNode);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Temperature Converter Tool");

		//It will fix the size of stage i.e. we cannot change the size of stage.
		//primaryStage.setResizable(false);

		// show() will make application visible in front of user
		primaryStage.show();
	}

	private MenuBar createMenu() {
		// File Menu
		Menu fileMenu = new Menu("File"); // Menu
		MenuItem newMenuItem = new MenuItem("New"); // Menu Item

		/*  Normal Function which is replaced by Lambda function:
		*
		*   newMenuItem.setOnAction(new EventHandler<ActionEvent>() {
		*   	@Override
		*   	public void handle(ActionEvent event) {
		*   		System.out.println("New is clicked");
		*   	}
		*   });
		*/

		newMenuItem.setOnAction(event -> System.out.println("New is clicked")); // Lambda Function

		SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem(); // Provide separator line
		MenuItem quitMenuItem = new MenuItem("Quit"); // Menu Item

		// Lambda Function
		quitMenuItem.setOnAction(event -> {
			Platform.exit();
			System.exit(0);
		});

		/*  quitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
		*   	@Override
		*   	public void handle(ActionEvent event) {
		*   		Platform.exit();
		*   		System.exit(0);
		*   	}
		*   });
		*/

		fileMenu.getItems().addAll(newMenuItem, separatorMenuItem, quitMenuItem); // Getting Menu Item in fileMenu

		// Help Menu
		Menu helpMenu = new Menu("Help"); // Menu
		MenuItem  aboutApp = new MenuItem("About"); // Menu Item
		helpMenu.setOnAction(event -> aboutApp());  // Lambda Function

		helpMenu.getItems().addAll(aboutApp); // Getting Menu item in helpMenu

		// Menu Bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, helpMenu);
		return menuBar;
	}

	public static void aboutApp() {

		Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
		alertDialog.setTitle("My First Desktop Application");
		alertDialog.setHeaderText("Learning JavaFX");
		alertDialog.setContentText("I am beginner but soon I will be pro and start developing awesome games");

		ButtonType yesBtn = new ButtonType("Yes");
		ButtonType noBtn = new ButtonType("No");

		alertDialog.getButtonTypes().setAll(yesBtn, noBtn);

		Optional<ButtonType> clickedBtn = alertDialog.showAndWait();

		if(clickedBtn.isPresent() && clickedBtn.get() == yesBtn){
			System.out.println("Yes Button Clicked");
		}else{
			System.out.println("No Button is Clicked");
		}
	}

	@Override
	public void stop() throws Exception {
		System.out.println("Stop"); // Called when application is stopped and about to shutdown, optional to override.
		super.stop();
	}
}
/*
*   Container or Layout: Pane, GridPane, VBox: Vertical Box, HBox: Horizontal Box.
*
 */