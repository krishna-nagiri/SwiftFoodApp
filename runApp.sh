#!/bin/bash

echo "⏳ Starting SwiftFood Build & Run Script"

# Move into src directory
cd src || { echo "❌ Failed to enter src directory"; read -p "Press Enter to exit..."; exit 1; }

# Classpath (Linux/Mac use ':', Windows Git Bash will also work with ':')
CP="../lib/gson-2.10.1.jar:."

echo "📦 Compiling..."
javac -encoding UTF-8 -cp "$CP" main/com/infy/*.java main/com/infy/**/*.java -d ../bin

if [ $? -ne 0 ]; then
  echo "❌ Compilation Failed."
  read -p "Press Enter to exit..."
  exit 1
fi

echo "✅ Compilation Successful"

# Back to project root
cd .. || { echo "❌ Failed to move back"; read -p "Press Enter to exit..."; exit 1; }

echo "🚀 Launching App..."

# Launch the main class
java -cp "./lib/gson-2.10.1.jar;./bin" main.com.infy.App

echo "🏁 Program finished."
read -p "Press Enter to exit..."
