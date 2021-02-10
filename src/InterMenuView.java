import java.util.HashMap;
import java.util.Map;

public class InterMenuView implements ViewInterface {


    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
        Integer choice;

        do {
            System.out.println("\n1. Send an application");
            System.out.println("2. Show my applications");
            System.out.println("3. Manage applications");
            System.out.println("4. Back to main menu");
            System.out.println();

            choice = getInteger("Enter your choice : ", false);
        }
        while (choice == null || choice < 1 || choice > 4);

        Map<String, Object> userInput = new HashMap<>();
        userInput.put("MenuChoice", choice);

        switch (choice.intValue()) {
            case 1:
                functionName = "Institution";
                operationName = "select";
                Map<String, Object> parameters = new HashMap<>();
                return new ViewData(functionName, operationName, parameters);
            case 2:
                functionName = "Application";
                break;
            case 3:
                operationName = "";
                break;
            case 4:
                functionName = "LoginMenu";
                operationName = "";
                break;
            default:
                return new ViewData(null, null);
        }

        return new ViewData(functionName, operationName);
    }
}