/*
 * @author      Yaotian He and Dau Ting Lam
 * @subject     CSCI 211 - Final Programming Project, Option#1
 * @time        April 23, 2013
 * 
 */
package informationretrievalsystems;


import java.util.*;
import java.io.*;
import java.net.*;
import java.util.regex.*;

public class OccurrencesOfWords {
    TreeMap tMap = new TreeMap();
    java.util.List<String> wordList = new ArrayList(5); 
    String link  ;
    String content ="";
    
    public OccurrencesOfWords(String link){
  try{  this.link = link;
        URL url = new URL(link); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line; 
        String word;
     
            
        while ((line = reader.readLine()) != null) 
      { content =content + line + "\n";
        line = removeHTML(line);// no more html
        String[] tokens = line.split("( |\\|)");
                for (int i = 0; i < tokens.length; i++ ) 
                {  if(!tokens[i].isEmpty()) 
                  { word = tokens[i].toLowerCase();
                     for(int j = 0; j<word.length();j++)// this loop is make sure the element is a word which all contain letters
                    { Character ch = new Character(word.charAt(j));
                        int n = ch - 'a';
                        if( n<0||n >25){break;}
                        else{  if(j+1==word.length()){
                               if ( tMap.containsKey(word) ) 
                                 { Count count = (Count)tMap.get(word);
                                      tMap.put(word, new Count(word, count.i+1)); } 
                                          else {tMap.put(word, new Count(word, 1));}
               
                                 }
                            }
                      } 
                }}
      }
        reader.close();
    }catch (Exception e){}
    }
    
    public TreeMap getMap()
    {return tMap;}
    
    public String getLink()
    {return link;}
    
    public ArrayList<String>  allLinks()
    {ArrayList<String> list = new ArrayList();
    Pattern linkPattern = Pattern.compile("<a\\b[^>]*href=\"(.*?)\">(.*?)</a>",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
     Matcher matcher = linkPattern.matcher(content);        
             while(matcher.find())
             {if(matcher.group(1).startsWith("http"))
             { list.add(matcher.group(1)); }
             else { Character cha = new Character(matcher.group(1).charAt(0));
                   if( Character.isLetter(cha)){list.add(link+matcher.group(1));}
             }  
             }
    return list;
                 
    } 
   
            
     public static String removeHTML(String htmlString) {
        // Remove HTML tag from java String    
        String noHTMLString = htmlString.replaceAll("\\<.*?\\>", " ");

        // Remove other possible unwanted characters
        noHTMLString = noHTMLString.replaceAll("\r", "<br/>");
        noHTMLString = noHTMLString.replaceAll("\n|\\t|\\s|\\s+", " ");
        noHTMLString = noHTMLString.replaceAll("\'", " ");
        noHTMLString = noHTMLString.replaceAll("\\\\|\\[|\\]", " ");
        noHTMLString = noHTMLString.replaceAll("\"", " ");
        noHTMLString = noHTMLString.replaceAll("\\^|\\*|\\+", " ");
        noHTMLString = noHTMLString.replaceAll("/|\\?|!", " ");
        noHTMLString = noHTMLString.replaceAll("\\(|\\)", " ");
        noHTMLString = noHTMLString.replaceAll("(\\.\\w{1,4})", " ");
        noHTMLString = noHTMLString.replaceAll("\\.|_|,|:|=", " ");
        noHTMLString = noHTMLString.replaceAll("@|#|\\;|\\$|\\&", " ");
        noHTMLString = noHTMLString.replaceAll("\\}|\\{|\\>|\\<", " ");
        noHTMLString = noHTMLString.replaceAll("~|-|\\=|\\+", " ");
        return noHTMLString;
    }
   



}
    