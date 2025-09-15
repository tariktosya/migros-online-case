@echo off
echo -------------------------------
echo Courier Service Full Test
echo -------------------------------

:: -------------------------------
echo Posting courier locations...

:: basic entry and reentry
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"301\",\"lat\":40.992330,\"lng\":29.124423,\"timestamp\":1695658600}" http://localhost:8080/courier/location
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"301\",\"lat\":40.986106,\"lng\":29.116129,\"timestamp\":1695658660}" http://localhost:8080/courier/location
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"301\",\"lat\":40.992330,\"lng\":29.124423,\"timestamp\":1695658680}" http://localhost:8080/courier/location
echo.

:: Multi-courier and total distance
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"401\",\"lat\":40.992330,\"lng\":29.124423,\"timestamp\":1695658600}" http://localhost:8080/courier/location
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"401\",\"lat\":40.986106,\"lng\":29.116129,\"timestamp\":1695658660}" http://localhost:8080/courier/location
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"402\",\"lat\":41.006685,\"lng\":28.655226,\"timestamp\":1695658600}" http://localhost:8080/courier/location
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"402\",\"lat\":41.055783,\"lng\":29.021029,\"timestamp\":1695658800}" http://localhost:8080/courier/location
echo.

::Reentry ve 1 min filter
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"501\",\"lat\":40.992330,\"lng\":29.124423,\"timestamp\":1695658600}" http://localhost:8080/courier/location
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"501\",\"lat\":40.992330,\"lng\":29.124423,\"timestamp\":1695658650}" http://localhost:8080/courier/location
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"501\",\"lat\":40.992330,\"lng\":29.124423,\"timestamp\":1695658700}" http://localhost:8080/courier/location
echo.

:: Total distance
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"601\",\"lat\":40.992330,\"lng\":29.124423,\"timestamp\":1695658600}" http://localhost:8080/courier/location
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"601\",\"lat\":40.986106,\"lng\":29.116129,\"timestamp\":1695658660}" http://localhost:8080/courier/location
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"601\",\"lat\":41.006685,\"lng\":28.655226,\"timestamp\":1695658720}" http://localhost:8080/courier/location
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"601\",\"lat\":41.055783,\"lng\":29.021029,\"timestamp\":1695658780}" http://localhost:8080/courier/location
curl -s -X POST -H "Content-Type: application/json" ^
-d "{\"courierId\":\"601\",\"lat\":40.963246,\"lng\":29.063091,\"timestamp\":1695658840}" http://localhost:8080/courier/location
echo.

:: --------------------------
echo total distances...
curl -s -X GET "http://localhost:8080/courier/distance?courierId=301"
echo.
curl -s -X GET "http://localhost:8080/courier/distance?courierId=401"
echo.
curl -s -X GET "http://localhost:8080/courier/distance?courierId=402"
echo.
curl -s -X GET "http://localhost:8080/courier/distance?courierId=501"
echo.
curl -s -X GET "http://localhost:8080/courier/distance?courierId=601"
echo.


pause
