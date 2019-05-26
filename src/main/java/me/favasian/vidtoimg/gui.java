package me.favasian.vidtoimg;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class gui {
    private JFrame window = new JFrame("Converter");;
    private convertMovietoJPG converter;
    public gui(){
        //creates instance of gridbagconstraint
        GridBagConstraints gbc = new GridBagConstraints();

        //create instance of border
        Border blackline = BorderFactory.createLineBorder(Color.black);

        //create imageicon for button
        ImageIcon dots = new ImageIcon(getClass().getClassLoader().getResource("textures/dots.jpg"));

        //set window size and make window appear
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        //main panel for the whole window
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        /*panels for the 5 directions*/
        JPanel centerPanel = new JPanel();
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();

        //sets center layout to be grid layout
        centerPanel.setLayout(new GridBagLayout());

        /*Components*/
        //JLabels
        JLabel addmp4 = new JLabel("Select mp4 file to be tagged");
        addmp4.setFont(new Font("Courier", Font.PLAIN,12));
        addmp4.setHorizontalAlignment(JLabel.CENTER);

        JLabel addFolder = new JLabel("Select output folder");
        addFolder.setFont(new Font("Courier", Font.PLAIN,12));
        addFolder.setHorizontalAlignment(JLabel.CENTER);

        JLabel addJump = new JLabel("Enter how many frame you would like to jump");
        addJump.setFont(new Font("Courier", Font.PLAIN,12));
        addJump.setHorizontalAlignment(JLabel.CENTER);

        JLabel title = new JLabel("Convert video to images!");
        title.setFont(new Font("Courier", Font.BOLD,12));

        //JButtons
        JButton done = new JButton("Done");

        final JButton mp4Btn = new JButton(resizeIcon(dots, 30, 20));
        mp4Btn.setPreferredSize(new Dimension(30, 20));

        final JButton folderBtn = new JButton(resizeIcon(dots, 30, 20));
        folderBtn.setPreferredSize(new Dimension(30, 20));

        //Numeric Text Field
        final NumericTextField frameJump = new NumericTextField();
        frameJump.setColumns(5);
        frameJump.setBorder(blackline);

        //JTextArea
        final JTextArea mp4Dir = new JTextArea(1,15);
        mp4Dir.setBorder(blackline);

        final JTextArea folderDir = new JTextArea(1,15);
        folderDir.setBorder(blackline);

        /*add labels to directional panel*/
        //center && adds position for each component
        //gbc changes for addmp4
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(addmp4, gbc);
        //gbc changes for mp4Dir
        gbc.gridy++;
        centerPanel.add(mp4Dir, gbc);
        //gbc change for mp4 dots
        gbc.gridx = 1;
        centerPanel.add(mp4Btn, gbc);
        //gbc change for addFolder
        gbc.gridx = 0;
        gbc.gridy++;
        centerPanel.add(addFolder, gbc);
        //gbc change for FolderDir
        gbc.gridy++;
        centerPanel.add(folderDir, gbc);
        //gbc change for folder dot
        gbc.gridx = 1;
        centerPanel.add(folderBtn, gbc);
        //gbc changes for addJump
        gbc.gridx = 0;
        gbc.gridy++;
        centerPanel.add(addJump, gbc);
        //gbc changes for frameJump
        gbc.gridy++;
        centerPanel.add(frameJump, gbc);

        //north
        northPanel.add(title);

        //south
        southPanel.add(done);

        /*adds directional panel to main panel*/
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        //set textarea to uneditable
        mp4Dir.setEditable(false);
        folderDir.setEditable(false);

        window.setContentPane(mainPanel);
        window.setVisible(true);

        /*add button actions*/
        //actionlistener for mp4 button
        mp4Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //create Instance of JFileChooser
                JFileChooser mp4Window = new JFileChooser();

                //set file filter
                mp4Window.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        if(file.isDirectory()){
                            return true;
                        }
                        else{
                            String filename = file.getName().toLowerCase();
                            return filename.endsWith(".mp4");
                        }
                    }

                    @Override
                    public String getDescription() {
                        return "MP4 File";
                    }
                });

                //set window title
                mp4Window.setDialogTitle("MP4 Window");

                //sets filechooser to only allow selection fo files
                mp4Window.setFileSelectionMode(JFileChooser.FILES_ONLY);

                //sets mp4 button to open JFileChooser window
                if(mp4Window.showOpenDialog(mp4Btn) == JFileChooser.APPROVE_OPTION){
                }

                //sets textarea to path file
                mp4Dir.setText(mp4Window.getSelectedFile().getAbsolutePath());
            }
        });

        //actionlistener for folder button
        folderBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //create Instance of JFileChooser
                JFileChooser folderWindow = new JFileChooser();

                //set window title
                folderWindow.setDialogTitle("Folder Window");

                folderWindow.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                //sets folder button to open JFileChooser window
                if(folderWindow.showOpenDialog(folderBtn) == JFileChooser.APPROVE_OPTION){
                }

                //sets textarea to path file
                folderDir.setText(folderWindow.getSelectedFile().getAbsolutePath());
            }
        });

        //actionlistener for done button
        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent){

                //creates instance of conversion class
                converter = new convertMovietoJPG(mp4Dir.getText(), folderDir.getText(), "jpg", Integer.parseInt(frameJump.getText()));

                try{
                    //calls converter
                    converter.vidimg();

                    JOptionPane.showMessageDialog(null, "Finished converting!");
                } catch (Exception e){
                    System.out.println(e);
                }
            }
        });
    }

    //Resizes imageicon
    private Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
