CREATE TABLE bank_users (
    user_id VARCHAR(50) PRIMARY KEY,       
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    department VARCHAR(50) NOT NULL,
    account_status VARCHAR(20) DEFAULT 'Active' 
);

CREATE TABLE access_campaigns (
    campaign_id SERIAL PRIMARY KEY,
    campaign_name VARCHAR(100) NOT NULL,    
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'Pending'    
);

CREATE TABLE compliance_audit_logs (
    log_id SERIAL PRIMARY KEY,
    campaign_id INT REFERENCES access_campaigns(campaign_id),
    user_id VARCHAR(50) REFERENCES bank_users(user_id),
    reviewed_by VARCHAR(100) NOT NULL,     
    action_taken VARCHAR(20) NOT NULL,     
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
