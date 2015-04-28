/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kotakpasir;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Aodyra
 */
public class Command {
    private Kotak kotak;
    public void Save(String[][] matrix,String filename){
        try{
            File file = new File(filename+".txt");
            if(!file.exists()){
                    file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            for (int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[0].length; j++){
                    writer.write(matrix[i][j]);
                    if(j != matrix[0].length-1){
                        writer.write(" ");   
                    }
                }
                if(i != matrix.length-1){
                    writer.newLine();                    
                }
            }
            writer.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    String[][] Load(String filename){
        String[][] matrix = new String[1][1];
        try{
            filename = filename+".txt";
            File file = new File(filename);
            Scanner input = new Scanner(new BufferedReader(new FileReader(file)));
            int i = 0;
            while(input.hasNextLine()){
                String temp[] = input.nextLine().split(" ");
                if (matrix[0][0] == null){
                    matrix = new String[temp.length][temp.length];
                }
                for(int j = 0; j < temp.length; j++){
                    matrix[i][j] = temp[j];
                }
                i++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return matrix;
    }
    
    public void Pause(){
        kotak.playpause(1);
    }
    
    public void NewWorld(String[][] S){
        kotak.setWorld(S);
    }
    
    public void chooseElement(String s){
        kotak.setElement(s);
    }
    
    public void Exit(){
        
    }
    
}
