package com.sp_hash.javafxapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
	public Label welcomeLabel;
	@FXML
	public ChoiceBox<String> choiceBox;
	@FXML
	public TextField userInputField;
	@FXML
	public Button convertButton;

	private static final String C_TO_F = "Celsius to Fahrenheit";
	private static final String F_TO_C = "Fahrenheit to Celsius";
	private boolean isC_To_F = true;
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		choiceBox.getItems().add(C_TO_F);
		choiceBox.getItems().add(F_TO_C);
		choiceBox.setValue(C_TO_F);

		choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.equals(C_TO_F)) {
				isC_To_F = true;
			} else {
				isC_To_F = false;
			}
		});

		convertButton.setOnAction((event) -> {
			convert();
		});
	}

	private void convert() {
		String input = userInputField.getText(); // 23.4 = "23.4"

		float enteredTemperature = 0.0f;
		try {
			enteredTemperature = Float.parseFloat(input);// 23.4f
		} catch (Exception exception){
			warnUser();
			return;
			// No code will executed
		}

		float newTemperature = 0.0f;
		if(isC_To_F) {          // If user has selected "Celsius to Fahrenheit"
			newTemperature = (enteredTemperature * 9/5) + 32;
		}else {
			newTemperature = (enteredTemperature - 35) * 5/9;
		}

		display(newTemperature);
	}

	private void warnUser() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Occurred");
		alert.setHeaderText("Invalid Temperature Entered");
		alert.setContentText("Please enter a valid Temperature");
		alert.show();
	}

	private void display(float newTemperature) {
		String unit = isC_To_F? "F" : "C";
		System.out.println("The new temperature is: " + newTemperature + unit);

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("The new temperature is: " + newTemperature + unit);
		alert.show();
	}
}
