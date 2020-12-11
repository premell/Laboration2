import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarContainer implements Iterable{

    private ArrayList<PairFix<Car,String>> carAndImagePaths = new ArrayList<>();

    @Override
    public Iterator<PairFix<Car,String>> iterator() {
        return carAndImagePaths.iterator();
    }

    public void add(PairFix<Car,String> pair){
        carAndImagePaths.add(pair);
    }
}
