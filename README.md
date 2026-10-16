# Little Lemon App 🍋

A native Android application for **Little Lemon**, a restaurant menu experience built with Kotlin and Jetpack Compose. The app retrieves menu data from a remote JSON endpoint, saves it locally, and displays it through a Compose navigation flow.

## Features

- **Remote menu loading** — retrieves menu data from the Little Lemon sample menu API.
- **Local persistence** — stores menu items in a Room database so the app can use cached data.
- **Jetpack Compose UI** — builds the interface with modern declarative Android UI components and Material 3.
- **Navigation** — uses Navigation Compose to move between app screens.
- **Menu data parsing** — uses Kotlin serialization with Ktor's content negotiation support.
- **Image support** — includes Glide Compose for loading images in Compose interfaces.

## Tech Stack

| Technology | Purpose |
| --- | --- |
| Kotlin | Primary programming language |
| Jetpack Compose | UI toolkit |
| Material 3 | UI components and theming |
| Navigation Compose | App navigation |
| Room | Local SQLite-backed persistence |
| Ktor Client (Android) | HTTP requests |
| Kotlinx Serialization | JSON deserialization |
| KSP | Room compiler integration |
| Gradle Kotlin DSL | Build configuration |

## How It Works

On launch, the app observes menu items stored in the local Room database and passes them to the navigation layer. If the database is empty, it requests the menu JSON from the endpoint below and inserts the returned items locally.

**Menu API:** [Little Lemon sample menu JSON](https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json)

The app requests internet access through the Android manifest. If the initial network request fails, the exception is logged and the local database remains available; the app does not automatically retry in this startup flow.

## Getting Started

### Prerequisites

- Android Studio with support for the project's Android Gradle Plugin
- JDK 11-compatible toolchain (as configured in the project)
- Android SDK for the configured compile SDK
- An emulator or Android device running Android 7.0 (API 24) or newer

### Run the App

1. Clone the repository:

   ```bash
   git clone https://github.com/Jayati-Rai/little_lemon_app_.git
   ```

2. Open the `little_lemon_app_` folder in Android Studio.
3. Allow Gradle to sync and download the required dependencies.
4. Select an emulator or connect an Android device.
5. Run the `app` configuration.

You can also build the debug APK from the project root:

```bash
./gradlew assembleDebug
```

On Windows:

```bat
gradlew.bat assembleDebug
```

The generated APK is normally located at `app/build/outputs/apk/debug/app-debug.apk`.

## Project Structure

```text
little_lemon_app_/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/littlelemonapp/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── navigation/       # Navigation flow
│   │   │   │   ├── networkAPI/       # Remote menu data models
│   │   │   │   └── ui/theme/         # Compose theme, colors, typography
│   │   │   ├── res/                  # Android resources
│   │   │   └── AndroidManifest.xml
│   │   ├── test/                     # Unit tests
│   │   └── androidTest/              # Instrumented tests
│   └── build.gradle.kts
├── gradle/
│   └── libs.versions.toml            # Dependency version catalog
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

> The structure above highlights the intended roles of the main packages; some components may be added as the project evolves.

## Testing

Run unit tests with:

```bash
./gradlew test
```

Run connected Android tests with a device or emulator attached:

```bash
./gradlew connectedAndroidTest
```

## Current Scope

This repository is an Android app project focused on menu data, persistence, Compose UI, and navigation. For the latest implemented screens and interactions, see the source code in the repository.

## Acknowledgements

The sample menu data is provided by the [Meta Mobile Developer PC Working With Data API repository](https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API).

## License

No license file is currently included in this repository. Unless a license is added, the project should be treated as having all rights reserved by default.
