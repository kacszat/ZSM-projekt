import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class FrameSettings {
    static Integer main_frame_width = 1280;
    static Integer main_frame_height = 720;

    static Integer panel_width = 600;
    static Integer panel_height = 600;

    static JFrame main_frame;
    static JMenuBar main_menubar;
    static JMenu main_menu;
    static JMenuItem menu_option_1, menu_option_2, menu_option_3, menu_option_4;

    static SimulationPanel sim_panel;
    static OptionsPanel options_panel;
    static StreetlightsPanel streetlights_panel;

    public static void showFrame() {
        // Tworzymy nowe okno
        main_frame = new JFrame("Symulacja ruchu");

        // Ustawienia okna
        main_frame.setSize(main_frame_width, main_frame_height); // Rozmiar okna
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zamknięcie aplikacji przy zamknięciu okna
        main_frame.getContentPane().setBackground(Color.GRAY);

        // Opcje
        main_menubar = new JMenuBar();
        main_menu = new JMenu("Opcje");
        menu_option_1 = new JMenuItem("Option1");
        menu_option_2 = new JMenuItem("Option2");
        menu_option_3 = new JMenuItem("Option3");
        menu_option_4 = new JMenuItem("Option4");

        main_menu.add(menu_option_1);
        main_menu.add(menu_option_2);
        main_menu.add(menu_option_3);
        main_menu.add(menu_option_4);

        main_menubar.add(main_menu);
        main_frame.setJMenuBar(main_menubar);

//        // Ustawienie układu GridBagLayout
//        main_frame.setLayout(new GridBagLayout());
//        GridBagConstraints new_grid = new GridBagConstraints();
//        new_grid.gridx = 0;
//        new_grid.gridy = 0;
//        new_grid.anchor = GridBagConstraints.CENTER;
//        //new_grid.insets = new Insets(10, 10, 10, 10); // Dodanie marginesów
//
//        // Panel symulacji
//        sim_panel = new SimulationPanel();
//        sim_panel.setPreferredSize(new Dimension(panel_width, panel_height));
//        main_frame.add(sim_panel, new_grid);
//
//        // Tworzenie JLabel
//        JLabel label = new JLabel("Witaj w moim programie!", JLabel.LEFT);
//
//        // Dodanie JLabel do głównego okna, ustawienie constraints
//        new_grid.gridx = 0;
//        new_grid.gridy = 1; // Umieszczenie poniżej panelu symulacji
//        new_grid.anchor = GridBagConstraints.WEST; // Ustawienie wyrównania do lewej
//        main_frame.add(label, new_grid);

        // Ustawienie układu BorderLayout
        main_frame.setLayout(new BorderLayout());

        // Puste panele jako marginesy
        main_frame.add(Box.createVerticalStrut(200), BorderLayout.NORTH);
        main_frame.add(Box.createVerticalStrut(200), BorderLayout.SOUTH);
        //main_frame.add(Box.createHorizontalStrut(600), BorderLayout.WEST);
        //.add(Box.createHorizontalStrut(670), BorderLayout.EAST);

        // Panel opcji symulacji
        options_panel = new OptionsPanel();
        options_panel.setPreferredSize(new Dimension(670, panel_height));
        main_frame.add(options_panel, BorderLayout.WEST);

        // Panel symulacji
        sim_panel = new SimulationPanel();
        sim_panel.setPreferredSize(new Dimension(panel_width, panel_height));
        main_frame.add(sim_panel, BorderLayout.CENTER);

        // Panel opcji sygnalziacji świetlnej
        streetlights_panel = new StreetlightsPanel();
        streetlights_panel.setPreferredSize(new Dimension(670, panel_height));
        main_frame.add(streetlights_panel, BorderLayout.EAST);

        // Obsługa przycisku START
        options_panel.getStartSimButton().addActionListener(e -> {
            sim_panel.simulation_time = options_panel.getSimTime() * 60000;
            Map<String, Integer> cars_map = options_panel.getCarsDataMap();
            sim_panel.startSimulation(cars_map);
        });

        // Obłsuga przycisku STOP/WZNÓW
        options_panel.getStopSimButton().addActionListener(e -> {
            // Wstrzymanie symulacji
            if (!sim_panel.is_sim_paused) {
                sim_panel.is_sim_paused = true;
                options_panel.getStopSimButton().setText("WZNÓW");
                sim_panel.pause_start_time = System.currentTimeMillis(); // Zapisanie czasu rozpoczęcia pauzy
                sim_panel.sim_timer.stop();
                sim_panel.generator_timer.stop();
            } else {
                // Wznowienie symulacji
                sim_panel.is_sim_paused = false;
                options_panel.getStopSimButton().setText("STOP");
                sim_panel.pause_time += System.currentTimeMillis() - sim_panel.pause_start_time; // Dodanie czasu pauzy
                sim_panel.pause_start_time = 0; // Zapisanie czasu rozpoczęcia pauzy
                sim_panel.sim_timer.start();
                sim_panel.generator_timer.start();
            }
        });

        // Obsługa przycisku RESET
        options_panel.getResetSimButton().addActionListener(e -> {
            options_panel.Cars_Data_Map.clear(); // Czyszczenie mapy Cars_Data_Map
            sim_panel.resetSimulation(); // reset symulacji
            options_panel.getStopSimButton().setText("STOP");
            System.out.println("Symulacja została zresetowana.");
        });

        // Obsługa przycisku WYCZYŚĆ
        options_panel.getClearButton().addActionListener(e -> {
            options_panel.clearTextFields(); // Czyszczenie pól tekstowych
        });

        // Obsługa comboboxa do zmiany prędkości upływu czasu
        options_panel.getComboTimeSpeed().addActionListener(e -> {
            // Wywołujemy updateTimeSpeed z SimulationPanel
            int newTimeSpeed = options_panel.getTimeSpeedValue();  // Pobieramy wartość z combo_box
            sim_panel.updateTimeSpeed(newTimeSpeed);  // Wywołujemy metodę aktualizującą prędkość
        });

        // Wyświetlenie okna
        main_frame.setVisible(true);
    }
}