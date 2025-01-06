import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Random;

public class SimulationPanel extends JPanel {

    static Integer sim_height = FrameSettings.panel_height;
    static Integer sim_width = FrameSettings.panel_width;
    public Timer sim_timer;
    public Timer generator_timer;

    private java.util.List<Car> cars;
    private java.util.List<Car> cars_to_generate;
    private java.util.List<Checkpoint> checkpoints;
    public long simulation_time; // Czas symulacji w milisekundach
    public long start_time;      // Czas rozpoczęcia symulacji
    public long pause_start_time = 0; // Czas rozpoczęcia pauzy
    public long pause_time = 0; // Czas trwania pauzy
    public int timer_time = 0;
    private int time_speed_value;

    public Boolean is_sim_paused = false; // Flaga określająca stan pauzy

    SimulationPanel(){
        cars = new ArrayList<>();
        cars_to_generate = new ArrayList<>();
        checkpoints = new ArrayList<>();

        time_speed_value = 1;
        sim_timer = new Timer(1000 / time_speed_value, e -> updateTimer());

        // Definiowanie punktów zmiany kierunku (przykładowo)
        checkpoints.add(new Checkpoint(335, 300, 15, 15, "south")); // Punkt na środku skrzyżowania
        checkpoints.add(new Checkpoint(250, 285, 15, 15, "north")); // Punkt na środku skrzyżowania
        checkpoints.add(new Checkpoint(285, 335, 15, 15, "west")); // Punkt na środku skrzyżowania
        checkpoints.add(new Checkpoint(300, 250, 15, 15, "east")); // Punkt na środku skrzyżowania
        //checkpoints.add(new Checkpoint(290, 100, 20, 20, "south")); // Inny punkt zmiany kierunku

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Ustawia poprawne rysowanie
        Graphics2D g2d = (Graphics2D) g;

        // Rysowanie obszaru skrzyżowania
        //g.setColor(new Color(0, 153, 0));
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, sim_width, sim_height);

        // Rysowanie jezdni pionowej
        g.setColor(new Color(96, 96, 96));
        g.fillRect(250, 0, 100, sim_height);

        // Rysowanie jezdni poziomej
        g.setColor(new Color(96, 96, 96));
        g.fillRect(0, 250, sim_width, 100);

        // Rysowanie znaków poziomych na jezdni
        g.setColor(Color.WHITE);
        // Linie P-4
        g.drawLine(300,0,300,250);
        g.drawLine(300, 350, 300, 600);
        g.drawLine(0,300,250,300);
        g.drawLine(350, 300, 600, 300);
        // Linie P-12
        g.drawLine(250, 250, 300, 250);
        g.drawLine(300, 350, 350, 350);
        g.drawLine(250, 300, 250, 350);
        g.drawLine(350, 250, 350, 300);

        // Checkpointy
//        g.fillRect(335, 300, 15, 15);
//        g.fillRect(250, 285, 15, 15);
//        g.fillRect(285, 335, 15, 15);
//        g.fillRect(300, 250, 15, 15);

