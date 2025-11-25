# History Log

## Initial Setup
- Created `specifications.md`.
- Created `implementation_plan.md`.

## Implementation
- Updated `build.gradle.kts` with Navigation, DataStore, ARCore, Accompanist.
- Updated `AndroidManifest.xml` with permissions and AR features.
- Created `ui/theme` with Riyadh Air colors.
- Created `data/UserPreferences.kt` for local storage.
- Created `ui/components/CommonUi.kt` for reusable UI.
- Created `ui/screens/HomeScreen.kt` with Smart Timer logic.
- Created `ui/screens/ChecklistScreen.kt` with persistence.
- Created `ui/screens/ARGuideScreen.kt` with Camera permission handling and UI shell.
- Created `ui/navigation/NavGraph.kt`.
- Integrated everything in `MainActivity.kt`.

## Fixes
- Updated `gradle/libs.versions.toml` to include `navigation-compose`, `datastore-preferences`, `accompanist-permissions`, and `ar-core`.
- Updated `app/build.gradle.kts` to use version catalog aliases.
- Fixed deprecated `Icons.Filled.List` usage in `MainActivity.kt` to `Icons.AutoMirrored.Filled.List`.
- Fixed `Unresolved reference 'hasPermission'` in `ARGuideScreen.kt` by updating to `status.isGranted` and adding the necessary import.
- Fixed missing imports in `ChecklistScreen.kt` (`background`, `clip`, `RoundedCornerShape`, `TextDecoration`).

## Refactor (Design Improvements)
- **Onboarding**: Added `OnboardingScreen` and `isFirstLaunch` preference in `UserPreferences`.
- **Home Screen**: Redesigned `HomeScreen` to be a rich dashboard with mock flight data, gradient header, and image-based quick action cards. Removed user inputs.
- **AR Guide**: Refactored `ARGuideScreen` to remove manual inputs and use mock gate/seat data.
- **Components**: Added `PlaceholderImage` and `ImageCard` to `CommonUi.kt` to support the new design.
- **Navigation**: Updated `NavGraph` and `MainActivity` to support the onboarding flow and conditional bottom bar visibility.
- **Checklist**: Polished `ChecklistScreen` UI to match the new design aesthetic.

## Image Integration
- Updated `CommonUi.kt`: `ImageCard` now accepts a drawable resource ID and uses `Image` composable with `ContentScale.Crop`.
- Updated `HomeScreen.kt`: Replaced placeholders with actual images for User Avatar, Airplane Icon, and Quick Action Cards (`bg_checklist`, `bg_airport_guide`, `bg_seat_finder`, `bg_flight_updates`).
- Updated `OnboardingScreen.kt`: Replaced logo placeholder with `riyadh_air_logo`.
