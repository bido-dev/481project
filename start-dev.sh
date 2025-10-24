#!/bin/bash

# Load environment variables
set -a
source .env
set +a

echo "========================================"
echo "Starting Murshid Full Stack Application"
echo "========================================"

# Start Spring Boot Backend
echo ""
echo "Starting Spring Boot Backend on port 8080..."
./mvnw spring-boot:run > backend.log 2>&1 &
BACKEND_PID=$!
echo "Backend PID: $BACKEND_PID"

# Wait a bit for backend to start
sleep 5

# Start React Frontend
echo ""
echo "Starting React Frontend on port 5173..."
cd frontend
npm install
npm run dev &
FRONTEND_PID=$!
echo "Frontend PID: $FRONTEND_PID"

echo ""
echo "========================================"
echo "✓ Backend API: http://localhost:8080"
echo "✓ Frontend App: http://localhost:5173"
echo "========================================"
echo ""
echo "Press Ctrl+C to stop both servers"

# Wait for user interrupt
trap "echo 'Stopping servers...'; kill $BACKEND_PID $FRONTEND_PID 2>/dev/null; exit" INT TERM

# Keep script running
wait
