/*

Keyboard mnemonics allows users to invoke a menu item with a single key. But the menu contains that menu item be popped up first. 
To users to invoke a menu item without its menu popped up, we need to assign an accelerator, key combination, to that menu item with the setAccelerator() method. Here is how it works: 
1. Assign difference accelerators, representing different key combinations, to different menu items. 

2) FileDialog

static int LOAD 
          This constant value indicates that the purpose of the file dialog window is to locate a file from which to read. 
static int SAVE 
          This constant value indicates that the purpose of the file dialog window is to locate a file to which to write. 

FileDialog(Dialog parent, String title, int mode) 
          Creates a file dialog window with the specified title for loading or saving a file


Reader(java.io.*)

public abstract int read(char[] cbuf,
                         int off,
                         int len)
                  throws IOExceptionReads characters into a portion of an array. This method will block until some input is available, an I/O error occurs, or the end of the stream is reached. 

Parameters:
cbuf - Destination buffer
off - Offset at which to start storing characters
len - Maximum number of characters to read 
Returns:
The number of characters read, or -1 if the end of the stream has been reached 
Throws: 
IOException - If an I/O error occurs
read:Attempts to read characters into the specified character buffer.

*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.awt.GraphicsEnvironment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.datatransfer.*;
import javax.swing.*;
class Notepad03 implements ActionListener
{ JMenuBar mbar;
JMenu file,edit,format,help;
JMenuItem fnew,open,save,saveAs,page,print,exit;
JMenuItem undo,cut,copy,paste,delete,find,findnext,selectall,timedate;
JMenuItem wordwrap,font;
JMenuItem about,topics;
JTextArea ta;
JFrame f;
final Clipboard clip= Toolkit.getDefaultToolkit().getSystemClipboard();  
 String wholeText,findString;
	int ind=0;
public Notepad03()
{
 f=new JFrame("Notepad2");
mbar=new JMenuBar();
f.setJMenuBar(mbar);
ta=new JTextArea();
f.add(ta);
   
//ta.addTextListener(this);
file=new JMenu("File");
file.setMnemonic(KeyEvent.VK_F);
edit=new JMenu("Edit");
edit.setMnemonic(KeyEvent.VK_E);
format=new JMenu("Format");
format.setMnemonic(KeyEvent.VK_O);
help=new JMenu("Help");
help.setMnemonic(KeyEvent.VK_H);
fnew=new JMenuItem("New");
fnew.setAccelerator(KeyStroke.getKeyStroke(
KeyEvent.VK_N, ActionEvent.CTRL_MASK));
fnew.addActionListener(this);
open=new JMenuItem("Open");
open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
open.addActionListener(this);
save=new JMenuItem("Save");
save.setAccelerator(KeyStroke.getKeyStroke(
KeyEvent.VK_S, ActionEvent.CTRL_MASK));
save.addActionListener(this);
saveAs=new JMenuItem("SaveAs");
saveAs.addActionListener(this);
page=new JMenuItem("Page Setup...");
page.addActionListener(this);
print=new JMenuItem("Print...");
print.addActionListener(this);
exit=new JMenuItem("Exit");
exit.addActionListener(this);
undo=new JMenuItem("Undo");
undo.addActionListener(this);
cut=new JMenuItem("Cut");
cut.addActionListener(this);
copy=new JMenuItem("Copy");
copy.addActionListener(this);
paste=new JMenuItem("Paste");
paste.addActionListener(this);

delete=new JMenuItem("Delete");
delete.addActionListener(this);

find=new JMenuItem("Find");
find.addActionListener(this);

findnext=new JMenuItem("Find Next");
findnext.addActionListener(this);
selectall=new JMenuItem("Select All");
selectall.addActionListener(this);

timedate=new JMenuItem("Time/Date");
timedate.addActionListener(this);
wordwrap=new JMenuItem("Word Wrap");
wordwrap.addActionListener(this);
font=new JMenuItem("Font...");
font.addActionListener(this);
about=new JMenuItem("About Notepad");
about.addActionListener(this);
topics=new JMenuItem("Help topics");
topics.addActionListener(this);
file.add(fnew);
file.add(open);
file.add(save);
file.add(saveAs);
file.addSeparator();
file.add(page);
file.add(print);
file.addSeparator();
file.add(exit);
edit.add(undo);
edit.addSeparator();
edit.add(cut);
edit.add(copy);
edit.add(paste);
edit.add(delete);
edit.addSeparator();
edit.add(find);
edit.add(findnext);
edit.addSeparator();
edit.add(selectall);
edit.add(timedate);

format.add(wordwrap);
format.add(font);
help.add(topics);
help.addSeparator();
help.add(about);
mbar.add(file);
mbar.add(edit);
mbar.add(format);
mbar.add(help);
f.setBounds(100,100,600,500);
f.setVisible(true);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}
public static void main(String args[])
{ new Notepad03();
}
public void actionPerformed(ActionEvent ae)
{ JMenuItem mi=(JMenuItem)ae.getSource();
try
{
if(mi==fnew)
{ 
ta.setText("");
}
else if(mi==open)
{
FileDialog fd=new FileDialog(f,"OPEN",FileDialog.LOAD);
fd.setVisible(true);
File file=new File(fd.getDirectory()+"\\"+fd.getFile());
Reader r=new  BufferedReader(new FileReader(file));
char ch[]=new char[(int)file.length()];
r.read(ch,0,ch.length);
String s=new String(ch);
ta.setText(s);
r.close();
f.setTitle(fd.getFile()+" –Notepad");
}
else if(mi==save)
{ 
FileDialog fds=new FileDialog(f,"Save",FileDialog.SAVE);
fds.setVisible(true);
System.out.println ("get dir==="+fds.getDirectory());
Writer w=new FileWriter(fds.getDirectory()+"\\"+fds.getFile());
String str=ta.getText();
w.write(str);
w.close();
f.setTitle(fds.getFile()+" –Notepad2");
//mi.setEnabled(false);
}
else if(mi==saveAs)
{ FileDialog fds=new FileDialog(f,"Save",FileDialog.SAVE);
fds.setVisible(true);
Writer w=new FileWriter(fds.getDirectory()+"\\"+fds.getFile());
String str=ta.getText();
w.write(str);
w.close();
f.setTitle(fds.getFile()+" –Notepad2");
}
else if(mi==page)
{
PrinterJob pj = PrinterJob.getPrinterJob();
{
PageFormat pf = pj.pageDialog(pj.defaultPage());
}

}
else if(mi==cut)
{
	String selection = ta.getSelectedText();
	 StringSelection data = new StringSelection(selection);
	clip.setContents(data,null);
	//1 parameter stringselection,myclipboardowner
	ta.replaceRange("",ta.getSelectionStart(),ta.getSelectionEnd());
	
}
else if(mi==copy)
{
	String selection = ta.getSelectedText();
	//A Transferable which implements the capability required to transfer a String
	//java.?awt.?datatransfer

	StringSelection data = new StringSelection(selection);
	clip.setContents(data,null);

}
else if(mi==paste)
{
		
	Transferable clipData = clip.getContents(clip);
	/*Defines the interface for classes that can be used to provide data for a transfer operation.*/
	try 
	{
	if(clipData.isDataFlavorSupported(DataFlavor.stringFlavor)) 
		{
	String s = (String)(clipData.getTransferData(DataFlavor.stringFlavor));
	ta.replaceSelection(s);
		}
	}
	catch (Exception a)
	{}



}

