/*
 * @author      Yaotian He and Dau Ting Lam
 * @subject     CSCI 211 - Final Programming Project, Option#1
 * @time        April 23, 2013
 * 
 */
package informationretrievalsystems;

import java.util.Comparator;
import java.util.List;

// This class is for compare the Bucket, and it is used in sorting the Bucket List which in the a element of the mainMap
public class ComparatorBucket implements Comparator {
    
 public int compare(Object arg0, Object arg1)
 {Bucket bucket0 =(Bucket) arg0;
  Bucket bucket1 =(Bucket) arg1;
  int num = bucket0.frqCount.compareTo(bucket1.frqCount);
  if (num ==0)
  {return bucket0.docID.compareTo(bucket1.docID);}
  else{
  return num;
 }   
 }
}
