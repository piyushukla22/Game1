# This game was submitted as a college project and has lots of memories attacthed. anyways below is the README you all came here for. this is v1. if you want the more more detailed one dont hesitate to ask.

# Game README

This Java game demonstrates a simple 2D game using the AWT library. The game includes a player that can move around the screen and avoid collisions with enemies. The player's health decreases upon collision with enemies, and the game ends when the player's health reaches zero.

## Game Structure

### `Game` Class
- Manages the game loop and initializes game objects.
- Uses `Handler` to manage game objects and `HUD` to display player health and score.

### `Handler` Class
- Manages a list of `GameObject` instances (e.g., player, enemies).
- Updates and renders all game objects.

### `GameObject` Class
- Represents a generic game object with position and ID.
- Extended by specific game object classes like `Player` and `Enemy`.

### `Player` Class
- Extends `GameObject` and represents the player entity.
- Handles player movement, collision detection, and rendering.
- Uses `Trail` for visual effects behind the player.

### `Enemy` Classes
- Extend `GameObject` and represent different types of enemies (e.g., `BasicEnemy`, `FastEnemy`, `SmartEnemy`).
- Handle enemy movement, collision detection, and rendering.

### `HUD` Class
- Manages the Heads-Up Display (HUD) for displaying player health and score.

## Running the Game
To run the game, compile all Java files and run the `Game` class. Use the arrow keys to move the player and avoid collisions with enemies. The game ends when the player's health reaches zero.

## Additional Notes
- This game is a basic demonstration and can be expanded with additional features, levels, and enemy types.
- It uses simple graphics and collision detection techniques for educational purposes.
