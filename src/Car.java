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
    private static final int base_speed = 5;
    private int speed;

    private Set<Checkpoint> visitedCheckpoints; // Lista odwiedzonych checkpointów
    private Set<Checkpoint_Streetlight> visitedStreetlights; // Lista odwiedzonych checkpointów

    public Car(String car_origin, String car_destination) {
        this.x = 0;
        this.y = 0;
        this.dx = base_speed;
        this.dy = base_speed;
        this.origin = car_origin;
        this.destination = car_destination;
        this.original_origin = car_origin;
        this.turn_direction = "straight";
        this.stopped = false;
        this.colision = false;

        // Inicjalizacja zbioru odwiedzonych checkpointów
        visitedCheckpoints = new HashSet<>();
        visitedStreetlights = new HashSet<>();
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
                    y += speed;
                    break;
                case "south":
                    y -= speed;
                    break;
                case "east":
                    x -= speed;
                    break;
                case "west":
                    x += speed;
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
                        dx = speed;
                        dy = 0;
                        origin = "west"; // Aktualizacja kierunku
                        break;
                    case "west":
                        dx = speed;
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
                        dx = speed;
                        dy = 0;
                        origin = "west"; // Aktualizacja kierunku
                        break;
                    case "west":
                        dx = speed;
                        dy = 0;
                        origin = "east"; // Aktualizacja kierunku
                        break;
                }
                break;
            case "east":
                switch (destination) {
                    case "north":
                        dx = 0;
                        dy = speed;
                        origin = "south"; // Aktualizacja kierunku
                        break;
                    case "south":
                        dx = 0;
                        dy = speed;
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
                        dy = speed;
                        origin = "south"; // Aktualizacja kierunku
                        break;
                    case "south":
                        dx = 0;
                        dy = speed;
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

    public boolean hasVisitedStreetlight(Checkpoint_Streetlight streetlight) {
        return visitedStreetlights.contains(streetlight);
    }

    public void visitStreetlight(Checkpoint_Streetlight streetlight) {
        visitedStreetlights.add(streetlight);
    }

    public void setSpeed(int new_value) {
        this.speed = base_speed * new_value;
    }

}