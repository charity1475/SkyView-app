package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import geotagg.Geotag;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

	//---Scene1 objects---
		AnchorPane anchor = new AnchorPane();

		Label welcome = new Label("Welcome");
		welcome.setFont(new Font(50));

		JFXButton cont = new JFXButton("Continue");
		cont.setFont(new Font(15));
		cont.setBackground(new Background(new BackgroundFill(Color.DARKCYAN,
				new CornerRadii(40),
				Insets.EMPTY)));

		AnchorPane.setTopAnchor(welcome,100.0);
		AnchorPane.setRightAnchor(welcome,100.0);
		AnchorPane.setLeftAnchor(welcome,300.0);

		AnchorPane.setBottomAnchor(cont,70.0);
		AnchorPane.setRightAnchor(cont,320.0);
		AnchorPane.setLeftAnchor(cont,320.0);
		anchor.getChildren().addAll(welcome,cont);

	//---cont shadow---
		DropShadow shadow = new DropShadow();
		cont.addEventFilter(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			cont.setEffect(shadow);
		});
		cont.addEventFilter(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			cont.setEffect(null);
		});

		Scene scene1 = new Scene(anchor);
		stage.setScene(scene1);
	//---Setting the stage---
		stage.setTitle("Introduction");
		stage.setWidth(800);
		stage.setHeight(500);
		stage.show();

//---Scene2 objects---
	//---Anchor pane created---
		AnchorPane anchor1 = new AnchorPane();
		AnchorPane backAnchor = new AnchorPane();
	//---  blur ---
		GaussianBlur blur = new GaussianBlur();
		blur.setRadius(10);
		backAnchor.setEffect(blur);
	//---stack pane created---
		StackPane stack1 = new StackPane();
	//---Grid creation---
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		grid.setEffect(shadow);
	//--- Grid pane nodes ---
		//---Label creation---
		Label locationText = new Label();
		locationText.setFont(new Font("Arial", 25));
		locationText.setText("Location:");
		//---Text Field creation---
		TextField location = new TextField();
		//---Button creation---
		JFXButton btn = new JFXButton();
		btn.setText("Browse");
		btn.setBackground(new Background(new BackgroundFill(Color.DARKCYAN,
				new CornerRadii(40),
				Insets.EMPTY)));
	//---shadow for btn---
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			btn.setEffect(shadow);
		});
		btn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			btn.setEffect(null);
		});
	//---Button, Text field and label into Grid---
		grid.add(locationText, 0, 0);
		grid.add(location, 1, 0);
		grid.add(btn, 1, 1);
		grid.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, new CornerRadii(5), Insets.EMPTY)));
		JFXButton tointro = new JFXButton("Back");
		tointro.setFont(new Font(15));
		tointro.setBackground(new Background(new BackgroundFill(Color.DARKCYAN,
				new CornerRadii(40),
				Insets.EMPTY)));
	//---Tointro shadow---
		tointro.addEventFilter(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			tointro.setEffect(shadow);
		});
		tointro.addEventFilter(MouseEvent.MOUSE_EXITED, (MouseEvent e) ->{
			tointro.setEffect(null);
		});
		tointro.setOnAction(event1 -> stage.setScene(scene1));
	//--- Grid into anchor---
		anchor1.getChildren().addAll(grid,tointro);
		AnchorPane.setBottomAnchor(grid,70.0);
		AnchorPane.setLeftAnchor(grid,220.0);
		AnchorPane.setRightAnchor(grid,220.0);
		AnchorPane.setBottomAnchor(tointro,20.0);
		AnchorPane.setLeftAnchor(tointro,320.0);
		AnchorPane.setRightAnchor(tointro,320.0);
	//---backanchor + anchor1 into stack1---
		stack1.getChildren().addAll(backAnchor,anchor1);
	//---Scene Creation---
		Scene scene2 = new Scene(stack1,600,700);

