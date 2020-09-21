package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import geotagg.Geotag;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import openWeather.Send_HTTP_Request2;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class Main extends Application {
	@Override
	public void start(Stage stage) {



	//---Border pane created---
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10,20, 80,20));

	//---Grid creation---
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));

	//---Label creation---
		Label locationText = new Label();
		locationText.setFont(new Font("Arial", 30));
		locationText.setText("Location:");

	//---Text Field creation---
		TextField location = new TextField();

	//---Button creation---
		Button btn = new Button();
		btn.setText("Browse");

	//---Button, Text field and label into Grid---
		grid.add(locationText, 0, 0);
		grid.add(location, 1, 0);
		grid.add(btn, 1, 1);
//		root.setPadding(new Insets(5));
//		root.setSpacing(5);

	//---space labels for border pane---
		Label top = new Label();
		top.setFont(new Font("Arial", 30));
		top.setText("Top space");

		Label center = new Label();
		center.setFont(new Font("Arial", 30));
		center.setText("Center space");

		Label right = new Label();
		right.setFont(new Font("Arial", 30));
		right.setText("Right space");

		Label left = new Label();
		left.setFont(new Font("Arial", 30));
		left.setText("Left space");


	//---space labels + Grid into Border pane---
		borderPane.setTop(top);
		borderPane.setCenter(center);
		borderPane.setRight(right);
		borderPane.setLeft(left);
		borderPane.setBottom(grid);

		//
		BorderPane.setAlignment(top, Pos.CENTER);
		BorderPane.setAlignment(left, Pos.CENTER);
		BorderPane.setAlignment(right, Pos.CENTER);

	//---Scene Creation---
		Scene scene1 = new Scene(borderPane,600,700);

	//---Adding image to filestream(to project)---
		FileInputStream input;
		try {
			input = new FileInputStream("/home/charity/practjvm/SkyView/src/graphics/weathering.jpeg");
		//---create a image---
			Image image = new Image(input);
		//---create a background image---
			BackgroundImage backgroundimage = new BackgroundImage(image,
					BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,
					new BackgroundSize(1.0, 1.0, true, true, true, true) );

		//---create Background---
			Background background = new Background(backgroundimage);
		//---Background into Pane---
			borderPane.setBackground(background);
		} catch (FileNotFoundException e2) import sun.net.NetworkClient;{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	//---Scene into Stage---
		stage.setScene(scene1);

	//---Setting the stage---
		stage.setTitle("Location Getter");
		stage.setWidth(800);
		stage.setHeight(500);
		stage.show();

	//---Shadow for button created---
		DropShadow shadow = new DropShadow();
	//---Adding the shadow when the mouse cursor is on---
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			btn.setEffect(shadow);
		});
	//Removing the shadow when the mouse cursor is off
		btn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			btn.setEffect(null);
		});

	//---Web view creation---
		final WebView browser = new WebView();

		//---Web engine creation---
		final WebEngine webEngine = browser.getEngine();
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String myLocation = location.getText();
				Geotag located = new Geotag(myLocation);
				String htmlResponse;
				VBox root = new VBox(10);
				//---->adding layout
				HBox hbox = new HBox(10);
				String url="https://www.google.com/maps/place/"+myLocation+"/";
				final WebView map = new WebView();
				final WebEngine mapEngine = map.getEngine();
				hbox.getChildren().add(map);
				mapEngine.load(url);
				
				//
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
							"      <h1>Oops! Location entered could not be determined, Please try again.</h1>\n" +
							"  </body>\n" +
							"</html>";
				}
				//root.setPadding(5);
				root.setAlignment(Pos.CENTER);
				hbox.getChildren().add(browser);
				Scene scene2 = new Scene(root, 50,50);
				stage.setTitle("steinny.web.view");
				stage.setScene(scene2);
				stage.setWidth(800);
				stage.setHeight(500);
				Button btn1 = new Button();
				root.getChildren().addAll(hbox,btn1);
				webEngine.loadContent(htmlResponse);
				btn1.setText("Back");
				DropShadow shadow1 = new DropShadow();
				btn1.addEventFilter(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
					btn1.setEffect(shadow1);
				});
				btn1.addEventFilter(MouseEvent.MOUSE_EXITED, (MouseEvent e) ->{
					btn1.setEffect(null);
				});
				btn1.setOnAction(event -> stage.setScene(scene1));
				/******/
				//adding the first scene
				BorderPane entry = new BorderPane();
				entry.setPadding(new Insets(10,10,10,10));
				/******/
				//---Adding image to filestream(to project)---
					FileInputStream input;
						try {
							input = new FileInputStream("C:\\Users\\Shoko\\Pictures\\Camera Roll\\weatherimg.jpg");
							//---create a image---
							Image image = new Image(input);
							//---create a background image---
							BackgroundImage backgroundimage = new BackgroundImage(image,
									BackgroundRepeat.NO_REPEAT,
									BackgroundRepeat.NO_REPEAT,
									BackgroundPosition.DEFAULT,
									new BackgroundSize(1.0, 1.0, true, true, true, true) );

							//---create Background---
							Background background = new Background(backgroundimage);
							//---Background into Pane---
							root.setBackground(background);
						} catch (FileNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

				stage.show();
			}
		});
	}
	public static void main(String[] args) {
		launch(args);
	}
}