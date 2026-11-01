import java.util.ArrayList;
import java.util.List;

public class Squad  {
    private String name;
    private ArrayList<Hero> heroesList = new ArrayList<>();
    private Leader leader;

    public Squad (String name){
        this.name=name;
    }
    public void setLeader (Leader leader) {
        this.leader=leader;
    }
    public void addHero (Hero hero) throws TooManyHeroesExceptio {
        if (heroesList.size()>=1) {
            throw new TooManyHeroesExceptio("Too many heroes!!!");
        }
        heroesList.add(hero);
    }
    public String toString (){
        return this.name+", leader: "+leader.getName()+", heroes: "+heroesList.get(0).getName();
    }
}
