# Princeton Algorithms (Coursera) — Dev Container

A ready-to-use Linux environment for the [Algorithms, Part I / II](https://www.coursera.org/learn/algorithms-part1) Coursera course, matching Princeton's command-line toolchain (JDK 11, `javac-algs4`, `java-algs4`, Checkstyle, SpotBugs, PMD).

## Quick start

**Prerequisites:** [Docker](https://docs.docker.com/get-docker/), [VS Code or Cursor](https://code.visualstudio.com/) with the [Dev Containers](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers) extension.

1. Clone this repository.
2. Open the repo folder in VS Code or Cursor.
3. Run **Dev Containers: Reopen in Container** (or accept the prompt to reopen in container).
4. Wait for the image build and setup checks to finish.
5. Open the integrated terminal and work in `src/` and `data/`.

No manual JDK install or `lift-cli.zip` download is required.

## Folder layout

| Path | Purpose |
|------|---------|
| `src/` | Java source files — compile and run from here |
| `data/` | Input files (`.txt` and other fixtures) |
| `assets/` | Static files (`WELCOME.txt`, `logo.png`) |
| `.lift/` | Coursera Checkstyle rules and project `algs4.jar` |
| `.devcontainer/` | Docker image and devcontainer configuration |

Each exercise is a standalone program: put one `.java` file in `src/` and any input files in `data/`.

## Compile and run

From the integrated terminal:

```bash
cd src
javac-algs4 HelloWorld.java
java-algs4 HelloWorld
```

Programs that read files should use paths relative to `src/`, for example `../data/animals.txt`, or redirect stdin:

```bash
java-algs4 MyProgram < ../data/animals.txt
```

## Checkstyle

Run from the repository root:

```bash
checkstyle -coursera src/HelloWorld.java
```

## Adding a new exercise

1. Add your `.java` file to `src/`.
2. Add any input files to `data/`.
3. Compile and run from `src/` as shown above.

## Coursera assignment zips

Official Princeton assignment zips use a flat IntelliJ project layout (`.idea/`, `.iml`, mixed files). This repo uses a **custom layout** with separated `src/` and `data/`. When you download a new assignment, copy the Java sources into `src/` and data files into `data/` rather than replacing the repository tree.

## Optional notes

- **StdDraw** animations require an X11 display; GUI programs may not work inside the container without display forwarding.
- **StdAudio** may not work in the container without audio device forwarding.
- The CLI wrappers use `/usr/local/lift/lib/algs4.jar`. The copy in `.lift/algs4.jar` is used by Coursera Checkstyle configuration.
