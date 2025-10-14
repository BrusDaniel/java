
public class Kontener<T, S> {
    public T value;
    public S value1;

    public Kontener(T value, S value1) {
        this.value = value;
        this.value1 = value1;
    }

        public void wypisz () {
            System.out.println(value+" "+value1);
        }


}
