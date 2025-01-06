public class Checkpoint {

        private int x, y; // Współrzędne
        private int widht, height; // Wymiary
        private String type; // Typ

        public Checkpoint(int ch_x, int ch_y, int ch_widht, int ch_height, String ch_type) {
            this.x = ch_x;
            this.y = ch_y;
            this.widht = ch_widht;
            this.height = ch_height;
            this.type = ch_type;
        }

        public int getX_ch() {
            return x;
        }

        public int getY_ch() {
            return y;
        }

        public int getWidht_ch() {
            return widht;
        }

        public int getHeight_ch() {
            return height;
        }

        public String getType_ch() {
            return type;
        }

}
