/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    /**
     * Constructor with preset font sizes and font names
     *
     * @param fontSizes List<Integer> of font sizes. Must contain at least 1
     * element
     * @param fontNames List<String> of font names. Must contain at least 1
     * element
     */
    public TextController(List<Integer> fontSizes, List<String> fontNames) {
        this.fontNames = fontNames;
        this.fontSizes = fontSizes;
        currentFontName = fontNames.get(0);
        currentFontSize = fontSizes.get(0);
        currentFontStyle = Font.PLAIN;
    }

    /**
     * Constructor that sets font names and font sizes to defaults
     */
    public TextController() {
        this.fontNames = new ArrayList<String>();
        fontNames.add(Font.MONOSPACED);
        fontNames.add(Font.SANS_SERIF);
        fontNames.add(Font.SERIF);
        currentFontName = fontNames.get(0);
        this.fontSizes = new ArrayList<Integer>();
        fontSizes.add(10);
        fontSizes.add(12);
        fontSizes.add(14);
        fontSizes.add(16);
        currentFontSize = fontSizes.get(0);
        currentFontStyle = Font.PLAIN;
    }

    /**
     * Retrieves the list of possible font sizes;
     *
     * @return an List<Integer> of font sizes
     */
    List<Integer> getFontSizes() {
        return fontSizes;
    }

    /**
     * Retrieves the list of possible font names;
     *
     * @return an List<String> of font names
     */
    List<String> getFontNames() {
        return fontNames;
    }

    /**
     * Change the current font name being used
     *
     * @param newFontName The name of the new font name being used
     */
    void setFontName(String newFontName) {

        currentFontName = newFontName;
    }

    /**
     * Change the current font size being used
     *
     * @param newFontSize The size of the new font size being used
     */
    void setFontSize(int newFontSize) {

        currentFontSize = newFontSize;
    }

    /**
     * Retrieves the Font object that represents the font being used
     *
     * @return Font object with the current font name and size
     */
    Font getFont() {

        return new Font(currentFontName, currentFontStyle, currentFontSize);
    }

    /**
     * Set the editor view to the text controller
     * @param editorView the EditorView that represents of the UI of the editor
     */
    void setEditorView(EditorView editorView) {
        this.editorView = editorView;
    }

    /**
     * Retrieves the String in the text area of the text editor
     * @return A String object that represents the content of the text editor
     */
    String getText() {
        return editorView.getText();
    }

    /**
     * Clears previous contents in the text editor and insert the new text from the 
     * BufferedReader into the text editor.
     * @param reader A BufferedReader object that contains the new text to be
     * inserted into the text editor
     */
    void addText(BufferedReader reader) {
        try {
            editorView.addText(reader);
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }
    }

    /**
     * Clears all text content in the text editor.
     */
    void newTextArea() {
        editorView.newTextArea();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //Create all the controller
        final TextController textController = new TextController();
        final FileController fileController = new FileController(textController);
        
        //Create editor view
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditorView editorViewer = new EditorView(textController, fileController);
                editorViewer.setVisible(true);
                fileController.setEditorView(editorViewer);
                textController.setEditorView(editorViewer);
            }
        });

        //create file select view
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FileSelectView fileViewer = new FileSelectView(fileController);
                fileViewer.setVisible(false);
                fileController.setFileSelectView(fileViewer);
            }

        });
        
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               RenameView renameView= new RenameView(fileController);
               renameView.setVisible(false);
               fileController.setRenameView(renameView);
            }
        });

    }
}
