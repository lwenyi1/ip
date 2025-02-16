@ECHO OFF

REM Clean previous build
if exist ..\bin rmdir /s /q ..\bin
mkdir ..\bin

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist ACTUAL2.TXT del ACTUAL2.TXT

REM Force delete the "data" directory
if exist data rmdir /s /q data

REM compile the code into the bin folder
javac  -cp ..\src\main\java\chloe -Xlint:none -d ..\bin ..\src\main\java\chloe\*.java ..\src\main\java\chloe\tasktypes\*.java ..\src\main\java\chloe\exceptions\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM test commands
echo Testing basic functionality...
REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
REM NOTE: change ACTUAL to EXPECTED and comment out FC below to update EXPECTED.TXT easily
java -classpath ..\bin chloe.Chloe < input.txt > ACTUAL.TXT
REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

REM test save file loading
echo Testing save file loading...
REM run the program again, check if previous list was stored properly
java -classpath ..\bin chloe.Chloe < input2.txt > ACTUAL2.TXT
REM compare the output to the expected output
FC ACTUAL2.TXT EXPECTED2.TXT
