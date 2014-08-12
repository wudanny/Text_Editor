/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package texteditor;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for the text part of the editor
 */
public class TextController {
    //current font information
    private int currentFontSize;
    private String currentFontName;
    private int currentFontStyle;
    //all possible fonts
    private List<Integer> fontSizes;
    private List<String> fontNames;
    //the view
    private EditorView editorView;

    public TextController(List<Integer> fontSizes, List<String> fontNames){
        this.fontNames=fontNames;
        this.fontSizes=fontSizes;
        currentFontName=fontNames.get(0);
        currentFontSize=fontSizes.get(0);
        currentFontStyle=Font.PLAIN;
    }
    
    public TextController(){
        this.fontNames=new ArrayList<String>();
        fontNames.add(Font.MONOSPACED);
        fontNames.add(Font.SANS_SERIF);
        fontNames.add(Font.SERIF);
        currentFontName=fontNames.get(0);
        this.fontSizes=new ArrayList<Integer>();
        fontSizes.add(10);
        fontSizes.add(12);
        fontSizes.add(14);
        fontSizes.add(16);
        currentFontSize=fontSizes.get(0);
        currentFontStyle=Font.PLAIN;
    }
    
    List<Integer> getFontSizes(){
        return fontSizes;
    }
    
    List<String> getFontNames(){
        return fontNames;  
    }
    
    void setFontName(String newFontName){
        
        currentFontName=newFontName;
    }
    
    void setFontSize(int newFontSize){
        
        currentFontSize=newFontSize;
    }
    
    Font getFont(){
        
        return new Font(currentFontName,currentFontStyle,currentFontSize);
    }
    
            
        void setEditorView(EditorView editorView){
            this.editorView=editorView;
        }


        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        final TextController  textController=new TextController();
        final FileController fileController=new FileController();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               EditorView editorViewer=new EditorView(textController,fileController);
                editorViewer.setVisible(true);
                fileController.setEditorView(editorViewer);
                 textController.setEditorView(editorViewer);
            }
        });
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FileSelectView fileViewer=new FileSelectView(fileController);
                fileViewer.setVisible(false);
                fileController.setFileFileView(fileViewer);
            }

    });



}
}
