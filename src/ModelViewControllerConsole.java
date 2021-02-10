import java.util.*;


public class ModelViewControllerConsole {

	public static void main(String[] args) throws Exception {
		// Connect to database
		connectToDatabase();

		// Model View Controller (MVC)
		// Router knows all the controllers
		Map<String, Controller> router = new HashMap<>();
		router.put("Menu", new Controller(new MenuView(), new NopModel()));
		router.put("MainMenu", new Controller(new MainMenuView(), new NopModel()));
		router.put("Application", new Controller(new ApplicationView(), new ApplicationModel()));
		router.put("Institution", new Controller(new InstitutionView(), new InstitutionModel())); // AHMET
		router.put("Person", new Controller(new PersonView(), new PersonModel()));
		router.put("ApplicationMenu", new Controller(new ApplicationMenu(), new NopModel()));
		router.put("Address", new Controller(new AddressView(), new AddressModel()));
		router.put("Form", new Controller(new FormView(), new FormModel()));

		ViewData viewData = new ViewData("Person", "loginCheck");
		do {
			// Model, View, and Controller
			Controller controller = router.get(viewData.functionName);
			ModelData modelData = controller.executeModel(viewData);
			viewData = controller.getView(modelData, viewData.functionName, viewData.operationName);


			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println();
		}
		while (viewData.functionName != null);


		/*ViewData viewData = new ViewData("MainMenu", "");
		do {
			// Model, View, and Controller
			Controller controller = router.get(viewData.functionName);
			ModelData modelData = controller.executeModel(viewData);
			viewData = controller.getView(modelData, viewData.functionName, viewData.operationName);
			
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println();
		}
		while (viewData.functionName != null);*/
		
		System.out.println();
		System.out.println();
		System.out.println("Program is ended...");


		// Disconnect from database
		disconnectFromDatabase();
	}

	
	public static void connectToDatabase() {
		DatabaseUtilities.host = "localhost:1433";
		DatabaseUtilities.databaseName = "CENG301Project";
		
		try {
			DatabaseUtilities.getConnection();
		}
		catch(Exception e) {
			System.out.println("Exception occured : " + e);
			return;
		}		
	}
	
	public static void disconnectFromDatabase() {
		try {
			DatabaseUtilities.disconnect();
		}
		catch(Exception e) {
			System.out.println("Error disconnecting from database : " + e);
			return;
		}		
	}
	
}
