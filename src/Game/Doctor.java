package Game;

class Doctor extends Hero {

    protected int health = 110;
    protected String name;
    protected int damage = 0;
    protected int addHeal = 80;

    public Doctor(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
    }

    public Doctor(String name){
        super(name);
    }

    @Override
    void hit(Hero hero) {
        System.out.println("Доктор не может бить!");
    }

    @Override
    void healing(Hero hero) {
        hero.addHealth(addHeal);
    }
}
