import java.util.HashMap;
import java.util.Map;

public class LoginMenuView implements ViewInterface {

    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
        Integer choice;

        do {
            System.out.println("WELCOME TO INFORMATION RIGHTS SYSTEM");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.println();

            choice = getInteger("Enter your choice : ", false);
        }
        while (choice == null || choice < 1 || choice > 3);

        Map<String, Object> userInput = new HashMap<>();
        userInput.put("MenuChoice", choice);

        switch (choice.intValue()) {
            case 1:
                functionName = "Person";
                operationName = "loginCheck";
                break;
            case 2:
                functionName = "Person";
                operationName = "insert.gui";
                break;
            case 3:
                functionName = null;
                operationName = null;
                break;
            default:
                return new ViewData(null, null);
        }

        return new ViewData(functionName, operationName);
    }
}
