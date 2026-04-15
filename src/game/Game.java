package game;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import edu.princeton.cs.introcs.StdDraw;


public class Game {
    
    private static final int INITIAL_ENEMIES = 3;
    private static final int INITIAL_SCORE = 0;
    private static final double SCORE_TEXT_X = 0.1;
    private static final double SCORE_TEXT_Y = 0.9;
    private static final int PAUSE_TIME = 40;

    private Player player;
    private List<Projectile> enemyProjectiles;
    private List<Projectile> playerProjectiles;
    private List<Enemy> enemies;
    private int numberOfEnemies;
    private int score;

    public Game() {
        player = new Player();
        enemyProjectiles = new LinkedList<>();
        playerProjectiles = new LinkedList<>();
        enemies = new LinkedList<>();
        numberOfEnemies = INITIAL_ENEMIES;
        score = INITIAL_SCORE; 
    }

    public void run() {
        StdDraw.enableDoubleBuffering();
        while(true) {
            score = 0;
            initializeLevel();
            boolean gameOver = false;
            while(gameOver == false) {
                updatePositions();
                fireProjectiles();
                gameOver = checkCollisions();
                if(enemies.size() == 0) {
                    numberOfEnemies++;
                    initializeLevel();
                }
                draw();
            }
        }
    }

    public void initializeLevel() {
        player = new Player();
        enemyProjectiles.clear();
        playerProjectiles.clear();
        enemies.clear();
        for(int i = 0; i < numberOfEnemies; i++) {
            Enemy e = new Enemy();
            enemies.add(e);
        }  
    }

    private void updatePositions() {
        updateEnemyProjectiles();
        updatePlayerProjectiles();
        updateEnemies();
        updatePlayer();
    }

    private void updateEnemyProjectiles() {
        for(int i = 0; i < enemyProjectiles.size(); i++) {
            Projectile p = enemyProjectiles.get(i);
            p.moveDown();
            if(p.isOutOfBounds() == true) {
                enemyProjectiles.remove(p);
            }
        }
    }

    private void updatePlayerProjectiles() {
        for(int i = 0; i < playerProjectiles.size(); i++) {
            Projectile p = playerProjectiles.get(i);
            p.moveUp();
            if(p.isOutOfBounds() == true) {
                playerProjectiles.remove(p);
            }
        }
    }

    private void updateEnemies() {
        for(Enemy e : enemies) {
            e.move();
        }
    }

    private void updatePlayer() {
        player.move();
    }

    private void fireProjectiles() {
        for(Enemy e : enemies) {
            if(e.isFiring() == true) {
                Projectile p = new Projectile(e.getXPosition(), e.getYPosition() - e.getSize(), Color.RED);
                enemyProjectiles.add(p);
            }
            
        }
        if(player.isFiring() == true) {
            Projectile p = new Projectile(player.getXPosition(), player.getYPosition() + player.getSize(), Color.BLACK);
            playerProjectiles.add(p);
        }
    }

    private boolean checkCollisions() {
        checkPlayerProjectileCollisions();
        return checkEnemyProjectileCollisions();
    }

    private void checkPlayerProjectileCollisions() {
        for(int i = 0; i < playerProjectiles.size(); i++) {
            Projectile p = playerProjectiles.get(i);
            for(int j = 0; j < enemies.size(); j++) {
                Enemy e = enemies.get(j);
                if(p.collidesWith(e) == true) {
                    enemies.remove(e);
                    playerProjectiles.remove(p);
                    score++;
                }
            }
        }
    }

    private boolean checkEnemyProjectileCollisions() {
        for(Projectile p : enemyProjectiles) {
            if(p.collidesWith(player)) {
                return true;
            }
        }
        return false;
    }

    private void draw() {
        StdDraw.clear();
        for(Enemy e : enemies) {
            e.draw();
        }
        for(Projectile p : playerProjectiles) {
            p.draw();
        }
        for(Projectile p : enemyProjectiles) {
            p.draw();
        }
        player.draw();
        StdDraw.text(SCORE_TEXT_X, SCORE_TEXT_Y, "Score: " + score);
        StdDraw.pause(PAUSE_TIME);
        StdDraw.show();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.run();
    }
}