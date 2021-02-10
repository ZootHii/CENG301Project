import java.util.HashMap;
import java.util.Map;

public class InterMenuView implements ViewInterface {


    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
        Integer choice;

        do {
            System.out.println("--------APPLICATION MENU--------");
            System.out.println("\t1. Send an application");
            System.out.println("\t2. Show my applications");
            System.out.println("\t3. Manage applications");
            System.out.print("\t4. Back to login menu");
            System.out.println("\n--------------------------------");
            System.out.println();

            choice = getInteger("Enter your choice : ", false);
        }
        while (choice == null || choice < 1 || choice > 4);
        System.out.println();
        Map<String, Object> userInput = new HashMap<>();
        userInput.put("MenuChoice", choice);

        switch (choice.intValue()) {
            case 1:
                functionName = "Institution";
                operationName = "select";
                return new ViewData(functionName, operationName, new HashMap<>());
            case 2:
                functionName = "Pending";
                operationName = "displayLoggedApplication";
                return new ViewData(functionName, operationName, new HashMap<>());
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
