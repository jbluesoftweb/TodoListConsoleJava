#!/bin/bash

# Crear directorio de salida si no existe
mkdir -p out

# Limpiar archivos compilados anteriores
echo "Limpiando archivos compilados anteriores..."
rm -rf out/*

# Recopilar todos los archivos .java
echo "Compilando código Java..."
find code -name "*.java" > sources.txt

# Compilar con javac y colocar las clases en out/
javac -d out @sources.txt

# Verificar si la compilación fue exitosa
if [ $? -eq 0 ]; then
    echo "✓ Compilación exitosa"
    echo ""
    echo "Ejecutando programa..."
    echo "================================"
    java -cp out app.Main
else
    echo "✗ Error durante la compilación"
    exit 1
fi
