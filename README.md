# 🏦 MyBank Application

A full-stack banking application built with React.js frontend and Spring Boot backend, featuring secure authentication, account management, and transaction processing.

## 🚀 Features

### Frontend (React.js)
- **User Authentication**: Signup and login with JWT
- **Dashboard**: Account overview with balance and recent transactions
- **Account Management**: Profile updates and account details
- **Transaction Management**: Deposit, withdraw, and transfer money
- **Responsive Design**: Modern UI with Tailwind CSS
- **Protected Routes**: Secure navigation with authentication

### Backend (Spring Boot)
- **RESTful APIs**: Complete banking operations
- **JWT Authentication**: Secure token-based authentication
- **Spring Security**: Role-based access control
- **JPA/Hibernate**: Database operations with H2 in-memory database
- **Exception Handling**: Comprehensive error management
- **CORS Configuration**: Cross-origin resource sharing

## 🛠️ Tech Stack

### Frontend
- **React.js** - UI framework
- **React Router** - Navigation
- **Axios** - HTTP client
- **Tailwind CSS** - Styling
- **JWT** - Authentication state management

### Backend
- **Spring Boot** - Application framework
- **Spring Security** - Authentication & authorization
- **Spring Data JPA** - Database operations
- **H2 Database** - In-memory database
- **Maven** - Build tool
- **JWT** - Token generation and validation

## 📁 Project Structure

```
my-bank-app/
├── frontend/                 # React.js application
│   ├── public/
│   ├── src/
│   │   ├── components/      # Reusable components
│   │   ├── pages/          # Page components
│   │   ├── context/        # React context
│   │   ├── api/           # API configuration
│   │   └── assets/        # Static assets
│   ├── package.json
│   └── tailwind.config.js
├── backend/                 # Spring Boot application
│   ├── src/main/java/
│   │   └── com/mybank/bank/
│   │       ├── model/      # JPA entities
│   │       ├── repo/       # Data repositories
│   │       ├── service/    # Business logic
│   │       ├── web/        # REST controllers
│   │       ├── security/   # Security configuration
│   │       └── exception/  # Exception handling
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
└── README.md
```

## 🚀 Getting Started

### Prerequisites
- **Node.js** (v14 or higher)
- **Java** (v17 or higher)
- **Maven** (v3.6 or higher)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/my-bank-app.git
   cd my-bank-app
   ```

2. **Start the Backend**
   ```bash
   cd backend
   mvn spring-boot:run
   ```
   The backend will start on `http://localhost:8080`

3. **Start the Frontend**
   ```bash
   cd frontend
   npm install
   npm start
   ```
   The frontend will start on `http://localhost:3000`

### Quick Start Scripts

#### Windows
```bash
# Start backend
cd backend && mvn spring-boot:run

# Start frontend (in new terminal)
cd frontend && npm start
```

#### Linux/Mac
```bash
# Start backend
cd backend && mvn spring-boot:run &

# Start frontend
cd frontend && npm start
```

## 📖 API Documentation

### Authentication Endpoints
- `POST /api/auth/signup` - User registration
- `POST /api/auth/login` - User login

### Account Endpoints
- `GET /api/accounts` - Get user accounts
- `GET /api/accounts/{id}` - Get specific account
- `POST /api/accounts` - Create new account

### Transaction Endpoints
- `GET /api/transactions` - Get transaction history
- `POST /api/transactions/deposit` - Deposit money
- `POST /api/transactions/withdraw` - Withdraw money
- `POST /api/transactions/transfer` - Transfer money

### Health Check
- `GET /actuator/health` - Application health status

## 🔐 Security Features

- **JWT Authentication**: Secure token-based authentication
- **Password Encryption**: BCrypt password hashing
- **CORS Configuration**: Secure cross-origin requests
- **Protected Routes**: Role-based access control
- **Input Validation**: Comprehensive data validation

## 🎨 UI Features

- **Modern Design**: Clean and professional banking interface
- **Responsive Layout**: Works on desktop, tablet, and mobile
- **User-Friendly**: Intuitive navigation and forms
- **Real-time Updates**: Dynamic content updates
- **Error Handling**: User-friendly error messages

## 🗄️ Database Schema

### Users Table
- `id` - Primary key
- `name` - User's full name
- `email` - Unique email address
- `password_hash` - Encrypted password
- `phone` - Phone number

### Accounts Table
- `id` - Primary key
- `account_number` - Unique account number
- `balance` - Current balance
- `user_id` - Foreign key to users

### Transactions Table
- `id` - Primary key
- `amount` - Transaction amount
- `transaction_type` - DEPOSIT, WITHDRAW, TRANSFER_IN, TRANSFER_OUT
- `description` - Transaction description
- `account_id` - Foreign key to accounts
- `created_at` - Transaction timestamp

## 🧪 Testing

### Backend Testing
```bash
cd backend
mvn test
```

### Frontend Testing
```bash
cd frontend
npm test
```

## 📦 Deployment

### Backend Deployment
```bash
cd backend
mvn clean package
java -jar target/bank-api-1.0.0.jar
```

### Frontend Deployment
```bash
cd frontend
npm run build
# Deploy the build folder to your web server
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)
- Email: your.email@example.com

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- React team for the amazing UI library
- Tailwind CSS for the utility-first CSS framework
- All contributors and supporters

---

⭐ **Star this repository if you found it helpful!**
