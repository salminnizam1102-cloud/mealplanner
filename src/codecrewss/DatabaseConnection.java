package codecrewss;
import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/meal_planner?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "meharin1102@"; 

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
    public static String getMealSuggestions(double tdee) {
        String sql = "SELECT breakfast, breakfast_calories, lunch, lunch_calories, dinner, dinner_calories "
                   + "FROM meals WHERE ? BETWEEN min_calories AND max_calories LIMIT 1";
        try (Connection conn = getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, tdee);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String breakfast = rs.getString("breakfast");
                int bCal = rs.getInt("breakfast_calories");
                String lunch = rs.getString("lunch");
                int lCal = rs.getInt("lunch_calories");
                String dinner = rs.getString("dinner");
                int dCal = rs.getInt("dinner_calories");
                
                
                StringBuilder sb = new StringBuilder();
               
                sb.append("\t•Recommended Meal Plan (").append(String.format("%.0f", tdee)).append(" kcal/day):\n\n");
                sb.append("\tBreakfast: ").append(breakfast).append(" (").append(bCal).append(" kcal)\n");
                sb.append("\tLunch: ").append(lunch).append(" (").append(lCal).append(" kcal)\n");
                sb.append("\tDinner: ").append(dinner).append(" (").append(dCal).append(" kcal)\n");
                sb.append("\n\n\t•Adjust portions as needed for exact TDEE.\n");
                sb.append(" \t •Consult a nutritionist for personalized advice.\n");
                
                return sb.toString();
            } else {
                return "No meal plan found for your TDEE range.\nTry adjusting your activity level or inputs.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error loading meal plan.\nCheck database connection.";
        }
    }
}