//---scene3 objects---
	//---Vboxes created---
		VBox vbox1 = new VBox();
		VBox vbox2 = new VBox(10);
	//---Stackpane2 created---
		StackPane stack2 = new StackPane();
	//---margins, paddings and alignment of vbox1 + vbox2 in hbox---
		vbox2.setPadding(new Insets(5));
		vbox1.setPadding(new Insets(7));
		vbox2.setAlignment(Pos.CENTER);
		vbox1.setMaxWidth(200);
		vbox1.setMinWidth(100);
		vbox1.setMaxHeight(200);
		vbox1.setMinHeight(170);
	//---Adding the shadow to vbox1---
		vbox1.setEffect(shadow);
	//---Web view + web engine objects created---
		final WebView map = new WebView();
		final WebEngine mapEngine = map.getEngine();
	//---btn1 created---
		Button btn1 = new Button("Back");
		btn1.setFont(new Font(15));
		btn1.setMinSize(130, 32);
		btn1.setBackground(new Background(new BackgroundFill(Color.DARKCYAN,
				new CornerRadii(40),
				Insets.EMPTY)));
		VBox.setMargin(btn1, new Insets(0, 0, 18, 0));

	//---btn1 shadow---
		btn1.addEventFilter(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			btn1.setEffect(shadow);
		});
		btn1.addEventFilter(MouseEvent.MOUSE_EXITED, (MouseEvent e) ->{
			btn1.setEffect(null);
		});
	//---btn1 action back to scene2---
		btn1.setOnAction(event -> stage.setScene(scene2));
	//---vbox2 into scene3---
		Scene scene3 = new Scene(vbox2);
	//---Web view creation---
		final WebView browser = new WebView();
	//---Web engine creation---
		final WebEngine webEngine = browser.getEngine();
	//---Adding the shadow to browser---
		browser.setEffect(shadow);
	//---browser into vbox1---
		vbox1.getChildren().add(browser);
		vbox1.setBackground(new Background(new BackgroundFill(Color.DARKGREY, new CornerRadii(10), Insets.EMPTY)));
		stack2.getChildren().addAll(map,vbox1);
		StackPane.setMargin(vbox1,new Insets(150,540,90,10));
		StackPane.setAlignment(vbox1,Pos.CENTER_LEFT);
		vbox2.getChildren().addAll(stack2,btn1);

	//---cont button action---
		cont.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//---Scene into Stage---
				stage.setScene(scene2);
				//---Setting the stage---
				stage.setTitle("Location Getter");
				stage.show();
			}
		});
	//---btn button action---
		btn.setOnKeyPressed(event -> {
			switch (event.getCode()) {
				case ENTER:
					//---Get typed location---
					String myLocation = location.getText();

					if(myLocation.equals("")){

						JFXDialogLayout Dlayout = new JFXDialogLayout();
						JFXDialog dialog = new JFXDialog(stack1,Dlayout,JFXDialog.DialogTransition.LEFT);
						JFXButton closeD = new JFXButton("OK");
						closeD.setMinSize(150, 32);
						closeD.setBackground(new Background(new BackgroundFill(Color.DARKCYAN,
								new CornerRadii(40),
								Insets.EMPTY)));
						Label text = new Label("A location must be entered");
						text.setFont(new Font(25));
						Dlayout.setHeading(text);
						Dlayout.setActions(closeD);
						dialog.show();
						anchor1.setEffect(blur);
						closeD.setOnAction(event1 -> dialog.close());
						dialog.setOnDialogClosed(event1 -> anchor1.setEffect(null));

					}else{
						//---Location into geotag constructor---
						Geotag located = new Geotag(myLocation);
						String htmlResponse;
						//---url sent in request if the map---
						String url="https://www.google.com/maps/place/"+myLocation+"/";
						//---map engine loads the url---
						mapEngine.load(url);
						try {
							//---callme method in geotag to get lat and lon response---
							located.call_me();
							//---lat + lon sent in request in SendHttpRequest2---
							Send_HTTP_Request2 request = new Send_HTTP_Request2(located.lat, located.lon);
							htmlResponse = request.call_me();
						} catch (Exception e1) {
							//---Exception response---
							htmlResponse = "<!DOCTYPE html>\n" +
									"<html lang=\"en\" dir=\"ltr\">\n" +
									"  <head>\n" +
									"    <meta charset=\"utf-8\">\n" +
									"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
									"    <title>Exception</title>\n" +
									"  </head>\n" +
									"  <body>\n" +
									"      <h1 style = \"font-size:18px;\">Oops! Location entered could not be determined, Please try again.</h1>\n" +
									"  </body>\n" +
									"</html>";
						}

						webEngine.loadContent(htmlResponse);
						stage.setTitle("The weather");
						stage.setScene(scene3);
						stage.show();

					}
					break;
			default:
				break;
			}
		});

		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				//---Get typed location---
				String myLocation = location.getText();

				 if(myLocation.equals("")){

					 JFXDialogLayout Dlayout = new JFXDialogLayout();
					 JFXDialog dialog = new JFXDialog(stack1,Dlayout,JFXDialog.DialogTransition.LEFT);
					 JFXButton closeD = new JFXButton("OK");
					 closeD.setMinSize(150, 32);
					 closeD.setBackground(new Background(new BackgroundFill(Color.DARKCYAN,
							 new CornerRadii(40),
							 Insets.EMPTY)));
					 Label text = new Label("A location must be entered");
					 text.setFont(new Font(25));
					 Dlayout.setHeading(text);
					 Dlayout.setActions(closeD);
					 dialog.show();
					 anchor1.setEffect(blur);
					 closeD.setOnAction(event -> dialog.close());
					 dialog.setOnDialogClosed(event -> anchor1.setEffect(null));

				}else{
					 //---Location into geotag constructor---
					 Geotag located = new Geotag(myLocation);
					 String htmlResponse;
					 //---url sent in request if the map---
					 String url="https://www.google.com/maps/place/"+myLocation+"/";
					 //---map engine loads the url---
					 mapEngine.load(url);
					 try {
						 //---callme method in geotag to get lat and lon response---
						 located.call_me();
						 //---lat + lon sent in request in SendHttpRequest2---
						 Send_HTTP_Request2 request = new Send_HTTP_Request2(located.lat, located.lon);
						 htmlResponse = request.call_me();
					 } catch (Exception e1) {
						 //---Exception response---
						 htmlResponse = "<!DOCTYPE html>\n" +
								 "<html lang=\"en\" dir=\"ltr\">\n" +
								 "  <head>\n" +
								 "    <meta charset=\"utf-8\">\n" +
								 "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
								 "    <title>Exception</title>\n" +
								 "  </head>\n" +
								 "  <body>\n" +
								 "      <h1 style = \"font-size:18px;\">Oops! Location entered could not be determined, Please try again.</h1>\n" +
								 "  </body>\n" +
								 "</html>";
					 }

					 webEngine.loadContent(htmlResponse);
					 stage.setTitle("The weather");
					 stage.setScene(scene3);
					 stage.show();

				 }



			}
		});

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
			vbox2.setBackground(background);
			anchor.setBackground(background);
			backAnchor.setBackground(background);

		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


	}

	public static void main(String[] args) {
		launch(args);
	}
}