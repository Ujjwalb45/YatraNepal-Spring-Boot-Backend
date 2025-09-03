# YatraNepal Spring Boot Startup Script
Write-Host "Starting YatraNepal Spring Boot API..." -ForegroundColor Green

# Check Java installation
try {
    $javaVersion = java -version 2>&1
    Write-Host "Java found: $javaVersion" -ForegroundColor Yellow
} catch {
    Write-Host "Java not found. Please install Java 17+" -ForegroundColor Red
    exit 1
}

# Check Maven installation
try {
    $mavenVersion = mvn -version 2>&1
    Write-Host "Maven found: $mavenVersion" -ForegroundColor Yellow
} catch {
    Write-Host "Maven not found. Trying alternative methods..." -ForegroundColor Yellow
}

# Set environment variables
$env:MONGO_URI = "mongodb://localhost:27017/yatranepal"
$env:JWT_SECRET = "mySecretKey123456789"

Write-Host "Environment configured" -ForegroundColor Green

# Try to run with Maven
Write-Host "Attempting to start with Maven..." -ForegroundColor Cyan
try {
    mvn spring-boot:run
} catch {
    Write-Host "Maven failed. Trying direct Java execution..." -ForegroundColor Yellow
    
    # Compile if needed
    if (!(Test-Path "target/classes")) {
        Write-Host "Compiling project..." -ForegroundColor Cyan
        mvn compile
    }
    
    # Run directly
    java -cp "target/classes" com.yatranepal.api.YatraNepalApiApplication
}
