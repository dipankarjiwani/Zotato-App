import java.util.HashMap;

public class Authentic extends Restau {
    final private float dobl;//discount bill value
    public Authentic(String name, Zotato app,float dobl,String add){
        super(name,app,add);
        this.dobl=dobl;
    }

    @Override
    public float modReward(float price,Restau rest) {
        float t=(float)(((int)(price/200))*25);
        reward=(int)(reward+t);
        return t;
    }

    @Override
    public float giveDOBl() {
        return dobl;
    }
}
