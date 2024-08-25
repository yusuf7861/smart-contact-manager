# Smart Contact Manager

The **Smart Contact Manager** is a simple web-based application for managing contacts. It allows users to create, view, and manage their personal or business contacts efficiently.

## Features

- User registration and login system.
- Manage contacts with details like name, email, phone number, etc.
- Secure authentication using Spring Security.
- Responsive design using Tailwind CSS.
- User-friendly UI for adding and managing contacts.
- Role-based access control (user and admin).

## Tech Stack

- **Backend**: Java, Spring Boot, Spring Security
- **Frontend**: Thymeleaf, Tailwind CSS
- **Database**: MySQL
- **Version Control**: Git and GitHub

## Installation

1. Clone the repository:

```bash
git clone https://github.com/yusuf7861/smart-contact-manager.git
```

2. Navigate to the project directory:

```bash
cd smart-contact-manager
```

3. Import the project into your preferred IDE (e.g., IntelliJ IDEA, Eclipse).

4. Set up the MySQL database:

```sql
CREATE DATABASE smart_contact_manager;
```

5. Update the `application.properties` with your database credentials.

6. Build the project and run the application:

```bash
mvn clean install
mvn spring-boot:run
```

7. Access the application at `http://localhost:8080`.

## Usage

1. Register as a user on the platform.
2. Log in with your credentials.
3. Add new contacts, view, update, or delete existing contacts.

## Screenshots
![Login Page Screenshot](assets/screenshot1.png)


## Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a Pull Request.
