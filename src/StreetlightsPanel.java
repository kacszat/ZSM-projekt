import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StreetlightsPanel extends JPanel {

    JToggleButton toggle_streetlights;
    JLabel label_in1, label_in2, label_in3, label_in4, label_in5, label_in6, label_in7,
            label_null1, label_null2, label_null3;
    JTextField tf_ziel_dwu1, tf_ziel_dwu2, tf_ewaku_dwu1, tf_ewaku_dwu2,
            tf_ziel_trzy1, tf_ziel_trzy2, tf_ziel_trzy3, tf_ewaku_trzy1, tf_ewaku_trzy2, tf_ewaku_trzy3,
            tf_ziel_czt1, tf_ziel_czt2, tf_ewaku_czt1, tf_ewaku_czt2;

    public static Map<String, Integer> Streetlights_Map;

    public static JButton bt_get_streetlights;
    public static JComboBox<String> combo_streetlights_type;
    public String streetlights_type[] = {"Dwufazowa", "Trójfazowa", "Czterofazowa"};

    public static boolean is_streetlight_on = false;

    StreetlightsPanel(){
        // Ustawienia wyglądu panelu
        setLayout(new GridBagLayout());
        setBackground(Color.GRAY);

        Streetlights_Map = new HashMap<String, Integer>();

        label_null1 = new JLabel("");
        label_null2 = new JLabel("");
        label_null3 = new JLabel("");
        label_in1 = new JLabel("Sygnalizacja świetlna");
        label_in2 = new JLabel("Typ sygnalizacji");
        label_in3 = new JLabel("Czas sygnału zielonego:");
        label_in4 = new JLabel("Czas ewakuacji:");
        label_in5 = new JLabel("Północ - Południe");
        label_in6 = new JLabel("Wschód - Zachód");
        label_in7 = new JLabel("Relacja:");

        label_in1.setForeground(Color.WHITE);
        label_in2.setForeground(Color.WHITE);
        label_in3.setForeground(Color.WHITE);
        label_in4.setForeground(Color.WHITE);
        label_in5.setForeground(Color.WHITE);
        label_in6.setForeground(Color.WHITE);
        label_in7.setForeground(Color.WHITE);

        label_in1.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_in2.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_in3.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_in4.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_in5.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_in6.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_in7.setFont(new Font("Calibri", Font.PLAIN, 20));

        bt_get_streetlights = new JButton("WCZYTAJ");
        toggle_streetlights = new JToggleButton("WŁĄCZ");
        combo_streetlights_type = new JComboBox<>(streetlights_type);

        tf_ziel_dwu1 = new JTextField();
        tf_ziel_dwu2 = new JTextField();
        tf_ewaku_dwu1 = new JTextField();
        tf_ewaku_dwu2 = new JTextField();
        tf_ziel_trzy1 = new JTextField();
        tf_ziel_trzy2 = new JTextField();
        tf_ziel_trzy3 = new JTextField();
        tf_ewaku_trzy1 = new JTextField();
        tf_ewaku_trzy2 = new JTextField();
        tf_ewaku_trzy3 = new JTextField();
        tf_ziel_czt1 = new JTextField();
        tf_ziel_czt2 = new JTextField();
        tf_ewaku_czt1 = new JTextField();
        tf_ewaku_czt2 = new JTextField();

        // Ustawienie widoczności wszystkich obiektów na false
        setAllInvisible();

        // Dodanie ActionListener do przycisku toggle
        toggle_streetlights.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (toggle_streetlights.isSelected()) {
                    toggle_streetlights.setText("WYŁĄCZ");
                    combo_streetlights_type.setSelectedIndex(0);
                    setVisibility(true, label_in2, combo_streetlights_type);
                    setVisibility(true, tf_ziel_dwu1, tf_ziel_dwu2, tf_ewaku_dwu1, tf_ewaku_dwu2, label_in5, label_in6);
                    setVisibility(true, label_in3, label_in4, label_in7);
                    setVisibility(true, bt_get_streetlights);
                    is_streetlight_on = true;
                } else {
                    toggle_streetlights.setText("WŁĄCZ");
                    setAllInvisible();
                    is_streetlight_on = false;
                }
                // Odświeżenie panelu, aby zmiana widoczności była natychmiastowa
                revalidate();
                repaint();
            }
        });

        // Wybór opcji z Comboboxa
        combo_streetlights_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) combo_streetlights_type.getSelectedItem(); // Rzutowanie na String
                switch (selectedItem) {
                    case "Dwufazowa":
                        setVisibility(true, tf_ziel_dwu1, tf_ziel_dwu2, tf_ewaku_dwu1, tf_ewaku_dwu2, label_in5, label_in6);
                        setVisibility(false, tf_ziel_trzy1, tf_ziel_trzy2, tf_ziel_trzy3, tf_ewaku_trzy1, tf_ewaku_trzy2, tf_ewaku_trzy3);
                        setVisibility(false, tf_ziel_czt1, tf_ziel_czt2, tf_ewaku_czt1, tf_ewaku_czt2);
                        break;
                    case "Trójfazowa":
                        setVisibility(false, tf_ziel_dwu1, tf_ziel_dwu2, tf_ewaku_dwu1, tf_ewaku_dwu2, label_in5, label_in6);
                        setVisibility(true, tf_ziel_trzy1, tf_ziel_trzy2, tf_ziel_trzy3, tf_ewaku_trzy1, tf_ewaku_trzy2, tf_ewaku_trzy3);
                        setVisibility(false, tf_ziel_czt1, tf_ziel_czt2, tf_ewaku_czt1, tf_ewaku_czt2);
                        break;
                    case "Czterofazowa":
                        setVisibility(false, tf_ziel_dwu1, tf_ziel_dwu2, tf_ewaku_dwu1, tf_ewaku_dwu2, label_in5, label_in6);
                        setVisibility(false, tf_ziel_trzy1, tf_ziel_trzy2, tf_ziel_trzy3, tf_ewaku_trzy1, tf_ewaku_trzy2, tf_ewaku_trzy3);
                        setVisibility(true, tf_ziel_czt1, tf_ziel_czt2, tf_ewaku_czt1, tf_ewaku_czt2);
                        break;
                }
                // Odświeżenie panelu, aby zmiana widoczności była natychmiastowa
                revalidate();
                repaint();
            }
        });

        // Wczytanie programu sygnalizacji
        bt_get_streetlights.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) combo_streetlights_type.getSelectedItem(); // Rzutowanie na String
                switch (selectedItem) {
                    case "Dwufazowa":
                        Streetlights_Map = new LinkedHashMap<>();
                        Streetlights_Map.put("1 north south", parseTextField(tf_ziel_dwu1));
                        Streetlights_Map.put("2 north south ewaku", parseTextField(tf_ewaku_dwu1));
                        Streetlights_Map.put("3 west east", parseTextField(tf_ziel_dwu2));
                        Streetlights_Map.put("4 west east ewaku", parseTextField(tf_ewaku_dwu2));
                        break;
                    case "Trójfazowa":
                        break;
                    case "Czterofazowa":
                        break;
                }
            }
        });

        // Konfiguracja GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 10, 10); // Marginesy między komponentami
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridwidth = 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(label_in1, gbc);
        gbc.gridx = 1;
        add(toggle_streetlights, gbc);
        gbc.gridx = 2;
        add(label_null1, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(label_in2, gbc);
        gbc.gridx = 1;
        add(combo_streetlights_type, gbc);
        gbc.gridx = 2;
        add(label_null2, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(label_in7, gbc);
        gbc.gridx = 1;
        add(label_in3, gbc);
        gbc.gridx = 2;
        add(label_in4, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(label_in5, gbc);
        gbc.gridx = 1;
        add(tf_ziel_dwu1, gbc);
        gbc.gridx = 2;
        add(tf_ewaku_dwu1, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        add(label_in6, gbc);
        gbc.gridx = 1;
        add(tf_ziel_dwu2, gbc);
        gbc.gridx = 2;
        add(tf_ewaku_dwu2, gbc);

        gbc.gridy = 5;
        gbc.gridx = 1;
        add(bt_get_streetlights, gbc);
    }

    // Obsługa comboboxa
    public static JComboBox<String> getComboStreetlightsType() {
        return combo_streetlights_type;
    }

    // Pobranie wartości prędkości upływu czasu
    public static int getStreetlightsTypeIndex() {
        return (int) combo_streetlights_type.getSelectedItem();
    }

    // Ustawienie widoczności danych komponentów
    private void setVisibility(boolean isVisible, JComponent... components) {
        for (JComponent component : components) {
            component.setVisible(isVisible);
        }
    }

    // Ustawienie widoczności wszystkich obiektów na false
    private void setAllInvisible() {
        setVisibility(false, label_in2, combo_streetlights_type);
        setVisibility(false, tf_ziel_dwu1, tf_ziel_dwu2, tf_ewaku_dwu1, tf_ewaku_dwu2, label_in5, label_in6);
        setVisibility(false, tf_ziel_trzy1, tf_ziel_trzy2, tf_ziel_trzy3, tf_ewaku_trzy1, tf_ewaku_trzy2, tf_ewaku_trzy3);
        setVisibility(false, tf_ziel_czt1, tf_ziel_czt2, tf_ewaku_czt1, tf_ewaku_czt2);
        setVisibility(false, label_in3, label_in4, label_in7);
        setVisibility(false, bt_get_streetlights);
    }

    // Metoda pomocnicza, która "parsuje" wartość z pola tekstowego i nie przyjmuje wartości mniejszych niż 0.
    private int parseTextField(JTextField textField) {
        try {
            int value = Integer.parseInt(textField.getText().trim());
            return Math.max(value, 0); // Zwraca wartość, ale nie mniejszą niż 0
        } catch (NumberFormatException e) {
            return 0; // Zwraca 0, jeśli pole jest puste lub zawiera błędne dane
        }
    }

}
