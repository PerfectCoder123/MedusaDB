import java.awt.*;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class NewJFrame extends javax.swing.JFrame   {

    public static String col_data[];
    public static String row_info[][];

    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JMenu jMenu1;
    public static javax.swing.JMenu jMenu2;
    public static javax.swing.JMenu jMenu3;
    public static javax.swing.JMenu jMenu4;
    public static javax.swing.JMenu jMenu5;
    public static javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JMenuItem jMenuItem1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextArea jTextArea1;

    public NewJFrame() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("medusa.jpg"));
        initComponents();
    }

    private void initComponents() {

        // intialising all components
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu4.setText("jMenu4");
        jMenuItem1.setText("jMenuItem1");

        // customizing textarea
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 16)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setRows(5);
        jTextArea1.setBorder(null);
        jTextArea1.setCaretColor(new java.awt.Color(153, 153, 153));
        jScrollPane1.setViewportView(jTextArea1);

        // customizing label
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 16));
        jLabel1.setText(
                "<html><body><p> &nbsp Terminal output<br></p></body></html>");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setAlignmentX(1.0F);
        jScrollPane2.setViewportView(jLabel1);
        jLabel1.setAutoscrolls(true);

        // customizing table
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setAutoscrolls(true);
        jScrollPane3.setViewportView(jTable1);

        // customizing menu

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);
       

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Run");
        jMenuBar1.add(jMenu3);
        jMenu3.addMenuListener(new Listener());

        jMenu5.setText("Help");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        // customizing the frame.
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout
                                                .createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 985,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 529,
                                                        Short.MAX_VALUE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 562,
                                                Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 66,
                                        Short.MAX_VALUE)));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("                                                                                                                                                                                               MedusaDb - workspace for your cursed projects");
        setPreferredSize(new java.awt.Dimension(1566, 865));

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main() {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        new NewJFrame();
    } 
   public static void dynamicTable(Object [][] row_info,Object []col_data){
    jTable1 = new JTable(row_info,col_data);
        jTable1.setShowHorizontalLines(true);
        if(col_data.length > 6)jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        else jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setRowHeight(30);
        jTable1.setFont(new Font("Franklin Gothic Demi", 0, 14));
        jTable1.setShowVerticalLines(true);
        for(int i = 0;i< col_data.length;i++) jTable1.getColumnModel().getColumn(i).setPreferredWidth(150);
        jScrollPane3.setViewportView(jTable1);
   }

}

class Listener implements MenuListener {
    IDE ide ;

    @Override
    public void menuSelected(MenuEvent e) {
        if(e.getSource() == NewJFrame.jMenu3) 
        {
        ide = new IDE(NewJFrame.jTextArea1.getText());
        }
        
    }

    @Override
    public void menuDeselected(MenuEvent e) {
     
        
    }

    @Override
    public void menuCanceled(MenuEvent e) {
 
        
    }
    
  }
