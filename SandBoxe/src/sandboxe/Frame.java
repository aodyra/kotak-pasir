/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandboxe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import kotakpasir.*;

/**
 *
 * @author Adar
 */

class MyPanel extends JPanel{
    protected static final Command C = new Command();
    @Override
    protected void paintComponent(Graphics g) {
        String element;
        super.paintComponent(g);
        for (int i = 0; i < 152; i++) {
            for (int j = 0; j < 102; j++) {
                g.drawRect(i*5, j*4, 5, 4);
                if (i == 0 || i == 151 || j == 0 || j == 101){
                    g.setColor(new Color(100,100,100));
                }else{
                    element = (C.kotak.getWorld()[i][j]);
                    if (element.compareTo("Zonk") == 0) {
                        g.setColor(new Color(230,230,230));
                    } else {
                        element = "kotakpasir."+element;
                        Element El = null;
                        try {
                            El = (Element) Class.forName(element).getConstructor(int.class,int.class,int.class).newInstance(0,0,C.kotak.getSuhu());
                        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            Logger.getLogger(MyPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Color c = new Color(255,255,255);
                        if (El != null)
                            c = El.getWarna();
                        g.setColor(c);
                    }
                }
                g.fillRect(i*5, j*4, 5, 4);
            }
        }       
    }

    //so our panel is the corerct size when pack() is called on Jframe
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(770, 470);
    }
    
    public void printWorld(){
       repaint();
    }
}


class ElementPanel1 extends JPanel {
    protected static int elementTerpilih;
    protected static int jumlahElement;
    protected static Vector<String> elementTersedia;
    protected static Vector<Color> warnaElement;
    public ElementPanel1(){
        jumlahElement = 3;
        elementTerpilih = 2;
        elementTersedia = new Vector(100);
        elementTersedia.addElement("Air");
        elementTersedia.addElement("Lumpur");
        elementTersedia.addElement("Tanah");
        warnaElement = new Vector(100);
        warnaElement.addElement(new Color(73,145,203));
        warnaElement.addElement(new Color(155,119,36));
        warnaElement.addElement(new Color(218,182,97));
    }
    @Override
    protected void paintComponent(Graphics g) {
        int i;
        super.paintComponent(g);
        for (i = 0; i < elementTersedia.size();i++){
            g.drawRect(10, i*50, 30, 30);
            g.setColor(warnaElement.get(i));
            g.fillRect(10, i*50, 30, 30);
            if (i == elementTerpilih)
                g.setColor(new Color(0,0,0));
            g.drawString(elementTersedia.get(i),10,i*50+42);
        }
    }
    
    public void addElement(String Element, Color ColorElement){
        elementTersedia.addElement(Element);
    }
    
    public void removeElement(String Element){
        //elementTersedia.remove(Element);
    }
    
    //so our panel is the corerct size when pack() is called on Jframe
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 2000);
    }
}


public class Frame extends javax.swing.JFrame implements WindowListener,ActionListener{

    /**
     * Creates new form NewJFrame
     */
    public Frame() {
        initComponents();
    }
    
   @Override
   public void actionPerformed(ActionEvent e){
        if ("play".equals(e.getActionCommand())){
            if (!jalan){
                Multithread M = new Multithread(1);
                M.start();
            }else{
                jalan = false;
            }
        } else if ("insert".equals(e.getActionCommand())){
            inputan = true;
        }else if ("delete".equals(e.getActionCommand())){
            inputan = false;
        }else if ("newgame".equals(e.getActionCommand())){
            KotakPanel.C.newWorld();
            KotakPanel.revalidate();
            KotakPanel.repaint();
        }
    }

   private boolean holding;
   
