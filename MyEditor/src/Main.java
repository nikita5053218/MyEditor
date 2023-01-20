import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class TextEditor implements ActionListener {

    JFrame frame;
    JMenuBar menubar;
    JMenu file,edit;
    JMenuItem newfile,openfile,savefile;
    JMenuItem cut,copy,paste,selectall,close;
    JTextArea textArea;
    TextEditor(){
        frame = new JFrame();
        menubar= new JMenuBar();
        textArea=new JTextArea();
        file =new JMenu("FILE");
        edit = new JMenu("EDIT");
        frame.add(textArea);
        menubar.add(file);
        menubar.add(edit);
        newfile =new JMenuItem("NEW");
        openfile=new JMenuItem("OPEN");
        savefile=new JMenuItem("SAVE");
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);
        cut=new JMenuItem("CUT");
        copy=new JMenuItem("COPY");
        paste=new JMenuItem("PASTE");
        selectall=new JMenuItem("SELECT ALL");
        close=new JMenuItem("CLOSE WINDOW");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);
        frame.setJMenuBar(menubar);
        frame.setBounds(100,100,800,500);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectall){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        if(actionEvent.getSource()==newfile){
            TextEditor newWindow =new TextEditor();
        }
        if(actionEvent.getSource()==openfile){
            JFileChooser fileChooser=new JFileChooser("D:");
            int chooseOption =fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){

                File file=fileChooser.getSelectedFile();
                String filePath=file.getPath();
                try{
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
                    String intermediate="",output="";
                    while((intermediate=bufferedReader.readLine())!=null){
                        output=output+intermediate+"\n";

                    }
                    textArea.setText(output);

                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==savefile){
            JFileChooser fileChooser=new JFileChooser("D:");
            int chooseOption=fileChooser.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    BufferedWriter outfile = new BufferedWriter(new FileWriter(file));
                    textArea.write(outfile);
                    outfile.close();
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        }

    }
    public static void main(String[] args) {
        TextEditor textEditor=new TextEditor();
    }
}