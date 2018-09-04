import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public static int WIDTH=800, HEIGHT=600;
    public String title="Game #1";
    
    private Thread thread;
    private boolean isRunning=false;
    
    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    
    public Game() {
        handler = new Handler();
        
        this.addKeyListener(new KeyInput(handler));
        
        new Window(WIDTH, HEIGHT, title, this);
        
        hud=new HUD();   
        spawner=new Spawn(handler, hud);
        r=new Random();
        
        handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
        
        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
        
        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
        start(); 
    }
    
    private synchronized void start(){
        if(isRunning) return;
        
        thread=new Thread(this);
        thread.start();
        isRunning=true;
              
    }
    
    private synchronized void stop(){
        if(!isRunning) return;
        
        try{
            thread.join();
        }catch(Exception E){
            System.out.println("Exception :"+E);
        }
        isRunning=false;
    }
    
    //Gameloop
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
			
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    
    private void tick(){
        //updates the game
        handler.tick();
        hud.tick();
        spawner.tick();
    }
    
    private void render(){
        //renders the game
        BufferStrategy bs=this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g=bs.getDrawGraphics();
        
        //Meat and Bones of our rendering
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        handler.render(g);
        
        hud.render(g);
        
        bs.show();
        g.dispose();
    }
    
    public static int clamp(int var, float min, float max){
        if(var>=max)
            return var=(int) max;
        else if(var<=min)
            return var=(int) min;
        else return var;        
    }
    public static void main(String[] args) {
        new Game();
    }     
}
