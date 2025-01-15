public class Checkpoint_Streetlight {

    private int x, y; // Współrzędne
    private int widht, height; // Wymiary
    private String type, color; // Typ

    public Checkpoint_Streetlight(int st_x, int st_y, int st_widht, int st_height, String st_type, String st_color) {
        this.x = st_x;
        this.y = st_y;
        this.widht = st_widht;
        this.height = st_height;
        this.type = st_type;
        this.color = st_color;
    }

    public int getX_st() {
        return x;
    }

    public int getY_st() {
        return y;
    }

    public int getWidht_st() {
        return widht;
    }

    public int getHeight_st() {
        return height;
    }

    public String getType_st() {
        return type;
    }

    public String getColor_st() {
        return color;
    }

}
