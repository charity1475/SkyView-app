package application;
	
import geotagg.Geotag;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import openWeather.Send_HTTP_Request2;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		Button btn = new Button();
		btn.setText("Browse");
		DropShadow shadow = new DropShadow();
		//Adding the shadow when the mouse cursor is on
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
		    btn.setEffect(shadow);
		});
		 
		//Removing the shadow when the mouse cursor is off
		btn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
		    btn.setEffect(null);
		});
		Label locationText = new Label();
		locationText.setText("Location:");
		grid.add(locationText, 0, 0);
		TextField location = new TextField();
		grid.add(location, 1, 0);
		grid.add(btn, 1, 1);
		final WebView browser = new WebView();
		final WebEngine webEngine = browser.getEngine();
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				String myLocation = location.getText();
				Geotag located = new Geotag(myLocation);
				String htmlResponse;
				VBox root = new VBox();
				try {
					located.call_me();
					Send_HTTP_Request2 request = new Send_HTTP_Request2(located.lat, located.lon);
					htmlResponse = request.call_me();
				} catch (Exception e1) {
					htmlResponse = "<!DOCTYPE html>\n" + 
							"<html lang=\"en\" dir=\"ltr\">\n" + 
							"  <head>\n" + 
							"    <meta charset=\"utf-8\">\n" + 
							"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + 
							"    <title>Exception</title>\n" + 
							"  </head>\n" + 
							"  <body>\n" + 
							"      <h1>Sorry, correct the location entered in the input field</h1>\n" + 
							"  </body>\n" + 
							"</html>";
				}
				  root.setPadding(new Insets(5));
			        root.setSpacing(5);
			        Scene scene = new Scene(root);
			        stage.setTitle("steinny.web.view");
			        stage.setScene(scene);
			        stage.setWidth(1000);
			        stage.setHeight(700);
				root.getChildren().addAll(browser);
		        webEngine.loadContent(htmlResponse);
		        stage.show();
			}
		});
		VBox root = new VBox();
        root.setPadding(new Insets(5));
        root.setSpacing(5);
        root.getChildren().add(grid);
        Scene scene = new Scene(root);
        stage.setTitle("steinny.web.view");
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(700);
        stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
