import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{

    Random r=new Random();
    Handler handler;

    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id);       
        this.handler=handler;
    }

    @Override
    public Rectangle getBounced() {
        return new Rectangle((int)x,(int)y,32,32);
    }
    
    @Override
    public void tick() {
        x+=velX;
        y+=velY;
        
        //if(y <=0 || y>=Game.HEIGHT-32) velY *= -1;
        //if(x <=0 || x>=Game.WIDTH-16) velX *= -1;
        
        x=Game.clamp((int) x, 0, Game.WIDTH-37);
        y=Game.clamp((int) y, 0, Game.HEIGHT-60);
        
        handler.addObject(new Trail(x, y, ID.Trail, Color.WHITE, 32, 32, 0.1f, handler));//Trail size changes from here
        
        
        collision();
    } 

    public void collision(){
        for(int i=0;i<handler.object.size();i++){
            
            GameObject tempObject=handler.object.get(i);
            
            if(tempObject.getId() == ID.BasicEnemy ||
                    tempObject.getId() == ID.FastEnemy ||
                    tempObject.getId() == ID.SmartEnemy){ //tempObject is now basicEnemy
                if(getBounced().intersects(tempObject.getBounced())){
                    //Collision code
                     HUD.HEALTH-=2; 
                }
            }
        }
    }
    @Override
    public void render(Graphics g) {
        /*--->Temp: To show rectangle class
        Graphics2D g2d =(Graphics2D)g;
        g.setColor(Color.green);
        g2d.draw(getBounced());
        */
        
        //Player setting
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);

    }
}
