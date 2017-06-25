package entity;

import Data.GameObject;

public abstract class Character extends GameObject {
    private float life;
    private String name;

    public Character( String name, float life ){
        this.name = name;
        this.life = life;
    }
    Character(String name, float life, int x, int y){
        super( x, y );
    }

    public float getLife() { return life; }
    public String getName() { return name; }
    
    public void setLife( float life ) { this.life = life; }
    public void setName( String name ) { this.name = name; }
}
