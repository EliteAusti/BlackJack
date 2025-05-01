import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
public class blackjack extends JFrame{
    Random rand = new Random();
    
    List<String> playerscards=new ArrayList<>();
    List<String> dealercards=new ArrayList<>();
    List<Integer> cardsvals1 = Arrays.asList(11,2,3,4,5,6,7,8,9,10,10,10,10,11,2,3,4,5,6,7,8,9,10,10,10,10,11,2,3,4,5,6,7,8,9,10,10,10,10,11,2,3,4,5,6,7,8,9,10,10,10,10);
    List<String> cards1 = Arrays.asList("🂡","🂢","🂣","🂤","🂥","🂦","🂧","🂨","🂩","🂪","🂫","🂭","🂮","🂱","🂲","🂳","🂴","🂵","🂶","🂷","🂸","🂹","🂺","🂻","🂽","🂾","🃁","🃂","🃃","🃄","🃅","🃆","🃇","🃈","🃉","🃊","🃋","🃍","🃎","🃑","🃒","🃓","🃔","🃕","🃖","🃗","🃘","🃙","🃚","🃛","🃝","🃞");
    List<String> cards=new ArrayList<>();
    List<Integer> cardsvals=new ArrayList<>();
    private JFrame frame;
    private JLabel finallabel;
    private JFrame panel;
    private JButton hitbut;
    private JButton standbut;
    private JLabel playertotal;
    private JButton restart;
    int dealertotal=0;
    int dealeraces=0;
    int total=0;
    int playersaces=0;
    boolean playerwin=false;
    boolean dealerbust=false;
    public blackjack() {
        
        for(String item:cards1){
            cards.add(item);
        }
        for(int item:cardsvals1){
            cardsvals.add(item);
        }
        
        frame = new JFrame("Button Click Example");
        frame.setLayout(null);
        frame.setSize(1000, 600);
        restart=new JButton("Restart");
        restart.setBounds(300,260,100,60);
        restart.setVisible(false);
        frame.add(restart);
        hitbut = new JButton("Hit");
        standbut = new JButton("Stand");
        playertotal=new JLabel("Total: 0");
        //starting two cards for dealer and player
        makecard(true);
        makecard(true);
        makecard(false);
        JLabel facedown=new JLabel("🂠");
        facedown.setBounds(200+50,300,128,200);
        facedown.setFont(new Font("null",Font.PLAIN,100));
        frame.add(facedown);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playertotal.setBounds(100,270,80,30);
        hitbut.setBounds(30,240,80,30);
        standbut.setBounds(30,300,80,30);
        
        frame.add(playertotal);
        frame.add(hitbut);
        frame.add(standbut);
        frame.setVisible(true);

        hitbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                
                makecard(true);
                SwingUtilities.updateComponentTreeUI(frame);
                if(total>21){
                    if(playersaces>0){
                        playersaces-=1;
                        total-=10;
                    }else{
                        System.out.println("player bust");
                        endgame();
                    }
                }
                

            }
        });
        standbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitbut.setEnabled(false);
                facedown.setVisible(false);
                
                while(dealertotal<17){
                    System.out.println("dealing card to dealer");
                    makecard(false);
                    if(dealertotal>21){
                        if(dealeraces>0){
                            dealeraces-=1;
                            dealertotal-=10;
                        }else{
                            playerwin=true;
                            dealerbust=true;
                            System.out.println("dealer bust");
                        }
                    }
                }
                System.out.println("dealertotal= "+dealertotal);
                if(dealerbust=false){
                    if(dealertotal>total){
                        playerwin=false;
                    }else{
                        playerwin=true;
                    }
                }
                
                endgame();
            }
        });
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("restarting game");
                restart.setVisible(false);
                frame.dispose();
                new blackjack();
            }
        });
    }
    public void endgame(){
        
        
        hitbut.setEnabled(false);
        standbut.setEnabled(false);
        
        System.out.println(playerwin);
        if(playerwin==true){
            finallabel=new JLabel("player wins");
        }
        if(playerwin==false){
            finallabel=new JLabel("dealer wins");
        }
        if(total==dealertotal){
            finallabel=new JLabel("draw");
        }
        finallabel.setBounds(200,270,80,30);
        frame.add(finallabel);
        restart.setVisible(true);
    }
    
    
    public void makecard(boolean player){
        int randomint=rand.nextInt(cards.size());
        String card=cards.get(randomint);
        int val=cardsvals.get(randomint); 
        if(player==true){
            playerscards.add(card);
            total+=val;
            JLabel c=new JLabel(card);
            c.setBounds(100*playerscards.size()+50,50,128,200);
            c.setFont(new Font("null",Font.PLAIN,100));
                    
            playertotal.setText("Total: "+total);
            frame.add(c);
            if(val==11){
                playersaces+=1;
            }
        }else{
            dealercards.add(card);
            dealertotal+=val;
            JLabel c=new JLabel(card);
            c.setBounds(100*dealercards.size()+50,300,128,200);
            c.setFont(new Font("null",Font.PLAIN,100));
            frame.add(c);
            if(val==11){
                dealeraces+=1;
            }

        }
        SwingUtilities.updateComponentTreeUI(frame);
    }
    
   
    
    public static void main(String[] args) {
        new blackjack();
    }
}

    

  
