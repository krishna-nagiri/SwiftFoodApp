#!/bin/bash

echo "⏳ Starting SwiftFood Build & Run Script"

# Move into src directory to compile
cd src || { echo "❌ Failed to enter src directory"; read -p "Press Enter to exit..."; exit 1; }

# Adjust classpath (NOTE: ':' is correct for Git Bash. Use ';' for Windows CMD.)
CP="../lib/gson-2.10.1.jar:."

echo "📦 Compiling..."
javac -encoding UTF-8 -cp "$CP" com/infy/*.java com/infy/**/*.java -d ../bin


if [ $? -ne 0 ]; then
  echo "❌ Compilation Failed."
  read -p "Press Enter to exit..."
  exit 1
fi

echo "✅ Compilation Successful"

# Now move back to root (where bin/ and data/ are located)
cd .. || { echo "❌ Failed to move back"; read -p "Press Enter to exit..."; exit 1; }

echo "🚀 Launching App..."

# Launch from root, using bin as classpath root(replace ";" with ":" if using linux/max OS )
java -cp ".\lib\gson-2.10.1.jar;.\bin" com.infy.App



echo "🏁 Program finished."
read -p "Press Enter to exit..."