   public class MouseForKotakPanel implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent me) {
            int x = me.getX() / 5;
            int y = me.getY() / 4;
            if (x > 0 && x < 151 && y > 0 && y < 101){
                if (inputan){
                    KotakPanel.C.kotak.insert(x, y);
                    KotakPanel.revalidate();
                    KotakPanel.repaint();
                }else{
                    KotakPanel.C.kotak.delete(x, y);
                    KotakPanel.revalidate();
                    KotakPanel.repaint();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
            int x = me.getX() / 5;
            int y = me.getY() / 4;
            if (x > 0 && x < 151 && y > 0 && y < 101){
                if (inputan){
                    KotakPanel.C.kotak.insert(x, y);
                    KotakPanel.revalidate();
                    KotakPanel.repaint();
                }else{
                    KotakPanel.C.kotak.delete(x, y);
                    KotakPanel.revalidate();
                    KotakPanel.repaint();
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }
   }
   
   public class MouseForElementPanel implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent me) {
            int x = me.getX() / 40 + 1;
            int y = me.getY() / 50;
            if (x == 1 && y< EP1.jumlahElement){
                EP1.elementTerpilih = y;
                EP1.revalidate();
                EP1.repaint();
                KotakPanel.C.chooseElement(EP1.elementTersedia.get(y));
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }
   }

    public class Multithread extends Thread{
        private int e;
        private MouseEvent me;
        public Multithread(int _e){
            e = _e;
        }
        public Multithread(int _e, MouseEvent _me){
            me = _me;
            e = _e;
        }
        public void run(){
            if (e == 1){
                jalan = true;
                while(jalan){
                    KotakPanel.C.play();
                    KotakPanel.revalidate();
                    KotakPanel.repaint();
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (e == 2){
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */         
    private boolean jalan;
    private boolean inputan;
    private MyPanel KotakPanel;
    private ElementPanel1 EP1;
    private void initComponents() {
        inputan = true;
        Toolbar = new javax.swing.JPanel();
        KotakPanel = new MyPanel();
        KotakPanel.addMouseListener(new MouseForKotakPanel());
        ElementPanel = new javax.swing.JScrollPane();
        EP1 = new ElementPanel1();
        EP1.addMouseListener(new MouseForElementPanel());
        jScrollPane2 = new javax.swing.JScrollPane();
        Play = new javax.swing.JButton();
        Play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/play.png")));
        Play.setActionCommand("play");
        Play.addActionListener(this);
        NewGame = new javax.swing.JButton();
        NewGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/newworld.png")));
        NewGame.setActionCommand("newgame");
        NewGame.addActionListener(this);
        Load = new javax.swing.JButton();
        Load.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/openfile.png")));
        Save = new javax.swing.JButton();
        Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/save.png")));
        Insert = new javax.swing.JButton();
        Insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/insert.png")));
        Insert.setActionCommand("insert");
        Insert.addActionListener(this);
        Delete = new javax.swing.JButton();
        Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png")));
        Delete.setActionCommand("delete");
        Delete.addActionListener(this);
        Setting = new javax.swing.JButton();
        Setting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/setting.png")));
        About = new javax.swing.JButton();
        About.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/about.png")));
        
        
        setTitle("Sand Box Game");
        setSize(new Dimension(1020,820));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 102, 51));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        javax.swing.GroupLayout ToolbarLayout = new javax.swing.GroupLayout(Toolbar);
        Toolbar.setLayout(ToolbarLayout);
        ToolbarLayout.setHorizontalGroup(
            ToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ToolbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Play, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGap(25, 25, 25)
                .addComponent(NewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Load, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(Setting, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(About, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(188, Short.MAX_VALUE))
        );
        ToolbarLayout.setVerticalGroup(
            ToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ToolbarLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(ToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(About, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Setting, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Load, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Play, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout KotakPanelLayout = new javax.swing.GroupLayout(KotakPanel);
        KotakPanel.setLayout(KotakPanelLayout);
        KotakPanelLayout.setHorizontalGroup(
            KotakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
                
        );
        KotakPanelLayout.setVerticalGroup(
            KotakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
                
        );

        ElementPanel.setViewportView(EP1);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, 952)
                    .addComponent(KotakPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, 952))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ElementPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, 210)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, 210)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ElementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, 100))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(KotakPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)))
        );
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setToolTipText("");
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel2.setPreferredSize(new java.awt.Dimension(191, 400));

        jLabel1.setLabelFor(jButton6);
        jLabel1.setText("Api");

        jLabel2.setLabelFor(jButton6);
        jLabel2.setText("Air");

        jLabel3.setLabelFor(jButton6);
        jLabel3.setText("Tanah");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 87, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 310, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        pack();
        
        
        Multithread M = new Multithread(1);
        M.start();
        //KotakPanel.setGraphics(gfx);
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify                     
    //private javax.swing.JPanel KotakPanel;
    private javax.swing.JPanel Toolbar;
    private javax.swing.JScrollPane ElementPanel;                    
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton Play;
    private javax.swing.JButton NewGame;
    private javax.swing.JButton Load;
    private javax.swing.JButton Save;
    private javax.swing.JButton Insert;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Setting;
    private javax.swing.JButton About;
    private javax.swing.JPanel IE;
    // End of variables declaration                   

    @Override
    public void windowOpened(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}