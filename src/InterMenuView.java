import java.util.HashMap;
import java.util.Map;

public class InterMenuView implements ViewInterface {

    public static String personStatus;

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
        Integer choice;
        if (operationName.equals("normal")) {
            personStatus = "normal";
            do {
                System.out.println("--------APPLICATION MENU--------");
                System.out.println("\t1. Send an application");
                System.out.println("\t2. Show my applications");
                System.out.print("\t3. Back to login menu");
                System.out.println("\n--------------------------------");
                System.out.println();

                choice = getInteger("Enter your choice : ", false);
            }
            while (choice == null || choice < 1 || choice > 3);
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
                    functionName = "LoginMenu";
                    operationName = "";
                    break;
                default:
                    return new ViewData(null, null);
            }
        } else {
            personStatus = "auth";
            do {
                System.out.println("\n\t--------EMPLOYEE MENU--------");
                System.out.println("\t1. Send an application");
                System.out.println("\t2. Show my applications");
                System.out.println("\t3. Show applications sent to my Institution");
                System.out.println("\t4. Answer applications sent to my Institution");
                System.out.print("\t5. Back to login menu");
                System.out.println("\n\t----------------------------");
                System.out.println();

                choice = getInteger("Enter your choice : ", false);
            }
            while (choice == null || choice < 1 || choice > 5);
            System.out.println();
            Map<String, Object> userInput = new HashMap<>();
            userInput.put("MenuChoice", choice);
            int choose;
            switch (choice.intValue()) {
                case 1:
                    functionName = "Institution";
                    do {
                        System.out.println("\t1. Person");
                        System.out.println("\t2. Institution");
                        System.out.println();
                        choose = getInteger("Please choose the option which you want to make an application as : ", false);

                    } while (choose < 1 || choose > 2);
                    switch (choose) {
                        case 1:
                            operationName = "select";
                            break;
                        case 2:
                            operationName = "selectInstitutionOperation";
                            break;
                        default:
                            break;
                    }
                    return new ViewData(functionName, operationName, new HashMap<>());
                case 2:
                    functionName = "Pending";
                    operationName = "displayLoggedApplication";
                    return new ViewData(functionName, operationName, new HashMap<>());
                case 3:
                    functionName = "Employee";
                    operationName = "employeeInstitutionBind";
                    break;
                case 4:
                    functionName = "Employee";
                    operationName = "employeeAnswerPendingApplication";
                    break;
                case 5:
                    functionName = "LoginMenu";
                    operationName = "";
                    break;
                default:
                    return new ViewData(null, null);
            }
        }

        return new ViewData(functionName, operationName);
    }
}
