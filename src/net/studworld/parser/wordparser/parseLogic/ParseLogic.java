/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.studworld.parser.wordparser.parseLogic;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ярослав
 */
public class ParseLogic {
    private String lol ; 
    private Document doc;
    private int [] firstIndexes ;
    private int [] lastIndexes;

    /**
     *
     */
    public void parseDoc(){
        this.doc= Jsoup.parse(lol);
        ArrayList<String> titles = new ArrayList();
         ArrayList<String> posts = new ArrayList();
        
        Element component = doc.select("style").first();
        String teg = component.data();
        String title=teg.substring(1,teg.indexOf("font-family:Helvetica;font-size:22pt;"));
        String needTeg  = title.substring(title.lastIndexOf("}"), title.lastIndexOf("{")).replaceAll("[{},.     ]","");
        String needTeg1=needTeg.replaceAll("[ \\s ]", "");
        this.setFirstIndexes(new int[this.doc.body().select("p."+needTeg1).size()]);
        this.setLastIndexes(new int[this.getFirstIndexes().length]);
        for(int i =0 ; i<this.firstIndexes.length;i++){
            titles.add(this.getArticleTitle(needTeg1, i,doc));        
        }
        for(int j = 0;j< getFirstIndexes().length; j++){
            if(j==getFirstIndexes().length){
                posts.add(this.getContentWithTegs(this.lastIndexes[j],this.doc.html().length(),this.doc.html()));    
            }else{
                posts.add(this.getContentWithTegs(this.lastIndexes[j],this.firstIndexes[j+1],this.doc.html()));
            }
       //     System.out.println(posts.get(j));
        }
        
    }

    /**
     *
     * @param teg
     * @param i
     * @param doc
     * @return
     */
    public String getArticleTitle(String teg, int  i,Document doc ){
        this.firstIndexes[i]=this.doc.html().indexOf(this.doc.body().select("p."+teg).get(i).html());
        this.lastIndexes[i]= this.doc.html().indexOf(this.doc.body().select("p."+teg).get(i).html())+this.doc.body().select("p."+teg).get(i).html().length();
        if(firstIndexes[i]==0){
            firstIndexes[i]=this.doc.html().length();
        }
        return this.doc.body().select("p."+teg).get(i).text().replaceAll("<[ a-zA-Z\\s/]+>", "");
    }
    
    /**
     *
     * @param lastIndex
     * @param firstIndex
     * @param fulText
     * @return
     */
    public String getContentWithTegs(int lastIndex ,int firstIndex, String fulText){
        return clearTegs(Jsoup.parse(fulText.substring(lastIndex, firstIndex))).replaceAll("<[ a-zA-Z/]+>","");
    }
    
    /**
     *
     * @param post
     * @return
     */
    public  String clearTegs(Document post){
         Elements link3 = post.select("img[src]");
         ArrayList<String> src = new ArrayList();
         for(int i = 0 ; i<link3.size(); i++){
             src.add(link3.get(i).attr("src"));
         }
         String [] l = new String[link3.size()];
        String lol = post.html();
        StringBuffer k = new StringBuffer(lol.subSequence(0, lol.length()));
        for(int i = 0 ; i<link3.size(); i++){
          //  System.out.println(src.get(i));
           k.replace(lol.indexOf(link3.get(i).html()),lol.indexOf(link3.get(i).html())+link3.get(i).html().length(),src.get(i));
           //  System.out.println(k);
        }
        System.out.println(k);
        return k.toString().replaceAll("<[ a-zA-Z/]+>","");
    }
    /**
     * @return the lol
     */
    public String getLol() {
        return lol;
    }

    /**
     * @param lol the lol to set
     */
    public void setLol(String lol) {
        this.lol = lol;
    }
    
    /**
     *
     */
    public void doSomething(){
        System.out.println(this.getLol());

    }

    /**
     * @return the firstIndexes
     */
    public int[] getFirstIndexes() {
        return firstIndexes;
    }

    /**
     * @param firstIndexes the firstIndexes to set
     */
    public void setFirstIndexes(int[] firstIndexes) {
        this.firstIndexes = firstIndexes;
    }

    /**
     * @return the lastIndexes
     */
    public int[] getLastIndexes() {
        return lastIndexes;
    }

    /**
     * @param lastIndexes the lastIndexes to set
     */
    public void setLastIndexes(int[] lastIndexes) {
        this.lastIndexes = lastIndexes;
    }
}
