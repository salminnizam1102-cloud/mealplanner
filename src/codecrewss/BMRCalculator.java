package codecrewss;
public class BMRCalculator {
    public static double calculateBMR(int age, double weight, double height, boolean isMale) {
        if (isMale) {
            return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            return 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
    }

    public static double calculateTDEE(double bmr, String activity) {
        double factor = switch (activity) {
            case "Lightly Active" -> 1.375;
            case "Moderately Active" -> 1.55;
            case "Very Active" -> 1.725;
            case "Super Active" -> 1.9;
            default -> 1.2; 
        };
        return bmr * factor;
    }
}

