@ECHO OFF

REM Clean previous build
if exist ..\bin rmdir /s /q ..\bin
mkdir ..\bin

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java\chloe -Xlint:none -d ..\bin ..\src\main\java\chloe\*.java ..\src\main\java\chloe\tasktypes\*.java ..\src\main\java\chloe\exceptions\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin chloe.Chloe < input.txt > ACTUAL.TXT
REM NOTE: change ACTUAL to EXPECTED and comment out FC below to update EXPECTED.TXT easily

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
