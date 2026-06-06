package cs.sbs.web.model;

public class MenuItem {
    private String name;
    private int price;

    public MenuItem(String name, int price) {  // 注意：不要写 price: 8
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}