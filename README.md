# 🏪 helaPOS - Point of Sale System

<div align="center">

![helaPOS Logo](src/assets/_Logo-removebg%20re%20size.png)

[![Java](https://img.shields.io/badge/Java-20-orange.svg)](https://www.oracle.com/java/)
[![NetBeans](https://img.shields.io/badge/NetBeans-21-blue.svg)](https://netbeans.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

**A comprehensive Point of Sale (POS) system built with Java Swing for retail business management**

</div>

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [System Architecture](#-system-architecture)
- [Installation & Setup](#-installation--setup)
- [Database Setup](#-database-setup)
- [Usage Guide](#-usage-guide)
- [Project Structure](#-project-structure)
- [User Roles & Permissions](#-user-roles--permissions)
- [Documentation](#-documentation)
- [Contributing](#-contributing)
- [License](#-license)
- [Contact](#-contact)

## 🌟 Overview

**helaPOS** is a modern, feature-rich Point of Sale system designed for retail businesses. Built with Java Swing, it provides a comprehensive solution for managing sales, inventory, customers, suppliers, and employees. The system supports multiple user roles with appropriate permissions and includes advanced features like purchase order management, goods receipt notes (GRN), and detailed reporting.

### 🎯 Key Objectives

- **Efficient Sales Management**: Streamline the sales process with an intuitive interface
- **Inventory Control**: Real-time stock management and tracking
- **User Management**: Role-based access control for different user types
- **Business Intelligence**: Comprehensive reporting and analytics
- **Data Integrity**: Robust validation and error handling

## ✨ Features

### 🛒 Sales Management
- **Point of Sale Interface**: User-friendly sales processing
- **Invoice Generation**: Automated invoice creation and printing
- **Payment Processing**: Multiple payment method support
- **Discount Management**: Item-level and invoice-level discounts
- **Customer Management**: Customer information and purchase history

### 📦 Inventory Management
- **Product Catalog**: Comprehensive product information management
- **Category & Subcategory**: Hierarchical product organization
- **Unit Management**: Multiple unit types (pieces, kg, liters, etc.)
- **Stock Tracking**: Real-time inventory levels
- **Stock Alerts**: Low stock notifications and reorder points

### 🏢 Purchase Management
- **Supplier Management**: Supplier information and contact details
- **Purchase Orders**: Create and manage purchase orders
- **Goods Receipt Notes (GRN)**: Track incoming inventory
- **Purchase History**: Complete purchase audit trail

### 👥 User Management
- **Multi-Role Support**: Admin, Supervisor, and Cashier roles
- **User Authentication**: Secure login system
- **Employee Management**: Staff information and salary tracking
- **Permission Control**: Role-based feature access

### 📊 Reporting & Analytics
- **Sales Reports**: Daily, weekly, monthly sales analysis
- **Inventory Reports**: Stock levels and movement reports
- **Financial Reports**: Revenue and expense tracking
- **Dashboard**: Real-time business metrics

### 🔧 System Features
- **Database Integration**: MySQL database connectivity
- **Data Validation**: Comprehensive input validation
- **Error Handling**: Robust exception management
- **Backup Support**: Database backup and restore capabilities

## 🛠 Technology Stack

### Core Technologies
- **Programming Language**: Java 20
- **GUI Framework**: Java Swing
- **Database**: MySQL 8.0+
- **Build Tool**: Apache Ant
- **IDE**: NetBeans 21

### Libraries & Dependencies
- **Lombok**: Annotation-based code generation
- **MySQL Connector**: Database connectivity
- **Java AWT/Swing**: User interface components

### Development Tools
- **Version Control**: Git
- **Database Design**: MySQL Workbench
- **Project Management**: NetBeans IDE

## 🏗 System Architecture

### Layered Architecture Overview

```mermaid
graph TB
    subgraph "Presentation Layer"
        A[GUI Components<br/>Swing Panels & Frames]
        B[Login Interface]
        C[Dashboard]
        D[Invoice Panel]
        E[Product Management]
    end
    
    subgraph "Service Layer"
        F[Business Logic]
        G[Validation Services]
        H[ProductService]
        I[InvoiceService]
        J[UserService]
    end
    
    subgraph "Data Access Layer"
        K[DAO Objects]
        L[CategoryDAO]
        M[InvoiceDAO]
        N[UserDAO]
        O[ProductDAO]
    end
    
    subgraph "Data Layer"
        P[DTO Objects]
        Q[Employee]
        R[Product]
        S[Invoice]
        T[Stock]
    end
    
    subgraph "Database Layer"
        U[(MySQL Database<br/>HelaPos)]
    end
    
    A --> F
    B --> F
    C --> F
    D --> I
    E --> H
    
    F --> K
    G --> K
    H --> K
    I --> M
    J --> N
    
    K --> P
    L --> P
    M --> P
    N --> P
    O --> P
    
    P --> U
    Q --> U
    R --> U
    S --> U
    T --> U
    
    style A fill:#e1f5ff
    style F fill:#fff4e1
    style K fill:#ffe1e1
    style P fill:#f0e1ff
    style U fill:#e1ffe1
```

### Architecture Flow

```mermaid
sequenceDiagram
    participant User
    participant GUI as GUI Layer
    participant Service as Service Layer
    participant DAO as DAO Layer
    participant DTO as DTO Layer
    participant DB as Database
    
    User->>GUI: Interaction
    GUI->>Service: Request with Data
    Service->>Service: Validate & Process
    Service->>DAO: Data Operation Request
    DAO->>DTO: Create/Map Objects
    DTO->>DB: Execute Query
    DB-->>DTO: Return Results
    DTO-->>DAO: Mapped Objects
    DAO-->>Service: Data Objects
    Service-->>GUI: Processed Response
    GUI-->>User: Display Result
```

### Design Patterns Used
- **MVC (Model-View-Controller)**: Separation of concerns
- **DAO Pattern**: Data access abstraction
- **DTO Pattern**: Data transfer between layers
- **Singleton Pattern**: Database connection management
- **Observer Pattern**: GUI component updates

### System Workflows

#### Sales Process Flow

```mermaid
flowchart TD
    Start([Start Sales Process]) --> Login[Cashier Login]
    Login --> Dashboard[Access Dashboard]
    Dashboard --> SelectCustomer{Select/Add<br/>Customer}
    SelectCustomer -->|New| AddCustomer[Add New Customer]
    SelectCustomer -->|Existing| LoadCustomer[Load Customer Info]
    AddCustomer --> ScanProducts
    LoadCustomer --> ScanProducts[Scan/Add Products]
    
    ScanProducts --> CheckStock{Stock<br/>Available?}
    CheckStock -->|No| StockAlert[Low Stock Alert]
    StockAlert --> ScanProducts
    CheckStock -->|Yes| AddToCart[Add to Cart]
    
    AddToCart --> MoreItems{Add More<br/>Items?}
    MoreItems -->|Yes| ScanProducts
    MoreItems -->|No| ApplyDiscount[Apply Discounts]
    
    ApplyDiscount --> Calculate[Calculate Total]
    Calculate --> Payment[Process Payment]
    Payment --> PrintInvoice[Generate & Print Invoice]
    PrintInvoice --> UpdateStock[Update Stock Levels]
    UpdateStock --> End([Complete Transaction])
    
    style Login fill:#e1f5ff
    style ScanProducts fill:#fff4e1
    style Payment fill:#ffe1e1
    style UpdateStock fill:#e1ffe1
```

#### Inventory Management Flow

```mermaid
flowchart TD
    Start([Start Inventory Process]) --> CreatePO[Create Purchase Order]
    CreatePO --> SelectSupplier[Select Supplier]
    SelectSupplier --> SelectProduct[Select Product]
    SelectProduct --> EnterQty[Enter Quantity & Price]
    EnterQty --> SubmitPO[Submit Purchase Order]
    
    SubmitPO --> ReceiveGoods{Goods<br/>Received?}
    ReceiveGoods -->|No| Wait[Wait for Delivery]
    Wait --> ReceiveGoods
    ReceiveGoods -->|Yes| CreateGRN[Create GRN]
    
    CreateGRN --> InspectGoods[Inspect Goods]
    InspectGoods --> EnterDetails[Enter Stock Details]
    EnterDetails --> AddStock[Add to Stock]
    AddStock --> UpdateInventory[Update Inventory Levels]
    UpdateInventory --> End([Complete Process])
    
    style CreatePO fill:#e1f5ff
    style CreateGRN fill:#fff4e1
    style AddStock fill:#e1ffe1
```

#### User Access Control

```mermaid
flowchart LR
    subgraph Admin[Administrator Access]
        A1[User Management]
        A2[System Configuration]
        A3[Financial Reports]
        A4[All Operations]
    end
    
    subgraph Supervisor[Supervisor Access]
        S1[Inventory Management]
        S2[Sales Monitoring]
        S3[Customer Management]
        S4[Reports]
    end
    
    subgraph Cashier[Cashier Access]
        C1[POS Operations]
        C2[Invoice Generation]
        C3[Customer Service]
        C4[Basic Reports]
    end
    
    Login([User Login]) --> CheckRole{User Role?}
    CheckRole -->|Admin| Admin
    CheckRole -->|Supervisor| Supervisor
    CheckRole -->|Cashier| Cashier
    
    style Admin fill:#ff6b6b
    style Supervisor fill:#ffd93d
    style Cashier fill:#6bcf7f
```

## 🚀 Installation & Setup

### Prerequisites

#### Java Development Kit (JDK) 20
Download and install JDK 20 from the following link:
- [JDK 20 Windows x64](https://download.java.net/openjdk/jdk20/ri/openjdk-20+36_windows-x64_bin.zip)

**Installation Steps:**
1. Download the JDK 20 package
2. Extract to your preferred directory (e.g., `C:\Program Files\Java\jdk-20`)
3. Set `JAVA_HOME` environment variable
4. Add `%JAVA_HOME%\bin` to your PATH

#### NetBeans 21 IDE
Download and install NetBeans 21 IDE from the following link:
- [NetBeans 21 Windows x64](https://dlcdn.apache.org/netbeans/netbeans-installers/21/Apache-NetBeans-21-bin-windows-x64.exe)

**Installation Steps:**
1. Download the NetBeans installer
2. Run the installer as administrator
3. Follow the installation wizard
4. Ensure JDK 20 is detected during installation

#### MySQL Server
- **Version**: MySQL 8.0 or higher
- **Download**: [MySQL Community Server](https://dev.mysql.com/downloads/mysql/)

### Project Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/isharax9/HelaPos.git
   cd HelaPos
   ```

2. **Open in NetBeans**
   - Launch NetBeans 21
   - File → Open Project
   - Navigate to the cloned directory
   - Select the project folder

3. **Configure Database Connection**
   - Copy `src/utils/Database.sample` to `src/utils/Database.java`
   - Update database credentials in the file
   - Ensure MySQL server is running

4. **Build the Project**
   - Right-click on project in NetBeans
   - Select "Clean and Build"
   - Resolve any dependency issues

## 🗄 Database Setup

### Database Schema
The project includes a complete database schema with the following main entities:

- **Users & Authentication**: Employee management and login
- **Product Management**: Categories, subcategories, products, units
- **Inventory**: Stock management and tracking
- **Sales**: Invoices, invoice items, customers
- **Purchasing**: Suppliers, purchase orders, GRN
- **Financial**: Payments, expenses, salaries

### Setup Instructions

1. **Create Database**
   ```sql
   CREATE DATABASE helapos;
   USE helapos;
   ```

2. **Import Schema**
   - Use the provided ER diagram (`ER/er.mwb`) in MySQL Workbench
   - Execute the generated SQL script
   - Or import from provided SQL dump file

3. **Initial Data Setup**
   - Create default admin user
   - Set up basic categories and units
   - Configure initial system settings

### ER Diagram

The complete Entity-Relationship diagram is available in the `ER/` directory:
- **er.mwb**: MySQL Workbench file
- **er.svg**: Visual diagram

#### Database Schema Visualization

```mermaid
erDiagram
    User ||--o{ Employee : "has profile"
    User ||--|| BankDetails : "has"
    Employee ||--o{ Invoice : "creates"
    Employee ||--o{ Salleries : "receives"
    
    Category ||--o{ SubCategory : "contains"
    SubCategory ||--o{ Product : "has"
    Product ||--|| Unit : "measured in"
    Product ||--o{ Stock : "has inventory"
    Product ||--o{ PurchaseOrder : "ordered as"
    
    Supplier ||--|| BankDetails : "has"
    Supplier ||--o{ PurchaseOrder : "supplies"
    
    PurchaseOrder ||--o{ GRN : "receives via"
    GRN ||--o{ Stock : "adds to"
    
    Invoice ||--|| Customer : "billed to"
    Invoice ||--|| PaymentTypes : "paid via"
    Invoice ||--o{ InvoiceItem : "contains"
    InvoiceItem ||--|| Stock : "references"
    
    OtherExpences ||--|| Employee : "recorded by"
    
    User {
        int user_id PK
        string first_name
        string last_name
        string username
        string user_email
        string user_password
        string address
        string user_type
        int bank_details_id FK
    }
    
    Employee {
        int user_id PK
        string user_email
        string user_type
        string first_name
        string last_name
        string address
        int bank_details_id FK
    }
    
    BankDetails {
        int bank_details_id PK
        string bank_name
        string branch
        string account_number
        string account_holder_name
    }
    
    Category {
        int cat_id PK
        string category
    }
    
    SubCategory {
        int sub_cat_id PK
        string sub_category
        int cat_id FK
    }
    
    Product {
        int product_id PK
        string product_name
        string product_printing_name
        int sub_cat_id FK
        int unit_id FK
        double stock_refilling_qty
    }
    
    Unit {
        int unit_id PK
        string unit_name
    }
    
    Stock {
        int stock_barcode PK
        date mnf_date
        date exp_date
        double unit_price
        double available_qty
        double discount
        int grn_id FK
    }
    
    Supplier {
        int supplier_id PK
        string supplier_first_name
        string supplier_last_name
        string supplier_contact
        string supplier_address
        int bank_details_id FK
    }
    
    PurchaseOrder {
        int po_id PK
        date ordered_date
        double order_qty
        int product_id FK
        double wholesale_unit_price
        double paid_amount
        int supplier_id FK
        string po_status
    }
    
    GRN {
        int grn_id PK
        timestamp grn_date
        int po_id FK
    }
    
    Invoice {
        int invoice_id PK
        int employee_id FK
        int customer_id FK
        double total
        double total_discount
        double grand_total
        double paid_amount
        double balance
        datetime invoice_date
        int payment_type_id FK
    }
    
    Customer {
        int customer_id PK
        string customer_name
        string customer_address
        string customer_contact
        int point
    }
    
    InvoiceItem {
        int invoice_item_id PK
        int invoice_id FK
        int stock_barcode FK
        double qty
        double discount
    }
    
    PaymentTypes {
        int payment_type_id PK
        string payment_type
    }
    
    Salleries {
        int sallery_id PK
        int employee_id FK
        double amount
        date paid_date
        string description
    }
    
    OtherExpences {
        int expense_id PK
        string description
        double amount
        date expense_date
        int employee_id FK
    }
```

### Key Database Relationships

- **Users & Employees**: One-to-one relationship with extended employee profile
- **Product Hierarchy**: Categories → SubCategories → Products
- **Inventory Flow**: Purchase Orders → GRN → Stock
- **Sales Flow**: Stock → Invoice Items → Invoices
- **Financial Tracking**: Employees → Salaries & Expenses

## 📖 Usage Guide

### First Time Setup

1. **Start the Application**
   - Run the project from NetBeans
   - The login screen will appear

2. **Initial Login**
   - Use default admin credentials
   - Access: Admin panel

3. **System Configuration**
   - Set up product categories
   - Add initial products
   - Configure suppliers
   - Create user accounts

### Daily Operations

#### For Cashiers
- Process sales transactions
- Handle customer inquiries
- Generate invoices
- Process payments

#### For Supervisors
- Monitor daily sales
- Manage inventory levels
- Handle special transactions
- Generate basic reports

#### For Administrators
- Complete system access
- User management
- Financial reporting
- System configuration

## 📁 Project Structure

```
helaPOS/
├── src/
│   ├── assets/           # Images and icons
│   ├── components/       # Custom Swing components
│   ├── dao/             # Data Access Objects
│   ├── dto/             # Data Transfer Objects
│   ├── gui/             # User Interface forms
│   ├── reports/         # Report generation
│   ├── services/        # Business logic layer
│   └── utils/           # Utility classes
├── ER/                  # Database design files
├── nbproject.sample/    # NetBeans project template
├── build.xml           # Ant build configuration
├── manifest.mf         # JAR manifest
└── README.md           # This file
```

### Key Directories

#### `/src/dto/` - Data Transfer Objects
- `Employee.java` - Employee entity
- `Product.java` - Product information
- `Invoice.java` - Sales invoice data
- `Stock.java` - Inventory management
- `Customer.java` - Customer information

#### `/src/dao/` - Data Access Layer
- `CategoryDAO.java` - Product category operations
- `InvoiceDAO.java` - Invoice database operations
- `UserDAO.java` - User management operations

#### `/src/services/` - Business Logic
- `ProductService.java` - Product management
- `InvoiceService.java` - Sales processing
- `UserService.java` - User operations

#### `/src/gui/` - User Interface
- `Login.java` - Authentication interface
- `DashboardFrame.java` - Main application window
- `InvoicePanel.java` - Sales interface
- `ProductsPanel.java` - Product management

## 👤 User Roles & Permissions

### 🔑 Administrator
**Full system access including:**
- User management (create, modify, delete users)
- System configuration
- Financial reporting
- Database backup/restore
- All operational features

### 👨‍💼 Supervisor
**Management level access:**
- Inventory management
- Sales monitoring
- Customer management
- Reporting (except financial)
- User creation (limited)

### 💰 Cashier
**Operational access:**
- Point of sale operations
- Invoice generation
- Customer interactions
- Basic inventory viewing
- Sales reporting (own transactions)

## 📚 Documentation

### External Documentation
Read the complete project documents for detailed specifications:

#### Confluence Documentation
- [Project Document](https://helasoft.atlassian.net/wiki/external/YjNjNjQzZGUwNDc2NDBiOWJjZDlkNzk2ZjJlNDViYTM)
- [Software Requirement Specification](https://helasoft.atlassian.net/wiki/external/ZDdmYTRkNGI1MmU4NGNhNGFkZDc5YmZjNGRkMzc1MDk)

#### Google Docs
- [Project Document](https://docs.google.com/document/d/1qeWOP-9nX3KPb2UCv_iST1AWtuYW_ZdXJeLp50zBk8Y/edit?usp=sharing)
- [Software Requirement Specification](https://docs.google.com/document/d/1T_ikoU8-Z6x6rpHjyB0JCqiFAko5Y1cJ0-8bnFG4e5o/edit)

#### Project Presentation
- [Project Presentation](https://www.canva.com/design/DAGGY9mt1TY/5zMGxbLnhhpzgx6d20J-dw/edit?utm_content=DAGGY9mt1TY&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

### API Documentation
- JavaDoc documentation available in `/docs/` (after build)
- Service layer documentation
- Database schema documentation

## 🤝 Contributing

We welcome contributions to helaPOS! Please follow these guidelines:

### Development Workflow

1. **Fork the Repository**
   ```bash
   git fork https://github.com/isharax9/HelaPos.git
   ```

2. **Create Feature Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

3. **Make Changes**
   - Follow Java coding standards
   - Add appropriate comments
   - Update documentation if needed

4. **Test Your Changes**
   - Ensure all existing functionality works
   - Test new features thoroughly
   - Check database operations

5. **Submit Pull Request**
   - Provide clear description of changes
   - Include screenshots for UI changes
   - Reference any related issues

### Coding Standards

- **Java Style**: Follow Oracle Java coding conventions
- **Comments**: Use JavaDoc for public methods
- **Naming**: Use descriptive variable and method names
- **Validation**: Always validate user inputs
- **Error Handling**: Implement proper exception handling

### Bug Reports

When reporting bugs, please include:
- **Environment**: OS, Java version, MySQL version
- **Steps to Reproduce**: Detailed reproduction steps
- **Expected Behavior**: What should happen
- **Actual Behavior**: What actually happens
- **Screenshots**: If applicable

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

The MIT License allows you to:
- ✅ Use the software commercially
- ✅ Modify the software
- ✅ Distribute the software
- ✅ Place warranty

## 📞 Contact

### Project Maintainer
- **Email**: [isharax9@gmail.com](mailto:isharax9@gmail.com)
- **GitHub**: [@isharax9](https://github.com/isharax9)

### Support
For technical support or questions:
- **Create an Issue**: [GitHub Issues](https://github.com/isharax9/HelaPos/issues)
- **Email Support**: [isharax9@gmail.com](mailto:isharax9@gmail.com)

### Project Links
- **Repository**: [GitHub](https://github.com/isharax9/HelaPos)
- **Documentation**: [Project Docs](https://helasoft.atlassian.net/wiki/external/YjNjNjQzZGUwNDc2NDBiOWJjZDlkNzk2ZjJlNDViYTM)
- **Presentations**: [Canva](https://www.canva.com/design/DAGGY9mt1TY/5zMGxbLnhhpzgx6d20J-dw/edit)

---

<div align="center">

**⭐ Star this repository if you find it helpful!**

Made with ❤️ by the helaPOS Team

*Please ensure you have installed the required software and read through the documentation before proceeding with the project setup.*

</div>
