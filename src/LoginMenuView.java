import java.util.HashMap;
import java.util.Map;

public class LoginMenuView implements ViewInterface {

    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
        Integer choice;
        String choosen = "";
        do {
            System.out.println("1. Login");
            System.out.println("2. Application");
            System.out.println("3. Manage applications (for authorities)");
            System.out.println();

            choice = getInteger("Enter your choice : ", false);
        }
        while (choice == null || choice < 1 || choice > 3);

        Map<String, Object> userInput = new HashMap<>();
        userInput.put("MenuChoice", choice);

        switch (choice.intValue()) {
            case 1:
                choosen = "MainMenu";
                break;
            case 2:
                choosen = "Application";
                break;
            //case 3: operationName = "insert.gui";	break;
            default:
                return new ViewData(null, null);
        }
        return new ViewData(choosen, operationName = "");
    }
}
