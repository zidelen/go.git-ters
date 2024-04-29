# CS3354Spring2024 - go.git-ters

## Team Members

| Name           | NetID |
|----------------|-------|
| Phillip Henry  | rrj29 |
| Camila Godinez | wir3  |
| Jared Beasley  | vhu2  |

---

## Project Description

Welcome to **go.git-ters**! This project is our implementation of a chess game
using Java. It includes various classes for representing the chessboard, pieces,
player moves, and a graphical user interface (GUI) for interacting with the
game.

---

## Project Structure

- **java/**: Contains the Java source code for the project.
    - **go_git_ters/**
        - **Main.java**: Entry point of the application.
        - **chess/**
            - **board/**
                - **Board.java**: Represents the chessboard and game logic.
                - **ChessGUI.java**: GUI implementation for playing chess.
            - **pieces/**
                - **Piece.java**: Abstract class for chess pieces.
                - **playable/**
                    - **Bishop.java**, **King.java**, **Knight.java**, *
                      *Pawn.java**, **Queen.java**, **Rook.java**: Classes
                      representing playable chess pieces.
        - **utils/**
            - **Color.java**: Enum for piece colors.
            - **Column.java**: Enum for chessboard columns.
            - **Position.java**: Class representing positions on the chessboard.

- **resources/**: Contains image resources for chess piece icons.

---

## Running the Application

1. **Clone the Repository**:
   git clone https://git.txstate.edu/rrj29/go.git-ters

2. **Open Project in IntelliJ IDEA**:

- Open IntelliJ IDEA.
- Select `Open` and choose the cloned project directory.

3. **Build and Run**:

- Build and run the `Main.java` file to start the chess game.

---

## Usage

- Upon running the application, the chessboard GUI will be displayed.
- Click on a piece to select it, then click on a valid square to move the piece.
- Follow the game rules and enjoy playing chess!

---

## Additional Notes

- Ensure you have Java Development Kit (JDK) installed on your machine.
- Make sure to configure the project SDK and dependencies in IntelliJ IDEA.

---

Feel free to contact us if you have any questions or suggestions. Happy gaming!
🚀🛡️


