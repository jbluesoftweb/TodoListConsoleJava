# Crear directorio de salida si no existe
if (-not (Test-Path out)) {
    New-Item -ItemType Directory -Path out | Out-Null
}

# Limpiar archivos compilados anteriores
Write-Host "Limpiando archivos compilados anteriores..."
Remove-Item -Path out/* -Force -Recurse -ErrorAction SilentlyContinue

# Recopilar todos los archivos .java
Write-Host "Compilando código Java..."
$files = Get-ChildItem -Path code -Recurse -Filter *.java | ForEach-Object { $_.FullName }

# Compilar con javac y colocar las clases en out/
$filelist = $files -join " "
& javac -d out $filelist 2>&1

# Verificar si la compilación fue exitosa
if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Compilación exitosa" -ForegroundColor Green
    Write-Host ""
    Write-Host "Ejecutando programa..."
    Write-Host "================================"
    & java -cp out app.Main
}
else {
    Write-Host "✗ Error durante la compilación" -ForegroundColor Red
    exit 1
}
