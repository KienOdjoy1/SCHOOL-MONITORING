# MyCSU Companion

A Java-based Android companion app for quick access to CSU MySchool.

**Official portal:** https://myschool.carsu.edu.ph/

## Current features

- A simple student dashboard
- Quick-access buttons for Grades, Class Schedule, Announcements, Clearance, School Balance / Bills, Offered Subjects, and Online Enrollment
- Opens the official MySchool portal inside the app using Android WebView
- Web navigation and a Home button to return to the dashboard
- Internet permission declared in the Android manifest

## Important limitations

This first version is a **portal shortcut**, not an official CSU application. Each service button currently opens the MySchool homepage because the correct internal pages and supported data-access methods have not yet been verified.

The app does not read grades, bills, clearance, or enrollment data directly into its own dashboard. Those features require verified page destinations or an authorized API/integration from the school. Do not try to bypass school login, access controls, or other security measures.

The app has not been tested against the live school portal on every device. If the portal blocks WebView, uses an unsupported sign-in flow, or requires an external browser, use the official portal in your browser until compatibility is confirmed.

## Requirements

- Android Studio
- JDK 17 (Android Studio's bundled JDK is usually suitable)
- Internet connection

## Open and run

1. Download and extract this repository.
2. In Android Studio, choose **Open** and select the `MyCSU-Companion` folder.
3. Wait for Gradle sync to finish.
4. Connect an Android phone with USB debugging enabled, or start an emulator.
5. Press **Run**.

If Android Studio asks to install a compatible Android Gradle Plugin or SDK platform, allow it to install the required components.

## Build an APK

In Android Studio, select **Build > Build Bundle(s) / APK(s) > Build APK(s)** (the exact menu wording may vary by version).

The debug APK is normally created at:

`app/build/outputs/apk/debug/app-debug.apk`

## Publish to GitHub

1. Create a new repository named `MyCSU-Companion` on GitHub.
2. For the simplest upload, choose **Add file > Upload files** and upload the project contents, or use Git from the project folder:
   ```bash
   git init
   git add .
   git commit -m "Initial MyCSU Companion Android app"
   git branch -M main
   git remote add origin https://github.com/YOUR-USERNAME/MyCSU-Companion.git
   git push -u origin main
   ```
3. Replace `YOUR-USERNAME` with your GitHub username.
4. Do not commit passwords, cookies, session tokens, student records, or other private data.

## Package name

`com.example.mycsucompanion`

You can change it later from Android Studio's refactor tools.

## Disclaimer

This is an independent student project and is not affiliated with or endorsed by Caraga State University. Use your own account only and follow the school's rules.

## Build the APK using GitHub Actions (no Android Studio required on your PC)

This repository includes `.github/workflows/build-apk.yml`. After uploading the project to GitHub:

1. Open the repository on GitHub and select **Actions**.
2. If prompted, enable GitHub Actions for the repository.
3. Push a commit to the `main` branch, or choose **Build MyCSU Companion APK** and click **Run workflow**.
4. Open the completed workflow run.
5. Under **Artifacts**, download `MyCSU-Companion-debug-apk`.
6. Extract the downloaded artifact to get `app-debug.apk`.

The workflow builds a **debug APK for testing**. It is not a signed production release for Google Play. GitHub-hosted build minutes and artifact limits depend on your GitHub account and current GitHub plan.

