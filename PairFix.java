public class PairFix<E,T> {

    private E e;
    private T t;
    public PairFix(E e, T t){
        this.e=e;
        this.t=t;
    }

    public PairFix(PairFix<E,T> pairFix){
        this.e=pairFix.getKey();
        this.t=pairFix.getValue();
    }

    public E getKey(){
        return this.e;
    }

    public T getValue(){
        return this.t;
    }
}
