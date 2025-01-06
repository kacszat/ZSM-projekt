import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class OptionsPanel extends JPanel {

    JLabel label_info, label_info2, label_info3, label_info4, label_info5,
            label_info6, label_info7, label_info8, label_info9;
    public static JLabel label_timer;
    JTextField tb_south_left, tb_south_straight, tb_south_right,
            tb_east_left, tb_east_straight, tb_east_right,
            tb_north_left, tb_north_straight, tb_north_right,
            tb_west_left, tb_west_straight, tb_west_right,
            tb_sim_time;
    public static Map<String, Integer> Cars_Data_Map;
    public static JButton bt_start_simulation, bt_stop_simulation, bt_reset_simulation,
                        bt_clear_tb;

    public OptionsPanel() {
        // Ustawienia wyglądu panelu
        setLayout(new GridBagLayout());
        setBackground(Color.GRAY);

        // Konfiguracja GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 10, 50); // Marginesy między komponentami
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Tworzenie etykiet i pól tekstowych
        label_info = new JLabel("Liczba występujących pojazdów i ich relację");
        label_info2 = new JLabel("Wlot południowy:");
        label_info3 = new JLabel("Wlot wschodni:");
        label_info4 = new JLabel("Wlot północny:");
        label_info5 = new JLabel("Wlot zachodni:");
        label_info6 = new JLabel("Czas symulacji (w min):");
        label_info7 = new JLabel("Opcje symulacji:");
        label_info8 = new JLabel("Czas trwania symulacji:");
        label_info9 = new JLabel("Prędkość upływu czasu:");
        label_timer = new JLabel("00:00:00");
        label_info.setForeground(Color.WHITE);
        label_info2.setForeground(Color.WHITE);
        label_info3.setForeground(Color.WHITE);
        label_info4.setForeground(Color.WHITE);
        label_info5.setForeground(Color.WHITE);
        label_info6.setForeground(Color.WHITE);
        label_info7.setForeground(Color.WHITE);
        label_info8.setForeground(Color.WHITE);
        label_info9.setForeground(Color.WHITE);
        label_timer.setForeground(Color.WHITE);
        label_info.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_info2.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_info3.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_info4.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_info5.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_info6.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_info7.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_info8.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_info9.setFont(new Font("Calibri", Font.PLAIN, 20));
        label_timer.setFont(new Font("Calibri", Font.PLAIN, 20));


        tb_south_left = new JTextField(10);
        tb_south_straight = new JTextField(10);
        tb_south_right = new JTextField(10);
        tb_east_left = new JTextField(10);
        tb_east_straight = new JTextField(10);
        tb_east_right = new JTextField(10);
        tb_north_left = new JTextField(10);
        tb_north_straight = new JTextField(10);
        tb_north_right = new JTextField(10);
        tb_west_left = new JTextField(10);
        tb_west_straight = new JTextField(10);
        tb_west_right = new JTextField(10);
        tb_sim_time = new JTextField(10);

        bt_start_simulation = new JButton("START");
        bt_stop_simulation = new JButton("STOP");
        bt_reset_simulation = new JButton("RESET");
        bt_clear_tb = new JButton("WYCZYŚĆ");

        // Dodawanie komponentów w odpowiednich miejscach
        gbc.gridwidth = 3;
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(label_info, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(label_info2, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(tb_south_left, gbc);
        gbc.gridx = 1;
        add(tb_south_straight, gbc);
        gbc.gridx = 2;
        add(tb_south_right, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(label_info3, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        add(tb_east_left, gbc);
        gbc.gridx = 1;
        add(tb_east_straight, gbc);
        gbc.gridx = 2;
        add(tb_east_right, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        add(label_info4, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        add(tb_north_left, gbc);
        gbc.gridx = 1;
        add(tb_north_straight, gbc);
        gbc.gridx = 2;
        add(tb_north_right, gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        add(label_info5, gbc);

        gbc.gridy = 8;
        gbc.gridx = 0;
        add(tb_west_left, gbc);
        gbc.gridx = 1;
        add(tb_west_straight, gbc);
        gbc.gridx = 2;
        add(tb_west_right, gbc);

        gbc.gridy = 9;
        gbc.gridx = 0;
        add(label_info6, gbc);
        gbc.gridx = 1;
        add(tb_sim_time, gbc);

        gbc.gridy = 10;
        gbc.gridx = 0;
        add(label_info7, gbc);

        gbc.gridy = 11;
        gbc.gridx = 0;
        add(bt_start_simulation, gbc);
        gbc.gridx = 1;
        add(bt_stop_simulation, gbc);
        gbc.gridx = 2;
        add(bt_reset_simulation, gbc);

        gbc.gridy = 12;
        gbc.gridx = 2;
        add(bt_clear_tb, gbc);

        gbc.gridy = 13;
        gbc.gridx = 0;
        add(label_info8, gbc);
        gbc.gridx = 1;
        add(label_timer, gbc);

        gbc.gridy = 14;
        gbc.gridx = 0;
        add(label_info9, gbc);
    }

     //Mapa, gdzie kluczem jest opis pola, a wartością liczba pojazdów.
    public Map<String, Integer> getCarsDataMap() {
        Cars_Data_Map = new HashMap<>();
        Cars_Data_Map.put("south left", parseTextField(tb_south_left));
        Cars_Data_Map.put("south straight", parseTextField(tb_south_straight));
        Cars_Data_Map.put("south right", parseTextField(tb_south_right));
        Cars_Data_Map.put("east left", parseTextField(tb_east_left));
        Cars_Data_Map.put("east straight", parseTextField(tb_east_straight));
        Cars_Data_Map.put("east right", parseTextField(tb_east_right));
        Cars_Data_Map.put("north left", parseTextField(tb_north_left));
        Cars_Data_Map.put("north straight", parseTextField(tb_north_straight));
        Cars_Data_Map.put("north right", parseTextField(tb_north_right));
        Cars_Data_Map.put("west left", parseTextField(tb_west_left));
        Cars_Data_Map.put("west straight", parseTextField(tb_west_straight));
        Cars_Data_Map.put("west right", parseTextField(tb_west_right));
        return Cars_Data_Map;
    }

    //Metoda pomocnicza, która "parsuje" wartość z pola tekstowego.
    private int parseTextField(JTextField textField) {
        try {
            return Integer.parseInt(textField.getText().trim());
        } catch (NumberFormatException e) {
            return 0; // Zwraca 0, jeśli pole jest puste lub zawiera błędne dane
        }
    }

    // Funkcję obsługujące przyciski
    public JButton getStartSimButton() {
        return bt_start_simulation;
    }

    public JButton getStopSimButton() {
        return bt_stop_simulation;
    }

    public JButton getResetSimButton() {
        return bt_reset_simulation;
    }

    public JButton getClearButton() {
        return bt_clear_tb;
    }

    // Pobranie czasu symulacji
    public long getSimTime() {
        return parseTextField(tb_sim_time);
    }

    // Wyczuszczenie zapisów w TextFieldach
    public void clearTextFields() {
        tb_south_left.setText("");
        tb_south_straight.setText("");
        tb_south_right.setText("");
        tb_east_left.setText("");
        tb_east_straight.setText("");
        tb_east_right.setText("");
        tb_north_left.setText("");
        tb_north_straight.setText("");
        tb_north_right.setText("");
        tb_west_left.setText("");
        tb_west_straight.setText("");
        tb_west_right.setText("");
        tb_sim_time.setText("");
    }

}
