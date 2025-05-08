
# 📱 ContactApp

**ContactApp** is a modern Android application for managing contacts. Built using **Jetpack Compose**, **Kotlin**, and **Hilt**, it allows users to create, update, delete, and restore contacts with an intuitive UI.

---

## ✨ Features

- ➕ Add new contacts with first name, last name, phone number, and email
- 🗂 View and manage a list of saved contacts
- 🖊 Edit and 🗑 delete contact entries
- ♻️ View and restore deleted contacts
- 📱 Click-to-call feature
- 💾 Local data persistence (Room DB)
- 🧪 MVVM architecture with clean separation of concerns

---

## 🚀 Getting Started

### ✅ Prerequisites

Ensure you have the following installed:

- Android Studio **Giraffe or later**
- Kotlin **1.9+**
- Gradle **8.0+**
- JDK **17**
- Android SDK set in `local.properties`

---

### 📦 Clone the Repository

```bash
git clone [https://github.com/amanrathore2024/Contact-App.git]
cd ContactApp
```

---

### ⚙️ Build and Run

1. Open the project in Android Studio.
2. Sync Gradle and let dependencies resolve.
3. Connect an emulator or physical Android device.
4. Click **Run** or use:

```bash
./gradlew installDebug
```

---

## 📸 Screenshots 

### ➕ Add Contact Screen
<img src="WhatsApp Image 2025-04-30 at 23.19.36_c2381739.jpg" alt="Add Contact Screen" width="300"/>

### 📇 Contact List Screen
<img src="WhatsApp Image 2025-04-30 at 23.19.36_c77f03db.jpg" alt="Contact List Screen" width="300"/>

### 🗑 Deleted Contacts Screen
<img src="WhatsApp Image 2025-04-30 at 23.19.36_e4521e6c.jpg" alt="Deleted Contacts Screen" width="300"/>

---

## 🧾 Dependencies

| Library         | Purpose                     |
|-----------------|-----------------------------|
| Jetpack Compose | Modern UI Toolkit           |
| Hilt            | Dependency Injection        |
| Room            | Local Database              |
| Navigation      | Compose Navigation          |
| Coil            | Image Loading      |
| KotlinX         | Coroutines for async ops    |

---

## 📂 Project Structure

```plaintext
├── di/                    # Hilt modules
├── data/                  # Room database, DAOs
├── domain/                # Models, use-cases
├── presentation/          # UI and screens (Compose)
├── MainActivity.kt        # App entry point
├── ContactApp.kt          # Hilt Application class
```

---

## 🧪 Testing

Run the following commands:

```bash
./gradlew test
./gradlew connectedAndroidTest
```

---