        for (Car car : cars) {
            g.setColor(Color.RED);
            g.fillRect(car.getX(), car.getY(), 30, 30);
        }

    }

    // Określenie liczby pojazdów i ich relacji
    public void countCars(Map<String, Integer> cars_map) {
        String route;
        int count;

        // Iterujemy po mapie, aby uzyskać kierunek i liczbę pojazdów
        for (Map.Entry<String, Integer> entry : cars_map.entrySet()) {
            route = entry.getKey();  // Np. "South Left"
            count = entry.getValue();    // Liczba pojazdów

            // Generujemy pojazdy w zależności od kierunku
            if (route.contains("south")) {
                if (route.contains("left")) {
                    prepareGenerateCars("south","left", count);
                } else if (route.contains("straight")) {
                    prepareGenerateCars("south","straight", count);
                } else if (route.contains("right")) {
                    prepareGenerateCars("south","right", count);
                }
            } else if (route.contains("east")) {
                if (route.contains("left")) {
                    prepareGenerateCars("east","left", count);
                } else if (route.contains("straight")) {
                    prepareGenerateCars("east","straight", count);
                } else if (route.contains("right")) {
                    prepareGenerateCars("east","right", count);
                }
            } else if (route.contains("north")) {
                if (route.contains("left")) {
                    prepareGenerateCars("north","left", count);
                } else if (route.contains("straight")) {
                    prepareGenerateCars("north","straight", count);
                } else if (route.contains("right")) {
                    prepareGenerateCars("north","right", count);
                }
            } else if (route.contains("west")) {
                if (route.contains("left")) {
                    prepareGenerateCars("west","left", count);
                } else if (route.contains("straight")) {
                    prepareGenerateCars("west","straight", count);
                } else if (route.contains("right")) {
                    prepareGenerateCars("west","right", count);
                }
            }

            // Odświeżenie panelu po dodaniu pojazdów
            repaint();

        }
    }

    // Funkcja przygotowuje samochody do generacji
    public void prepareGenerateCars(String origin, String direction, int car_count) {
        for (int i = 0; i < car_count; i++) {
            // Tworzymy pojazdy w zależności od kierunku
            if (origin.equals("south")) {
                if (direction.contains("left")) {
                    cars_to_generate.add(new Car("south", "west"));
                } else if (direction.contains("straight")) {
                    cars_to_generate.add(new Car("south", "north"));
                } else if (direction.contains("right")) {
                    cars_to_generate.add(new Car("south", "east"));
                }
            } else if (origin.equals("east")) {
                if (direction.contains("left")) {
                    cars_to_generate.add(new Car("east", "south"));
                } else if (direction.contains("straight")) {
                    cars_to_generate.add(new Car("east", "west"));
                } else if (direction.contains("right")) {
                    cars_to_generate.add(new Car("east", "north"));
                }
            } else if (origin.equals("north")) {
                if (direction.contains("left")) {
                    cars_to_generate.add(new Car("north", "east"));
                } else if (direction.contains("straight")) {
                    cars_to_generate.add(new Car("north", "south"));
                } else if (direction.contains("right")) {
                    cars_to_generate.add(new Car("north", "west"));
                }
            } else if (origin.equals("west")) {
                if (direction.contains("left")) {
                    cars_to_generate.add(new Car("west", "north"));
                } else if (direction.contains("straight")) {
                    cars_to_generate.add(new Car("west", "east"));
                } else if (direction.contains("right")) {
                    cars_to_generate.add(new Car("west", "south"));
                }
            }
        }

    }

    public void startCarGeneration() {
        if (generator_timer != null && generator_timer.isRunning()) {
            return; // Jeśli timer już działa, nie robimy nic
        }

        if (cars_to_generate.isEmpty()) {
            return; // Zwrot, jeśli lista jest pusta
        }

        Random random = new Random();
        int totalCars = cars_to_generate.size();
        int interval = (int) ((simulation_time / totalCars) / time_speed_value); // Interwał w milisekundach

        generator_timer = new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (is_sim_paused) {
                    generator_timer.stop(); // Zatrzymujemy timer, gdy symulacja jest wstrzymana
                    return;
                }

                if (!cars_to_generate.isEmpty()) {
                    int index = random.nextInt(cars_to_generate.size());
                    Car car = cars_to_generate.get(index);
                    cars.add(car); // Dodajemy samochód do listy cars w symulacji
                    cars_to_generate.remove(index); // Usuwamy go z listy cars_to_generate
                    car.spawn(); // Spawnowanie samochodu
                    repaint(); // Odświeżenie panelu po dodaniu samochodu
                } else {
                    generator_timer.stop(); // Zatrzymujemy timer, gdy lista jest pusta
                }
            }
        });

        generator_timer.start(); // Uruchamiamy timer
    }

    public void startSimulation(Map<String, Integer> cars_map) {
        start_time = System.currentTimeMillis(); // Zapisanie czasu rozpoczęcia symulacji
        sim_timer.start(); // Uruchomienie wyświetlania czasu
        countCars(cars_map);                // Generowanie pojazdów
        startCarGeneration();               // Rozpoczęcie generowania samochodów z interwałem
        new Thread(this::run).start();      // Uruchomienie symulacji w nowym wątku
    }

    public void run() {
        // Spawn samochodów
        for (Car car : cars) {
            car.spawn();
        }

        while (true) {
            // Sprawdzenie czasu symulacji
            long elapsedTime = System.currentTimeMillis() - start_time;
            if (elapsedTime >= (simulation_time + pause_time)) {
                System.out.println("Symulacja zakończona.");

                break;
            }

            if (!is_sim_paused) { // Wstrzymanie ruchu pojazdów w pauzie
                for (Car car : cars) {
                    boolean shouldStop = false;

                    // Sprawdzenie, czy w polu widzenia danego pojazdu znajduje się inny pojazd
                    Rectangle fieldOfView = car.getFieldOfView();
                    for (Car otherCar : cars) {
                        if (car != otherCar && fieldOfView.intersects(otherCar.getBounds())) {
                            shouldStop = true;
                            break;
                        }
                    }

                    if (shouldStop) {
                        car.stop();
                    } else {
                        car.resume();
                    }

                    // Sprawdzenie, czy samochód wchodzi w obszar checkpointu
                    for (Checkpoint checkpoint : checkpoints) {
                        Rectangle carBounds = car.getBounds();
                        Rectangle checkpointBounds = new Rectangle(checkpoint.getX_ch(), checkpoint.getY_ch(), checkpoint.getWidht_ch(), checkpoint.getHeight_ch());

                        // Sprawdź, czy samochód przecina checkpoint i czy nie odwiedził go wcześniej
                        if (carBounds.intersects(checkpointBounds) && !car.hasVisited(checkpoint)) {
                            car.visitCheckpoint(checkpoint);  // Oznacz checkpoint jako odwiedzony
                            if (!Objects.equals(car.getOrigin(), checkpoint.getType_ch()) && Objects.equals(car.getTurnDirection(), "left")) {
                                car.changeDirection();            // Zmień kierunek pojazdu
                            } else if (Objects.equals(car.getTurnDirection(), "right")){
                                car.changeDirection();            // Zmień kierunek pojazdu
                            }
                            //System.out.println("Samochód dotarł do checkpointu typu: " + checkpoint.getType_ch());
                            break;
                        }
                    }

                    car.setSpeed(OptionsPanel.getTimeSpeedValue());

                    car.move();
                }
            }

            repaint();

            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetSimulation() {
        // Zatrzymanie symulacji, jeśli jest aktywna
        is_sim_paused = false;

        // Usunięcie wszystkich pojazdów
        cars.clear();
        cars_to_generate.clear();

        // Odświeżenie panelu
        repaint();

        // Wyzerwoanie czasów
        simulation_time = 0;
        start_time = 0;
        pause_time = 0;
        pause_start_time = 0;

        // Reset timera
        sim_timer.stop();
        timer_time = 0;
        String timeString = String.format("%02d:%02d:%02d", 0, 0, 0);
        OptionsPanel.label_timer.setText(timeString);  // Aktualizacja tekstu etykiety
    }

    // Aktualizowanie wyświetlania czasu
    private void updateTimer() {
        long elapsedTime = System.currentTimeMillis() - start_time;
        if (elapsedTime <= (simulation_time + pause_time)) {
            timer_time++;  // Zwiększ licznik sekund
        }

        int hours = timer_time / 3600;
        int minutes = (timer_time % 3600) / 60;
        int seconds = timer_time % 60;

        // Formatowanie czasu jako hh:mm:ss
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        OptionsPanel.label_timer.setText(timeString);  // Aktualizacja tekstu etykiety
    }

    // Aktualizacja szybkości timera w trakcie symulacji
    public void updateTimeSpeed(int newTimeSpeedValue) {
        this.time_speed_value = newTimeSpeedValue;

        // Zatrzymujemy obecny timer
        if (sim_timer.isRunning()) {
            sim_timer.stop();
        }

        if (generator_timer != null && generator_timer.isRunning()) {
            generator_timer.stop(); // Zatrzymujemy generator_timer
        }

        // Ustawiamy nowy interwał na podstawie nowej wartości time_speed_value
        sim_timer = new Timer(1000 / time_speed_value, e -> updateTimer());
        sim_timer.start();  // Uruchamiamy sim_timer ponownie

        // Ponwonie uruchamiamy funckję generacji, by zmienić interwał
        startCarGeneration();

    }

}

