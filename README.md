# Migros Online Case

This project is a **restful web application** that tracks courier locations near Migros stores and calculates the total distance traveled.  
The project is written in **Java 21** and does not use any framework or library.  

The project also uses **Singleton** and **Strategy** design patterns, making the application flexible and maintainable.

## 📄 Data and Log Files

The application generates and uses several files to store runtime information:

| File Name | Description |
|-----------|-------------|
| `logs.txt` | Application logs. All info about the application's operation is written here and also displayed in the console. |
| `courier_locations.txt` | Tracks all courier location updates received by the system. |
| `store_logs.txt` | Logs when a courier enters the radius of a store, including store details and timestamps. |

> These files are automatically updated while the application is running, allowing you to monitor courier movements, store interactions, and any errors or events.

---

## 📥 Cloning the Project

To clone the project to your computer, use a terminal or command prompt:

```bash
git clone https://github.com/tariktosya/migros-online-case.git
cd migros-online-case

You can use the **start.bat** file I prepared to test the project. Here you'll see the basic test cases I've prepared in advance.
