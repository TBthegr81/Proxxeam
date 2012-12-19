package main;
/*
 * Kira_Login_Client
 * @Authur: Erik Welander
 * @Version: 2012-12-17
 * @JRE: JDK 1.6.0_32 X64
 * @Encoding: UTF-8
 * @IDE: Netbeans 7.2.1
 * 
 * Description: A GUI client that handles internet login and access to everyone inside the
 * "PROXXI" domain by Erik Welander, see https://github.com/Kira9204/Kira-Login-Client/
 * 
 */
//JAVA AWT GUI LIBRARIES
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//JAVA SWING GUI LIBRARIES
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;

//JAVA IO LIBRARIES
import java.io.IOException;
import javax.swing.SwingConstants;

public class Kira_Login_Client extends JFrame implements ActionListener{
//APPLICATION PROPERTIES
private final String version = "2012-12-17";
private final int WINDOW_WIDTH = 365;
private final int WINDOW_HEIGHT = 346;
private final Color bg_color = new Color(39,43,57);
private final Color ok_color = new Color(36,148,69);
private Timer update_timer = new Timer(30000,this);
private Timer logout_timer = new Timer(1000,this);
private Timer login_delay_timer = new Timer(100,this);
private int logout_time_left = 3;

//MENU BAR PUBLIC VARIABLES
JMenuItem m_about;

//TEXT BAR PUBLIC VARIABLES
JTextField tf_username;
JPasswordField ptf_password;

//BUTTONs PUBLIC
JButton bt_login;
JButton bt_logout;
JButton bt_full_logout;

//LABELS PUBLIC
JLabel l_status;

