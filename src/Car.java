import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

public class Car {
    private int x, y; // Współrzędne
    private int dx, dy; // Kierunek ruchu
    private boolean stopped, colision; // Czy pojazd jest zatrzymany
    private String origin; // Pochodzenie
    private String destination; // Destynacja
    private String original_origin; // Oryginalne pochodzenie
    private String turn_direction; // Kierunek skrętu
    private int speed_value;

    private Set<Checkpoint> visitedCheckpoints; // Lista odwiedzonych checkpointów

    public Car(String car_origin, String car_destination) {
        this.x = 0;
        this.y = 0;
        this.dx = 5;
        this.dy = 5;
        this.origin = car_origin;
        this.destination = car_destination;
        this.original_origin = car_origin;
        this.turn_direction = "straight";
        this.stopped = false;
        this.colision = false;

        // Inicjalizacja zbioru odwiedzonych checkpointów
        visitedCheckpoints = new HashSet<>();
    }

    public void spawn() {
        switch (origin) {
            case "south" -> {
                x = 310;
                y = 600;
            }
            case "north" -> {
                x = 260;
                y = -30;
            }
            case "east" -> {
                x = 600;
                y = 260;
            }
            case "west" -> {
                x = -30;
                y = 310;
            }
        }
    }

    public void move() {
        if (!stopped) {
            switch (origin) {
                case "north":
                    y += dy;
                    break;
                case "south":
                    y -= dy;
                    break;
                case "east":
                    x -= dx;
                    break;
                case "west":
                    x += dx;
                    break;
            }
        }
    }

    public void changeDirection() {
        switch (origin) {
            case "north":
                switch (destination) {
                    case "south":
                        // Nie zmieniamy kierunku, bo jedzie prosto
                        break;
                    case "east":
                        dx = 5;
                        dy = 0;
                        origin = "west"; // Aktualizacja kierunku
                        break;
                    case "west":
                        dx = 5;
                        dy = 0;
                        origin = "east"; // Aktualizacja kierunku
                        break;
                }
                break;
            case "south":
                switch (destination) {
                    case "north":
                        break;
                    case "east":
                        dx = 5;
                        dy = 0;
                        origin = "west"; // Aktualizacja kierunku
                        break;
                    case "west":
                        dx = 5;
                        dy = 0;
                        origin = "east"; // Aktualizacja kierunku
                        break;
                }
                break;
            case "east":
                switch (destination) {
                    case "north":
                        dx = 0;
                        dy = 5;
                        origin = "south"; // Aktualizacja kierunku
                        break;
                    case "south":
                        dx = 0;
                        dy = 5;
                        origin = "north"; // Aktualizacja kierunku
                        break;
                    case "west":
                        break;
                }
                break;
            case "west":
                switch (destination) {
                    case "north":
                        dx = 0;
                        dy = 5;
                        origin = "south"; // Aktualizacja kierunku
                        break;
                    case "south":
                        dx = 0;
                        dy = 5;
                        origin = "north"; // Aktualizacja kierunku
                        break;
                    case "east":
                        break;
                }
                break;
        }
    }


    public Rectangle getFieldOfView() {
        int width = 40;  // Szerokość pola widzenia
        int height = 100; // Długość pola widzenia

        switch (destination) {
            case "north":
                return new Rectangle(x, y - height, width, height);
            case "south":
                return new Rectangle(x, y, width, height);
            case "east":
                return new Rectangle(x, y, height, width);
            case "west":
                return new Rectangle(x - height, y, height, width);
            default:
                return new Rectangle(x, y, width, height);
        }
    }

    public String getTurnDirection() {
        switch (origin) {
            case "north":
                switch (destination) {
                    case "south":
                        // Nie zmieniamy kierunku skrętu, bo jedzie prosto
                        break;
                    case "east":
                        turn_direction = "left";
                        break;
                    case "west":
                        turn_direction = "right";
                        break;
                }
                break;
            case "south":
                switch (destination) {
                    case "north":
                        break;
                    case "east":
                        turn_direction = "right";
                        break;
                    case "west":
                        turn_direction = "left";
                        break;
                }
                break;
            case "east":
                switch (destination) {
                    case "north":
                        turn_direction = "right";
                        break;
                    case "south":
                        turn_direction = "left";
                        break;
                    case "west":
                        break;
                }
                break;
            case "west":
                switch (destination) {
                    case "north":
                        turn_direction = "left";
                        break;
                    case "south":
                        turn_direction = "right";
                        break;
                    case "east":
                        break;
                }
                break;
        }
        return turn_direction;
    }

    public void stop() {
        stopped = true;
    }

    public void resume() {
        stopped = false;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public boolean hasVisited(Checkpoint checkpoint) {
        return visitedCheckpoints.contains(checkpoint);
    }

    public void visitCheckpoint(Checkpoint checkpoint) {
        visitedCheckpoints.add(checkpoint);
    }

}