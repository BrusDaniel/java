public class DrzewoIglaste extends Drzewo {
    private int iloscIgiel;
    private double dlugoscSzyszki;

    public DrzewoIglaste(boolean wiecznieZielone, int wysokosc, String przekrojDzrewa,
                         int iloscIgiel, double dlugoscSzyszki) {
        super(wiecznieZielone, wysokosc, przekrojDzrewa);
        this.dlugoscSzyszki=dlugoscSzyszki;
        this.iloscIgiel=iloscIgiel;
    }
    public String toString() {
        return super.toString()+"\nDlugosc Szyszki: "+ dlugoscSzyszki+" cm"+"\nIlosc igiel: "+iloscIgiel+" sztuk";
    }
}
