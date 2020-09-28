package datahandler;

public class Hold {

	public static void main(String[] args) {
		Database database = new Database();
		database.setLocationString("London");
		database.store();

	}

}
