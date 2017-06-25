package systems;

public class Time {
	
	//ticker
    private static int fps = 12; //frames per second
	private static long targetTime = 1000 / fps; //milisegundos entre fps
	private static boolean running = true;
    private static long wait;
    private static Thread hilo;
	//-----------------------------
	

    public static synchronized void sleep( long time ){
        try{
    	    hilo.sleep(time);
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    public static synchronized void start(){
        hilo = new Thread(){
	        public void run(){
            //ticker -- se usa para manejar el tiempo del juego				
		    long start;
            long elapsed;

            while( running ){
                start = System.nanoTime();//obtenemos tiempo inicial
                elapsed = System.nanoTime() - start; //final - inicial == tiempo transcurrido
                wait = targetTime - elapsed / 1000000;
                try{			
                    Thread.sleep(wait);
                    }catch( Exception e ){
                        e.getStackTrace();
                    }//catch
                }//while
            }//ticker
        };
        hilo.start();
    }

    public static long getTime(){
        return wait;
    }

    public static int getFps() {
        return fps;
    }
}
