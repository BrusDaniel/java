public class Drzewo {
    private boolean wiecznieZielone;
    private int wysokosc;
    String przekrojDzrewa;
    public Drzewo (boolean wiecznieZielone, int wysokosc,  String przekrojDzrewa) {
        this.wiecznieZielone=wiecznieZielone;
        this.wysokosc=wysokosc;
        this.przekrojDzrewa=przekrojDzrewa;
    }
    public String toString(){
        String wieczn;
        if (wiecznieZielone) wieczn="tak"; else wieczn="nie";
        return "czy jest wiecznie zielone? "+ wieczn+"\n wysokosc: "+wysokosc+
                "\nprzekroj: "+przekrojDzrewa;
    }

}
