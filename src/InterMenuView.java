import java.util.HashMap;
import java.util.Map;

public class InterMenuView implements ViewInterface {


    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
        Integer choice;
        if(operationName.equals("normal")){
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
        }else{
            do {
                System.out.println("\n\t--------EMPLOYEE MENU--------");
                System.out.println("\t1. Send an application");
                System.out.println("\t2. Show my applications");
                System.out.println("\t3. Manage applications");
                System.out.println("\t4. Show applications sent to my Institution");
                System.out.println("\t5. Answer applications sent to my Institution");
                System.out.print("\t6. Back to login menu");
                System.out.println("\n\t----------------------------");
                System.out.println();

                choice = getInteger("Enter your choice : ", false);
            }
            while (choice == null || choice < 1 || choice > 6);
            System.out.println();
            Map<String, Object> userInput = new HashMap<>();
            userInput.put("MenuChoice", choice);
            int choose;
            switch (choice.intValue()) {
                case 1:
                    functionName = "Institution";
                    do {
                        choose = getInteger("Please choose the option which you want to application as\n1- Person\n2-Institution\nYour answer : ", false);
                    } while (choose < 1 || choose> 2);
                    switch (choose){
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
                    operationName = "";
                    break;
                case 4:
                    functionName = "Employee";
                    operationName = "employeeInstitutionBind";
                    break;
                case 5:
                    functionName = "Employee";
                    operationName = "employeeAnswerPendingApplication";
                    break;
                case 6:
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
