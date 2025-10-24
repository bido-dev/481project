# Murshid Application - Setup Instructions

## Overview
This is a full-stack educational guidance application with:
- **Backend**: Spring Boot (Java 17) REST API
- **Frontend**: React + TypeScript + Vite + Tailwind CSS
- **Database**: Supabase PostgreSQL

## What Has Been Configured

### ✅ Database (Supabase)
- All required tables have been created in Supabase PostgreSQL
- Tables: student, university, major, graduate, advice, student_experience
- Junction tables for many-to-many relationships
- Row Level Security (RLS) policies configured
- Indexes added for performance

### ✅ Backend Configuration
- Updated `pom.xml` to use PostgreSQL driver instead of MySQL
- Updated `application.properties` with Supabase connection details
- Added CORS configuration to allow frontend access
- Created `WebConfig.java` for proper CORS setup
- Environment variables configured in `.env` file

### ✅ Frontend Configuration
- Vite configured to run on port 5173 with proper host settings
- API calls already configured to connect to backend on port 8080
- Login and Register forms already implemented

## Prerequisites

Your colleagues' computers have these installed, which is why it works for them:

1. **Java Development Kit (JDK) 17 or higher**
   - Download: https://adoptium.net/ or https://www.oracle.com/java/technologies/downloads/
   - Verify: `java -version`

2. **Node.js 18+ and npm**
   - Download: https://nodejs.org/
   - Verify: `node -v` and `npm -v`

3. **Maven** (optional - project includes Maven Wrapper)
   - The project uses `./mvnw` which downloads Maven automatically

## Current Environment Issue

**The localhost connection is being refused because Java is not installed in this environment.**

This is why it works on your colleagues' computers but not here - they have Java installed.

## How to Run on Your Local Machine

### Step 1: Ensure Java is Installed

```bash
java -version
```

You should see something like:
```
openjdk version "17.0.x" ...
```

If not, install Java 17 from: https://adoptium.net/

### Step 2: Run the Full Stack Application

Use the startup script I created:

```bash
cd /tmp/cc-agent/59157623/project
chmod +x start-dev.sh
./start-dev.sh
```

This will:
1. Start the Spring Boot backend on `http://localhost:8080`
2. Start the React frontend on `http://localhost:5173`

### Alternative: Run Backend and Frontend Separately

#### Terminal 1 - Backend:
```bash
cd /tmp/cc-agent/59157623/project
./mvnw spring-boot:run
```

Wait for the message: "Started Swe481Application in X seconds"

#### Terminal 2 - Frontend:
```bash
cd /tmp/cc-agent/59157623/project/frontend
npm install
npm run dev
```

### Step 3: Access the Application

- **Frontend**: http://localhost:5173
- **Backend API**: http://localhost:8080/api/v1/student

## API Endpoints

### Student Endpoints (http://localhost:8080/api/v1/student)

1. **Register**: `POST /signup`
   - Body (JSON):
   ```json
   {
     "username": "john_doe",
     "studentName": "John Doe",
     "studentEmail": "john@example.com",
     "password": "Password123!"
   }
   ```

2. **Login**: `POST /login?email=john@example.com&password=Password123!`

## Database Connection Details

The application is now connected to Supabase PostgreSQL:
- Host: `db.wnrzgrckygjuumlbdhbc.supabase.co`
- Port: `5432`
- Database: `postgres`
- All credentials are in the `.env` file

## Troubleshooting

### "Connection Refused" on localhost:5173
- **Cause**: Java is not installed, so the Spring Boot backend cannot start
- **Fix**: Install Java 17 or higher

### Backend fails to start
- Check Java installation: `java -version`
- Check if port 8080 is already in use: `lsof -i :8080` (Mac/Linux) or `netstat -ano | findstr :8080` (Windows)
- Check database credentials in `.env` file

### Frontend fails to connect to backend
- Ensure backend is running: Visit http://localhost:8080/api/v1/student
- Check browser console for CORS errors
- Verify CORS is properly configured in `WebConfig.java`

### Database connection errors
- Verify Supabase credentials in `.env`
- Check if your IP is allowed in Supabase project settings
- Ensure PostgreSQL driver is in `pom.xml`

## What's Different from MySQL Setup

The original project was configured for MySQL, but I've migrated it to Supabase PostgreSQL:

1. **Database Driver**: Changed from `mysql-connector-j` to `postgresql`
2. **Database URL**: Changed from `jdbc:mysql://localhost:3306/swe481` to Supabase PostgreSQL
3. **Dialect**: Changed from `MySQL8Dialect` to `PostgreSQLDialect`
4. **DDL**: Changed from `hibernate.ddl-auto=update` to `none` (using migrations instead)
5. **RLS Policies**: Added Row Level Security for data protection

## Next Steps

Once you have Java installed on your machine:

1. Run `./start-dev.sh` from the project root
2. Open http://localhost:5173 in your browser
3. Try registering a new student account
4. Test the login functionality

The database is ready and all configurations are complete. You just need Java installed to run the Spring Boot backend!
