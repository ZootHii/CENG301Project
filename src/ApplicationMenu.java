import java.util.HashMap;
import java.util.Map;

public class ApplicationMenu implements ViewInterface {

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
        Integer choice;

        do {

            // KULLANMIYORUZ ŞUAN

            System.out.println("Are you a");
            System.out.println("1. Person");
            System.out.println("2. Institution\n (Press any other number to exit)");
            System.out.println();

            choice = getInteger("Enter your choice : ", false);
        }
        while (choice == null || choice < 1 || choice > 2);

        switch (choice) {
            case 1: functionName = "Person";
                    operationName = "insert.gui";
                    break;
            case 2: functionName = "Institution";
                    operationName = "insert.gui";
                    break;

            default: return new ViewData(null, null);
        }

        return new ViewData(functionName, operationName, new HashMap<>());
    }
}
