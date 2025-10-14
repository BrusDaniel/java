public class Main {
    public static void main (String args[]){
        Hero h1 = new Hero ("Jan", 50);
        Leader l1 = new Leader("Eagle", 20);
        Hero h3 =(Hero)l1;

        h1.sayHelloTo(h3);
        h3.sayHelloTo(h1);

        Squad s = new Squad("DELTA");

        s.setLeader(l1);

        try {
            s.addHero(h1);
            s.addHero(h3);
        } catch (TooManyHeroesExceptio e) {
            e.printStackTrace();
        }

        System.out.println(s);
    }
}
