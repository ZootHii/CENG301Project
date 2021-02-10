import java.util.HashMap;
import java.util.Map;

public class ApplicationMenu implements ViewInterface {

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

        Integer choice;
        String choosen = "";
        Map<String, Controller> router = new HashMap<>();
        router.put("MainMenu", new Controller(new MainMenuView(), new NopModel()));
        do {
            System.out.println("Are you a");
            System.out.println("1. Person");
            System.out.println("2. Institution\n (Press any other number to exit)");
            System.out.println();

            choice = getInteger("Enter your choice : ", false);
        }
        while (choice == null || choice < 1 || choice > 2);

        switch (choice) {
            case 1: choosen = "Person";
                    operationName = "insert.gui";
                    break;
            case 2: choosen = "Institution";
                    operationName = "insert.gui";
                    break;

            default: return new ViewData(null, null);
        }

        return new ViewData(choosen, operationName, new HashMap<>());
    }
}
