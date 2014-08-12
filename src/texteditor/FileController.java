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
        private boolean  changed;
        private FileSelectView fileView;
        private EditorView editorView;
        
        public FileController(){
            fileName="Untitled";
            changed=false;
        }
        
        void saveFileClicked(){
            if(filePath==null){
                saveAsClicked();
            }else{
                writeTextToFile();
                changed=false;
            }
         
        }
        
        void saveAsClicked(){
            fileView.showSave();
            
        }
        
        void saveAs(String filePath){
            this.filePath=new File(filePath);
            fileName=this.filePath.getName();
            editorView.setWindowTitle(fileName);
            writeTextToFile();
            changed=false;
        }
        private void writeTextToFile(){
            try{
                BufferedWriter writer=new BufferedWriter(new FileWriter(filePath));
                writer.write(editorView.getText());
                writer.close();
            }catch(IOException ex){
                System.err.print(ex.getMessage());
            }
        }
        void openFileClicked(){
            this.fileView.showOpen();
        }
        void openFile(String filePath){
            
            this.filePath=new File(filePath);
            fileName=this.filePath.getName();
            editorView.setWindowTitle(fileName);
            writeFileToText();
            changed=false;
        }
        private void writeFileToText(){
            try{
                
                BufferedReader reader=new BufferedReader(new FileReader(filePath));
                editorView.addText(reader, filePath.getPath());
                reader.close();

            }catch(IOException ex){
                System.err.print(ex.getMessage());
            }
            
        }
        void newFileClicked(){
            this.newFile();
        }
        
        void newFile(){
            fileName="Untitled";
            filePath=null;
            changed=false;
           editorView.newTextArea();
           editorView.setWindowTitle(fileName);
        }
        void renameFileClicked(String rename){
            this.fileName=rename;
            renameFile();
        }
        void renameFile(){
             filePath.renameTo(new File(filePath.getParent()+"/"+this.fileName));
             editorView.setWindowTitle(fileName);
        }
        void quitFileClicked(){
            editorView.dispose();
            fileView.dispose();
        }
        
        void setFileFileView(FileSelectView fileView){
            this.fileView=fileView;
        }
        
        void setEditorView(EditorView editorView){
            this.editorView=editorView;
        }
        
        String getFileName(){
            return fileName;
        }
        
}
