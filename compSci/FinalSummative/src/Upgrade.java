public class Upgrade {
    public static int SINGLE = 0;
    public static int MULT = 1;
    public String name;
    public int type;
    public int price;
    public double multiplier;
    public int count;
    
    public Upgrade(String name, int price, double multiplier) {
        this.name = name;
        this.price = price;
        this.count = 0;
        this.type = MULT;
    }
    public Upgrade(String name, int price) {
        this.name = name;
        this.price = price;
        this.count = 0;
        this.type = SINGLE;
    }

    public void update() {
        if (type == MULT) {
            price = (int)(price*multiplier);
        }
        count++;
    }
}
