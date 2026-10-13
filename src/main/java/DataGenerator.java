import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DataGenerator {
    private static final String[] DEPARTMENTS = {"Retail Banking", "Investment Trading", "Fraud Operations", "Wealth Management", "Risk Compliance"};
    private static final String[] MANAGERS = {"Sarah Jenkins (IAM Admin)", "David Vance (Security Lead)", "Elena Rostova (Compliance Manager)"};
    private static final String[] ACTIONS = {"APPROVED", "REVOKED"};

    public static void main(String[] args) {
        System.out.println("Generating simulated banking security data in Java...");
        StringBuilder sqlBuilder = new StringBuilder();
        Random random = new Random();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 1. Generate 100 Bank Employees
        for (int i = 1; i <= 100; i++) {
            String userId = "BNK-" + (1000 + i);
            String name = "Employee_Name_" + i;
            String email = "employee_" + i + "@securebank.com";
            String dept = DEPARTMENTS[random.nextInt(DEPARTMENTS.length)];
            String status = (random.nextDouble() > 0.1) ? "Active" : "Suspended";

            sqlBuilder.append(String.format(
                    "INSERT INTO bank_users (user_id, full_name, email, department, account_status) VALUES ('%s', '%s', '%s', '%s', '%s') ON CONFLICT DO NOTHING;\n",
                    userId, name, email, dept, status
            ));
        }

        // 2. Generate Access Campaigns
        sqlBuilder.append("INSERT INTO access_campaigns (campaign_name, start_date, end_date, status) VALUES ('Q1 SOX Access Review', '2026-01-01', '2026-01-15', 'Completed');\n");
        sqlBuilder.append("INSERT INTO access_campaigns (campaign_name, start_date, end_date, status) VALUES ('Q2 Financial Vault Review', '2026-04-01', '2026-04-15', 'Completed');\n");
        sqlBuilder.append("INSERT INTO access_campaigns (campaign_name, start_date, end_date, status) VALUES ('Q3 Global IAM Audit', '2026-07-01', '2026-07-15', 'Completed');\n");

        // 3. Generate 1,000 Audit Logs
        LocalDateTime baseDate = LocalDateTime.now().minusDays(200);
        for (int i = 0; i < 1000; i++) {
            int campaignId = random.nextInt(3) + 1;
            String randomUserId = "BNK-" + (1000 + (random.nextInt(100) + 1));
            String manager = MANAGERS[random.nextInt(MANAGERS.length)];
            String action = ACTIONS[random.nextInt(ACTIONS.length)];

            // Random timestamp scatter over the past 200 days
            LocalDateTime timestamp = baseDate.plusHours(random.nextInt(4000));
            String timestampStr = timestamp.format(formatter);

            sqlBuilder.append(String.format(
                    "INSERT INTO compliance_audit_logs (campaign_id, user_id, reviewed_by, action_taken, timestamp) VALUES (%d, '%s', '%s', '%s', '%s');\n",
                    campaignId, randomUserId, manager, action, timestampStr
            ));
        }

        // Write the data to a local file
        try (FileWriter writer = new FileWriter("insert_mock_data.sql")) {
            writer.write(sqlBuilder.toString());
            System.out.println("Success! Created 'insert_mock_data.sql' with 1,000+ compliance audit trails.");
        } catch (IOException e) {
            System.err.println("Error writing SQL generation file: " + e.getMessage());
        }
    }
}
