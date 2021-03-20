import java.util.ArrayList;
import java.util.Scanner;

public class Customer implements User {
    final protected String Name;
    final protected String address;
    protected float acc_bal;
    protected float reward;
    protected ArrayList<Food> orders;
    final protected Zotato app;
    protected int delCharges;//subclasses modify it,hence not final
    Scanner in=new Scanner(System.in);
    public Customer(String name,Zotato app,String address){
        this.Name=name;
        this.app=app;
        acc_bal=1000;
        reward=0;
        delCharges=40;
        this.address=address;
        orders=new ArrayList<Food>(0);
    }

    public float calcTotal(ArrayList<Food> cart,Restau rest){
        float tot=0;
        for(int i=0;i<cart.size();i++) {
            float p=((1 - (cart.get(i).getOffer() / 100)) * (cart.get(i).getPrice() * cart.get(i).getQty()));
            //rest.itemspres.get(i);
            tot = tot + p;
            //float t=modReward(p,rest);
        }
        float p=(tot*(rest.giveDOBl()/100));
        tot=tot-p;
        if(rest instanceof Authentic){
            if(tot>100){
                tot=tot-50;//50 rs off on all orders after discount on bill extra for authentic restaurants
            }
        }
        tot=tot-custDisc(tot);//customer discount calculated after restaurant discount
        tot=tot+delCharges;
        float t=modReward(tot,rest);//modifies own reward too
        return tot;
    }

    public String getName() {
        return Name;
    }

    public float custDisc(float price){
        return 0;
    }
    public int getDelCharges(){
        return  delCharges;
    }
    @Override
    public void showName(User obj){
        System.out.println("Welcome "+getName());
    }
    @Override
    public float modReward(float price,Restau rest){//to be put in interface
            float t=rest.modReward(price,rest);//calling the modReward of restaurant and modifying values there
            reward=(float) (int)(reward+t);
            return t;
    }

    @Override
    public void disDetails() {
        System.out.print(Name +" - ");
        System.out.println(this.getClass());
        System.out.println(address);
        System.out.println(acc_bal);
    }

    @Override
    public void disMenu() {
        ArrayList<Food> cart=new ArrayList<Food>(0);//since cart is declared outisde,it can add from multiple restraunts
        Restau pres=app.getRestaurants().get(0);//default is restaurant one,but declared outside since restaurant is only selected once and not changed
        int count=0;//to ensure restauarant selecting is provided only once
        int k;
        do {
            showName(this);
            System.out.println("1) Select Restaurant");
            System.out.println("2) Checkout Cart");
            System.out.println("3) Rewards won");
            System.out.println("4) Print recent Orders");
            System.out.println("5) Exit");
            k = in.nextInt();
            if (k == 1 && count==0) {
                count=1;//to ensure option 1 is accessed only once,
                System.out.println("Choose Restaurant:");
                for(int i=0;i<app.getRestaurants().size();i++){
                    System.out.println( i+1+") "+ app.getRestaurants().get(i).getName());
                }
                int l=in.nextInt();
                pres=app.getRestaurants().get(l-1);
                for(int i=0;i<pres.itemspres.size();i++){
                    Food now=pres.itemspres.get(i);
                    System.out.println(now.getId()+" "+pres.getName()+" "+now.getName()+" "+now.getPrice()+" "+now.getQty()+" "+now.getOffer()+" off"+now.getCategory());
                }
                int it=in.nextInt();//we take the id
                //int index=pres.useful.get(it);
                //Food temp=pres.itemspres.get(index);
                Food temp=pres.its.get(it);
                System.out.println("Enter Item Quantity:");
                int qt=in.nextInt();
                Food foodtoCart=new Food(temp.getId(),pres.getName()+" "+temp.getName(),temp.getPrice(),temp.getCategory(),qt,temp.getOffer(),pres);
                orders.add(foodtoCart);
                cart.add(foodtoCart);
                System.out.println("Items Added to Cart");
            } else if (k == 2) {
                System.out.println("Items in Cart:");
                for(int i=0;i<cart.size();i++){
                    Food temp=cart.get(i);
                    //int index=pres.useful.get(temp.getId());//get the index of the food item from hashmap with the id
                    //int qtyy=pres.itemspres.get(index).getQty();//get the current quantity available with present restaurant
                    //pres.itemspres.get(index).setQty(qtyy-temp.getQty());//set the new quantity after subtracting qty ordered
                    System.out.println(temp.getId()+" "+temp.getName()+" "+temp.getPrice()+" "+temp.getQty()+" "+temp.getOffer()+" off");
                }
                System.out.println("Delivery Charges: "+ getDelCharges());
                float totnow=calcTotal(cart,pres);
                System.out.println("Total Order Value: INR "+totnow);
                System.out.println("Pres 1 to Proceed checkout");
                int z=in.nextInt();
                while(z!=1){
                    System.out.println("Pres 1 to Proceed checkout");
                }
                if(reward>=totnow){//following conditions to deduct from rewards first
                    reward=reward-totnow;
                }
                else if(reward>0){
                    acc_bal=acc_bal-(totnow-reward);
                    reward=0;
                }
                else{
                    acc_bal=acc_bal-totnow;
                }
                float x=app.getdCharge();
                app.setdCharge(x+delCharges);//adding the delivery charges in zotato account
                float y=app.getsCharge();
                float o=((float)1/100)*totnow;
                app.setsCharge(y+o);//adding 1% of total amount to zotato account
                System.out.println(cart.size()+" items successfully purchased for INR "+totnow);
            }
            else if(k==3){
                System.out.println(reward);
            }
            else if(k==4){
                for(int i=orders.size()-1;i>=0 && 1>orders.size()-11;i--){
                    Food now=orders.get(i);
                    System.out.println("From "+now.getName()+" was brought, with Quantity: "+now.getQty()+" for price "+now.getPrice()+" with delivery charges "+ getDelCharges());
                }
            }
            else{}
        }while(k!=5);
    }
}
