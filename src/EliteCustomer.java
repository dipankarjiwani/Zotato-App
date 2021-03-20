public class EliteCustomer extends Customer {
    public EliteCustomer(String name, Zotato app,String add){
        super(name,app,add);
        delCharges=0;
    }

    @Override
    public float custDisc(float price) {
        if(price>200){
            return 50;
        }
        else{
            return 0;
        }
    }
}
