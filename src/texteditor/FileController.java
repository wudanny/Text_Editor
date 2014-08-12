/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Danny
 */
public class FileController {

    private String fileName;
    private File filePath;
    private boolean changed;
    private FileSelectView fileView;
    private EditorView editorView;
    private TextController textController;
    private RenameView renameView;

    /**
     * Constructor for the file controller and sets new text to the name
     * "Untitled"
     *
     * @param textController A TextController object that controls the text
     * component of the text editor
     */
    public FileController(TextController textController) {
        fileName = "Untitled";
        changed = false;
        this.textController = textController;
    }

    /**
     * If the save button is clicked, checks if it is a new file and if it is,
     * go through the a new file save instead.
     */
    void saveFileClicked() {
        if (filePath == null) {
            saveAsClicked();
        } else {
            saveFile();
        }

    }

    /**
     * Retrieve and save the content in the text editor.
     */
    void saveFile() {
        writeTextToFile();
        changed = false;
    }

    /**
     * When the save as button is clicked. Show the save dialog.
     */
    void saveAsClicked() {
        fileView.showSave();

    }

    /**
     * Creates a new file and save content of the text file to it.
     *
     * @param filePath A String object that represents the file path of the new
     * file.
     */
    void saveAs(String filePath) {
        this.filePath = new File(filePath);
        fileName = this.filePath.getName();
        editorView.setWindowTitle(fileName);
        writeTextToFile();
        changed = false;
    }

    /**
     * write the content of the text to the file.
     */
    private void writeTextToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(textController.getText());
            writer.close();
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }
    }

    /**
     * When the open file button is clicked. Show open dialog.
     */
    void openFileClicked() {
        this.fileView.showOpen();
    }

    /**
     * Retrieves and display the text content in the file.
     *
     * @param filePath A String object that represents the file path.
     */
    void openFile(String filePath) {

        this.filePath = new File(filePath);
        fileName = this.filePath.getName();
        editorView.setWindowTitle(fileName);
        writeFileToText();
        changed = false;
    }

    /**
     * Write the content of the file to the text editor.
     */
    private void writeFileToText() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            textController.addText(reader);
            reader.close();

        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }

    }

    /**
     * When the new file button is clicked.
     */
    void newFileClicked() {
        this.newFile();
    }

    /**
     * Create a new text and name it "Untitled".
     */
    void newFile() {
        fileName = "Untitled";
        filePath = null;
        changed = false;
        textController.newTextArea();
        editorView.setWindowTitle(fileName);
    }

    /**
     * The rename button is clicked.
     *
     */
    void renameFileClicked() {
        renameView.setTextName(this.fileName);
        renameView.setVisible(true);
    }

    /**
     * The cancel button is clicked on renameView
     */
    void renameFileCanceled() {
        renameView.setVisible(false);
    }

    /**
     * change the file name to reflect the name change.
     *
     * @param rename
     */
    void renameFile(String rename) {
        renameView.setVisible(false);
        this.fileName = rename;
        editorView.setWindowTitle(fileName);
        if (filePath != null) {
            filePath.renameTo(new File(filePath.getParent() + "/" + this.fileName));
        }
    }

    /**
     * When the quit file is clicked.
     */
    void quitFileClicked() {
        quitFile();
    }

    /**
     * Dispose of all the views.
     */
    void quitFile() {
        editorView.dispose();
        fileView.dispose();
    }

    /**
     * Sets the file select view.
     *
     * @param fileView A FileSelectView object that represents the file select
     * UI
     */
    void setFileSelectView(FileSelectView fileView) {
        this.fileView = fileView;
    }

    /**
     * Sets the editor view.
     *
     * @param editorView A EditorView object that represents the text editor UI
     */
    void setEditorView(EditorView editorView) {
        this.editorView = editorView;
    }

    /**
     * Sets the rename view.
     *
     * @param renameView A RenameView object that represents the renaming UI.
     */
    void setRenameView(RenameView renameView) {
        this.renameView = renameView;
    }

    /**
     * Retrieves the file name of the current text.
     *
     * @return A String object that represents the name of the file
     */
    String getFileName() {
        return fileName;
    }

    /**
     * Set if there is any changes made to the text
     *
     * @param isChanged a boolean. If there where changes made to the text then
     * it will be true. Otherwise false.
     */
    void setChanged(boolean isChanged) {
        this.changed = isChanged;
    }

}
