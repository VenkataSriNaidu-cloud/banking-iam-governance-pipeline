# banking-iam-governance-pipeline
An enterprise-grade Identity Governance &amp; Administration (IGA) pipeline for banking compliance.
# Enterprise Access Governance & Compliance Automation Pipeline

A production-grade, containerized **DevOps & Platform Engineering architecture sandbox** that models end-to-end user access governance tracking, continuous integration, and automated compliance auditing within a highly regulated corporate banking framework.

This repository demonstrates how to bridge distinct platform layers—including client interfaces, identity boundaries, containerized storage networks, backend processing engines, and automated cloud workflows—into a unified, resilient delivery lifecycle.

---

## Architectural Traffic Flow Matrix

```text
 [ Client UI Terminal ] ➔ (Triggers administrative "REVOKED" click events)
              │
              ▼
 [ ForgeRock IAM Gate ] ➔ (Validates token metadata & intercepts security state)
              │
              ▼
 [  Docker Compose / SQL ] ➔ (Isolates multi-container network & PostgreSQL engine)
              │
              ▼
 [  Java Backend Utility ] ➔ (Leverages JDBC driver to extract & parse raw logs)
              │
              ▼
 [  Audit-Ready Spreadsheet ] ➔ (Outputs 'banking_compliance_report.csv')
              │
              ▼
 [  GitHub Actions Runway ] ➔ (Executes continuous integration server compilation)
```

---

##  The DevOps Toolchain Blueprint

* **Integrated Workspace:** **IntelliJ IDEA** for centralized file editing, local compilation validation, and version control stage management.
* **Infrastructure Sandbox:** **Docker & Docker Compose** for local microservice isolation, network bridging, and parameter binding.
* **Storage Vault:** **PostgreSQL & pgAdmin 4** for data schema modeling, logging initializations, and structured relational queries.
* **Identity Simulation Plane:** Simulated **ForgeRock Identity Management** data boundaries enforcing perimeter token tracking parameters.
* **Application Engine:** **Java 17 (Maven)** utilizing native **JDBC drivers** for high-fidelity data treatment and log parsing.
* **Cloud Automation (CI/CD):** **GitHub Actions & YAML** for programmatically provisioning remote Linux environments and running continuous integration checks.

---

##  Repository Layout Directory
```text
├── .github/
│   └── workflows/
│       └── ci-cd-pipeline.yml   #  Automated GitHub Actions cloud runner blueprint
├── src/
│   └── main/
│       └── java/
│           └── com/banking/governance/
│               └── ComplianceReporter.java # ☕ Core Java JDBC extraction utility engine
├── docker-compose.yml           #  Multi-container network virtualization mapping
├── schema.sql                   #  Relational database structural DDL blueprints
├── insert_mock_data.sql         #  ForgeRock data injection record simulation
├── index.html                   #  Interactive bank manager simulation dashboard
└── pom.xml                      #  Maven dependency management structure
```

---

##  Core System Architecture Mechanics

### 1. The Perimeter Identity Layer (`index.html` & `schema.sql`)
* Focuses on simulating a responsive, front-facing administration panel representing security operator access controls.
* When a privileged manager (e.g., `Sarah Jenkins`) forcefully terminates employee permissions, the platform generates granular transaction payloads stamped with the exact security lifecycle marker: **`REVOKED`**.

### 2. The Isolated Infrastructure Vault (`docker-compose.yml`)
* Isolates the core relational storage plane completely away from the local computer operating system host using an optimized Linux container image.
* Exposes external service connectivity on standard database port `5432` while keeping credentials abstracted behind environment parameter blocks.

### 3. The Backend Extraction Engine (`ComplianceReporter.java`)
* Instantiates a high-performance database connection socket using local JDBC drivers to securely access the running storage tables.
* Executes precise relational queries to filter for targeted organizational threats:
  ```sql
  SELECT log_id, user_id, action_taken, timestamp 
  FROM compliance_audit_logs 
  WHERE action_taken = 'REVOKED';
  ```
* Systematically processes the result set registers line-by-line, formatting transaction timestamps and streaming records into a structured, physical `.csv` spreadsheet file fit for external governance auditors.

### 4. The Cloud Automation Engine (`ci-cd-pipeline.yml`)
* A declarative Continuous Integration (CI/CD) assembly framework configured to execute automatically on every codebase push or pull request.
* Instantly spins up a headless **Ubuntu Linux virtual machine**, downloads the repository dependencies via **Maven**, provisions a sandboxed **PostgreSQL database container matching production parameters**, runs the compilation logic, and validates application stability completely untouched by human hands.

---

##  Local Installation & Execution Steps

###  Prerequisites
* Java Development Kit (JDK 17) and Maven installed locally.
* Docker Desktop active in your background systems layer.
* IntelliJ IDEA or an equivalent integrated workspace.

###  Step-by-Step Deployment Guide

**1. Clone the repository blueprints and boot the infrastructure sandbox:**
```bash
docker-compose up -d
```

**2. Initialize the Database & Mock Telemetry Logs:**
* Access the local **pgAdmin panel** by opening a browser tab to **`http://localhost:8080`**.
* Login with the configured portal parameters (`admin@securebank.com` / `AdminPassword123!`).
* Open the SQL Query Tool on the `banking_compliance` database database. Execute the table blueprints inside **`schema.sql`** first, then populate the logs using **`insert_mock_data.sql`**.

**3. Interact with the Live Management Console:**
* Right-click on **`index.html`** inside IntelliJ and select **Open in Browser**.
* Click the red **REVOKE ACCESS** buttons to dynamically see the simulated ForgeRock token authorization logic execute and stream mock SQL queries in real-time.

**4. Trigger the Automated Processing Engine:**
* Open **`ComplianceReporter.java`** in your editor window.
* Click the **green play arrow button** next to the main method line.
* Watch the text log terminal complete its extraction sweep and find your fresh **`banking_compliance_report.csv`** file sitting cleanly in your project root list.

---

##  Key Engineering Takeaways & Troubleshot Lessons
Building this lifecycle architecture provided deep experience isolating typical platform environment integration hurdles:
* **Infrastructure Cache Eviction:** Diagnosed container cached storage synchronization locks, deploying volume teardown procedures (`docker-compose down -v`) to successfully wipe stale volume memories and ensure fresh credential alignment.
* **Data Drift Remediation:** Troubleshot silent data log aggregation failures caused by strict string query mismatches (`'REVOKE'` vs `'REVOKED'`), utilizing high-fidelity database inspections to identify drift and align database filtering queries exactly.
