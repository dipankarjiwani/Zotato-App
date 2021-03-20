import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Restau implements User {
    protected int global;
    final protected String name;
    protected ArrayList<Food> itemspres;
    final protected String add;
    protected int reward;
    //protected HashMap<Integer,Integer> useful=new HashMap<Integer, Integer>(0);//key is id, value is array index
    protected HashMap<Integer,Food> its=new HashMap<Integer, Food>(0);
    final protected Zotato app;
    Scanner in=new Scanner(System.in);
    public Restau(String name,Zotato app,String add){
        this.global=global;
        this.name=name;
        itemspres=new ArrayList<Food>(0);
        //qty=new ArrayList<Integer>(0);
        reward=0;
        this.app=app;
        this.add=add;
        }
     @Override
     public void showName(User obj){
        System.out.println("Welcome "+name);
     }
    @Override
    public void disDetails() {
        System.out.print(name+" - ");
        System.out.println(this.getClass());
        System.out.println(add);
        System.out.println(reward);
    }

    @Override
        public void disMenu(){
        int k;
        do {
            showName(this);
            System.out.println("1) Add Item");
            System.out.println("2) Edit Item");
            System.out.println("3) Print Rewards");
            System.out.println("4) Discount on Bill Value");
            System.out.println("5) Exit");
            k = in.nextInt();
            if (k == 1) {
                System.out.println("Food Name:");
                String name = in.next();
                System.out.println("Price");
                float price = in.nextFloat();
                System.out.println("Quantity");
                int qty = in.nextInt();
                System.out.println("Category:");
                String category = in.next();
                System.out.println("Offer:");
                int offer = in.nextInt();
                int a = app.getGlobal();
                a++;
                app.setGlobal(a);
                Food foodnow=new Food(a, name, price, category, qty, offer,this);
                addFood(foodnow);
                its.put(a,foodnow);
                //useful.put(a, itemspres.size() - 1);
                System.out.println(foodnow.getId()+" "+foodnow.getName()+" "+foodnow.getPrice()+" "+foodnow.getQty()+" "+foodnow.getCategory()+" "+ foodnow.getOffer()+" off");//details of food
            } else if (k == 2) {
                editItem();
            }
            else if(k==3){
                printReward();
            }
            else if(k==4){
                System.out.println(giveDOBl());
            }
            else{}
        }while(k!=5);
        }
        @Override
        public float modReward(float price, Restau rest){
            float t=(float)(((int)(price/100))*5);
            reward=(int)(reward+t);
            return t;
        }
    public void addFood(Food item){//query1
        itemspres.add(item);
        }
    public void editItem(){//query2
        System.out.println("Choose an item by code: ");
        for(int i=0;i<itemspres.size();i++){
            Food temp=itemspres.get(i);
            System.out.println(temp.getId()+" "+temp.getName()+" "+ temp.getPrice()+" "+temp.getQty()+" "+temp.getOffer()+" off "+ temp.getCategory());
        }
        int it=in.nextInt();//we take the index
        //int index=useful.get(it);
        //Food temp=itemspres.get(index);//Verify if hashmap works correct,i.e putting id gives index
        Food temp=its.get(it);
        System.out.println("Choose an attribute to edit:");
        System.out.println("1) Name");
        System.out.println("2) Price");
        System.out.println("3) Quantity ");
        System.out.println("4) Category");
        System.out.println("5) Offer");
        int c=in.nextInt();
        if(c==1){
            System.out.println("New Name:");
            String name=in.next();
            temp.setName(name);
        }
        else if(c==2){
            System.out.println("New Price");
            int p=in.nextInt();
            temp.setPrice(p);
        }
        else if(c==3){
            System.out.println("New Quantity");
            int q=in.nextInt();
            temp.setQty(q);
        }
        else if(c==4){
            System.out.println("New Category:");
            String cat=in.next();
            temp.setCategory(cat);
        }
        else{
            System.out.println("New Offer");
            int off=in.nextInt();
            temp.setOffer(off);
        }
    }
    public void printReward(){
        System.out.println("Reward points: "+reward);
    }
    public float giveDOBl(){
        return 0;
    }





    public String getName() {
        return name;
    }

    public ArrayList<Food> getItemspres() {
        return itemspres;
    }



    public int getReward() {
        return reward;
    }

}
