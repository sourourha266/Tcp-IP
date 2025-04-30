# 🌍 City Temperature Management System (Java Socket Project)

## 📘 Overview

This project simulates a **client-server system** in Java where clients can either:
- **Produce (send/update)** temperature data for cities
- **Consume (retrieve)** temperature data or statistics

The server handles multiple client connections concurrently using threads and maintains a shared **map of cities and their temperatures**.

---

## 🧱 Architecture

**Client Types**:
- 🧊 **Producer**: Sends city names with temperature readings.
- 🔍 **Consumer**: Requests temperatures of specific cities, average temperature, or the full list.

**Server**:
- Accepts connections on port `20000`
- Spawns a thread depending on client type (`p` or `c`)
- Maintains a shared `HashMap<String, Double>` for city temperature data

---

## 💻 Technologies

- Language: **Java**
- Communication: **TCP Sockets**
- Concurrency: **Java Threads**
- I/O Streams: **Scanner**, **PrintStream**

---

## 🚀 How to Run

1. **Start the Server**  
   Compile and run `serverCityTemperatures.java`  
   ```bash
   javac serverCityTemperatures.java
   java week4.serverCityTemperatures
