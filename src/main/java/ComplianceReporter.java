
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileWriter;
import java.io.IOException;

public class ComplianceReporter {

    // 🔗 Connection parameters to target the sandboxed PostgreSQL container
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/banking_compliance";
    private static final String DB_USER = "security_admin";
    private static final String DB_PASSWORD = "BankSecurePassword123!";

    public static void main(String[] args) {
        System.out.println("🚀 Waking up the Java Compliance Reporting Engine...");

        // 🎯 EXACT SQL matching your schema: compliance_audit_logs and user_id columns
        String query = "SELECT log_id, user_id, action_taken, timestamp FROM compliance_audit_logs WHERE action_taken = 'REVOKED'";
        String csvOutputFile = "banking_compliance_report.csv";

        // Open the automated bridge connection to the database
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery();
             FileWriter csvWriter = new FileWriter(csvOutputFile)) {

            System.out.println("✅ Unlocked the PostgreSQL filing cabinet inside Docker successfully!");

            // Write the top structural columns header row inside the CSV file
            csvWriter.append("Log ID,User ID,Action Enforced,Timestamp Logged\n");

            int logCounter = 0;

            // Loop through the data rows flowing out of the database memory
            while (resultSet.next()) {
                int logId = resultSet.getInt("log_id");
                String userId = resultSet.getString("user_id"); // Matched from employee_id
                String actionTaken = resultSet.getString("action_taken");
                String timestamp = resultSet.getString("timestamp");

                // Write the treated record line straight into the spreadsheet row
                csvWriter.append(String.format("%d,%s,%s,%s\n", logId, userId, actionTaken, timestamp));
                logCounter++;
            }

            csvWriter.flush();
            System.out.println("📊 Data Treatment Complete! Total Revoked Profiles Swept: " + logCounter);
            System.out.println("🟩 Official audit file generated successfully: " + csvOutputFile);

        } catch (SQLException e) {
            System.err.println("❌ Database connection error: Make sure Docker Compose is up on port 5432!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("❌ Spreadsheet export error: Failed to generate the final CSV file.");
            e.printStackTrace();
        }
    }
}
