public class SpecialCustomer extends Customer {
    public SpecialCustomer(String name, Zotato app,String add){
        super(name,app,add);
        delCharges=20;
    }

    @Override
    public float custDisc(float price) {
        if(price>200){
            return 20;
        }
        else{
            return 0;
        }
    }
}
