import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Zotato {
    //private static HashMap<Integer,Food> items=new HashMap<Integer, Food>(0);
    private ArrayList<Restau> restaurants;
    private ArrayList<Customer> customers;
    private int global;//to give ids
    private float dCharge;
    private float sCharge;

    public float getdCharge() {
        return dCharge;
    }

    public float getsCharge() {
        return sCharge;
    }

    public void setdCharge(float dCharge) {
        this.dCharge = dCharge;
    }

    public void setsCharge(float sCharge) {
        this.sCharge = sCharge;
    }

    public int getGlobal() {
        return global;
    }

    public void setGlobal(int global) {
        this.global = global;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Restau> getRestaurants() {
        return restaurants;
    }

    public Zotato(){
        global=0;
        restaurants=new ArrayList<Restau>(0);
        customers=new ArrayList<Customer>(0);
    }
    public void selectRest(){

    }


    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        Zotato app=new Zotato();
        app.restaurants.add(new Authentic("Shah",app,1,"Delhi"));
        app.restaurants.add(new Restau("Ravi",app,"Pune"));
        app.restaurants.add(new Authentic("The Chineese",app,2,"Mumbai"));
        app.restaurants.add(new FastFood("Wang's",app,3,"Delhi"));
        app.restaurants.add(new Restau("Paradise",app,"Jaipur"));
        app.customers.add(new EliteCustomer("Ram",app,"Delhi"));
        app.customers.add(new EliteCustomer("Sam",app,"Pune"));
        app.customers.add(new SpecialCustomer("Tim",app,"Mumbai"));
        app.customers.add(new Customer("Kim",app,"Delhi"));
        app.customers.add(new EliteCustomer("Jim",app,"Jaipur"));
        int c;
        do{
            System.out.println("Welcome to Zotato:");
            System.out.println("1) Enter as Restaurant Owner: ");
            System.out.println("2) Enter as Customer");
            System.out.println("3) Check User Details");
            System.out.println("4) Company Account Details");
            System.out.println("5) Exit");
            c=in.nextInt();//call apt
            if(c==1){
                System.out.println("Choose Restaurant:");
                for(int i=0;i<app.restaurants.size();i++){
                    System.out.println((i+1)+") "+app.restaurants.get(i).getName());
                }
                int k=in.nextInt();
                app.restaurants.get(k-1).disMenu();//takes control to the restaurant
            }
            else if(c==2){
                System.out.println("Choose Customer:");
                for(int i=0;i<app.customers.size();i++){
                    System.out.println((i+1)+") "+app.customers.get(i).getName());
                }
                int k=in.nextInt();
                app.customers.get(k-1).disMenu();//takes control to the restaurant
            }
            else if(c==3){
                System.out.println("1) Customers list:");
                System.out.println("2) Restaurants list:");
                int f=in.nextInt();
                if(f==1){
                    for(int i=0;i<app.customers.size();i++){
                        System.out.println((i+1)+") "+app.customers.get(i).getName());
                    }
                    int m=in.nextInt();
                    app.customers.get(m-1).disDetails();
                }
                else{
                    for(int i=0;i<app.restaurants.size();i++){
                        System.out.println((i+1)+") "+app.restaurants.get(i).getName());
                    }
                    int m=in.nextInt();
                    app.restaurants.get(m-1).disDetails();
                }
            }
            else if(c==4){
                System.out.println("Total service Charge: "+app.sCharge);
                System.out.println("Total Delivery Charge: "+app.dCharge);
            }
            else{

            }
        }while(c!=5);
    }
}
