import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameTest implements ActionListener {
    private static JFrame mainFrm, testFrm;
    private static JButton btn;

    public static void main(String[] args) {
        FrameTest cl = new FrameTest();
        cl.setupMainFrame();
        cl.setupOtherFrame();
    }

    public void setupMainFrame() {
        mainFrm = new JFrame();
        mainFrm.setTitle("Main Frame");
        mainFrm.setSize(300, 300);
        mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrm.setVisible(true);

        Container mainContent = mainFrm.getContentPane();
        btn = new JButton("open Frame");
        btn.addActionListener(this);
        mainContent.add(btn);
    }

    public void setupOtherFrame() {
        testFrm = new JFrame();
        testFrm.setTitle("LOL");
        testFrm.setSize(300, 300);
        testFrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        testFrm.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == btn) {
            EventQueue.invokeLater(() -> testFrm.setVisible(true));
        }
    }
}
