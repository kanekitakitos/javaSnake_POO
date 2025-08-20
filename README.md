## javaSnake_POO

![Java](https://img.shields.io/badge/language-Java-blue.svg)
![Status](https://img.shields.io/badge/status-in%20development-orange)
<!-- Add a license badge once defined -->

OOPSnake is a modern, object‑oriented Java project inspired by the classic Snake game, with a strong focus on OOP best practices, clear separation of concerns, and a custom geometry layer for precise collisions.

The main goal is to solidify Object‑Oriented Programming concepts by building a functional, modular, and maintainable game featuring:

- Engaging graphical interface (Java Swing)
- Configurable gameplay mechanics and dynamic obstacles
- Manual and automatic control (A* pathfinding)
- Integrated image and audio assets


## Table of Contents

- [Description](#description)
- [Design Patterns](#design-patterns)
- [Main Features](#main-features)
- [Controls](#controls)
- [Project Structure](#project-structure)
- [Main Components](#main-components)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Contribution Guide](#contribution-guide)
- [Try the Classic Snake](#try-the-classic-snake)
- [License](#license)
- [Credits](#credits)

---

## Description

OOPSnake implements Snake with a clear split between game logic, rendering, and geometry utilities. The entry point (`Main`) instantiates a `Game` with grid and obstacle parameters, then opens the Swing `View`, which arranges the `MenuPanel` (START/CUSTOM START buttons) and the `GamePanel` (rendering and game loop via Swing `Timer`).

Resources (images and audio) are managed by `Assets`/`Loader`. Collision and drawing are based on geometric primitives (`Quadrado`, `Poligono`, `Circulo`, etc.), enabling rotating obstacles and safe fruit spawning.

---

## Design Patterns

1. **Strategy Pattern**  
   Used for game control: `Control` is the strategy with implementations `ManualControl` (keyboard) and `AutoControl` (A*). `Game` delegates left/right/no‑turn decisions to the active strategy, allowing easy switching without modifying core logic.

2. **Model–View separation (MVC‑ish)**  
   Logic (`OOPS`) is decoupled from UI (`Graphics`). `View`/`GamePanel` handle rendering and events; `Game` owns state and rules. This separation eases testing, maintenance, and future UI swaps.

---

## Main Features

- **Manual and Automatic Control**: arrow keys or A* to reach fruit while avoiding obstacles/body.
- **Custom Geometry Engine**: collisions with polygons, rectangles, triangles, circles; continuously rotating obstacles.
- **Configurable Grid and Maps**: cell size, dimensions (H×V), and predefined obstacle sets.
- **Persistent Leaderboard**: TSV‑based top scores.
- **Assets**: backgrounds, snake sprites (head/body/tail), multiple fruits, music and SFX.

---

## Controls

- **Arrow Keys**: move the snake
- **START**: start with default settings
- **CUSTOM START**: configure cell size, dimensions, map [0–4], and mode (Manual/Auto)

> Tip: after START, click the game panel once if the arrow keys don’t respond (keyboard focus).

---

## Project Structure

```
snakePOO/
├── src/
│   ├── Geometrics/        # Geometry shapes and operations (Point, Square, Polygon, etc.)
│   ├── Graphics/          # Swing UI (View, GamePanel, MenuPanel), Assets and Loader
│   ├── OOPS/              # Game logic (Game, Snake, Control, Obstacle, Leaderboard)
│   └── Main.java          # Entry point
├── out/                   # Compiled output (IDE)
└── README.md
```

---

## Main Components

### Core (OOPS)
- **Game**: game state and rules; update loop; fruit spawns; collision checks and game over; delegates control to `Control`.
- **Snake / SnakeBodyPart**: segmented model and updates; orientation, growth, and self‑collision.
- **Control (Strategy)**: `ManualControl` (keyboard) and `AutoControl` (A* grid pathfinding).
- **Obstacle**: polygons (some continuously rotating); interacts with geometry for collisions.
- **Leaderboard/Score**: TSV read/write, sorting of scores.

### Graphics
- **View**: main `JFrame`; arranges menu and game panel; handles START/CUSTOM START.
- **GamePanel**: rendering, score HUD, Swing `Timer` loop, keyboard input.
- **Assets/Loader**: image loading and resizing; audio (music/SFX) via Java Sound.

### Geometrics
- **Quadrado, Poligono, Circulo, Triangulo, Reta, SegmentoReta, Ponto, Trajetoria**: building blocks for precise drawing and collisions.

---

## Technologies Used

- **Java 17+**
- **Java Swing** (UI) and **Java Sound** (audio)
- **BufferedImage** for sprites/images; dynamic resizing
- **Cross‑platform**: Windows, Linux, macOS
- **Recommended IDEs**: IntelliJ IDEA, Eclipse, VS Code (Java extension)

---

## Getting Started

### 1) Clone the repository

```bash
git clone https://github.com/kanekitakitos/snakePOO.git
cd snakePOO
```

### 2) Compile the project

Windows (PowerShell):

```powershell
Remove-Item -Recurse -Force bin -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Force bin | Out-Null
Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
javac -d bin @sources.txt
```

macOS/Linux (bash):

```bash
rm -rf bin && mkdir -p bin
find src -name "*.java" > sources.txt
javac -d bin @sources.txt
```

### 3) Run the game

```bash
java -cp bin Main
```

> Important: run from the repository root — assets are loaded via relative paths (e.g., `src/Graphics/...`).

---

## Contribution Guide

1. Fork the repository
2. Create a feature branch (`git checkout -b my-feature`)
3. Commit (`git commit -m 'feat: my new feature'`)
4. Push (`git push origin my-feature`)
5. Open a Pull Request

Suggestions welcome: switch asset loading to classpath, improve keyboard focus handling, refine `AutoControl`, and polish UI/UX.

---

## Try the Classic Snake

Want to revisit the classic before playing our version?  
Play a classic Snake online here:  
[https://playsnake.org](https://playsnake.org)

---

## License

This project is licensed under the MIT.

---

## Credits

- **Authors (in code headers):** [José Diogo Ferras](https://github.com/zediogoferras), [Miguel Silva](https://github.com/MiguelDzn2), [Brandon Mejia](https://github.com/kanekitakitos)
- **Images/Audio:** under `src/Graphics/` (development use)

