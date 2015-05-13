/*
 * @author      Yaotian He and Dau Ting Lam
 * @subject     CSCI 211 - Final Programming Project, Option#1
 * @time        April 23, 2013
 * 
 */
package informationretrievalsystems;

public class Bucket {
    protected String docID;
    protected Integer frqCount;
    
    public Bucket() {
        docID = "";
        frqCount = 0;
    }
    
    public Bucket(String docID, int frq) {
        this.docID = docID;
        this.frqCount = frq;
    }
}