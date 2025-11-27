# Implementation Plan - Tirhal

## Phase 1: Project Setup & Dependencies
- [ ] Update `build.gradle.kts` with dependencies (Compose, Nav, DataStore, ARCore, Accompanist).
- [ ] Update `AndroidManifest.xml` with permissions (Camera, Internet, Notifications) and AR features.
- [ ] Create `ui/theme` with Riyadh Air colors and typography.

## Phase 2: Data Layer
- [ ] Create `data/UserPreferences.kt` for DataStore (Flight info, Checklist state).
- [ ] Create `data/ChecklistRepository.kt` (optional, or handle in ViewModel).

## Phase 3: UI Components & Screens
- [ ] Create `ui/components/CommonUi.kt` (Buttons, Cards, Inputs).
- [ ] Implement `ui/screens/HomeScreen.kt` (Timer logic).
- [ ] Implement `ui/screens/ChecklistScreen.kt` (List & Progress).
- [ ] Implement `ui/screens/ARGuideScreen.kt` (UI shell for AR).

## Phase 4: AR Implementation
- [ ] Implement `ui/ar/ARView.kt` using ARCore.
- [ ] Add Wayfinding logic (Compass-based rotation).
- [ ] Add Seat Finder logic (Mock logic for demo).

## Phase 5: Navigation & Main Activity
- [ ] Setup `ui/navigation/NavGraph.kt`.
- [ ] Integrate all screens in `MainActivity.kt`.
- [ ] Final Polish & Testing.
