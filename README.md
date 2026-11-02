# Music Player

A lightweight desktop audio player built with Java and Swing. The project provides a minimal dark interface for loading and playing local audio files, with a simple playlist model and playback controls.

## Features

- Desktop user interface built with Java Swing
- Load multiple local audio files through a file chooser
- Basic playback operations: play, pause, resume, stop, next, and previous
- Simple playlist management
- Playback progress and elapsed-time tracking
- Custom interface elements, including a painted progress bar
- SVG icons, album artwork, and custom Montserrat fonts
- Audio playback powered by BasicPlayer

## Supported Formats

The player currently filters and loads MP3 and WAV files through the graphical file chooser.

The audio format utility also lists MP3, WAV, OGG, and AAC as supported extensions, but the current UI does not expose OGG or AAC file selection.

## Tech Stack

- Java
- Java Swing / AWT
- Gradle (Kotlin DSL)
- BasicPlayer
- MP3 SPI
- SVG Salamander

## Requirements

- JDK 17 or newer
- Gradle Wrapper (included)

## Getting Started

Clone the repository:

```bash
git clone https://github.com/dlxarl/music-player.git
cd music-player
```

Run the application using the Gradle Wrapper:

On macOS / Linux:

```bash
./gradlew run
```

On Windows:

```bat
gradlew.bat run
```

> Note: The project currently defines Java and test dependencies but does not declare a Gradle `application` plugin or a `run` task. To launch it from Gradle, configure an application entry point or run the compiled `Main` class from your IDE.

## Project Structure

```text
src/
└── main/
    ├── java/
    │   ├── Main.java
    │   ├── player/
    │   │   ├── AudioFile.java
    │   │   ├── AudioFormatSupport.java
    │   │   ├── AudioPlayer.java
    │   │   └── Playlist.java
    │   ├── ui/
    │   │   ├── AudioPlayerUI.java
    │   │   └── ProgressBar.java
    │   └── util/
    │       ├── FileUtils.java
    │       ├── FontUtils.java
    │       ├── IconLoader.java
    │       └── ImageLoader.java
    └── resources/
        ├── fonts/
        ├── icons/
        └── images/
```

## Current Status

This is an experimental project and is still under development. Some interface components and playback interactions are not fully wired up yet. The current implementation is best viewed as a foundation for a compact desktop music player rather than a feature-complete media application.

## License

No license file is currently included in the repository.
