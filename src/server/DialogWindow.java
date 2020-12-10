package server;

import gui.Infos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Dialog Window to display about, disclaimer, help
 */
public class DialogWindow extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JTextArea display;
    private final JButton buttonClose;
    private final JPanel panelButton;
    private final JScrollPane scroll;

    DialogWindow(JFrame owner, String title, String msg) throws HeadlessException {
        super(owner, title);
        setSize(900, 400);
        setResizable(false);
        setLocationRelativeTo(owner.getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        display = new JTextArea();
        display.setText(msg);
        setDisplay();

        scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setViewportView(display);
        scroll.setBackground(Color.WHITE);
        scroll.setBorder(new TitledBorder(new LineBorder(Color.BLACK), Infos.sysName));

        add(scroll, BorderLayout.CENTER);

        panelButton = new JPanel();
        buttonClose = new JButton("Close");
        buttonClose.addActionListener(this);
        panelButton.add(buttonClose);

        add(panelButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    
    /** 
     * Set the window visible.
     * @param arg0
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.setVisible(false);
    }

    /**
     * Set display configurations.
     */
    public void setDisplay() {
        display.setBackground(Color.GRAY);
        display.setEditable(false);
        display.setFocusable(true);
        display.setLineWrap(true);
        display.setWrapStyleWord(true);
        display.setMargin(new Insets(10, 20, 15, 20));
        display.setFont(new Font("Verdana", Font.PLAIN, 12));
        display.setCaretPosition(0);
    }
}
