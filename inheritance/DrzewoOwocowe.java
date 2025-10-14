public class DrzewoOwocowe extends DrzewoLisciaste {
    private String nazwaOwoca;
    public DrzewoOwocowe (boolean wiecznieZielone, int wysokosc, String przekrojDzrewa,
                          int ksztaltLiscia,String nazwaOwoca) {
        super(wiecznieZielone, wysokosc, przekrojDzrewa, ksztaltLiscia);
        this.nazwaOwoca=nazwaOwoca;
    }
    public String toString(){
        return super.toString()+"\nNazwa owoca: "+ nazwaOwoca;
    }

}
