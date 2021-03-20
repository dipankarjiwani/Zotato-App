public class Food {
    private String name;
    private float price;
    private String category;
    private float offer;
    final private int id;
    private int qty;
    private Restau rest;
    public Food(int id, String name,Float price, String category, int qty, float offer,Restau rest){
        this.id=id;
        this.name=name;
        this.price=price;
        this.category=category;
        this.qty=qty;
        this.offer=offer;
        this.rest=rest;
    }

    public Restau getRest() {
        return rest;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getOffer() {
        return offer;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
