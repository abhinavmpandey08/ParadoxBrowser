package main;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Window extends Application
{
	public static final String TITLE = "Paradox Browser Dev 2.0";
	public final WebView browser = new WebView();
	public final WebEngine webEngine = browser.getEngine();
	public static final String GOOGLE = "http://google.com";
	
	private TextField addressBar;
	private Scene scene;
	private TabPane tp;
	private HBox bar;
	private VBox vb;
	private Tab t1;        	
	private ToolBar tb;

	//strings
	private String text1;
	private String url;
	private String homepage;
	
	//array lists
	private ArrayList <String> pLinks;
	private ArrayList <String> fLinks;
	

	//buttons
	private Button back;
	private Button forward;
	private Button home;
	private Button refresh;
	private Button go;
	
	private History history = new History();
	
	private int i = 1;

	@Override
	public void start(Stage window) throws Exception 
	{
		window.setTitle(TITLE);
 		
		homepage = GOOGLE;
		
		webEngine.load(homepage);
		
		addressBar = new TextField();
		addressBar.setText(GOOGLE);
		addressBar.setMinSize(500, 20);
		addressBar.setOnAction(e -> this.loadWebpage());
		
		url = addressBar.getText();

		text1 = "New Tab"; 
		
		back = new Button("Back");
		back.setMinSize(40, 20);		
		back.setOnAction(e -> this.loadWebpage(this.history.getBack()));
		
		forward = new Button("Forward");
		forward.setMinSize(40, 20);		
		forward.setOnAction(e -> this.loadWebpage(this.history.getForward()));
		
		refresh = new Button("Refresh");
		refresh.setMinSize(40, 20);		
		refresh.setOnAction(e -> this.loadWebpage(url));
		
		home = new Button("Home");
		home.setMinSize(40, 20);		
		home.setOnAction(e -> this.loadWebpage(GOOGLE));

		go = new Button("Go");
		go.setMinSize(40, 20);		
		go.setOnAction(e -> this.loadWebpage());

		bar = new HBox();
		bar.getChildren().addAll(back, forward, refresh, home, addressBar, go);

		tb = new ToolBar(bar);

		browser.setMinSize(1650, 920);
		
		vb = new VBox();
		vb.setPrefSize(1650, 1000);
		vb.setOnKeyPressed(e -> fixURL());
		vb.setOnMouseClicked(e -> fixURL());
		vb.getChildren().addAll(tb, browser);

		tp = new TabPane();
		t1 = new Tab();
		t1.setText(text1);
		addTabs(t1);
		t1.setContent(vb);

		scene = new Scene(tp, 1650, 1000); 

		window.setScene(scene);
		window.setMaximized(true);
		window.show();
	}

	private void fixURL() 
	{
		addressBar.setText(webEngine.getLocation());
		System.out.println(i++);
	}

	private void addTabs(Tab tab)
	{
		tp.getTabs().add(tab);
	}

	private void loadWebpage()
	{
		url = addressBar.getText();
		if(url.length() == 0)
			addressBar.setPromptText("Enter a valid URL");
		else if(!url.startsWith("http"))
		{
			url = "http://" + url;
			addressBar.setText(url);
		}
		webEngine.load(url);
		
		this.history.addBack(url);
	}

	private void loadWebpage(String www)
	{
		addressBar.setText(www);
		this.loadWebpage();
	}
	
	private String getCurrentURL()
	{
		return webEngine.getLocation();
	}
	
	private void updateLinks()
	{
		
	}
	
	private void addPLinks()
	{
		pLinks.add(this.getCurrentURL());
		System.out.println(pLinks);
		this.removeFLinks();
	}
	
	private void removePLinks()
	{
		pLinks.remove(pLinks.size() - 1);
		System.out.println(pLinks);
	}
	
	private void addFLinks()
	{
		fLinks.add(this.getCurrentURL());
		System.out.println(fLinks);
		this.removePLinks();
	}
	
	private void removeFLinks()
	{
		fLinks.remove(fLinks.size() - 1);
		System.out.println(fLinks);
	}
	
	public static void main(String args[])
	{
		launch(args);
	}

}
