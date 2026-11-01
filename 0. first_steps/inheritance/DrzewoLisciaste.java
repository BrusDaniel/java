public class DrzewoLisciaste extends Drzewo{
    private int ksztaltLiscia;
    public DrzewoLisciaste (boolean wiecznieZielone, int wysokosc, String przekrojDzrewa, int ksztaltLiscia) {
        super(wiecznieZielone, wysokosc, przekrojDzrewa);
        this.ksztaltLiscia=ksztaltLiscia;
    }
    public String toString(){
        return super.toString()+"\nKsztalt liscia: "+ ksztaltLiscia;
    }
}
