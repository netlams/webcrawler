/*
 * @author      Yaotian He and Dau Ting Lam
 * @subject     CSCI 211 - Final Programming Project, Option#1
 * @time        April 23, 2013
 * 
 */
package informationretrievalsystems;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;
import java.net.*;

// this is the most important part in this project
public class GUI extends JFrame{
     private TextArea text = new TextArea("Display the Result:");// for displaying the result
     private JTextField seedField = new JTextField("http://jsmooth.sourceforge.net/");
     private JTextField searchField = new JTextField("search this");
     private JButton build   = new JButton("Build"); // for starting to collect the data
     private JButton save   = new JButton("Save to File"); 
     private JButton links   = new JButton("Show All Links"); // show the links which was processed 
     private JButton search   = new JButton("Search");
     private String content ="";// store all words, links, and frequence which is tranfered to a String from mainMap
     private String allLinks ="";// store all links
     private TreeMap<String,LinkedList> mainMap = new TreeMap();// fore store all store all words, links, and frequence
     private Set<String> processedSet = new TreeSet();// for storing all links which was processed
     private LinkedList<String> mainList = new LinkedList();// use in processing link
     
     
     
     
    public GUI()
    { JPanel p1 = new JPanel(new BorderLayout());
     p1.add(new Label("Enter a Seed Site:"),BorderLayout.WEST);
     p1.add(seedField,BorderLayout.CENTER);
     
      JPanel p2 = new JPanel(new GridLayout(1,3));
      p2.add (build );
      p2.add( save );
      p2.add (links );
      
     JPanel p3 = new JPanel(new BorderLayout());
     p3.add(new Label("Enter search Terms:"),BorderLayout.WEST);
     p3.add(searchField,BorderLayout.CENTER);
     p3.add(search,BorderLayout.EAST);
     
     JPanel p4 = new JPanel(new GridLayout(3,1));
     p4.add(p1);
     p4.add(p2);
     p4.add(p3);
     p4.setBorder(new TitledBorder("Information Retrieval Systems"));
    
   
     add(p4,BorderLayout.NORTH);
      add(text,BorderLayout.CENTER);
  
      
      build.addActionListener(new GUI. buildListener());
      save.addActionListener(new GUI. saveListener());
      links.addActionListener(new GUI. linksListener());
      search.addActionListener(new  GUI. searchListener());
    }
   
    // the most important part in this class
    private class buildListener implements ActionListener {
        public void actionPerformed(ActionEvent ae)  {   
    try{
        mainMap.clear();
        processedSet.clear();
        mainList.clear();
        content ="";
        allLinks ="";
        // clean all the data and  make them empty, so the last running won't effect the next running;
        
          text.setText(("Please wait a few second!"));
          String seedlink = seedField.getText();
            URL url = new URL(seedlink); // check if the links can connect
             BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
   OccurrencesOfWords occ = new OccurrencesOfWords(seedlink);//count the words which in the seed site
   mainMap = passTo(occ, mainMap);// pass the result of processing in seed site  to the mainMap
  ArrayList<String> al = occ.allLinks();
  for(int i=0; i<al.size();i++)
  { mainList.addLast(al.get(i));}// store all external links in a list
   
  while(!mainList.isEmpty()) // If the mainList is empty,  it mean no more link to process.
  { String link = mainList.getLast();// get the last element in the list for processing
   mainList.removeLast();// remove it because it will be process.
     if(!processedSet.contains(link))// if the link was processed, it doesn't need to process one more time
     {processedSet.add(link);  // add the link to processedSet, avoid one link processing more than one time
      allLinks = allLinks +"\n"+link;
     OccurrencesOfWords oc = new OccurrencesOfWords(link);// processing the link
     mainMap = passTo(oc, mainMap);// pass the result to mainMap
      ArrayList<String> alist = occ.allLinks();
     for(int i=0; i<alist.size();i++)
     { mainList.addLast(alist.get(i));}// add the new links into the mainList for future processing
      }
  }
   text.setText(("Display the Result:"));
   
   Set k = mainMap.keySet();// send all words from the mainMap to a set
    Iterator it = k.iterator();
    while(it.hasNext())
    {String word = (String)it.next();
     LinkedList<Bucket> ll = (LinkedList) mainMap.get(word).clone();
    text.append("\n"+word+"\t\t ");
    content = content +   "\n\n"+word+"\t\t ";// return a word which is in the mainMap

     while(!ll.isEmpty())// return the BuckerList which in this word
     { text.append("("+ll.getLast().docID+", "+ll.getLast().frqCount+")\t");
     content = content + "("+ll.getLast().docID+", "+ll.getLast().frqCount+")\t";
      ll.removeLast();}
      } 
     
    }catch (Exception e){JOptionPane.showMessageDialog(null,"Cannot connect the links.Please try again.");}
        }
    }
    
    

    
  private class saveListener implements ActionListener {
        public void actionPerformed(ActionEvent ae)  {
           
      try{
        File file = new File("Inverted Index.txt");
        PrintWriter output = new PrintWriter(new FileOutputStream(file) );    
        output.println(content);
       JOptionPane.showMessageDialog(null,"Data has saved to file");
        output.close();
      }
      catch (IOException ex){JOptionPane.showMessageDialog(null,"File not found.");}
        }
    }
  
   private class linksListener implements ActionListener {
        public void actionPerformed(ActionEvent ae)  {
            text.setText(allLinks);
        }
        }
  
      
  private class searchListener implements ActionListener {
        public void actionPerformed(ActionEvent ae)  {
         String terms = searchField.getText();
         String[] tokens = terms.split("[,.\\s]");
         Set searchTerm = new TreeSet();

         
            for(int i=0;i<tokens.length;i++)
            {if(tokens[i]!= null)
              {searchTerm.add(tokens[i]);
              }   
                }
          text.setText( "Display the Result:\n");
    Iterator termIt =  searchTerm.iterator();
    LinkedList<Bucket> outl = new LinkedList();
                        searchTerm.addAll(Arrays.asList(tokens));
    while(termIt.hasNext())
    {  String word = (String)termIt.next();
         word =word.toLowerCase();
        if(mainMap.containsKey(word))
        {outl.addAll((LinkedList)mainMap.get(word).clone()) ;
        }
    }
      ComparatorBucket comp = new ComparatorBucket();
       Collections.sort(outl, comp);
       Set outSet = new TreeSet();
      if(outl.isEmpty())
      {text.append("\n\n    No result");}
       
        while(!outl.isEmpty())// return the BuckerList which in this word
        { if(!outSet.contains(outl.getLast().docID))
          {outSet.add(outl.getLast().docID);
           text.append(outl.getLast().docID+"\n");}
           outl.removeLast();
        }
        }
        }
  
    
  public static TreeMap passTo(OccurrencesOfWords oc,TreeMap mainMap)
    {
    Set key = oc.tMap.keySet();
    Iterator tIter = key.iterator();
       
  while(tIter.hasNext())
  {String word =(String)tIter.next();
    Count count = (Count) oc.tMap.get(word);
    Bucket bucket = new Bucket(oc.getLink(),count.i);
    LinkedList<Bucket> tList = new LinkedList();
   
     if(mainMap.containsKey(word))
     { tList = (LinkedList) mainMap.get(word);
       tList.addFirst(bucket);
       mainMap.put(word, tList);
       ComparatorBucket comp = new ComparatorBucket();
       Collections.sort(tList, comp);}
     else{  tList.addFirst(bucket);
            mainMap.put(word, tList); }
  }
  return mainMap;
  } 
    
    
    
}
