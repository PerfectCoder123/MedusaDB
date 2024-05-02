import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;
  
public class Demo extends Canvas{  
    
    public void paint(Graphics g) {  
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("medu.png");
        g.drawImage(i,0,0,this);   
    }  

    @SuppressWarnings("removal")
    public static void main(String[] args) throws InterruptedException {  
        Demo m=new Demo();  
        JFrame f=new JFrame(); 
        JLayeredPane pane = f.getLayeredPane();  
        f.setLayout(null);
        m.setBounds(0,0,500,320);
        JProgressBar bar = new JProgressBar(); 
        bar.setBounds(5, 300, 490, 15);
        bar.setValue(0);
        pane.add(m,new Integer(1)); 
        f.setUndecorated(true);
        f.setBounds(550,270,500,320);
        pane.add(bar,new Integer(2));
        bar.setStringPainted(true);
        f.setVisible(true);  
        bar.setForeground(Color.green);
        bar.setBackground(Color.black);
        int i =0;
        while(i<101){
           bar.setValue(i++);
          if(i<10) i+=2;
          if(i>30) i+=5;
          if(i>70) i+=7;
            Thread.sleep(200);
        }
       bar.setString("Opening workspace");
        Thread.sleep(2000);
        NewJFrame.main();
        f.setVisible(false);
    }  
  
}  
