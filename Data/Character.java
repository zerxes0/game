package Data;

public abstract class Character extends GameObject {
    public float life;
    public String name;  
    /*TODO
     * Implementacion de las habilidades de batalla
     * que tendra el personaje
     */

    public Character(){}
    public Character( String name, float life ){
        this.name = name;
        this.life = life;
    }
    public Character( String name, float life, int x, int y){
        super( x, y );
    }

    public float getLife() { return life; }
    public String getName() { return name; }
    
    public void setLife( float life ) { this.life = life; }
    public void setName( String name ) { this.name = name; }
}
