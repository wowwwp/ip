@ECHO OFF
REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM Add path to GSON
SET GSON_JAR=C:\Users\spooj\.m2\repository\com\google\code\gson\gson\2.8.9\gson-2.8.9.jar
REM compile the code into the bin folder
javac  -cp ..\src\main\java;%GSON_JAR% -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin;%GSON_JAR% Ella < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

REM data dir after completion
rmdir /s /q .\data