else if(mi==delete)
{
	String selection = ta.getSelectedText(); 
	StringSelection data = new StringSelection(selection);
	clip.setContents(data, data);
	ta.replaceRange("",ta.getSelectionStart(),ta.getSelectionEnd());

}

else if(mi==find)
{
	
	  wholeText=ta.getText();
           findString=JOptionPane.showInputDialog(f,"Find what","Findtitle",JOptionPane.INFORMATION_MESSAGE);
            ind = wholeText.indexOf(findString,0);
            if(ind>=0)
	{
	ta.setCaretPosition(ind);
            ta.setSelectionStart(ind);
           int a = ind+findString.length();
           //txtArea.SelectionEnd( a );
           ta.setSelectionEnd(a);
	}

}

else if(mi==findnext)
{
	 wholeText=ta.getText();
            findString = JOptionPane.showInputDialog(null,"Find what","Find Next",JOptionPane.INFORMATION_MESSAGE);
            ind = wholeText.indexOf(findString,ta.getCaretPosition());
            ta.setCaretPosition(ind);
            ta.setSelectionStart(ind);
            ta.setSelectionEnd(ind+findString.length());

}

else if(mi==selectall)
{
ta.selectAll();

}
else if(mi==timedate)
{
	     try 
        {
        Thread.sleep(1000);
        Calendar cal = new GregorianCalendar();
        String hour = String.valueOf(cal.get(Calendar.HOUR));
        String minute = String.valueOf(cal.get(Calendar.MINUTE));

        String m1 = String.valueOf(cal.get(Calendar.AM_PM));
        String d1 = String.valueOf(cal.get(Calendar.DATE));
        String d2 = String.valueOf(cal.get(Calendar.MONTH));
        String d3 = String.valueOf(cal.get(Calendar.YEAR));
       //ta.setText(hour + ":" + minute+" " +d1 +"/"+d2+"/"+d3);
	ta.setText(String.valueOf(Calendar.getInstance().getTime()));
        }
        catch(Exception e1)
        {

        }
}
else if(mi==print)
{
	PrinterJob pj = PrinterJob.getPrinterJob(); 
	pj.printDialog();

}
else if(mi==exit)
{ //f.dispose();
//System.exit(0);

f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}
else if(mi==wordwrap)
{


}
else if(mi==font)
{
	/*JFontChooser fd = new JFontChooser(this,ta.getFont());
	fd.show();
	if(fd.getReturnStatus() == fd.RET_OK)
	{
	ta.setFont(fd.getFont());
	}
	fd.dispose();
	*/
}


}
catch(IOException e)
{ System.out.println(e.getMessage());
}
}
}

