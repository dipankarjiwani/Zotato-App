import java.util.HashMap;

public class FastFood extends Restau {
    final private float dobl;
    public FastFood(String name, Zotato app, float dobl, String add){
        super(name,app,add);
        this.dobl=dobl;
    }

    @Override
    public float giveDOBl() {
        return dobl;
    }

    @Override
    public float modReward(float price,Restau rest) {
        float t=(float)(((int)(price/150))*10);
        reward=(int)(reward+t);
        return t;
    }
}
