# Tirhal - Specifications

## Core Idea
Tirhal is a companion app for Riyadh Air passengers, designed to streamline the airport experience with smart timers, checklists, and AR navigation.

## Design System
- **Colors**:
  - Primary Purple: #8B7BA8
  - Deep Navy Purple: #240454
  - Light Indigo: #5B4B8A
  - Background: White (#FFFFFF)
  - Text: Dark Gray (#1A1A1A)
  - Success: Green (#4CAF50)
- **Typography**: Roboto (Clean, Minimalistic)
- **Components**: Material 3, Rounded Corners (16dp)

## Features

### 1. Smart Departure Timer (Home Screen)
- **Input**: Flight time, Flight Type (Domestic/International).
- **Logic**: 
  - Domestic: Alert 2h before.
  - International: Alert 3h before.
- **Display**: Countdown timer.
- **Notification**: Local notification 15 min before departure.
- **Storage**: DataStore Preferences.

### 2. Documents Checklist (Checklist Screen)
- **Sections**: Documents, Essentials.
- **Items**: Passport, Boarding Pass, Visa, Insurance, Charger, Meds, Wallet, Keys.
- **UI**: Progress bar, Checkboxes, Reset button.
- **Storage**: DataStore Preferences.

### 3. AR Airport Wayfinding (AR Guide Screen)
- **Input**: Gate Number.
- **Action**: Show 3D arrow pointing to gate.
- **Tech**: ARCore, Device Sensors (Compass).
- **Visuals**: Purple arrow, distance overlay.

### 4. AR Seat Finder (AR Guide Screen)
- **Input**: Seat Number.
- **Action**: Show arrow pointing to seat/row.
- **Tech**: ARCore.
- **Visuals**: Light purple arrow, green circle on arrival.

## Technical Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material 3)
- **Navigation**: Jetpack Navigation Compose
- **Persistence**: DataStore Preferences
- **AR**: ARCore (Sceneform or raw)
- **Permissions**: Accompanist Permissions (Camera)
