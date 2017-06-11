package systems;

import sun.audio.*;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioManager {
	
	//private static AudioStream music;
	private static AudioStream sound;
	
	public static void playMusic( final String path){
		Thread thread = new Thread() {
			public void run(){
        try{
	      /*  music = new AudioStream(new FileInputStream(path));
	        for(;;){
		        AudioPlayer.player.start(music);	
	        }*/
        	 Clip clip = AudioSystem.getClip();
             AudioInputStream inputStream = AudioSystem.getAudioInputStream(
            		 AudioManager.class.getResourceAsStream(path)
            		 );
             clip.open(inputStream);
             clip.start(); 
        }catch(Exception e){
            e.printStackTrace();
        }
			}
		};
		thread.start();        
	}
	
	public static void playSound( String path ){
        try{
	        sound = new AudioStream(new FileInputStream(path));
	        AudioPlayer.player.start(sound);
        }catch(Exception e){
            e.printStackTrace();
        }
	}//func
}
