# PetCareMAX

PetCareMAX is a Java Swing veterinary and pet-care management system using MySQL and JasperReports.

## Architecture

The project uses a layered MVC-oriented architecture designed for the coursework rubric.

```text
View
  -> Form Controller
  -> Domain Controller
  -> Service
  -> DAO
  -> MySQL
```

### View
Swing forms and NetBeans GUI Builder forms are responsible for the user interface, input fields, tables and displaying feedback. The `.form` files are retained so the forms can be opened and edited in NetBeans Design View.

### Form Controller
Form Controller classes receive button events through `ActionListener`. They coordinate the requested user operation instead of placing CRUD button listeners directly in the View.

### Model
Model classes represent application data such as Customer, Pet, Veterinarian, Appointment, Treatment and Payment.

### Domain Controller
Domain controllers provide the application operations to the UI layer and pass model data to the Service layer.

### Service
Services contain validation and business rules and keep them separate from Swing UI code.

### DAO
DAO interfaces and implementations perform database CRUD operations. `DAOFactory` creates the DAO implementations.

## Main coursework evidence

- Java Swing enterprise application
- OOP and layered MVC architecture
- DAO, Service and Factory patterns
- Customer, Pet, Veterinarian, Service, Medication, Appointment, Treatment, Treatment Medication and Payment modules
- CRUD and search operations
- Dashboard with database-driven statistics and recent appointments
- Login, password hashing and role-based access
- Validation and user-defined `CustomerValidationException`
- ArrayList and collection processing
- Lambda expressions
- SwingWorker for background dashboard loading
- JasperReports appointment report using multiple related tables
- NetBeans GUI Builder `.form` files
- Maven JAR packaging

## Add User flow

`AddUserForm -> AddUserController -> AddUserService -> AddUserDAO -> AddUserDAOImpl -> users table`

Passwords are hashed with the PBKDF2 utility before being stored.

## Design View

The main forms use a consistent PetCareMAX visual system: light background, navy headings, gold primary actions, compact spacing, consistent field sizes and styled tables. The `.form` files are preserved for GUI Builder editing.

## Database

The application expects a local MySQL database named `petcare_db`. Update `DBConnection` only if the local database settings are different.