    public Kira_Login_Client(){
        initGUI();
    }
    public void initGUI(){
        //Mimic the native OS Look and Feel
        try{ 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex){
            System.out.println("NOTICE: No feasable native look and feel could be determined, defaulting to standard swing layout");
        }
        //Code for setting up the basics for the JFrame
        JFrame jframe = new JFrame();
        jframe.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);    //Set the applications GUI window size
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE); //Close the application when pressing X
        jframe.setLocationRelativeTo(null);     //Center the window
        jframe.setTitle("PROXXI Internet Login Client V "+version);    //Set the Window title
        jframe.setResizable(false);
        
        //Code for adding a menu bar
        JMenuBar menubar = new JMenuBar();
        JMenu m_help = new JMenu("Hjälp");
        m_about = new JMenuItem("Om");
        m_about.addActionListener(this);
        m_help.add(m_about);
        menubar.add(m_help);
        
        //Code for setting up all the JPanels
        JPanel jp_ground = new JPanel(new GridLayout(6,1));
        jp_ground.setBackground(bg_color);
        
        JPanel jp_tf_username = new JPanel(new FlowLayout());
        jp_tf_username.setBackground(bg_color);
        
        JPanel jp_ptf_password = new JPanel(new FlowLayout());
        jp_ptf_password.setBackground(bg_color);
        
        JPanel jp_bt = new JPanel(new FlowLayout());
        jp_bt.setBackground(bg_color);
        
        JPanel jp_bt_fl = new JPanel(new FlowLayout());
        jp_bt_fl.setBackground(bg_color);
        
        //Code for setting up all of the labels
        JLabel l_title = new JLabel("PROXXI INTERNET LOGIN", SwingConstants.CENTER);
        l_title.setFont(new Font("Tahoma", Font.BOLD, 20));
        l_title.setForeground(Color.white);
        //l_title.setPreferredSize(new Dimension(200,30));
        
        l_status = new JLabel("<html>Vänligen logga in <br>"+System.getProperty("user.name")+"...</html>", SwingConstants.CENTER);
        l_status.setFont(new Font("Tahoma", Font.BOLD, 20));
        l_status.setForeground(Color.white);
        //l_status.setPreferredSize(new Dimension(200,30));
        
        //Code for adding the text fields
        tf_username = new JTextField(System.getProperty("user.name"));
        tf_username.setPreferredSize(new Dimension(200,30));
        ptf_password = new JPasswordField();
        ptf_password.setPreferredSize(new Dimension(200,30));
        ptf_password.addActionListener(this);
        
        
        //Code for adding the buttons
        bt_login = new JButton("Logga in");
        bt_login.setForeground(Color.blue);
        bt_login.setPreferredSize(new Dimension(100,30));
        bt_login.addActionListener(this);
        
        bt_logout = new JButton("Logga ut");
        bt_logout.setForeground(Color.blue);
        bt_logout.setPreferredSize(new Dimension(100,30));
        bt_logout.addActionListener(this);
        
        bt_full_logout = new JButton("Logga ut datorn");
        bt_full_logout.setForeground(Color.blue);
        bt_full_logout.setPreferredSize(new Dimension(120,30));
        bt_full_logout.addActionListener(this);
        
        //Code for adding the objects to labels
        jp_tf_username.add(tf_username);
        jp_ptf_password.add(ptf_password);
        
        jp_bt.add(bt_login);
        jp_bt.add(bt_logout);
        jp_bt_fl.add(bt_full_logout);
        
        //Code for adding all the final code to the ground label
        jp_ground.add(l_title);
        jp_ground.add(jp_tf_username);
        jp_ground.add(jp_ptf_password);
        jp_ground.add(l_status);
        jp_ground.add(jp_bt);
        jp_ground.add(jp_bt_fl);
        
        //Final JFRAME code
        jframe.setJMenuBar(menubar);
        jframe.add(jp_ground);
        //jframe.pack();
        jframe.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ev) {
        if(ev.getSource() == bt_login || ev.getSource() == ptf_password){
            l_status.setForeground(Color.white);
            l_status.setText("Loggar in...");
            bt_login.setEnabled(false);
            bt_logout.setEnabled(true);
            login_delay_timer.start();
        }
        else if(ev.getSource() == login_delay_timer){
            login_delay_timer.stop();
            String result[] = libKira_Login_Client.performHttpsPOST("https://bruse.proxxi.org?do=login", "iso-8859-1", "uname", tf_username.getText(), "pass", ptf_password.getText());
            if(result[0].equals("OK")){
                String real_name = libKira_Login_Client.getRealName(result[1]);
                if(real_name.length() > 0){
                    l_status.setForeground(ok_color);
                    l_status.setText("<html>Inloggad som: <br>"+real_name+" :)</html>");
                    update_timer.start();
                }
                else{
                    l_status.setForeground(Color.red);
                    l_status.setText("Fel: Icke godkänd inlogg :(");
                    update_timer.stop();
                    bt_login.setEnabled(true);
                }
                ptf_password.setText("");
            }
            else{
                l_status.setForeground(Color.red);
                l_status.setText("FEL: Inget svar");
                update_timer.stop();
                bt_login.setEnabled(true);
            }
        }
        else if(ev.getSource() == bt_logout){
            l_status.setForeground(Color.red);
            l_status.setText("Utloggad");
            libKira_Login_Client.performHttpsPOST("https://bruse.proxxi.org/index.php?do=logout", "iso-8859-1", "", "", "", "");
            bt_logout.setEnabled(false);
            bt_login.setEnabled(true);
        }
        else if(ev.getSource() == bt_full_logout){
            l_status.setForeground(Color.red);
            l_status.setText("<html>Loggar ut datorn om:<br> "+logout_time_left+" sek</html>");
            logout_timer.start();
            bt_full_logout.setEnabled(false);
            bt_logout.setEnabled(false);
            bt_login.setEnabled(false);
        }
        else if(ev.getSource() == update_timer){
            String result[] = libKira_Login_Client.accessHTTPS("https://bruse.proxxi.org/keep-alive.php");
                if(!result[0].equals("OK")){
                    l_status.setForeground(Color.red);
                    l_status.setText("<html>FEL: Servern svarade<br> inte på förfrågan...</html>");
                    update_timer.stop();
                    bt_logout.setEnabled(false);
                    bt_login.setEnabled(true);
                }
        }
        else if(ev.getSource() == logout_timer){
            if(logout_time_left == 0){
                try {
                    libKira_Login_Client.accessHTTPS("https://bruse.proxxi.org/index.php?do=logout");
                    Process p = Runtime.getRuntime().exec("shutdown -l");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,"Error: Runtime.getRuntime().exec(\"shutdown -l\")\n"
                            +ex+"\n\nOm felet består, kontakta Erik Welander(Kira) via proxxigruppen @ Facebook","Ett fel inträffade",JOptionPane.WARNING_MESSAGE);
                }
            }
            else{
                --logout_time_left;
                l_status.setText("<html>Loggar ut datorn om:<br> "+logout_time_left+" sek</html>");
            }
        }
        else if(ev.getSource() == m_about){
            String message = "Kira Login Client for PROXXI"
                            +"\nVersion: "+version
                            +"\nSkapare: Kira (Erik Welander)"
                            +"\nSyfte: Detta program har jag skapat för att underlätta det för PROXXIs"
                            +"\nalla medlemmar med syfte att förbättra deras inloggnins och internet upplevelse."
                            +"\n\nAnvändaren behöver nu INTE konstant ha en webbläsare med \"bruses\" hemsida uppe för att få internetåtkomst."
                            +"\nKlienten känner av vilken användare som loggat in i domänen,"
                            +"\ndu behöver endast ange ett lösenord som kommer att skickas krypterat direkt till \"bruse\"."
                            +"\n\nKällkoden finns tillgänglig på github: https://github.com/Kira9204/Kira-Login-Client/"
                            +"\nKontakta mig annars via proxxi gruppen @ facebook";
            String title = "Om Kira Login Client for PROXXI";
            JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
        }
    }
    public static void main(String[] arg){
        new Kira_Login_Client();
    }
}