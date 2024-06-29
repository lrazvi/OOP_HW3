import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;

//For selection of ids in treeview from Composite Pattern
public class JLabelClick extends JLabel implements MouseListener{
    JLabel jL;
    JComponent jC;
    int c;
    static AdminControlPanel inst = AdminControlPanel.getInstance();

    public JLabelClick(String title){
        super(title);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        jL = ((JLabel) e.getSource());
        jC = ((JComponent) e.getSource());
        c++;
        
        jC.setBorder(BorderFactory.createLineBorder(Color.RED));

        if(c%2 == 0 && ((c/2)%2 == 0)){        //deselect for every other click, the mouseClick calc is weird
            jC.setBorder(null);
        }
        if ((jL.getText() == "Root" || jL.getForeground() == Color.DARK_GRAY) &&    //if root or group is selected

            jL.getBorder() != null ) {
                inst.b.setEnabled(true);
                inst.b2.setEnabled(true);
                jL.setEnabled(true);
        }
        else if (jL.getForeground() == Color.LIGHT_GRAY 
            && jL.getBorder() != null) {                //when user is selected, openuserview button enabled
            inst.b3.setEnabled(true);
        }
        else if (jL.getBorder() == null) {
            inst.b.setEnabled(false);
            inst.b2.setEnabled(false);
            inst.b3.setEnabled(false);
        }
        
    }

    public static JLabelClick getSelectedGroup(){        //retrieving label of selected Id in treeview
        JLabelClick selected = new JLabelClick("");
       for( JLabelClick clk : inst.clckGroup){
            if (clk.getBorder() != null) {
                selected = clk;
                break;
            }
       }
        return selected;
    }

    

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    

}
