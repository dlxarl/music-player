# Music Player

A lightweight desktop music player project built with Java Swing. It combines a minimal dark-themed interface, a BasicPlayer-based audio playback layer, and a simple playlist model.

## Overview

The project is organized into three main parts:

- **Player** — wraps BasicPlayer and exposes playback operations such as play, pause, resume, stop, next, and previous.
- **Playlist** — stores the paths of audio tracks and provides basic indexed access.
- **UI** — provides a Swing window, custom progress-bar drawing, track artwork, icons, and typography assets.

## Current Features

- Java Swing desktop window with a minimal dark theme
- Audio playback layer based on BasicPlayer
- Basic playlist data model
- Playback state and elapsed-time tracking in the audio layer
- Custom-drawn progress bar component
- SVG icon loading and raster image scaling
- Bundled Montserrat fonts and interface assets
- File chooser code configured for selecting multiple MP3 and WAV files

## Audio Formats

The file chooser implementation is configured to filter **MP3** and **WAV** files.

The separate `AudioFormatSupport` helper recognizes the extensions MP3, WAV, OGG, and AAC. This helper only checks file extensions; actual decoding depends on the available playback codecs and libraries.

## Tech Stack

- Java
- Java Swing / AWT
- Gradle with Kotlin DSL
- BasicPlayer
- MP3 SPI
- Tritonus Share
- SVG Salamander

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

## Getting Started

Clone the repository:

```bash
git clone https://github.com/dlxarl/music-player.git
cd music-player
```

Build the project:

On macOS / Linux:

```bash
./gradlew build
```

On Windows:

```bat
gradlew.bat build
```

To launch the application, import the project into an IDE as a Gradle project and run the `Main` class.

> The current Gradle configuration applies the Java plugin but does not define an `application` plugin or a `run` task.

## Development Status

This repository is an early-stage implementation. The playback layer and several UI components exist, but the graphical interface is not yet fully wired to the player: for example, the file chooser method is not connected to the folder button, and the progress display is not yet updated from playback events. Expect incomplete interactions and ongoing development.

## License

No license file is currently included in the repository.
