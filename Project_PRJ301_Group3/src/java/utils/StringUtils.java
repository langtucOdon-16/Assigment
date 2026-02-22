/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author NamTQ
 * Date: 20/02/2026
 * Description: utility function for handle String
 */
public class StringUtils {
    
    public static boolean contains (String[] arr, String target){
        for (String str : arr) {
            if (str.equals(target)){
                return true;
            }
        }
        return false;
    }
    
}
