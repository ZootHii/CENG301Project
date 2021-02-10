import java.util.HashMap;
import java.util.Map;

public class LoginMenuView implements ViewInterface {

    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
        Integer choice;

        do {
            System.out.println("\n-----WELCOME TO INFORMATION RIGHTS SYSTEM-----");
            System.out.println("\t\t\t\t1. Login");
            System.out.println("\t\t\t\t2. Register");
            System.out.print("\t\t\t\t3. Exit");
            System.out.println("\n----------------------------------------------");
            System.out.println();

            choice = getInteger("Enter your choice : ", false);
        }
        while (choice == null || choice < 1 || choice > 3);
        System.out.println();
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
