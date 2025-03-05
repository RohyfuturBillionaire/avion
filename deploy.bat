set nomAppli=avion
set nomSrcTxt=java_files_list.txt
set web=C:\Users\rohyr\Documents\study\S6\avion\web\views\jsp
set webapps=C:\tomcat\webapps
set lib=C:\Users\rohyr\Documents\study\S6\avion\lib
@REM set src=C:\Users\rohyr\Documents\java\MVC\src\
set temp=C:\Users\rohyr\Documents\study\S6\avion\temp
set webxml=C:\Users\rohyr\Documents\study\S6\avion\webxml
rmdir /s /q %temp%
mkdir %temp%

for /f "delims=" %%i in (%nomSrcTxt%) do set src=%src% %%i

mkdir %temp%\assets
mkdir %temp%\WEB-INF
mkdir %temp%\WEB-INF\views\jsp
mkdir %temp%\WEB-INF\lib
mkdir %temp%\WEB-INF\classes
@REM mkdir %webapps%\%nomAppli%\assets
@REM mkdir %webapps%\%nomAppli%\WEB-INF
@REM mkdir %webapps%\%nomAppli%\WEB-INF\lib
@REM mkdir %webapps%\%nomAppli%\WEB-INF\classes
@REM robocopy %web% %temp%\WEB-INF\views\jsp /E
robocopy %web% %temp%
robocopy %lib% %temp%\WEB-INF\lib /E
robocopy %webxml% %temp%\WEB-INF /E


javac -parameters -cp  "%lib%\*" %src% -d  %temp%\WEB-INF\classes 
cd %temp% && jar cvf %nomAppli%.war * 
copy %nomAppli%.war %webapps%
rm %nomAppli%.war